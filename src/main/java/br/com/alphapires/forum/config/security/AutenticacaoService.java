package br.com.alphapires.forum.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.alphapires.forum.model.Usuario;
import br.com.alphapires.forum.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService{
// usa a interface UserDetailsService para dizer que esta e a classe de autenticação

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
		
		if (usuario.isPresent()) {
			return usuario.get();
		}
		throw new UsernameNotFoundException("Usuario ou senha inválido!");
	}

	
}
