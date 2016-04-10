package com.EasyMarathon.service;

import java.sql.SQLException;

import com.EasyMarathon.dao.AthleteDao;

public class AthleteService {
	private AthleteDao athletedao;

	public boolean ifexist(String wechatID,int eventID) throws SQLException {
		athletedao = null;
		Integer aID=athletedao.GetAthleteID(wechatID, eventID);
		
		if (aID.equals(null)) {
			
			return true;
		}
		return false;
	}	
}
