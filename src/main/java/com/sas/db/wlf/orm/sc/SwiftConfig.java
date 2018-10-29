package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;


/**
 * The persistent class for the SWIFT_CONFIG database table.
 * 
 */
@Entity
@Table(name="SWIFT_CONFIG", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftConfig.findAll", query="SELECT s FROM SwiftConfig s")
public class SwiftConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftConfigPK id;

	@Column(name="FIELD_NAME")
	private String fieldName;

	@Column(name="SCREEN_BIC_COUNTRY")
	private String screenBicCountry;

	@Column(name="SCREEN_CCC_CODE")
	private String screenCccCode;

	@Column(name="SCREEN_COUNTRY_CITY")
	private String screenCountryCity;

	@Column(name="SCREEN_FIELD")
	private String screenField;

	@Column(name="SCREEN_POBOX")
	private String screenPobox;

	public SwiftConfig() {
	}

	public SwiftConfigPK getId() {
		return this.id;
	}

	public void setId(SwiftConfigPK id) {
		this.id = id;
	}

	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getScreenBicCountry() {
		return this.screenBicCountry;
	}

	public void setScreenBicCountry(String screenBicCountry) {
		this.screenBicCountry = screenBicCountry;
	}

	public String getScreenCccCode() {
		return this.screenCccCode;
	}

	public void setScreenCccCode(String screenCccCode) {
		this.screenCccCode = screenCccCode;
	}

	public String getScreenCountryCity() {
		return this.screenCountryCity;
	}

	public void setScreenCountryCity(String screenCountryCity) {
		this.screenCountryCity = screenCountryCity;
	}

	public String getScreenField() {
		return this.screenField;
	}

	public void setScreenField(String screenField) {
		this.screenField = screenField;
	}

	public String getScreenPobox() {
		return this.screenPobox;
	}

	public void setScreenPobox(String screenPobox) {
		this.screenPobox = screenPobox;
	}

}