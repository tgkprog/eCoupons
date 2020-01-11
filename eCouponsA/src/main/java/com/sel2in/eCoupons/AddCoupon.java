package com.sel2in.eCoupons;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.sel2in.eCoupons.csv.LineProcessor;
import com.sel2in.eCoupons.csv.TsvRead1;

public class AddCoupon implements LineProcessor {
	List<List<String>> data = new ArrayList<>();
	Map<String, String> rndNumbers = new HashMap<>();
	Random r = new Random();

	public void proc() {
		String filePath = "/home/t/Documents/t1/lohri2020/1BLohri.tsv";
		File fOut = new File("/home/t/Documents/t1/lohri2020/2BLohri.tsv");
		File ff = new File(filePath);
		TsvRead1 csvR = new TsvRead1();
		try {
			csvR.parseFile(ff, this, '\t');
			try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(fOut), StandardCharsets.UTF_8)) {
				// do stuff

				String out = null;
				int rowNum = 0;
				for (List<String> row : data) {
					/*
					 * Name Flat (block and number) Number of people for lunch Mobile (optional) e-coupon code Donation amount Who collected
					 * Pay Method Comment Shailja UPI
					 */
					if (rowNum > 1) {
						String noPeopleS = row.get(3);
						boolean needsCode = false;
						if (noPeopleS != null && noPeopleS.length() > 0) {
							try {
								int nn = Integer.parseInt(noPeopleS);
								needsCode = nn > 0;
							} catch (Exception e) {
								System.out.println("Not a number - no people row " + rowNum + " val :" + noPeopleS + ". " + e);
							}
						}
						if (needsCode) {
							checkCode(row, 5);
							checkCode(row, 10);
						}
					}
					out = String.join("\t", row);
					writer.append(out);
					writer.append("\n");
					rowNum++;
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} catch (

		Exception e) {
			//
			e.printStackTrace();
		}
	}

	private void checkCode(List<String> row, int col) {
		String c2 = row.get(col);
		c2 = checkOrGenNewUnique(c2);
		row.set(col, c2);// could check if got same value back, need to this strictly only if values differ

	}

	public String checkOrGenNewUnique(String c2) {
		boolean alreadyUsed = false;
		boolean genNew = c2 == null || c2.length() == 0;
		if (genNew == false) {
			alreadyUsed = rndNumbers.containsKey(c2);
		}
		if (alreadyUsed == false && genNew == false) {
			rndNumbers.put(c2, null);
			System.out.println("Reuse existing " + c2);
			return c2;
		} else {
			// code was already there regen it
			if (alreadyUsed) {
				System.out.println("*! code was already there regen it !* " + c2);
			}
			c2 = genACode();
			while (rndNumbers.containsKey(c2)) {
				System.out.println("*! code repeat c3 :" + c2);
				c2 = genACode();
			}

		}
		if (rndNumbers.containsKey(c2)) {
			System.out.println("Not unique code c4 :" + c2);
		} else {
			System.out.println("New code c4 :" + c2);
		}
		rndNumbers.put(c2, null);
		return c2;
	}

	private String genACode() {
		int g = r.nextInt(35);
		char c = getChar(g);
		StringBuilder code = new StringBuilder().append(c);
		code.append(c = getChar((r.nextInt(35))));
		code.append(c = getChar((r.nextInt(35))));
		// code.append(c = getChar((r.nextInt(35))));
		return code.toString();
	}

	public char getChar(int i) {

		if (i < 10)
			return (char) (i + 48);
		return (char) (65 + i - 10);

	}

	@Override
	public void proc(String line, List<String> columns, long lineNumber) {
		data.add(columns);

	}

}
