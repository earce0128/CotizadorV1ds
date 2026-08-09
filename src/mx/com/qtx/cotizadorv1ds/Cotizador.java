package mx.com.qtx.cotizadorv1ds;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cotizador {
    private float porcPrecioAgregado = 20;
    private int cantMinDsctoA = 3;
    private int cantMaxDsctoA = 5;
    private float dsctoA = 5;
    private int cantMinDsctoB = 6;
    private int cantMaxDsctoB = 999999;
    private float dsctoB = 10;

    private List<Integer> cantidades = new ArrayList<>();
    private List<Componente> componentes = new ArrayList<>();
    
    private void desplegarLineaCotizacion(Componente componente, int cantidad, BigDecimal importeCotizador) {
    	System.out.println(String.format("%3d", cantidad) + " "
    			+ String.format("%-20s", componente.getDescripcion())
    			+ " Tipo " + String.format("%-15s", componente.getTipo())
    			+ " con precio base de $" + String.format("%8.2f", componente.getPrecioBase())
    			+ " cuesta(n) " + String.format("%8.2f", importeCotizador));
	}
    
    // Métodos de cálculo de precios (ejemplo básico)
    private BigDecimal calcularPrecioPromocion3x2(int cantidad, Componente componente) {
    	// Obtener el precio base del componente
    	BigDecimal precioBase = componente.getPrecioBase();
    	
    	// Calcular grupos completos de 3 unidades y unidades restantes
    	int gruposCompletos = cantidad / 3;
    	int unidadesRestantes = cantidad % 3;
    	
    	// Calcular total: (2* grupos) + restantes
    	BigDecimal total = precioBase.multiply(BigDecimal.valueOf(gruposCompletos * 2L + unidadesRestantes));
    	
    	return total;
    }
    
    private BigDecimal calcularPrecioComponenteAgregado(int cantidad, Componente componente) {
    	BigDecimal total = BigDecimal.ZERO;
    	for(Componente c : componente.getSubcomponentes()) {
    		if(c == null)
    			continue;
    		total = total.add(c.getPrecioBase());
    	}
        return total.multiply(BigDecimal.valueOf(1 - (porcPrecioAgregado / 100)));
    }

    private BigDecimal calcularPrecioConDsctoXCantidad(int cantidad, Componente componente) {
    	BigDecimal precioConDscto = new BigDecimal(0);
    	precioConDscto = componente.getPrecioBase().multiply(new BigDecimal(cantidad));
    	if(cantidad >= this.cantMinDsctoA && cantidad <= this.cantMaxDsctoA) {
    		precioConDscto = precioConDscto.multiply(new BigDecimal(1-(this.dsctoA/100)));
    	} else if(cantidad >= this.cantMinDsctoB && cantidad <= this.cantMaxDsctoB) {
    		precioConDscto = precioConDscto.multiply(new BigDecimal(1-(this.dsctoB/100)));
    	}
    	return precioConDscto;
    }
    
    private BigDecimal calcularPrecioDefault(int cantidad, Componente componente) {
    	return componente.getPrecioBase().multiply(BigDecimal.valueOf(cantidad));
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
			
			switch(compI.getTipo()) {
			case "TarjetaVideo" :
				importeCotizadorI = this.calcularPrecioPromocion3x2(cantidadI, compI);
				break;
			case "PC":
				importeCotizadorI = this.calcularPrecioComponenteAgregado(cantidadI, compI);
				break;
			case "Monitor":
				importeCotizadorI = this.calcularPrecioConDsctoXCantidad(cantidadI, compI);
				break;
			default:
				importeCotizadorI = this.calcularPrecioDefault(cantidadI, compI);
			}
			
			desplegarLineaCotizacion(compI, cantidadI, importeCotizadorI);
			total = total.add(importeCotizadorI);
		}
    }

	public void listarComponentes() {
    	System.out.println("=== Componentes a cotizar ===");
    	for (int i=0; i < this.cantidades.size(); i++) {
			Componente c = this.componentes.get(i);
			System.out.println(this.cantidades.get(i) + " " + c.getDescripcion() + ": $" + c.getPrecioBase() + " ID:" + c.getId());
		}
    }
}