package mx.com.qtx.cotizadorv1ds.promos;

import java.math.BigDecimal;

public class PromNXM extends PromBase {
	
	private int lleveN;
	private int pagueM;
	
	private boolean esPromocionNXMValida(int n, int m) {
		if(n <= 0 || n <= m) this.msgErrValidacion.add("n:" + n + " debe ser un número positivo mayor que m");
		if(m <= 0 || m >= n) this.msgErrValidacion.add("m:" + m + " debe ser un número positivo menor que n");
		if(this.msgErrValidacion.size() > 0) return false;
		return true;
	}

	public PromNXM(int n, int m) {
		super(n + " X " + m, "Lleve " + n + ", pague " + m);
		if(this.esPromocionNXMValida(n, m) == false)
			throw new IllegalArgumentException("Promoción NxM no válida: \n " + this.msgErrValidacion);
		this.lleveN = n;
		this.pagueM = m;
	}
	
	public int getLleveN() {
		return lleveN;
	}

	public void setLleveN(int lleveN) {
		this.lleveN = lleveN;
	}

	public int getPagueM() {
		return pagueM;
	}

	public void setPagueM(int pagueM) {
		this.pagueM = pagueM;
	}

	@Override
	public BigDecimal calcularImportePromocion(int cant, BigDecimal precioBase) {
		
		this.validarCantYPrecioBase(cant, precioBase);
		if(this.msgErrValidacion.size() > 0) 
			throw new IllegalArgumentException("Error en calcularImportePromocion \n" + this.msgErrValidacion);
		
		// Calcular grupos completos de N unidades y unidades restantes
	    int gruposCompletos = cant / this.lleveN;
	    int unidadesRestantes = cant % this.lleveN;
	    
	    // Calcular total: (M * grupos) + restantes
	    BigDecimal total = precioBase
	        .multiply(BigDecimal.valueOf(gruposCompletos * this.pagueM + unidadesRestantes));
    
		return total;
	}
}
