package mx.com.qtx.cotizadorv1ds;

import java.math.BigDecimal;

public class TarjetaVideo extends Componente {
	private String memoria;

	public TarjetaVideo(String id, String descripcion, String marca, String modelo, BigDecimal costo, BigDecimal precioBase, String memoria) {
		super(id, descripcion, marca, modelo, costo, precioBase);
		this.memoria = memoria;
		if(memoria == null) {
			throw new IllegalArgumentException("Falta atributo memoria en una Tarjeta de Video");
		}
	}
	
	private BigDecimal calcularPrecioPromocion3x2(int cantidad, Componente componente) {
		//System.out.println("Aplicando promoción de 3x2");
		// Obtener el precio base del componente
    	BigDecimal precioBase = componente.getPrecioBase();
    	
    	// Calcular grupos completos de 3 unidades y unidades restantes
    	int gruposCompletos = cantidad / 3;
    	int unidadesRestantes = cantidad % 3;
    	
    	// Calcular total: (2* grupos) + restantes
    	BigDecimal total = precioBase.multiply(BigDecimal.valueOf(gruposCompletos * 2L + unidadesRestantes));
    	
    	return total;
    }

	public String getMemoria() {
		return memoria;
	}

	public void setMemoria(String memoria) {
		this.memoria = memoria;
	}
	
	@Override
	public BigDecimal cotizar(int cantidad) {
		return this.calcularPrecioPromocion3x2(cantidad, this);
	}

	@Override
	public void mostrarCaracteristicas() {
		super.mostrarCaracteristicas();
        System.out.println("Memoria: " + this.memoria);
        System.out.println("Utilidad: " + this.calcularUtilidad());
    }
	
	
	
}
