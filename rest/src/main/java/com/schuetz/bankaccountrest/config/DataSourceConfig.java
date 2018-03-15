package com.schuetz.bankaccountrest.config;

import oracle.jdbc.pool.OracleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
public class DataSourceConfig {
    @Bean
    public DataSource getOracleDataSource() throws SQLException {
        OracleDataSource dataSource = new OracleDataSource();
        dataSource.setUser("5EHIF20178_19");
        dataSource.setPassword("lenovo");
        dataSource.setURL("jdbc:oracle:thin:@oracle.spengergasse.at:1521:orcl");
        dataSource.setImplicitCachingEnabled(true);
        dataSource.setFastConnectionFailoverEnabled(true);
        return dataSource;
    }
}
