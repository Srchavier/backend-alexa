package br.com.projetoAlexa;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
@EnableCaching
public class ProjetoAlexaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoAlexaApplication.class, args);
	}
	
	@Bean
	@Profile("!prod")
	public GroupedOpenApi actuatorApi() {
		return GroupedOpenApi.builder().group("Actuator")
				.pathsToMatch("/actuator/**")
				.pathsToExclude("/actuator/health/*")
				.build();
	}

	@Bean
	public OpenAPI customOpenAPI(@Value("${application-version}") String appVersion) {
		return new OpenAPI()
				.info(new Info().title("Horoscopo API").version(appVersion).description("")
						.termsOfService("http://swagger.io/terms/")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}
}
