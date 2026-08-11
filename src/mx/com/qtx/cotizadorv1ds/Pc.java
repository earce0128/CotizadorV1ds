package mx.com.qtx.cotizadorv1ds;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Pc extends Componente {
	
	private static final float DSCTO_PRECIO_AGREGADO = 20.0f;
	private static final int MAX_CANT_DISCOSDUSROS = 2;
	private static final int MAX_CANT_MONITORES = 1;
	private static final int MAX_CANT_TARJETASVIDEO = 1;
	private List<Componente> subcomponentes;

	public Pc(String id, String descripcion, String marca, String modelo, 
			List<Componente> subcomponentes) {
		super(id, descripcion, marca, modelo, new BigDecimal(0), new BigDecimal(0));
		if(this.validarComponentesPc(subcomponentes)) {
			this.subcomponentes = subcomponentes;
			this.setPrecioBase(this.calcularPrecioComponenteAgregado());
			this.setCosto(this.calcularCostoComponenteAgregado());
		}
	}
	
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
    	
    	this.setPrecioBase(this.calcularPrecioComponenteAgregado());
		this.setCosto(this.calcularCostoComponenteAgregado());
	}
	
	private int obtenerCantComponentes(List<Componente> subcomponentes, String tipo) {
		return (int)subcomponentes.stream()
				.filter(compI -> compI.getClass().getSimpleName().equalsIgnoreCase(tipo))
				.count();
	}
	
	private int obtenerCantComponentesNoPermitidos(List<Componente> subcomponentes) {
		return (int)subcomponentes.stream().filter(compI -> !compI.getClass().getSimpleName().equalsIgnoreCase("Monitor"))  
				   						   .filter(compI -> !compI.getClass().getSimpleName().equalsIgnoreCase("TarjetaVideo"))
				   						   .filter(compI -> !compI.getClass().getSimpleName().equalsIgnoreCase("DiscoDuro"))
				   						   .count();
	}
	
	private boolean validarComponentesPc(List<Componente> subcomponentes) {

		int cantDiscos = this.obtenerCantComponentes(subcomponentes, "DiscoDuro");
		int cantTarjetasVideo = this.obtenerCantComponentes(subcomponentes, "TarjetaVideo");
		int cantMonitores = this.obtenerCantComponentes(subcomponentes, "Monitor");
		int cantCompNoPermitidos = this.obtenerCantComponentesNoPermitidos(subcomponentes);
		
		System.out.println("Discos encontrados: " + cantDiscos);
		System.out.println("Tarjetas de Video encontradas: " + cantTarjetasVideo);
		System.out.println("Monitores encontrados: " + cantMonitores);
		System.out.println("Componentes no permitidos encontrados: " + cantCompNoPermitidos);
		
		if(cantDiscos == 0 || cantDiscos > MAX_CANT_DISCOSDUSROS) {
			throw new IllegalArgumentException("Una PC debe tener al menos un Disco Duro y no más de " + MAX_CANT_DISCOSDUSROS);
		}
		if(cantMonitores == 0 || cantMonitores > MAX_CANT_MONITORES) {
			throw new IllegalArgumentException("Una PC debe tener un Monitor y no más de " + MAX_CANT_MONITORES);
		}
		if(cantTarjetasVideo == 0 || cantTarjetasVideo > MAX_CANT_TARJETASVIDEO) {
			throw new IllegalArgumentException("Una PC debe tener una Tarjeta de Video y no más de " + MAX_CANT_TARJETASVIDEO);
		}
		if(cantCompNoPermitidos > 0) {
			throw new IllegalArgumentException("Una PC solo debe tener Discos Duros, un Monitor y una Tarjeta de Video");
		}
		
		return true;
	}
	
	private BigDecimal calcularPrecioComponenteAgregado() {
		BigDecimal precioPc = BigDecimal.ZERO;
    	for(Componente c : this.subcomponentes) {
    		if(c == null)
    			continue;
    		precioPc = precioPc.add(c.getPrecioBase());
    	}
    	return precioPc;
    }
	
	private BigDecimal calcularCostoComponenteAgregado() {
		BigDecimal costoPc = BigDecimal.ZERO;
    	for(Componente c : this.subcomponentes) {
    		if(c == null)
    			continue;
    		costoPc = costoPc.add(c.getCosto());
    	}
    	return costoPc;
    }

	public List<Componente> getSubcomponentes() {
		return subcomponentes;
	}

	@Override
	public BigDecimal cotizar(int cantidad) {
		//return this.calcularPrecioComponenteAgregado(cantidad);
		return PromocionUtil.calcularPrecioPromocionDscto(cantidad, this.precioBase, DSCTO_PRECIO_AGREGADO);
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
