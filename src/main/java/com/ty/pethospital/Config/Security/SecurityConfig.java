package com.ty.pethospital.Config.Security;

import com.ty.pethospital.Entity.TyUser;
import com.ty.pethospital.Service.TyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    TyUserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .formLogin().disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        WebSecurity.IgnoredRequestConfigurer ignoring = web.ignoring();
        //阿里数据监控
        ignoring.antMatchers("/druid/**");
        // 允许对于网站静态资源的无授权访问
        ignoring.antMatchers("/",
                "/**/favicon.ico",
                "/**/*.css",
                "/**/*.js",
                "/**/*.ttf",
                "/**/*.woff",
                "/**/*.jpg",
                "/**/*.png");
        ignoring.antMatchers(
                //用户登录
                "/user/login",
                //注册
                "/user",
                //验证
                "/user/exist",
                //忘记密码
                "/user/forget",
                "/user/existByParm",
                //接口文档
                "/swagger-ui.html",
                "/webjars/**",
                "/v2/**",
                "/swagger-resources/**");
        //跨域请求会先进行一次options请求
        ignoring.antMatchers(HttpMethod.OPTIONS);
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> {
            TyUser oneByUsername = userService.findOneByUsername(username);
            if (oneByUsername != null) {
                return oneByUsername;
            }
            throw new UsernameNotFoundException("用户名或密码错误");
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
