/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal;

/**
 *
 * @author Jose Sisay
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
public class ListaMultas {
    
   
    private NodoMulta inicio = null;
    private NodoMulta fin = null;

    public void cargarMultasDesdeArchivo(DefaultTableModel modelo, String departamento) {
        limpiarLista();
        modelo.setRowCount(0);

        String ruta = "Departamentos/" + departamento + "/" + departamento + "_multas.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            br.readLine(); // Saltar encabezado

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 4) {
                    NodoMulta nodo = new NodoMulta(partes[0], partes[1], partes[2], partes[3]);
                    insertar(nodo);
                    modelo.addRow(nodo.aArray());
                }
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "❌ Error leyendo el archivo de multas");
        }
    }

    private void insertar(NodoMulta nodo) {
        if (inicio == null) {
            inicio = fin = nodo;
        } else {
            fin.siguiente = nodo;
            nodo.anterior = fin;
            fin = nodo;
        }
    }

    private void limpiarLista() {
        inicio = fin = null;
    }
}

    

