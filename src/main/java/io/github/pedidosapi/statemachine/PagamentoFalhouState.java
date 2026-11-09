package io.github.pedidosapi.statemachine;

import io.github.pedidosapi.enums.StatusPedido;

public class PagamentoFalhouState implements PedidoState {

    @Override
    public StatusPedido status() {
        return StatusPedido.PAGAMENTO_FALHOU;
    }


}
