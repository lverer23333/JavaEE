package edu.bjtu.ee4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableCaching

@SpringBootApplication
@Configuration

@EntityScan
@EnableJpaRepositories(basePackages = "webroot.webserv",
entityManagerFactoryRef = "entityManagerFactory",
transactionManagerRef = "transactionManager")

@EnableTransactionManagement
@EnableAutoConfiguration

public class SpringMvcCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcCrudApplication.class, args);
	}

}
