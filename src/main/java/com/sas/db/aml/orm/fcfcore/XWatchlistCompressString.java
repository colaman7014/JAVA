package com.sas.db.aml.orm.fcfcore;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the X_WATCHLIST_COMPRESS_STRING database table.
 * 
 */
@Entity
@Table(name="X_WATCHLIST_COMPRESS_STRING")
@NamedQuery(name="XWatchlistCompressString.findAll", query="SELECT x FROM XWatchlistCompressString x")
public class XWatchlistCompressString implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="String_Key")
	private String stringKey;
	

	@Column(name="Compress_String")
	private String compressString;

	@Column(name="Compress_String_Desc")
	private String compressStringDesc;

	@Column(name="Compress_Type")
	private String compressType;
	

	@Column(name="IsEnglish")
	private String isEnglish;
	
	@Column(name="ReplaceStr")
	private String replaceStr;
	

	public String getStringOrder() {
		return stringOrder;
	}


	public void setStringKey(String stringKey) {
		this.stringKey = stringKey;
	}
	public XWatchlistCompressString() {
	}

	public String getCompressString() {
		return this.compressString;
	}

	public void setCompressString(String compressString) {
		this.compressString = compressString;
	}

	public String getCompressStringDesc() {
		return this.compressStringDesc;
	}

	public void setCompressStringDesc(String compressStringDesc) {
		this.compressStringDesc = compressStringDesc;
	}

	public String getCompressType() {
		return this.compressType;
	}

	public void setCompressType(String compressType) {
		this.compressType = compressType;
	}
	public String getIsEnglish() {
		return isEnglish;
	}
	
	public void setIsEnglish(String isEnglish) {
		this.isEnglish = isEnglish;
	}
	
	public String getReplaceStr() {
		return replaceStr;
	}
	
	public void setReplaceStr(String replaceStr) {
		this.replaceStr = replaceStr;
	}
	public void setStringOrder(String stringOrder) {
		this.stringOrder = stringOrder;
	}

	@Column(name="String_Order")
	private String stringOrder;
	
	public String getStringKey() {
		return stringKey;
	}
	

}