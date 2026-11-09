package io.github.pedidosapi.statemachine;

import io.github.pedidosapi.enums.StatusPedido;

public class CriadoState implements PedidoState {

    @Override
    public StatusPedido status() {
        return StatusPedido.CRIADO;
    }

    @Override
    public PedidoState aguardarPagamento() {
        return new AguardandoPagamentoState();
    }

    @Override
    public PedidoState cancelar() {
        return new CanceladoState();
    }
}
