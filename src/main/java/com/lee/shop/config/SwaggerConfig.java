package com.lee.shop.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "Shop API 명세서",
            description = "Shop 서비스 API 명세서",
            version = "v1"
        )
)
@Configuration
public class SwaggerConfig {


}
