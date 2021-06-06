package ca.poc.djj.utils;

import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptUtil {

	private static String strMasterKey = "";
	// private static String strMasterKeyFile = "C:\\AutoTest\\RAPID\\Keys\\Master.key";
	private static String strMasterKeyFile = "\\\\dcnas1\\departments\\SpecializedServices\\FORBIDDEN\\Master.key";

	public static SecretKeySpec setKey(String myKey) {
		byte[] key;
		MessageDigest sha = null;
		SecretKeySpec secretKey = null;
		// System.out.println("Encryption key: " + myKey);
		try {
			key = myKey.getBytes("UTF-8");
			// System.out.println(key.length);
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16); // use only first 128 bit
			// System.out.println(key.length);
			// System.out.println(new String(key, "UTF-8"));
			secretKey = new SecretKeySpec(key, "AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return secretKey;
	}

	public static String encrypt(String strToEncrypt, SecretKeySpec secretKey) {
		String strEncrypted = null;
		try {
			// System.out.println("String to Encrypt: " + strToEncrypt);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			strEncrypted = Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
			// System.out.println("Encrypted: " + strEncrypted);
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return strEncrypted;
	}

	public static String decrypt(String strToDecrypt, SecretKeySpec secretKey) {
		String strDecrypted = null;
		try {
			// System.out.println("String to Decrypt : " + strToDecrypt);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			strDecrypted = new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		return strDecrypted;
	}

	static public String convertHexToString(String hex) {
		StringBuilder sb = new StringBuilder();
		// StringBuilder temp = new StringBuilder();

		// 49204c6f7665204a617661 split into two characters 49, 20, 4c...
		for (int i = 0; i < hex.length() - 1; i += 2) {
			// grab the hex in pairs
			String output = hex.substring(i, (i + 2));
			// convert hex to decimal
			int decimal = Integer.parseInt(output, 16);
			// convert the decimal to character
			sb.append((char) decimal);
			// temp.append(decimal);
		}
		// System.out.println("Decimal : " + temp.toString());
		return sb.toString();
	}

	static String readFile(String path) {
		byte[] encoded = null;
		int maxTries = 10;
		for (int count = 0; count <= maxTries; count++) {
			try {
				System.out.println("Reading from " + path);
				encoded = Files.readAllBytes(Paths.get(path));
				break;
			} catch (Exception e) {
				System.out.println("Retry #" + count);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException eWait) {
					eWait.printStackTrace();
				}
				if (count == maxTries)
					assertFalse("Exception: " + e.toString() + "Message: " + e.getMessage(), true);
			}
		}
		return new String(encoded);
	}

	static void readMasterKeyFile() {
		byte[] encoded = null;
		int maxTries = 10;
		for (int count = 0; count < maxTries; count++) {
			try {
				encoded = Files.readAllBytes(Paths.get(strMasterKeyFile));
				break;
			} catch (IOException e) {
				System.out.println("Retry #" + count);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException eWait) {
					eWait.printStackTrace();
				}
				if (count == maxTries)
					e.printStackTrace();
			}
		}
		strMasterKey = new String(encoded);
		strMasterKey = convertHexToString(strMasterKey);
		// System.out.println("Master Key : " + strMasterKey);
	}

	public static void writeFile(String strpath, String fileContent) throws IOException {
		Path path = Paths.get(strpath);
		Files.write(path, fileContent.getBytes());
	}

	public static void encryptFile(String strFileToEncrypt, String key) {
		// 1. Encrypt original file with user provided key
		// 2. Encrypt user provided key with Master key
		// 3. Concatenate encrypted file and key, join by "_"
		// 4. Encrypt Concatenate string with Master key and output to file
		readMasterKeyFile();
		SecretKeySpec secretKey1;
		SecretKeySpec secretKey2;
		secretKey1 = setKey(key);
		secretKey2 = setKey(strMasterKey);

		String strEnc1 = null;
		String strEnc2 = null;
		String strEncPswd = null;
		System.out.println("Encrypting file: " + strFileToEncrypt);

		String strToEncrypt = readFile(strFileToEncrypt);
		// System.out.println("");
		strEnc1 = encrypt(strToEncrypt.trim(), secretKey1);
		strEncPswd = encrypt(key.trim(), secretKey2);
		strToEncrypt = strEnc1 + "_" + strEncPswd;
		strEnc2 = encrypt(strToEncrypt.trim(), secretKey2);
		String strOutputFile = strFileToEncrypt.substring(0, strFileToEncrypt.lastIndexOf(".")) + ".enc";
		System.out.println("Output File: " + strOutputFile);
		try {
			writeFile(strOutputFile, strEnc2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("");
	}

	public static String decryptFile(String strpath) {
		// 1. Decrypt file with Master key
		// 2. Split decrypted message into message and key by "_"
		// 3. Decrypt user provided key with Master key
		// 4. Decrypt original file with user provided key and return value
		readMasterKeyFile();
		SecretKeySpec secretKey1;
		SecretKeySpec secretKey2;
		secretKey2 = setKey(strMasterKey);

		String strToDecrypt = readFile(strpath);
		String strDecrypted = decrypt(strToDecrypt, secretKey2);
		// System.out.println("Decrypted: " + strDecrypted);
		// System.out.println("");
		int ndxUnderScore = strDecrypted.indexOf("_");
		String strTemp1 = strDecrypted.substring(0, ndxUnderScore);
		String strTemp2 = strDecrypted.substring(ndxUnderScore + 1);
		String strDecryptedPswd = decrypt(strTemp2, secretKey2);
		// System.out.println("Decrypted Password: " + strDecryptedPswd);
		// System.out.println("");
		secretKey1 = setKey(strDecryptedPswd);
		String strDecryptedMsg = decrypt(strTemp1, secretKey1);
		// System.out.println("Decrypted Message: " + strDecryptedMsg);

		return strDecryptedMsg;
	}

}