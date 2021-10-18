package com.ssafy.buki.config.security;

import com.ssafy.buki.config.properties.CorsProperties;
import com.ssafy.buki.token.TokenAuthenticationEntryPoint;
import com.ssafy.buki.token.TokenAccessDeniedHandler;
import com.ssafy.buki.token.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenProvider tokenProvider;
    private CorsProperties corsProperties;
    private final TokenAuthenticationEntryPoint tokenAuthenticationEntryPoint;
    private final TokenAccessDeniedHandler tokenAccessDeniedHandler;

    public SecurityConfig( //객체 주입
                           TokenProvider tokenProvider,
                           CorsProperties corsProperties,
                           TokenAuthenticationEntryPoint tokenAuthenticationEntryPoint,
                           TokenAccessDeniedHandler tokenAccessDeniedHandler
    ) {
        this.tokenProvider = tokenProvider;
        this.corsProperties = corsProperties;
        this.tokenAuthenticationEntryPoint = tokenAuthenticationEntryPoint;
        this.tokenAccessDeniedHandler = tokenAccessDeniedHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); //패스워드인코더
    }

    //h2콘솔 하위 모든 요청들과 파비콘 관련 요청은 Spring Security로직을 수행하지 않고 접근할 수 있게 configure메소드 오버라이드 하여 추가
    @Override
    public void configure(WebSecurity web){
        web
                .ignoring()
                .antMatchers(
                        "/h2-console/**"
                        , "/favicon.ico"
                );
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
                .csrf().disable()

                .cors().configurationSource(corsProperties.corsFilter()) //jwt 관련 적용해야할 부분
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(tokenAuthenticationEntryPoint) //예외처리 시 우리가 만든 클래스 추가
                .accessDeniedHandler(tokenAccessDeniedHandler)

                // enable h2-console
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                // 세션을 사용하지 않기 때문에 STATELESS로 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll()
                .anyRequest().authenticated()

                .and()
                .apply(new JwtConfig(tokenProvider));
    }
}
