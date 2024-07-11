package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Cliente;

import java.time.LocalDate;
import java.util.List;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String sobrenome,
        LocalDate dataNascimento,
        String cpf,
        String email,
        String login,
        List<TelefoneDTO> listaTelefone,
        List<EnderecoDTO> listaEndereco

) {
    public static ClienteResponseDTO valueOf(Cliente cliente) {
        try {
            return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getSobrenome(),
                cliente.getDataNascimento(),
                cliente.getCpf(),
                cliente.getEmail(),
                cliente.getLogin(),
                cliente.getListaTelefone().stream().map(t -> TelefoneDTO.valueOf(t)).toList(),
                cliente.getListaEndereco().stream().map(t -> EnderecoDTO.valueOf(t)).toList());
                
                    
                    
            
        } catch (NullPointerException e) {
            return null;
        }
    }
}
