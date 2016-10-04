package com.automate.config;

import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.automate.model.User;

@Configuration
@EnableTransactionManagement
public class DBConfig {
	
	@Bean
	public HibernateTemplate hibernateTemplate(){
		return new HibernateTemplate(sessionFactory());
	}
	
	@Bean
	public DataSource getDataSource(){
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/automate?autoReconnect=true&useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}
	
	@Bean
	public SessionFactory sessionFactory(){
		return new LocalSessionFactoryBuilder(getDataSource()).addAnnotatedClass(User.class).buildSessionFactory();
	}
	
	@Bean
	public HibernateTransactionManager hibTransMan(){
		return new HibernateTransactionManager(sessionFactory());
	}

}

