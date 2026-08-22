package mx.com.qtx.cotizadorv1ds;

import java.math.BigDecimal;

public class DetalleCotizacion {
	protected int numDetalle;
	protected String idComponente;
	protected String descripcion;
	protected int cantidad;
	protected BigDecimal precioBase;
	protected BigDecimal importeCotizado;
	protected String categoria;
	
	public DetalleCotizacion(int numDetalle, String idComponente, String descripcion, int cantidad,
			BigDecimal precioBase, BigDecimal importeCotizado, String categoria) {
		super();
		this.numDetalle = numDetalle;
		this.idComponente = idComponente;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.precioBase = precioBase;
		this.importeCotizado = importeCotizado;
		this.categoria = categoria;
	}

	public int getNumDetalle() {
		return numDetalle;
	}

	public void setNumDetalle(int numDetalle) {
		this.numDetalle = numDetalle;
	}

	public String getIdComponente() {
		return idComponente;
	}

	public void setIdComponente(String idComponente) {
		this.idComponente = idComponente;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(BigDecimal precioBase) {
		this.precioBase = precioBase;
	}

	public BigDecimal getImporteCotizado() {
		return importeCotizado;
	}

	public void setImporteCotizado(BigDecimal importeCotizado) {
		this.importeCotizado = importeCotizado;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
}
