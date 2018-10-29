package com.sas.amlCheck.initCfg;
/*
import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
*/
//@Configuration
//@EnableTransactionManagement
//@EnableAutoConfiguration
//@EnableJpaRepositories(
//       entityManagerFactoryRef = "entityManagerFactory",
//      transactionManagerRef = "transactionManager",
//        basePackages = {"com.sas.db.wlf.dao"})
//@PropertySource("classpath:jpaCfg.properties")
//public class NcScJpaCfg {

	
	/*
	 * Here you can specify any provider specific properties.
	 */
//	@Value("${spring.datasource.wlf.jndi-name}")
//	@Value("${spring.datasource.aml.jndi-name}")
//	String wlfJndiName;
/*	
	@Value("${spring.jpa.properties.hibernate.dialect}")
	String dialect;
	@Value("${spring.jpa.properties.hibernate.default_catalog}")
	String defaultCatalog;
	@Value("${spring.jpa.properties.hibernate.default_schema}")
	String defaultSchema;
	@Value("${spring.jpa.hibernate.ddl-auto}")
	String hbm2ddlAuto;
	@Value("${spring.jpa.show-sql}")
	String showSql;
	
	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	String batchSize;
	@Value("${spring.jpa.properties.hibernate.cache.use_second_level_cache}")
	String secondLevelCache;
	@Value("${spring.jpa.properties.hibernate.order_inserts}")
	String orderInsert;
	@Value("${spring.jpa.properties.hibernate.order_updates}")
	String orderUpdates;

	public Properties jpaProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", dialect);
		properties.put("hibernate.default_catalog", defaultCatalog);
		properties.put("hibernate.default_schema", defaultSchema);
		properties.put("hibernate.hbm2ddl.auto", hbm2ddlAuto);
		properties.put("hibernate.show_sql", showSql);
		properties.put("hibernate.format_sql", false);
		
		properties.put("hibernate.jdbc.batch_size", batchSize);
		properties.put("hibernate.cache.use_second_level_cache", secondLevelCache);
		properties.put("hibernate.order_inserts", orderInsert);
		properties.put("hibernate.order_updates", orderUpdates);
		return properties;
	}
*/	
/*	
	@Primary
	@Bean(name = "dataSource")
	public DataSource wlfDataSource() {
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
        DataSource dataSource = dataSourceLookup.getDataSource(wlfJndiName);
        return dataSource;
	}

	@Primary
	@Bean("entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		factory.setJpaProperties(jpaProperties());
		factory.setPackagesToScan("com.sas.db.wlf.orm");
		factory.setDataSource(wlfDataSource());
		return factory;
	}
	
	@Primary
	@Bean("transactionManager")
	public PlatformTransactionManager transactionManager()
			throws NamingException {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return txManager;
	}
*/	
	
//}
