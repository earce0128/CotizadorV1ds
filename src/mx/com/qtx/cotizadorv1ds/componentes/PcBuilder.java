package mx.com.qtx.cotizadorv1ds.componentes;

import java.util.ArrayList;
import java.util.List;

public class PcBuilder {
	
	private String id;
	private String descripcion;
	private String marca;
	private String modelo;
	
	private List<Componente> subcomponentes;
	
	private int contDiscos = 0;
	private int contMonitores = 0;
	private int contTarjetas = 0;
	
	private static int maxDiscos = 2;
	private static int maxMonitores = 1;
	private static int maxTarjetas = 1;
	
	public PcBuilder(String id, String descripcion, String marca, String modelo) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.marca = marca;
		this.modelo = modelo;
		this.subcomponentes = new ArrayList<>();
	}

	private boolean esPcValida() {
		if(contMonitores == 0 || contMonitores > PcBuilder.maxMonitores) {
			//throw new IllegalStateException("La PC debe tener un monitor y no más de " + PcBuilder.maxMonitores);
			return false;
		}
		if(contDiscos == 0 || contDiscos > PcBuilder.maxDiscos){
			//throw new IllegalStateException("La PC debe tener al menos un disco duro y no más de " + PcBuilder.maxDiscos);
			return false;
		}
		if(contTarjetas == 0 || contTarjetas > PcBuilder.maxTarjetas){
			//throw new IllegalStateException("La PC debe tener una tarjeta de video y no más de " + PcBuilder.maxTarjetas);
			return false;
		}
		return true;
	}
	
	public PcBuilder agregarTarjeta(Componente tarjeta) {
		if(tarjeta instanceof TarjetaVideo == false) {
			throw new IllegalArgumentException("No es una tarjeta de video válida. Componente enviado: " + tarjeta.getCategoria());
		}
		subcomponentes.add(tarjeta);
		contTarjetas++;
		return this;
	}
	
	public PcBuilder agregarMonitor(Componente monitor) {
		if(monitor instanceof Monitor == false) {
			throw new IllegalArgumentException("No es un monitor válido. Componente enviado: " + monitor.getCategoria());
		}
		subcomponentes.add(monitor);
		contMonitores++;
		return this;
	}
	
	public PcBuilder agregarDiscoDuro(Componente disco) {
		if(disco instanceof DiscoDuro == false) {
			throw new IllegalArgumentException("No es un disco duro válido. Componente enviado: " + disco.getCategoria());
		}
		subcomponentes.add(disco);
		contDiscos++;
		return this;
	}
	
	public Componente build() {
		
		if(esPcValida() == false) {
			throw new IllegalStateException("Estructura Pc Invalida [" + this.toString() + "]");
		}
		return Componente.crearPc(this.id, this.descripcion, this.marca, this.modelo, this.subcomponentes);
	}
	
	@Override
	public String toString() {
		return "\nPcBuilder["
				+ "\n   monitores(" + this.contMonitores
				+ ") =" + this.subcomponentes.stream().filter(compI-> compI instanceof Monitor).map(comp-> comp.descripcion).toList()
				+ "\n   tarjetas(" + this.contTarjetas
				+ ") =" + this.subcomponentes.stream().filter(compI-> compI instanceof TarjetaVideo).map(comp-> comp.descripcion).toList() 
				+ "\n   discos(" + this.contDiscos
				+ ") =" + this.subcomponentes.stream().filter(compI-> compI instanceof DiscoDuro).map(comp-> comp.descripcion).toList() 
				+ "\n   idPc=" + this.id
				+ ", descripcionPc=" + this.descripcion 
				+ ", marcaPc=" + this.marca 
				+ ", modeloPc=" + this.modelo + "]\n";
	}
}
