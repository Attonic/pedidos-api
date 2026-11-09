package io.github.pedidosapi.statemachine;

import io.github.pedidosapi.enums.StatusPedido;
import io.github.pedidosapi.exception.TransicaoInvalidaException;

public interface PedidoState {

    StatusPedido status();

    default PedidoState aguardarPagamento() {
        throw invalida(StatusPedido.AGUARDANDO_PAGAMENTO);
    }

    default PedidoState pagar() {
        throw invalida(StatusPedido.PAGO);
    }

    default PedidoState falharPagamento() {
        throw invalida(StatusPedido.PAGAMENTO_FALHOU);
    }

    default PedidoState enviar() {
        throw invalida(StatusPedido.ENVIADO);
    }

    default PedidoState cancelar() {
        throw invalida(StatusPedido.CANCELADO);
    }

    private TransicaoInvalidaException invalida(StatusPedido destino) {
        return new TransicaoInvalidaException(
                "Transição inválida: " + status() + " -> " + destino
        );
    }

}
