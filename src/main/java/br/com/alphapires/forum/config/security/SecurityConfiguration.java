package br.com.alphapires.forum.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	//Configurações de autenticação(login e controle de acesso)
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	}
	
	//Configuraçoes de Autorização(perfil de aceesso, quem pode acessar cada url)
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		        .antMatchers(HttpMethod.GET, "/topicos").permitAll()
				.antMatchers(HttpMethod.GET, "/topicos/*").permitAll();
	}

	//Configurações de recurso estatico(arquivos css, js, imagens, etc...)
	@Override
	public void configure(WebSecurity web) throws Exception {

	}
}
