/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal;

/**
 *
 * @author Jose Sisay
 */
public class NodoVehiculo {
    
String placa, dpi, nombre, marca, modelo, anio, multas, traspasos;
    NodoVehiculo anterior, siguiente;

    public NodoVehiculo(String placa, String dpi, String nombre, String marca, String modelo, String anio, String multas, String traspasos) {
        this.placa = placa;
        this.dpi = dpi;
        this.nombre = nombre;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.multas = multas;
        this.traspasos = traspasos;
    }

    public String[] aArray() {
        return new String[]{placa, dpi, nombre, marca, modelo, anio, multas, traspasos};
    }
}
