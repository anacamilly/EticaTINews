package com.ufrn.br.eticatinews.controller;

import com.ufrn.br.eticatinews.model.Usuario;
import com.ufrn.br.eticatinews.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    UsuarioService service;
    ModelMapper mapper;

    public UsuarioController(UsuarioService service, ModelMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario.DtoResponse create(@RequestBody Usuario.DtoRequest u) {
        Usuario usuario = Usuario.DtoRequest.convertToEntity(u, mapper);
        this.service.createUsuario(usuario);

        Usuario.DtoResponse response = Usuario.DtoResponse.convertToDto(usuario, mapper);
        return response;

    }

    @GetMapping
    public List<Usuario.DtoResponse> list(){
        return this.service.list().stream().map(
                elementoAtual -> {
                    Usuario.DtoResponse response = Usuario.DtoResponse.convertToDto(elementoAtual, mapper);
                    return response;
                }).toList();
    }
}
