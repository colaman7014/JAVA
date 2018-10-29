package com.sas.db.aml.dao.fcfcore;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.jpa.HibernateQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sas.constraint.SwiftMtConst;
import com.sas.db.aml.orm.fcfcore.FscPartyDim;
import com.sas.db.aml.orm.fcfcore.FscPartyDimPK;
import com.sas.util.AmlConfiguration;
import com.sas.util.StringUtils;

import ch.qos.logback.core.net.SyslogOutputStream;

public class IFscPartyDimDaoImpl implements IFscPartyDimDaoCustom{
	
	private static final Logger logger = LoggerFactory.getLogger(IFscPartyDimDaoImpl.class);
	
	@PersistenceContext(unitName = "amlEntityManagerFactory")
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<FscPartyDim> nativeSql(String sql) {
		
		Query query = entityManager.createNativeQuery(sql, FscPartyDim.class);
		List<FscPartyDim> resultList = (List<FscPartyDim>)query.getResultList();
		
		return resultList;
	}
//
//	@Override
//	public String getInculsiveQuerySql(List<String> inculsiveList, boolean isChineseName) {
//		String parsingColumn = null;
//		if(isChineseName){
//			parsingColumn = "PARTY_NAME_2";
//		}
//		else{
//			parsingColumn = "PARTY_NAME";
//		}
//		
//		StringBuffer sb = new StringBuffer();
//		String select = "   SELECT * ";
//		String from = "   FROM AMLHK0.FCFCOREHK0.FSC_PARTY_DIM ";
//		
//		StringBuffer csb = new StringBuffer();
//		for(String str : inculsiveList){
//			csb.append(" OR CONTAINS(").append(parsingColumn).append(String.format(", '%s')", StringUtils.handelEscape(str)));
//		}
//		String andInculsiveOr = String.format(" AND (%s)", csb.substring(3));
//		
//		String where = String.format("   WHERE CHANGE_CURRENT_IND = 'Y' %s  ", andInculsiveOr);
//		return sb.append(select).append(from).append(where).toString();
//	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FscPartyDim> queryInclusiveNameCheck(List<String> inculsiveList, boolean isChineseName) {
		String parsingColumn = null;
		if(isChineseName){
			parsingColumn = "PARTY_NAME_ENG";
		}
		else{
			parsingColumn = "PARTY_NAME";
		}
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT PARTY_IDENTIFICATION_ID, PARTY_NAME, PARTY_NAME_ENG ");
		sb.append(String.format(" FROM %s.%s.FSC_PARTY_DIM_VIEW ", AmlConfiguration.getAmlCatalog(), AmlConfiguration.getFcfcoreSchema()));
		
		StringBuffer csb = new StringBuffer();
		for(String str : inculsiveList){
			csb.append(" OR CONTAINS(").append(parsingColumn).append(String.format(", '%s')", StringUtils.handelEscape(str)));
		}
		String andInculsiveOr = String.format(" AND (%s)", csb.substring(3));
		
		String where = String.format("   WHERE CHANGE_CURRENT_IND = 'Y' %s  ", andInculsiveOr);
		sb.append(where);

		logger.info("queryInclusiveNameCheck,  "
				+ ", isChineseName=" + isChineseName);

		HibernateQuery query = (HibernateQuery) entityManager.createNativeQuery(sb.toString());
		((HibernateQuery)query).getHibernateQuery().setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> tmpList = query.getResultList();
		List<FscPartyDim> returnList = new ArrayList<FscPartyDim>();
		for(Map<String, Object> obj : tmpList){
			FscPartyDim bean = new FscPartyDim();
			String partyIdentificationId = obj.get("PARTY_IDENTIFICATION_ID")==null? null: obj.get("PARTY_IDENTIFICATION_ID").toString();
			String partyName = obj.get("PARTY_NAME")==null? null: obj.get("PARTY_NAME").toString();
			String partyNameEng = obj.get("PARTY_NAME_ENG")==null? null: obj.get("PARTY_NAME_ENG").toString();
			bean.setPartyIdentificationId(partyIdentificationId);
			bean.setPartyName(partyName);
			bean.setPartyNameEng(partyNameEng);
			returnList.add(bean);
		}
		return returnList;
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> queryExactTransactionCheck(
			String partyIdentificationId, String partyName, 
			String beginDate, String endDate, boolean isChineseName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT pd.PARTY_IDENTIFICATION_ID, pd.PARTY_NAME, pd.PARTY_NAME_ENG, ");
		sb.append(" f.REMITTER_EXT_PARTY_KEY, f.BENEFICIARY_EXT_PARTY_KEY, f.SECONDARY_ACCOUNT_KEY, ");
		sb.append(" (case "); 
		sb.append("   when f.remitter_ext_party_key>-1 ");
		sb.append("     then (select epam.full_name from ").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_EXT_PARTY_ACCOUNT_DIM epam ");
		sb.append("        where epam.ext_party_account_key = f.remitter_ext_party_key ");
		sb.append("        and f.segment_id=epam.segment_id) ");
		sb.append("   when f.beneficiary_ext_party_key>-1 ");
		sb.append("     then (select epam.full_name from ").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_EXT_PARTY_ACCOUNT_DIM epam ");
		sb.append("        where epam.ext_party_account_key = f.beneficiary_ext_party_key ");
		sb.append("        and f.segment_id=epam.segment_id) ");
		sb.append("   else null ");
		sb.append(" end) as TRANCACTION_NAME, ");
		sb.append(" td.TRANSACTION_REFERENCE_NUMBER, f.DATE_KEY, ");
		sb.append(" ttd.TRANSACTION_CDI_DESC + ttd.PRIMARY_MEDIUM_DESC as TRANSACTION_TYPE, ");
		sb.append(" f.CURRENCY_AMOUNT ");
		sb.append(String.format(" FROM %s.%s.FSC_PARTY_DIM  pd ", AmlConfiguration.getAmlCatalog(), AmlConfiguration.getFcfcoreSchema()));
		sb.append(" INNER JOIN ").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_PARTY_ACCOUNT_BRIDGE b ");
		sb.append(" ON pd.PARTY_KEY=b.PARTY_KEY ");
		sb.append(String.format(" INNER JOIN %s.%s.FSC_CASH_FLOW_FACT f ", AmlConfiguration.getAmlCatalog(), AmlConfiguration.getFcfcoreSchema()));
		sb.append(" on b.ACCOUNT_KEY=f.ACCOUNT_KEY ");
		sb.append(" AND f.DATE_KEY>").append(beginDate);
		sb.append(" AND f.DATE_KEY < ").append(endDate);
		sb.append(" INNER JOIN ").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_TRANSACTION_DIM td ");
		sb.append(" ON f.TRANSACTION_KEY=td.TRANSACTION_KEY AND f.segment_id=td.segment_id ");
		sb.append(" INNER JOIN ").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_TRANSACTION_TYPE_DIM ttd ");
		sb.append(" ON f.TRANSACTION_TYPE_KEY=ttd.TRANSACTION_TYPE_KEY ");
		sb.append(" WHERE (f.remitter_ext_party_key<>-1 or f.beneficiary_ext_party_key<>-1) ");
		sb.append(" AND (pd.PARTY_IDENTIFICATION_ID=:partyIdentificationId ");
		if(isChineseName){
			sb.append(" OR pd.PARTY_NAME_ENG=:partyName)");
		}
		else{
			sb.append(" OR pd.PARTY_NAME=:partyName)");
		}
		
		logger.info("queryExactTransactionCheck,  "
				+ "partyIdentificationId=" + partyIdentificationId  
				+ ", partyName=" + partyName  
				+ ", beginDate=" + beginDate  
				+ ", endDate=" + endDate  
				+ ", isChineseName=" + isChineseName);
		HibernateQuery query = (HibernateQuery) entityManager.createNativeQuery(sb.toString());
		((HibernateQuery)query).getHibernateQuery().setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		query.setParameter("partyIdentificationId", partyIdentificationId);
		query.setParameter("partyName", partyName);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> queryFuzzyTransactionCheck(
			List<String> matchCodeList, String beginDate,
			String endDate, boolean isChineseName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT pd.PARTY_IDENTIFICATION_ID, pd.PARTY_NAME, pd.PARTY_NAME_ENGS, ");
		sb.append(" f.REMITTER_EXT_PARTY_KEY, f.BENEFICIARY_EXT_PARTY_KEY, f.SECONDARY_ACCOUNT_KEY, ");
		sb.append(" (case "); 
		sb.append("   when f.remitter_ext_party_key>-1 ");
		sb.append("     then (select epam.full_name from ").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_EXT_PARTY_ACCOUNT_DIM epam ");
		sb.append("        where epam.ext_party_account_key = f.remitter_ext_party_key ");
		sb.append("        and f.segment_id=epam.segment_id) ");
		sb.append("   when f.beneficiary_ext_party_key>-1 ");
		sb.append("     then (select epam.full_name from ").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_EXT_PARTY_ACCOUNT_DIM epam ");
		sb.append("        where epam.ext_party_account_key = f.beneficiary_ext_party_key ");
		sb.append("        and f.segment_id=epam.segment_id) ");
		sb.append("   else null ");
		sb.append(" end) as TRANCACTION_NAME, ");
		sb.append(" td.TRANSACTION_REFERENCE_NUMBER, f.DATE_KEY, ");
		sb.append(" ttd.TRANSACTION_CDI_DESC + ttd.PRIMARY_MEDIUM_DESC as TRANSACTION_TYPE, ");
		sb.append(" f.CURRENCY_AMOUNT ");
		sb.append(String.format(" FROM %s.%s.FSC_PARTY_DIM  pd ", AmlConfiguration.getAmlCatalog(), AmlConfiguration.getFcfcoreSchema()));
		sb.append(" INNER JOIN ").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_PARTY_ACCOUNT_BRIDGE b ");
		sb.append(" ON pd.PARTY_KEY=b.PARTY_KEY ");
		sb.append(String.format(" INNER JOIN %s.%s.FSC_CASH_FLOW_FACT f ", AmlConfiguration.getAmlCatalog(), AmlConfiguration.getFcfcoreSchema()));
		sb.append(" on b.ACCOUNT_KEY=f.ACCOUNT_KEY ");
		sb.append(" AND f.DATE_KEY>").append(beginDate);
		sb.append(" AND f.DATE_KEY < ").append(endDate);
		sb.append(" INNER JOIN ").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_TRANSACTION_DIM td ");
		sb.append(" ON f.TRANSACTION_KEY=td.TRANSACTION_KEY AND f.segment_id=td.segment_id ");
		sb.append(" INNER JOIN ").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_TRANSACTION_TYPE_DIM ttd ");
		sb.append(" ON f.TRANSACTION_TYPE_KEY=ttd.TRANSACTION_TYPE_KEY ");
		sb.append(" WHERE (f.remitter_ext_party_key<>-1 or f.beneficiary_ext_party_key<>-1) ");
		
		String parsingColumn = null;
		if(isChineseName){
			parsingColumn = "pd.match_code_party_name_ENG";
		}
		else{
			parsingColumn = "pd.match_code_party_name";
		}
		
		StringBuffer csb = new StringBuffer();
		for(String str : matchCodeList){
			csb.append(" OR ").append(parsingColumn).append(String.format(" = '%s'", StringUtils.handelEscape(str)));
		}
		String whereInculsiveOr = String.format(" AND (%s)", csb.substring(3));
		sb.append(whereInculsiveOr);


		logger.info("queryFuzzyTransactionCheck,  "
				+ ", beginDate=" + beginDate  
				+ ", endDate=" + endDate  
				+ ", isChineseName=" + isChineseName);
		
		HibernateQuery query = (HibernateQuery) entityManager.createNativeQuery(sb.toString());
		((HibernateQuery)query).getHibernateQuery().setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> queryInclusiveTransactionCheck(List<String> inculsiveList, String beginDate,
			String endDate, boolean isChineseName) {
		String parsingColumn = null;
		if(isChineseName){
			parsingColumn = "pd.PARTY_NAME_ENG";
		}
		else{
			parsingColumn = "pd.PARTY_NAME";
		}
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT pd.PARTY_IDENTIFICATION_ID, pd.PARTY_NAME, pd.PARTY_NAME_ENG, ");
		sb.append(" f.REMITTER_EXT_PARTY_KEY, f.BENEFICIARY_EXT_PARTY_KEY, f.SECONDARY_ACCOUNT_KEY, ");
		sb.append(" (case "); 
		sb.append("   when f.remitter_ext_party_key>-1 ");
		sb.append("     then (select epam.full_name from ").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_EXT_PARTY_ACCOUNT_DIM epam ");
		sb.append("        where epam.ext_party_account_key = f.remitter_ext_party_key ");
		sb.append("        and f.segment_id=epam.segment_id) ");
		sb.append("   when f.beneficiary_ext_party_key>-1 ");
		sb.append("     then (select epam.full_name from ").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_EXT_PARTY_ACCOUNT_DIM epam ");
		sb.append("        where epam.ext_party_account_key = f.beneficiary_ext_party_key ");
		sb.append("        and f.segment_id=epam.segment_id) ");
		sb.append("   else null ");
		sb.append(" end) as TRANCACTION_NAME, ");
		sb.append(" td.TRANSACTION_REFERENCE_NUMBER, f.DATE_KEY, ");
		sb.append(" ttd.TRANSACTION_CDI_DESC + ttd.PRIMARY_MEDIUM_DESC as TRANSACTION_TYPE, ");
		sb.append(" f.CURRENCY_AMOUNT ");
		sb.append(String.format(" FROM %s.%s.FSC_PARTY_DIM_VIEW  pd ", AmlConfiguration.getAmlCatalog(), AmlConfiguration.getFcfcoreSchema()));
		sb.append(" INNER JOIN ").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_PARTY_ACCOUNT_BRIDGE b ");
		sb.append(" ON pd.PARTY_KEY=b.PARTY_KEY ");
		sb.append(String.format(" INNER JOIN %s.%s.FSC_CASH_FLOW_FACT f ", AmlConfiguration.getAmlCatalog(), AmlConfiguration.getFcfcoreSchema()));
		sb.append(" on b.ACCOUNT_KEY=f.ACCOUNT_KEY ");
		sb.append(" AND f.DATE_KEY>").append(beginDate);
		sb.append(" AND f.DATE_KEY < ").append(endDate);
		sb.append(" INNER JOIN ").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_TRANSACTION_DIM td ");
		sb.append(" ON f.TRANSACTION_KEY=td.TRANSACTION_KEY AND f.segment_id=td.segment_id ");
		sb.append(" INNER JOIN ").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_TRANSACTION_TYPE_DIM ttd ");
		sb.append(" ON f.TRANSACTION_TYPE_KEY=ttd.TRANSACTION_TYPE_KEY ");
		sb.append(" WHERE (f.remitter_ext_party_key<>-1 or f.beneficiary_ext_party_key<>-1) ");
		
		StringBuffer csb = new StringBuffer();
		for(String str : inculsiveList){
			csb.append(" OR CONTAINS(").append(parsingColumn).append(String.format(", '%s')", StringUtils.handelEscape(str)));
		}
		String whereInculsiveOr = String.format(" AND (%s)", csb.substring(3));
		sb.append(whereInculsiveOr);

		logger.info("queryFuzzyTransactionCheck,  "
				+ ", beginDate=" + beginDate  
				+ ", endDate=" + endDate  
				+ ", isChineseName=" + isChineseName);

		HibernateQuery query = (HibernateQuery) entityManager.createNativeQuery(sb.toString());
		((HibernateQuery)query).getHibernateQuery().setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		return query.getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FscPartyDim> queryBatchNameCheck(String externalPartyInd, int indexFrom , int indexTo) {

		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT primary_branch_number, party_number , party_type_desc ,party_name, party_name_eng ");
		sb.append(" ,party_identification_id , party_date_of_birth, match_code_party_name , match_code_party_name_eng ");
		sb.append(" ,citizenship_country_name ");
		sb.append("  FROM ( ");
		sb.append("     SELECT ROW_NUMBER() OVER(ORDER BY party_key) AS Row,  * ");
		sb.append("     FROM ").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_PARTY_DIM " );
		sb.append("     WHERE change_Current_Ind ='Y' AND external_party_ind = '").append(externalPartyInd).append("'  ");
		sb.append("     AND (party_status_desc != 'D' OR party_status_desc is null)  ");
		sb.append("   ) AS T1 ");
		sb.append(" WHERE T1.Row BETWEEN  ").append( indexFrom ).append(" AND ").append( indexTo );

		HibernateQuery query = (HibernateQuery) entityManager.createNativeQuery(sb.toString());
		((HibernateQuery)query).getHibernateQuery().setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> tmpList = query.getResultList();
		List<FscPartyDim> returnList = new ArrayList<FscPartyDim>();
		for(Map<String, Object> obj : tmpList){
			FscPartyDim bean = new FscPartyDim();
			
			String primary_branch_number = obj.get("primary_branch_number")==null? null: obj.get("primary_branch_number").toString();
			String party_number = obj.get("party_number")==null? null: obj.get("party_number").toString();
			String party_type_desc = obj.get("party_type_desc")==null? null: obj.get("party_type_desc").toString();
			String party_name = obj.get("party_name")==null? null: obj.get("party_name").toString();
			String party_name_eng = obj.get("party_name_eng")==null? null: obj.get("party_name_eng").toString();
			String party_identification_id = obj.get("party_identification_id")==null? null: obj.get("party_identification_id").toString();
			Timestamp party_date_of_birth = (Timestamp) (obj.get("party_date_of_birth")==null ? null: obj.get("party_date_of_birth"));
			String match_code_party_name = obj.get("match_code_party_name")==null? null: obj.get("match_code_party_name").toString();
		    String match_code_party_name_eng = obj.get("match_code_party_name_eng")==null? null: obj.get("match_code_party_name_eng").toString();
		    
		    bean.setPrimaryBranchNumber(primary_branch_number);
		    bean.setPartyName(party_number);
		    bean.setPartyTypeDesc(party_type_desc);
		    bean.setPartyName(party_name);
		    bean.setPartyNameEng(party_name_eng);
		    bean.setPartyIdentificationId(party_identification_id);
		    bean.setPartyDateOfBirth(party_date_of_birth);
		    bean.setMatchCodePartyName(match_code_party_name);
		    bean.setMatchCodePartyNameEng(match_code_party_name_eng);
		    
			returnList.add(bean);
		}
		return returnList;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public int countBatchNameCheck(String externalPartyInd) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT COUNT(*) AS party_count ");
		sb.append("     FROM ").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_PARTY_DIM " );
		sb.append(" WHERE change_Current_Ind ='Y'  AND external_party_ind = '").append(externalPartyInd).append("'  ");
		sb.append(" AND (party_status_desc != 'D' OR party_status_desc is null)  ");

		HibernateQuery query = (HibernateQuery) entityManager.createNativeQuery(sb.toString());
		((HibernateQuery)query).getHibernateQuery().setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> tmpList = query.getResultList();
		int party_count = 0;
		for(Map<String, Object> obj : tmpList){		
			party_count = (int) (obj.get("party_count") == null ? 0: obj.get("party_count"));
		}
		return party_count;
	}	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<Long,List<String>> queryCandidateInclusive(List<String> inculsiveList, String entityTypeCode) {
		StringBuffer sb = new StringBuffer();
		String select = "   SELECT party_key , party_name_compress , party_name_eng_compress , party_type_code ";
		String from = String.format("   FROM %s.%s.FSC_PARTY_DIM_WLF   ", AmlConfiguration.getAmlCatalog(), AmlConfiguration.getFcfcoreSchema());	
		StringBuffer csb = new StringBuffer();
		for(String str : inculsiveList){
			csb.append(String.format(" OR CONTAINS(PARTY_NAME_ENG_COMPRESS , '%s')", StringUtils.handelEscape(str)));
		}
		for(String str : inculsiveList){
			csb.append(String.format(" OR CONTAINS(PARTY_NAME_COMPRESS , '%s')", StringUtils.handelEscape(str)));
		}
		String andInculsiveOr = String.format(" AND (%s)", csb.substring(3));
		String where = String.format(" WHERE CHANGE_CURRENT_IND = 'Y'  %s  ", andInculsiveOr);
		HibernateQuery query = (HibernateQuery) entityManager.createNativeQuery(sb.append(select).append(from).append(where).toString());
		((HibernateQuery)query).getHibernateQuery().setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> tmpList = query.getResultList();
		Map<Long,List<String>> returnListMap = new HashMap<Long,List<String>>();
		List<String> partyNameList;
		for(Map<String, Object> obj : tmpList){		
			partyNameList = new ArrayList<String>();
			String party_name_compress = (String) (obj.get("party_name_compress")==null? "": obj.get("party_name_compress"));
			String party_name_eng_compress = (String) (obj.get("party_name_eng_compress")==null? "": obj.get("party_name_eng_compress"));
			String partyTypeCode = (String) (obj.get("party_type_code")==null? "": obj.get("party_type_code"));
			if("E".equals(partyTypeCode) || "FI".equals(partyTypeCode) || "GV".equals(partyTypeCode) ||"NPI".equals(partyTypeCode) 
					||"COR".equals(partyTypeCode) ||"PS".equals(partyTypeCode)  ||"PI".equals(partyTypeCode) ){
				//為法人
				if("03".equals(entityTypeCode) || "04".equals(entityTypeCode) || "09".equals(entityTypeCode)){
					if(!"".equals(party_name_compress)){
						partyNameList.add(party_name_compress);
					}
					if(!"".equals(party_name_eng_compress)){
						partyNameList.add(party_name_eng_compress);
					}
					long party_key = ((BigDecimal) (obj.get("party_key")==null? 0: obj.get("party_key"))).longValue();
					returnListMap.put(party_key, partyNameList);
				}
			}else{
			   //為個人
	
				if("02".equals(entityTypeCode) || "09".equals(entityTypeCode)){
					if(!"".equals(party_name_compress)){
						partyNameList.add(party_name_compress);
					}
					if(!"".equals(party_name_eng_compress)){
						partyNameList.add(party_name_eng_compress);
					}
					long party_key = ((BigDecimal) (obj.get("party_key")==null? 0: obj.get("party_key"))).longValue();
					returnListMap.put(party_key, partyNameList);
				}
			}

		}
		return returnListMap;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> queryPartyKeyCandidateByExactOrFuzzyOrId() {
		StringBuffer sb = new StringBuffer();
		sb.append("select  distinct(t2.party_key)   ");
		sb.append(" from ").append(SwiftMtConst.COM_SAS_JPACFG_AML_CATALOG).append(".").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_ENTITY_WATCH_LIST_DIM_CHNG t1,   ");
		sb.append(SwiftMtConst.COM_SAS_JPACFG_AML_CATALOG).append(".").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_PARTY_DIM_WLF t2  ");
		sb.append("where t1.entity_name = t2.party_name_compress ");
		sb.append("and t1.change_current_ind = 'Y' and t1.delete_ind = 'N' and t1.exclude_Ind = 'N'    ");
		sb.append("and t2.change_current_ind = 'Y' and (t2.party_status_desc != 'D' OR t2.party_status_desc is null)    ");
		sb.append(" union 	   ");
		sb.append("select  distinct(t2.party_key)  ");
		sb.append(" from ").append(SwiftMtConst.COM_SAS_JPACFG_AML_CATALOG).append(".").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_ENTITY_WATCH_LIST_DIM_CHNG t1,   ");
		sb.append(SwiftMtConst.COM_SAS_JPACFG_AML_CATALOG).append(".").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_PARTY_DIM_WLF t2  ");
		sb.append("where t1.entity_name = t2.party_name_eng_compress ");
		sb.append("and t1.change_current_ind = 'Y' and t1.delete_ind = 'N' and t1.exclude_Ind = 'N'   ");
		sb.append("and t2.change_current_ind = 'Y' and (t2.party_status_desc != 'D' OR t2.party_status_desc is null)   ");
		sb.append("  union ");
		sb.append(" select  distinct(t2.party_key)  ");
		sb.append(" from ").append(SwiftMtConst.COM_SAS_JPACFG_AML_CATALOG).append(".").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_ENTITY_WATCH_LIST_DIM_CHNG t1,   ");
		sb.append(SwiftMtConst.COM_SAS_JPACFG_AML_CATALOG).append(".").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_PARTY_DIM_WLF t2  ");
		sb.append(" where t1.match_code_entity_name = t2.match_code_party_name ");
		sb.append(" and t1.change_current_ind = 'Y' and t1.delete_ind = 'N' and t1.exclude_Ind = 'N'   ");
		sb.append(" and t2.change_current_ind = 'Y' and (t2.party_status_desc != 'D' OR t2.party_status_desc is null)  ");
		sb.append(" union ");
		sb.append(" select  distinct(t2.party_key)  ");
		sb.append(" from ").append(SwiftMtConst.COM_SAS_JPACFG_AML_CATALOG).append(".").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_ENTITY_WATCH_LIST_DIM_CHNG t1,   ");
		sb.append(SwiftMtConst.COM_SAS_JPACFG_AML_CATALOG).append(".").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_PARTY_DIM_WLF t2  ");
		sb.append(" where t1.match_code_entity_name = t2.match_code_party_name_eng ");
		sb.append("  and t1.change_current_ind = 'Y' and t1.delete_ind = 'N' and t1.exclude_Ind = 'N'   ");
		sb.append(" and t2.change_current_ind = 'Y' and (t2.party_status_desc != 'D' OR t2.party_status_desc is null)  ");
		sb.append(" union ");
		sb.append(" select  distinct(t2.party_key)  ");
		sb.append(" from ").append(SwiftMtConst.COM_SAS_JPACFG_AML_CATALOG).append(".").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_ENTITY_WATCH_LIST_DIM_CHNG t1,   ");
		sb.append(SwiftMtConst.COM_SAS_JPACFG_AML_CATALOG).append(".").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_PARTY_DIM_WLF t2  ");
		sb.append(" where t1.identification_id = t2.party_identification_id ");
		sb.append(" and t1.change_current_ind = 'Y' and t1.delete_ind = 'N' and t1.exclude_Ind = 'N'   ");
		sb.append(" and t2.change_current_ind = 'Y' and (t2.party_status_desc != 'D' OR t2.party_status_desc is null)  ");
		HibernateQuery query = (HibernateQuery) entityManager.createNativeQuery(sb.toString());
		((HibernateQuery)query).getHibernateQuery().setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> tmpList = query.getResultList();
		List<Long> returnList = new ArrayList<Long>();
		for(Map<String, Object> obj : tmpList){
			long party_key = ((BigDecimal) (obj.get("party_key")==null? 0: obj.get("party_key"))).longValue();		
			returnList.add(party_key);
		}
		return returnList;	
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public int insertPartyKeyCandidateByExactOrFuzzyOrId() {
		StringBuffer sb = new StringBuffer();
		sb.append("insert into ").append(SwiftMtConst.COM_SAS_JPACFG_AML_CATALOG).append(".").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_PARTY_DIM_SCAN_CANDIDATE  ");
		sb.append("select  distinct(t2.party_key)   ");
		sb.append(" from ").append(SwiftMtConst.COM_SAS_JPACFG_AML_CATALOG).append(".").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_ENTITY_WATCH_LIST_DIM_CHNG t1,   ");
		sb.append(SwiftMtConst.COM_SAS_JPACFG_AML_CATALOG).append(".").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_PARTY_DIM_WLF t2  ");
		sb.append("where t1.entity_name = t2.party_name_compress ");
		sb.append("and t1.change_current_ind = 'Y' and t1.delete_ind = 'N' and t1.exclude_Ind = 'N'    ");
		sb.append("and t2.change_current_ind = 'Y'     ");
		sb.append(" union 	   ");
		sb.append("select  distinct(t2.party_key)  ");
		sb.append(" from ").append(SwiftMtConst.COM_SAS_JPACFG_AML_CATALOG).append(".").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_ENTITY_WATCH_LIST_DIM_CHNG t1,   ");
		sb.append(SwiftMtConst.COM_SAS_JPACFG_AML_CATALOG).append(".").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_PARTY_DIM_WLF t2  ");
		sb.append("where t1.entity_name = t2.party_name_eng_compress ");
		sb.append("and t1.change_current_ind = 'Y' and t1.delete_ind = 'N' and t1.exclude_Ind = 'N'   ");
		sb.append("and t2.change_current_ind = 'Y'    ");
		sb.append("  union ");
		sb.append(" select  distinct(t2.party_key)  ");
		sb.append(" from ").append(SwiftMtConst.COM_SAS_JPACFG_AML_CATALOG).append(".").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_ENTITY_WATCH_LIST_DIM_CHNG t1,   ");
		sb.append(SwiftMtConst.COM_SAS_JPACFG_AML_CATALOG).append(".").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_PARTY_DIM_WLF t2  ");
		sb.append(" where t1.match_code_entity_name = t2.match_code_party_name ");
		sb.append(" and t1.change_current_ind = 'Y' and t1.delete_ind = 'N' and t1.exclude_Ind = 'N'   ");
		sb.append(" and t2.change_current_ind = 'Y'   ");
		sb.append(" union ");
		sb.append(" select  distinct(t2.party_key)  ");
		sb.append(" from ").append(SwiftMtConst.COM_SAS_JPACFG_AML_CATALOG).append(".").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_ENTITY_WATCH_LIST_DIM_CHNG t1,   ");
		sb.append(SwiftMtConst.COM_SAS_JPACFG_AML_CATALOG).append(".").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_PARTY_DIM_WLF t2  ");
		sb.append(" where t1.match_code_entity_name = t2.match_code_party_name_eng ");
		sb.append("  and t1.change_current_ind = 'Y' and t1.delete_ind = 'N' and t1.exclude_Ind = 'N'   ");
		sb.append(" and t2.change_current_ind = 'Y'   ");
		sb.append(" union ");
		sb.append(" select  distinct(t2.party_key)  ");
		sb.append(" from ").append(SwiftMtConst.COM_SAS_JPACFG_AML_CATALOG).append(".").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_ENTITY_WATCH_LIST_DIM_CHNG t1,   ");
		sb.append(SwiftMtConst.COM_SAS_JPACFG_AML_CATALOG).append(".").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_PARTY_DIM_WLF t2  ");
		sb.append(" where t1.identification_id = t2.party_identification_id ");
		sb.append(" and t1.change_current_ind = 'Y' and t1.delete_ind = 'N' and t1.exclude_Ind = 'N'   ");
		sb.append(" and t2.change_current_ind = 'Y'  ");
		HibernateQuery query = (HibernateQuery) entityManager.createNativeQuery(sb.toString());
		int result = query.executeUpdate();
		return result;

	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FscPartyDim> queryCandidatePartyKey(String externalPartyInd, List<Long> partyKeyList) {

		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT party_key, primary_branch_number, party_number , party_type_code ,party_name_compress, party_name_eng_compress  ");
		sb.append(" ,party_identification_id , party_date_of_birth, match_code_party_name , match_code_party_name_eng  ");
		sb.append(" ,citizenship_country_code  ");
		sb.append("  FROM ").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_PARTY_DIM_WLF " );	
		sb.append("  WHERE change_Current_Ind ='Y' AND external_party_ind = '").append(externalPartyInd).append("'  ");
		sb.append("  AND party_key in ( ");
		for(int i=0;i<partyKeyList.size();i++){
			sb.append(partyKeyList.get(i));
			if(i<partyKeyList.size()-1){
				sb.append(",");
			}
		}
		sb.append(")");
		HibernateQuery query = (HibernateQuery) entityManager.createNativeQuery(sb.toString());
		((HibernateQuery)query).getHibernateQuery().setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> tmpList = query.getResultList();
		List<FscPartyDim> returnList = new ArrayList<FscPartyDim>();
		for(Map<String, Object> obj : tmpList){
			FscPartyDim bean = new FscPartyDim();
			FscPartyDimPK fscPartyDimPK = new FscPartyDimPK();
			BigDecimal party_key_Decimail = (BigDecimal) (obj.get("party_key")==null? 0: obj.get("party_key"));
			long party_key =  party_key_Decimail.longValue();
			String primary_branch_number = obj.get("primary_branch_number")==null? null: obj.get("primary_branch_number").toString();
			String party_number = obj.get("party_number")==null? null: obj.get("party_number").toString();
			String party_type_code = obj.get("party_type_code")==null? null: obj.get("party_type_code").toString();
			String party_name = obj.get("party_name_compress")==null? null: obj.get("party_name_compress").toString();
			String party_name_eng = obj.get("party_name_eng_compress")==null? null: obj.get("party_name_eng_compress").toString();
			String party_identification_id = obj.get("party_identification_id")==null? null: obj.get("party_identification_id").toString();
			Timestamp party_date_of_birth = (Timestamp) (obj.get("party_date_of_birth")==null ? null: obj.get("party_date_of_birth"));
			String match_code_party_name = obj.get("match_code_party_name")==null? null: obj.get("match_code_party_name").toString();
		    String match_code_party_name_eng = obj.get("match_code_party_name_eng")==null? null: obj.get("match_code_party_name_eng").toString();
		    String citizenship_country_code = obj.get("citizenship_country_code")==null? null: obj.get("citizenship_country_code").toString();
		    
		    fscPartyDimPK.setPartyKey(party_key);
		    bean.setId(fscPartyDimPK);
		    bean.setPrimaryBranchNumber(primary_branch_number);
		    bean.setPartyNumber(party_number);
		    bean.setPartyTypeCode(party_type_code);
		    bean.setPartyName(party_name);
		    bean.setPartyNameEng(party_name_eng);
		    bean.setPartyIdentificationId(party_identification_id);
		    bean.setCitizenshipCountryCode(citizenship_country_code);
		    bean.setPartyDateOfBirth(party_date_of_birth);
		    bean.setMatchCodePartyName(match_code_party_name);
		    bean.setMatchCodePartyNameEng(match_code_party_name_eng);
		    
			returnList.add(bean);
		}
		return returnList;
	}
	
	
	
}
