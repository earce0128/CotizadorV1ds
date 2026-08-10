package mx.com.qtx.cotizadorv1ds;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cotizador {

	private List<Integer> cantidades = new ArrayList<>();
    private List<Componente> componentes = new ArrayList<>();
    
    private void desplegarLineaCotizacion(Componente componente, int cantidad, BigDecimal importeCotizador) {
    	System.out.println(String.format("%3d", cantidad) + " "
    			+ String.format("%-20s", componente.getDescripcion())
    			+ " Tipo " + String.format("%-15s", componente.getClass().getSimpleName())
    			+ " con precio base de $" + String.format("%8.2f", componente.getPrecioBase())
    			+ " cuesta(n) " + String.format("%8.2f", importeCotizador));
	}
    
    public void agregarComponente(int cantidad, Componente componente) {
        this.cantidades.add(cantidad);
        this.componentes.add(componente);
    }

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

    public void emitirCotizacion() {
        System.out.println("=== Cotización ===");
        BigDecimal total = new BigDecimal(0);
        
        for (int i=0; i<this.cantidades.size(); i++) {
			Componente compI = this.componentes.get(i);
			int cantidadI = this.cantidades.get(i);
			BigDecimal importeCotizadorI = new BigDecimal(0);
			
			importeCotizadorI = compI.cotizar(cantidadI);
			
			desplegarLineaCotizacion(compI, cantidadI, importeCotizadorI);
			total = total.add(importeCotizadorI);
		}
        System.out.println("Total: $" + String.format("%8.2f",total));
    }

	public void listarComponentes() {
    	System.out.println("=== Componentes a cotizar ===");
    	for (int i=0; i < this.cantidades.size(); i++) {
			Componente c = this.componentes.get(i);
			System.out.println(this.cantidades.get(i) + " " + c.getDescripcion() + ": $" + c.getPrecioBase() + " ID:" + c.getId());
		}
    }
}