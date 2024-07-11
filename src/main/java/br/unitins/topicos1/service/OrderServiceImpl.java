package br.unitins.topicos1.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.ItemCarrinhoDTO;
import br.unitins.topicos1.dto.OrderDTO;
import br.unitins.topicos1.dto.OrderResponseDTO;
import br.unitins.topicos1.model.BateriaCompleta;
import br.unitins.topicos1.model.ItemCarrinho;
import br.unitins.topicos1.model.Order;
import br.unitins.topicos1.repository.BateriaCompletaRepository;
import br.unitins.topicos1.repository.ClienteRepository;
import br.unitins.topicos1.repository.OrderRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class OrderServiceImpl implements OrderService {

    @Inject
    BateriaCompletaRepository bateriaCompletaRepository;

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    OrderRepository orderRepository;

    @Override
    @Transactional
    public OrderResponseDTO insert(OrderDTO dto, String login) {
        Order order = new Order();
        order.setDataHora(LocalDateTime.now());

        // calculo do total do order
        Double total = 0.0;
        for (ItemCarrinhoDTO itemDto : dto.itens()) {
            total += (itemDto.preco() * itemDto.quantidade());
        }
        order.setTotalPedido(total);

        // adicionando os itens do order
        order.setItens(new ArrayList<ItemCarrinho>());
        for (ItemCarrinhoDTO itemDto : dto.itens()) {
            ItemCarrinho item = new ItemCarrinho();
            item.setPreco(itemDto.preco());
            item.setQuantidade(itemDto.quantidade());
            item.setPedido(order);
            BateriaCompleta bateriaCompleta = bateriaCompletaRepository.findById(itemDto.idProduct());
            item.setBateriaCompleta(bateriaCompleta);

            // atualizado o estoque
            bateriaCompleta.setQuantidadeEstoque(bateriaCompleta.getQuantidadeEstoque() - item.getQuantidade());

            order.getItens().add(item);
        }

        // buscando o usuario pelo login
        order.setCliente(clienteRepository.findByLogin(login));

        // salvando no banco
        orderRepository.persist(order);

        // atualizando o estoque

        return OrderResponseDTO.valueOf(order);
    }

    // @Override
    // public OrderResponseDTO findById(Long id) {
    // return OrderResponseDTO.valueOf(orderRepository.findById(id));
    // }

    // @Override
    // public OrderResponseDTO findById(Long id) {
    //     Order order = orderRepository.findById(id);
    //     if (order == null) {
    //         // Tratar o caso onde o pedido não é encontrado, talvez lançar uma exceção ou
    //         // retornar null.
    //         return null;
    //     }
    //     return OrderResponseDTO.valueOf(order);
    // }

    @Override
    @Transactional
    public OrderResponseDTO findById(Long id) {
        Order order = orderRepository.findByIdWithItems(id);
        if (order == null) {
            // Tratar o caso onde o pedido não é encontrado, talvez lançar uma exceção ou retornar null.
            return null;
        }
        return OrderResponseDTO.valueOf(order);
    }

    // @Override
    // public List<OrderResponseDTO> findByAll() {
    // return orderRepository.listAll().stream()
    // .map(e -> OrderResponseDTO.valueOf(e)).toList();
    // }

    @Override
    public List<OrderResponseDTO> findByAll() {
        return orderRepository.listAll().stream()
                .map(OrderResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    // @Override
    // public List<OrderResponseDTO> findByAll(String login) {
    // return orderRepository.find("usuario.login", login).list().stream()
    // .map(e -> OrderResponseDTO.valueOf(e)).toList();
    // }

    @Override
    public List<OrderResponseDTO> findByAll(String login) {
        return orderRepository.find("cliente.login", login).list().stream()
                .map(OrderResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    // @Override
    // public List<OrderResponseDTO> findByUserLogin(String login) {
    // List<Order> orders = orderRepository.findAll(login);
    // return orders.stream()
    // .map(OrderResponseDTO::valueOf)
    // .collect(Collectors.toList());
    // }

    @Override
    public List<OrderResponseDTO> findByUserLogin(String login) {
        return orderRepository.find("usuario.login", login).list().stream()
                .map(OrderResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    public List<Order> findByAll(Long idCliente) {
        return orderRepository.findAll(idCliente);
    }

    @Override
    public List<OrderResponseDTO> findByUserId(Long idCliente) {
        return orderRepository.find("cliente.id", idCliente).list().stream()
                .map(OrderResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    // @Override
    // public List<OrderResponseDTO> findByUserId(Long idUsuario) {
    // List<Order> orders = orderRepository.findAll(idUsuario);
    // return orders.stream()
    // .map(OrderResponseDTO::valueOf)
    // .collect(Collectors.toList());
    // }

}
