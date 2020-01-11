package com.sel2in.eCoupons;

import java.util.ArrayList;

public class ECouponsApplication {
	public static void main(String[] args) {

		AddCoupon rf = new AddCoupon();
		rf.proc();
	}
	
	public static void main2(String[] args) {
		
		AddCoupon rf = new AddCoupon();
		ArrayList<String> ar = new ArrayList<String>();
		String b = null, r1 = null, r2 = null;
		for (int i = 0; i < 100; i++) {
			if (i == 10) {
				b = r1;
			} else if (i == 30) {
				b = r2;
			} else {
				b = null;
			}
			String s = rf.checkOrGenNewUnique(b);
			if (i == 2) {
				r1 = s;
			} else if (i == 22) {
				r2 = s;
			}
			ar.add(s);
		}
		for (int i = 0; i < 100; i++) {
			System.out.println(i + "|" + ar.get(i));
		}
	}

}
