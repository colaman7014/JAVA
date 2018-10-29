package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT256 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT256", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt256.findAll", query="SELECT s FROM SwiftMt256 s")
public class SwiftMt256 implements Serializable{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt256PK id;

	@Column(name="Account_With_Institution_for_Settlement")
	private String account_With_Institution_for_Settlement;

	@Column(name="Beneficiary_Institutions_Account_for_Settlement")
	private String beneficiary_Institutions_Account_for_Settlement;

	@Column(name="Cheque_Amount")
	private String cheque_Amount;

	@Column(name="Cheque_Number")
	private String cheque_Number;

	@Column(name="Cheque_Reference_Number")
	private String cheque_Reference_Number;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="Interest_Charges")
	private String interest_Charges;

	@Column(name="Interest_Rate")
	private String interest_Rate;

	@Column(name="Issuers_Financial_Institution_Charges")
	private String issuers_Financial_Institution_Charges;

	@Column(name="Original_Value_Date")
	private String original_Value_Date;

	@Column(name="Reason_for_Non_Payment_Dishonour")
	private String reason_for_Non_Payment_Dishonour;

	@Column(name="Related_Message_Reference")
	private String related_Message_Reference;

	@Column(name="Senders_Charges")
	private String senders_Charges;

	@Column(name="Senders_Reference")
	private String senders_Reference;

	@Column(name="Sum_of_Interest_Charges")
	private String sum_of_Interest_Charges;

	@Column(name="Sum_of_Issuers_Financial_Institution_Charges")
	private String sum_of_Issuers_Financial_Institution_Charges;

	@Column(name="Sum_of_Senders_Charges")
	private String sum_of_Senders_Charges;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="Total_Cheque_Amount")
	private String total_Cheque_Amount;

	@Column(name="Transaction_Reference_Number")
	private String transaction_Reference_Number;

	@Column(name="Value_Date_Currency_Code_and_Total_Amount_Claimed")
	private String value_Date_Currency_Code_and_Total_Amount_Claimed;

	public SwiftMt256() {
	}

	public SwiftMt256PK getId() {
		return this.id;
	}

	public void setId(SwiftMt256PK id) {
		this.id = id;
	}

	public String getAccount_With_Institution_for_Settlement() {
		return this.account_With_Institution_for_Settlement;
	}

	public void setAccount_With_Institution_for_Settlement(String account_With_Institution_for_Settlement) {
		this.account_With_Institution_for_Settlement = account_With_Institution_for_Settlement;
	}

	public String getBeneficiary_Institutions_Account_for_Settlement() {
		return this.beneficiary_Institutions_Account_for_Settlement;
	}

	public void setBeneficiary_Institutions_Account_for_Settlement(String beneficiary_Institutions_Account_for_Settlement) {
		this.beneficiary_Institutions_Account_for_Settlement = beneficiary_Institutions_Account_for_Settlement;
	}

	public String getCheque_Amount() {
		return this.cheque_Amount;
	}

	public void setCheque_Amount(String cheque_Amount) {
		this.cheque_Amount = cheque_Amount;
	}

	public String getCheque_Number() {
		return this.cheque_Number;
	}

	public void setCheque_Number(String cheque_Number) {
		this.cheque_Number = cheque_Number;
	}

	public String getCheque_Reference_Number() {
		return this.cheque_Reference_Number;
	}

	public void setCheque_Reference_Number(String cheque_Reference_Number) {
		this.cheque_Reference_Number = cheque_Reference_Number;
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

	public String getInterest_Charges() {
		return this.interest_Charges;
	}

	public void setInterest_Charges(String interest_Charges) {
		this.interest_Charges = interest_Charges;
	}

	public String getInterest_Rate() {
		return this.interest_Rate;
	}

	public void setInterest_Rate(String interest_Rate) {
		this.interest_Rate = interest_Rate;
	}

	public String getIssuers_Financial_Institution_Charges() {
		return this.issuers_Financial_Institution_Charges;
	}

	public void setIssuers_Financial_Institution_Charges(String issuers_Financial_Institution_Charges) {
		this.issuers_Financial_Institution_Charges = issuers_Financial_Institution_Charges;
	}

	public String getOriginal_Value_Date() {
		return this.original_Value_Date;
	}

	public void setOriginal_Value_Date(String original_Value_Date) {
		this.original_Value_Date = original_Value_Date;
	}

	public String getReason_for_Non_Payment_Dishonour() {
		return this.reason_for_Non_Payment_Dishonour;
	}

	public void setReason_for_Non_Payment_Dishonour(String reason_for_Non_Payment_Dishonour) {
		this.reason_for_Non_Payment_Dishonour = reason_for_Non_Payment_Dishonour;
	}

	public String getRelated_Message_Reference() {
		return this.related_Message_Reference;
	}

	public void setRelated_Message_Reference(String related_Message_Reference) {
		this.related_Message_Reference = related_Message_Reference;
	}

	public String getSenders_Charges() {
		return this.senders_Charges;
	}

	public void setSenders_Charges(String senders_Charges) {
		this.senders_Charges = senders_Charges;
	}

	public String getSenders_Reference() {
		return this.senders_Reference;
	}

	public void setSenders_Reference(String senders_Reference) {
		this.senders_Reference = senders_Reference;
	}

	public String getSum_of_Interest_Charges() {
		return this.sum_of_Interest_Charges;
	}

	public void setSum_of_Interest_Charges(String sum_of_Interest_Charges) {
		this.sum_of_Interest_Charges = sum_of_Interest_Charges;
	}

	public String getSum_of_Issuers_Financial_Institution_Charges() {
		return this.sum_of_Issuers_Financial_Institution_Charges;
	}

	public void setSum_of_Issuers_Financial_Institution_Charges(String sum_of_Issuers_Financial_Institution_Charges) {
		this.sum_of_Issuers_Financial_Institution_Charges = sum_of_Issuers_Financial_Institution_Charges;
	}

	public String getSum_of_Senders_Charges() {
		return this.sum_of_Senders_Charges;
	}

	public void setSum_of_Senders_Charges(String sum_of_Senders_Charges) {
		this.sum_of_Senders_Charges = sum_of_Senders_Charges;
	}

	public String getSwiftFullText() {
		return this.swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
	}

	public String getTotal_Cheque_Amount() {
		return this.total_Cheque_Amount;
	}

	public void setTotal_Cheque_Amount(String total_Cheque_Amount) {
		this.total_Cheque_Amount = total_Cheque_Amount;
	}

	public String getTransaction_Reference_Number() {
		return this.transaction_Reference_Number;
	}

	public void setTransaction_Reference_Number(String transaction_Reference_Number) {
		this.transaction_Reference_Number = transaction_Reference_Number;
	}

	public String getValue_Date_Currency_Code_and_Total_Amount_Claimed() {
		return this.value_Date_Currency_Code_and_Total_Amount_Claimed;
	}

	public void setValue_Date_Currency_Code_and_Total_Amount_Claimed(String value_Date_Currency_Code_and_Total_Amount_Claimed) {
		this.value_Date_Currency_Code_and_Total_Amount_Claimed = value_Date_Currency_Code_and_Total_Amount_Claimed;
	}

}