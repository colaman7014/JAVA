package com.sas.db.aml.dao.fcfcore;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.fcfcore.FscBankNcWatchListDim;

/**
 * FCFCORE-FSC_BANK_WATCH_LIST_DIM(主要黑名單銀行檔) DAO 方法
 * @author SAS
 *
 */
public interface IFscBankNcWatchListDimDao extends CrudRepository<FscBankNcWatchListDim, Long>, IFscBankNcWatchListDimDaoCustom{
	List<FscBankNcWatchListDim> findByChangeCurrentIndAndBccCode(String changeCurrentInd, String bccCode);
	List<FscBankNcWatchListDim> findByChangeCurrentIndAndBccCodeStartingWith(String changeCurrentInd, String bccCode);
	List<FscBankNcWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndBccCode(String changeCurrentInd, String deleteInd, String excludeInd, String bccCode);
	List<FscBankNcWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndBankName(String changeCurrentInd, String deleteInd, String excludeInd, String bankName);
	List<FscBankNcWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndBankNameIn(String changeCurrentInd, String deleteInd, String excludeInd, List<String> bankName);
	List<FscBankNcWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndMatchCodeBankNameIn(String changeCurrentInd, String deleteInd, String excludeInd, List<String> matchCodeBankName);
	
	@Query(value="select f from FscBankNcWatchListDim f where f.bccCode = ?1 and f.changeCurrentInd = ?2")
	List<FscBankNcWatchListDim> findByBccCodeAndChangeCurrentInd(String bccCode, String changeCurrentInd);
}

