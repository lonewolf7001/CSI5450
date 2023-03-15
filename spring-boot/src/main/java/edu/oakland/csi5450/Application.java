package edu.oakland.csi5450;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
public class Application
{
	@Value("${spring.datasource.url}")
	String url;
	
	@Value("${spring.datasource.username}")
	String username;
	
	@Value("${spring.datasource.password}")
	String password;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}
	@Bean
    public DataSource datasource() {
        return DataSourceBuilder.create()
          .driverClassName("org.postgresql.Driver")
          .url(url)
          .username(username)
          .password(password)
          .build(); 
    }
}
