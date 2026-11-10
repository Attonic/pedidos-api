package io.github.pedidosapi.repository;

import io.github.pedidosapi.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoReposiory extends JpaRepository<Pedido, Long> {
}
