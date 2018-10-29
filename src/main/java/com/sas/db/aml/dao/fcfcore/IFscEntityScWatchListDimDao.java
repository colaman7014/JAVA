package com.sas.db.aml.dao.fcfcore;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.fcfcore.FscEntityScWatchListDim;
import com.sas.db.aml.orm.fcfcore.FscEntityWatchListDim;

/**
 * NCSC-FSC_ENTITY_WATCH_LIST_DIM(主要黑名單) DAO 方法
 * @author SAS
 *
 */
public interface IFscEntityScWatchListDimDao extends CrudRepository<FscEntityScWatchListDim, Long>, IFscEntityScWatchListDimDaoCustom{	
	List<FscEntityScWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndEntityName(String changeCurrentInd, String deleteInd, String excludeInd, String entityNameList);
	List<FscEntityScWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndEntityNameIn(String changeCurrentInd, String deleteInd, String excludeInd, List<String> entityNameList);
	
	List<FscEntityScWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndMatchCodeEntityNameIn(String changeCurrentInd, String deleteInd, String excludeInd, List<String> matchCodeEntityName);
	
	List<FscEntityScWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndIdentificationId(String changeCurrentInd, String deleteInd, String excludeInd, String identificationId);
	List<FscEntityScWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndIdentificationId(String changeCurrentInd, String deleteInd, String excludeInd,List<String> typeDesc, String identificationId);
	
	@Query(value="select f from FscEntityWatchListDim f where f.entityName = ?1 and f.changeCurrentInd = ?2")
	List<FscEntityScWatchListDim> findByEntityNameAndChangeCurrentInd(String entityName, String changeCurrentInd);
	
	@Query(value="select f from FscEntityWatchListDim f where f.matchCodeEntityName = ?1 and f.changeCurrentInd = ?2")
	List<FscEntityScWatchListDim> findByMatchCodeEntityNameAndChangeCurrentInd(String matchCodeEntityName, String changeCurrentInd);
	
	//Terry 新增
	List<FscEntityScWatchListDim> findByChangeCurrentIndAndEntityNameIn(String changeCurrentInd, List<String> entityNameList);
	
	//Terry  新增
	List<FscEntityScWatchListDim> findByChangeCurrentIndAndMatchCodeEntityNameIn(String changeCurrentInd, List<String> matchCodeEntityName);
	
	//Terry 新增 Batch Job fullParthAndChangeWatchList 使用
	List<FscEntityScWatchListDim> findByChangeCurrentIndAndEntityNameInAndChangeBeginDateGreaterThanEqual(String changeCurrentInd, List<String> entityNameList, Timestamp changeBeginDate);
		
	//Terry 新增 Batch Job fullParthAndChangeWatchList 使用
    List<FscEntityScWatchListDim> findByChangeCurrentIndAndMatchCodeEntityNameInAndChangeBeginDateGreaterThanEqual(String changeCurrentInd, List<String> matchCodeEntityNameList, Timestamp changeBeginDate);
}
