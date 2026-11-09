package io.github.pedidosapi.statemachine;

import io.github.pedidosapi.enums.StatusPedido;

public class CanceladoState implements PedidoState {

    @Override
    public StatusPedido status() {
        return StatusPedido.CANCELADO;
    }
}
