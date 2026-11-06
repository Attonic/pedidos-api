package io.github.pedidosapi.entity;

import io.github.pedidosapi.enums.StatusPedido;
import io.github.pedidosapi.exception.TransicaoInvalidaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PedidoTest {

    @Test
    void deveNascerComoCriado() {
        Pedido pedido = new Pedido();

        assertEquals(StatusPedido.CRIADO, pedido.getStatusPedido());
    }

    @Test
    void deveTransicionarDeCriadoParaAguardandoPagamento() {
        Pedido pedido = new Pedido();

        pedido.transicionarPara(StatusPedido.AGUARDANDO_PAGAMENTO);

        assertEquals(StatusPedido.AGUARDANDO_PAGAMENTO, pedido.getStatusPedido());
    }

    @Test
    void naoDeveTransicionarDeCriadoParaPago() {
        Pedido pedido = new Pedido();

        assertThrows(TransicaoInvalidaException.class,
                () -> pedido.transicionarPara(StatusPedido.PAGO));
    }



}
