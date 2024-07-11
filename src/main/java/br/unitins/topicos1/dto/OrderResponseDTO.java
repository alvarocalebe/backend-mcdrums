package br.unitins.topicos1.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.unitins.topicos1.model.Order;

public record OrderResponseDTO(
        Long id,
        LocalDateTime dataHora,
        ClienteResponseDTO cliente,
        Double totalPedido,
        List<ItemCarrinhoResponseDTO> itens) {

    public static OrderResponseDTO valueOf(Order order) {
        return new OrderResponseDTO(order.getId(),
                order.getDataHora(),
                ClienteResponseDTO.valueOf(order.getCliente()),
                order.getTotalPedido(),
                ItemCarrinhoResponseDTO.valueOf(order.getItens()));
    }
}
