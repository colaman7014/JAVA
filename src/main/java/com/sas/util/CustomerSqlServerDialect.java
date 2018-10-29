package com.sas.util;

import java.sql.Types;

import org.hibernate.dialect.SQLServer2012Dialect;

public class CustomerSqlServerDialect extends SQLServer2012Dialect{

	public CustomerSqlServerDialect() {
		super();
		registerHibernateType(Types.NVARCHAR, "string");
		registerHibernateType(Types.NCHAR, "string");
	}

	
}
