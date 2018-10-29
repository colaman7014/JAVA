package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT416 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT416", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt416.findAll", query="SELECT s FROM SwiftMt416 s")
public class SwiftMt416 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt416PK id;

	@Column(name="ADVICE_TYPE")
	private String adviceType;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="DRAWEE")
	private String drawee;

	@Column(name="[FACE_AMOUNT_OF_DOCUMENT(S)]")
	private String faceAmountOfDocument_s_;

	@Column(name="PRINCIPAL")
	private String principal;

	@Column(name="PRINCIPALS_REFERENCE")
	private String principalsReference;

	@Column(name="REASON_FOR_NON_PAYMENT_NON_ACCEPTANCE")
	private String reasonForNonPaymentNonAcceptance;

	@Column(name="RELATED_REFERENCE")
	private String relatedReference;

	@Column(name="RELATED_SEQUENCE_REFERENCE")
	private String relatedSequenceReference;

	@Column(name="SENDERS_CHARGES")
	private String sendersCharges;

	@Column(name="SENDERS_CORRESPONDENT")
	private String sendersCorrespondent;

	@Column(name="SENDERS_REFERENCE")
	private String sendersReference;

	@Column(name="[SENDING_INSTITUTION_(FILEACT_ONLY)]")
	private String sendingInstitution_fileactOnly_;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	public SwiftMt416() {
	}

	public SwiftMt416PK getId() {
		return this.id;
	}

	public void setId(SwiftMt416PK id) {
		this.id = id;
	}

	public String getAdviceType() {
		return this.adviceType;
	}

	public void setAdviceType(String adviceType) {
		this.adviceType = adviceType;
	}

	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Timestamp getCreatedTimestamp() {
		return this.createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public String getDrawee() {
		return this.drawee;
	}

	public void setDrawee(String drawee) {
		this.drawee = drawee;
	}

	public String getFaceAmountOfDocument_s_() {
		return this.faceAmountOfDocument_s_;
	}

	public void setFaceAmountOfDocument_s_(String faceAmountOfDocument_s_) {
		this.faceAmountOfDocument_s_ = faceAmountOfDocument_s_;
	}

	public String getPrincipal() {
		return this.principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getPrincipalsReference() {
		return this.principalsReference;
	}

	public void setPrincipalsReference(String principalsReference) {
		this.principalsReference = principalsReference;
	}

	public String getReasonForNonPaymentNonAcceptance() {
		return this.reasonForNonPaymentNonAcceptance;
	}

	public void setReasonForNonPaymentNonAcceptance(String reasonForNonPaymentNonAcceptance) {
		this.reasonForNonPaymentNonAcceptance = reasonForNonPaymentNonAcceptance;
	}

	public String getRelatedReference() {
		return this.relatedReference;
	}

	public void setRelatedReference(String relatedReference) {
		this.relatedReference = relatedReference;
	}

	public String getRelatedSequenceReference() {
		return this.relatedSequenceReference;
	}

	public void setRelatedSequenceReference(String relatedSequenceReference) {
		this.relatedSequenceReference = relatedSequenceReference;
	}

	public String getSendersCharges() {
		return this.sendersCharges;
	}

	public void setSendersCharges(String sendersCharges) {
		this.sendersCharges = sendersCharges;
	}

	public String getSendersCorrespondent() {
		return this.sendersCorrespondent;
	}

	public void setSendersCorrespondent(String sendersCorrespondent) {
		this.sendersCorrespondent = sendersCorrespondent;
	}

	public String getSendersReference() {
		return this.sendersReference;
	}

	public void setSendersReference(String sendersReference) {
		this.sendersReference = sendersReference;
	}

	public String getSendingInstitution_fileactOnly_() {
		return this.sendingInstitution_fileactOnly_;
	}

	public void setSendingInstitution_fileactOnly_(String sendingInstitution_fileactOnly_) {
		this.sendingInstitution_fileactOnly_ = sendingInstitution_fileactOnly_;
	}

	public String getSwiftFullText() {
		return this.swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
	}

}