package com.EasyMarathon.bean;

public class Athlete {
	public static enum State
	{
		origin, succeed, failed;
		//�����ɹ����ʼĬ�ϣ�origin=0; �к����ƣ�succeed=1; ����ʧ�ܣ�failed=2
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
