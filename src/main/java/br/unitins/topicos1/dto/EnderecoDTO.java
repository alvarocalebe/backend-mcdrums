package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Endereco;

public record EnderecoDTO(

        String rua,
        String numero,
        String cidade,
        String estado,
        String cep,
        Long idCliente) {

    public static EnderecoDTO valueOf(Endereco endereco) {
        return new EnderecoDTO(
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep(),
                endereco.getIdCliente());
    }
}
