package br.com.alphapires.forum.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private AutenticacaoService autenticacaoService;
	
	//Configurações de autenticação(login e controle de acesso)
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//Bcrypt = criptografia de senha;
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	//Configuraçoes de Autorização(perfil de aceesso, quem pode acessar cada url)
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		        .antMatchers(HttpMethod.GET, "/topicos").permitAll()
				.antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
				//usuario autenticado ver Usuario/Perfil
				.anyRequest().authenticated()
				//formulario de login gerado pelo spring
				.and().formLogin();
	}

	//Configurações de recurso estatico(arquivos css, js, imagens, etc...)
	@Override
	public void configure(WebSecurity web) throws Exception {

	}
	
//	public static void main(String[] args) {
//		System.out.println(new BCryptPasswordEncoder().encode("123456"));
//	}
}
