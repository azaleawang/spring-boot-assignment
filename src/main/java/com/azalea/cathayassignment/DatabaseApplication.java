package com.azalea.cathayassignment;

import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
@Log
public class DatabaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(DatabaseApplication.class, args);
    }

//    public void run(final String... args) {
//        log.info("DataSource:" + dataSource.toString());
//        final JdbcTemplate restTemplate = new JdbcTemplate(dataSource);
//        restTemplate.execute("select 1");
//    }
}
