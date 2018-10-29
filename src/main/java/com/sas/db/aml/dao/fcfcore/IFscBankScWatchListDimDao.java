package com.sas.db.aml.dao.fcfcore;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.fcfcore.FscBankScWatchListDim;

/**
 * FCFCORE-FSC_BANK_WATCH_LIST_DIM(主要黑名單銀行檔) DAO 方法
 * @author SAS
 *
 */
public interface IFscBankScWatchListDimDao extends CrudRepository<FscBankScWatchListDim, Long>, IFscBankScWatchListDimDaoCustom{
	List<FscBankScWatchListDim> findByChangeCurrentIndAndBccCode(String changeCurrentInd, String bccCode);

	List<FscBankScWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndBccCode(String changeCurrentInd, String deleteInd, String excludeInd, String bccCode);
	List<FscBankScWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndBankName(String changeCurrentInd, String deleteInd, String excludeInd, String bankName);
	List<FscBankScWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndBankNameIn(String changeCurrentInd, String deleteInd, String excludeInd, List<String> bankName);
	List<FscBankScWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndMatchCodeBankNameIn(String changeCurrentInd, String deleteInd, String excludeInd, List<String> matchCodeBankName);
	
	@Query(value="select f from FscBankScWatchListDim f where f.bccCode = ?1 and f.changeCurrentInd = ?2")
	List<FscBankScWatchListDim> findByBccCodeAndChangeCurrentInd(String bccCode, String changeCurrentInd);
}

