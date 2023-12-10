package com.ufrn.br.eticatinews.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Noticia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titulo;

    private String conteudo;

    private LocalDateTime dataPublicacao;

    private String imagem;

    @Data
    public static class DtoRequest {
        @NotBlank(message = "Titulo com nome em branco")
        String titulo;
        @NotBlank(message = "Conteudo com nome em branco")
        String conteudo;
        @NotBlank(message = "Data de publicacao com nome em branco")
        String dataPublicacao;
        String imagem;

        public static Noticia convertToEntity(Noticia.DtoRequest dto, ModelMapper mapper) {
            return mapper.map(dto, Noticia.class);
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class DtoResponse extends RepresentationModel<Noticia.DtoResponse> {
        String titulo;
        String conteudo;

        public static Noticia.DtoResponse convertToDto(Noticia u, ModelMapper mapper){
            return mapper.map(u, Noticia.DtoResponse.class);
        }

    }
}
