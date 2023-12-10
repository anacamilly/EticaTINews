package com.ufrn.br.eticatinews.service;

import com.ufrn.br.eticatinews.model.Usuario;
import com.ufrn.br.eticatinews.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {
    UsuarioRepository repository;
    PasswordEncoder encoder;

    public UsuarioService(UsuarioRepository repository, PasswordEncoder encoder){
        this.repository = repository;
        this.encoder = encoder;
    }
    public void createUsuario(Usuario u){
        u.setPassword(encoder.encode(u.getPassword()));
        this.repository.save(u);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }

    public Usuario getById(String id) {
        Optional<Usuario> pessoaBanco = repository.findById(id);
        if (pessoaBanco.isPresent()){
            return (Usuario) pessoaBanco.get();
        }else{
            throw new EntityNotFoundException();
        }
    }

    public List<Usuario> list() {
        return (List<Usuario>) this.repository.findAll();
    }
}
