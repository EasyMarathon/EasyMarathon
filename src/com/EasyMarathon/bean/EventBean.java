package com.EasyMarathon.bean;

public class EventBean
{
	private int eventID = -1;
	private String eventName = "";
	private int eventStatus = -1;

	public int getEventID()
	{
		return eventID;
	}

	public void setEventID(int eventID)
	{
		this.eventID = eventID;
	}

	public String getEventName()
	{
		return eventName;
	}

	public void setEventName(String eventName)
	{
		this.eventName = eventName;
	}

	public int getEventStatus()
	{
		return eventStatus;
	}

	public void setEventStatus(int eventStatus)
	{
		this.eventStatus = eventStatus;
	}

}
