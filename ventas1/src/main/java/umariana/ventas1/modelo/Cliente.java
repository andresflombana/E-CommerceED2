/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umariana.ventas1.modelo;

/**
 *
 * @author sebas
 */
public class Cliente {

    private int idCliente;
    private String rutCliente;
    private String nombreCliente;
    private String telefonosCliente;

    public Cliente() {
    }

    public Cliente(int idCliente, String rutCliente, String nombreCliente, String telefonosCliente) {
        this.idCliente = idCliente;
        this.rutCliente = rutCliente;
        this.nombreCliente = nombreCliente;
        this.telefonosCliente = telefonosCliente;
    }

    

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getRutCliente() {
        return rutCliente;
    }

    public void setRutCliente(String rutCliente) {
        this.rutCliente = rutCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getTelefonosCliente() {
        return telefonosCliente;
    }

    public void setTelefonosCliente(String telefonosCliente) {
        this.telefonosCliente = telefonosCliente;
    }

   
    
     
}
