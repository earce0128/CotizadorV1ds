package mx.com.qtx.cotizadorv1ds;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public class CotizacionFmtoBProm extends Cotizacion{
	
	private Map<Integer,DetalleCotizacionProm> detalles;
	
	public CotizacionFmtoBProm() {
		super();
		this.detalles = new TreeMap<>();
	}

	public void agregarDetalle(int numDetalle, String idComponente, String descripcion, int cantidad, BigDecimal precioBase, BigDecimal importeCotizado,
			String categoria, String cvePromocion, BigDecimal impSinDscto, BigDecimal descuento) {
		DetalleCotizacionProm det = new DetalleCotizacionProm(numDetalle, idComponente, descripcion, cantidad,precioBase, importeCotizado, categoria, 
				cvePromocion, impSinDscto, descuento);
		this.detalles.put(det.getNumDetalle(), det);
	}

	@Override
	public void emitirComoReporte() {
		System.out.println("=".repeat(145));
		System.out.println("Cotizacion número:" + this.num );
		System.out.println("Fecha:" + this.fecha );
		System.out.println("=".repeat(145));
		System.out.printf("%5s %-10s %-15s %-30s %-25s    %-12s  %-12s  %-12s  %-12s\n\n","#", "Cantidad", "Id", "Descripcion", "Promoción Aplicada", 
				"Precio Base", "Subtotal", "Descuento", "Total"  );
		
		for(Integer k:this.detalles.keySet()) {
			desplegarLineaCotizacion(this.detalles.get(k));
		}
		
		System.out.printf("\n%145s","$" + String.format("%10.2f",this.getTotal()));
		
	}
	
	protected void desplegarLineaCotizacion(DetalleCotizacionProm detI) {
		System.out.printf("%5d     %4d  %-15s %-30s %-25s    $%10.2f   $%10.2f   $%10.2f   $%10.2f\n", detI.getNumDetalle(), detI.getCantidad(), 
				detI.getIdComponente(),	detI.getDescripcion(), detI.getCveProm(), detI.getPrecioBase(), detI.getImporteSinDscto(), detI.getDescuento(), 
				detI.getImporteCotizado());
	}
	
}
