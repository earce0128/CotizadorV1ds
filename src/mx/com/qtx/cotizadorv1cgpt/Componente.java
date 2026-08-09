package mx.com.qtx.cotizadorv1cgpt;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Representa un componente que puede ser utilizado por el Cotizador.
 */
public class Componente {

    private String id;
    private String descripcion;
    private String marca;
    private String modelo;
    private BigDecimal costo;
    private BigDecimal precioBase;
    private String tipo;
    private int memoria;
    private String capacidadAlm;

    public Componente() {
    }

    public Componente(String id, String descripcion, String marca, String modelo,
                      BigDecimal costo, BigDecimal precioBase, String tipo,
                      int memoria, String capacidadAlm) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public BigDecimal getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(BigDecimal precioBase) {
        this.precioBase = precioBase;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getMemoria() {
        return memoria;
    }

    public void setMemoria(int memoria) {
        this.memoria = memoria;
    }

    public String getCapacidadAlm() {
        return capacidadAlm;
    }

    public void setCapacidadAlm(String capacidadAlm) {
        this.capacidadAlm = capacidadAlm;
    }

    /**
     * Muestra las características principales del componente.
     */
    public void mostrarCaracteristicas() {
        System.out.println("ID: " + id);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Costo: " + costo);
        System.out.println("Precio base: " + precioBase);
        System.out.println("Tipo: " + tipo);
        System.out.println("Memoria: " + memoria);
        System.out.println("Capacidad almacenamiento: " + capacidadAlm);
    }

    /**
     * Calcula la utilidad unitaria: precio de venta - costo.
     */
    public BigDecimal calcularUtilidad() {
        if (precioBase == null || costo == null) {
            return BigDecimal.ZERO;
        }
        return precioBase.subtract(costo).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return "Componente{" +
                "id='" + id + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", precioBase=" + precioBase +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
