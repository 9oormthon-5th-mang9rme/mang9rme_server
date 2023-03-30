package com.manggoormy.manggoormy.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocsConfig {

    @Bean
    public OpenAPI openAPI(
            @Value("${springdoc.version}") String version
    ) {

        Info info = new Info()
                .title("맹구르미 API 문서") // 타이틀
                .version(version) // 문서 버전
                .description("잘못된 부분이나 오류 발생 시 바로 말씀해주세요.") // 문서 설명
                .contact(new Contact() // 연락처
                        .name("Hwan")
                        .email("thirdshine@gmail.com"));

        return new OpenAPI()
                .info(info);
    }
}
