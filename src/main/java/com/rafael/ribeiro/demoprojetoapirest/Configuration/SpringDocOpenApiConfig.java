package com.rafael.ribeiro.demoprojetoapirest.Configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {

    @Bean
    public OpenAPI openAPI(){
            return new OpenAPI()
            .info(
                    new Info()
                            .title("Rest Api - Spring Park")
                            .description("Api para Criação e cadastro dos clientes no meu sistema")
                            .version("v1.0")
                            .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0"))
                            .contact(new Contact().name("Rafael Ribeiro").email("rafaelsilveira0780@outlook.com\n"))
                    );
    }
}
