package com.sas.amlCheck.initCfg;

import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "amlEntityManagerFactory",
        transactionManagerRef = "amlTransactionManager",
        basePackages = {"com.sas.db.aml.dao", "com.sas.db.wlf.dao", "com.sas.aml.retry.event.queue.repository"})
@PropertySource("classpath:jpaCfg.properties")
public class AmlJpaCfg {
	/*
	 * Here you can specify any provider specific properties.
	 */
	@Value("${spring.datasource.aml.jndi-name}")
	String amlJndiName;
	@Value("${spring.jpa.properties.hibernate.dialect}")
	String dialect;
	@Value("${spring.jpa.properties.hibernate.aml_catalog}")
	String defaultCatalog;
	@Value("${spring.jpa.properties.hibernate.fcfcore_schema}")
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
	
	@Bean(name = "amlDataSource", destroyMethod="")
	public DataSource amlDataSource() {
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
        DataSource dataSource = dataSourceLookup.getDataSource(amlJndiName);
        return dataSource;
	}

	@Bean("amlEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean amlEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		factory.setJpaProperties(jpaProperties());
		factory.setPackagesToScan("com.sas.db.aml.orm", "com.sas.db.wlf.orm", "com.sas.aml.retry.event.queue.bean");
		factory.setDataSource(amlDataSource());
		return factory;
	}
	
	@Bean("amlTransactionManager")
	public PlatformTransactionManager amlTransactionManager()
			throws NamingException {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(amlEntityManagerFactory().getObject());
		return txManager;
	}
}
