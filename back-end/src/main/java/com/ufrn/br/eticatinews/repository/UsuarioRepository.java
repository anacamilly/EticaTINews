package com.ufrn.br.eticatinews.repository;


import com.ufrn.br.eticatinews.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    UserDetails findByUsername(String username);
}