package com.sas.db.aml.dao.fcfcore;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.sas.db.aml.orm.fcfcore.XWatchlistCompressString;

public interface IXWatchlistCompressStringDao extends CrudRepository<XWatchlistCompressString, Integer> {
	
//	@Query("select s.id.compressString from XWatchlistCompressString s where s.id.compressType = ?1")
	List<XWatchlistCompressString> findCompressStringListByCompressType(String compressType);
	List<XWatchlistCompressString> findAll();
}