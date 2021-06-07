package com.example.graphql.config.oauth;

import com.example.graphql.services.serviceprovider.impl.userprofile.UserProfileService;
import com.example.graphql.utils.AESUtils;
import com.example.graphql.utils.JwtFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;


@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    protected static final Logger logger = LogManager.getLogger("demo_graphql");

    private static final String API_DOCS = "/v2/api-docs";
    private static final String SWAGGER_UI_HTML = "/swagger-ui.html";
    private static final String SWAGGER_UI = "/swagger-ui/**";
    private static final String SWAGGER_RESOURCES = "/swagger-resources/**";
    private static final String WEBJARS = "/webjars/**";
    private static final String PUBLIC = "/public";
    private static final String FORGOT_PASSWORD = "/public/user/request-password-reset";
    private static final String OPEN_PATH = "/graphiql";
    private static String SECRET_KEY = "YuTr45QEsUiOppTy";


    @Autowired
    private UserProfileService userDetailsService;
    @Autowired
    private MyBasicAuthenticationEntryPoint basicAuthenticationEntryPoint;
    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        logger.debug("Started building JDBC authentication, adding UserDetailsService, and adding AuthenticationProvider's");
        auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
        logger.debug("Finished building JDBC authentication, adding UserDetailsService, and adding AuthenticationProvider's");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        logger.debug("Started applying web based security for specific http requests");
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();
        http
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilter, RequestHeaderAuthenticationFilter.class);
        logger.debug("Finished applying web based security for specific http requests");
    }

    private PasswordEncoder getPasswordEncoder() {

        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence encodedPassword, String actualPassword) {
                logger.debug("Inside matches");
                logger.debug("encodedPassword is" + encodedPassword.toString() + "rawPassword is " + actualPassword);
                //String decryptText = AESUtils.decrypt(encodedPassword.toString());
                //logger.debug("decrypted text " + decryptText);
                String rawPassword = AESUtils.decrypt(actualPassword, SECRET_KEY).trim();
                logger.debug("raw actualPassword " + rawPassword);
                boolean equals = encodedPassword.toString().trim().equals(rawPassword.trim());
                if (!equals) {
                    logger.error("EncodedPassword & RawPassword does not match . Hence InternalAuthenticationServiceException is thrown");
                    throw new AuthenticationServiceException("Invalid credentials");
                }
                logger.debug("Outside matches");
                return equals;
            }
        };
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(API_DOCS, SWAGGER_UI_HTML, SWAGGER_UI, SWAGGER_RESOURCES,
                WEBJARS, PUBLIC, FORGOT_PASSWORD);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        logger.debug("Creating AuthenticationManager bean");
        return super.authenticationManagerBean();
    }

    @Bean
    SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }


}