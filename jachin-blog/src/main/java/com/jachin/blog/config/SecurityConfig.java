package com.jachin.blog.config;

import com.jachin.blog.filter.JwtAuthenticationTokenFilter;
import com.jachin.blog.handler.AuthenticationEntryPointImpl;
import com.jachin.blog.handler.CustomAccessDeniedHandler;
import com.jachin.blog.handler.CustomLogoutSuccessHandler;
import com.jachin.blog.handler.LoginSuccessHandler;
import com.jachin.blog.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.filter.CorsFilter;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/06/30 17:26
 */
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomAccessDeniedHandler accessDeniedHandler;
    private final AuthenticationEntryPointImpl authenticationEntryPoint;
    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    private final PermitAllUrlProperties permitAllUrl;
    private final CorsFilter corsFilter;
    private final LoginSuccessHandler loginSuccessHandler;

    public SecurityConfig(CustomAccessDeniedHandler accessDeniedHandler, AuthenticationEntryPointImpl authenticationEntryPoint, UserDetailsService userDetailsService, UserService userService, JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter, PermitAllUrlProperties permitAllUrl, CorsFilter corsFilter, LoginSuccessHandler loginSuccessHandler) {
        this.accessDeniedHandler = accessDeniedHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationTokenFilter = jwtAuthenticationTokenFilter;
        this.permitAllUrl = permitAllUrl;
        this.corsFilter = corsFilter;
        this.loginSuccessHandler = loginSuccessHandler;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 注解标记允许匿名访问的url
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
        permitAllUrl.getUrls().forEach(url -> registry.antMatchers(url).permitAll());

        http
                // CSRF禁用，因为不使用session
                .csrf().disable()
                // 认证失败处理类
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(authenticationEntryPoint).and()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .formLogin().successHandler(loginSuccessHandler).and()
                // 过滤请求
                .authorizeRequests()
                // 对于登录login 注册register 验证码captcha 允许匿名访问
                .antMatchers("/login", "/register", "/captcha", "sendCode", "/").anonymous()
                // 静态资源，可匿名访问
                .antMatchers(HttpMethod.GET, "/", "/*.html", "/**/*.html", "/**/*.css", "/**/*.js", "/profile/**").permitAll()
                .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/*/api-docs", "/druid/**").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated().and()
                .headers().frameOptions().disable();
        // 添加Logout filter
        http.logout().logoutUrl("/logout").logoutSuccessHandler(new CustomLogoutSuccessHandler());
        // 添加JWT filter
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // 添加CORS filter
        http.addFilterBefore(corsFilter, JwtAuthenticationTokenFilter.class);
        http.addFilterBefore(corsFilter, LogoutFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
