package mx.com.qtx.cotizadorv1ds.promos;

public abstract class PromAcumulable extends Promocion{
	
	protected Promocion promoBase;
	
	public PromAcumulable(String descripcion, String nombre, Promocion promoBase) {
		super(descripcion, nombre);
		this.promoBase = promoBase;
	}

}
