package br.com.alphapires.forum.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Override
	@Bean 
	// a classe AuthenticationManager nao e injetada automaticamente para podermos 
	// injeta-la com @Autowired precisamos sobreescrever o metodo authenticationManager
	// da WebSecurityConfigurerAdapter que devolver um objeto to tipo AuthenticationManager
	// sendo assim precisamos anotar o metodo como @Bean ou seja, com isto o spring gerancia
	// o objeto retornado e que nos permite anota-lo com @Autowired em outra classe
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	
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
				.antMatchers(HttpMethod.POST, "/auth").permitAll()
				.antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
				//usuario autenticado ver Usuario/Perfil
				.anyRequest().authenticated()
				//formulario de login gerado pelo spring
				.and().csrf().disable()
				//metodo para dizer que nao usaremos sessao para autenticação esta serao stateless
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	//Configurações de recurso estatico(arquivos css, js, imagens, etc...)
	@Override
	public void configure(WebSecurity web) throws Exception {

	}
	
//	public static void main(String[] args) {
//		System.out.println(new BCryptPasswordEncoder().encode("123456"));
//	}
}
