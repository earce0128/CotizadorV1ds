package mx.com.qtx.cotizadorv1ds.componentes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Pc extends Componente {
	
	//private static final float DSCTO_PRECIO_AGREGADO = 20.0f;
	
	private List<ComponenteSimple> subcomponentes;
	
	protected Pc(String id, String descripcion, String marca, String modelo, List<ComponenteSimple> subcomponentes) {
		super(id, descripcion, marca, modelo, new BigDecimal(0), new BigDecimal(0));
		if(this.esUnaPcValida(subcomponentes) == false)
			throw new IllegalArgumentException("Pc no válida: \n" + this.msgErrValidacion);
		this.subcomponentes = subcomponentes;
		this.setPrecioBase(this.calcularPrecioComponenteAgregado());
		this.setCosto(this.calcularCostoComponenteAgregado());

	}
	
	protected Pc(PcBuilder config) {
		super(config.getIdPc(), config.getDescripcionPc(), 
			  config.getMarcaPc(), config.getModeloPc(), new BigDecimal(0), new BigDecimal(0));
		
		List<ComponenteSimple> lstDispositivosPc = new ArrayList<>();
		lstDispositivosPc.addAll(config.getComponentesPc());
		
		if(this.esUnaPcValida(lstDispositivosPc) == false)
			throw new IllegalArgumentException("Pc no válida: \n" + this.msgErrValidacion);
		this.subcomponentes = lstDispositivosPc;
		this.setPrecioBase(this.calcularPrecioComponenteAgregado());
		this.setCosto(this.calcularCostoComponenteAgregado());
	}
	
	private boolean esUnaPcValida(List<ComponenteSimple> subcomponentes) {
		validarSubcomponentesPc(subcomponentes);
		if(this.msgErrValidacion.size() > 0) return false;
		return true;
	}
	
	private static int obtenerCantComponentes(List<ComponenteSimple> subcomponentes, String tipo) {
		return (int)subcomponentes.stream()
				.filter(compI -> compI.getClass().getSimpleName().equalsIgnoreCase(tipo))
				.count();
	}
	
	private static int obtenerCantComponentesNoPermitidos(List<ComponenteSimple> subcomponentes) {
		return (int)subcomponentes.stream().filter(compI -> !compI.getClass().getSimpleName().equalsIgnoreCase("Monitor"))  
				   						   .filter(compI -> !compI.getClass().getSimpleName().equalsIgnoreCase("TarjetaVideo"))
				   						   .filter(compI -> !compI.getClass().getSimpleName().equalsIgnoreCase("DiscoDuro"))
				   						   .count();
	}
	
	private boolean validarSubcomponentesPc(List<ComponenteSimple> subcomponentes) {

		int cantDiscos = obtenerCantComponentes(subcomponentes, "DiscoDuro");
		int cantTarjetasVideo = obtenerCantComponentes(subcomponentes, "TarjetaVideo");
		int cantMonitores = obtenerCantComponentes(subcomponentes, "Monitor");
		int cantCompNoPermitidos = obtenerCantComponentesNoPermitidos(subcomponentes);
		
		if(cantDiscos < PcBuilder.getMinDisco() || cantDiscos > PcBuilder.getMaxDiscos())
			this.msgErrValidacion.add("Debe tener mínimo " + PcBuilder.getMinDisco() + " y máximo " + PcBuilder.getMaxDiscos() + " discos");
		if(cantMonitores < PcBuilder.getMinMonitores() || cantMonitores > PcBuilder.getMaxMonitores())
			this.msgErrValidacion.add("Debe tener mínimo " + PcBuilder.getMinMonitores() + " y máximo " +	PcBuilder.getMaxMonitores() + " monitores");
		if(cantMonitores < PcBuilder.getMinTarjetas() || cantTarjetasVideo > PcBuilder.getMaxTarjetas())
			this.msgErrValidacion.add("Debe tener mínimo " + PcBuilder.getMinTarjetas() + " y máximo " +	PcBuilder.getMaxTarjetas() + " tarjetas");
		if(cantCompNoPermitidos > 0) 
			this.msgErrValidacion.add("Una PC solo debe tener Discos Duros, un Monitor y una Tarjeta de Video");
		
		if(this.msgErrValidacion.size() > 0) return false;
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

	public List<ComponenteSimple> getSubcomponentes() {
		return subcomponentes;
	}
	
	public List<ComponenteSimple> getDiscosDuros(){
		List<ComponenteSimple> lstDiscos = this.subcomponentes.stream()
														.filter(compI -> compI instanceof DiscoDuro)
														.toList();
		if(lstDiscos.size() > 0) return lstDiscos;
		return null;
	}
	
	public List<ComponenteSimple> getMonitores(){
		List<ComponenteSimple> lstMonitores = this.subcomponentes.stream()
															.filter(compI -> compI instanceof Monitor)
															.toList();
		if(lstMonitores.size() > 0) return lstMonitores;
		return null;
	}
	
	public List<ComponenteSimple> getTarjetasVideo(){
		List<ComponenteSimple> lstTarjetas = this.subcomponentes.stream()
														.filter(compI -> compI instanceof Monitor)
														.toList();
		if(lstTarjetas.size() > 0) return lstTarjetas;
		return null;
	}
	
	/*
	@Override
	public BigDecimal cotizar(int cantidad) {
		return PromocionUtil.calcularPrecioPromocionDscto(cantidad, this.precioBase, DSCTO_PRECIO_AGREGADO);
	}
	*/

	@Override
	public void mostrarCaracteristicas() {
		super.mostrarCaracteristicas();
		
		// Mostrando los subcomponentes de una PC
		System.out.println("\n==== Disco(s) ====");
		this.subcomponentes.stream()
		                   .filter(scI->scI instanceof DiscoDuro)
		                   .forEach(dscI-> { dscI.mostrarCaracteristicas(); 
		                   		             System.out.println();
		                   		             });
		System.out.println("==== Monitor(es) ====");
		this.subcomponentes.stream()
		                   .filter(scI->scI instanceof Monitor)
		                   .forEach(monI-> { monI.mostrarCaracteristicas(); 
		                   		             System.out.println();});
		System.out.println("==== Tarjeta(s) de Video ====");
		this.subcomponentes.stream()
		                   .filter(scI->scI instanceof Monitor)
		                   .forEach(tarI-> { tarI.mostrarCaracteristicas(); 
		                   		             System.out.println();});
	}

	@Override
	public String getCategoria() {
		return "PC";
	}
}
