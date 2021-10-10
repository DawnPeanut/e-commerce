package com.fzh.ecommerce.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.stereotype.Component;

@Configuration
public class SecuritySecureConfig extends WebSecurityConfigurerAdapter {
    private final String adminContextPath;

    public SecuritySecureConfig(AdminServerProperties adminServerProperties){
        this.adminContextPath =adminServerProperties.getContextPath();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler successHandler =
                new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(adminContextPath+"/");

        http.authorizeRequests()
                //1.配置所有的静态资源和登录页可以公开访问
                .antMatchers(adminContextPath+"/assets/**").permitAll()
                .antMatchers(adminContextPath+"/login").permitAll()
                //2.其他请求，必须要经过认证
                .anyRequest().authenticated()
                .and()
                //3.配置登录和登出路径
                .formLogin().loginPage(adminContextPath+"login")
                .successHandler(successHandler)
                .and()
                .logout().logoutUrl(adminContextPath+"/logout")
                .and()
                //4.开启http basic支持，其他的服务模块注册时需要使用
                .httpBasic()
                .and()
                //5.开启基于cookie的csrf保护
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .ignoringAntMatchers(
                        adminContextPath+"/instances",
                        adminContextPath+"/actuator/**"
                );
    }
}
