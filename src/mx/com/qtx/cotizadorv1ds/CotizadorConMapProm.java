package mx.com.qtx.cotizadorv1ds;

import java.math.BigDecimal;

import mx.com.qtx.cotizadorv1ds.componentes.Componente;

public class CotizadorConMapProm extends CotizadorConMap {
	
	@Override
	public Cotizacion generarCotizacion() {
        BigDecimal total = new BigDecimal(0);
        
        // Se invoca a la cotización
        CotizacionFmtoBProm cotizacion = new CotizacionFmtoBProm();
        
        int i=0;
        for(Componente compI : this.componentes.keySet()) {
        	int cantidadI = this.componentes.get(compI);
        	BigDecimal importeCotizadoI = new BigDecimal(0);
        	
        	BigDecimal importeSinDescto = new BigDecimal(0);
        	BigDecimal importeDescto = new BigDecimal(0);
        	String cvePromocion = ""; 
        	
        	importeCotizadoI = compI.cotizar(cantidadI);
        	importeSinDescto = compI.getPrecioBase().multiply(new BigDecimal(cantidadI));
        	
        	// Analizando las promociones
        	if(compI.getPromo() != null) {
        		cvePromocion = compI.getPromo().getCveProm();
        		importeDescto = importeSinDescto.subtract(importeCotizadoI);
        	}
        	
			cotizacion.agregarDetalle((i+1), compI.getId(), compI.getDescripcion(), cantidadI, compI.getPrecioBase(), importeCotizadoI, 
					compI.getCategoria(), cvePromocion, importeSinDescto, importeDescto);
			total = total.add(importeCotizadoI);
			
			i++;
        }
        
        cotizacion.setTotal(total);
        return cotizacion;
	}
}
