package com.sel2in.eCoupons.csv;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;

import com.sel2in.eCoupons.Coupon;

public class TsvRead1 {
	
	public static final char DEFAULT_SEPARATOR = '\t';
	public static final char COMMA = ',';
	public static final char TAB = '\t';
	private static final char DEFAULT_QUOTE = '\"';
	Gson gson = new Gson();
	
	public void parseFile(File ff, LineProcessor proc, char sep) throws Exception {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(ff));
		Scanner scanner = new Scanner(bis);
		long cnt = 0;
		while (scanner.hasNext()) {
			String line = scanner.nextLine(); 
			List<String> fields = parseLine(line, sep);
			// System.out.println("Country [name " + fields.get(0) + ", iso= " +
			// fields.get(1) + " , name=" + fields.get(2) + "]");
			proc.proc(line, fields, cnt);
			if(cnt < Long.MAX_VALUE) {
				cnt++;
			}
		}
		System.out.println("\\n****\\n\\nProc\'ed" + cnt + " lines.\n****\n\n");
		scanner.close();
		bis.close();

	}

	public List<String> parseLine(String cvsLine, char sep) {
		
		final char separators = sep;
		final char customQuote = DEFAULT_QUOTE;
		List<String> result = new ArrayList<>();

		// if empty, return!
		if (cvsLine == null && cvsLine.isEmpty()) {
			return result;
		}
		StringBuffer curVal = new StringBuffer();
		boolean inQuotes = false;
		boolean startCollectChar = false;
		boolean doubleQuotesInColumn = false;

		char[] chars = cvsLine.toCharArray();
		int last = 0;
		int curr = 0;
		int len = 0;
		for (char ch : chars) {
			
			if(ch == sep) {		
				result.add(cvsLine.substring(last, curr));
				last = curr + 1;
			}
			curr++;
		}
		if(curr > last) {
			result.add(cvsLine.substring(last, curr));
		}

		//result.add(curVal.toString());

		return result;
	}
}


