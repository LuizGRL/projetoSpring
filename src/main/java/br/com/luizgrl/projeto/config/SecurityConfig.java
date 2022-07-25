package br.com.luizgrl.projeto.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment environment;
    private static final String[] PUBLIC_MATCHERS = {
            "/h2-console/**", // tudo que vier desses caminhos vai ser liberado
            "/produtos/**",
            "/categorias/**"
    };

    private static final String[] PUBLIC_MATCHERS_GET = {
            "/produtos/**",
            "/categorias/**",
            "/clientes/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws  Exception{
        if(Arrays.asList(environment.getActiveProfiles()).contains("test")){
            http.headers().frameOptions().sameOrigin(); // libera acesso ao h2
        }
        http.cors().and().csrf().disable();//vai proteger de ataques csrf
        http.authorizeRequests().antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll().antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated(); // vai permitir todos os caminhos que estejam, contudo caso nao esteja na lista vao ser autenticadas
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // vai assegurar que o back end nao vai criar uma seção de usuario
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return  source; // Isso serve para permitir acesso aos end points com configurações basicas
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
