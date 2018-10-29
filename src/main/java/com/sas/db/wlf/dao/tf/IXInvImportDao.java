package com.sas.db.wlf.dao.tf;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.tf.XInvImport;
import com.sas.db.wlf.orm.tf.XInvImportPK;

/**
 * NCSC-X_BL_EXPORT(貿易融資Invoice Import) DAO
 * @author SAS
 *
 */
public interface IXInvImportDao extends CrudRepository<XInvImport, XInvImportPK>, IXInvImportDaoCustom{
	/**
	 * 透過uniqueKey尋找最近一筆進口發票相關資料
	 * @param uniqueKey
	 * @return
	 */
	public List<XInvImport> findByIdUniqueKeyOrderByCreateDttmDesc(String uniqueKey);
	
	public List<XInvImport> findByIdFileKeyAndScrNoAndTypeAndLCNoAndIbNoAndInvoiceNo(
			 String fileKey, String scrNo, String type, String lCNo, String ibNo, String invoiceNo);

	
	public List<XInvImport> findTop20ByHkHsCodeAndIdUniqueKeyNotAndInvoiceNoNotAndCreateDttmLessThanEqualOrderByCreateDttmDesc(
			String hkHsCode, String uniqueKey, String invoiceNo, Timestamp createDttm);
	
	public List<XInvImport> findTop20ByCategoryOfGoodsAndIdUniqueKeyNotAndInvoiceNoNotAndCreateDttmLessThanEqualOrderByCreateDttmDesc(
			String categoryOfGoods, String uniqueKey, String invoiceNo, Timestamp createDttm);
}
