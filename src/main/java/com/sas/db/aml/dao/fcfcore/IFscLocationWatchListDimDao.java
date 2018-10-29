package com.sas.db.aml.dao.fcfcore;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.fcfcore.FscLocationWatchListDim;
/**
 * NCSC-FSC_LOCATION_WATCH_LIST_DIM(主要黑名單國家檔) DAO 方法
 * @author SAS
 *
 */
public interface IFscLocationWatchListDimDao extends CrudRepository<FscLocationWatchListDim, Long>, IFscLocationWatchListDimDaoCustom{
	List<FscLocationWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndMatchCodeCountryName(String changeCurrentInd, String deleteInd, String excludeInd, String matchCodeCountryName);
	List<FscLocationWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndCountryNameIn(String changeCurrentInd, String deleteInd, String excludeInd, List<String> countryNameList);
	List<FscLocationWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndMatchCodeCountryNameIn(String changeCurrentInd, String deleteInd, String excludeInd, List<String> matchCodeCountryNameList);
	@Query(value="select f from FscLocationWatchListDim f where f.countryName = ?1 and f.changeCurrentInd = ?2")
	List<FscLocationWatchListDim> findByCountryNameAndChangeCurrentInd(String countryName, String changeCurrentInd);
	
	List<FscLocationWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndCountryCode(String changeCurrentInd, String deleteInd, String excludeInd, String countryCode);
}
