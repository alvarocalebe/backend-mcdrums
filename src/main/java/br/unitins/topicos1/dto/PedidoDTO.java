package br.unitins.topicos1.dto;


public record PedidoDTO(
        Integer quantidade,
        Double preco,
        Long idProduct
) {





//             public Endereco valueOf() {
//         Endereco novo = new Endereco();
//         novo.setCep(cep());
//         novo.setCidade(cidade());
//         novo.setEstado(Estado.valueOf(idEstado()));
//         novo.setNumero(numero());
//         novo.setRua(rua());
//         return novo;
//     }

}
