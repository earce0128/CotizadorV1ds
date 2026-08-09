package mx.com.qtx.cotizadorv1ds;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Componente {
    private String id;
    private String descripcion;
    private String marca;
    private String modelo;
    private BigDecimal costo;
    private BigDecimal precioBase;
    private String tipo;
    private String memoria;
    private String capacidadAlm;
    
    private List<Componente> subcomponentes;

    public Componente() {}
    
    public Componente(String id, String descripcion, String marca, String modelo, List<Componente> subcomponentes) {
    	super();
    	this.id = id;
		this.descripcion = descripcion;
		this.marca = marca;
		this.modelo = modelo;
		this.subcomponentes = subcomponentes;
    }
    
    public Componente(String id, String descripcion, String marca, String modelo, BigDecimal costo,
			BigDecimal precioBase, String tipo, String memoria, String capacidadAlm) {
		super();
		
		// Validar los tipos de componente
		if(tipo.equalsIgnoreCase("TarjetaVideo")) {
			if(memoria == null) {
				throw new IllegalArgumentException("Falta atributo memoria en una Tarjeta de Video");
			} else if(capacidadAlm != null) {
				throw new IllegalArgumentException("Una Tarjeta de Video no debe incluir atributo capacidad de almacenamiento");
			}
		} else if(tipo.equalsIgnoreCase("DiscoDuro")) {
			if(capacidadAlm == null) {
				throw new IllegalArgumentException("Falta atributo capacidadAlm en un " + tipo);
			} else if(memoria != null) {
				throw new IllegalArgumentException("Un Disco Duro no debe incluir atributo memoria");
			}
		} else {
			if(memoria != null) {
				throw new IllegalArgumentException("Un(a) " + tipo + " no debe incluir atributo memoria");
			} else if(capacidadAlm != null) {
				throw new IllegalArgumentException("Un(a) " + tipo + " no debe incluir atributo capacidadAlm");
			}
		}
		
		this.id = id;
		this.descripcion = descripcion;
		this.marca = marca;
		this.modelo = modelo;
		this.costo = costo;
		this.precioBase = precioBase;
		this.tipo = tipo;
		this.memoria = memoria;
		this.capacidadAlm = capacidadAlm;
	}
    
	// Setters
    public void setId(String id) { this.id = id; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setCosto(BigDecimal costo) { this.costo = costo; }
    public void setPrecioBase(BigDecimal precioBase) { this.precioBase = precioBase; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setMemoria(String memoria) { this.memoria = memoria; }
    public void setCapacidadAlm(String capacidadAlm) { this.capacidadAlm = capacidadAlm; }

    // Getters
    public String getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public BigDecimal getCosto() { return costo; }
    public BigDecimal getPrecioBase() { return precioBase; }
    public String getTipo() { return tipo; }
    public String getMemoria() { return memoria; }
    public String getCapacidadAlm() { return capacidadAlm; }
    public List<Componente> getSubcomponentes() { return subcomponentes; }
    
    public static Componente crearPC(String id, String descripcion, String marca, String modelo,
    		Componente disco1, Componente disco2, Componente monitor, Componente tarjeta) {
    	
    	// Validaciones ...
    	if(disco1 == null) {
    		throw new IllegalArgumentException("Una PC debe tener al menos un Disco Duro");
    	}
    	if(monitor == null) {
    		throw new IllegalArgumentException("Una PC debe tener un Monitor");
    	}
    	if(tarjeta == null) {
    		throw new IllegalArgumentException("Una PC debe tener una Tarjeta de Video");
    	}
    	
    	//Crear agregado
    	List<Componente> componentesPc = new ArrayList<>();
    	componentesPc.add(disco1);
    	componentesPc.add(disco2);
    	componentesPc.add(monitor);
    	componentesPc.add(tarjeta);
    	
    	Componente pc = new Componente(id,descripcion,marca,modelo,componentesPc);
    	pc.setTipo("PC");
    	
    	//Calculando el costo por los subcomponentes
    	BigDecimal costo = disco1.costo.add((disco2 == null) ? new BigDecimal(0) : disco2.costo)
    							  .add(monitor.costo)
    							  .add(tarjeta.costo);
    	pc.setCosto(costo);
    	
    	//Calculando el precioBase con los subcomponentes
    	BigDecimal precioBase = disco1.precioBase.add((disco2 == null) ? new BigDecimal(0) : disco2.precioBase)
				  								 .add(monitor.precioBase)
				  								 .add(tarjeta.precioBase);
    	pc.setPrecioBase(precioBase);
    	
    	return pc;
    }

    public void mostrarCaracteristicas() {
        System.out.println("ID: " + this.id);
        System.out.println("Descripción: " + this.descripcion);
        System.out.println("Marca: " + this.marca);
        System.out.println("Modelo: " + this.modelo);
        System.out.println("Costo: $" + this.costo);
        System.out.println("Precio Base: $" + this.precioBase);
        System.out.println("Tipo: " + this.tipo);
        System.out.println("Memoria: " + this.memoria);
        System.out.println("Capacidad Almacenamiento: " + this.capacidadAlm);
        System.out.println("Utilidad: " + this.calcularUtilidad());
        
        // Mostrando los subcomponentes de una PC
        if(this.tipo.equalsIgnoreCase("PC")) {
        	System.out.println("\nComponentes anidados: ------------------------");
        	this.subcomponentes.stream()
        						.filter(compI -> compI != null)
        						.forEach(compI-> compI.mostrarCaracteristicas());
        }
    }

    public BigDecimal calcularUtilidad() {
        return precioBase.subtract(costo);
    }
}