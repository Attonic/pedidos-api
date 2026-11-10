package io.github.pedidosapi.repository;

import io.github.pedidosapi.entity.Pedido;
import io.github.pedidosapi.enums.StatusPedido;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.context.annotation.Import;
import io.github.pedidosapi.TestcontainersConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestcontainersConfiguration.class)
public class PedidoRepositoryTest {

    @Autowired
    private PedidoReposiory repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void deveSalvarPedidoEGerarId() {

        Pedido pedido = repository.save(new Pedido());

        assertEquals( StatusPedido.CRIADO, pedido.getStatusPedido());
        assertNotNull(pedido.getId());

    }

    @Test
    void devePersistirTransicaoDeStatus() {

        Pedido pedido = repository.save(new Pedido());
        pedido.transicionarPara(StatusPedido.AGUARDANDO_PAGAMENTO);
        entityManager.flush();
        entityManager.clear();

        Pedido buscado = repository.findById(pedido.getId()).orElseThrow();

        assertEquals(StatusPedido.AGUARDANDO_PAGAMENTO, buscado.getStatusPedido());
    }

}
