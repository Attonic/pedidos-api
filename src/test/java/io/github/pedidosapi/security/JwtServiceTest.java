package io.github.pedidosapi.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class JwtServiceTest {

    private static final String SEGREDO = "um-segredo-bem-longo-para-testes-1234567890";

    @Test
    void tokenGeradoTrazOUsernameDeVolta() {
        JwtService service = new JwtService(SEGREDO, 60_000);
        String token = service.gerarToken("antonio");
        assertEquals("antonio", service.extrairUsername(token));

    }

    @Test
    void tokenAdulteradoEInvalido() {
        JwtService service = new JwtService(SEGREDO, 60_000);
        String token = service.gerarToken("Antonio");

        String[] partes = token.split("\\.");
        char[] assinatura = partes[2].toCharArray();
        int meio = assinatura.length / 2;

        assinatura[meio] = assinatura[meio] == 'A' ? 'B' : 'A';
        partes[2] = new String(assinatura);

        String adulterado = String.join(".", partes);

        assertFalse(service.tokenValido(adulterado));
    }

    @Test
    void tokenExpiradoEInvalido () throws InterruptedException {
        JwtService service = new JwtService(SEGREDO, 1);
        String token = service.gerarToken("antonio");

        Thread.sleep(50);
        assertFalse(service.tokenValido(token));

    }

    @Test
    void tokenAssinadoComOutraChaveEInvalida () {
        JwtService emissor = new JwtService(SEGREDO, 10_000);
        JwtService validador = new JwtService(SEGREDO + "2", 10_000);

        String token = emissor.gerarToken("antonio");
        assertFalse(validador.tokenValido(token));

    }

    @Test
    void tokenMalFormadoEInvalido () {
        JwtService service = new JwtService(SEGREDO, 60_000);
        String token = service.gerarToken("antonio");

        assertFalse(service.tokenValido(token + "errado"));
        assertFalse(service.tokenValido("isso-nao-e-um-jwt"));
    }
}
