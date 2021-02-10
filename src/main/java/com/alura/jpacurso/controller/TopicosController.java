package com.alura.jpacurso.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.alura.jpacurso.controller.DTO.DetalhesdoTopicoDTO;
import com.alura.jpacurso.controller.DTO.TopicoDTO;
import com.alura.jpacurso.controller.form.AtualizacaoTopicoForm;
import com.alura.jpacurso.controller.form.TopicoForm;
import com.alura.jpacurso.modelo.Topico;
import com.alura.jpacurso.repository.CursoRepository;
import com.alura.jpacurso.repository.TopicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicorepository;

    @Autowired
    private CursoRepository cursorepository;

    @GetMapping
    @Cacheable(value = "listaDeTopicos")
    public Page<TopicoDTO> lista(@RequestParam(required = false) String nomeCurso, @PageableDefault(sort="id",direction = Direction.DESC, page = 0, size = 10) Pageable pegebale) {
      

        if (nomeCurso == null) {
            Page<Topico> topicos = this.topicorepository.findAll(pegebale);
            return TopicoDTO.converter(topicos);
        } else {
            Page<Topico> topicos = this.topicorepository.findByCursoNome(nomeCurso, pegebale);
            return TopicoDTO.converter(topicos);
        }
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
        Topico topico = form.convverter(cursorepository);
        topicorepository.save(topico);

        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));

    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesdoTopicoDTO> detalhar(@PathVariable Long id) {

        Optional<Topico> topico = topicorepository.findById(id);
        if(topico.isPresent()){
            return ResponseEntity.ok(new DetalhesdoTopicoDTO(topico.get()));
        }
        return ResponseEntity.notFound().build();

    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form){
     

        Optional<Topico> optional = topicorepository.findById(id);
        if(optional.isPresent()){
            Topico topico = form.atualizar(id,topicorepository);
            return ResponseEntity.ok(new TopicoDTO(topico));
        }
        return ResponseEntity.notFound().build();
     
      
      

    } 

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<?> remover(@PathVariable Long id){

        Optional<Topico> optional = topicorepository.findById(id);
        if(optional.isPresent()){
            topicorepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();


    }
}
