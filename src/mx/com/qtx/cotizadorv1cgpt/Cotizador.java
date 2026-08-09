package mx.com.qtx.cotizadorv1cgpt;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementa los servicios de cotización de componentes.
 */
public class Cotizador {

    private float porcPrecioAgregado = 20;
    private int cantMinDsctoA = 3;
    private int cantMaxDsctoA = 5;
    private float dsctoA = 5;
    private int cantMinDsctoB = 6;
    private int cantMaxDsctoB = 999999;
    private float dsctoB = 10;

    /*
     * La clase mantiene los componentes y las cantidades solicitadas.
     * Se utiliza LinkedHashMap para conservar el orden de agregado.
     */
    private final Map<String, Componente> componentes = new LinkedHashMap<>();
    private final Map<String, Integer> cantidades = new LinkedHashMap<>();

    /**
     * Agrega una cantidad de un componente a la cotización.
     */
    public void agregarComponente(int cant, Componente compK) {
        if (cant <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero.");
        }
        if (compK == null || compK.getId() == null || compK.getId().isBlank()) {
            throw new IllegalArgumentException("El componente y su ID son obligatorios.");
        }

        componentes.put(compK.getId(), compK);
        cantidades.merge(compK.getId(), cant, Integer::sum);
    }

    /**
     * Elimina un componente de la cotización por su identificador.
     */
    public void eliminarComponente(String idComponente) {
        componentes.remove(idComponente);
        cantidades.remove(idComponente);
    }

    /**
     * Calcula el precio unitario con el porcentaje de precio agregado.
     * Ejemplo: precio base 100 + 20% = 120.
     */
    public BigDecimal calcularPrecioComponenteAgregado() {
        BigDecimal total = BigDecimal.ZERO;

        for (Map.Entry<String, Componente> entry : componentes.entrySet()) {
            Componente componente = entry.getValue();
            int cantidad = cantidades.getOrDefault(entry.getKey(), 0);
            BigDecimal precio = precioConAgregado(componente.getPrecioBase());
            total = total.add(precio.multiply(BigDecimal.valueOf(cantidad)));
        }

        return dinero(total);
    }

    /**
     * Promoción 3x2: por cada tres unidades se pagan dos.
     * Las unidades restantes se pagan a precio normal.
     */
    public BigDecimal calcularPrecioPromocion3x2(int cant, Componente compK) {
        validarCantidadYComponente(cant, compK);

        BigDecimal precio = precioConAgregado(compK.getPrecioBase());
        int grupos = cant / 3;
        int restantes = cant % 3;

        int unidadesPagadas = (grupos * 2) + restantes;
        return dinero(precio.multiply(BigDecimal.valueOf(unidadesPagadas)));
    }

    /**
     * Aplica el descuento por cantidad:
     * - 3 a 5 unidades: 5%
     * - 6 a 999999 unidades: 10%
     * - cualquier otra cantidad: precio normal.
     */
    public BigDecimal calcularPrecioPromocionXCantidad(int cant, Componente compK) {
        validarCantidadYComponente(cant, compK);

        BigDecimal precio = precioConAgregado(compK.getPrecioBase());
        BigDecimal subtotal = precio.multiply(BigDecimal.valueOf(cant));

        if (cant >= cantMinDsctoA && cant <= cantMaxDsctoA) {
            subtotal = aplicarDescuento(subtotal, dsctoA);
        } else if (cant >= cantMinDsctoB && cant <= cantMaxDsctoB) {
            subtotal = aplicarDescuento(subtotal, dsctoB);
        }

        return dinero(subtotal);
    }

    /**
     * Calcula el precio sin promoción.
     */
    public BigDecimal calcularPrecioDefault(int cant, Componente compK) {
        validarCantidadYComponente(cant, compK);

        BigDecimal precio = precioConAgregado(compK.getPrecioBase());
        return dinero(precio.multiply(BigDecimal.valueOf(cant)));
    }

    /**
     * Emite la cotización por consola.
     *
     * La estrategia aplicada es:
     * - 3x2 cuando la cantidad es múltiplo o contiene grupos de tres.
     * - descuento por cantidad para cantidades de 3 o más que no se
     *   beneficien del 3x2.
     *
     * Si el enunciado original exige una prioridad distinta entre promociones,
     * este método puede ajustarse fácilmente.
     */
    public void emitirCotizacion() {
        System.out.println("========== COTIZACIÓN ==========");

        BigDecimal total = BigDecimal.ZERO;

        for (Map.Entry<String, Componente> entry : componentes.entrySet()) {
            Componente componente = entry.getValue();
            int cantidad = cantidades.getOrDefault(entry.getKey(), 0);

            BigDecimal precioUnitario = precioConAgregado(componente.getPrecioBase());
            BigDecimal precio;

            if (cantidad >= 3) {
                precio = calcularPrecioPromocion3x2(cantidad, componente);
            } else {
                precio = calcularPrecioDefault(cantidad, componente);
            }

            total = total.add(precio);

            System.out.printf(
                    "%s - %s x%d | Unitario: $%s | Subtotal: $%s%n",
                    componente.getId(),
                    componente.getDescripcion(),
                    cantidad,
                    dinero(precioUnitario),
                    dinero(precio)
            );
        }

        System.out.println("---------------------------------");
        System.out.println("TOTAL: $" + dinero(total));
        System.out.println("=================================");
    }

    /**
     * Devuelve una copia de los componentes actualmente cotizados.
     */
    public List<Componente> getComponentes() {
        return new ArrayList<>(componentes.values());
    }

    /**
     * Devuelve la cantidad solicitada de un componente.
     */
    public int getCantidad(String idComponente) {
        return cantidades.getOrDefault(idComponente, 0);
    }

    private BigDecimal precioConAgregado(BigDecimal precioBase) {
        if (precioBase == null) {
            throw new IllegalArgumentException("El precio base no puede ser null.");
        }

        BigDecimal porcentaje = BigDecimal.valueOf(porcPrecioAgregado)
                .divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_UP);

        return precioBase.multiply(BigDecimal.ONE.add(porcentaje));
    }

    private BigDecimal aplicarDescuento(BigDecimal importe, float porcentaje) {
        BigDecimal descuento = BigDecimal.valueOf(porcentaje)
                .divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_UP);

        return importe.multiply(BigDecimal.ONE.subtract(descuento));
    }

    private void validarCantidadYComponente(int cant, Componente compK) {
        if (cant <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero.");
        }
        if (compK == null) {
            throw new IllegalArgumentException("El componente no puede ser null.");
        }
        if (compK.getPrecioBase() == null) {
            throw new IllegalArgumentException("El componente debe tener precio base.");
        }
    }

    private BigDecimal dinero(BigDecimal valor) {
        return valor.setScale(2, RoundingMode.HALF_UP);
    }
}
