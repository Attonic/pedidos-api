package io.github.pedidosapi.statemachine;

import io.github.pedidosapi.enums.StatusPedido;

public class AguardandoPagamentoState implements PedidoState {


    @Override
    public StatusPedido status() {
        return StatusPedido.AGUARDANDO_PAGAMENTO;
    }

    @Override
    public PedidoState pagar() {
        return new PagoState();
    }

    @Override
    public PedidoState falharPagamento() {
        return new PagamentoFalhouState();
    }

    @Override
    public PedidoState cancelar() {
        return new CanceladoState();
    }
}
