package mx.com.qtx.cotizadorv1ds.promos;

import java.math.BigDecimal;

public class PromDsctoPlano extends PromAcumulable {

	private float porcDescto; // Se recibe como 7.5 para 7.5%
	
	private boolean esPromDsctoPlanoValida(Promocion promoBase, float porcDescto) {
		if(promoBase == null) this.msgErrValidacion.add("Debe existir una promoción base asignada");
		if(porcDescto <= 0.0 || porcDescto > PromocionBuilder.MAX_DSCTO_PLANO )
			this.msgErrValidacion.add("El valor del porcentaje de descuento [" + porcDescto + "] debe ser positivo y menor que " 
					+ PromocionBuilder.MAX_DSCTO_PLANO  + "%");
		if(this.msgErrValidacion.size()>0) return false;
		
		return true;
	}

	public PromDsctoPlano(Promocion promoBase, float porcDescto) {
		super(String.format("Descuento Plano del %4.2f %%",porcDescto), "Dscto Plano", promoBase);
		if(esPromDsctoPlanoValida(promoBase, porcDescto) == false)
			throw new IllegalArgumentException("Promoción Acum DsctoPlano no válida: \n " + this.msgErrValidacion);
		this.porcDescto = porcDescto;
		this.cveProm = promoBase.cveProm + String.format(" +%2.1f%%", porcDescto);
	}

	@Override
	public BigDecimal calcularImportePromocion(int cant, BigDecimal precioBase){
		if(this.msgErrValidacion.size() > 0) 
			throw new IllegalArgumentException("Error en calcularImportePromocion \n" + this.msgErrValidacion);
		BigDecimal baseCalculo = this.promoBase.calcularImportePromocion(cant, precioBase);
		BigDecimal porcDscto = new BigDecimal(porcDescto).divide(new BigDecimal(100));
		BigDecimal importeDscto = baseCalculo.multiply(porcDscto);
		return baseCalculo.subtract(importeDscto);
	}

}