package com.sas.db.aml.dao.fcfcore;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.fcfcore.FscLocationNcWatchListDim;
/**
 * NCSC-FSC_LOCATION_WATCH_LIST_DIM(主要黑名單國家檔) DAO 方法
 * @author SAS
 *
 */
public interface IFscLocationNcWatchListDimDao extends CrudRepository<FscLocationNcWatchListDim, Long>, IFscLocationNcWatchListDimDaoCustom{
	List<FscLocationNcWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndMatchCodeCountryName(String changeCurrentInd, String deleteInd, String excludeInd, String matchCodeCountryName);
	List<FscLocationNcWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndCountryNameIn(String changeCurrentInd, String deleteInd, String excludeInd, List<String> countryNameList);
	List<FscLocationNcWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndMatchCodeCountryNameIn(String changeCurrentInd, String deleteInd, String excludeInd, List<String> matchCodeCountryNameList);
	@Query(value="select f from FscLocationNcWatchListDim f where f.countryName = ?1 and f.changeCurrentInd = ?2")
	List<FscLocationNcWatchListDim> findByCountryNameAndChangeCurrentInd(String countryName, String changeCurrentInd);
	
	List<FscLocationNcWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndCountryCode(String changeCurrentInd, String deleteInd, String excludeInd, String countryCode);
	
	@Query(value="select f from FscLocationNcWatchListDim f where f.changeCurrentInd = ?1 and f.deleteInd = ?2 and f.excludeInd = ?3 and (countryCode = ?4 or countryCode3 = ?4)")
	List<FscLocationNcWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndCountryCodeOrCountryCode3(String changeCurrentInd, String deleteInd, String excludeInd, String countryCode);

}
