package com.sas.db.aml.dao.fcfcore;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.fcfcore.FscBankWatchListDim;

/**
 * FCFCORE-FSC_BANK_WATCH_LIST_DIM(主要黑名單銀行檔) DAO 方法
 * @author SAS
 *
 */
public interface IFscBankWatchListDimDao extends CrudRepository<FscBankWatchListDim, Long>, IFscBankWatchListDimDaoCustom{
	List<FscBankWatchListDim> findByChangeCurrentIndAndBccCode(String changeCurrentInd, String bccCode);

	List<FscBankWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndBccCode(String changeCurrentInd, String deleteInd, String excludeInd, String bccCode);
	List<FscBankWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndBankName(String changeCurrentInd, String deleteInd, String excludeInd, String bankName);
	List<FscBankWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndBankNameIn(String changeCurrentInd, String deleteInd, String excludeInd, List<String> bankName);
	List<FscBankWatchListDim> findByChangeCurrentIndAndDeleteIndAndExcludeIndAndMatchCodeBankNameIn(String changeCurrentInd, String deleteInd, String excludeInd, List<String> matchCodeBankName);
	
	@Query(value="select f from FscBankWatchListDim f where f.bccCode = ?1 and f.changeCurrentInd = ?2")
	List<FscBankWatchListDim> findByBccCodeAndChangeCurrentInd(String bccCode, String changeCurrentInd);
}

