package com.skblab.project.account_service.config;

public class SecurityConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .authoritiesByUsernameQuery("select PHONE, ROLE from account where PHONE=?")
                .usersByUsernameQuery("select PHONE, PASSWORD, ENABLED from account where USERNAME=?");
    }

}
