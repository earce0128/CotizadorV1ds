package mx.com.qtx.cotizadorv1ds.componentes;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

abstract public class Componente {
    
	protected String id;
    protected String descripcion;
    protected String marca;
    protected String modelo;
    protected BigDecimal costo;
    protected BigDecimal precioBase;
    protected List<String> msgErrValidacion;
    
    public Componente(String id, String descripcion, String marca, String modelo, BigDecimal costo,	BigDecimal precioBase) {
		super();
		this.msgErrValidacion = new ArrayList<>();
		this.validarAtributosComponente(id, descripcion, marca, modelo, costo, precioBase);
		if(this.msgErrValidacion.size() == 0) {
			this.id = id;
			this.descripcion = descripcion;
			this.marca = marca;
			this.modelo = modelo;
			this.costo = costo;
			this.precioBase = precioBase;
		}
	}
    
    private BigDecimal calcularPrecioDefault(int cantidad, Componente componente) {
    	return componente.getPrecioBase().multiply(BigDecimal.valueOf(cantidad));
    }
    
    protected void validarAtributosComponente(String id, String descripcion, String marca, String modelo, BigDecimal costo, BigDecimal precioBase) {
    	if(id == null || id.trim().equals(""))
    		this.msgErrValidacion.add("El id es obligatorio");
    	if(descripcion == null || descripcion.trim().equals(""))
    		this.msgErrValidacion.add("La descripcion es obligatoria");
    	if(marca == null || marca.trim().equals(""))
    		this.msgErrValidacion.add("La marca es obligatoria");
    	if(modelo == null || modelo.trim().equals(""))
    		this.msgErrValidacion.add("El modelo es obligatorio");
    	if(costo == null)
    		this.msgErrValidacion.add("El costo es obligatorio");
    	if(precioBase == null)
    		this.msgErrValidacion.add("El precioBase es obligatorio");
    };
    
	// Setters
    public void setId(String id) { this.id = id; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setCosto(BigDecimal costo) { this.costo = costo; }
    public void setPrecioBase(BigDecimal precioBase) { this.precioBase = precioBase; }

    // Getters
    public String getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public BigDecimal getCosto() { return costo; }
    public BigDecimal getPrecioBase() { return precioBase; }
    
    public BigDecimal cotizar(int cantidad) {
    	return this.calcularPrecioDefault(cantidad, this);
    }
    
    public void mostrarCaracteristicas() {
    	System.out.println("\n=== Componente Características ===");
    	System.out.println("ID: " + this.id);
        System.out.println("Descripción: " + this.descripcion);
        System.out.println("Marca: " + this.marca);
        System.out.println("Modelo: " + this.modelo);
        System.out.println("Costo: $" + this.costo);
        System.out.println("Precio Base: $" + this.precioBase);
        System.out.println("Categoria: " + this.getCategoria());
        System.out.println("Utilidad: " + this.calcularUtilidad());
    }

    public BigDecimal calcularUtilidad() {
        return precioBase.subtract(costo);
    }
    
    abstract public String getCategoria();
    
    // Métodos de creación de diversos componentes (Factory)
    public static Componente crearDiscoDuro(String id, String descripcion, String marca, String modelo, BigDecimal costo,
											BigDecimal precioBase, String capacidadAlm) {
    	return new DiscoDuro(id,descripcion,marca,modelo,costo,precioBase,capacidadAlm);
    }
    
    public static Componente crearTarjetaVideo(String id, String descripcion, String marca, String modelo, BigDecimal costo, 
    		BigDecimal precioBase, String memoria) {
    	return new TarjetaVideo(id, descripcion, marca, modelo, costo, precioBase, memoria);
    }
    
    public static Componente crearMonitor(String id, String descripcion, String marca, String modelo, BigDecimal costo, BigDecimal precioBase) {
    	return new Monitor(id,descripcion,marca,modelo,costo,precioBase);
    }
    
    public static Componente crearPc(String id, String descripcion, String marca, String modelo, List<Componente> subcomponentes) {
    	if(subcomponentes == null) throw new IllegalArgumentException("Pc no válida: \n Debe tener subcomponentes");
    	
    	List<String> lstCompNoValidos = PcBuilder.validarSubcomponentesPc(subcomponentes);
    	
    	if(lstCompNoValidos.size() > 0) throw new IllegalArgumentException("Pc no válida: \n " + lstCompNoValidos);
    	
    	List<ComponenteSimple> lstDispositivos = subcomponentes.stream()
					.filter(compI -> (compI instanceof ComponenteSimple))
					.map(compI -> (ComponenteSimple)compI)
					.toList();

    	return new Pc(id,descripcion,marca,modelo,lstDispositivos);
    }
    
    public List<String> getMsgErrValidacion(){
    	return this.msgErrValidacion; 
    }
    
    public static PcBuilder getPcBuilder() {
		return new PcBuilder();
	}
    
    @Override
	public String toString() {
		return "Componente [id=" + id + ", categoria=" + getCategoria() + ", descripcion=" + descripcion + ", marca=" + marca + ", modelo=" + modelo
				+ ", costo=" + costo + ", precioBase=" + precioBase + "]";
	}
    
}