package mx.com.qtx.cotizadorv1ds;

import java.math.BigDecimal;

public class Monitor extends Componente {
	
    private int cantMinDsctoA = 3;
    private int cantMaxDsctoA = 5;
    private float dsctoA = 5;
    private int cantMinDsctoB = 6;
    private int cantMaxDsctoB = 999999;
    private float dsctoB = 10;

	public Monitor(String id, String descripcion, String marca, String modelo, BigDecimal costo,
			BigDecimal precioBase) {
		super(id, descripcion, marca, modelo, costo, precioBase);
	}
	
	private BigDecimal calcularPrecioConDsctoXCantidad(int cantidad, Componente componente) {
		//System.out.println("Aplicando promoción de precio con descuento x cantidad");
    	BigDecimal precioConDscto = new BigDecimal(0);
    	precioConDscto = componente.getPrecioBase().multiply(new BigDecimal(cantidad));
    	if(cantidad >= this.cantMinDsctoA && cantidad <= this.cantMaxDsctoA) {
    		precioConDscto = precioConDscto.multiply(new BigDecimal(1-(this.dsctoA/100)));
    	} else if(cantidad >= this.cantMinDsctoB && cantidad <= this.cantMaxDsctoB) {
    		precioConDscto = precioConDscto.multiply(new BigDecimal(1-(this.dsctoB/100)));
    	}
    	return precioConDscto;
    }
	
	@Override
	public BigDecimal cotizar(int cantidad) {
		return this.calcularPrecioConDsctoXCantidad(cantidad, this);
	}
	
	@Override
	public void mostrarCaracteristicas() {
		super.mostrarCaracteristicas();
		System.out.println("Utilidad: " + this.calcularUtilidad());
	}

}
