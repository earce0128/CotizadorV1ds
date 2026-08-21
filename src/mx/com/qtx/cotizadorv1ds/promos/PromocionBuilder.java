package mx.com.qtx.cotizadorv1ds.promos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PromocionBuilder {
	
	// Config. de Máximos descuentos permitidos
	static final float MAX_DSCTO_PLANO = 20f;
	static final float MAX_DSCTO_CANTIDAD = 25f;
	
	// Config. manejo de mensajes de Error
	private List<String> msgPromErrValidacion;
	
	static final int PROM_BASE_SIN_DSCTO = 1;
	static final int PROM_BASE_NXM = 2;
	private int tipoPromocionBase;
	private int n;
	private int m;
	
	private List<Float> lstDsctosPlanos;
	private List<Map<Integer,Double>> lstMapsCantVsDscto;
	
	// Validaciones de la promoción
	private void validarPromBaseNXM(int n, int m) {
		if(n <= 0 || n <= m) this.msgPromErrValidacion.add("conPromocionBaseNXM-> n:" + n + " debe ser un número positivo mayor que m");
		if(m <= 0 || m >= n) this.msgPromErrValidacion.add("conPromocionBaseNXM-> m:" + m + " debe ser un número positivo menor que n");
	}
	
	private void validarPromDescPlano(float porcDscto) {
		if(porcDscto <= 0.0 || porcDscto > MAX_DSCTO_PLANO )
			this.msgPromErrValidacion.add("agregarDsctoPlano-> El valor del porcentaje de descuento [" + porcDscto + "] debe ser positivo y menor que " 
					+ MAX_DSCTO_PLANO  + "%");
	}
	
	private void validarDsctoXCantidad(Map<Integer,Double> mapCantVsDscto) {
		List<Integer> lstKeysDsctos = mapCantVsDscto.keySet()
					.stream()
					.sorted()
					.filter(dsctoI -> dsctoI < 0)
					.toList();
		List<Double> lstValDsctos = mapCantVsDscto.values()
						.stream()
						.sorted()
						.filter(dsctoI -> dsctoI < 0 || dsctoI > MAX_DSCTO_CANTIDAD )
						.toList();
		
		if(lstKeysDsctos.size() > 0) 
		this.msgPromErrValidacion.add("agregarDsctoXCantidad -> Las cantidades en la tabla de descuentos " + lstKeysDsctos + " no pueden ser negativas");
		if(lstValDsctos.size() > 0) 
		this.msgPromErrValidacion.add("agregarDsctoXCantidad -> Los porcentajes de descuento en la tabla " + lstValDsctos + 
				" no pueden ser negativos o mayores a "	+ MAX_DSCTO_CANTIDAD + "%");
	}
	
	public PromocionBuilder(){
		this.lstDsctosPlanos = new ArrayList<>();
		this.lstMapsCantVsDscto = new ArrayList<>();
		this.msgPromErrValidacion = new ArrayList<>();
	}

	public int getTipoPromocionBase() {
		return tipoPromocionBase;
	}

	public void setTipoPromocionBase(int tipoPromocionBase) {
		this.tipoPromocionBase = tipoPromocionBase;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public List<Float> getLstDsctosPlanos() {
		return lstDsctosPlanos;
	}

	public List<Map<Integer, Double>> getLstMapsCantVsDscto() {
		return lstMapsCantVsDscto;
	}

	public PromocionBuilder conPromocionBaseSinDscto(){
		this.tipoPromocionBase = PROM_BASE_SIN_DSCTO;
		return this;
	}
	
	public PromocionBuilder conPromocionBaseNXM(int n, int m){
		validarPromBaseNXM(n,m);
		this.tipoPromocionBase = PROM_BASE_NXM;
		this.n = n;
		this.m = m;
		return this;
	}
	
	public PromocionBuilder agregarDsctoPlano(float porcDscto){
		validarPromDescPlano(porcDscto);
		this.lstDsctosPlanos.add(porcDscto);
		return this;

	}

	public PromocionBuilder agregarDsctoXCantidad(Map<Integer,Double> mapCantVsDscto){
		validarDsctoXCantidad(mapCantVsDscto);
		this.lstMapsCantVsDscto.add(mapCantVsDscto);
		return this;

	}
	
	public Promocion build() {
		if(this.msgPromErrValidacion.size() > 0) 
			throw new IllegalArgumentException("Error de estructura en la Promoción \n" + this.msgPromErrValidacion + "\n " + this.toString());
		return Promocion.crearPromocion(this);
	}

	@Override
	public String toString() {
		return "PromocionBuilder [tipoPromocionBase=" + tipoPromocionBase + ", n=" + n + ", m=" + m
				+ ", lstDsctosPlanos=" + lstDsctosPlanos + ", lstMapsCantVsDscto=" + lstMapsCantVsDscto + "]";
	}
		
}