package ca.poc.djj.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;


public class PDFCompare {
	private static String patternFile = "C:\\COMMON-LIB\\selenium\\projects\\common-lib\\pattern.txt";

	public static String removePattern(String input, String pattern) {
		input = input.replaceAll(pattern, "");
		return input;
	}

	public static String removePattern(String input, List<String> pattern) {
		for (String item : pattern) {
			input = input.replaceAll(item, "");
		}
		return input;
	}

	public static List<String> initPattern() {
		List<String> excludePattern = new ArrayList<String>();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(patternFile));
			String line = reader.readLine();
			while (line != null) {
				// System.out.println(line);
				excludePattern.add(line);
				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return excludePattern;
	}

	public static Boolean pdfCompare(String TC_Folder, String fileName, List<String> excludePattern) {
		String outputPath = TC_Folder + "/DocFiles/";
		String baselinePath = TC_Folder + "/DocFilesBaseline/";
		return pdfCompare(outputPath, baselinePath, fileName, excludePattern);
	}

	public static Boolean pdfCompare(String outputPath, String baselinePath, String fileName, List<String> excludePattern) {
		String ExpectedFilename = baselinePath + fileName;
		String ActualFilename = outputPath + fileName;
		File F_Expected = new File(ExpectedFilename);
		File F_Actual = new File(ActualFilename);

		// String imageTempPath = outputPath + "/temp/";
		String imageTempPath = "c:/temp/images/";
		File folder = new File(imageTempPath);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		Boolean IdentPage = true;
		Boolean IdentImage = true;
		Boolean IdentText = true;

		PDDocument doc1 = null;
		try {
			doc1 = PDDocument.load(F_Expected);
		} catch (InvalidPasswordException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		PDDocument doc2 = null;
		try {
			doc2 = PDDocument.load(F_Actual);
		} catch (InvalidPasswordException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int DocNumPages1 = doc1.getNumberOfPages();
		int DocNumPages2 = doc2.getNumberOfPages();
		log("DocNumPages1: " + DocNumPages1);
		log("DocNumPages2: " + DocNumPages2);

		// @SuppressWarnings("rawtypes")
		PDPageTree list1 = doc1.getPages();
		PDPageTree list2 = doc2.getPages();
		log("list1 size : " + list1.getCount());
		log("list2 size : " + list2.getCount());

		PDFTextStripper stripper1 = null;
		try {
			stripper1 = new PDFTextStripper();
		} catch (IOException e) {
			e.printStackTrace();
		}
		PDFTextStripper stripper2 = null;
		try {
			stripper2 = new PDFTextStripper();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String pagetext1 = null;
		String pagetext2 = null;

		PDPage page1;
		PDPage page2;
		PDResources pdResources1;
		PDResources pdResources2;
		int totalImages1 = 1;
		int totalImages2 = 1;

		if (list1.getCount() == list2.getCount()) {
			for (int i = 1; i <= list1.getCount(); i++) {
				log("*** Comparing Image in Page: " + i);
				page1 = list1.get(i - 1);
				page2 = list2.get(i - 1);
				pdResources1 = page1.getResources();
				pdResources2 = page2.getResources();

				// Extract images using PDFUtil
				log("Extracting images...");

				// Images from Doc 1
				int pageImageStart1 = totalImages1;
				for (COSName c : pdResources1.getXObjectNames()) {
					PDXObject o = null;
					try {
						o = pdResources1.getXObject(c);
					} catch (IOException e) {
						e.printStackTrace();
					}
					if (o instanceof PDImageXObject) {
						// String fname1 = outputPath + "temp/" + fileName + "_page_" + i + "_" + c.getName() + "_A." + "png";
						String fname1 = imageTempPath + fileName + "_page_" + i + "_" + c.getName() + "_A." + "png";

						try {
							ImageIO.write(((PDImageXObject) o).getImage(), "png", new File(fname1));
						} catch (IOException e) {
							e.printStackTrace();
						}
						totalImages1++;
					}
				}
				int pageImageEnd1 = totalImages1 - 1;
				log("Expected page Image Begin/End: " + pageImageStart1 + " to " + pageImageEnd1);

				// Images from Doc 2
				int pageImageStart2 = totalImages2;
				for (COSName c : pdResources2.getXObjectNames()) {
					PDXObject o = null;
					try {
						o = pdResources2.getXObject(c);
					} catch (IOException e) {
						e.printStackTrace();
					}
					if (o instanceof org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject) {
						// String fname2 = outputPath + "temp/" + fileName + "_page_" + i + "_" + c.getName() + "_B." + "png";
						String fname2 = imageTempPath + fileName + "_page_" + i + "_" + c.getName() + "_B." + "png";

						try {
							ImageIO.write(((org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject) o).getImage(), "png", new File(fname2));
						} catch (IOException e) {
							e.printStackTrace();
						}
						totalImages2++;
					}
				}
				int pageImageEnd2 = totalImages2 - 1;
				log("Actual page Image Begin/End: " + pageImageStart2 + " to " + pageImageEnd2);

				// Compare extracted images
				int NumOfImagesInPage1 = pageImageEnd1 - pageImageStart1 + 1;
				int NumOfImagesInPage2 = pageImageEnd2 - pageImageStart2 + 1;
				log("Number Of Images In Page (Expect vs Actual): " + NumOfImagesInPage1 + " : " + NumOfImagesInPage2);

				if (NumOfImagesInPage1 == NumOfImagesInPage2) {
					FileFilter fileFilter = new WildcardFileFilter(fileName + "_*_A." + "png");
					File[] files = new File(imageTempPath).listFiles(fileFilter);
					for (int img = pageImageStart1 - 1; img < pageImageEnd1; img++) {
						File F_image1 = files[img];
						File F_image2 = new File(F_image1.getAbsoluteFile().getPath().toString().replace("_A.png", "_B.png"));
						Boolean ImageIdentical = false;
						try {
							ImageIdentical = FileUtils.contentEquals(F_image1, F_image2);
						} catch (IOException e) {
							e.printStackTrace();
						}
						if (ImageIdentical == true) {
							log("Image (" + img + ") " + F_image1.getAbsoluteFile().getPath().toString() + " Identical: " + ImageIdentical);
						} else {
							log("Image (" + img + ") " + F_image1.getAbsoluteFile().getPath().toString() + " Identical: " + ImageIdentical);
							IdentImage = false;
						}
					}
				} else {
					log("Different number of image objects in compared pages");
					IdentImage = false;
				}

				///////////////////////////////////////////////////////////////////////////
				// Compare text
				///////////////////////////////////////////////////////////////////////////
				stripper1.setStartPage(i);
				stripper1.setEndPage(i);
				stripper2.setStartPage(i);
				stripper2.setEndPage(i);

				log("*** Comparing text in Page: " + stripper1.getEndPage());
				try {
					pagetext1 = stripper1.getText(doc1);
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					pagetext2 = stripper2.getText(doc2);
				} catch (IOException e) {
					e.printStackTrace();
				}
				// log("Before removing pattern: " + pagetext1);
				pagetext1 = removePattern(pagetext1, excludePattern);
				pagetext2 = removePattern(pagetext2, excludePattern);
				// log("After removing pattern: " + pagetext1);

				String[] lines1 = pagetext1.split("\\r?\\n");
				String[] lines2 = pagetext2.split("\\r?\\n");
				log("Line in first page  : " + lines1.length);
				log("Line in second page : " + lines2.length);
				if (lines1.length == lines2.length) {
					for (int a = 0; a < lines1.length; a++) {
						// log"Text: " + lines1[a]

						String[] cols1 = lines1[a].split("\\s+");
						String[] cols2 = lines2[a].split("\\s+");
						if (cols1.length == cols2.length) {
							for (int b = 0; b < cols1.length; b++) {
								// log.info(cols1[b].toString()+" - - - - "+cols2[b].toString());
								// log.info("Page : "+i+" Row : "+a+" Column : "+b);
								if (!cols1[b].toString().equalsIgnoreCase(cols2[b].toString())) {
									log("Text (1): " + lines1[a]);
									log("Text (2): " + lines2[a]);
									log("Not matched : " + cols2[b].toString());
									log("Page : " + i + " Row : " + a + " Column : " + b);
									IdentText = false;
								}
							}
						} else {
							log("column are not equals");
							IdentText = false;
						}
					}
				} else {
					log("Line are not equal ");
					IdentText = false;
				}
			}
			IdentPage = IdentText && IdentImage;
		} else {
			log("Page size is not equal");
			IdentPage = false;
		}
		try {
			doc1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			doc2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return IdentPage;
	}

	public static void log(String msg) {
		System.out.println(msg);
	}

	public static void test1() {
		// String TC_Folder = "C:\\CS\\TSB\\DataSink\\TSB_UAT\\PINS-GetParcelRegister\\Positive";
		// String TC_FIle = "TestCase_01.PDF";
		String TC_Folder = "C:\\tmp";
		String TC_FIle = "TestCase_40.PDF";

		List<String> excludePattern = initPattern();
		if (pdfCompare(TC_Folder, TC_FIle, excludePattern)) {
			log("PDFs are identical.");
		}
	}

	public static void test2() throws Exception {
		System.setProperty("webdriver.chrome.driver", "c:/CSP/Drivers/chromedriver.exe");
		System.setProperty("webdriver.ie.driver", "c:/CSP/Drivers/IEDriverServer3.141.5_win32.exe");

		String TC_Folder = "C:\\tmp";
		String TC_File = "ROSCO_Pricing_Guide_20150921.pdf";

		TestResponseCode.DownloadPage("https://www.cspuat.ca/content/support_en/pdf/ROSCO_Pricing_Guide_20150921.pdf", "ie", false);
	//	SavePDF.user_saves_pdf(TC_Folder + "\\DocFiles", "");

		TestResponseCode.CloseDownloadPage();
		List<String> excludePattern = initPattern();
		if (pdfCompare(TC_Folder, TC_File, excludePattern)) {
			log("PDFs are identical.");
		}
	}

	public static void main(String args[]) {
		test1();
	}
}
