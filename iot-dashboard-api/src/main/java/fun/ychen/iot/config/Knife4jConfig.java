package fun.ychen.iot.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfig {
    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("app-api")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("物联网大屏")
                        .description("设备数据上报、告警自动生成等")
                        .version("v1.0")
                        .contact(new Contact().name("ychen").email("2317413623@qq.com"))
                        .license(new License().name("Apache 2.0").url("https://ychen.fun")));
    }
}
