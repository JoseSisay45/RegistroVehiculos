/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal;

/**
 *
 * @author Jose Sisay
 */
public class NodoAVL {
   
    
       String placa, dpi, nombre, marca, modelo, anio, multas, traspasos;
    NodoAVL izquierda, derecha;
    int altura;

    public NodoAVL(String[] datos) {
        this.placa = datos[0];
        this.dpi = datos[1];
        this.nombre = datos[2];
        this.marca = datos[3];
        this.modelo = datos[4];
        this.anio = datos[5];
        this.multas = datos[6];
        this.traspasos = datos[7];
        this.altura = 1;
    }
}
