package io.github.pedidosapi.entity;

import io.github.pedidosapi.enums.StatusPedido;
import io.github.pedidosapi.exception.TransicaoInvalidaException;

public class Pedido {

    private StatusPedido status = StatusPedido.CRIADO;

    public StatusPedido getStatusPedido() {
        return status;
    }

    public void transicionarPara(StatusPedido novo){
        if (status == StatusPedido.CRIADO && novo == StatusPedido.AGUARDANDO_PAGAMENTO) {
            this.status = novo;
            return;
        }

        throw new TransicaoInvalidaException("Transição inválida: " + status + " -> " + novo);
    }

}
