package com.lee.shop.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "Shop API 명세서",
            description = "Shop 서비스 API 명세서",
            version = "v1"
        )
)
@Configuration
public class SwaggerConfig {

        @Bean
        public GroupedOpenApi allApi(){
                return GroupedOpenApi.builder()
                        .group("전체")
                        .pathsToMatch("/**")
                        .build();
        }

        @Bean
        public GroupedOpenApi memberApi(){
                return GroupedOpenApi.builder()
                        .group("회원")
                        .pathsToMatch("/api/members/**")
                        .build();
        }
}
