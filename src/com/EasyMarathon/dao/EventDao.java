package com.EasyMarathon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.EasyMarathon.bean.EventBean;

public class EventDao
{
	Connection conn = null;

	public EventDao(Connection c)
	{
		conn = c;
	}

	public EventBean GetEventByID(int eventID) throws SQLException
	{
		final String sql1 = "select * from Events where EventID=?";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1))
		{
			ps1.setInt(1, eventID);
			ResultSet rs1 = ps1.executeQuery();
			if (!rs1.next())// no match
				return null;

			// get event
			EventBean event = new EventBean();
			event.setEventID(eventID);
			event.setEventName(rs1.getString("EventName"));
			event.setEventStatus(rs1.getInt("EventStatus"));
			return event;
		}
	}

	public ArrayList<EventBean> GetEventByStatus(EventBean.Status eventStatus) throws SQLException
	{
		final String sql1 = "select * from Events where EventStatus=?";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1))
		{
			ps1.setInt(1, eventStatus.ordinal());
			ResultSet rs1 = ps1.executeQuery();
			ArrayList<EventBean> events = new ArrayList<>();
			while(rs1.next())
			{
				EventBean event = new EventBean();
				event.setEventID(rs1.getInt("EventID"));
				event.setEventName(rs1.getString("EventName"));
				event.setEventStatus(eventStatus);
				events.add(event);
			}
			return events;
		}
	}
	
	public Integer AddEvent(String eventName)
			throws SQLException
	{
		final String sql1 = "insert into Events (EventName,EventStatus) values(?,0)";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS))
		{
			ps1.setString(1, eventName);

			ps1.executeUpdate();
			ResultSet rs1=ps1.getGeneratedKeys();
			
			if(rs1.next())
				return rs1.getInt(1);
			else
				return null;
		}
	}
	public ArrayList<EventBean> GetAllEvents() throws SQLException
	{
		final String sql1 = "select * from Events";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1))
		{
			ResultSet rs1 = ps1.executeQuery();
			ArrayList<EventBean> events = new ArrayList<>();
			while(rs1.next())
			{
				EventBean event = new EventBean();
				event.setEventID(rs1.getInt("EventID"));
				event.setEventName(rs1.getString("EventName"));
				events.add(event);
			}
			return events;
		}
	}
	public boolean updateEvent(EventBean event)
			throws SQLException
	{
		final String sql1 = "update Events set EventName=? , EventStatus=? where EventID=?";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1))
		{
			ps1.setString(1, event.getEventName());
			ps1.setInt(2, event.getEventStatus().ordinal());
			ps1.setInt(3, event.getEventID());

			ps1.executeUpdate();
			return true;
		}
	}
}
