package com.sas.webservice.createCase.bean;

public class QueryHitRecordBean {
	
	private String uniqueKey;
	private int ncReferenceId;
	private String entityType;
	private String entityRelationship;
	private String entityRelationshipDesc;
	private String englishName;
	private String nonEnglishName;
	private String entiyNameDisplay;
	private String country;
	private String idNumber;
	private String watchListTypeCd;
	private String yearOfBirth;
	private String entityWatchListKey;
	private int mixScore;
	private String checkSeq;
	private String seq;
	private String checkResult;
	private String WatchListName;
	private String entityWatchListNumber;
	private String whiteListInd;
	private String fieldName;
	private int rowNo;
	private String isNeedWhiteList;
	private String fieldValue;
	private String transCheckResult;
	private String transEntityRelationship;
	private String transRouteRule;
	private String transEntityType;
	public QueryHitRecordBean() {

	}	
	public QueryHitRecordBean(QueryHitRecordBean hitRecordDetail) {
		this.uniqueKey=hitRecordDetail.getUniqueKey();
		this.ncReferenceId=hitRecordDetail.getNcReferenceId();
		this.entityType=hitRecordDetail.getEntityType();
		this.entityRelationship=hitRecordDetail.getEntityRelationship();
		this.entityRelationshipDesc=hitRecordDetail.getEntityRelationshipDesc();
		this.englishName=hitRecordDetail.getEnglishName();
		this.nonEnglishName=hitRecordDetail.getNonEnglishName();
		this.entiyNameDisplay=hitRecordDetail.getEntiyNameDisplay();
		this.country=hitRecordDetail.getCountry();
		this.idNumber=hitRecordDetail.getIdNumber();
		this.watchListTypeCd=hitRecordDetail.getWatchListTypeCd();
		this.yearOfBirth=hitRecordDetail.getYearOfBirth();
		this.entityWatchListKey=hitRecordDetail.getEntityWatchListKey();
		this.mixScore=hitRecordDetail.getMixScore();
		this.checkSeq=hitRecordDetail.getCheckSeq();
		this.seq=hitRecordDetail.getSeq();
		this.checkResult=hitRecordDetail.getCheckResult();
		this.WatchListName=hitRecordDetail.getWatchListName();
		this.entityWatchListNumber=hitRecordDetail.getEntityWatchListNumber();
		this.whiteListInd=hitRecordDetail.getWhiteListInd();
		this.fieldName=hitRecordDetail.getFieldName();
		this.rowNo=hitRecordDetail.getRowNo();
		this.isNeedWhiteList=hitRecordDetail.getIsNeedWhiteList();
		this.fieldValue =hitRecordDetail.getFieldValue();
	}
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	public int getNcReferenceId() {
		return ncReferenceId;
	}
	public void setNcReferenceId(int ncReferenceId) {
		this.ncReferenceId = ncReferenceId;
	}
	
	
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	public String getEntityRelationship() {
		return entityRelationship;
	}
	public void setEntityRelationship(String entityRelationship) {
		this.entityRelationship = entityRelationship;
	}
	public String getEntityRelationshipDesc() {
		return entityRelationshipDesc;
	}
	public void setEntityRelationshipDesc(String entityRelationshipDesc) {
		this.entityRelationshipDesc = entityRelationshipDesc;
	}
	public String getEnglishName() {
		return englishName;
	}
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	public String getNonEnglishName() {
		return nonEnglishName;
	}
	public void setNonEnglishName(String nonEnglishName) {
		this.nonEnglishName = nonEnglishName;
	}
	
	
	public String getEntiyNameDisplay() {
		return entiyNameDisplay;
	}
	public void setEntiyNameDisplay(String entiyNameDisplay) {
		this.entiyNameDisplay = entiyNameDisplay;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	
	public String getWatchListTypeCd() {
		return watchListTypeCd;
	}
	public void setWatchListTypeCd(String watchListTypeCd) {
		this.watchListTypeCd = watchListTypeCd;
	}
	public String getEntityWatchListKey() {
		return entityWatchListKey;
	}
	public void setEntityWatchListKey(String entityWatchListKey) {
		this.entityWatchListKey = entityWatchListKey;
	}
	
	public int getMixScore() {
		return mixScore;
	}
	public void setMixScore(int mixScore) {
		this.mixScore = mixScore;
	}
	public String getCheckSeq() {
		return checkSeq;
	}
	public void setCheckSeq(String checkSeq) {
		this.checkSeq = checkSeq;
	}
	public String getCheckResult() {
		return checkResult;
	}
	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}
	public String getWatchListName() {
		return WatchListName;
	}
	public void setWatchListName(String watchListName) {
		WatchListName = watchListName;
	}
	public String getEntityWatchListNumber() {
		return entityWatchListNumber;
	}
	public void setEntityWatchListNumber(String entityWatchListNumber) {
		this.entityWatchListNumber = entityWatchListNumber;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getWhiteListInd() {
		return whiteListInd;
	}
	public void setWhiteListInd(String whiteListInd) {
		this.whiteListInd = whiteListInd;
	}
	public String getYearOfBirth() {
		return yearOfBirth;
	}
	public void setYearOfBirth(String yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
	public int getRowNo() {
		return rowNo;
	}
	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getIsNeedWhiteList() {
		return isNeedWhiteList;
	}
	public void setIsNeedWhiteList(String isNeedWhiteList) {
		this.isNeedWhiteList = isNeedWhiteList;
	}
	public String getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	public String getTransCheckResult() {
		return transCheckResult;
	}
	public void setTransCheckResult(String transCheckResult) {
		this.transCheckResult = transCheckResult;
	}
	public String getTransEntityRelationship() {
		return transEntityRelationship;
	}
	public void setTransEntityRelationship(String transEntityRelationship) {
		this.transEntityRelationship = transEntityRelationship;
	}
	public String getTransRouteRule() {
		return transRouteRule;
	}
	public void setTransRouteRule(String transRouteRule) {
		this.transRouteRule = transRouteRule;
	}
	public String getTransEntityType() {
		return transEntityType;
	}
	public void setTransEntityType(String transEntityType) {
		this.transEntityType = transEntityType;
	}	

}