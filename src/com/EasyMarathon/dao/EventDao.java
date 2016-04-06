package com.EasyMarathon.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.EasyMarathon.bean.EventBean;
import com.EasyMarathon.bean.UserBean;

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

	public ArrayList<EventBean> GetEventByStatus(int eventStatus) throws SQLException
	{
		final String sql1 = "select * from Events where EventStatus=?";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1))
		{
			ps1.setInt(1, eventStatus);
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
}
