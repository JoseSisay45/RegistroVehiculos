/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal;

/**
 *
 * @author Jose Sisay
 */
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

public class ArbolMultasABB {
    
    
     private NodoMultaABB raiz;

    public void insertar(String[] datos) {
        raiz = insertarRec(raiz, datos);
    }

    private NodoMultaABB insertarRec(NodoMultaABB nodo, String[] datos) {
        if (nodo == null) {
            return new NodoMultaABB(datos[0], datos[1], datos[2], Double.parseDouble(datos[3]), datos[4]);
        }
        if (datos[0].compareToIgnoreCase(nodo.placa) < 0) {
            nodo.izquierda = insertarRec(nodo.izquierda, datos);
        } else {
            nodo.derecha = insertarRec(nodo.derecha, datos);
        }
        return nodo;
    }

    public void inOrden(DefaultTableModel modelo) {
        inOrdenRec(raiz, modelo);
    }

    private void inOrdenRec(NodoMultaABB nodo, DefaultTableModel modelo) {
        if (nodo != null) {
            inOrdenRec(nodo.izquierda, modelo);
            modelo.addRow(new Object[]{nodo.placa, nodo.fecha, nodo.descripcion, nodo.monto, nodo.estado});
            inOrdenRec(nodo.derecha, modelo);
        }
    }

    public DefaultListModel<String> medirTiemposDesdeTabla(DefaultTableModel modelo) {
        DefaultListModel<String> listaTiempos = new DefaultListModel<>();

        long t1, t2;
        long totalInsercion = 0, totalBusqueda = 0, totalModificacion = 0, totalEliminacion = 0;

        // Inserción
        for (int i = 0; i < modelo.getRowCount(); i++) {
            String[] datos = new String[5];
            for (int j = 0; j < 5; j++) {
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

        // Modificación simulada
        for (int i = 0; i < modelo.getRowCount(); i++) {
            String[] datos = new String[5];
            for (int j = 0; j < 5; j++) {
                datos[j] = modelo.getValueAt(i, j).toString();
            }
            String placa = datos[0];

            t1 = System.nanoTime();
            eliminar(placa);
            insertar(datos);
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

    public NodoMultaABB buscar(String placa) {
        return buscarRec(raiz, placa);
    }

    private NodoMultaABB buscarRec(NodoMultaABB nodo, String placa) {
        if (nodo == null || nodo.placa.equalsIgnoreCase(placa)) return nodo;
        if (placa.compareToIgnoreCase(nodo.placa) < 0)
            return buscarRec(nodo.izquierda, placa);
        else
            return buscarRec(nodo.derecha, placa);
    }

    public void eliminar(String placa) {
        raiz = eliminarRec(raiz, placa);
    }

    private NodoMultaABB eliminarRec(NodoMultaABB nodo, String placa) {
        if (nodo == null) return null;

        if (placa.compareToIgnoreCase(nodo.placa) < 0) {
            nodo.izquierda = eliminarRec(nodo.izquierda, placa);
        } else if (placa.compareToIgnoreCase(nodo.placa) > 0) {
            nodo.derecha = eliminarRec(nodo.derecha, placa);
        } else {
            if (nodo.izquierda == null) return nodo.derecha;
            else if (nodo.derecha == null) return nodo.izquierda;

            NodoMultaABB sucesor = minValor(nodo.derecha);
            nodo.placa = sucesor.placa;
            nodo.fecha = sucesor.fecha;
            nodo.descripcion = sucesor.descripcion;
            nodo.monto = sucesor.monto;
            nodo.estado = sucesor.estado;

            nodo.derecha = eliminarRec(nodo.derecha, sucesor.placa);
        }

        return nodo;
    }

    private NodoMultaABB minValor(NodoMultaABB nodo) {
        while (nodo.izquierda != null) nodo = nodo.izquierda;
        return nodo;
    }
}

    

