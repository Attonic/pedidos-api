package io.github.pedidosapi;

import org.springframework.boot.SpringApplication;

public class TestPedidosApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(PedidosApiApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
