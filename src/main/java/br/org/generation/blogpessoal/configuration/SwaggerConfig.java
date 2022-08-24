package br.org.generation.blogpessoal.configuration;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI springBlogPessoalOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Bianca Controle")
						.description("Projeto Pessoal - Controle Bianca")
						.version("v0.0.1")
				.license(new License()
						.name("Igor Santos")
						.url("Meu Site Aqui"))
				.contact(new Contact()
						.name("Igor Santos")
						.url("https://www.linkedin.com/in/igorss4/")
						.email("igor.ox13@gmail.com")))
				.externalDocs(new ExternalDocumentation()
						.description("Github")
						.url("https://github.com/Igorss4/"));
	}
	
	@Bean
	public OpenApiCustomiser customerGobalHeaderOpenApiCustomiser() {
		
		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
				
				ApiResponses apiResponses = operation.getResponses();
				
				apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
				apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!"));
				apiResponses.addApiResponse("204", createApiResponse("Objeto Excluido!"));
				apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição!"));
				apiResponses.addApiResponse("401", createApiResponse("Acesso Não Autorizado!"));
				apiResponses.addApiResponse("404", createApiResponse("Objeto Não Encontrado!"));
				apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));
				
				
			}));
		};
	}
	
	private ApiResponse createApiResponse(String message) {
		return new ApiResponse().description(message);
	}
	

}
