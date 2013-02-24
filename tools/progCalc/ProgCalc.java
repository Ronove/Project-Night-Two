/**************************************
**        Progress Calculator        **
***************************************
**        Developed by Yirba         **
**   for Spider Lily Translations    **
** http://spiderlilytranslations.com **
**************************************/

import java.io.*;

class ProgCalc {

	// path of directory containing script files relative to this Java program
	private static final String REL_FILE_PATH = "../../";
	private static String[][] chapters = new String[8][3];
	private static int[] chapterLines = new int[8];
	
	// set some data about each script file
	private static void initChapters() {
		chapters[0][0] = "Chapter1.script"; chapters[0][1] = "Chapter 1"; chapterLines[0] = 327;
		chapters[1][0] = "Chapter2.script"; chapters[1][1] = "Chapter 2"; chapterLines[1] = 1110;
		chapters[2][0] = "Chapter3.script"; chapters[2][1] = "Chapter 3"; chapterLines[2] = 381;
		chapters[3][0] = "Chapter4.script"; chapters[3][1] = "Chapter 4"; chapterLines[3] = 1120;
		chapters[4][0] = "Chapter5.script"; chapters[4][1] = "Chapter 5"; chapterLines[4] = 569;
		chapters[5][0] = "Chapter6.script"; chapters[5][1] = "Chapter 6"; chapterLines[5] = 1268;
		chapters[6][0] = "Chapter7.script"; chapters[6][1] = "Chapter 7"; chapterLines[6] = 960;
		chapters[7][0] = "ChapterExtra.script"; chapters[7][1] = "Chapter ?"; chapterLines[7] = 179;
	}
	
	private static int countLines(String filename) {
		int counter = 0; // counts lines containing '`'
		try {
			FileInputStream fis = new FileInputStream(REL_FILE_PATH + filename);
			InputStreamReader isr = new InputStreamReader(fis, "Shift_JIS");
			BufferedReader in = new BufferedReader(isr);
			String line;
			while ((line = in.readLine()) != null) {
				if (line.contains("`")) {
					counter++;
				}
			}
			fis.close();
			isr.close();
			in.close();
			return counter;
		}
		catch (Exception ex) {
			System.err.println("ERROR: File could not be read. " + ex);
			System.exit(-1);
		}
		return counter;
	}
	
	public static void main(String[] args) {
		initChapters();
		int totalEN = 0; // total number of translated lines in all chapters
		int totalLines = 0; // total number of (Japanese) lines in all chapters
		for (int i=0 ; i < chapters.length ; i++) {
			int enLines = countLines(chapters[i][0]); // translated lines
			totalEN += enLines;
			int chapterTotal; // Number of (Japanese) lines in this chapter
			
			if (enLines > chapterLines[i]) {
				chapterTotal = enLines;
			}
			else {
				chapterTotal = chapterLines[i];
			}
			
			totalLines += chapterTotal;
			
			System.out.print(chapters[i][1] + ": ");
			System.out.print(String.format("%4s", Integer.toString(enLines)));
			System.out.print(" / " + String.format("%4s", Integer.toString(chapterTotal)));
			System.out.println(" (" + (int) (((float) enLines / (float) chapterTotal) * 100) + "%)");
		}
		System.out.print("Total: " + String.format("%8s", Integer.toString(totalEN)));
		System.out.print(" / " + String.format("%4s", Integer.toString(totalLines)));
		System.out.println(" (" + (int) (((float) totalEN / (float) totalLines) * 100) + "%)");
	}

}