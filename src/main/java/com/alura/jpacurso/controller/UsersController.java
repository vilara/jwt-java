package com.alura.jpacurso.controller;

import java.net.URI;
import java.util.Optional;

import javax.print.URIException;
import javax.validation.Valid;

import com.alura.jpacurso.controller.DTO.UsersDTO;
import com.alura.jpacurso.controller.form.UserForm;
import com.alura.jpacurso.modelo.Usuario;
import com.alura.jpacurso.repository.UsuarioRepository;

import org.dom4j.util.UserDataAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;



@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public Page<UsersDTO> lista(@RequestParam(required = false) String email, @PageableDefault(sort="id",direction = Direction.DESC, page = 0, size = 10) Pageable pageable) {
      

        if (email == null) {
            Page<Usuario> usuario = this.usuarioRepository.findAll(pageable);
            return UsersDTO.converter(usuario);
           
        } else {

            Page<Usuario> usuario = this.usuarioRepository.findAll(pageable);
            return UsersDTO.converter(usuario);
        }
    }

    @PostMapping
    public ResponseEntity<UsersDTO> cadastrar(@RequestBody @Valid UserForm form, UriComponentsBuilder uriBuilder){

        Usuario usuario = form.converter();
        usuarioRepository.save(usuario);

        URI uri = uriBuilder.path("/users/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsersDTO(usuario));



    }
 

@GetMapping("/{id}")
public ResponseEntity<UsersDTO> detalhar(@PathVariable Long id) {

    Optional<Usuario> usuario = usuarioRepository.findById(id);
    if(usuario.isPresent()){
        return ResponseEntity.ok(new UsersDTO(usuario.get()));
    }
    return ResponseEntity.notFound().build();

}
  
}
