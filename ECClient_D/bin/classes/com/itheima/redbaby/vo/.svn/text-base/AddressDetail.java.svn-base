package com.itheima.redbaby.vo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 地址详细
 * 
 * @author liu
 * 
 */
public class AddressDetail implements Parcelable {

	/** 地址簿ID */
	private int id;

	/** 收货人姓名 */
	private String name;

	/** 手机号码 */
	private String phonenumber;

	/** 固定电话 */
	private String fixedtel;
	/** 省ID */
	private int provinceid;

	/** 市ID */
	private int cityid;

	/** 地区ID */
	private int areaid;

	/** 订单地址 */
	private String areadetail;

	/** 邮编 */
	private String zipcode;

	public AddressDetail() {
	}

	public AddressDetail(int id, String name, String phonenumber, String fixedtel, int provinceid, int cityid,
			int areaid, String areadetail, String zipcode) {
		super();
		this.id = id;
		this.name = name;
		this.phonenumber = phonenumber;
		this.fixedtel = fixedtel;
		this.provinceid = provinceid;
		this.cityid = cityid;
		this.areaid = areaid;
		this.areadetail = areadetail;
		this.zipcode = zipcode;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getFixedtel() {
		return fixedtel;
	}

	public void setFixedtel(String fixedtel) {
		this.fixedtel = fixedtel;
	}

	public int getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(int provinceid) {
		this.provinceid = provinceid;
	}

	public int getCityid() {
		return cityid;
	}

	public void setCityid(int cityid) {
		this.cityid = cityid;
	}

	public int getAreaid() {
		return areaid;
	}

	public void setAreaid(int areaid) {
		this.areaid = areaid;
	}

	public String getAreadetail() {
		return areadetail;
	}

	public void setAreadetail(String areadetail) {
		this.areadetail = areadetail;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressDetail other = (AddressDetail) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AddressDetail [id=" + id + ", name=" + name + ", phonenumber=" + phonenumber + ", fixedtel=" + fixedtel
				+ ", provinceid=" + provinceid + ", cityid=" + cityid + ", areaid=" + areaid + ", areadetail="
				+ areadetail + ", zipcode=" + zipcode + "]";
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(name);
		dest.writeString(phonenumber);
		dest.writeString(fixedtel);

		dest.writeInt(provinceid);
		dest.writeInt(cityid);
		dest.writeInt(areaid);

		dest.writeString(areadetail);
		dest.writeString(zipcode);

	}

	public AddressDetail(Parcel in) {
		id = in.readInt();
		name = in.readString();
		phonenumber = in.readString();
		fixedtel = in.readString();

		provinceid = in.readInt();
		cityid = in.readInt();
		areaid = in.readInt();

		areadetail = in.readString();
		zipcode = in.readString();
	}

	public static final Creator<AddressDetail> CREATOR = new Creator<AddressDetail>() {

		@Override
		public AddressDetail createFromParcel(Parcel source) {
			return new AddressDetail(source);
		}

		@Override
		public AddressDetail[] newArray(int size) {
			return new AddressDetail[size];
		}
	};

}
