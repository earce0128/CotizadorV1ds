package mx.com.qtx.cotizadorv1ds.promos;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import mx.com.qtx.cotizadorv1ds.Cotizacion;
import mx.com.qtx.cotizadorv1ds.CotizadorConMap;
import mx.com.qtx.cotizadorv1ds.ICotizador;
import mx.com.qtx.cotizadorv1ds.componentes.Componente;

public class PromocionTest {
	
	private static Map<Integer, Double> mapDsctos = Map.of(0,  0.0,
														   3,  5.0,
														   6, 10.0,
														   9, 15.0);
	
	private static Map<Integer, Double> mapDsctosError = Map.of(0,  0.0,
			   													-3,  5.0,
			   													7, -10.0,
			   													6, 30.0);
	
	public static void main(String[] args) {
		testPromoSinDscto();
		testPromoSinDsctoConErrorEnCantYPrecio();
		testPromNXM();
		testPromNXMConErrores();
		testPromBaseSinDsctoYDsctPlano();
		testPromBaseSinDsctoYDsctPlanoConError();
		testPromMXMYDsctPlano();
		testPromMXMYDsctPlanoConError();
		testPromBaseSinDsctoYDsctoXCant();
		testPromBaseSinDsctoYDsctoXCantConError();
		testPromMXMYDsctXCant();
		testPromMXMYDsctXCantConError();
		testPromBuilder();
		testPromBuilderConError();
		testEmitirCotizacionConPromociones();
	}
	
	private static Promocion getPromo_SinDscto() {
		Promocion promo = new PromSinDescto();
		return promo;
	}
	
	private static Promocion getPromo_3x2() {
		Promocion promo = new PromNXM(3, 2);
		return promo;
	}
	
	private static Promocion getPromo_2x1_mas_5_mas_10() {
		Promocion promo = new PromDsctoPlano(
								new PromDsctoPlano(
										new PromNXM(2,1), 
										5.0f),
								10.0f);
		return promo;
	}
	
	private static void testPromoSinDscto() {
		System.out.println("\n ***** testPromoSinDscto *****");
		Promocion promoSinDscto = getPromo_SinDscto();
		Promocion.mostrarEstructuraPromocion(promoSinDscto);
		
		System.out.println(" ==== Cálculo del importe ====");
		int cantidad = 2;
		BigDecimal precioBase= new BigDecimal("500.00");
		double importeCalculado = promoSinDscto.calcularImportePromocion(cantidad, precioBase).doubleValue();
		System.out.println("\n Cant: " + cantidad + "\n Precio Base: " + precioBase + "\n Importe calculado: " + importeCalculado);
	}
	
	private static void testPromoSinDsctoConErrorEnCantYPrecio() {
		System.out.println("\n ***** testPromoSinDsctoConErrorEnCantYPrecio *****");
		try {
			Promocion promoSinDscto = getPromo_SinDscto();
			Promocion.mostrarEstructuraPromocion(promoSinDscto);
			
			System.out.println(" ==== Cálculo del importe ====");
			int cantidad = -2;
			BigDecimal precioBase= new BigDecimal("-500.00");
			double importeCalculado = promoSinDscto.calcularImportePromocion(cantidad, precioBase).doubleValue();
			System.out.println("\n Cant: " + cantidad + "\n Precio Base: " + precioBase + "\n Importe calculado: " + importeCalculado);
		} catch(IllegalArgumentException iaex) {
			System.err.println("Error correcto en testPromoSinDsctoConErrorEnCantYPrecio " + iaex.getMessage());
		}
	}
	
	private static void testPromNXM() {
		System.out.println("\n ***** testPromNXM *****");
		Promocion promo3x2 = getPromo_3x2();
		Promocion.mostrarEstructuraPromocion(promo3x2);
		
		System.out.println(" ==== Cálculo del importe ====");
		int cantidad = 6;
		BigDecimal precioBase= new BigDecimal("1000.00");
		double importeCalculado = promo3x2.calcularImportePromocion(cantidad, precioBase).doubleValue();
		System.out.println("\n Cant: " + cantidad + "\n Precio Base: " + precioBase + "\n Importe calculado: " + importeCalculado);
		
		System.out.println("\n ***** testPromNXM 2x1 + 5 + 10 *****");
		Promocion prom2x1_5_10 =  getPromo_2x1_mas_5_mas_10();
		Promocion.mostrarEstructuraPromocion(prom2x1_5_10);
		
		System.out.println(" ==== Cálculo del importe ====");
		cantidad = 8;
		precioBase= new BigDecimal("1000.00");
		importeCalculado = prom2x1_5_10.calcularImportePromocion(cantidad, precioBase).doubleValue();
		System.out.println("\n Cant: " + cantidad + "\n Precio Base: " + precioBase + "\n Importe calculado: " + importeCalculado);
	}

	private static void testPromNXMConErrores() {
		System.out.println("\n ***** testPromNXMConErrores *****");
		int n = -1;
		int m = 0;
		
		try {
			Promocion promErr = new PromNXM(n, m);
			Promocion.mostrarEstructuraPromocion(promErr);
		} catch(IllegalArgumentException iaex) {
			System.err.println("Error correcto en testPromNXMConErrores \n" + iaex.getMessage());
		}
	}
	
	private static void testPromBaseSinDsctoYDsctPlano() {
		System.out.println("\n ***** testPromBaseSinDsctoYDsctPlano *****");
		
		try {
			Promocion promSnDscto = getPromo_SinDscto();
			Promocion promDescAcum5 = new PromDsctoPlano(promSnDscto, 5);
			Promocion.mostrarEstructuraPromocion(promDescAcum5);
			
			System.out.println(" ==== Cálculo del importe ====");
			int cantidad = 2;
			BigDecimal precioBase= new BigDecimal("500.00");
			double importeCalculado = promDescAcum5.calcularImportePromocion(cantidad, precioBase).doubleValue();
			System.out.println("\n Cant: " + cantidad + "\n Precio Base: " + precioBase + "\n Importe calculado: " + importeCalculado);
			
		} catch(IllegalArgumentException iaex) {
			System.err.println("Error en testPromBaseSinDsctoYDsctPlano " + iaex.getMessage());
		}
	}
		
	private static void testPromBaseSinDsctoYDsctPlanoConError() {
		System.out.println("\n ***** testPromBaseSinDsctoYDsctPlano *****");
		
		try {
			Promocion promSnDscto = getPromo_SinDscto();
			Promocion promDescAcum25 = new PromDsctoPlano(promSnDscto, 25);
			Promocion.mostrarEstructuraPromocion(promDescAcum25);
			
			System.out.println(" ==== Cálculo del importe ====");
			int cantidad = 2;
			BigDecimal precioBase= new BigDecimal("500.00");
			double importeCalculado = promDescAcum25.calcularImportePromocion(cantidad, precioBase).doubleValue();
			System.out.println("\n Cant: " + cantidad + "\n Precio Base: " + precioBase + "\n Importe calculado: " + importeCalculado);
			
		} catch(IllegalArgumentException iaex) {
			System.err.println("Error correcto en testPromBaseSinDsctoYDsctPlanoConError " + iaex.getMessage());
		}
	}
	
	private static void testPromMXMYDsctPlano() {
		System.out.println("\n ***** testPromMXMYDsctPlano *****");
		
		try {
			Promocion prom3x2 = getPromo_3x2();
			Promocion promDescAcum5 = new PromDsctoPlano(prom3x2, 5);
			Promocion.mostrarEstructuraPromocion(promDescAcum5);
			
			System.out.println(" ==== Cálculo del importe ====");
			int cantidad = 4;
			BigDecimal precioBase= new BigDecimal("1000.00");
			double importeCalculado = promDescAcum5.calcularImportePromocion(cantidad, precioBase).doubleValue();
			System.out.println("\n Cant: " + cantidad + "\n Precio Base: " + precioBase + "\n Importe calculado: " + importeCalculado);
			
		} catch(IllegalArgumentException iaex) {
			System.err.println("Error en testPromMXMYDsctPlano " + iaex.getMessage());
		}
	}
		
	private static void testPromMXMYDsctPlanoConError() {
		System.out.println("\n ***** testPromMXMYDsctPlanoConError *****");
		
		try {
			Promocion prom3x2 = getPromo_3x2();
			Promocion promDescAcum25 = new PromDsctoPlano(prom3x2, 25);
			Promocion.mostrarEstructuraPromocion(promDescAcum25);
			
			System.out.println(" ==== Cálculo del importe ====");
			int cantidad = 4;
			BigDecimal precioBase= new BigDecimal("1000.00");
			double importeCalculado = promDescAcum25.calcularImportePromocion(cantidad, precioBase).doubleValue();
			System.out.println("\n Cant: " + cantidad + "\n Precio Base: " + precioBase + "\n Importe calculado: " + importeCalculado);
			
		} catch(IllegalArgumentException iaex) {
			System.err.println("Error correcto en testPromMXMYDsctPlanoConError " + iaex.getMessage());
		}
	}
	
	private static void testPromBaseSinDsctoYDsctoXCant() {
		System.out.println("\n ***** testPromBaseSinDsctoYDsctoXCant *****");
		
		try {
			Promocion promSnDscto = getPromo_SinDscto();
			Promocion promDescXCant = new PromDsctoXCantidad(promSnDscto, mapDsctos);
			Promocion.mostrarEstructuraPromocion(promDescXCant);
			
			System.out.println(" ==== Cálculo del importe ====");
			int cantidad = 5;
			BigDecimal precioBase= new BigDecimal("1000.00");
			double importeCalculado = promDescXCant.calcularImportePromocion(cantidad, precioBase).doubleValue();
			System.out.println("\n Cant: " + cantidad + "\n Precio Base: " + precioBase + "\n Importe calculado: " + importeCalculado);
			
		} catch(IllegalArgumentException iaex) {
			System.err.println("Error en testPromBaseSinDsctoYDsctoXCant " + iaex.getMessage());
		}
	}
		
	private static void testPromBaseSinDsctoYDsctoXCantConError() {
		System.out.println("\n ***** testPromBaseSinDsctoYDsctoXCantConError *****");
		
		try {
			Promocion promSnDscto = getPromo_SinDscto();
			Promocion promDescXCant = new PromDsctoXCantidad(promSnDscto, mapDsctosError);
			Promocion.mostrarEstructuraPromocion(promDescXCant);
			
			System.out.println(" ==== Cálculo del importe ====");
			int cantidad = 5;
			BigDecimal precioBase= new BigDecimal("500.00");
			double importeCalculado = promDescXCant.calcularImportePromocion(cantidad, precioBase).doubleValue();
			System.out.println("\n Cant: " + cantidad + "\n Precio Base: " + precioBase + "\n Importe calculado: " + importeCalculado);
			
		} catch(IllegalArgumentException iaex) {
			System.err.println("Error correcto en testPromBaseSinDsctoYDsctoXCantConError " + iaex.getMessage());
		}
	}
	
	private static void testPromMXMYDsctXCant() {
		System.out.println("\n ***** testPromMXMYDsctXCant *****");
		
		try {
			Promocion prom3x2 = getPromo_3x2();
			Promocion prom3x2_DescXCant = new PromDsctoXCantidad(prom3x2, mapDsctos);
			Promocion.mostrarEstructuraPromocion(prom3x2_DescXCant);
			
			System.out.println(" ==== Cálculo del importe ====");
			int cantidad = 4;
			BigDecimal precioBase= new BigDecimal("1000.00");
			double importeCalculado = prom3x2_DescXCant.calcularImportePromocion(cantidad, precioBase).doubleValue();
			System.out.println("\n Cant: " + cantidad + "\n Precio Base: " + precioBase + "\n Importe calculado: " + importeCalculado);
			
		} catch(IllegalArgumentException iaex) {
			System.err.println("Error en testPromMXMYDsctXCant " + iaex.getMessage());
		}
	}
		
	private static void testPromMXMYDsctXCantConError() {
		System.out.println("\n ***** testPromMXMYDsctXCantConError *****");
		
		try {
			Promocion prom3x2 = getPromo_3x2();
			Promocion prom3x2_DsctoXCant = new PromDsctoXCantidad(prom3x2, mapDsctosError);
			Promocion.mostrarEstructuraPromocion(prom3x2_DsctoXCant);
			
			System.out.println(" ==== Cálculo del importe ====");
			int cantidad = 4;
			BigDecimal precioBase= new BigDecimal("1000.00");
			double importeCalculado = prom3x2_DsctoXCant.calcularImportePromocion(cantidad, precioBase).doubleValue();
			System.out.println("\n Cant: " + cantidad + "\n Precio Base: " + precioBase + "\n Importe calculado: " + importeCalculado);
			
		} catch(IllegalArgumentException iaex) {
			System.err.println("Error correcto en testPromMXMYDsctXCantConError " + iaex.getMessage());
		}
	}
	
	private static void testPromBuilder() {
		System.out.println("\n ***** testPromBuilder *****");
		try {
			PromocionBuilder promoBuilder = Promocion.getBuilder();
			
			Promocion promo = promoBuilder.conPromocionBaseNXM(3,2)
			            				  .agregarDsctoPlano(5.0f)
			            				  .agregarDsctoPlano(10f)
			            				  .agregarDsctoXCantidad(mapDsctos)
			            				  .build();
			
			Promocion.mostrarEstructuraPromocion(promo);
			
			System.out.println(" ==== Cálculo del importe ====");
			int cantidad = 7;
			BigDecimal precioBase= new BigDecimal("1000.00");
			double importeCalculado = promo.calcularImportePromocion(cantidad, precioBase).doubleValue();
			System.out.println("\n Cant: " + cantidad + "\n Precio Base: " + precioBase + "\n Importe calculado: " + importeCalculado);
			
			
		} catch(IllegalArgumentException iaex) {
			System.err.println("Error en testPromBuilder " + iaex.getMessage());
		}
	}
	
	private static void testPromBuilderConError() {
		System.out.println("\n ***** testPromBuilderConError *****");
		try {
			PromocionBuilder promoBuilder = Promocion.getBuilder();
			
			Promocion promo = promoBuilder.conPromocionBaseNXM(-3,-2)
			            				  .agregarDsctoPlano(0.0f)
			            				  .agregarDsctoPlano(30f)
			            				  .agregarDsctoXCantidad(mapDsctosError)
			            				  .build();
			
			Promocion.mostrarEstructuraPromocion(promo);
			
			System.out.println(" ==== Cálculo del importe ====");
			int cantidad = 7;
			BigDecimal precioBase= new BigDecimal("1000.00");
			double importeCalculado = promo.calcularImportePromocion(cantidad, precioBase).doubleValue();
			System.out.println("\n Cant: " + cantidad + "\n Precio Base: " + precioBase + "\n Importe calculado: " + importeCalculado);
			
		} catch(IllegalArgumentException iaex) {
			System.err.println("Error correcto en testPromBuilderConError " + iaex.getMessage());
		}
	}
	
	public static void testEmitirCotizacionConPromociones() {
		System.out.println("\n ***** testEmitirCotizacionConPromociones *****");
        try {
			// Crear instancia del Cotizador
			ICotizador cotizador = getCotizadorActual();
			
			// Crear algunos componentes
			Componente disco = Componente.crearDiscoDuro("D001",
					   "Disco Seagate",
					   "Tech XYZ",
					   "X200",
					   new BigDecimal("150.00"),
					   new BigDecimal("200.00"),
					   "1TB");
			Componente tarjeta = Componente.crearTarjetaVideo("TV001",
					   "Tarjeta ABC",
					   "GraphicBrand",
					   "G100",
					   BigDecimal.valueOf(300.00),
					   BigDecimal.valueOf(400.00),
					   "8GB");
			Componente monitor = Componente.crearMonitor("M00T",
						"Monitor 17 pulgadas",
						"Sony",
						"Z9000",
						new BigDecimal("1000.00"),
						new BigDecimal("2000.00"));
			Componente discoPc = Componente.crearDiscoDuro("D00Y",
					   "Disco Seagate",
					   "Tech XYZ",
					   "X200",
					   new BigDecimal("1880.00"),
					   new BigDecimal("2000.00"),
					   "1TB");
			Componente monitorPc = Componente.crearMonitor("M00X",
					"Monitor 17 pulgadas",
					"Sony",
					"Z9000",
					new BigDecimal("1200.00"),
					new BigDecimal("3000.00"));
			Componente tarjetaPc = Componente.crearTarjetaVideo("C005",
				   "Tarjeta XYZ",
				   "TechBrand",
				   "X200",
				   BigDecimal.valueOf(150.00),
				   BigDecimal.valueOf(1000.00),
				   "16GB");
			Componente miPc = Componente.crearPc("PC0001", "Laptop 15000 s300", "Dell", "Terminator", List.of(discoPc, monitorPc, tarjetaPc));
			
			// Creación con PcBuilder
			Componente miPc2 = Componente.getPcBuilder()
				.definirId("PC0002")
				.definirDescripcion("Laptop PC Builder")
				.definirMarcaYModelo("Dell", "Terminator")
				.agregarDiscoDuro("D00Y","Disco Seagate","Tech XYZ","X200",new BigDecimal("1880.00"),new BigDecimal("2000.00"),"1TB")
				.agregarMonitor("M00X","Monitor 17 pulgadas","Sony","Z9000",new BigDecimal("1800.00"),new BigDecimal("3000.00"))
				.agregarTarjeta("C005","Tarjeta XYZ","TechBrand","X200",BigDecimal.valueOf(150.00),BigDecimal.valueOf(1000.00),"16GB")
				.build();

			// Definiendo promociones
			Promocion prom2x1 = Promocion.getBuilder()
					.conPromocionBaseNXM(3, 2)
					.build();
			Promocion.mostrarEstructuraPromocion(prom2x1);
			
			Promocion promDescPlano20 = Promocion.getBuilder()
					.conPromocionBaseSinDscto()
					.agregarDsctoPlano(20.0f)
					.build();
			Promocion.mostrarEstructuraPromocion(promDescPlano20);
			
			Promocion promDescXCant = Promocion.getBuilder()
					.conPromocionBaseSinDscto()
					.agregarDsctoXCantidad(mapDsctos)
					.build();
			Promocion.mostrarEstructuraPromocion(promDescXCant);
			
			Promocion prom4x3_mas_15_mas_5 = Promocion.getBuilder()
					.conPromocionBaseNXM(4, 3)
					.agregarDsctoPlano(15.0f)
					.agregarDsctoPlano(5.0f)
					.build();
			Promocion.mostrarEstructuraPromocion(prom4x3_mas_15_mas_5);
			
			// Asignado promociones a componentes
			tarjeta.setPromo(prom2x1);
			miPc.setPromo(promDescPlano20);
			monitor.setPromo(promDescXCant);
			disco.setPromo(prom4x3_mas_15_mas_5);
			
			System.out.println("=== Agregar componentes ===");
			cotizador.agregarComponente(10, disco);
			cotizador.agregarComponente(5, tarjeta);
			cotizador.agregarComponente(10, monitor);
			cotizador.agregarComponente(1, miPc);
			cotizador.agregarComponente(2, miPc2);
			
			// Prueba: Generar cotización
			Cotizacion cot = cotizador.generarCotizacion(); // Mostrar cotización actual
			cot.emitirComoReporte();
        } catch(Exception ex) {
			System.err.println("Prueba incorrecta de testEmitirCotizacionConPCBuilder " + ex.getMessage());
        }
	}
	
	public static ICotizador getCotizadorActual() {
		return new CotizadorConMap();
		//return new Cotizador();
	}

}