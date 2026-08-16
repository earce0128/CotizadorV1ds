package mx.com.qtx.cotizadorv1ds.componentes;

import java.math.BigDecimal;

public class DiscoDuro extends ComponenteSimple {
	
	private String capacidadAlm;
	
	private boolean esDiscoDuroValido(String capacidadAlm) {
		//this.validarComponenteSimple(id, descripcion, marca, modelo, costo, precioBase);
		if(capacidadAlm == null || capacidadAlm.trim().equals(""))
			this.msgErrValidacion.add("La capacidad de Almacenamiento es obligatoria");
		if(this.msgErrValidacion.size() > 0) return false;
		return true;
	}

	protected DiscoDuro(String id, String descripcion, String marca, String modelo, BigDecimal costo,
			BigDecimal precioBase, String capacidadAlm) {
		super(id, descripcion, marca, modelo, costo, precioBase);
		if(this.esDiscoDuroValido(capacidadAlm) == false)
			throw new IllegalArgumentException("Disco duro no válido: \n" + this.msgErrValidacion);
		this.capacidadAlm = capacidadAlm;
	}

	public String getCapacidadAlm() {
		return capacidadAlm;
	}

	public void setCapacidadAlm(String capacidadAlm) {
		this.capacidadAlm = capacidadAlm;
	}
	
	@Override
	public void mostrarCaracteristicas() {
        super.mostrarCaracteristicas();
		System.out.println("Capacidad Almacenamiento: " + this.capacidadAlm);
    }

	@Override
	public String getCategoria() {
		return "Disco Duro";
	}

}
