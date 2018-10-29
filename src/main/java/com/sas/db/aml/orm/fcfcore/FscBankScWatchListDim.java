package com.sas.db.aml.orm.fcfcore;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the FSC_BANK_SC_WATCH_LIST_DIM database table.
 * 
 */
@Entity
@Table(name="FSC_BANK_SC_WATCH_LIST_DIM")
@NamedQuery(name="FscBankScWatchListDim.findAll", query="SELECT f FROM FscBankScWatchListDim f")
public class FscBankScWatchListDim implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int SC_Key;

	@Column(name="bank_name")
	private String bankName;

	@Column(name="bank_name_display")
	private String bankNameDisplay;

	@Column(name="bank_watch_list_key")
	private long bankWatchListKey;

	@Column(name="bank_watch_list_number")
	private String bankWatchListNumber;

	@Column(name="bcc_code")
	private String bccCode;

	@Column(name="change_current_ind")
	private String changeCurrentInd;

	@Column(name="delete_ind")
	private String deleteInd;

	@Column(name="exclude_ind")
	private String excludeInd;

	@Column(name="match_code_bank_name")
	private String matchCodeBankName;

	@Column(name="nospace_name_ind")
	private String nospaceNameInd;

	@Column(name="process_dttm")
	private Timestamp processDttm;

	@Column(name="sort_name_ind")
	private String sortNameInd;

	@Column(name="watch_list_name")
	private String watchListName;

	public FscBankScWatchListDim() {
	}

	public int getSC_Key() {
		return this.SC_Key;
	}

	public void setSC_Key(int SC_Key) {
		this.SC_Key = SC_Key;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankNameDisplay() {
		return this.bankNameDisplay;
	}

	public void setBankNameDisplay(String bankNameDisplay) {
		this.bankNameDisplay = bankNameDisplay;
	}

	public long getBankWatchListKey() {
		return this.bankWatchListKey;
	}

	public void setBankWatchListKey(long bankWatchListKey) {
		this.bankWatchListKey = bankWatchListKey;
	}

	public String getBankWatchListNumber() {
		return this.bankWatchListNumber;
	}

	public void setBankWatchListNumber(String bankWatchListNumber) {
		this.bankWatchListNumber = bankWatchListNumber;
	}

	public String getBccCode() {
		return this.bccCode;
	}

	public void setBccCode(String bccCode) {
		this.bccCode = bccCode;
	}

	public String getChangeCurrentInd() {
		return this.changeCurrentInd;
	}

	public void setChangeCurrentInd(String changeCurrentInd) {
		this.changeCurrentInd = changeCurrentInd;
	}

	public String getDeleteInd() {
		return this.deleteInd;
	}

	public void setDeleteInd(String deleteInd) {
		this.deleteInd = deleteInd;
	}

	public String getExcludeInd() {
		return this.excludeInd;
	}

	public void setExcludeInd(String excludeInd) {
		this.excludeInd = excludeInd;
	}

	public String getMatchCodeBankName() {
		return this.matchCodeBankName;
	}

	public void setMatchCodeBankName(String matchCodeBankName) {
		this.matchCodeBankName = matchCodeBankName;
	}

	public String getNospaceNameInd() {
		return this.nospaceNameInd;
	}

	public void setNospaceNameInd(String nospaceNameInd) {
		this.nospaceNameInd = nospaceNameInd;
	}

	public Timestamp getProcessDttm() {
		return this.processDttm;
	}

	public void setProcessDttm(Timestamp processDttm) {
		this.processDttm = processDttm;
	}

	public String getSortNameInd() {
		return this.sortNameInd;
	}

	public void setSortNameInd(String sortNameInd) {
		this.sortNameInd = sortNameInd;
	}

	public String getWatchListName() {
		return this.watchListName;
	}

	public void setWatchListName(String watchListName) {
		this.watchListName = watchListName;
	}

}