package com.sas.wlsearch.bean;

public class MatchCodeResultBean {
	private String matchcodeInd;
	private String matchcodeOrg;
	public String getMatchcodeInd() {
		return matchcodeInd;
	}
	public void setMatchcodeInd(String matchcodeInd) {
		this.matchcodeInd = matchcodeInd;
	}
	public String getMatchcodeOrg() {
		return matchcodeOrg;
	}
	public void setMatchcodeOrg(String matchcodeOrg) {
		this.matchcodeOrg = matchcodeOrg;
	}
	@Override
	public String toString() {
		return "MatchCodeResultBean [matchcodeInd=" + matchcodeInd
				+ ", matchcodeOrg=" + matchcodeOrg + "]";
	}
}
