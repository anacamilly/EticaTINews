package com.ufrn.br.eticatinews.controller;

import com.ufrn.br.eticatinews.model.Noticia;
import com.ufrn.br.eticatinews.service.NoticiaService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/noticia")
public class NoticiaController {
    NoticiaService service;
    ModelMapper mapper;

    public NoticiaController(NoticiaService service, ModelMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<Noticia.DtoResponse> list(){
        return this.service.list().stream().map(
                elementoAtual -> {
                    Noticia.DtoResponse response = Noticia.DtoResponse.convertToDto(elementoAtual, mapper);
                    return response;
                }
        ).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Noticia.DtoResponse create(@RequestBody Noticia.DtoRequest m){
        Noticia medico = this.service.create(Noticia.DtoRequest.convertToEntity(m, mapper));
        Noticia.DtoResponse response = Noticia.DtoResponse.convertToDto(medico, mapper);
        return response;
    }

    @GetMapping("{id}")
    public Noticia.DtoResponse getById(@PathVariable String id){
        Noticia medico = this.service.getById(id);

        Noticia.DtoResponse response = Noticia.DtoResponse.convertToDto(medico, mapper);

        return response;
    }

    @PutMapping("{id}")
    public Noticia update(@RequestBody Noticia m, @PathVariable String id){
        return this.service.update(m, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        this.service.delete(id);
    }
}
