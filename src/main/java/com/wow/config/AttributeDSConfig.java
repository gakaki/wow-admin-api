package com.wow.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages ="com.wow.attribute.mapper",sqlSessionFactoryRef="attributeSqlSessionFactory")
public class AttributeDSConfig {

    @Bean(name = "attributeDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.attribute")
    public DataSource attributeDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "attributeSqlSessionFactory")
    @Autowired
    @Qualifier("attributeDataSource")
    public SqlSessionFactory attributeSqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/attribute/*Mapper.xml"));
        return bean.getObject();
    }

    @Bean(name = "attributeTransactionManager")
    @Autowired
    @Qualifier("attributeDataSource")
    public DataSourceTransactionManager attributeTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "attributeSqlSessionTemplate")
    @Autowired
    @Qualifier("attributeSqlSessionFactory")
    public SqlSessionTemplate attributeSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}