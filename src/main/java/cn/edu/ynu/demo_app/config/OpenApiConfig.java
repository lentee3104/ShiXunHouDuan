package cn.edu.ynu.demo_app.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "${api.title}",
                version = "${api.version}",
                description = "${api.description}",
                contact = @Contact(name = "SunXP", url = "https://github.com/sxpynu"),
                license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0")
        )
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApiConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder().group("public_api")
                .pathsToMatch("/auth/**", "/public/**")
                .build();
    }

    @Bean
    public GroupedOpenApi protectedApi() {
        return GroupedOpenApi.builder().group("protected_api")
                .pathsToMatch("/api/**")
                .build();
    }

}
