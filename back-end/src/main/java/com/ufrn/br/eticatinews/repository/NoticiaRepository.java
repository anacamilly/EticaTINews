package com.ufrn.br.eticatinews.repository;

import com.ufrn.br.eticatinews.model.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticiaRepository extends JpaRepository<Noticia, String> {
}
