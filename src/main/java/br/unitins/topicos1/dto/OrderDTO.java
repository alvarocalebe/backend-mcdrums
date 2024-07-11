package br.unitins.topicos1.dto;

import java.util.List;

public record OrderDTO(
        List<ItemCarrinhoDTO> itens) {
}
