package mx.com.qtx.cotizadorv1ds.componentes;

import java.math.BigDecimal;

public abstract class ComponenteSimple extends Componente {
	
	protected void validarComponenteSimple(BigDecimal costo, BigDecimal precioBase) {
		if(costo == null || costo.doubleValue() <= 0)
    		this.msgErrValidacion.add("El costo debe ser un valor positivo");
    	if(precioBase == null || precioBase.doubleValue() <= 0 || precioBase.doubleValue() <= costo.doubleValue())
    		this.msgErrValidacion.add("El precio debe un valor positivo y mayor que el costo");
    }

	public ComponenteSimple(String id, String descripcion, String marca, String modelo, BigDecimal costo, BigDecimal precioBase) {
		super(id, descripcion, marca, modelo, costo, precioBase);
		validarComponenteSimple(costo, precioBase);
	}
}
