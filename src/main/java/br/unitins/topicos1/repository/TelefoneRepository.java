package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Telefone;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TelefoneRepository implements PanacheRepository<Telefone> {

    public List<Telefone> findByIdCliente(Long idCliente) {
        return find("cliente.id = ?1", idCliente).list();
    }

    public Telefone findById(Integer id) {
        if (id == null)
            return null;
        return find("id", id).firstResult();
    }
}
