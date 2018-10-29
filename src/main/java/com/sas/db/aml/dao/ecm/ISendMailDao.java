package com.sas.db.aml.dao.ecm;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.ecm.SendMail;

public interface ISendMailDao extends CrudRepository<SendMail, Integer>{

}
