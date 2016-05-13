package com.EasyMarathon.bean;

public class Athlete {
	public static enum State
	{
		origin, succeed, failed;
		//报名成功后初始默认：origin=0; 有号码牌：succeed=1; 报名失败：failed=2
	}
	private int EventID;
	private String WechatID = "";
	private String AthleteID = "";
	private State state;
	public int getEventID() {
		return EventID;
	}
	public void setEventID(int eventID) {
		EventID = eventID;
	}
	public String getWechatID() {
		return WechatID;
	}
	public void setWechatID(String wechatID) {
		WechatID = wechatID;
	}
	public String getAthleteID() {
		return AthleteID;
	}
	public void setAthleteID(String athleteID) {
		AthleteID = athleteID;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	
}
