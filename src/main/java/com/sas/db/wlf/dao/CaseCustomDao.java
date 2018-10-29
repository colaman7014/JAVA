package com.sas.db.wlf.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.jpa.HibernateQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sas.constraint.SwiftMtConst;
import com.sas.webservice.createCase.bean.QueryHitRecordBean;

@Repository
public class CaseCustomDao {
	
	private Logger log = LoggerFactory.getLogger(CaseCustomDao.class);
	
	@PersistenceContext
	private EntityManager em;
	
	public List<QueryHitRecordBean> queryHitRecord(String refId, String caseRk, String sourceType) throws Exception{
//		String sql = null;
		StringBuffer sql = new StringBuffer();
		if(sourceType.equalsIgnoreCase(SwiftMtConst.SOURCE_TYPE_RT_SC) || sourceType.equalsIgnoreCase(SwiftMtConst.SOURCE_TYPE_BT_SC)){
			sql.append( "select hit.REFERENCE_ID AS NC_REFERENCE_ID,hit.UNIQUE_KEY, ");
			sql.append( "hit.TYPE_DESC as ENTITY_TYPE, null as ENTITY_RELATIONSHIP, ");
			sql.append( "null as ENTITY_RELATIONSHIP_DESC, hit.YEAR_OF_BIRTH, ");
			sql.append( "hit.ENTITY_NAME_DISPLAY , ");
			sql.append( "hit.CITIZENSHIP_COUNTRY_NAME as COUNTRY, hit.IDENTIFICATION_ID as ID_NUMBER, ");
			sql.append( "hit.WATCHLIST_TYPE_CD , hit.WATCH_LIST_NAME, hit.FIELD_NAME,");
			sql.append( "hit.ENTITY_WATCH_LIST_NUMBER, hit.ENTITY_WATCH_LIST_KEY,hit.FIELD_VALUE, ");
			sql.append( " null as WEIGHT_SCORE, 1 as CHECK_SEQ, hit.SEQ,  ");
			sql.append( " r.ROW_NO, r.CHECK_RESULT, r.WHITELIST_IND ");
			sql.append( " from %1$s.%2$s.SWIFT_HIT_RECORD hit  ");
			sql.append( " left join %1$s.%2$s.X_NCSC_CASE_RESULT r on hit.UNIQUE_KEY=r.UNIQUE_KEY ");
			sql.append( "AND hit.SEQ=r.SEQ AND hit.REFERENCE_ID=r.NC_REFERENCE_ID ");
			sql.append( "where hit.REFERENCE_ID=:refId ");
			sql.append( "and r.CASE_RK=:caseRk");
		}
		else{
			sql.append("select d.NC_REFERENCE_ID,d.UNIQUE_KEY, d.ENTITY_TYPE, "); 
			sql.append( "d.ENTITY_RELATIONSHIP, d.ENTITY_RELATIONSHIP_DESC, hit.YEAR_OF_BIRTH, ");
			sql.append( "hit.ENTITY_NAME_DISPLAY, hit.CITIZENSHIP_COUNTRY_NAME as COUNTRY, hit.IDENTIFICATION_ID  as ID_NUMBER, ");
			sql.append( "hit.WATCHLIST_TYPE_CD, hit.MIX_SCORE ,hit.CHECK_SEQ, hit.SEQ, hit.ENTITY_WATCH_LIST_NUMBER, ");
			sql.append( "null as FIELD_NAME, hit.ENTITY_WATCH_LIST_KEY, hit.WATCH_LIST_NAME,r.CHECK_RESULT, r.WHITELIST_IND, r.ROW_NO ");
			sql.append( "from %1$s.%2$s.NAME_HIT_RECORD hit inner join %1$s.%2$s.NAME_CHECK_RECORD_DETAIL d ");
			sql.append( "on hit.UNIQUE_KEY=d.UNIQUE_KEY AND hit.NC_REFERENCE_ID=d.NC_REFERENCE_ID ");
			sql.append( "and hit.CHECK_SEQ = d.CHECK_SEQ ");
			sql.append( " left join %1$s.%2$s.X_NCSC_CASE_RESULT r on hit.UNIQUE_KEY=r.UNIQUE_KEY ");
			sql.append( "AND hit.NC_REFERENCE_ID=r.NC_REFERENCE_ID ");
			sql.append( "AND hit.CHECK_SEQ=r.CHECK_SEQ and hit.SEQ=r.SEQ ");					
			sql.append( "where d.NC_REFERENCE_ID=:refId ");
			sql.append( "and r.CASE_RK=:caseRk");
		}
		String realSql = String.format(sql.toString(), SwiftMtConst.COM_SAS_JPACFG_NCSC_CATALOG, SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA);
//		log.info("queryHitRecord Sql : {}", realSql);
		HibernateQuery query = (HibernateQuery)em.createNativeQuery(realSql);
		((HibernateQuery)query).getHibernateQuery()
		.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		query.setParameter("refId", refId);
		query.setParameter("caseRk", caseRk);
		List<Map<String, Object>> resultList = query.getResultList();
		List<QueryHitRecordBean> returnList = new ArrayList<QueryHitRecordBean>();
		for(Map<String, Object> obj :resultList){
			QueryHitRecordBean bean = new QueryHitRecordBean();
			bean.setNcReferenceId(obj.get("NC_REFERENCE_ID")==null? null: (Integer)obj.get("NC_REFERENCE_ID"));
			bean.setUniqueKey(obj.get("UNIQUE_KEY")==null? null: obj.get("UNIQUE_KEY").toString());
			bean.setEntityType(obj.get("ENTITY_TYPE")==null? null: obj.get("ENTITY_TYPE").toString());
			bean.setEntityRelationship(obj.get("ENTITY_RELATIONSHIP")==null? null: obj.get("ENTITY_RELATIONSHIP").toString());
			bean.setEntityRelationshipDesc(obj.get("ENTITY_RELATIONSHIP_DESC")==null? null: obj.get("ENTITY_RELATIONSHIP_DESC").toString());
			bean.setYearOfBirth(obj.get("YEAR_OF_BIRTH")==null? null: obj.get("YEAR_OF_BIRTH").toString());
//			bean.setEnglishName(obj.get("ENGLISH_NAME")==null? null: obj.get("ENGLISH_NAME").toString());
//			bean.setNonEnglishName(obj.get("NON_ENGLISH_NAME")==null? null: obj.get("NON_ENGLISH_NAME").toString());
			bean.setEntiyNameDisplay(obj.get("ENTITY_NAME_DISPLAY")== null ? null : obj.get("ENTITY_NAME_DISPLAY").toString()) ;
			bean.setCountry(obj.get("COUNTRY")==null? null: obj.get("COUNTRY").toString());
			bean.setIdNumber(obj.get("ID_NUMBER")==null? null: obj.get("ID_NUMBER").toString());
			bean.setWatchListTypeCd(obj.get("WATCHLIST_TYPE_CD")==null? null: obj.get("WATCHLIST_TYPE_CD").toString().trim());
			bean.setWatchListName(obj.get("WATCH_LIST_NAME")==null? null: obj.get("WATCH_LIST_NAME").toString());
			bean.setEntityWatchListNumber(obj.get("ENTITY_WATCH_LIST_NUMBER")==null? null: obj.get("ENTITY_WATCH_LIST_NUMBER").toString());
			bean.setEntityWatchListKey(obj.get("ENTITY_WATCH_LIST_KEY")==null? null: obj.get("ENTITY_WATCH_LIST_KEY").toString());
			bean.setFieldValue(obj.get("FIELD_VALUE")==null? null: obj.get("FIELD_VALUE").toString());
			bean.setMixScore(obj.get("MIX_SCORE") ==null ? 0 : (Integer)obj.get("MIX_SCORE"));
			bean.setCheckSeq(obj.get("CHECK_SEQ")==null ? null: obj.get("CHECK_SEQ").toString());
			bean.setSeq(obj.get("SEQ")==null? null: obj.get("SEQ").toString());
			bean.setCheckResult(obj.get("CHECK_RESULT")==null? null: obj.get("CHECK_RESULT").toString());
			bean.setWhiteListInd(obj.get("WHITELIST_IND")==null? null: obj.get("WHITELIST_IND").toString());
			bean.setRowNo(obj.get("ROW_NO")==null? null: Integer.valueOf(obj.get("ROW_NO").toString()));
			bean.setFieldName(obj.get("FIELD_NAME")==null? null: obj.get("FIELD_NAME").toString());
			returnList.add(bean);
		}
		return returnList;
	}
	
	public List<Map<String, Object>> queryCheckHit(String sourceType,String uniqueKey, String ncReferenceId) {
		String sql = "";
		if(sourceType.equalsIgnoreCase("RT-SC") || sourceType.equalsIgnoreCase("BT-SC")){
			sql =	" select a.UNIQUE_KEY, a.REFERENCE_ID as NC_REFERENCE_ID, null as CHECK_SEQ, b.SEQ "
			.concat(" from " + String.format("%s.%s.SWIFT_CHECK_RECORD a, %s.%s.SWIFT_HIT_RECORD b", SwiftMtConst.COM_SAS_JPACFG_NCSC_CATALOG, SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA, SwiftMtConst.COM_SAS_JPACFG_NCSC_CATALOG, SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA))	
			.concat(" where a.REFERENCE_ID =  :ncReferenceId")
			.concat(" and a.UNIQUE_KEY = :uniqueKey")
			.concat(" and a.ROUTE_RULE is not null")
			.concat(" and a.REFERENCE_ID = b.REFERENCE_ID")
			.concat(" and a.UNIQUE_KEY = b.UNIQUE_KEY");
		}else {
			sql =	" select a.UNIQUE_KEY, a.NC_REFERENCE_ID, b.CHECK_SEQ, b.SEQ "
			.concat(" from " + String.format("%s.%s.NAME_CHECK_RECORD_DETAIL a, %s.%s.NAME_HIT_RECORD b", SwiftMtConst.COM_SAS_JPACFG_NCSC_CATALOG, SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA, SwiftMtConst.COM_SAS_JPACFG_NCSC_CATALOG, SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA))	
			.concat(" where a.UNIQUE_KEY = :uniqueKey")
			.concat(" and a.NC_REFERENCE_ID = :ncReferenceId")
			.concat(" and a.ROUTE_RULE is not null")
			.concat(" and a.UNIQUE_KEY = b.UNIQUE_KEY")
			.concat(" and a.NC_REFERENCE_ID = b.NC_REFERENCE_ID")
			.concat(" and a.CHECK_SEQ = b.CHECK_SEQ");
		}
		
		HibernateQuery query = (HibernateQuery)em.createNativeQuery(sql);
		((HibernateQuery)query).getHibernateQuery()
		.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		query.setParameter("ncReferenceId", ncReferenceId);
		query.setParameter("uniqueKey", uniqueKey);
		List<Map<String, Object>> resultList = query.getResultList();
		return resultList;
	}
	
	
	
	public List<Map<String, Object>> queryNCDetail(String caseRk) {
		StringBuffer sb = new StringBuffer();
		sb.append("select a.CHECK_SEQ, a.ENTITY_TYPE, a.ENTITY_RELATIONSHIP, a.ENTITY_RELATIONSHIP_DESC, a.ENGLISH_NAME, a.NON_ENGLISH_NAME,");
		sb.append(" a.CCC_CODE, a.ID_NUMBER,  a.BIC_SWIFT_CODE, a.FREE_FORMAT_TEXT, a.COUNTRY, a.YEAR_OF_BIRTH, a.GENDER ");
		sb.append(String.format(" from %s.%s.NAME_CHECK_RECORD_DETAIL a, %s.%s.NAME_CHECK_RECORD_MAIN b ", SwiftMtConst.COM_SAS_JPACFG_NCSC_CATALOG, SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA, SwiftMtConst.COM_SAS_JPACFG_NCSC_CATALOG, SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA));
		sb.append(" where b.CASE_RK = :caseRk");
		sb.append(" and a.UNIQUE_KEY = b.UNIQUE_KEY");
		sb.append(" and b.NC_REFERENCE_ID = b.NC_REFERENCE_ID");
				
		HibernateQuery query = (HibernateQuery)em.createNativeQuery(sb.toString());
		((HibernateQuery)query).getHibernateQuery()
		.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		query.setParameter("caseRk", caseRk);
		List<Map<String, Object>> resultList = query.getResultList();
		return resultList;		
	}

}