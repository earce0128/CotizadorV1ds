package mx.com.qtx.cotizadorv1ds.componentes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PcBuilder {
	
	private static int MIN_MONITORES = 1;
	private static int MAX_MONITORES = 2;
	private static int MIN_TARJETAS = 1;
	private static int MAX_TARJETAS = 2;
	private static int MIN_DISCOS = 1;
	private static int MAX_DISCOS = 2;
	
	private List<String> msgErrPcValidacion;
	
	private String idPc;
	private String descripcionPc;
	private String marcaPc;
	private String modeloPc;
	
	private List<Componente> subcomponentes;
	
	private int contDiscosPc = 0;
	private int contMonitoresPc = 0;
	private int contTarjetasPc = 0;
	
	public PcBuilder() {
		super();
		this.subcomponentes = new ArrayList<>();
	}
	
	private boolean esPcValida() {
		if(idPc == null || idPc.trim().equals(""))
    		msgErrPcValidacion.add("El id es obligatorio");
    	if(descripcionPc == null || descripcionPc.trim().equals(""))
    		msgErrPcValidacion.add("La descripcion es obligatoria");
    	if(marcaPc == null || marcaPc.trim().equals(""))
    		msgErrPcValidacion.add("La marca es obligatoria");
    	if(modeloPc == null || modeloPc.trim().equals(""))
    		msgErrPcValidacion.add("El modelo es obligatorio");
		if(contMonitoresPc < MIN_MONITORES || contMonitoresPc > MAX_MONITORES)
			msgErrPcValidacion.add("La PC debe tener mínimo " + MIN_MONITORES + " y máximo " + MAX_MONITORES + " monitores");
		if(contDiscosPc < MIN_DISCOS || contDiscosPc > MAX_DISCOS)
			msgErrPcValidacion.add("La PC debe tener mínimo " + MIN_DISCOS + " y máximo " + MAX_DISCOS + " discos");
		if(contTarjetasPc < MIN_TARJETAS || contTarjetasPc > MAX_TARJETAS)
			msgErrPcValidacion.add("La PC debe tener mínimo " + MIN_TARJETAS + " y máximo " + MAX_TARJETAS + " tarjetas");
		if(msgErrPcValidacion.size() > 0) return false;
		return true;
	}
	
	private static int obtenerCantComponentesNoPermitidos(List<Componente> subcomponentes) {
		return (int)subcomponentes.stream()
				.filter(compI -> !(compI instanceof Monitor))
				.filter(compI -> !(compI instanceof TarjetaVideo))
				.filter(compI -> !(compI instanceof DiscoDuro))
				.count();
	}
	
	private static List<Componente> obtenerComponentesNoPermitidos(List<Componente> subcomponentes) {
		return subcomponentes.stream()
				.filter(compI -> !(compI instanceof Monitor))
				.filter(compI -> !(compI instanceof TarjetaVideo))
				.filter(compI -> !(compI instanceof DiscoDuro))
				.toList();
	}
	
	protected static List<String> validarSubcomponentesPc(List<Componente> subcomponentesPc) {

		int cantCompNoPermitidos = obtenerCantComponentesNoPermitidos(subcomponentesPc);
		List<Componente> lstCompNoPermitidos = obtenerComponentesNoPermitidos(subcomponentesPc);
		
		List<String> msgErrPcCompNoValidos = new ArrayList<>();
		
		if(cantCompNoPermitidos > 0) {
			msgErrPcCompNoValidos.add("Una PC solo debe tener Discos Duros, Monitores y Tarjetas de Video " 
							+ "\n ** Componentes no permitidos encontrados " +  cantCompNoPermitidos + " **"
							+ "\n " + lstCompNoPermitidos);
		}
		return msgErrPcCompNoValidos;
	}
	
	protected static int getMinMonitores() {
		return MIN_MONITORES;
	}
	
	protected static int getMaxMonitores() {
		return MAX_MONITORES;
	}
	
	protected static int getMinTarjetas() {
		return MIN_TARJETAS;
	}
	
	protected static int getMaxTarjetas() {
		return MAX_TARJETAS;
	}
	
	protected static int getMinDisco() {
		return MIN_DISCOS;
	}
	
	protected static int getMaxDiscos() {
		return MAX_DISCOS;
	}
	
	protected String getIdPc() {
		return this.idPc;
	}
	
	protected String getDescripcionPc() {
		return this.descripcionPc;
	}
	
	protected String getMarcaPc() {
		return this.marcaPc;
	}
	
	protected String getModeloPc() {
		return this.modeloPc;
	}
	
	public PcBuilder definirId(String idPc) {
		this.idPc = idPc;
		return this;
	}
	
	public PcBuilder definirDescripcion(String descripcionPc) {
		this.descripcionPc = descripcionPc;
		return this;
	}
	
	public PcBuilder definirMarcaYModelo(String marcaPc, String modeloPc) {
		this.marcaPc = marcaPc;
		this.modeloPc = modeloPc;
		return this;
	}
	
	public PcBuilder agregarTarjeta(String id, String descripcion, String marca, String modelo, BigDecimal costo,
			BigDecimal precioBase, String memoria) {
		try {
			subcomponentes.add(Componente.crearTarjetaVideo(id, descripcion, marca, modelo, costo, precioBase, memoria));
			contTarjetasPc++;
		} catch(IllegalArgumentException iaex) {
			throw new IllegalArgumentException("Err PcBuider: No es una tarjeta de video válida. -> " + iaex.getMessage() + this.toString());
		}
		return this;
	}
	
	public PcBuilder agregarMonitor(String id, String descripcion, String marca, String modelo, BigDecimal costo, BigDecimal precioBase) {
		try {
			subcomponentes.add(Componente.crearMonitor(id, descripcion, marca, modelo, costo, precioBase));
			contMonitoresPc++;
		} catch(IllegalArgumentException iaex) {
			throw new IllegalArgumentException("Err PcBuider: No es un monitor válido. -> " + iaex.getMessage() + this.toString());
		}
		return this;
	}
	
	public PcBuilder agregarDiscoDuro(String id, String descripcion, String marca, String modelo, BigDecimal costo, 
			BigDecimal precioBase, String capAlmacenamiento) {
		try {
			subcomponentes.add(Componente.crearDiscoDuro(id, descripcion, marca, modelo, costo, precioBase, capAlmacenamiento));
			contDiscosPc++;
		} catch(IllegalArgumentException iaex) {
			throw new IllegalArgumentException("Err PcBuilder: No es un disco duro válido. -> " + iaex.getMessage() + this.toString());
		}
		return this;
	}
	
	public Componente build() {
		this.msgErrPcValidacion = new ArrayList<>();
		if(esPcValida() == false) {
			throw new IllegalStateException("Estructura Pc Invalida \n" + this.msgErrPcValidacion + this.toString());
		}
		return Componente.crearPc(this.idPc, this.descripcionPc, this.marcaPc, this.modeloPc, this.subcomponentes);
	}
	
	@Override
	public String toString() {
		return "\nPcBuilder["
				+ "\n   monitores(" + this.contMonitoresPc
				+ ") =" + this.subcomponentes.stream().filter(compI-> compI instanceof Monitor).toList()
				+ "\n   tarjetas(" + this.contTarjetasPc
				+ ") =" + this.subcomponentes.stream().filter(compI-> compI instanceof TarjetaVideo).toList() 
				+ "\n   discos(" + this.contDiscosPc
				+ ") =" + this.subcomponentes.stream().filter(compI-> compI instanceof DiscoDuro).toList()
				+ "\n   idPc=" + this.idPc
				+ ", descripcionPc=" + this.descripcionPc 
				+ ", marcaPc=" + this.marcaPc 
				+ ", modeloPc=" + this.modeloPc + "]\n";
	}
}
