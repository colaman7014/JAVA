package com.sas.db.aml.dao.fcfcore;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.jpa.HibernateQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sas.constraint.SwiftMtConst;
import com.sas.db.aml.orm.fcfcore.FscPartyDimScanCandidate;

public class IFscPartyDimScanCandidateDaoImpl implements IFscPartyDimScanCandidateDaoCustom{
	
	private static final Logger logger = LoggerFactory.getLogger(IFscPartyDimScanCandidateDaoImpl.class);
	
	@PersistenceContext(unitName = "amlEntityManagerFactory")
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<FscPartyDimScanCandidate> nativeSql(String sql) {
		
		Query query = entityManager.createNativeQuery(sql, FscPartyDimScanCandidate.class);
		List<FscPartyDimScanCandidate> resultList = (List<FscPartyDimScanCandidate>)query.getResultList();
		
		return resultList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int queryCountFscPartyDimScanCandidate() {	
		StringBuffer sb = new StringBuffer();
		sb.append("   select count(*) as count_number  ");
		sb.append("   from ( ");
		sb.append("   select ROW_NUMBER() OVER(ORDER BY PARTY_KEY ) AS Row  ");
		sb.append("   from ").append(SwiftMtConst.COM_SAS_JPACFG_AML_CATALOG).append(".").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_PARTY_DIM_SCAN_CANDIDATE t1");
		sb.append("   	) as T1 ");
		
		HibernateQuery query = (HibernateQuery) entityManager.createNativeQuery(sb.toString());
		((HibernateQuery)query).getHibernateQuery().setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> tmpList = query.getResultList();
		int countNumber = 0;
		for(Map<String, Object> obj : tmpList){
			countNumber = (int) (obj.get("count_number")==null? 0: obj.get("count_number"));
		}
		return countNumber;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> queryCountFscPartyDimScanCandidate(int indexFrom , int indexTo) {	
		StringBuffer sb = new StringBuffer();
		sb.append("   select t1.PARTY_KEY ");
		sb.append("   from ( ");
		sb.append("   select ROW_NUMBER() OVER(ORDER BY PARTY_KEY ) AS Row , PARTY_KEY ");
		sb.append("   from ").append(SwiftMtConst.COM_SAS_JPACFG_AML_CATALOG).append(".").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_PARTY_DIM_SCAN_CANDIDATE t1");
		sb.append("   	) as T1 ");
		sb.append("   where t1.Row between ").append(indexFrom).append(" and ").append(indexTo);
		HibernateQuery query = (HibernateQuery) entityManager.createNativeQuery(sb.toString());
		((HibernateQuery)query).getHibernateQuery().setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> tmpList = query.getResultList();		
		List<Long> partyKeyList = new ArrayList<Long>();
		for(Map<String, Object> obj : tmpList){
			BigDecimal partyKeyTmp = (BigDecimal) (obj.get("PARTY_KEY")==null? 0: obj.get("PARTY_KEY"));
			partyKeyList.add(partyKeyTmp.longValue());
		}
		return partyKeyList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void truncateFscPartyDimScanCandidate() {	
		StringBuffer sb = new StringBuffer();
		sb.append("   truncate table  ").append(SwiftMtConst.COM_SAS_JPACFG_AML_CATALOG).append(".").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_PARTY_DIM_SCAN_CANDIDATE ");
		HibernateQuery query = (HibernateQuery) entityManager.createNativeQuery(sb.toString());
		((HibernateQuery)query).getHibernateQuery().setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		query.executeUpdate();


	}
	
	
}
