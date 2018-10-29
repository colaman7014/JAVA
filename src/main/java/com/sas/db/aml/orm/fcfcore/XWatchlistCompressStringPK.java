package com.sas.db.aml.orm.fcfcore;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the X_WATCHLIST_COMPRESS_STRING database table.
 * 
 */
@Embeddable
public class XWatchlistCompressStringPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="Compress_String")
	private String compressString;

	@Column(name="Compress_Type")
	private String compressType;

	public XWatchlistCompressStringPK() {
	}
	public String getCompressString() {
		return this.compressString;
	}
	public void setCompressString(String compress_String) {
		this.compressString = compress_String;
	}
	public String getCompressType() {
		return this.compressType;
	}
	public void setCompressType(String compress_Type) {
		this.compressType = compress_Type;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof XWatchlistCompressStringPK)) {
			return false;
		}
		XWatchlistCompressStringPK castOther = (XWatchlistCompressStringPK)other;
		return 
			this.compressString.equals(castOther.compressString)
			&& this.compressType.equals(castOther.compressType);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.compressString.hashCode();
		hash = hash * prime + this.compressType.hashCode();
		
		return hash;
	}
}