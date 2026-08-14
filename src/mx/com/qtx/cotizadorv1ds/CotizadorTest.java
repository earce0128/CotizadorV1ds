package mx.com.qtx.cotizadorv1ds;
import java.math.BigDecimal;
import java.util.List;

import mx.com.qtx.cotizadorv1ds.componentes.Componente;
import mx.com.qtx.cotizadorv1ds.componentes.PcBuilder;

public class CotizadorTest {
    
	public static void main(String[] args) {
//		  testCreacionComponente();
//        testComponenteDiscoDuro();
//        testComponenteDiscoDuroSinCamAlm();
//        testComponenteTarjetaVideo();
//        testComponentetestComponenteTarjetaVideoSinMemoria();
//        testComponenteMonitor();
//        testCreacionPC();
//        testCreacionPCConListaSubcomponentes();
//	      testCreacionPC_ConErrores();
//        testCreacionPC_ConErroresConListaSubcomponentes();
        testPCBuilder();
//        testAgregarComponentes();
//        testEliminarComponentes();
//        testEmitirCotizacion();
//        testEmitirCotizacionConPCBuilder();
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
    }
	
	public static void testComponenteDiscoDuroSinCamAlm() {
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
			System.out.println("\nPrueba de ERROR correcta: " + iaex.getMessage());
		}
    }
	
	public static void testComponenteTarjetaVideo() {
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
	}
	
	private static void testComponentetestComponenteTarjetaVideoSinMemoria() {
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
		} catch(IllegalArgumentException iaex) {
			System.out.println("\nPrueba de ERROR correcta: " + iaex.getMessage());
		}
	}
	
	public static void testComponenteMonitor() {
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
	}
	
	private static void testCreacionPC() {
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
		try {
			Componente miPc = Componente.crearPc("PC001", "Laptop 1500 s300", "Dell", "Terminator", List.of(disco1,monitor,tarjeta)); 
			miPc.mostrarCaracteristicas();
			
			// Calculando Cotizador
	    	int cantidad = 1;
	    	System.out.println("\nCotizador PC para " + cantidad + " elementos es: $" + miPc.cotizar(cantidad).floatValue());
			
		} catch(IllegalArgumentException iaex) {
			System.out.println("Error: testCreacionPC " + iaex.getMessage());
		}
		
	}
	
	private static void testCreacionPCConListaSubcomponentes() {
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
		try {
			Componente miPc = Componente.crearPc("PC001", "Laptop 1500 s300", "Dell", "Terminator", List.of(tarjeta,disco1,monitor)); 
			miPc.mostrarCaracteristicas();
			
			// Calculando Cotizador
	    	int cantidad = 1;
	    	System.out.println("\nCotizador PC para " + cantidad + " elementos es: $" + miPc.cotizar(cantidad).floatValue());
			
		} catch(IllegalArgumentException iaex) {
			System.out.println("Error: testCreacionPC " + iaex.getMessage());
		}
		
	}
	
	private static void testCreacionPC_ConErrores() {
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
		try {
			Componente miPc = Componente.crearPc("PC001", "Laptop 1500 s300", "Dell", "Terminator", List.of(disco1, monitor, tarjeta, tarjeta)); 
			miPc.mostrarCaracteristicas();
		} catch(IllegalArgumentException iaex) {
			System.out.println("testCreacionPC_ConErrores funciona correctamente " + iaex.getMessage());
		}
	}
	
	private static void testCreacionPC_ConErroresConListaSubcomponentes() {
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
		try {
			Componente miPc = Componente.crearPc("PC001", "Laptop 1500 s300", "Dell", "Terminator", List.of(monitor,disco1,tarjeta,c)); 
			miPc.mostrarCaracteristicas();
		} catch(IllegalArgumentException iaex) {
			System.out.println("testCreacionPC_ConErrores funciona correctamente " + iaex.getMessage());
		}
	}
	
	private static void testPCBuilder() {
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
			
			Componente miPc = new PcBuilder("PC001", "Laptop 1500 s300", "Dell", "Terminator")
					.agregarMonitor(monitor)
					.agregarDiscoDuro(disco1)
					.agregarTarjeta(tarjeta)
					.agregarDiscoDuro(disco1)
					.agregarMonitor(monitor)
					.build();
			
			miPc.mostrarCaracteristicas();
					
		} catch(IllegalArgumentException iaex) {
			System.out.println("Error en la creación de la PC: " + iaex.getMessage());
		} catch(IllegalStateException isex) {
			System.out.println("Error en la creación de la PC: " + isex.getMessage());
		}
	}
	
	private static void testAgregarComponentes() {
		Cotizador cotizador = new Cotizador();
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
		System.out.println("=== Agregar componentes ===");
		cotizador.agregarComponente(2, tarjeta);
		cotizador.agregarComponente(1, monitor);
		cotizador.agregarComponente(3, disco1);
		cotizador.listarComponentes();
	}
	
	private static void testEliminarComponentes() {
		Cotizador cotizador = new Cotizador();
		
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
		System.out.println("=== Agregar componentes ===");
		cotizador.agregarComponente(2, tarjeta);
		cotizador.agregarComponente(1, monitor);
		cotizador.agregarComponente(3, disco1);
		cotizador.listarComponentes(); // Mostrar cotización actual
		
		// Prueba: Eliminar Componentes
		System.out.println("=== Eliminando componente D001 ===");
		cotizador.eliminarComponente("D001");
		cotizador.listarComponentes();
	}

	public static void testEmitirCotizacion() {
        // Crear instancia del Cotizador
		Cotizador cotizador = new Cotizador();
		
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
				new BigDecimal("3200.00"),
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
	}
	
	public static void testEmitirCotizacionConPCBuilder() {
        // Crear instancia del Cotizador
		Cotizador cotizador = new Cotizador();
		
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
				new BigDecimal("3200.00"),
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
		Componente miPc2 = new PcBuilder("PC0002", "Laptop PC Builder", "Dell", "Terminator")
			.agregarDiscoDuro(discoPc)
			.agregarMonitor(monitorPc)
			.agregarTarjeta(tarjetaPc)
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
	}
}