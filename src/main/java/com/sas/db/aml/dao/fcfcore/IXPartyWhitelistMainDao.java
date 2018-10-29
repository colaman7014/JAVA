package com.sas.db.aml.dao.fcfcore;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

 

import com.sas.db.aml.orm.fcfcore.XPartyWhitelistMain;
import com.sas.db.aml.orm.fcfcore.XPartyWhitelistMainPK;
/**
 * 客戶白名單
 * @author SAS
 *
 */
public interface IXPartyWhitelistMainDao  extends CrudRepository<XPartyWhitelistMain, XPartyWhitelistMainPK>{

	public List<XPartyWhitelistMain> findByIdPartyNumber(String partyNumber);
	public List<XPartyWhitelistMain> findByIdPartyNameLike(String partyName);
	public List<XPartyWhitelistMain> findByIdPartyNumberAndIdPartyNameLike(String partyNumber, String partyName);
	public List<XPartyWhitelistMain> findById(XPartyWhitelistMainPK xPartyWhitelistMainPK);
	public List<XPartyWhitelistMain> findByCaseActionAndCaseDisposition(String caseActionNew,String caseDispositionApproval);
	public List<XPartyWhitelistMain> findByCaseAction(String caseActionStop);
	public List<XPartyWhitelistMain> findByIdPartyNameLikeAndCaseAction(String partyName, String caseActionStop);
	public List<XPartyWhitelistMain> findByIdPartyNumberAndCaseAction(String partyNumber, String caseActionStop);
	public List<XPartyWhitelistMain> findByIdPartyNumberAndIdPartyNameLikeAndCaseAction(String partyNumber,
			String partyName, String caseActionStop);
}
