/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal;

import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Jose Sisay
 */

public class ArbolABB {
 
    private NodoABB raiz;

    public void insertar(String[] datos) {
        raiz = insertarRec(raiz, datos);
    }

    private NodoABB insertarRec(NodoABB nodo, String[] datos) {
        if (nodo == null) return new NodoABB(datos);

        if (datos[0].compareToIgnoreCase(nodo.placa) < 0) {
            nodo.izquierda = insertarRec(nodo.izquierda, datos);
        } else {
            nodo.derecha = insertarRec(nodo.derecha, datos);
        }
        return nodo;
    }

    public NodoABB buscar(String placa) {
        return buscarRec(raiz, placa);
    }

    private NodoABB buscarRec(NodoABB nodo, String placa) {
        if (nodo == null || nodo.placa.equalsIgnoreCase(placa)) return nodo;
        if (placa.compareToIgnoreCase(nodo.placa) < 0)
            return buscarRec(nodo.izquierda, placa);
        else
            return buscarRec(nodo.derecha, placa);
    }

    public void inOrden(DefaultTableModel modelo) {
        inOrdenRec(raiz, modelo);
    }

    private void inOrdenRec(NodoABB nodo, DefaultTableModel modelo) {
        if (nodo != null) {
            inOrdenRec(nodo.izquierda, modelo);
            modelo.addRow(new Object[]{nodo.placa, nodo.dpi, nodo.nombre, nodo.marca, nodo.modelo, nodo.anio, nodo.multas, nodo.traspasos});
            inOrdenRec(nodo.derecha, modelo);
        }
    }

    public void eliminar(String placa) {
        raiz = eliminarRec(raiz, placa);
    }

    private NodoABB eliminarRec(NodoABB nodo, String placa) {
        if (nodo == null) return null;

        if (placa.compareToIgnoreCase(nodo.placa) < 0) {
            nodo.izquierda = eliminarRec(nodo.izquierda, placa);
        } else if (placa.compareToIgnoreCase(nodo.placa) > 0) {
            nodo.derecha = eliminarRec(nodo.derecha, placa);
        } else {
            if (nodo.izquierda == null) return nodo.derecha;
            else if (nodo.derecha == null) return nodo.izquierda;

            NodoABB sucesor = minValor(nodo.derecha);
            nodo.placa = sucesor.placa;
            nodo.dpi = sucesor.dpi;
            nodo.nombre = sucesor.nombre;
            nodo.marca = sucesor.marca;
            nodo.modelo = sucesor.modelo;
            nodo.anio = sucesor.anio;
            nodo.multas = sucesor.multas;
            nodo.traspasos = sucesor.traspasos;

            nodo.derecha = eliminarRec(nodo.derecha, sucesor.placa);
        }

        return nodo;
    }

    private NodoABB minValor(NodoABB nodo) {
        while (nodo.izquierda != null) nodo = nodo.izquierda;
        return nodo;
    }
    
    
    
    public DefaultListModel<String> medirTiemposDesdeTabla(DefaultTableModel modelo) {
    DefaultListModel<String> listaTiempos = new DefaultListModel<>();

    long t1, t2;
    long totalInsercion = 0, totalBusqueda = 0, totalModificacion = 0, totalEliminacion = 0;

    // Inserción
    for (int i = 0; i < modelo.getRowCount(); i++) {
        String[] datos = new String[8];
        for (int j = 0; j < 8; j++) {
            datos[j] = modelo.getValueAt(i, j).toString();
        }
        t1 = System.nanoTime();
        insertar(datos);
        t2 = System.nanoTime();
        totalInsercion += (t2 - t1);
    }
    listaTiempos.addElement("Inserción total: " + (totalInsercion / 1_000_000.0) + " ms");

    // Búsqueda
    for (int i = 0; i < modelo.getRowCount(); i++) {
        String placa = modelo.getValueAt(i, 0).toString();
        t1 = System.nanoTime();
        buscar(placa);
        t2 = System.nanoTime();
        totalBusqueda += (t2 - t1);
    }
    listaTiempos.addElement("Búsqueda total: " + (totalBusqueda / 1_000_000.0) + " ms");

    // Modificación simulada = eliminar + insertar (como si editáramos)
    for (int i = 0; i < modelo.getRowCount(); i++) {
        String[] datos = new String[8];
        for (int j = 0; j < 8; j++) {
            datos[j] = modelo.getValueAt(i, j).toString();
        }
        String placa = datos[0];

        t1 = System.nanoTime();
        eliminar(placa);
        insertar(datos); // "modificación"
        t2 = System.nanoTime();
        totalModificacion += (t2 - t1);
    }
    listaTiempos.addElement("Modificación total: " + (totalModificacion / 1_000_000.0) + " ms");

    // Eliminación
    for (int i = 0; i < modelo.getRowCount(); i++) {
        String placa = modelo.getValueAt(i, 0).toString();
        t1 = System.nanoTime();
        eliminar(placa);
        t2 = System.nanoTime();
        totalEliminacion += (t2 - t1);
    }
    listaTiempos.addElement("Eliminación total: " + (totalEliminacion / 1_000_000.0) + " ms");

    return listaTiempos;
}
    
}
