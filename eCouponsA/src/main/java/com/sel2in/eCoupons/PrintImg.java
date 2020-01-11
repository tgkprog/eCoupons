package com.sel2in.eCoupons;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

import com.sel2in.eCoupons.csv.LineProcessor;
import com.sel2in.eCoupons.csv.TsvRead1;

public class PrintImg implements LineProcessor {
	List<List<String>> data = new ArrayList<>();
	Map<String, String> rndNumbers = new HashMap<>();
	Random r = new Random();
	BufferedImage baseImg = null;
	File baseOutImgFolder = new File("/home/t/Documents/t1/lohri2020/imgs3");

	Font fntSmll;
	FontMetrics fmSmll = null;

	Font fntMed;
	FontMetrics fmMed = null;

	Font fntBig;
	FontMetrics fmBig = null;

	Color LIGHT_GRAY = new Color(60, 60, 60);

	public static void main(String[] args) {
		PrintImg rf = new PrintImg();
		rf.proc();
	}

	public void proc() {
		String filePath = "/home/t/Documents/t1/lohri2020/2BLohri.tsv";
		File fOut = new File("/home/t/Documents/t1/lohri2020/imgs");
		try {
			baseOutImgFolder.mkdir();
			baseImg = ImageIO.read(new File("/home/t/Documents/t1/lohri2020/d2.png"));
			Graphics2D g2d = baseImg.createGraphics();
			g2d.setPaint(Color.gray);

			fntSmll = new Font("Ubuntu", Font.PLAIN, 7);
			g2d.setFont(fntSmll);
			fmSmll = g2d.getFontMetrics();
			
			fntMed = new Font("Ubuntu", Font.PLAIN, 12);
			g2d.setFont(fntMed);
			fmMed = g2d.getFontMetrics();

			fntBig = new Font("Ubuntu", Font.PLAIN, 28);
			g2d.setFont(fntBig);
			fmBig = g2d.getFontMetrics();

			File ff = new File(filePath);
			TsvRead1 csvR = new TsvRead1();

			csvR.parseFile(ff, this, '\t');

			// do stuff

			int rowNum = 0;
			for (List<String> row : data) {
				/*
				 * Name Flat (block and number) Number of people for lunch Mobile (optional) e-coupon code Donation amount Who collected Pay
				 * Method Comment Shailja UPI
				 */
				if (rowNum > 1) {
					String noPeopleS = row.get(3);
					boolean hasPeople = false;
					if (noPeopleS != null && noPeopleS.length() > 0) {
						try {
							int nn = Integer.parseInt(noPeopleS);
							hasPeople = nn > 0;
						} catch (Exception e) {
							System.out.println("Not a number - no people row " + rowNum + " val :" + noPeopleS + ". " + e);
						}
					}
					if (hasPeople) {

					}
				}

				rowNum++;
			}

		} catch (

		Exception e) {
			//
			e.printStackTrace();
		}
	}

	@Override
	public void proc(String line, List<String> columns, long lineNumber) {
		try {
			// data.add(columns);
			if (lineNumber < 2)
				return;

			// if (lineNumber > 10){
			// return;}

			String noPeopleS = columns.get(3);
			boolean needsCode = false;
			if (noPeopleS != null && noPeopleS.length() > 0) {
				try {
					int nn = Integer.parseInt(noPeopleS);
					needsCode = nn > 0;
				} catch (Exception e) {
					System.out.println("Not a number - no people row " + lineNumber + " val :" + noPeopleS + ". " + e);
				}
			}
			if (needsCode == false) {
				return;
			}

			ColorModel cm = baseImg.getColorModel();
			boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
			WritableRaster raster = baseImg.copyData(null);
			BufferedImage img2 = new BufferedImage(cm, raster, isAlphaPremultiplied, null);
			Graphics2D g2d = img2.createGraphics();
			g2d.setPaint(Color.BLACK);
			g2d.setFont(fntBig);
			String s = "Hello " + columns.get(1);

			int x = 15;
			int y = 291 + fmBig.getHeight();
			g2d.drawString(s, x, y);

			s = "of flat " + columns.get(2) + ", people : " + columns.get(3);
			y += 2 + fmBig.getHeight();
			g2d.drawString(s, x, y);

			s = columns.get(5);
			// if(s != null && s.length() > 1) s= s.substring(1);
			s = "e Coupon : " + s + ", (ref " + columns.get(6) + ")";
			y += 2 + fmBig.getHeight();
			g2d.drawString(s, x, y);

			g2d.setPaint(LIGHT_GRAY);
			g2d.setFont(fntSmll);
			s = columns.get(10);
			// if(s != null && s.length() > 1) s= s.substring(1);
			g2d.drawString(s, 320, 480);

			g2d.setFont(fntMed);
			s = "Please be at Club house hall by 1:25pm";
			g2d.setPaint(Color.BLACK);
			y += 15 + fmMed.getHeight();
			g2d.drawString(s, x, y);

			g2d.dispose();
			String fileName = "";
			String nm = columns.get(6);
			if (nm == null || nm.length() == 0)
				nm = "Un";
			if (nm.length() < 2) {
				nm = nm + "-";
			}
			String ln = String.format("%02d", lineNumber);
			fileName = ln + "_" + nm + "_";
			fileName = fileName.substring(0, 2);
			fileName = fileName + columns.get(1) + "_" + columns.get(2) + "_" + ".png";

			File outFolder = new File(baseOutImgFolder, nm);
			outFolder.mkdir();
			File outputfile = new File(outFolder, fileName);
			ImageIO.write(img2, "png", outputfile);
		} catch (Exception e) {
			System.out.println("proc img line " + lineNumber + " " + e);
			e.printStackTrace();
		}

	}

}
