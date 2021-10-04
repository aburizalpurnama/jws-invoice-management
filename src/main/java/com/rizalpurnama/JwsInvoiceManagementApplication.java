package com.rizalpurnama;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.MustacheFactory;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

@SpringBootApplication
public class JwsInvoiceManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwsInvoiceManagementApplication.class, args);
	}

	@Bean
	public LayoutDialect layoutDialect(){
		return new LayoutDialect();
	}

	@Bean
	public SpringDataDialect springDataDialect() {
		return new SpringDataDialect();
	}

	@Bean
	public MustacheFactory mustacheFactory(){
		return new DefaultMustacheFactory();
	}

}
