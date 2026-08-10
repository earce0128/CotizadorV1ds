package mx.com.qtx.cotizadorv1ds;
import java.math.BigDecimal;

public class CotizadorTest {
    
	public static void main(String[] args) {
        testCreacionComponente();
        testComponenteDiscoDuro();
        testComponenteDiscoDuroSinCamAlm();
        testComponenteTarjetaVideo();
        testComponentetestComponenteTarjetaVideoSinMemoria();
        testComponenteMonitor();
        testCreacionPC();
		testCreacionPC_ConErrores();
        testAgregarComponentes();
        testEliminarComponentes();
		testEmitirCotizacion();
    }

	public static void testCreacionComponente() {
		Componente c1 = new Componente("C001",
    			"Tarjeta XYZ",
    			"TechBrand",
    			"X200",
    			BigDecimal.valueOf(150.00),
    			BigDecimal.valueOf(200.00));
    	c1.mostrarCaracteristicas();
    }
	
	public static void testComponenteDiscoDuro() {
		// Creando un disco duro
		Componente c1 = new DiscoDuro("D001",
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
			Componente c1 = new DiscoDuro("D001",
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
		Componente c1 = new TarjetaVideo("TV001",
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
			Componente c1 = new TarjetaVideo("TV001",
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
		Componente c1 = new Monitor("M001",
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
		Componente disco1 = new DiscoDuro("D001",
    									   "Disco Seagate",
    									   "Tech XYZ",
    									   "X200",
    									   new BigDecimal("1880.00"),
    									   new BigDecimal("2000.00"),
    									   "1TB");
		Componente monitor = new Monitor("M001",
    									 "Monitor 17 pulgadas",
    									 "Sony",
    									 "Z9000",
    									 new BigDecimal("3200.00"),
    									 new BigDecimal("6000.00"));
		Componente tarjeta = new TarjetaVideo("TV001",
    								   		  "Tarjeta XYZ",
    								   		  "TechBrand",
    								   		  "X200",
    								   		  BigDecimal.valueOf(150.00),
    								   		  BigDecimal.valueOf(200.00),
    								   		  "16GB");
		try {
			Componente miPc = new Pc("PC001", "Laptop 1500 s300", "Dell", "Terminator", disco1, null, monitor, tarjeta); 
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
		Componente disco1 = new DiscoDuro("D001",
    									   "Disco Seagate",
    									   "Tech XYZ",
    									   "X200",
    									   new BigDecimal("1880.00"),
    									   new BigDecimal("2000.00"),
    									   "1TB");
		Componente monitor = new Monitor("M001",
    									 "Monitor 17 pulgadas",
    									 "Sony",
    									 "Z9000",
    									 new BigDecimal("3200.00"),
    									 new BigDecimal("6000.00"));
		Componente tarjeta = new TarjetaVideo("TV001",
    								   		  "Tarjeta XYZ",
    								   		  "TechBrand",
    								   		  "X200",
    								   		  BigDecimal.valueOf(150.00),
    								   		  BigDecimal.valueOf(200.00),
    								   		  "16GB");
		try {
			Componente miPc = new Pc("PC001", "Laptop 1500 s300", "Dell", "Terminator", disco1, null, monitor, null); 
			miPc.mostrarCaracteristicas();
		} catch(IllegalArgumentException iaex) {
			System.out.println("testCreacionPC_ConErrores funciona correctamente " + iaex.getMessage());
		}
	}
	
	private static void testAgregarComponentes() {
		Cotizador cotizador = new Cotizador();
		Componente disco1 = new DiscoDuro("D001",
				   						"Disco Seagate",
				   						"Tech XYZ",
				   						"X200",
				   						new BigDecimal("1880.00"),
				   						new BigDecimal("2000.00"),
				   					    "1TB");
		Componente monitor = new Monitor("M001",
										"Monitor 17 pulgadas",
										"Sony",
										"Z9000",
										new BigDecimal("3200.00"),
										new BigDecimal("6000.00"));
		Componente tarjeta = new TarjetaVideo("TV001",
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
		Componente disco1 = new DiscoDuro("D001",
				   						"Disco Seagate",
				   						"Tech XYZ",
				   						"X200",
				   						new BigDecimal("1880.00"),
				   						new BigDecimal("2000.00"),
				   						"1TB");
		Componente monitor = new Monitor("M001",
										"Monitor 17 pulgadas",
										"Sony",
										"Z9000",
										new BigDecimal("3200.00"),
										new BigDecimal("6000.00"));
		Componente tarjeta = new TarjetaVideo("TV001",
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
		Componente disco = new DiscoDuro("D001",
				   "Disco Seagate",
				   "Tech XYZ",
				   "X200",
				   new BigDecimal("150.00"),
				   new BigDecimal("200.00"),
				   "1TB");
		Componente tarjeta = new TarjetaVideo("TV001",
				   "Tarjeta ABC",
				   "GraphicBrand",
				   "G100",
				   BigDecimal.valueOf(300.00),
				   BigDecimal.valueOf(400.00),
				   "8GB");
		Componente monitor = new Monitor("M00T",
					"Monitor 17 pulgadas",
					"Sony",
					"Z9000",
					new BigDecimal("1000.00"),
					new BigDecimal("2000.00"));
		Componente discoPc = new DiscoDuro("D00Y",
				   "Disco Seagate",
				   "Tech XYZ",
				   "X200",
				   new BigDecimal("1880.00"),
				   new BigDecimal("2000.00"),
				   "1TB");
		Componente monitorPc = new Monitor("M00X",
				"Monitor 17 pulgadas",
				"Sony",
				"Z9000",
				new BigDecimal("3200.00"),
				new BigDecimal("3000.00"));
		Componente tarjetaPc = new TarjetaVideo("C005",
			   "Tarjeta XYZ",
			   "TechBrand",
			   "X200",
			   BigDecimal.valueOf(150.00),
			   BigDecimal.valueOf(1000.00),
			   "16GB");
		Componente miPc = new Pc("PC0001", "Laptop 15000 s300", "Dell", "Terminator", 
				discoPc, null, monitorPc, tarjetaPc);

		System.out.println("=== Agregar componentes ===");
		cotizador.agregarComponente(10, disco);
		cotizador.agregarComponente(5, tarjeta);
		cotizador.agregarComponente(10, monitor);
		cotizador.agregarComponente(1, miPc);
		
		// Prueba: Emitir cotización
		cotizador.emitirCotizacion(); // Mostrar cotización actual
	}
}