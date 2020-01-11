package com.sel2in.eCoupons;

public class Coupon {
	private long rowNumber;

	private long name;
	private String flatNumber;
	private int numberOfPeopleLunch;
	private String mobile;
	private String eCouponCode;
	private String eCouponCode2;
	private int donation;
	private String whoCollected;
	private String payMethod;
	private String comment;

	public Coupon() {
		super();
		//
	}

	public Coupon(long rowNumber, long name, String flatNumber, int numberOfPeopleLunch, String mobile, String eCouponCode,
			String eCouponCode2, int donation, String whoCollected, String payMethod, String comment) {
		super();
		this.rowNumber = rowNumber;
		this.name = name;
		this.flatNumber = flatNumber;
		this.numberOfPeopleLunch = numberOfPeopleLunch;
		this.mobile = mobile;
		this.eCouponCode = eCouponCode;
		this.eCouponCode2 = eCouponCode2;
		this.donation = donation;
		this.whoCollected = whoCollected;
		this.payMethod = payMethod;
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Coupon [rowNumber=" + rowNumber + ", name=" + name + ", flatNumber=" + flatNumber + ", numberOfPeopleLunch="
				+ numberOfPeopleLunch + ", mobile=" + mobile + ", eCouponCode=" + eCouponCode + ", eCouponCode2=" + eCouponCode2
				+ ", donation=" + donation + ", whoCollected=" + whoCollected + ", payMethod=" + payMethod + "]";
	}

	public long getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(long rowNumber) {
		this.rowNumber = rowNumber;
	}

	public long getName() {
		return name;
	}

	public void setName(long name) {
		this.name = name;
	}

	public String getFlatNumber() {
		return flatNumber;
	}

	public void setFlatNumber(String flatNumber) {
		this.flatNumber = flatNumber;
	}

	public int getNumberOfPeopleLunch() {
		return numberOfPeopleLunch;
	}

	public void setNumberOfPeopleLunch(int numberOfPeopleLunch) {
		this.numberOfPeopleLunch = numberOfPeopleLunch;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String geteCouponCode() {
		return eCouponCode;
	}

	public void seteCouponCode(String eCouponCode) {
		this.eCouponCode = eCouponCode;
	}

	public String geteCouponCode2() {
		return eCouponCode2;
	}

	public void seteCouponCode2(String eCouponCode2) {
		this.eCouponCode2 = eCouponCode2;
	}

	public int getDonation() {
		return donation;
	}

	public void setDonation(int donation) {
		this.donation = donation;
	}

	public String getWhoCollected() {
		return whoCollected;
	}

	public void setWhoCollected(String whoCollected) {
		this.whoCollected = whoCollected;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
