package org.javaBasic;

public enum WeekDayDes {
	Monday("周一","一周的第一天"),Tuesday("周二","一周的第二天"),Friday("周五","一周的第五天");
	
	private String name;
	private String des;
	
	private WeekDayDes(String name, String des) {
		this.name = name;
		this.des = des;
	}
	
	public String getDes() {
		return this.name+" "+this.des;
	}
	
	
}
