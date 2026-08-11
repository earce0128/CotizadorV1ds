package mx.com.qtx.cotizadorv1ds;
import java.math.BigDecimal;

abstract public class Componente {
    protected String id;
    protected String descripcion;
    protected String marca;
    protected String modelo;
    protected BigDecimal costo;
    protected BigDecimal precioBase;
    
    public Componente(String id, String descripcion, String marca, String modelo, BigDecimal costo,	BigDecimal precioBase) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.marca = marca;
		this.modelo = modelo;
		this.costo = costo;
		this.precioBase = precioBase;
	}
    
    private BigDecimal calcularPrecioDefault(int cantidad, Componente componente) {
    	//System.out.println("Precio sin promoción");
    	return componente.getPrecioBase().multiply(BigDecimal.valueOf(cantidad));
    }
    
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
}