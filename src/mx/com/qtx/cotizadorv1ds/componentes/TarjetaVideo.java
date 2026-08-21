package mx.com.qtx.cotizadorv1ds.componentes;

import java.math.BigDecimal;

public class TarjetaVideo extends ComponenteSimple {
	private String memoria;
	
	private boolean esTarjetaValida(String memoria) {
		if(memoria == null || memoria.trim().equals(""))
			this.msgErrValidacion.add("La memoria es obligatoria");
		if(this.msgErrValidacion.size()>0) return false;
		return true;
	}

	protected TarjetaVideo(String id, String descripcion, String marca, String modelo, BigDecimal costo, BigDecimal precioBase, String memoria) {
		super(id, descripcion, marca, modelo, costo, precioBase);
		
		if(this.esTarjetaValida(memoria) == false) {
			throw new IllegalArgumentException("Tarjeta de video no válida: \n" + this.msgErrValidacion);
		}
		this.memoria = memoria;
	}
	
	public String getMemoria() {
		return memoria;
	}

	public void setMemoria(String memoria) {
		this.memoria = memoria;
	}
	
	/*
	@Override
	public BigDecimal cotizar(int cantidad) {
		return PromocionUtil.calcularPrecioPromocion3X2(cantidad, this.precioBase);
	}
	*/

	@Override
	public void mostrarCaracteristicas() {
		super.mostrarCaracteristicas();
        System.out.println("Memoria: " + this.memoria);
    }

	@Override
	public String getCategoria() {
		return "Tarjeta de Video";
	}
	
}
