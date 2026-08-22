package mx.com.qtx.cotizadorv1ds.promos;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class PromDsctoXCantidad extends PromAcumulable {

	private Map<Integer,Double> mapCantidadVsDscto;
	
	private boolean esPromDsctoXCantidadValida(Promocion promoBase, Map<Integer, Double> mapCantidadVsDscto) {
		if(promoBase == null) this.msgErrValidacion.add("Debe existir una promoción base asignada");
		List<Integer> lstKeysDsctos = mapCantidadVsDscto.keySet()
						  							.stream()
						  							.sorted()
						  							.filter(dsctoI -> dsctoI < 0)
						  							.toList();
		List<Double> lstValDsctos = mapCantidadVsDscto.values()
													.stream()
													.sorted()
													.filter(dsctoI -> dsctoI < 0 || dsctoI > PromocionBuilder.MAX_DSCTO_CANTIDAD )
													.toList();
		
		if(lstKeysDsctos.size() > 0) 
			this.msgErrValidacion.add("Las cantidades en la tabla de descuentos " + lstKeysDsctos + " no pueden ser negativas");
		if(lstValDsctos.size() > 0) 
			this.msgErrValidacion.add("Los porcentajes de descuento en la tabla " + lstValDsctos + " no pueden ser negativos o mayores a " 
					+ PromocionBuilder.MAX_DSCTO_CANTIDAD + "%");
		
		if(this.msgErrValidacion.size() > 0) return false;
		
		return true;
	}

	public PromDsctoXCantidad(Promocion promoBase, Map<Integer, Double> mapCantidadVsDscto) {
		super("Dscto con base en tabla de cantidades y descuentos" + mapCantidadVsDscto, "Dscto x cantidad", promoBase);
		if(esPromDsctoXCantidadValida(promoBase, mapCantidadVsDscto) == false)
			throw new IllegalArgumentException("Promoción Acum DsctoXCantidad no válida: \n " + this.msgErrValidacion);
		this.mapCantidadVsDscto = mapCantidadVsDscto;
		this.cveProm = promoBase.cveProm + " +DsctoXCant";
	}

	@Override
	public BigDecimal calcularImportePromocion(int cant, BigDecimal precioBase){
		
		BigDecimal baseCalculo = this.promoBase.calcularImportePromocion(cant, precioBase);
		
		int keyDscto = this.mapCantidadVsDscto.keySet()
											  .stream()
											  .sorted()                           // ordena asc
											  .filter(k -> k <= cant)             // elimina llaves mayores que la cantidad
											  .sorted((n,n2) -> n <= n2 ? 1 : -1) // Ordena elementos filtrados dsc
											  .findFirst()                        // toma el primero, devuele optional
											  .get();                             // toma el valor
		
		BigDecimal porcDscto = new BigDecimal(mapCantidadVsDscto.get(keyDscto)).divide(new BigDecimal(100));

		BigDecimal importeDscto = baseCalculo.multiply(porcDscto);
		return baseCalculo.subtract(importeDscto);

	}

}