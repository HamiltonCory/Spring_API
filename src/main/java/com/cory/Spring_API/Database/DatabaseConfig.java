package com.cory.Spring_API.Database;

import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.*;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

//@ComponentScan({ "com.cory.Spring_API" })
@Configuration
//@EnableJpaRepositories(basePackages = "org.springframework.data.jpa.repository.JpaRepository")
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        return (DataSource) new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("Account Transactions")
                .addScripts("schema.sql")
                .build();
    }

//    @Bean
//    public JdbcTemplate getJdbcTemplate() {
//        return new JdbcTemplate(dataSource);
//    }
}

