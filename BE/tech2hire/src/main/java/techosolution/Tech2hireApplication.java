package techosolution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@SpringBootApplication
@IntegrationComponentScan
@EnableIntegration
public class Tech2hireApplication {

	public static void main(String[] args) {
		SpringApplication.run(Tech2hireApplication.class, args);
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public Docket allApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("ALL Services").apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("techosolution.controller")).paths(regex("/.*"))
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Tech2hire Full Stack Development").description("Techolution Hackathon")
				.contact(new Contact("Atul", "+91-9411355956", "atulkp.eee13@nituk.ac.in"))
				.license("Licensed with Atul").version("SnapShot 1").build();
	}

}
