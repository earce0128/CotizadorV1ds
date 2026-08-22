package mx.com.qtx.cotizadorv1ds;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import mx.com.qtx.cotizadorv1ds.componentes.Componente;

public class CotizadorConMap implements ICotizador{
  
    protected Map<Componente, Integer> componentes;

    public CotizadorConMap() {
		super();
		this.componentes = new HashMap<>();
	}
    
    @Override
    public void agregarComponente(int cant, Componente compK) {
    	
    	// Componente a agregar
    	//System.out.println("Componente a agregar: " + compK);
    	
    	// Busca si ya existe un componente con el mismo id, y en caso de encontrarlo, suma la cantidad, si no lo agrega
    	Componente compEncontrado = this.componentes.keySet()
                .stream()
                .filter(k->k.getId().equals(compK.getId()))
                .findFirst()
                .orElse(null);
    	
    	if(compEncontrado == null)
    		componentes.put(compK, cant);
    	else
     		componentes.put(compEncontrado, componentes.get(compEncontrado) + cant);
    }

    public void eliminarComponente(String idComponente) {
    	Componente compLlave = this.componentes.keySet()
                .stream()
                .filter(k->k.getId().equals(idComponente))
                .findFirst()
                .orElse(null);
    	
    	if(componentes != null)
    		this.componentes.remove(compLlave);
    }

	@Override
	public Cotizacion generarCotizacion() {
		//System.out.println("=== Cotización ===");
        BigDecimal total = new BigDecimal(0);
        
        // Se invoca a la cotización
        CotizacionFmtoB cotizacion = new CotizacionFmtoB();
        
        int i=0;
        for(Componente compI : this.componentes.keySet()) {
        	int cantidadI = this.componentes.get(compI);
        	BigDecimal importeCotizadoI = new BigDecimal(0);
        	
			importeCotizadoI = compI.cotizar(cantidadI);
			
			cotizacion.agregarDetalle((i+1), compI.getId(), compI.getDescripcion(), cantidadI, compI.getPrecioBase(), importeCotizadoI, compI.getCategoria());
			total = total.add(importeCotizadoI);
			i++;
        }
        
        cotizacion.setTotal(total);
        return cotizacion;
	}

	@Override
	public void listarComponentes() {
		System.out.println("=== Componentes a cotizar en CotizadorConMap ===");
        for(Componente compI:this.componentes.keySet())  {
        	int cantidad = this.componentes.get(compI);
            System.out.println(cantidad + " " + compI.getDescripcion() 
            		 + ": $" + compI.getPrecioBase() + " ID:" + compI.getId());        	
        }
	}
}