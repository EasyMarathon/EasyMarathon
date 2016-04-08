package com.EasyMarathon.bean;

public class EventBean
{
	public static enum Status
	{
		ongoing, finish;
	}
	private int eventID = -1;
	private String eventName = "";
	private Status eventStatus;

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

	public Status getEventStatus()
	{
		return eventStatus;
	}

	public void setEventStatus(Status eventStatus)
	{
		this.eventStatus = eventStatus;
	}

	public void setEventStatus(int eventStatus)
	{
		this.eventStatus = Status.values()[eventStatus];
	}
	
}
