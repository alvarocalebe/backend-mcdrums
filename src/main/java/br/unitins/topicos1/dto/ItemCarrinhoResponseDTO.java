package br.unitins.topicos1.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.model.ItemCarrinho;

public record ItemCarrinhoResponseDTO(
        Integer quantidade,
        Double preco,
        Long idProduto,
        String nome
       ) {

    public static ItemCarrinhoResponseDTO valueOf(ItemCarrinho item) {
      
        return new ItemCarrinhoResponseDTO(
                item.getQuantidade(),
                item.getPreco(),
                item.getBateriaCompleta().getId(),
                item.getBateriaCompleta().getNomeBateria() 
                );
    }



    public static List<ItemCarrinhoResponseDTO> valueOf(List<ItemCarrinho> itens) {
        return itens.stream()
                .map(ItemCarrinhoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

}
