package br.unitins.topicos1.service;
import br.unitins.topicos1.dto.AlterarNomeDTO;
import br.unitins.topicos1.dto.AlterarSenhaDTO;
import br.unitins.topicos1.dto.AlterarLoginDTO;
import br.unitins.topicos1.dto.ClienteDTO;
import br.unitins.topicos1.dto.ClienteResponseDTO;
import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.dto.TelefoneDTO;
import jakarta.ws.rs.core.Response;

import java.util.List;

public interface ClienteService {
    ClienteResponseDTO insert(ClienteDTO dto);

    ClienteResponseDTO update(ClienteDTO dto, Long id);

     public ClienteResponseDTO findByLoginAndSenha(String login, String senha);
   
    public void delete(Long id);

    List<ClienteResponseDTO> getAll();

    ClienteResponseDTO findById(Long id);

    List<ClienteResponseDTO> findByName(String nome);

    public ClienteResponseDTO findByLogin(String login);

     Response updateTelefones(Long id, List<TelefoneDTO> newTelefones);

     Response updateEnderecos(Long id, List<EnderecoDTO> newEnderecos);

    public Boolean alterarSenha(AlterarSenhaDTO dto, String login);

    public Boolean alterarNome(AlterarNomeDTO dto, Long id);

     public Boolean alterarLogin(AlterarLoginDTO dto, Long id);



}
