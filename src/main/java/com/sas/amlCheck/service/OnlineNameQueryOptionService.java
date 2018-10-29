package com.sas.amlCheck.service;


import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sas.db.aml.dao.ecm.IFullRefTableTranDao;
import com.sas.db.aml.orm.ecm.FullRefTableTran;

/**
 * Online Name Query(線上名單查詢)，type_dsce下拉式選單查詢程式
 * 
 * @author SAS
 *
 */
@Service
public class OnlineNameQueryOptionService {
	private static final Logger logger = LoggerFactory.getLogger(OnlineNameQueryOptionService.class);
	
	@Autowired
	IFullRefTableTranDao iFullRefTableTranDao;

	/**
	 *  Online Name Query(線上名單查詢)，type_dsce下拉式選單查詢程式
	 * 
	 * @param nameCheckInputRestBean
	 * @return NameCheckOutputRestBean
	 */
	public List<FullRefTableTran> onlineNameQuerytypeDesc(String Tocale) {
		logger.debug("OnlineNameQueryOptionService locale : " + Tocale);

		String locale = Tocale;
		String refTableNm = "X_Entity_Type_Cd";
		List<FullRefTableTran> fullRefTableTran = new ArrayList<FullRefTableTran>();
		try {
			fullRefTableTran = iFullRefTableTranDao.findByIdLocaleAndIdRefTableNm(locale.trim(), refTableNm);
		} catch (Exception e) {
			logger.error(String.format("OnlineNameQueryOptionService fail, exception : %s", e.getMessage()), e);
		}

		return fullRefTableTran;
	}

}
