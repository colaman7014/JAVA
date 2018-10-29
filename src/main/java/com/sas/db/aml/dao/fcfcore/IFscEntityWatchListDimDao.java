package com.sas.db.aml.dao.fcfcore;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.fcfcore.FscEntityWatchListDim;

/**
 * NCSC-FSC_ENTITY_WATCH_LIST_DIM(主要黑名單) DAO 方法
 * @author SAS
 *
 */
public interface IFscEntityWatchListDimDao extends CrudRepository<FscEntityWatchListDim, Long>, IFscEntityWatchListDimDaoCustom{	
	List<FscEntityWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndEntityName(String changeCurrentInd, String deleteInd, String excludeInd, String entityNameList);
	List<FscEntityWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndEntityNameIn(String changeCurrentInd, String deleteInd, String excludeInd, List<String> entityNameList);
//	List<FscEntityWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndInAndEntityNameIn(String changeCurrentInd, String deleteInd, String excludeInd, List<String> entityNameList);
	
	List<FscEntityWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndMatchCodeEntityNameIn(String changeCurrentInd, String deleteInd, String excludeInd, List<String> matchCodeEntityName);
	
	List<FscEntityWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndIdentificationId(String changeCurrentInd, String deleteInd, String excludeInd, String identificationId);
	List<FscEntityWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndIdentificationId(String changeCurrentInd, String deleteInd, String excludeInd,List<String> typeDesc, String identificationId);
	
	@Query(value="select f from FscEntityWatchListDim f where f.entityName = ?1 and f.changeCurrentInd = ?2")
	List<FscEntityWatchListDim> findByEntityNameAndChangeCurrentInd(String entityName, String changeCurrentInd);
	
	@Query(value="select f from FscEntityWatchListDim f where f.matchCodeEntityName = ?1 and f.changeCurrentInd = ?2")
	List<FscEntityWatchListDim> findByMatchCodeEntityNameAndChangeCurrentInd(String matchCodeEntityName, String changeCurrentInd);
	
	//Terry 新增
	List<FscEntityWatchListDim> findByChangeCurrentIndAndEntityNameIn(String changeCurrentInd, List<String> entityNameList);
	
	//Terry  新增
	List<FscEntityWatchListDim> findByChangeCurrentIndAndMatchCodeEntityNameIn(String changeCurrentInd, List<String> matchCodeEntityName);
	
	//Terry 新增 Batch Job fullParthAndChangeWatchList 使用
	List<FscEntityWatchListDim> findByChangeCurrentIndAndEntityNameInAndChangeBeginDateGreaterThanEqual(String changeCurrentInd, List<String> entityNameList, Timestamp changeBeginDate);
		
	//Terry 新增 Batch Job fullParthAndChangeWatchList 使用
    List<FscEntityWatchListDim> findByChangeCurrentIndAndMatchCodeEntityNameInAndChangeBeginDateGreaterThanEqual(String changeCurrentInd, List<String> matchCodeEntityNameList, Timestamp changeBeginDate);
}
