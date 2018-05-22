package br.com.server.service.test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import br.com.server.model.Adicional;
import br.com.server.model.Lanche;
import br.com.server.model.Pedido;
import br.com.server.model.TipoIngrediente;
import br.com.server.model.TipoLanche;
import br.com.server.service.Service;

/**
 * Classe responsável pela cobertura de testes automatizados dos serviços da
 * classe {@link Service}.
 * 
 * @author Wesley de Araújo Barros
 */
public class ServiceTest {

	private Service service = new Service();
	
	// Teste para calcular o valor do lanche solicitado
	@Test
	public void calcularLanche() {
		BigDecimal expected = new BigDecimal(7.30).setScale(2, RoundingMode.HALF_EVEN);
		Lanche actual = service.calcularLanche(TipoLanche.XEGGBACON);
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(expected, actual.getValor());
	}

	// Teste para calcular valor do pedido sem promoções
	@Test
	public void calcularPedido() {
		Pedido pedido = new Pedido();
		pedido.setLanche(TipoLanche.XBURGER);

		Adicional ad1 = new Adicional();
		ad1.setIngrediente(TipoIngrediente.OVO);
		ad1.qtde = 1;
		Adicional ad2 = new Adicional();
		ad2.setIngrediente(TipoIngrediente.BACON);
		ad2.qtde = 2;
		pedido.adicionais = Arrays.asList(ad1, ad2);

		BigDecimal expected = new BigDecimal(9.30).setScale(2, RoundingMode.HALF_EVEN);

		Pedido actual = service.calcularPedido(pedido);
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(expected, actual.getTotal());
		
		// Sem adicionais
		pedido.adicionais = null;
		expected = new BigDecimal(4.50).setScale(2, RoundingMode.HALF_EVEN);

		actual = service.calcularPedido(pedido);
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(expected, actual.getTotal());
	}

	// Testes para o calculo da promoção: Light
	@Test
	public void calcularPedido_light() {
		Pedido pedido = new Pedido();
		pedido.setLanche(TipoLanche.XBURGER);

		Adicional ad = new Adicional();
		ad.setIngrediente(TipoIngrediente.ALFACE);
		ad.qtde = 2;
		pedido.adicionais = Arrays.asList(ad);

		BigDecimal expected = new BigDecimal(4.77).setScale(2, RoundingMode.HALF_EVEN);

		Pedido actual = service.calcularPedido(pedido);
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(expected, actual.getTotal());
	}

	// Testes para o calculo da promoção: Muita carne
	@Test
	public void calcularPedido_MuitaCarne() {
		Pedido pedido = new Pedido();
		pedido.setLanche(TipoLanche.XBACON);

		Adicional ad = new Adicional();
		ad.setIngrediente(TipoIngrediente.BURGER);
		ad.qtde = 2;
		pedido.adicionais = Arrays.asList(ad);

		BigDecimal expected = new BigDecimal(9.50).setScale(2, RoundingMode.HALF_EVEN);

		Pedido actual = service.calcularPedido(pedido);
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(expected, actual.getTotal());

		ad.qtde = 6;
		pedido.adicionais = Arrays.asList(ad);
		expected = new BigDecimal(18.50).setScale(2, RoundingMode.HALF_EVEN);

		actual = service.calcularPedido(pedido);
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(expected, actual.getTotal());
	}

	// Testes para o calculo da promoção: Muito queijo
	@Test
	public void calcularPedido_MuitoQueijo() {
		Pedido pedido = new Pedido();
		pedido.setLanche(TipoLanche.XEGG);

		Adicional ad = new Adicional();
		ad.setIngrediente(TipoIngrediente.QUEIJO);
		ad.qtde = 2;
		pedido.adicionais = Arrays.asList(ad);

		BigDecimal expected = new BigDecimal(6.80).setScale(2, RoundingMode.HALF_EVEN);

		Pedido actual = service.calcularPedido(pedido);
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(expected, actual.getTotal());

		ad.qtde = 6;
		pedido.adicionais = Arrays.asList(ad);
		expected = new BigDecimal(11.30).setScale(2, RoundingMode.HALF_EVEN);

		actual = service.calcularPedido(pedido);
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(expected, actual.getTotal());
	}

	// Testes para o calculo de todas as promoções
	@Test
	public void calcularPedido_TodasPromocoes() {
		Pedido pedido = new Pedido();
		pedido.setLanche(TipoLanche.XEGG);

		Adicional ad1 = new Adicional();
		ad1.setIngrediente(TipoIngrediente.BURGER);
		ad1.qtde = 3;
		Adicional ad2 = new Adicional();
		ad2.setIngrediente(TipoIngrediente.ALFACE);
		ad2.qtde = 2;
		Adicional ad3 = new Adicional();
		ad3.setIngrediente(TipoIngrediente.QUEIJO);
		ad3.qtde = 5;
		pedido.adicionais = Arrays.asList(ad1, ad2, ad3);

		BigDecimal expected = new BigDecimal(14.94).setScale(2, RoundingMode.HALF_EVEN);

		Pedido actual = service.calcularPedido(pedido);
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(expected, actual.getTotal());
	}
}
