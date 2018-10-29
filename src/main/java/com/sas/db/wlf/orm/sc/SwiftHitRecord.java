package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.sas.constraint.SwiftMtConst;
import com.sas.swift.bean.SwiftHitRecordBean;


/**
 * The persistent class for the SWIFT_HIT_RECORD database table.
 * 
 */
@Entity
@Table(name="SWIFT_HIT_RECORD", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftHitRecord.findAll", query="SELECT s FROM SwiftHitRecord s")
public class SwiftHitRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public SwiftHitRecord (SwiftHitRecordBean swiftHitRecordBean){
		SwiftHitRecordPK id = new SwiftHitRecordPK();
		id.setUniqueKey(swiftHitRecordBean.getUniqueKey());
		id.setReferenceId(Integer.parseInt(swiftHitRecordBean.getReferenceId()));
		id.setSeq(swiftHitRecordBean.getSeq());
		this.id = id;
		this.citizenshipCountryName = swiftHitRecordBean.getCitizenshipCountryName();
		this.citizenshipCountryCode = swiftHitRecordBean.getCitizenshipCountryCode();
		this.entityName = swiftHitRecordBean.getEntityName();
		this.entityNameDisplay = swiftHitRecordBean.getEntityNameDisplay();
		this.entityWatchListNumber = swiftHitRecordBean.getEntityWatchListNumber();
		this.fieldName = swiftHitRecordBean.getFieldName();
		this.fieldValue = swiftHitRecordBean.getFieldValue();
		this.firstName = swiftHitRecordBean.getFirstName();
		this.identificationId = swiftHitRecordBean.getIdentificationId();
		this.identificationTypeDesc = swiftHitRecordBean.getIdentificationTypeDesc();
		this.lastName = swiftHitRecordBean.getLastName();
		this.middleName = swiftHitRecordBean.getMiddleName();
		this.placeOfBirth = swiftHitRecordBean.getPlaceOfBirth();
		this.poBox = swiftHitRecordBean.getPoBox();
		this.typeDesc = swiftHitRecordBean.getTypeDesc();
		this.watchListModel = swiftHitRecordBean.getWatchListModel();
		this.watchListTypeCd = swiftHitRecordBean.getWatchListTypeCd();
		this.watchListSubTypeCd = swiftHitRecordBean.getWatchListSubTypeCd();
		this.yearOfBirth = swiftHitRecordBean.getYearOfBirth();
		this.entityWatchListKey = Long.parseLong(swiftHitRecordBean.getEntityWatchListKey());
		this.watchListName = swiftHitRecordBean.getWatchListName();
		this.match_code = swiftHitRecordBean.getMatch_code();
		this.nospace_name_ind = swiftHitRecordBean.getNospace_name_ind();
	}
	
	@EmbeddedId
	private SwiftHitRecordPK id;

	@Column(name="CITIZENSHIP_COUNTRY_CODE")
	private String citizenshipCountryCode;
	
	@Column(name="CITIZENSHIP_COUNTRY_NAME")
	private String citizenshipCountryName;

	@Column(name="ENTITY_NAME")
	private String entityName;
	
	@Column(name="ENTITY_NAME_DISPLAY")
	private String entityNameDisplay;
	
	@Column(name="ENTITY_WATCH_LIST_NUMBER")
	private String entityWatchListNumber;
	
	@Column(name="FIELD_NAME")
	private String fieldName;

	@Column(name="FIELD_VALUE")
	private String fieldValue;

	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="IDENTIFICATION_ID")
	private String identificationId;
	
	@Column(name="IDENTIFICATION_TYPE_DESC")
	private String identificationTypeDesc;

	@Column(name="LAST_NAME")
	private String lastName;

	@Column(name="MIDDLE_NAME")
	private String middleName;

	@Column(name="PLACE_OF_BIRTH")
	private String placeOfBirth;

	@Column(name="PO_BOX")
	private String poBox;
	
	@Column(name="TYPE_DESC")
	private String typeDesc;

	@Column(name="WATCH_LIST_MODEL")
	private String watchListModel;
	
	@Column(name="WATCHLIST_TYPE_CD")
	private String watchListTypeCd;
	
	@Column(name="WATCHLIST_SUB_TYPE_CD")
	private String watchListSubTypeCd;

	@Column(name="YEAR_OF_BIRTH")
	private BigDecimal yearOfBirth;
	
	@Column(name="ENTITY_WATCH_LIST_KEY")
	private long entityWatchListKey;
	
	@Column(name="WATCH_LIST_NAME")
	private String watchListName;
	
	@javax.persistence.Transient
	private String match_code;
	
	@javax.persistence.Transient
	private String nospace_name_ind;
	
	public SwiftHitRecord() {
	}

	public SwiftHitRecordPK getId() {
		return id;
	}

	public void setId(SwiftHitRecordPK id) {
		this.id = id;
	}

	public String getCitizenshipCountryCode() {
		return citizenshipCountryCode;
	}

	public void setCitizenshipCountryCode(String citizenshipCountryCode) {
		this.citizenshipCountryCode = citizenshipCountryCode;
	}

	public String getCitizenshipCountryName() {
		return citizenshipCountryName;
	}

	public void setCitizenshipCountryName(String citizenshipCountryName) {
		this.citizenshipCountryName = citizenshipCountryName;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityNameDisplay() {
		return entityNameDisplay;
	}

	public void setEntityNameDisplay(String entityNameDisplay) {
		this.entityNameDisplay = entityNameDisplay;
	}

	public String getEntityWatchListNumber() {
		return entityWatchListNumber;
	}

	public void setEntityWatchListNumber(String entityWatchListNumber) {
		this.entityWatchListNumber = entityWatchListNumber;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getIdentificationId() {
		return identificationId;
	}

	public void setIdentificationId(String identificationId) {
		this.identificationId = identificationId;
	}

	public String getIdentificationTypeDesc() {
		return identificationTypeDesc;
	}

	public void setIdentificationTypeDesc(String identificationTypeDesc) {
		this.identificationTypeDesc = identificationTypeDesc;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getPoBox() {
		return poBox;
	}

	public void setPoBox(String poBox) {
		this.poBox = poBox;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public String getWatchListModel() {
		return watchListModel;
	}

	public void setWatchListModel(String watchListModel) {
		this.watchListModel = watchListModel;
	}

	public String getWatchListTypeCd() {
		return watchListTypeCd;
	}

	public void setWatchListTypeCd(String watchListTypeCd) {
		this.watchListTypeCd = watchListTypeCd;
	}

	public String getWatchListSubTypeCd() {
		return watchListSubTypeCd;
	}

	public void setWatchListSubTypeCd(String watchListSubTypeCd) {
		this.watchListSubTypeCd = watchListSubTypeCd;
	}

	public BigDecimal getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(BigDecimal yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public long getEntityWatchListKey() {
		return entityWatchListKey;
	}

	public void setEntityWatchListKey(long entityWatchListKey) {
		this.entityWatchListKey = entityWatchListKey;
	}

	public String getWatchListName() {
		return watchListName;
	}

	public void setWatchListName(String watchListName) {
		this.watchListName = watchListName;
	}

	public String getMatch_code() {
		return match_code;
	}

	public void setMatch_code(String match_code) {
		this.match_code = match_code;
	}

	public String getNospace_name_ind() {
		return nospace_name_ind;
	}

	public void setNospace_name_ind(String nospace_name_ind) {
		this.nospace_name_ind = nospace_name_ind;
	}
}