package mx.com.qtx.cotizadorv1cgpt;

import java.math.BigDecimal;

/**
 * Pruebas básicas del Cotizador.
 */
public class CotizadorTest {

    public static void testEmitirCotizacion() {
        Cotizador cotizador = new Cotizador();

        Componente memoria = new Componente(
                "RAM-001",
                "Memoria RAM DDR4",
                "Kingston",
                "KVR32",
                new BigDecimal("35.00"),
                new BigDecimal("50.00"),
                "Memoria",
                16,
                "N/A"
        );

        Componente disco = new Componente(
                "SSD-001",
                "Disco SSD",
                "Samsung",
                "870 EVO",
                new BigDecimal("60.00"),
                new BigDecimal("100.00"),
                "Almacenamiento",
                0,
                "1 TB"
        );

        cotizador.agregarComponente(2, memoria);
        cotizador.agregarComponente(3, disco);

        System.out.println("=== TEST emitirCotizacion ===");
        cotizador.emitirCotizacion();

        System.out.println();
        System.out.println("=== TEST cálculos ===");
        System.out.println(
                "RAM, precio default x2: $" +
                cotizador.calcularPrecioDefault(2, memoria)
        );
        System.out.println(
                "SSD, promoción 3x2: $" +
                cotizador.calcularPrecioPromocion3x2(3, disco)
        );
        System.out.println(
                "SSD, descuento por cantidad x6: $" +
                cotizador.calcularPrecioPromocionXCantidad(6, disco)
        );
    }

    public static void main(String[] args) {
        testEmitirCotizacion();
    }
}
