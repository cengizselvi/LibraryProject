package com.springbootlibrary.library.config;


import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        //Siteler Arası İstek Sahteciliğini Devre Dışı Bırak
        http.csrf().disable();

// /api/<type>/secure konumunda uç noktaları koruyun
        http.authorizeRequests(configurer ->
                        configurer
                                .antMatchers("/api/books/secure/**",
                                        "/api/reviews/secure/**",
                                        "/api/admin/secure/**")
                                .authenticated())
                .oauth2ResourceServer()
                .jwt();

        // CORS filtrelerini ekle
        http.cors();


// İçerik müzakere stratejisi ekleyin
        http.setSharedObject(ContentNegotiationStrategy.class,
                new HeaderContentNegotiationStrategy());

// Yanıtı kolay hale getirmek için 401'ler için boş olmayan bir yanıt gövdesini zorlayın
Okta.configureResourceServer401ResponseBody(http);

        return http.build();
    }

}



