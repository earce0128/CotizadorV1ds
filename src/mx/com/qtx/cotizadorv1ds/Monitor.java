package mx.com.qtx.cotizadorv1ds;

import java.math.BigDecimal;
import java.util.Map;

public class Monitor extends Componente {
	
    private static Map<Integer, Double> mapDsctos = Map.of(0,  0.0,
			   											   3,  5.0,
			   											   6, 10.0,
			   											   9, 12.0);
    
	public Monitor(String id, String descripcion, String marca, String modelo, BigDecimal costo,
			BigDecimal precioBase) {
		super(id, descripcion, marca, modelo, costo, precioBase);
	}
	
	@Override
	public BigDecimal cotizar(int cantidad) {
		return PromocionUtil.calcularPrecioPromocionDsctoXcant(cantidad, this.precioBase, mapDsctos);
	}
	
	@Override
	public void mostrarCaracteristicas() {
		super.mostrarCaracteristicas();
		System.out.println("Utilidad: " + this.calcularUtilidad());
	}
}
