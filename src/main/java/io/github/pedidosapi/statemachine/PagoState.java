package io.github.pedidosapi.statemachine;

import io.github.pedidosapi.enums.StatusPedido;

public class PagoState implements PedidoState {


    @Override
    public StatusPedido status() {
        return StatusPedido.PAGO;
    }

    @Override
    public PedidoState enviar() {
        return new EnviadoState();
    }
}
