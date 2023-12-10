package com.ufrn.br.eticatinews.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

//    @NotBlank(message = "Usuário com nome em branco")
//    String nome;
//
//    @NotBlank(message = "Usuário com contato em branco")
//    String contato;
//
//    String email;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String login;

    private String password;

    private String foto;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Data
    public static class DtoRequest {
        @NotBlank(message = "Usuário com nome em branco")
        String username;
        @NotBlank(message = "Login com nome em branco")
        String login;
        @NotBlank(message = "Password com nome em branco")
        String password;

        public static Usuario convertToEntity(DtoRequest dto, ModelMapper mapper) {
            return mapper.map(dto, Usuario.class);
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class DtoResponse extends RepresentationModel<DtoResponse> {
        String username;
        String login;
        String password;

        public static DtoResponse convertToDto(Usuario u, ModelMapper mapper){
            return mapper.map(u, DtoResponse.class);
        }

    }
}
