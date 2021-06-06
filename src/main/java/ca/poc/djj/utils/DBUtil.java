package ca.poc.djj.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import org.jbehave.core.model.ExamplesTable;

import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;

public class DBUtil {

	private static String encryptedDBFile = "C:\\\\COMMON-LIB\\\\selenium\\\\projects\\\\common-lib\\\\RAPID_DB.enc";
	private static String encryptedDBFile_Prod = "C:\\COMMON-LIB\\selenium\\projects\\common-lib\\RAPID_PROD_DB.enc";
	private static String encryptedDBFile_UAT = "C:\\COMMON-LIB\\selenium\\projects\\common-lib\\RAPID_UAT_DB.enc";
	private static String strDecryptedDB = "";

	// old version
	private static String connStrProd = "";
	private static String connStrDev = "";
	private static String username = "";
	private static String password = "";

	// new version
	private static String connStrRAPID = "";
	public static String connStrRAPID_CC = "";

	public static void main(String[] args) throws Exception {
		EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
		String env = variables.getProperty("test.TestEnvironment");
		// new version
		String encryptedDB = searchTestRunRecord("401", "4747", "ONLAND", env);
		if (encryptedDB != null)
			SelectSQL(encryptedDB, connStrRAPID_CC, "select * from CCTransaction where ApplicationName = 'ONLAND'");
		else
			System.out.println("Not performing.");
		// Insert record
		// ExecSQL(encryptedDB, connStrRAPID_CC, "insert into CCTransaction (TransactionID, RequestID, RunID, ApplicationName, TestEnvironment, TransactionAmount, TransactionTime) values (101101,1,1,'ONLAND','UAT',99.9,GETDATE() )");
		// Update record
		// ExecSQL(encryptedDB, connStrRAPID_CC, "update CCTransaction set StatusCode=1, ReversedInd='Y', ReversedTime=GETDATE() where TransactionID=101101");
		// Delete all record
		// ExecSQL(encryptedDB, connStrRAPID_CC, "delete from CCTransaction where TransactionID=101101");
		// SelectSQL(encryptedDB, connStrRAPID_CC, "select * from CCTransaction");

		// old version
		// SelectSQL("UAT", "select * from CCTransaction where ApplicationName = 'ONLAND'");
		// SelectSQL("Prod", "select * from CCTransaction where ApplicationName = 'ONLAND'");
		// Insert record
		// ExecSQL("UAT", "insert into CCTransaction (TransactionID, RequestID, RunID, ApplicationName, TestEnvironment, TransactionAmount, TransactionTime) values (101,1,1,'CSP','UAT',12.4,GETDATE() )");
		// Update record
		// ExecSQL("UAT", "update CCTransaction set StatusCode=1, ReversedInd='Y', ReversedTime=GETDATE() where TransactionID=101");
		// Delete all record
		// ExecSQL("UAT", "delete from CCTransaction where TransactionID=101");
		// SelectSQL("UAT", "select * from CCTransaction");
	}

	public static String searchTestRunRecord(String requestID, String runID, String appName, String env) throws Exception {
		String SQL = "select count(*) from TestRun " +
				"where RequestID = '" + requestID + "' " +
				"and RunID = '" + runID + "' " +
				"and TestToolName = 'SELENIUM' " +
				"and ApplicationName = '" + appName + "' " +
				"and TestEnvironment = '" + env + "' " +
				"and Status = 'Test Run Started' ";
		initConnString(encryptedDBFile_Prod);
		if (SelectSQL(encryptedDBFile_Prod, connStrRAPID, SQL).contains("|1|"))
			return encryptedDBFile_Prod;
		initConnString(encryptedDBFile_UAT);
		if (SelectSQL(encryptedDBFile_UAT, connStrRAPID, SQL).contains("|1|"))
			return encryptedDBFile_UAT;
		System.out.println("Could not find any matching DB. Assign UAT DB");
		return encryptedDBFile_UAT;
	}

	private static void initConnString(String encryptedDBFile) {
		strDecryptedDB = EncryptUtil.decryptFile(encryptedDBFile);
		ExamplesTable theFieldTable = new ExamplesTable(strDecryptedDB);
		for (Map<String, String> row : theFieldTable.getRows()) {
			connStrRAPID = row.get("connStrRAPID");
			connStrRAPID_CC = row.get("connStrRAPID_CC");
		}
		connStrRAPID = connStrRAPID + ";integratedSecurity=true;";
		connStrRAPID_CC = connStrRAPID_CC + ";integratedSecurity=true;";
	}

	public static String SelectSQL(String encryptedDBFile, String connStr, String SQL) throws Exception {
		initConnString(encryptedDBFile);
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		// Open a connection
		Connection conn = null;
		// System.out.println(connStr);
		conn = DriverManager.getConnection(connStr);

		Statement stmt = conn.createStatement();
		// Execute a query
		ResultSet resultSet = stmt.executeQuery(SQL);
		System.out.println(resultSet.toString());
		String strReturn = null;

		while (resultSet.next()) {
			int numOfCols = resultSet.getMetaData().getColumnCount();
			strReturn = "|";
			for (int i = 1; i <= numOfCols; i++) {
				strReturn = strReturn + resultSet.getString(i) + "|";
			}
			strReturn = strReturn + "\r\n";
		}
		System.out.println(strReturn);

		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (Exception e) {
			}
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
		return strReturn;
	}

	public static void ExecSQL(String encryptedDBFile, String connStr, String SQL) throws Exception {
		initConnString(encryptedDBFile);
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		// Open a connection
		Connection conn = null;
		// System.out.println(connStr);
		conn = DriverManager.getConnection(connStr);

		Statement stmt = conn.createStatement();
		// Execute a query
		stmt.executeUpdate(SQL);

		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
	}

	// old version below
	private static void initConnString() {
		strDecryptedDB = EncryptUtil.decryptFile(encryptedDBFile);
		ExamplesTable theFieldTable = new ExamplesTable(strDecryptedDB);
		for (Map<String, String> row : theFieldTable.getRows()) {
			connStrProd = row.get("connStrProd");
			connStrDev = row.get("connStrDev");
			username = row.get("username");
			password = row.get("password");
		}
		connStrProd = connStrProd + ";integratedSecurity=true;";
		connStrDev = connStrDev + ";integratedSecurity=true;";
	}

	public static String SelectSQL(String env, String SQL) throws Exception {
		initConnString();
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		// Open a connection
		Connection conn = null;
		if (env.equals("UAT")) {
			// conn = DriverManager.getConnection(connStrDev, username, password);
			conn = DriverManager.getConnection(connStrDev);
		} else {
			// conn = DriverManager.getConnection(connStrProd, username, password);
			conn = DriverManager.getConnection(connStrProd);
		}

		Statement stmt = conn.createStatement();
		// Execute a query
		ResultSet resultSet = stmt.executeQuery(SQL);
		System.out.println(resultSet.toString());
		String strReturn = null;

		while (resultSet.next()) {
			int numOfCols = resultSet.getMetaData().getColumnCount();
			strReturn = "|";
			for (int i = 1; i <= numOfCols; i++) {
				strReturn = strReturn + resultSet.getString(i) + "|";
			}
			strReturn = strReturn + "\r\n";
		}
		System.out.println(strReturn);

		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (Exception e) {
			}
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
		return strReturn;
	}

	public static void ExecSQL(String env, String SQL) throws Exception {
		initConnString();
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		// Open a connection
		Connection conn = null;
		if (env.equals("UAT")) {
			// conn = DriverManager.getConnection(connStrDev, username, password);
			conn = DriverManager.getConnection(connStrDev);
		} else {
			// conn = DriverManager.getConnection(connStrProd, username, password);
			conn = DriverManager.getConnection(connStrProd);
		}
		Statement stmt = conn.createStatement();
		// Execute a query
		stmt.executeUpdate(SQL);

		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
	}
}
