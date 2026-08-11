package mx.com.qtx.cotizadorv1ds;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import mx.com.qtx.cotizadorv1ds.componentes.Componente;

public class CotizadorMap {
    private float porcPrecioAgregado = 20;
    private int cantMinDsctoA = 3;
    private int cantMaxDsctoA = 5;
    private float dsctoA = 5;
    private int cantMinDsctoB = 6;
    private int cantMaxDsctoB = 999999;
    private float dsctoB = 10;

    private Map<String, Integer> cantidades = new HashMap<>();
    private Map<String, Componente> componentes = new HashMap<>();

    public void agregarComponente(int cant, Componente compK) {
        String id = compK.getId();
        if (componentes.containsKey(id)) {
            cantidades.put(id, cantidades.get(id) + cant);
        } else {
            componentes.put(id, compK);
            cantidades.put(id, cant);
        }
    }

    public void eliminarComponente(String idComponente) {
        componentes.remove(idComponente);
        cantidades.remove(idComponente);
    }

    public BigDecimal calcularPrecioDefault(int cant, Componente compK) {
        BigDecimal precioBase = compK.getPrecioBase();
        BigDecimal subtotal = precioBase.multiply(BigDecimal.valueOf(cant));
        BigDecimal descuento = BigDecimal.ZERO;

        if (cant >= cantMinDsctoA && cant <= cantMaxDsctoA) {
            descuento = subtotal.multiply(BigDecimal.valueOf(dsctoA / 100.0));
        } else if (cant >= cantMinDsctoB) {
            descuento = subtotal.multiply(BigDecimal.valueOf(dsctoB / 100.0));
        }
        return subtotal.subtract(descuento).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calcularPrecioPromocion3x2(int cant, Componente compK) {
        // 3x2 promotion: for every 3 items, you pay for 2.
        int paidItems = cant - (cant / 3);
        BigDecimal precioBase = compK.getPrecioBase();
        return precioBase.multiply(BigDecimal.valueOf(paidItems)).setScale(2, RoundingMode.HALF_UP);
    }

    public void emitirCotizacion() {
        BigDecimal total = BigDecimal.ZERO;
        System.out.println("--- COTIZACIÓN ---");
        for (String id : componentes.keySet()) {
            Componente comp = componentes.get(id);
            int cant = cantidades.get(id);
            BigDecimal precio = calcularPrecioDefault(cant, comp);
            System.out.println(comp.getDescripcion() + " x" + cant + " -> $" + precio);
            total = total.add(precio);
        }
        System.out.println("Total: $" + total);
    }
}