package com.example.board.mybatis;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@RequiredArgsConstructor
@MapperScan("com.example.board.mappers")
public class MyBatisConfig {
    private final ApplicationContext applicationContext;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig() { return new HikariConfig(); }

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath*:/mappers/*.xml"));
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:/config/MyBatisConfig.xml"));
        try {
            SqlSessionFactory factory=sqlSessionFactoryBean.getObject();
            factory.getConfiguration().setMapUnderscoreToCamelCase(true);
            return factory; // 세션 팩토리 리턴
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
