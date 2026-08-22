package mx.com.qtx.cotizadorv1ds.promos;

import java.math.BigDecimal;

public class PromSinDescto extends PromBase {

	public PromSinDescto() {
		super("No se aplica ningun descuento", "Precio regular");
		this.cveProm = "Acum";
	}

	@Override
	public BigDecimal calcularImportePromocion(int cant, BigDecimal precioBase){
		this.validarCantYPrecioBase(cant, precioBase);
		if(this.msgErrValidacion.size() > 0) 
			throw new IllegalArgumentException("Error en calcularImportePromocion \n" + this.msgErrValidacion);
		return precioBase.multiply(new BigDecimal(cant));
	}

}