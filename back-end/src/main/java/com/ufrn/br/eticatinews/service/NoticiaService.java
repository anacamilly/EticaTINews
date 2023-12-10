package com.ufrn.br.eticatinews.service;

import com.ufrn.br.eticatinews.model.Noticia;
import com.ufrn.br.eticatinews.repository.NoticiaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoticiaService {
    NoticiaRepository repository;

    public NoticiaService(NoticiaRepository repository){
        this.repository = repository;
    }

    public Noticia create(Noticia e) {
        return (Noticia) this.repository.saveAndFlush(e);
    }

    public Noticia update(Noticia e, String id) {
        Optional<Noticia> pessoaBanco = repository.findById(id);
        if (pessoaBanco.isPresent()){
            return (Noticia) this.repository.save(e);
        }else{
            throw new EntityNotFoundException();
        }
    }

    public void delete(String id) {
        this.repository.deleteById(id);
    }

    public List<Noticia> list() {
        return (List<Noticia>) this.repository.findAll();
    }

    public Noticia getById(String id) {
        Optional<Noticia> pessoaBanco = repository.findById(id);
        if (pessoaBanco.isPresent()){
            return (Noticia) pessoaBanco.get();
        }else{
            throw  new EntityNotFoundException();
        }
    }
}
