package umariana.ventas1.modelo;

/**
 *
 * @author sebas
 */
public class Venta {
    private int idVenta;
    private String fechaVenta; 
    private double descuento;
    private double montoTotal;
    private int idCliente;

    // Constructores
    public Venta() {
    }

    public Venta(int idVenta, String fechaVenta, double descuento, double montoTotal, int idCliente) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.descuento = descuento;
        this.montoTotal = montoTotal;
        this.idCliente = idCliente;
    }

    // Getters y Setters
    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }


}