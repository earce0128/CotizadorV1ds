package mx.com.qtx.cotizadorv1ds;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Pc extends Componente {
	
	private float porcPrecioAgregado = 20;
	private List<Componente> subcomponentes;

	public Pc(String id, String descripcion, String marca, String modelo, 
			Componente disco1, Componente disco2, Componente monitor, Componente tarjeta) {
		super(id, descripcion, marca, modelo, new BigDecimal(0), new BigDecimal(0));
		// Validaciones ...
    	if(disco1 == null && disco2 == null) {
    		throw new IllegalArgumentException("Una PC debe tener al menos un Disco Duro");
    	}
    	if(monitor == null) {
    		throw new IllegalArgumentException("Una PC debe tener un Monitor");
    	}
    	if(tarjeta == null) {
    		throw new IllegalArgumentException("Una PC debe tener una Tarjeta de Video");
    	}
    	
    	//Crear agregado
    	this.subcomponentes = new ArrayList<>();
    	subcomponentes.add(disco1);
    	subcomponentes.add(disco2);
    	subcomponentes.add(monitor);
    	subcomponentes.add(tarjeta);
    	
    	//Calculando el costo por los subcomponentes
    	BigDecimal costo = ((disco1 == null) ? new BigDecimal(0) : disco1.costo)
    							.add((disco2 == null) ? new BigDecimal(0) : disco2.costo)
    							.add(monitor.costo)
    							.add(tarjeta.costo);
    	this.setCosto(costo);
    	
    	//Calculando el precioBase con los subcomponentes
    	BigDecimal precioBase = ((disco1 == null) ? new BigDecimal(0) : disco1.precioBase)
    								.add((disco2 == null) ? new BigDecimal(0) : disco2.precioBase)
				  					.add(monitor.precioBase)
				  					.add(tarjeta.precioBase);
    	this.setPrecioBase(precioBase);
	}
	
	private BigDecimal calcularPrecioComponenteAgregado(int cantidad, Componente componente) {
		//System.out.println("Aplicando promoción de descuento por componente agregado");
		BigDecimal total = BigDecimal.ZERO;
    	Pc pc = (Pc) componente;
    	for(Componente c : pc.subcomponentes) {
    		if(c == null)
    			continue;
    		total = total.add(c.getPrecioBase());
    	}
        return total.multiply(BigDecimal.valueOf(1 - (porcPrecioAgregado / 100)));
    }

	public List<Componente> getSubcomponentes() {
		return subcomponentes;
	}

	@Override
	public BigDecimal cotizar(int cantidad) {
		return this.calcularPrecioComponenteAgregado(cantidad, this);
	}

	@Override
	public void mostrarCaracteristicas() {
		super.mostrarCaracteristicas();
		System.out.println("Utilidad: " + this.calcularUtilidad());
		// Mostrando los subcomponentes de una PC
		System.out.println("\nComponentes anidados: ------------------------");
		this.subcomponentes.stream()
        					.filter(compI -> compI != null)
        					.forEach(compI-> compI.mostrarCaracteristicas());
	}
}
