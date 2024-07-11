package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Telefone;

public record TelefoneResponseDTO(
        Long id,
        String codigoArea,
        String numero,
        ClienteResponseDTO cliente) {
    public static TelefoneResponseDTO valueOf(Telefone telefone) {
        return new TelefoneResponseDTO(
                telefone.getId(),
                telefone.getCodigoArea(),
                telefone.getNumero(),
                ClienteResponseDTO.valueOf(telefone.getCliente()));
    }
}
