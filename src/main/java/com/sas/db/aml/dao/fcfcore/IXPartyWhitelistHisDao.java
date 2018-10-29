package com.sas.db.aml.dao.fcfcore;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.fcfcore.XPartyWhitelistHis;

/**
 * 客戶白名單歷史檔
 * @author SAS
 *
 */
public interface IXPartyWhitelistHisDao  extends CrudRepository<XPartyWhitelistHis, String>{
	public List<XPartyWhitelistHis> findByPartyNumber(String partyNumber);
	public List<XPartyWhitelistHis> findByPartyNameLike(String partyName);
	public List<XPartyWhitelistHis> findByPartyNumberAndPartyNameLike(String partyNumber, String partyName);
	public List<XPartyWhitelistHis> findAll();
}
