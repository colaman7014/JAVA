package com.sas.db.aml.dao.fcfcore;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.fcfcore.FscLocationScWatchListDim;
/**
 * NCSC-FSC_LOCATION_WATCH_LIST_DIM(主要黑名單國家檔) DAO 方法
 * @author SAS
 *
 */
public interface IFscLocationScWatchListDimDao extends CrudRepository<FscLocationScWatchListDim, Long>, IFscLocationScWatchListDimDaoCustom{
	List<FscLocationScWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndMatchCodeCountryName(String changeCurrentInd, String deleteInd, String excludeInd, String matchCodeCountryName);
	List<FscLocationScWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndCountryNameIn(String changeCurrentInd, String deleteInd, String excludeInd, List<String> countryNameList);
	List<FscLocationScWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndMatchCodeCountryNameIn(String changeCurrentInd, String deleteInd, String excludeInd, List<String> matchCodeCountryNameList);

	@Query(value="select f from FscLocationScWatchListDim f where f.countryName = ?1 and f.changeCurrentInd = ?2")
	List<FscLocationScWatchListDim> findByCountryNameAndChangeCurrentInd(String countryName, String changeCurrentInd);
	
	List<FscLocationScWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndCountryCode(String changeCurrentInd, String deleteInd, String excludeInd, String countryCode);
}
