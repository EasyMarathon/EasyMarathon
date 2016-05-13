package com.EasyMarathon.bean;

import java.sql.Date;

public class UserBean
{
	public static enum Gender
	{
		male,female;
	}
	private String wechatID = "";
	private String wechatName = "";
	private String headImgUrl =""; // 用户头像链接
	private String userName = "";
	private String celphone = "";
	private String email = "";
	private Date birth;
	private Gender gender;
	private String identityCard = "";	//身份证号
	private String identityPic = "";	//身份证上传图的存储路径
	private String bloodType = "";		//血型
	private String address = "";		//住址：省+市+区/县
	private float height;				//身高
	private float weight;				//体重
	private String urgencyContact = ""; //紧急联系人姓名
	private String urgencyPhone = "";	//紧急联系人电话
	
	
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public Date getBirth()
	{
		return birth;
	}
	public void setBirth(Date birth)
	{
		this.birth = birth;
	}
	public Gender getGender()
	{
		return gender;
	}
	public void setGender(int gender)
	{
		this.gender = Gender.values()[gender];
	}
	public String getWechatID()
	{
		return wechatID;
	}
	public void setWechatID(String wechatID)
	{
		this.wechatID = wechatID;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getCelphone()
	{
		return celphone;
	}
	public void setCelphone(String celphone)
	{
		this.celphone = celphone;
	}
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public String getIdentityPic() {
		return identityPic;
	}
	public void setIdentityPic(String identityPic) {
		this.identityPic = identityPic;
	}
	public String getBloodType() {
		return bloodType;
	}
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public String getUrgencyContact() {
		return urgencyContact;
	}
	public void setUrgencyContact(String urgencyContact) {
		this.urgencyContact = urgencyContact;
	}
	public String getUrgencyPhone() {
		return urgencyPhone;
	}
	public void setUrgencyPhone(String urgencyPhone) {
		this.urgencyPhone = urgencyPhone;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getWechatName() {
		return wechatName;
	}
	public void setWechatName(String wechatName) {
		this.wechatName = wechatName;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
}
