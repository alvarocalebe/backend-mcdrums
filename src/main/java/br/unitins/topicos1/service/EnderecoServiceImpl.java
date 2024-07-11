package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.dto.EnderecoResponseDTO;
import br.unitins.topicos1.model.Endereco;
import br.unitins.topicos1.repository.EnderecoRepository;
import br.unitins.topicos1.repository.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
//import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class EnderecoServiceImpl implements EnderecoService {

    @Inject
    EnderecoRepository enderecoRepository;

    @Inject
    ClienteRepository clienteRepository;

    // @Override
    // @Transactional
    // public EnderecoResponseDTO insert(EnderecoDTO dto, Long idCliente) {
    //     Endereco novoEndereco = new Endereco();
    //     novoEndereco.setRua(dto.rua());
    //     novoEndereco.setNumero(dto.numero());
    //     novoEndereco.setCidade(dto.cidade());
    //     novoEndereco.setEstado(dto.estado());
    //     novoEndereco.setCep(dto.cep());
    //     novoEndereco.setCliente(clienteRepository.findById(dto.idCliente()));

    //     enderecoRepository.persist(novoEndereco);

    //     return EnderecoResponseDTO.valueOf(novoEndereco);
    // }

    @Override
    @Transactional
    public EnderecoResponseDTO insert(EnderecoDTO dto) {
        Endereco novoEndereco = new Endereco();
        novoEndereco.setRua(dto.rua());
        novoEndereco.setNumero(dto.numero());
        novoEndereco.setCidade(dto.cidade());
        novoEndereco.setEstado(dto.estado());
        novoEndereco.setCep(dto.cep());
        novoEndereco.setCliente(clienteRepository.findById(dto.idCliente()));

        enderecoRepository.persist(novoEndereco);

        return EnderecoResponseDTO.valueOf(novoEndereco);
    }

    @Override
    @Transactional
    public EnderecoResponseDTO update(Long id, EnderecoDTO dto) {
        Endereco endereco = enderecoRepository.findById(id);

        endereco.setRua(dto.rua());
        endereco.setNumero(dto.numero());
        endereco.setCidade(dto.cidade());
        endereco.setEstado(dto.estado());
        endereco.setCep(dto.cep());
        endereco.setCliente(clienteRepository.findById(dto.idCliente()));

        return EnderecoResponseDTO.valueOf(endereco);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!enderecoRepository.deleteById(id))
            throw new NotFoundException();
    }

    @Override
    public EnderecoResponseDTO findById(Long id) {
        return EnderecoResponseDTO.valueOf(enderecoRepository.findById(id));
    }

    @Override
    public List<EnderecoResponseDTO> findByAll() {
        return enderecoRepository.listAll().stream()
                .map(e -> EnderecoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<EnderecoResponseDTO> findByIdCliente(Long id) {
        return enderecoRepository.findByIdCliente(id).stream()
                .map(e -> EnderecoResponseDTO.valueOf(e)).toList();
    }

}
