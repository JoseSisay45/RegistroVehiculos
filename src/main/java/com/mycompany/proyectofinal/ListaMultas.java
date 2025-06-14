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

    public void cargarDesdeArchivo(DefaultTableModel modelo, String departamento) {
        limpiarLista();
        modelo.setRowCount(0);
        String ruta = "Departamentos/" + departamento + "/" + departamento + "_multas.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea = br.readLine(); // Encabezado
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 4) {
                    String estado = "Pendiente";
                    insertar(new NodoMulta(partes[0], partes[1], partes[2], partes[3], estado));
                    modelo.addRow(new Object[]{partes[0], partes[1], partes[2], partes[3], estado});
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, " Error cargando archivo de multas.");
        }
    }

    
    
    public void insertarDesdeCampos(DefaultTableModel modelo, String[] datos) {
        NodoMulta nuevo = new NodoMulta(datos[0], datos[1], datos[2], datos[3], datos[4]);
        insertar(nuevo);
        modelo.addRow(nuevo.aArray());
    }

    public void modificarFila(DefaultTableModel modelo, int filaSeleccionada, String[] nuevosDatos) {
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "  Seleccione una fila para modificar.");
            return;
        }
        for (int j = 0; j < 5; j++) {
            modelo.setValueAt(nuevosDatos[j], filaSeleccionada, j);
        }
        JOptionPane.showMessageDialog(null, " Modifique la fila. Presione GUARDAR para aplicar cambios.");
    }

    public void eliminarFila(DefaultTableModel modelo, int filaSeleccionada) {
        if (filaSeleccionada != -1) {
            modelo.removeRow(filaSeleccionada);
            JOptionPane.showMessageDialog(null, " Fila eliminada. Presione GUARDAR para aplicar en archivo.");
        } else {
            JOptionPane.showMessageDialog(null, "  Seleccione una fila para eliminar.");
        }
    }

    public void pagarMulta(DefaultTableModel modelo, int filaSeleccionada) {
    if (filaSeleccionada != -1) {
        modelo.setValueAt("Pagada", filaSeleccionada, 4); // Cambiar estado
        modelo.setValueAt("0", filaSeleccionada, 3);       // Cambiar monto a 0
        JOptionPane.showMessageDialog(null, " Multa pagada. Presione GUARDAR para aplicar los cambios.");
    } else {
        JOptionPane.showMessageDialog(null, " Seleccione una multa para marcar como pagada.");
    }
}

    public void guardarEnArchivo(DefaultTableModel modelo, String departamento) {
        String ruta = "Departamentos/" + departamento + "/" + departamento + "_multas.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            bw.write("Placa,Fecha,Descripcion,Monto,Estado");
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
            JOptionPane.showMessageDialog(null, " Cambios guardados en archivo TXT de multas.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, " Error al guardar archivo de multas.");
        }
    }
    
    
    
    
public void buscarMultas(DefaultTableModel modelo, String departamento,
                              String placa, String fecha, String descripcion, String monto, String estado) {
        limpiarLista();
        modelo.setRowCount(0);
        String ruta = "Departamentos/" + departamento + "/" + departamento + "_multas.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            br.readLine(); // Saltar encabezado
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length != 4) continue;

                String estadoActual = (Double.parseDouble(datos[3]) > 0) ? "Pendiente" : "Pagado";

                boolean coincide = true;
                if (!placa.isEmpty() && !datos[0].equalsIgnoreCase(placa)) coincide = false;
                if (!fecha.isEmpty() && !datos[1].equalsIgnoreCase(fecha)) coincide = false;
                if (!descripcion.isEmpty() && !datos[2].toLowerCase().contains(descripcion.toLowerCase())) coincide = false;
                if (!monto.isEmpty() && !datos[3].equalsIgnoreCase(monto)) coincide = false;
                if (!estado.isEmpty() && !estadoActual.equalsIgnoreCase(estado)) coincide = false;

                if (coincide) {
                    insertar(new NodoMulta(datos[0], datos[1], datos[2], datos[3], estadoActual));
                    modelo.addRow(new Object[]{datos[0], datos[1], datos[2], datos[3], estadoActual});
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "❌ Error buscando en el archivo.");
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
