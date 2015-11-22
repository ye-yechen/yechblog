package com.yech.yechblog.entity;


/**
 * 用户上传的资源类
 * @author Administrator
 */
public class Resource{
	private Integer id;
	private String resName;//资源名称
	private String resDesc;//资源描述
	private String resType;//资源类型
	private Integer resScore;//资源分
	private String resSuffix;//资源后缀名
	private Integer downloadCount;//下载数量
	private String resPath;//资源路径
	private String resUploadTime;//资源上传时间
	private User uploadUser;//资源的上传者
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getResName() {
		return resName;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}
	public String getResDesc() {
		return resDesc;
	}
	public void setResDesc(String resDesc) {
		this.resDesc = resDesc;
	}
	public String getResType() {
		return resType;
	}
	public void setResType(String resType) {
		this.resType = resType;
	}
	public Integer getResScore() {
		return resScore;
	}
	public void setResScore(Integer resScore) {
		this.resScore = resScore;
	}
	public String getResSuffix() {
		return resSuffix;
	}
	public void setResSuffix(String resSuffix) {
		this.resSuffix = resSuffix;
	}
	public Integer getDownloadCount() {
		return downloadCount;
	}
	public void setDownloadCount(Integer downloadCount) {
		this.downloadCount = downloadCount;
	}
	public String getResPath() {
		return resPath;
	}
	public void setResPath(String resPath) {
		this.resPath = resPath;
	}
	public String getResUploadTime() {
		return resUploadTime;
	}
	public void setResUploadTime(String resUploadTime) {
		this.resUploadTime = resUploadTime;
	}
	public User getUploadUser() {
		return uploadUser;
	}
	public void setUploadUser(User uploadUser) {
		this.uploadUser = uploadUser;
	}
}
