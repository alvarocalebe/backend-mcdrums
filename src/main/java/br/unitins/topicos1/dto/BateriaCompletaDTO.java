package br.unitins.topicos1.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record BateriaCompletaDTO(
        @NotBlank
        String nomeBateria,
        @NotBlank
        String descricao,
        @NotNull
        @PositiveOrZero
        Double preco,
        @NotNull
        @PositiveOrZero
        Integer quantidadeTambor,
        @NotNull
        @PositiveOrZero
        Integer quantidadeEstoque,
        @NotNull
        Long IdMarca,
        @NotBlank
        String nomeImagem,
        @NotNull
        Long IdCategoria
) {

}
