package mx.com.qtx.cotizadorv1ds;
import java.math.BigDecimal;

public class CotizadorTest {
    
	public static void main(String[] args) {
        testCreacionComponente();
		testCreacionComponente_confEquivocada_DiscoConMemoria();
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
    			BigDecimal.valueOf(200.00),
    			"TarjetaVideo",
    			"16GB",
    			null);
    	c1.mostrarCaracteristicas();
    }

	private static void testCreacionComponente_confEquivocada_DiscoConMemoria() {
		try {
		Componente c1 = new Componente("D001",
    			"Disco Seagate",
    			"Tech XYZ",
    			"X200",
    			new BigDecimal("1880.00"),
    			new BigDecimal("2000.00"),
    			"DiscoDuro",
    			"16GB",
    			"1TB");
			c1.mostrarCaracteristicas();
			System.out.println("testCreacionComponente_confEquivocada funciona incorrectamente");
		} catch(IllegalArgumentException iaex) {
			System.out.println("testCreacionComponente_confEquivocada funciona correctamente");
		}
	}
	
	private static void testCreacionPC() {
		Componente disco1 = new Componente("D001",
    									   "Disco Seagate",
    									   "Tech XYZ",
    									   "X200",
    									   new BigDecimal("1880.00"),
    									   new BigDecimal("2000.00"),
    									   "DiscoDuro",
    									   null,
    									   "1TB");
		Componente monitor = new Componente("M001",
    										"Monitor 17 pulgadas",
    										"Sony",
    										"Z9000",
    										new BigDecimal("3200.00"),
    										new BigDecimal("6000.00"),
    										"Monitor",
    										null,
    										null);
		Componente tarjeta = new Componente("TV001",
    								   "Tarjeta XYZ",
    								   "TechBrand",
    								   "X200",
    								   BigDecimal.valueOf(150.00),
    								   BigDecimal.valueOf(200.00),
    								   "TarjetaVideo",
    								   "16GB",
    								   null);
		try {
			Componente miPc = Componente.crearPC("PC001", "Laptop 1500 s300", "Dell", "Terminator", 
											 disco1, null, monitor, tarjeta);
			miPc.mostrarCaracteristicas();
		} catch(IllegalArgumentException iaex) {
			System.out.println("Error: testCreacionPC " + iaex.getMessage());
		}
		
	}
	
	private static void testCreacionPC_ConErrores() {
		Componente disco1 = new Componente("D001",
				   "Disco Seagate",
				   "Tech XYZ",
				   "X200",
				   new BigDecimal("1880.00"),
				   new BigDecimal("2000.00"),
				   "DiscoDuro",
				   null,
				   "1TB");
		Componente monitor = new Componente("M001",
					"Monitor 17 pulgadas",
					"Sony",
					"Z9000",
					new BigDecimal("3200.00"),
					new BigDecimal("6000.00"),
					"Monitor",
					null,
					null);
		/*
		Componente tarjeta = new Componente("TV001",
			   "Tarjeta XYZ",
			   "TechBrand",
			   "X200",
			   BigDecimal.valueOf(150.00),
			   BigDecimal.valueOf(200.00),
			   "TarjetaVideo",
			   "16GB",
			   null);*/
		try {
			Componente miPc = Componente.crearPC("PC001", "Laptop 1500 s300", "Dell", "Terminator", 
					 disco1, null, monitor, null);
			miPc.mostrarCaracteristicas();
		} catch(IllegalArgumentException iaex) {
			System.out.println("testCreacionPC_ConErrores funciona correctamente " + iaex.getMessage());
		}
	}
	
	private static void testAgregarComponentes() {
		Cotizador cotizador = new Cotizador();
		Componente disco1 = new Componente("D001",
				   "Disco Seagate",
				   "Tech XYZ",
				   "X200",
				   new BigDecimal("1880.00"),
				   new BigDecimal("2000.00"),
				   "DiscoDuro",
				   null,
				   "1TB");
		Componente monitor = new Componente("M001",
					"Monitor 17 pulgadas",
					"Sony",
					"Z9000",
					new BigDecimal("3200.00"),
					new BigDecimal("6000.00"),
					"Monitor",
					null,
					null);
		Componente tarjeta = new Componente("TV001",
			   "Tarjeta XYZ",
			   "TechBrand",
			   "X200",
			   BigDecimal.valueOf(150.00),
			   BigDecimal.valueOf(200.00),
			   "TarjetaVideo",
			   "16GB",
			   null);
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
		Componente disco1 = new Componente("D001",
				   "Disco Seagate",
				   "Tech XYZ",
				   "X200",
				   new BigDecimal("1880.00"),
				   new BigDecimal("2000.00"),
				   "DiscoDuro",
				   null,
				   "1TB");
		Componente monitor = new Componente("M001",
					"Monitor 17 pulgadas",
					"Sony",
					"Z9000",
					new BigDecimal("3200.00"),
					new BigDecimal("6000.00"),
					"Monitor",
					null,
					null);
		Componente tarjeta = new Componente("TV001",
			   "Tarjeta XYZ",
			   "TechBrand",
			   "X200",
			   BigDecimal.valueOf(150.00),
			   BigDecimal.valueOf(200.00),
			   "TarjetaVideo",
			   "16GB",
			   null);
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
		// Crear algunos componentes
		Componente disco = new Componente("D001",
				   "Disco Seagate",
				   "Tech XYZ",
				   "X200",
				   new BigDecimal("150.00"),
				   new BigDecimal("200.00"),
				   "DiscoDuro",
				   null,
				   "1TB");
		Componente tarjeta = new Componente("TV001",
				   "Tarjeta ABC",
				   "GraphicBrand",
				   "G100",
				   BigDecimal.valueOf(300.00),
				   BigDecimal.valueOf(400.00),
				   "TarjetaVideo",
				   "8GB",
				   null);
		Componente monitor = new Componente("M00T",
					"Monitor 17 pulgadas",
					"Sony",
					"Z9000",
					new BigDecimal("1000.00"),
					new BigDecimal("2000.00"),
					"Monitor",
					null,
					null);
		Componente discoPc = new Componente("D00Y",
				   "Disco Seagate",
				   "Tech XYZ",
				   "X200",
				   new BigDecimal("1880.00"),
				   new BigDecimal("2000.00"),
				   "DiscoDuro",
				   null,
				   "1TB");
		Componente monitorPc = new Componente("M00X",
				"Monitor 17 pulgadas",
				"Sony",
				"Z9000",
				new BigDecimal("3200.00"),
				new BigDecimal("3000.00"),
				"Monitor",
				null,
				null);
		Componente tarjetaPc = new Componente("C005",
			   "Tarjeta XYZ",
			   "TechBrand",
			   "X200",
			   BigDecimal.valueOf(150.00),
			   BigDecimal.valueOf(1000.00),
			   "TarjetaVideo",
			   "16GB",
			   null);
		Componente miPc = Componente.crearPC("PC0001", "Laptop 15000 s300", "Dell", "Terminator", 
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