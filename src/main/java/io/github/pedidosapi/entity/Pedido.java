package io.github.pedidosapi.entity;

import io.github.pedidosapi.enums.StatusPedido;
import io.github.pedidosapi.exception.TransicaoInvalidaException;
import io.github.pedidosapi.statemachine.PedidoState;
import io.github.pedidosapi.statemachine.PedidoStateFactory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedido status;


    public Pedido() {
        this(StatusPedido.CRIADO);
    }

    public Pedido(StatusPedido statusInicial) {
        this.status = statusInicial;
    }

    public Long getId(){
        return id;
    }

    public StatusPedido getStatusPedido(){
        return status;
    }


   public void transicionarPara(StatusPedido novo) {

        PedidoState atual = PedidoStateFactory.de(this.status);

        PedidoState proximo = switch (novo) {
            case AGUARDANDO_PAGAMENTO -> atual.aguardarPagamento();
            case PAGO -> atual.pagar();
            case PAGAMENTO_FALHOU -> atual.falharPagamento();
            case ENVIADO -> atual.enviar();
            case CANCELADO -> atual.cancelar();
            case CRIADO -> throw new TransicaoInvalidaException(
                    "Transição inválida: " + atual.status() + " -> " + novo
            );
        };

        this.status = proximo.status();
   }


}
