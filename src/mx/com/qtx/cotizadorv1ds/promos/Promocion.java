package mx.com.qtx.cotizadorv1ds.promos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Promocion {
	
	private String descripcion;
	private String nombre;
	protected String cveProm;
	protected List<String> msgErrValidacion;
	
	private boolean esPromocionValida(String descripcion, String nombre) {
		if(descripcion == null || descripcion.trim().equals(""))
			this.msgErrValidacion.add("La descripcion es obligatoria");
		if(nombre == null || nombre.trim().equals(""))
			this.msgErrValidacion.add("El nombre es obligatorio");
		if(this.msgErrValidacion.size() > 0) return false;
		return true;
	}
	
	private static void mostrarElemEstructuraPromocion(Promocion prom) {
		if(prom instanceof PromBase) {
			System.out.println(prom.getClass().getSimpleName() + ": " + prom.getDescripcion() + " cveProm: " + prom.getCveProm());
		}
		else 
		if(prom instanceof PromAcumulable promAcum) {
			mostrarElemEstructuraPromocion(promAcum.promoBase);
			System.out.println(prom.getClass().getSimpleName() + ": " + prom.getDescripcion() + " cveProm: " + prom.getCveProm());
		}
	}
	
	protected void validarCantYPrecioBase(int cant, BigDecimal precioBase) {
		if(cant <= 0) this.msgErrValidacion.add("La cantidad debe ser un valor positivo");
		if(precioBase.doubleValue() <= 0) this.msgErrValidacion.add("El precio base, debe ser positivo");
	}
	
	public Promocion(String descripcion, String nombre) {
		super();
		this.msgErrValidacion = new ArrayList<>();
		if(esPromocionValida(descripcion, nombre) == false) 
			throw new IllegalArgumentException("Promoción no válida: \n " + this.msgErrValidacion);
		this.descripcion = descripcion;
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getCveProm() {
		return cveProm;
	}

	public void setCveProm(String cveProm) {
		this.cveProm = cveProm;
	}

	public static Promocion crearPromocion(PromocionBuilder builder){
		
		Promocion promoBase = null;
		int tipoPromBase = builder.getTipoPromocionBase();
		switch(tipoPromBase) {
			case PromocionBuilder.PROM_BASE_SIN_DSCTO: 
				promoBase = new PromSinDescto();
				break;
			case PromocionBuilder.PROM_BASE_NXM: 
				promoBase = new PromNXM(builder.getN(), builder.getM());
				break;
			default:
				promoBase = new PromSinDescto();
		}
		
		Promocion promoAcum = promoBase;
		for(Float dsctoPlanoI:builder.getLstDsctosPlanos()) {
			Promocion promDeco = new PromDsctoPlano(promoAcum,dsctoPlanoI);
			promoAcum = promDeco;
		}
		for(Map<Integer,Double> mapDsctosI:builder.getLstMapsCantVsDscto()) {
			Promocion promDeco = new PromDsctoXCantidad(promoAcum, mapDsctosI);
			promoAcum = promDeco;
		}
		return promoAcum;
	}
	
	public static PromocionBuilder getBuilder() {
		return new PromocionBuilder();
	}
	
	public abstract BigDecimal calcularImportePromocion(int cant, BigDecimal precioBase);
	
	public static void mostrarEstructuraPromocion(Promocion prom) {
		System.out.println("\n---------------------------------------------------------------------------------------------");
		mostrarElemEstructuraPromocion(prom);
		System.out.println("---------------------------------------------------------------------------------------------\n");		
	}
	
}
