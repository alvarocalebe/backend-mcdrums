package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.TelefoneResponseDTO;
import br.unitins.topicos1.dto.TelefoneDTO;

import br.unitins.topicos1.model.Telefone;
//import br.unitins.topicos1.model.Cliente;
import br.unitins.topicos1.repository.TelefoneRepository;
import br.unitins.topicos1.repository.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class TelefoneServiceImpl implements TelefoneService {

    @Inject
    TelefoneRepository telefoneRepository;

    @Inject
    ClienteRepository clienteRepository;

    // @Override
    // @Transactional
    // public TelefoneResponseDTO insert(TelefoneDTO dto, Long idCliente) {
    //     Telefone novoTelefone = new Telefone();
    //     novoTelefone.setCodigoArea(dto.codigoArea());
    //     novoTelefone.setNumero(dto.numero());
    //     novoTelefone.setCliente(clienteRepository.findById(dto.idCliente()));

    //     telefoneRepository.persist(novoTelefone);

    //     return TelefoneResponseDTO.valueOf(novoTelefone);
    // }

    @Override
    @Transactional
    public TelefoneResponseDTO insert(TelefoneDTO dto) {
        Telefone novoTelefone = new Telefone();
        novoTelefone.setCodigoArea(dto.codigoArea());
        novoTelefone.setNumero(dto.numero());
        novoTelefone.setCliente(clienteRepository.findById(dto.idCliente()));

        telefoneRepository.persist(novoTelefone);

        return TelefoneResponseDTO.valueOf(novoTelefone);
    }

    @Override
    @Transactional
    public TelefoneResponseDTO update(Long id, TelefoneDTO dto) {
        Telefone telefone = telefoneRepository.findById(id);

        telefone.setCodigoArea(dto.codigoArea());
        telefone.setNumero(dto.numero());
        telefone.setCliente(clienteRepository.findById(dto.idCliente()));

        return TelefoneResponseDTO.valueOf(telefone);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!telefoneRepository.deleteById(id))
            throw new NotFoundException();
    }

    @Override
    public TelefoneResponseDTO findById(Long id) {
        return TelefoneResponseDTO.valueOf(telefoneRepository.findById(id));
    }

    @Override
    public List<TelefoneResponseDTO> findByAll() {
        return telefoneRepository.listAll().stream()
                .map(e -> TelefoneResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<TelefoneResponseDTO> findByIdCliente(Long id) {
        return telefoneRepository.findByIdCliente(id).stream()
                .map(e -> TelefoneResponseDTO.valueOf(e)).toList();
    }

}
