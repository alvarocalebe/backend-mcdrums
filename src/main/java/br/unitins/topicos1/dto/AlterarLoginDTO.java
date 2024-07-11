package br.unitins.topicos1.dto;

import jakarta.validation.constraints.Size;

public record AlterarLoginDTO(
        @Size
        (min = 4) String loginNovo
) {

}

