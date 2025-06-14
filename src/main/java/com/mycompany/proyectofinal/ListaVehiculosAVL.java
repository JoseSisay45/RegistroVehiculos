/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal;

/**
 *
 * @author Jose Sisay
 */

import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ListaVehiculosAVL {
    
    
    private NodoVehiculoAVL inicio = null;
    private NodoVehiculoAVL fin = null;

    public void cargarDesdeArchivo(DefaultTableModel modelo, String departamento) {
        limpiarLista();
        modelo.setRowCount(0);
        String ruta = "Departamentos/" + departamento + "/" + departamento + "_vehiculos.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea = br.readLine(); // Encabezado
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 8) {
                    insertar(new NodoVehiculoAVL(partes[0], partes[1], partes[2], partes[3], partes[4], partes[5], partes[6], partes[7]));
                    modelo.addRow(partes);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, " Error cargando archivo.");
        }
    }

    public void insertarDesdeCampos(DefaultTableModel modelo, String[] datos) {
        NodoVehiculoAVL nuevo = new NodoVehiculoAVL(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], datos[6], datos[7]);
        insertar(nuevo);
        modelo.addRow(nuevo.aArray());
    }

    public void modificarFila(DefaultTableModel modelo, int filaSeleccionada, String[] nuevosDatos) {
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, " Seleccione una fila para modificar.");
            return;
        }

        for (int j = 0; j < 8; j++) {
            modelo.setValueAt(nuevosDatos[j], filaSeleccionada, j);
        }
        JOptionPane.showMessageDialog(null, " Modifique la fila seleccionada. Luego presione GUARDAR para aplicar cambios.");
    }

    public void eliminarFila(DefaultTableModel modelo, int filaSeleccionada) {
        if (filaSeleccionada != -1) {
            modelo.removeRow(filaSeleccionada);
            JOptionPane.showMessageDialog(null, " Fila eliminada. Recuerde presionar GUARDAR para aplicar en archivo.");
        } else {
            JOptionPane.showMessageDialog(null, " Seleccione una fila para eliminar.");
        }
    }

    public void guardarEnArchivo(DefaultTableModel modelo, String departamento) {
        String ruta = "Departamentos/" + departamento + "/" + departamento + "_vehiculos.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            bw.write("Placa,DPI,Nombre,Marca,Modelo,Año,Multas,Traspasos");
            bw.newLine();

            for (int i = 0; i < modelo.getRowCount(); i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < modelo.getColumnCount(); j++) {
                    sb.append(modelo.getValueAt(i, j));
                    if (j < modelo.getColumnCount() - 1) sb.append(",");
                }
                bw.write(sb.toString());
                bw.newLine();
            }

            JOptionPane.showMessageDialog(null, " Cambios guardados correctamente en el archivo TXT.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, " Error al guardar cambios.");
        }
    }

    public void buscarVehiculo(DefaultTableModel modelo, String departamento, String placa, String dpi, String nombre, String marca, String modeloTexto, String anio) {
        limpiarLista();
        modelo.setRowCount(0);
        String ruta = "Departamentos/" + departamento + "/" + departamento + "_vehiculos.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            br.readLine(); // Saltar encabezado
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length != 8) continue;

                boolean coincide = true;
                if (!placa.isEmpty() && !datos[0].equalsIgnoreCase(placa)) coincide = false;
                if (!dpi.isEmpty() && !datos[1].equalsIgnoreCase(dpi)) coincide = false;
                if (!nombre.isEmpty() && !datos[2].equalsIgnoreCase(nombre)) coincide = false;
                if (!marca.isEmpty() && !datos[3].equalsIgnoreCase(marca)) coincide = false;
                if (!modeloTexto.isEmpty() && !datos[4].equalsIgnoreCase(modeloTexto)) coincide = false;
                if (!anio.isEmpty() && !datos[5].equalsIgnoreCase(anio)) coincide = false;

                if (coincide) {
                    insertar(new NodoVehiculoAVL(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], datos[6], datos[7]));
                    modelo.addRow(datos);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, " Error al buscar datos.");
        }
    }

    private void insertar(NodoVehiculoAVL nodo) {
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
