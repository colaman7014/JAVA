package com.sas.db.aml.dao.fcfcore;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.jpa.HibernateQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sas.constraint.SwiftMtConst;
import com.sas.db.aml.orm.fcfcore.FscPartyDimWlf;
import com.sas.db.aml.orm.fcfcore.FscPartyDimWlfPK;

public class IFscPartyDimWlfDaoImpl implements IFscPartyDimWlfDaoCustom{
	private static final Logger logger = LoggerFactory.getLogger(IFscPartyDimWlfDaoImpl.class);
	
	@PersistenceContext(unitName = "amlEntityManagerFactory")
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FscPartyDimWlf> queryBatchNameCheck(String externalPartyInd, int indexFrom , int indexTo) {

		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT party_key, primary_branch_number, party_number , party_type_desc ,party_name, party_name_eng ");
		sb.append(" ,party_identification_id , party_date_of_birth, match_code_party_name , match_code_party_name_eng ");
		sb.append(" ,citizenship_country_code ");
		sb.append("  FROM ( ");
		sb.append("     SELECT ROW_NUMBER() OVER(ORDER BY party_key) AS Row,  * ");
		sb.append("     FROM ").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_PARTY_DIM_WLF " );
		sb.append("     WHERE change_Current_Ind ='Y' AND external_party_ind = '").append(externalPartyInd).append("'  ");
		sb.append("     AND (party_status_desc != 'D' OR party_status_desc is null)  ");
		sb.append("   ) AS T1 ");
		sb.append(" WHERE T1.Row BETWEEN  ").append( indexFrom ).append(" AND ").append( indexTo );

		HibernateQuery query = (HibernateQuery) entityManager.createNativeQuery(sb.toString());
		((HibernateQuery)query).getHibernateQuery().setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> tmpList = query.getResultList();
		List<FscPartyDimWlf> returnList = new ArrayList<FscPartyDimWlf>();
		for(Map<String, Object> obj : tmpList){
			FscPartyDimWlf bean = new FscPartyDimWlf();
			FscPartyDimWlfPK fscPartyDimWlfPK = new FscPartyDimWlfPK();
			BigDecimal party_key_Decimail = (BigDecimal) (obj.get("party_key")==null? 0: obj.get("party_key"));
			long party_key =  party_key_Decimail.longValue();
			String primary_branch_number = obj.get("primary_branch_number")==null? null: obj.get("primary_branch_number").toString();
			String party_number = obj.get("party_number")==null? null: obj.get("party_number").toString();
			String party_type_desc = obj.get("party_type_desc")==null? null: obj.get("party_type_desc").toString();
			String party_name = obj.get("party_name")==null? null: obj.get("party_name").toString();
			String party_name_eng = obj.get("party_name_eng")==null? null: obj.get("party_name_eng").toString();
			String party_identification_id = obj.get("party_identification_id")==null? null: obj.get("party_identification_id").toString();
			String citizenship_country_code = obj.get("citizenship_country_code")==null? null: obj.get("citizenship_country_code").toString();
			Timestamp party_date_of_birth = (Timestamp) (obj.get("party_date_of_birth")==null ? null: obj.get("party_date_of_birth"));
			String match_code_party_name = obj.get("match_code_party_name")==null? null: obj.get("match_code_party_name").toString();
		    String match_code_party_name_eng = obj.get("match_code_party_name_eng")==null? null: obj.get("match_code_party_name_eng").toString();

		    fscPartyDimWlfPK.setPartyKey(party_key);
		    bean.setId(fscPartyDimWlfPK);
		    bean.setPrimaryBranchNumber(primary_branch_number);
		    bean.setPartyName(party_number);
		    bean.setPartyTypeDesc(party_type_desc);
		    bean.setPartyName(party_name);
		    bean.setPartyNameEng(party_name_eng);
		    bean.setPartyIdentificationId(party_identification_id);
		    bean.setPartyDateOfBirth(party_date_of_birth);
		    bean.setMatchCodePartyName(match_code_party_name);
		    bean.setMatchCodePartyNameEng(match_code_party_name_eng);
		    bean.setCitizenshipCountryCode(citizenship_country_code);
		    
			returnList.add(bean);
		}
		return returnList;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FscPartyDimWlf> queryBatchNameCheckWlf(String externalPartyInd, int handleCount) {

		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT top ").append( handleCount );
		sb.append(" party_key, primary_branch_number, party_number , party_type_code ,party_name, party_name_eng  ");
		sb.append(" ,party_identification_id , party_date_of_birth, match_code_party_name , match_code_party_name_eng  ");
		sb.append(" ,citizenship_country_code  ");
		sb.append("  FROM ").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_PARTY_DIM_WLF " );	
		sb.append("  WHERE change_Current_Ind ='Y' AND external_party_ind = '").append(externalPartyInd).append("'  ");
		sb.append("  AND (wlf_flg != 'Y' OR wlf_flg is null) ");	
		HibernateQuery query = (HibernateQuery) entityManager.createNativeQuery(sb.toString());
		((HibernateQuery)query).getHibernateQuery().setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> tmpList = query.getResultList();
		List<FscPartyDimWlf> returnList = new ArrayList<FscPartyDimWlf>();
		for(Map<String, Object> obj : tmpList){
			FscPartyDimWlf bean = new FscPartyDimWlf();
			FscPartyDimWlfPK fscPartyDimWlfPK = new FscPartyDimWlfPK();
			BigDecimal party_key_Decimail = (BigDecimal) (obj.get("party_key")==null? 0: obj.get("party_key"));
			long party_key =  party_key_Decimail.longValue();
			String primary_branch_number = obj.get("primary_branch_number")==null? null: obj.get("primary_branch_number").toString();
			String party_number = obj.get("party_number")==null? null: obj.get("party_number").toString();
			String party_type_code = obj.get("party_type_code")==null? null: obj.get("party_type_code").toString();
			String party_name = obj.get("party_name")==null? null: obj.get("party_name").toString();
			String party_name_eng = obj.get("party_name_eng")==null? null: obj.get("party_name_eng").toString();
			String party_identification_id = obj.get("party_identification_id")==null? null: obj.get("party_identification_id").toString();
			Timestamp party_date_of_birth = (Timestamp) (obj.get("party_date_of_birth")==null ? null: obj.get("party_date_of_birth"));
			String match_code_party_name = obj.get("match_code_party_name")==null? null: obj.get("match_code_party_name").toString();
		    String match_code_party_name_eng = obj.get("match_code_party_name_eng")==null? null: obj.get("match_code_party_name_eng").toString();
		    String citizenship_country_code = obj.get("citizenship_country_code")==null? null: obj.get("citizenship_country_code").toString();
		    
		    fscPartyDimWlfPK.setPartyKey(party_key);
		    bean.setId(fscPartyDimWlfPK);
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
	
	
	

	
	@SuppressWarnings("unchecked")
	@Override
	public int countBatchNameCheck(String externalPartyInd) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT COUNT(party_key) AS party_count ");
		sb.append("     FROM ").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_PARTY_DIM_WLF " );
		sb.append(" WHERE change_Current_Ind ='Y'  AND external_party_ind = '").append(externalPartyInd).append("'  ");
		sb.append(" AND (wlf_flg != 'Y' OR wlf_flg is null) ");

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
	public void updateBatchNameCheck(List<Long> partyKeyList) {
		StringBuffer sb = new StringBuffer();
		sb.append(" update ").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_PARTY_DIM_WLF " );
		sb.append(" set wlf_flg='Y' ");
		sb.append(" where party_key in ( ");
		for(int i=0;i<partyKeyList.size();i++){
			sb.append(partyKeyList.get(i));
			if(i!=partyKeyList.size()-1){
				sb.append(",");
			}
		}
		sb.append(")");
		String test = sb.toString();
		HibernateQuery query = (HibernateQuery) entityManager.createNativeQuery(sb.toString());		
		((HibernateQuery)query).getHibernateQuery().setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> tmpList = query.getResultList();
		
	
	}	
	
}
