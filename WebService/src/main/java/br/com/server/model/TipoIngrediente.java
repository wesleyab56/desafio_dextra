package br.com.server.model;

import java.math.BigDecimal;

/**
 * Enumeração dos tipos de ingredientes.
 * 
 * @author Wesley de Araújo Barros
 */
public enum TipoIngrediente {
	
	ALFACE,
	
	BACON,
	
	BURGER,
	
	OVO,
	
	QUEIJO;
	
	public static String nome(TipoIngrediente tipo) {
		switch (tipo) {
		case ALFACE:
			return "Alface";
		case BACON:
			return "Bacon";
		case BURGER:
			return "Hambúrguer de carne";
		case OVO:
			return "Ovo";
		case QUEIJO:
			return "Queijo";
		default:
			break;
		}
		return null;
	}
	
	public static BigDecimal valor(TipoIngrediente tipo) {
		switch (tipo) {
		case ALFACE:
			return new BigDecimal(0.40);
		case BACON:
			return new BigDecimal(2.00);
		case BURGER:
			return new BigDecimal(3.00);
		case OVO:
			return new BigDecimal(0.80);
		case QUEIJO:
			return new BigDecimal(1.50);
		default:
			break;
		}
		return BigDecimal.ZERO;
	}
}
