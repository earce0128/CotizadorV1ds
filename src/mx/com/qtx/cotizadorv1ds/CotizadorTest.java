package mx.com.qtx.cotizadorv1ds;
import java.math.BigDecimal;
import java.util.List;

import mx.com.qtx.cotizadorv1ds.componentes.Componente;

public class CotizadorTest {
    
	public static void main(String[] args) {
        testComponenteDiscoDuro();
        testComponenteDiscoDuroConErrores();
        testComponenteDiscoDuroSinCapAlm();
        testComponenteTarjetaVideo();
        testComponenteTarjetaVideoConErrores();
        testComponentetestComponenteTarjetaVideoSinMemoria();
        testComponenteMonitor();
        testComponenteMonitorConErrores();
        testCreacionPC();
        testCreacionPCSinSubcomponentes();
        testCreacionPCConErroresEnParametros();
        testCreacionPCConErroresEnSubcomponentes();
        testCreacionPCConErroresEnSubcomponentesNoPermitidos();
        testPCBuilder();
        testPCBuilderConErroresEnSubComponentes();
        testPCBuilderConErroresEnParametros();
        testAgregarComponentes();
        testEliminarComponentes();
        testEmitirCotizacion();
        testEmitirCotizacionConPCBuilder();
    }

	// Al ser una clase abstracta, ya no puede ser instanciada.
	/*
	public static void testCreacionComponente() {
		Componente c1 = new Componente("C001",
    			"Tarjeta XYZ",
    			"TechBrand",
    			"X200",
    			BigDecimal.valueOf(150.00),
    			BigDecimal.valueOf(200.00));
    	c1.mostrarCaracteristicas();
    }
    */
	
	public static void testComponenteDiscoDuro() {
		System.out.println("\n ***** testComponenteDiscoDuro *****");
		try {
			// Creando un disco duro
			Componente c1 = Componente.crearDiscoDuro("D001",
	    			"Disco Seagate",
	    			"Tech XYZ",
	    			"X200",
	    			new BigDecimal("1880.00"),
	    			new BigDecimal("2000.00"),
	    			"1TB");
	    	c1.mostrarCaracteristicas();
	    	
	    	//Calculando Cotizador
	    	int cantidad = 10;
	    	System.out.println("\nCotizador Disco Duro para " + cantidad + " elementos es: $" + c1.cotizar(cantidad).floatValue());
		} catch(IllegalArgumentException iaex) {
			System.err.println("Prueba incorrecta en testComponenteDiscoDuro \n" + iaex.getMessage());
		}
    }
	
	public static void testComponenteDiscoDuroConErrores() {
		System.out.println("\n ***** testComponenteDiscoDuroConErrores *****");
		try {
			// Creando un disco duro
			Componente c1 = Componente.crearDiscoDuro(null,	null, "", "", new BigDecimal(-12.00), null, "");
	    	c1.mostrarCaracteristicas();
	    	
	    	//Calculando Cotizador
	    	int cantidad = 10;
	    	System.out.println("\nCotizador Disco Duro para " + cantidad + " elementos es: $" + c1.cotizar(cantidad).floatValue());
		} catch(IllegalArgumentException iaex) {
			System.err.println("Prueba correcta en testComponenteDiscoDuroConErrores \n" + iaex.getMessage());
		}
    }
	
	public static void testComponenteDiscoDuroSinCapAlm() {
		System.out.println("\n ***** testComponenteDiscoDuroSinCamAlm *****");
		try {
			// Creando un disco duro
			Componente c1 = Componente.crearDiscoDuro("D001",
	    			"Disco Seagate",
	    			"Tech XYZ",
	    			"X200",
	    			new BigDecimal("1880.00"),
	    			new BigDecimal("2000.00"),
	    			null);
	    	c1.mostrarCaracteristicas();
		} catch(IllegalArgumentException iaex) {
			System.err.println("Prueba correcta en testComponenteDiscoDuroSinCapAlm: \n" + iaex.getMessage());
		}
    }
	
	public static void testComponenteTarjetaVideo() {
		System.out.println("\n ***** testComponenteTarjetaVideo *****");
		try {
			// Creando una tarjeta de video
			Componente c1 = Componente.crearTarjetaVideo("TV001",
					   "Tarjeta XYZ",
					   "TechBrand",
					   "X200",
					   BigDecimal.valueOf(150.00),
					   BigDecimal.valueOf(200.00),
					   "16GB");
	    	c1.mostrarCaracteristicas();
	    	
	    	//Calculando Cotizador
	    	int cantidad = 5;
	    	System.out.println("\nCotizador Tarjeta de Video para " + cantidad + " elementos es: $" + c1.cotizar(cantidad).floatValue());
		} catch(IllegalArgumentException iaex) {
			System.err.println("Prueba incorrecta de testComponenteTarjetaVideo " + iaex.getMessage());
		}
	}
	
	public static void testComponenteTarjetaVideoConErrores() {
		System.out.println("\n ***** testComponenteTarjetaVideoConErrores *****");
		try {
			// Creando tarjeta de video erronea
			Componente c1 = Componente.crearTarjetaVideo(null,	null, "", "", new BigDecimal(-12.00), null, "");
	    	c1.mostrarCaracteristicas();
	    	
	    	//Calculando Cotizador
	    	int cantidad = 5;
	    	System.out.println("\nCotizador Tarjeta de Video para " + cantidad + " elementos es: $" + c1.cotizar(cantidad).floatValue());
		} catch(IllegalArgumentException iaex) {
			System.err.println("Prueba correcta en testComponenteTarjetaVideoConErrores \n" + iaex.getMessage());
		}
    }
	
	private static void testComponentetestComponenteTarjetaVideoSinMemoria() {
		System.out.println("\n ***** testComponentetestComponenteTarjetaVideoSinMemoria *****");
		try {
			// Creando una tarjeta de video
			Componente c1 = Componente.crearTarjetaVideo("TV001",
					   "Tarjeta XYZ",
					   "TechBrand",
					   "X200",
					   BigDecimal.valueOf(150.00),
					   BigDecimal.valueOf(200.00),
					   null);
	    	c1.mostrarCaracteristicas();
	    	
	    	//Calculando Cotizador
	    	int cantidad = 5;
	    	System.out.println("\nCotizador Tarjeta de Video para " + cantidad + " elementos es: $" + c1.cotizar(cantidad).floatValue());
		} catch(IllegalArgumentException iaex) {
			System.err.println("Prueba correcta en testComponentetestComponenteTarjetaVideoSinMemoria \n" + iaex.getMessage());
		}
	}
	
	public static void testComponenteMonitor() {
		System.out.println("\n ***** testComponenteMonitor *****");
		try {
			// Creando un monitor
			Componente c1 = Componente.crearMonitor("M001",
					"Monitor 17 pulgadas",
					"Sony",
					"Z9000",
					new BigDecimal("3200.00"),
					new BigDecimal("6000.00"));
	    	c1.mostrarCaracteristicas();
	    	
	    	//Calculando Cotizador
	    	int cantidad = 6;
	    	System.out.println("\nCotizador Monitor para " + cantidad + " elementos es: $" + c1.cotizar(cantidad).floatValue());
		} catch(IllegalArgumentException iaex) {
			System.err.println("Prueba incorrecta de testComponenteMonitor " + iaex.getMessage());
		}
	}
	
	public static void testComponenteMonitorConErrores() {
		System.out.println("\n ***** testComponenteMonitorConErrores *****");
		try {
			// Creando un monitor
			Componente c1 = Componente.crearMonitor("",	null, "", null,	null, new BigDecimal("-100.00"));
	    	c1.mostrarCaracteristicas();
	    	
	    	//Calculando Cotizador
	    	int cantidad = 6;
	    	System.out.println("\nCotizador Monitor para " + cantidad + " elementos es: $" + c1.cotizar(cantidad).floatValue());
		} catch(IllegalArgumentException iaex) {
			System.err.println("Prueba correcta de testComponenteMonitor " + iaex.getMessage());
		}
	}
	
	private static void testCreacionPC() {
		System.out.println("\n ***** testCreacionPC *****");
		try {
			// Creando comoponentes de una Pc
			Componente disco1 = Componente.crearDiscoDuro("D001",
	    									   "Disco Seagate",
	    									   "Tech XYZ",
	    									   "X200",
	    									   new BigDecimal("1880.00"),
	    									   new BigDecimal("2000.00"),
	    									   "1TB");
			Componente monitor = Componente.crearMonitor("M001",
	    									 "Monitor 17 pulgadas",
	    									 "Sony",
	    									 "Z9000",
	    									 new BigDecimal("3200.00"),
	    									 new BigDecimal("6000.00"));
			Componente tarjeta = Componente.crearTarjetaVideo("TV001",
	    								   		  "Tarjeta XYZ",
	    								   		  "TechBrand",
	    								   		  "X200",
	    								   		  BigDecimal.valueOf(150.00),
	    								   		  BigDecimal.valueOf(200.00),
	    								   		  "16GB");
			Componente miPc = Componente.crearPc("PC001", "Laptop 1500 s300", "Dell", "Terminator", List.of(disco1,	monitor, tarjeta)); 
			miPc.mostrarCaracteristicas();
			
			// Calculando Cotizador
	    	int cantidad = 1;
	    	System.out.println("\nCotizador PC para " + cantidad + " elementos es: $" + miPc.cotizar(cantidad).floatValue());
			
		} catch(IllegalArgumentException iaex) {
			System.out.println("Prueba incorrecta de testCreacionPC " + iaex.getMessage());
		}
	}
	
	private static void testCreacionPCSinSubcomponentes() {
		System.out.println("\n ***** testCreacionPCSinSubcomponentes *****");
		try {
			Componente miPc = Componente.crearPc(null, "", "", null, null); //List.of(tarjeta,disco1,monitor) 
			miPc.mostrarCaracteristicas();
			
			// Calculando Cotizador
	    	int cantidad = 1;
	    	System.out.println("\nCotizador PC para " + cantidad + " elementos es: $" + miPc.cotizar(cantidad).floatValue());
			
		} catch(IllegalArgumentException iaex) {
			System.err.println("Prueba correcta de testCreacionPCSinSubcomponentes " + iaex.getMessage());
		}
		
	}
	
	private static void testCreacionPCConErroresEnParametros() {
		System.out.println("\n ***** testCreacionPCConErroresEnParametros *****");
		try {
			// Creando comoponentes de una Pc
			Componente disco1 = Componente.crearDiscoDuro("D001",
	    									   "Disco Seagate",
	    									   "Tech XYZ",
	    									   "X200",
	    									   new BigDecimal("1880.00"),
	    									   new BigDecimal("2000.00"),
	    									   "1TB");
			Componente monitor = Componente.crearMonitor("M001",
	    									 "Monitor 17 pulgadas",
	    									 "Sony",
	    									 "Z9000", 
	    									 new BigDecimal("3200.00"),
	    									 new BigDecimal("6000.00"));
			Componente tarjeta = Componente.crearTarjetaVideo("TV001",
	    								   		  "Tarjeta XYZ",
	    								   		  "TechBrand",
	    								   		  "X200",
	    								   		  BigDecimal.valueOf(150.00),
	    								   		  BigDecimal.valueOf(200.00),
	    								   		  "16GB");
			Componente miPc = Componente.crearPc(null, "", "", null, List.of(tarjeta,disco1,monitor));  
			miPc.mostrarCaracteristicas();
			
			// Calculando Cotizador
	    	int cantidad = 1;
	    	System.out.println("\nCotizador PC para " + cantidad + " elementos es: $" + miPc.cotizar(cantidad).floatValue());
			
		} catch(IllegalArgumentException iaex) {
			System.err.println("Prueba correcta de testCreacionPCConErroresEnParametros " + iaex.getMessage());
		}
		
	}
	
	private static void testCreacionPCConErroresEnSubcomponentes() {
		System.out.println("\n ***** testCreacionPCConErroresEnSubcomponentes *****");
		try {
			// Creando comoponentes de una Pc
			Componente disco = Componente.crearDiscoDuro("D001",
	    									   "Disco Seagate",
	    									   "Tech XYZ",
	    									   "X200",
	    									   new BigDecimal("1880.00"),
	    									   new BigDecimal("2000.00"),
	    									   "1TB");
			Componente monitor = Componente.crearMonitor("M001",
	    									 "Monitor 17 pulgadas",
	    									 "Sony",
	    									 "Z9000", 
	    									 new BigDecimal("3200.00"),
	    									 new BigDecimal("6000.00"));
			Componente tarjeta = Componente.crearTarjetaVideo("TV001",
	    								   		  "Tarjeta XYZ",
	    								   		  "TechBrand",
	    								   		  "X200",
	    								   		  BigDecimal.valueOf(150.00),
	    								   		  BigDecimal.valueOf(200.00),
	    								   		  "16GB");
			Componente miPc = Componente.crearPc("PC001", "Laptop 1500 s300", "Dell", "Terminator", 
					List.of(tarjeta,tarjeta,tarjeta,disco,monitor,monitor,disco, disco, monitor));  
			miPc.mostrarCaracteristicas();
			
			// Calculando Cotizador
	    	int cantidad = 1;
	    	System.out.println("\nCotizador PC para " + cantidad + " elementos es: $" + miPc.cotizar(cantidad).floatValue());
			
		} catch(IllegalArgumentException iaex) {
			System.err.println("Prueba correcta de testCreacionPCConErroresEnSubcomponentes " + iaex.getMessage());
		}
		
	}
		
	private static void testCreacionPCConErroresEnSubcomponentesNoPermitidos() {
		System.out.println("\n ***** testCreacionPCConErroresEnSubcomponentesNoPermitidos *****");
		try {
			// Creando comoponentes de una Pc
			Componente disco1 = Componente.crearDiscoDuro("D001",
	    									   "Disco Seagate",
	    									   "Tech XYZ",
	    									   "X200",
	    									   new BigDecimal("1880.00"), 
	    									   new BigDecimal("2000.00"),
	    									   "1TB"); 
			Componente monitor = Componente.crearMonitor("M001",
	    									 "Monitor 17 pulgadas",
	    									 "Sony",
	    									 "Z9000",
	    									 new BigDecimal("3200.00"),
	    									 new BigDecimal("6000.00"));
			Componente tarjeta = Componente.crearTarjetaVideo("TV001",
	    								   		  "Tarjeta XYZ",
	    								   		  "TechBrand",
	    								   		  "X200",
	    								   		  BigDecimal.valueOf(150.00),
	    								   		  BigDecimal.valueOf(200.00),
	    								   		  "16GB");
			
			Componente c = Componente.crearPc("COMP-01", "Componente no permitido", "Marca no permitida", "Modelo", List.of(monitor,disco1,tarjeta));
			Componente miPc = Componente.crearPc("PC001", "Laptop 1500 s300", "Dell", "Terminator", List.of(monitor,c,disco1,tarjeta,c)); 
			c.mostrarCaracteristicas();
			miPc.mostrarCaracteristicas();
		} catch(IllegalArgumentException iaex) {
			System.err.println("Prueba correcta de testCreacionPCConErroresEnSubcomponentesNoPermitidos " + iaex.getMessage());
		}
	}
	
	private static void testPCBuilder() {
		System.out.println("\n ***** testPCBuilder *****");
		try {
			Componente miPc = Componente.getPcBuilder()
					.definirId("PC001")
					.definirDescripcion("Laptop 1500 s300")
					.definirMarcaYModelo("Dell","Terminator")
					.agregarMonitor("M001","Monitor 17 pulgadas","Sony","Z9000",new BigDecimal("3200.00"),new BigDecimal("6000.00"))
					.agregarDiscoDuro("D001","Disco Seagate","Tech XYZ","X200", new BigDecimal("1880.00"), new BigDecimal("2000.00"),"1TB")
					.agregarTarjeta("TV001","Tarjeta XYZ","TechBrand","X200",BigDecimal.valueOf(150.00),BigDecimal.valueOf(200.00),"16GB")
					.agregarDiscoDuro("D001","Disco Seagate","Tech XYZ","X200", new BigDecimal("1880.00"), new BigDecimal("2000.00"),"1TB")
					.agregarMonitor("M001","Monitor 17 pulgadas","Sony","Z9000",new BigDecimal("3200.00"),new BigDecimal("6000.00"))
					.build();
			
			miPc.mostrarCaracteristicas();
			
			
			// Obteniendo subcomponentes por tipo
			System.out.println("==== Subcomponentes usasndo getters de PC ====");
			System.out.println("-- Monitores --");
			
			//System.out.println("\n " + miPc.ge );
			
			// Calculando Cotizador
	    	int cantidad = 1;
	    	System.out.println("\nCotizador PC para " + cantidad + " elementos es: $" + miPc.cotizar(cantidad).floatValue());
					
		} catch(IllegalArgumentException iaex) {
			System.err.println("Error en la creación de la PC: " + iaex.getMessage());
		} catch(IllegalStateException isex) {
			System.err.println("Error en la creación de la PC: " + isex.getMessage());
		}
	}
	
	private static void testPCBuilderConErroresEnSubComponentes() {
		System.out.println("\n ***** testPCBuilderConErroresEnSubComponentes *****");
		try {
			Componente miPc = Componente.getPcBuilder()
					.definirId("PC001")
					.definirDescripcion("Laptop 1500 s300")
					.definirMarcaYModelo("Dell","Terminator")
					.agregarMonitor("M001","Monitor 17 pulgadas","Sony","Z9000",new BigDecimal("3200.00"),new BigDecimal("6000.00"))
					.agregarDiscoDuro("D001","Disco Seagate","Tech XYZ","X200", new BigDecimal("1880.00"), new BigDecimal("2000.00"),"1TB")
					.agregarTarjeta("TV001",null,"","X200",BigDecimal.valueOf(150.00),BigDecimal.valueOf(200.00),"16GB")
					.agregarDiscoDuro("D001","Disco Seagate","Tech XYZ","X200", new BigDecimal("1880.00"), new BigDecimal("2000.00"),"1TB")
					.agregarMonitor("M001","Monitor 17 pulgadas","Sony","Z9000",new BigDecimal("3200.00"),new BigDecimal("6000.00"))
					.build();
			
			miPc.mostrarCaracteristicas();
			
			// Calculando Cotizador
	    	int cantidad = 1;
	    	System.out.println("\nCotizador PC para " + cantidad + " elementos es: $" + miPc.cotizar(cantidad).floatValue());
					
		} catch(IllegalArgumentException iaex) {
			System.err.println("Prueba correcta de testPCBuilderConErroresEnSubComponentes " + iaex.getMessage());
		} catch(IllegalStateException isex) {
			System.err.println("Prueba correcta de testPCBuilderConErroresEnSubComponentes " + isex.getMessage());
		}
	}
	
	private static void testPCBuilderConErroresEnParametros() {
		System.out.println("\n ***** testPCBuilderConErroresEnParametros *****");
		try {
			Componente miPc = Componente.getPcBuilder()
					.definirId(" ")
					.definirDescripcion(null)
					.definirMarcaYModelo(null," ")
					.agregarMonitor("M001","Monitor 17 pulgadas","Sony","Z9000",new BigDecimal("3200.00"),new BigDecimal("6000.00"))
					.agregarDiscoDuro("D001","Disco Seagate","Tech XYZ","X200", new BigDecimal("1880.00"), new BigDecimal("2000.00"),"1TB")
					
					.agregarTarjeta("TV001","Tarjeta XYZ","TechBrand","X200",BigDecimal.valueOf(150.00),BigDecimal.valueOf(200.00),"16GB")
					.agregarDiscoDuro("D001","Disco Seagate","Tech XYZ","X200", new BigDecimal("1880.00"), new BigDecimal("2000.00"),"1TB")
					.agregarMonitor("M001","Monitor 17 pulgadas","Sony","Z9000",new BigDecimal("3200.00"),new BigDecimal("6000.00"))
					.build();
			
			miPc.mostrarCaracteristicas();
			
			// Calculando Cotizador
	    	int cantidad = 1;
	    	System.out.println("\nCotizador PC para " + cantidad + " elementos es: $" + miPc.cotizar(cantidad).floatValue());
					
		} catch(IllegalArgumentException iaex) {
			System.err.println("Prueba correcta de testPCBuilderConErroresEnParametros " + iaex.getMessage());
		} catch(IllegalStateException isex) {
			System.err.println("Prueba correcta de testPCBuilderConErroresEnParametros " + isex.getMessage());
		}
	}
	
	private static void testAgregarComponentes() {
		System.out.println("\n ***** testAgregarComponentes *****");
		try {
			// Crear instancia del Cotizador a través de la interfaz
			ICotizador cotizador = getCotizadorActual();
			Componente disco1 = Componente.crearDiscoDuro("D001",
					   						"Disco Seagate",
					   						"Tech XYZ",
					   						"X200",
					   						new BigDecimal("1880.00"),
					   						new BigDecimal("2000.00"),
					   					    "1TB");
			Componente monitor = Componente.crearMonitor("M001",
											"Monitor 17 pulgadas",
											"Sony",
											"Z9000",
											new BigDecimal("3200.00"),
											new BigDecimal("6000.00"));
			Componente tarjeta = Componente.crearTarjetaVideo("TV001",
												"Tarjeta XYZ",
												"TechBrand",
												"X200",
												BigDecimal.valueOf(150.00),
												BigDecimal.valueOf(200.00),
												"16GB");
			cotizador.agregarComponente(2, tarjeta);
			cotizador.agregarComponente(1, monitor);
			cotizador.agregarComponente(3, disco1);
			cotizador.listarComponentes();
		} catch(Exception ex) {
			System.err.println("Prueba incorrecta de testAgregarComponentes " + ex.getMessage());
		}
	}
	
	private static void testEliminarComponentes() {
		System.out.println("\n ***** testEliminarComponentes *****");
		try {
			// Crear instancia del Cotizador a través de la interfaz
			ICotizador cotizador = getCotizadorActual();
			
			// Crear algunos componentes
			Componente disco1 = Componente.crearDiscoDuro("D001",
					   						"Disco Seagate",
					   						"Tech XYZ",
					   						"X200",
					   						new BigDecimal("1880.00"),
					   						new BigDecimal("2000.00"),
					   						"1TB");
			Componente monitor = Componente.crearMonitor("M001",
											"Monitor 17 pulgadas",
											"Sony",
											"Z9000",
											new BigDecimal("3200.00"),
											new BigDecimal("6000.00"));
			Componente tarjeta = Componente.crearTarjetaVideo("TV001",
												"Tarjeta XYZ",
												"TechBrand",
												"X200",
												BigDecimal.valueOf(150.00),
												BigDecimal.valueOf(200.00),
												"16GB");
			// Prueba: Agregar componentes
			System.out.println("=== Agregando componentes ===");
			cotizador.agregarComponente(2, tarjeta);
			cotizador.agregarComponente(1, monitor);
			cotizador.agregarComponente(3, disco1);
			cotizador.listarComponentes(); // Mostrar cotización actual
			
			// Prueba: Eliminar Componentes
			System.out.println("=== Eliminando componente D001 ===");
			cotizador.eliminarComponente("D001");
			cotizador.listarComponentes();
		} catch(Exception ex) {
			System.err.println("Prueba incorrecta de testEliminarComponentes " + ex.getMessage());
		}
	}

	public static void testEmitirCotizacion() {
		System.out.println("\n ***** testEmitirCotizacion *****");
        try {
			// Crear instancia del Cotizador a través de la interfaz
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
			
			Componente miPc2 = Componente.crearPc("PC0002", "Laptop 800 s300", "Dell", "Terminator", List.of(monitorPc,discoPc,tarjetaPc));
	
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
			System.err.println("Prueba incorrecta de testEmitirCotizacion " + ex.getMessage());
        }
	}
	
	public static void testEmitirCotizacionConPCBuilder() {
		System.out.println("\n ***** testEmitirCotizacionConPCBuilder *****");
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