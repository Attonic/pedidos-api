package io.github.pedidosapi.entity;

import io.github.pedidosapi.enums.StatusPedido;
import io.github.pedidosapi.exception.TransicaoInvalidaException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

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

    @ParameterizedTest
    @CsvSource({
            "CRIADO, AGUARDANDO_PAGAMENTO",
            "CRIADO, CANCELADO",
            "AGUARDANDO_PAGAMENTO, PAGO",
            "AGUARDANDO_PAGAMENTO, PAGAMENTO_FALHOU",
            "AGUARDANDO_PAGAMENTO, CANCELADO",
            "PAGO, ENVIADO"
    })
    void devePermitirTransicoesValidas(StatusPedido origem, StatusPedido destino) {
        Pedido pedido = new Pedido(origem);

        pedido.transicionarPara(destino);

        assertEquals(destino, pedido.getStatusPedido());

    }

    @ParameterizedTest
    @CsvSource({
            "CRIADO, PAGO",
            "CRIADO, ENVIADO",
            "AGUARDANDO_PAGAMENTO, ENVIADO",
            "PAGO, CANCELADO",
            "PAGO, AGUARDANDO_PAGAMENTO"
    })
    void naoDevePermitirTransicoesInvalidas(StatusPedido origin, StatusPedido destino) {
        Pedido pedido = new Pedido(origin);

        assertThrows(TransicaoInvalidaException.class,
                () -> pedido.transicionarPara(destino));
    }

    @ParameterizedTest
    @EnumSource(value = StatusPedido.class, names = {"ENVIADO", "CANCELADO", "PAGAMENTO_FALHOU"})
    void estadoFinaisNaoAceitamNenhumaTransicao(StatusPedido estadoFinal) {
        for ( StatusPedido destino : StatusPedido.values()) {
            Pedido pedido = new Pedido(estadoFinal);

            assertThrows(TransicaoInvalidaException.class,
                () -> pedido.transicionarPara(destino));
        }
        
    }
}
