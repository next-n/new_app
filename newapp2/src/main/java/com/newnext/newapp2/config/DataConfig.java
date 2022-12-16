package com.newnext.newapp2.config;

import java.util.Collections;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.orm.jpa.vendor.Database;

import com.newnext.newapp2.entities.Authority;
import com.newnext.newapp2.entities.User;
import com.newnext.newapp2.service.interfaces.AuthorityService;
import com.newnext.newapp2.service.interfaces.UserService;

@Configuration
public class DataConfig {
	@Bean
	public DataSource dataSource() {
		DataSourceBuilder<?> ds = DataSourceBuilder.create();
		ds.username("postgres");
		ds.password("password");
		ds.url("jdbc:postgresql://localhost:5432/newapp2");
		ds.driverClassName("org.postgresql.Driver");
		return ds.build();
	}
	@Bean
	public JpaProperties jpaProperties() {
		JpaProperties jpa = new JpaProperties();
		jpa.setGenerateDdl(true);
		jpa.setDatabase(Database.POSTGRESQL);
		jpa.setShowSql(false);
		return jpa;
	}
	@Bean
	public HibernateProperties hibernateProperties() {
		HibernateProperties h = new HibernateProperties();
		h.setDdlAuto("update");
		return h;
	}

	}


