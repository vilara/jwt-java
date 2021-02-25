package com.alura.jpacurso.config.security;

import java.util.Base64;
import java.util.Date;

import com.alura.jpacurso.modelo.Usuario;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

    @Value("${jpacurso.jwt.expiration}")
    private String exp;

    // @Value("${jpacurso.jwt.secret}")
    private String secret = "teste";

    public String gerarToken(Authentication authentication) {

        Usuario logado = (Usuario) authentication.getPrincipal();
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(exp));

        String secretkey = "qwertypassword";
           
        return Jwts.builder()
                // .setIssuer("Geração alura")
                .setSubject(logado.getId().toString())
                .setIssuedAt(hoje).setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secretkey.getBytes()).compact();
   
            }

    public Boolean isTokenVakid(String token) {

        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getIdUsuario(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

}
