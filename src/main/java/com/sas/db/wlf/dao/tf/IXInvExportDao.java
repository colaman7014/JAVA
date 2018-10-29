package com.sas.db.wlf.dao.tf;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.tf.XInvExport;
import com.sas.db.wlf.orm.tf.XInvExportPK;

/**
 * NCSC-X_BL_EXPORT(貿易融資Invoice Export) DAO
 * @author SAS
 *
 */
public interface IXInvExportDao extends CrudRepository<XInvExport, XInvExportPK>, IXInvExportDaoCustom{
	/**
	 * 透過uniqueKey尋找最近一筆出口發票相關資料
	 * @param uniqueKey
	 * @return
	 */
	public List<XInvExport> findByIdUniqueKeyOrderByCreateDttmDesc(String uniqueKey);
	
	/**
	 * 查詢同一張發票底下所有ITEM
	 * @param uniqueKey
	 * @param seq
	 * @param fileKey
	 * @param scrNo
	 * @param currnecy
	 * @param ourRefNo
	 * @param seqNo
	 * @param invoiceNo
	 * @return
	 */
	public List<XInvExport> findByIdFileKeyAndScrNoAndCurrnecyAndOurRefNoAndSeqNoAndInvoiceNo(
			String fileKey, String scrNo, String currnecy, String ourRefNo, String seqNo, String invoiceNo);
	
	/**
	 * 查詢前20筆不在同一張發票但貨品類別相同的品項
	 * @param hkHsCode
	 * @param uniqueKey
	 * @param seq
	 * @param scrNo
	 * @param currnecy
	 * @param ourRefNo
	 * @param seqNo
	 * @param invoiceNo
	 * @return
	 */
	public List<XInvExport> findTop20ByHkHsCodeAndIdUniqueKeyNotAndInvoiceNoNotAndCreateDttmLessThanEqualOrderByCreateDttmDesc(
			String hkHsCode, String uniqueKey, String invoiceNo, Timestamp createDttm);
	
	/**
	 * 查詢前20筆不在同一張發票但香港貨物協調制度【港貨協制】編號相同的品項
	 * @param categoryOfGoods
	 * @param uniqueKey
	 * @param seq
	 * @param scrNo
	 * @param currnecy
	 * @param ourRefNo
	 * @param seqNo
	 * @param invoiceNo
	 * @return
	 */
	public List<XInvExport> findTop20ByCategoryOfGoodsAndIdUniqueKeyNotAndInvoiceNoNotAndCreateDttmLessThanEqualOrderByCreateDttmDesc(
			String categoryOfGoods, String uniqueKey, String invoiceNo, Timestamp createDttm);
}
