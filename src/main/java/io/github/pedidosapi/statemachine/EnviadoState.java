package io.github.pedidosapi.statemachine;

import io.github.pedidosapi.enums.StatusPedido;

public class EnviadoState implements PedidoState{

    @Override
    public StatusPedido status() {
        return StatusPedido.ENVIADO;
    }
}
