package com.alura.jpacurso.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alura.jpacurso.modelo.Usuario;
import com.alura.jpacurso.repository.UsuarioRepository;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;


public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {
  
    private TokenService tokenService;
    private UsuarioRepository repository;

    public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository repository) {
        this.tokenService = tokenService;
        this. repository = repository;
    }
    

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                String token = recuperarToken(request);
              //  System.out.println(token);

                Boolean valido = tokenService.isTokenVakid(token);
               // System.out.println(valido);
if (valido) {
    autenticarCliente(token);
}
                
                filterChain.doFilter(request, response);

    }

    private void autenticarCliente(String token) {
        Long idUsuario = tokenService.getIdUsuario(token);
        Usuario usuario = repository.findById(idUsuario).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recuperarToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")){

            return null;
        }

       return token.substring(7, token.length());


    }


    
}
