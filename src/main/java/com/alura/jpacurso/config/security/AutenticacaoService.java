package com.alura.jpacurso.config.security;

import java.util.Optional;

import com.alura.jpacurso.modelo.Usuario;
import com.alura.jpacurso.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<Usuario> usuario = usuarioRepository.findByEmail(username);

       if(usuario.isPresent()){
           return usuario.get();
       }

        throw new UsernameNotFoundException("Dados Inválidos!") ;
    }
    
}
