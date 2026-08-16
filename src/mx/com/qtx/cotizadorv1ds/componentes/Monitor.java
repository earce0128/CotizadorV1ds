package mx.com.qtx.cotizadorv1ds.componentes;

import java.math.BigDecimal;
import java.util.Map;

public class Monitor extends ComponenteSimple {
	
    private static Map<Integer, Double> mapDsctos = Map.of(0,  0.0,
			   											   3,  5.0,
			   											   6, 10.0,
			   											   9, 12.0);
    
	protected Monitor(String id, String descripcion, String marca, String modelo, BigDecimal costo,
			BigDecimal precioBase) {
		super(id, descripcion, marca, modelo, costo, precioBase);
		if(this.msgErrValidacion.size() > 0) {
			throw new IllegalArgumentException("Monitor no válido: \n" + this.msgErrValidacion);
		}
	}
	
	@Override
	public BigDecimal cotizar(int cantidad) {
		return PromocionUtil.calcularPrecioPromocionDsctoXcant(cantidad, this.precioBase, mapDsctos);
	}

	@Override
	public String getCategoria() {
		return "Monitor";
	}
	
}
