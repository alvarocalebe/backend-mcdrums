package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Endereco;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EnderecoRepository implements PanacheRepository<Endereco> {

    public List<Endereco> findByIdCliente(Long idCliente) {
        return find("cliente.id = ?1", idCliente).list();
    }

    @Transactional
    public void deleteByCliente(Long clienteId) {
        delete("cliente.id", clienteId);
    }

    public Endereco findById(Integer id) {
        if (id == null)
            return null;
        return find("id", id).firstResult();
    }

}
