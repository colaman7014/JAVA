package com.sas.db.aml.dao.fcfcore;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.fcfcore.FscEntityWatchListDim;
import com.sas.db.aml.orm.fcfcore.FscEntityWatchListDimChng;

/**
 * NCSC-FSC_ENTITY_WATCH_LIST_DIM(主要黑名單) DAO 方法
 * @author SAS
 *
 */
public interface IFscEntityWatchListDimChngDao extends CrudRepository<FscEntityWatchListDimChng, Long>, IFscEntityWatchListDimChngDaoCustom{	
	List<FscEntityWatchListDimChng> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndEntityName(String changeCurrentInd, String deleteInd, String excludeInd, String entityNameList);
	List<FscEntityWatchListDimChng> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndEntityNameIn(String changeCurrentInd, String deleteInd, String excludeInd, List<String> entityNameList);
//	List<FscEntityWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndInAndEntityNameIn(String changeCurrentInd, String deleteInd, String excludeInd, List<String> entityNameList);
	
	List<FscEntityWatchListDimChng> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndMatchCodeEntityNameIn(String changeCurrentInd, String deleteInd, String excludeInd, List<String> matchCodeEntityName);
	
	List<FscEntityWatchListDimChng> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndIdentificationId(String changeCurrentInd, String deleteInd, String excludeInd, String identificationId);
	List<FscEntityWatchListDimChng> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndIdentificationId(String changeCurrentInd, String deleteInd, String excludeInd,List<String> typeDesc, String identificationId);
	
	@Query(value="select f from FscEntityWatchListDimChng f where f.entityName = ?1 and f.changeCurrentInd = ?2")
	List<FscEntityWatchListDimChng> findByEntityNameAndChangeCurrentInd(String entityName, String changeCurrentInd);
	
	@Query(value="select f from FscEntityWatchListDimChng f where f.matchCodeEntityName = ?1 and f.changeCurrentInd = ?2")
	List<FscEntityWatchListDimChng> findByMatchCodeEntityNameAndChangeCurrentInd(String matchCodeEntityName, String changeCurrentInd);
	
	//Terry 新增
	List<FscEntityWatchListDimChng> findByChangeCurrentIndAndEntityNameIn(String changeCurrentInd, List<String> entityNameList);
	
	//Terry  新增
	List<FscEntityWatchListDimChng> findByChangeCurrentIndAndMatchCodeEntityNameIn(String changeCurrentInd, List<String> matchCodeEntityName);
	
	//Terry 新增 Batch Job fullParthAndChangeWatchList 使用
	List<FscEntityWatchListDimChng> findByChangeCurrentIndAndEntityNameInAndChangeBeginDateGreaterThanEqual(String changeCurrentInd, List<String> entityNameList, Timestamp changeBeginDate);
		
	//Terry 新增 Batch Job fullParthAndChangeWatchList 使用
    List<FscEntityWatchListDimChng> findByChangeCurrentIndAndMatchCodeEntityNameInAndChangeBeginDateGreaterThanEqual(String changeCurrentInd, List<String> matchCodeEntityNameList, Timestamp changeBeginDate);
}
