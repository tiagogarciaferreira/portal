package br.srv.portal.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = "br.srv.portal.model")
@ComponentScan(basePackages = {"br.srv.portal.*"})
@EnableJpaRepositories(basePackages = { "br.srv.portal.repository" })
@EnableTransactionManagement
@EnableAsync
public class Portal3EApplication extends SpringBootServletInitializer{
	
	public static void main(String[] args) {
		SpringApplication.run(Portal3EApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Portal3EApplication.class);
	}
}
