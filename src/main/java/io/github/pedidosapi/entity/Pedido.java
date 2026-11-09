package io.github.pedidosapi.entity;

import io.github.pedidosapi.enums.StatusPedido;
import io.github.pedidosapi.exception.TransicaoInvalidaException;
import io.github.pedidosapi.statemachine.PedidoState;
import io.github.pedidosapi.statemachine.PedidoStateFactory;

public class Pedido {

    private PedidoState state;

    public Pedido() {
        this(StatusPedido.CRIADO);
    }
    public Pedido(StatusPedido statosInicial){
        this.state = PedidoStateFactory.de(statosInicial);
    }

   public StatusPedido getStatusPedido() {
        return state.status();
   }

   public void transicionarPara(StatusPedido novo) {
        this.state = switch (novo) {
            case AGUARDANDO_PAGAMENTO -> state.aguardarPagamento();
            case PAGO -> state.pagar();
            case PAGAMENTO_FALHOU -> state.falharPagamento();
            case ENVIADO -> state.enviar();
            case CANCELADO -> state.cancelar();
            case CRIADO -> throw new TransicaoInvalidaException(
                    "Transição inválida: " + state.status() + " -> " + novo
            );
        };
   }


}
