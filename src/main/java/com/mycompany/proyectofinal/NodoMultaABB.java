/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal;

/**
 *
 * @author Jose Sisay
 */
public class NodoMultaABB {
    
  String placa;
    String fecha;
    String descripcion;
    double monto;
    String estado;

    NodoMultaABB izquierda, derecha;

    public NodoMultaABB(String placa, String fecha, String descripcion, double monto, String estado) {
        this.placa = placa;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.monto = monto;
        this.estado = estado;
    }
}