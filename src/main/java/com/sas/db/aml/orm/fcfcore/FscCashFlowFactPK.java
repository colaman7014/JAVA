package com.sas.db.aml.orm.fcfcore;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the FSC_CASH_FLOW_FACT database table.
 * 
 */
@Embeddable
public class FscCashFlowFactPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name="transaction_key")
	private BigDecimal transactionKey;
	
	@Column(name="segment_id")
	private String segmentId;

	public FscCashFlowFactPK() {
	}

	public BigDecimal getTransactionKey() {
		return transactionKey;
	}

	public void setTransactionKey(BigDecimal transactionKey) {
		this.transactionKey = transactionKey;
	}

	public String getSegmentId() {
		return segmentId;
	}

	public void setSegmentId(String segmentId) {
		this.segmentId = segmentId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((transactionKey == null) ? 0 : transactionKey.hashCode());
		result = prime
				* result
				+ ((segmentId == null) ? 0 : segmentId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FscCashFlowFactPK other = (FscCashFlowFactPK) obj;

		if (!segmentId.equals(other.segmentId))
			return false;
		if (transactionKey.intValue() != other.transactionKey.intValue())
			return false;
		return true;
	}
	

}