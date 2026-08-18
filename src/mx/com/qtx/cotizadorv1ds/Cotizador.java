package mx.com.qtx.cotizadorv1ds;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import mx.com.qtx.cotizadorv1ds.componentes.Componente;

public class Cotizador implements ICotizador{

	private List<Integer> cantidades = new ArrayList<>();
    private List<Componente> componentes = new ArrayList<>();
    
    /*
    private void desplegarLineaCotizacion(Componente componente, int cantidad, BigDecimal importeCotizador) {
    	System.out.println(String.format("%3d", cantidad) + " "
    			+ String.format("%-20s", componente.getDescripcion())
    			+ " Tipo " + String.format("%-15s", componente.getClass().getSimpleName())
    			+ " con precio base de $" + String.format("%8.2f", componente.getPrecioBase())
    			+ " cuesta(n) " + String.format("%8.2f", importeCotizador));
	}
	*/
    
    @Override
    public void agregarComponente(int cantidad, Componente componente) {
        this.cantidades.add(cantidad);
        this.componentes.add(componente);
    }

    @Override
    public void eliminarComponente(String idComponente) {
        int i = this.componentes.stream()
        						.map(compI -> compI.getId())
        						.toList()
        						.indexOf(idComponente);
        if(i == -1) // NO existe
        	return;
        this.cantidades.remove(i);
        this.componentes.remove(i);
    }

    @Override
    public Cotizacion generarCotizacion() {
        //System.out.println("=== Cotización ===");
        BigDecimal total = new BigDecimal(0);
        
        // Se invoca a la cotización
        Cotizacion cotizacion = new Cotizacion();
        
        for (int i=0; i<this.cantidades.size(); i++) {
			Componente compI = this.componentes.get(i);
			int cantidadI = this.cantidades.get(i);
			BigDecimal importeCotizadoI = new BigDecimal(0);
			
			importeCotizadoI = compI.cotizar(cantidadI);
			
			cotizacion.agregarDetalle((i+1), compI.getId(), compI.getDescripcion(), cantidadI, compI.getPrecioBase(), importeCotizadoI, compI.getCategoria());
			total = total.add(importeCotizadoI);
		}
        cotizacion.setTotal(total);
        //System.out.println("Total: $" + String.format("%8.2f",total));
        return cotizacion;
    }

    @Override
	public void listarComponentes() {
    	System.out.println("=== Componentes a cotizar ===");
    	for (int i=0; i < this.cantidades.size(); i++) {
			Componente c = this.componentes.get(i);
			System.out.println(this.cantidades.get(i) + " " + c.getDescripcion() + ": $" + c.getPrecioBase() + " ID:" + c.getId());
		}
    }
}