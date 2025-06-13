/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal;

/**
 *
 * @author Jose Sisay
 */



public class NodoMulta {
    String placa, fecha, descripcion, monto;
    String estado = "Pendiente"; // Por defecto
    NodoMulta anterior, siguiente;

    public NodoMulta(String placa, String fecha, String descripcion, String monto) {
        this.placa = placa;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.monto = monto;
    }

    public String[] aArray() {
        return new String[]{placa, fecha, descripcion, monto, estado};
    }
}
