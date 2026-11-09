package io.github.pedidosapi.statemachine;

import io.github.pedidosapi.enums.StatusPedido;

public final class PedidoStateFactory {

    private PedidoStateFactory() {
    }

    public static PedidoState de(StatusPedido status) {
        return switch (status) {
            case CRIADO -> new CriadoState();
            case AGUARDANDO_PAGAMENTO -> new AguardandoPagamentoState();
            case PAGO -> new PagoState();
            case ENVIADO -> new EnviadoState();
            case PAGAMENTO_FALHOU -> new PagamentoFalhouState();
            case CANCELADO -> new CanceladoState();
        };
    }

}
