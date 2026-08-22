package mx.com.qtx.cotizadorv1ds;

import java.math.BigDecimal;

public class DetalleCotizacionProm extends DetalleCotizacion {
	
	private String cveProm;
	private BigDecimal importeSinDscto;
	private BigDecimal descuento;
	
	public DetalleCotizacionProm(int numDetalle, String idComponente, String descripcion, int cantidad,
			BigDecimal precioBase, BigDecimal importeCotizado, String categoria, String cveProm, BigDecimal importeSinDscto, BigDecimal descuento) {
		super(numDetalle, idComponente, descripcion, cantidad, precioBase, importeCotizado, categoria);
		this.cveProm = cveProm;
		this.descuento = descuento;
		this.importeSinDscto = importeSinDscto;
	}

	public String getCveProm() {
		return cveProm;
	}

	public void setCveProm(String cveProm) {
		this.cveProm = cveProm;
	}
	
	public BigDecimal getImporteSinDscto() {
		return importeSinDscto;
	}

	public void setImporteSinDscto(BigDecimal importeSinDscto) {
		this.importeSinDscto = importeSinDscto;
	}

	public BigDecimal getDescuento() {
		return descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}
}
