package com.EasyMarathon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.EasyMarathon.bean.Athlete;
import com.EasyMarathon.bean.ConfirmData;

public class AthleteDao {
	Connection conn = null;

	public AthleteDao(Connection c) {
		conn = c;
	}

	public Integer GetAthleteID(String wechatID, int eventID) throws SQLException {
		final String sql1 = "select AthleteID from Athletes where WechatID=? AND EventID=?";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1)) {
			ps1.setString(1, wechatID);
			ps1.setInt(2, eventID);
			ResultSet rs1 = ps1.executeQuery();
			if (!rs1.next())// no match
				return null;

			return rs1.getInt("AthleteID");
		}
	}

	public boolean AddAthlete(String wechatID, int eventID, int athleteID) throws SQLException {
		final String sql1 = "select WechatID from Athletes where AthleteID=? AND EventID=?";
		final String sql2 = "insert into Athletes (WechatID,EventID,AthleteID) values(?,?,?)";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1); PreparedStatement ps2 = conn.prepareStatement(sql2)) {
			ps1.setInt(1, athleteID);
			ps1.setInt(2, eventID);
			ResultSet rs1 = ps1.executeQuery();
			if (rs1.next())// has record
			{
				if (!wechatID.equals(rs1.getString("WechatID")))// not match
					return false;
				else
					return true;
			}

			ps2.setString(1, wechatID);
			ps2.setInt(2, eventID);
			ps2.setInt(3, athleteID);

			ps2.executeUpdate();
			return true;
		}
	}

	public boolean AddAthleteBase(Athlete athlete) {// 增加运动员的基本信息，不包含号码牌
		final String sql = "insert into Athletes (EventID,WechatID,state) values(?,?,?)";
		try (PreparedStatement ps1 = conn.prepareStatement(sql)) {
			ps1.setInt(1, athlete.getEventID());
			ps1.setString(2, athlete.getWechatID());
			ps1.setInt(3, athlete.getState().ordinal());
			ps1.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error Code=" + e.getErrorCode());
			System.out.println("error Message=" + e.getMessage());
			return false;
		}
		return true;
	}

	public boolean updateAthlete(Athlete athlete) {
		String WechatID = athlete.getWechatID();
		String AthleteID = athlete.getAthleteID();
		int EventID = athlete.getEventID();
		final String SQL = "update Athletes set AthleteID=?,state=1 where WechatID=? and EventID=?";
		try (PreparedStatement ps1 = conn.prepareStatement(SQL)) {
			ps1.setString(1, AthleteID);
			ps1.setString(2, WechatID);
			ps1.setInt(3, EventID);

			ps1.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateAthleteReject(Athlete athlete) {// 审核->拒绝
		String WechatID = athlete.getWechatID();
		int EventID = athlete.getEventID();
		final String SQL = "update Athletes set state=2 where WechatID=? and EventID=?";
		try (PreparedStatement ps1 = conn.prepareStatement(SQL)) {
			ps1.setString(1, WechatID);
			ps1.setInt(2, EventID);

			ps1.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<ConfirmData> GetsomeAthletes() {
		final String SQL = "select UserName, EventName, IdentityCard, Celphone, UrgencyContact,UrgencyPhone,IdentityPic,Athletes.WechatID,Athletes.EventID,Athletes.AthleteID from Athletes, Users, Events"
				+ " where Athletes.state=1 and Athletes.WechatID=Users.WechatID and Events.EventID=Athletes.EventID";
		ArrayList<ConfirmData> dataList = new ArrayList<ConfirmData>();
		try (PreparedStatement ps1 = conn.prepareStatement(SQL)) {
			ResultSet rs1;
			rs1 = ps1.executeQuery();
			while (rs1.next()) {
				String name = rs1.getString(1);
				String eventName = rs1.getString(2);
				String IDcard = rs1.getString(3);
				String celphone = rs1.getString(4);
				String urgencyName = rs1.getString(5);
				String urgencyPhone = rs1.getString(6);
				String IdentityPic = rs1.getString(7);
				String wechatID = rs1.getString(8);
				int eventID = rs1.getInt(9);
				int athleteID=rs1.getInt(10);
				ConfirmData confirmData = new ConfirmData(name, eventName, IDcard, celphone, urgencyName, urgencyPhone,
						IdentityPic, wechatID, eventID,athleteID);
				dataList.add(confirmData);
			}
			return dataList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<ConfirmData> findAllNotChecked() {
		final String SQL = "select UserName, EventName, IdentityCard, Celphone, UrgencyContact,UrgencyPhone,IdentityPic,Athletes.WechatID,Athletes.EventID,Athletes.AthleteID from Athletes, Users, Events"
				+ " where Athletes.state=0 and Athletes.WechatID=Users.WechatID and Events.EventID=Athletes.EventID";
		ArrayList<ConfirmData> dataList = new ArrayList<ConfirmData>();
		try (PreparedStatement ps1 = conn.prepareStatement(SQL)) {
			ResultSet rs1;
			rs1 = ps1.executeQuery();
			while (rs1.next()) {
				String name = rs1.getString(1);
				String eventName = rs1.getString(2);
				String IDcard = rs1.getString(3);
				String celphone = rs1.getString(4);
				String urgencyName = rs1.getString(5);
				String urgencyPhone = rs1.getString(6);
				String IdentityPic = rs1.getString(7);
				String wechatID = rs1.getString(8);
				int eventID = rs1.getInt(9);
				int athleteID=rs1.getInt(10);
				ConfirmData confirmData = new ConfirmData(name, eventName, IDcard, celphone, urgencyName, urgencyPhone,
						IdentityPic, wechatID, eventID,athleteID);
				dataList.add(confirmData);
			}
			return dataList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public ConfirmData GetAthlete(int aID,int eventID) {
		final String SQL = "select UserName, EventName, IdentityCard, Celphone, UrgencyContact,UrgencyPhone,IdentityPic,Athletes.WechatID,Athletes.EventID,Athletes.AthleteID from Athletes, Users, Events"
				+ "where Athletes.eventID="+eventID+" and Athletes.AthleteID="+aID+" and Athletes.WechatID=Users.WechatID and Events.EventID=Athletes.EventID";
		//ConfirmData confirmData=new ConfirmData(SQL, SQL, SQL, SQL, SQL, SQL, SQL, SQL, aID,aID);
		try (PreparedStatement ps1 = conn.prepareStatement(SQL)) {
			ResultSet rs1;
			rs1 = ps1.executeQuery();
			if (rs1.next()) {
				String name = rs1.getString(1);
				String eventName = rs1.getString(2);
				String IDcard = rs1.getString(3);
				String celphone = rs1.getString(4);
				String urgencyName = rs1.getString(5);
				String urgencyPhone = rs1.getString(6);
				String IdentityPic = rs1.getString(7);
				String wechatID = rs1.getString(8);
				int athleteID=rs1.getInt(10);
				ConfirmData confirmData = new ConfirmData(name, eventName, IDcard, celphone, urgencyName, urgencyPhone,
						IdentityPic, wechatID, eventID,athleteID);
				//dataList.add(confirmData);
				return confirmData;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
