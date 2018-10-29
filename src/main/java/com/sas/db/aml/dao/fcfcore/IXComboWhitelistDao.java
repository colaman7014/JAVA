package com.sas.db.aml.dao.fcfcore;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.fcfcore.XComboWhitelist;
import com.sas.db.aml.orm.fcfcore.XComboWhitelistPK;

public interface IXComboWhitelistDao  extends CrudRepository<XComboWhitelist, XComboWhitelistPK>{

	public XComboWhitelist findByIdPartyNumberAndEntityDisplayName(String partyNumber, String entityDisplayName);
}
