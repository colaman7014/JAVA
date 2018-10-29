package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;


/**
 * The persistent class for the SWIFT_MAIN_CONFIG database table.
 * 
 */
@Entity
@Table(name="SWIFT_MAIN_CONFIG", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMainConfig.findAll", query="SELECT s FROM SwiftMainConfig s")
public class SwiftMainConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SWIFT_TYPE")
	private String swiftType;

	@Column(name="CREATE_CASE")
	private String createCase;

	@Column(name="NEED_WAIT")
	private String needWait;

	@Column(name="SCREEN_INCOMING")
	private String screenIncoming;

	@Column(name="SCREEN_OUTGOING")
	private String screenOutgoing;

	public SwiftMainConfig() {
	}

	public String getSwiftType() {
		return this.swiftType;
	}

	public void setSwiftType(String swiftType) {
		this.swiftType = swiftType;
	}

	public String getCreateCase() {
		return this.createCase;
	}

	public void setCreateCase(String createCase) {
		this.createCase = createCase;
	}

	public String getNeedWait() {
		return this.needWait;
	}

	public void setNeedWait(String needWait) {
		this.needWait = needWait;
	}

	public String getScreenIncoming() {
		return this.screenIncoming;
	}

	public void setScreenIncoming(String screenIncoming) {
		this.screenIncoming = screenIncoming;
	}

	public String getScreenOutgoing() {
		return this.screenOutgoing;
	}

	public void setScreenOutgoing(String screenOutgoing) {
		this.screenOutgoing = screenOutgoing;
	}

}