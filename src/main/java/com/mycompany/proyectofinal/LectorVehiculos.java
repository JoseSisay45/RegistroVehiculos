/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal;

/**
 *
 * @author Jose Sisay
 */
import javax.swing.table.DefaultTableModel;
import java.io.*;

public class LectorVehiculos {

    // Método para cargar todos los registros del archivo de vehículos
    public static void cargarArchivo(DefaultTableModel modelo, String departamento) {
        modelo.setRowCount(0); // Limpiar tabla

        // Construir la ruta al archivo
        String archivo = "Departamentos/" + departamento + "/" + departamento + "_vehiculos.txt";

        try {
            BufferedReader lector = new BufferedReader(new FileReader(archivo));
            String linea;

            lector.readLine(); // Saltar encabezado

            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(",");

                if (partes.length == 8) {
                    // Agregar los datos a la tabla
                    modelo.addRow(new Object[]{
                        partes[0], partes[1], partes[2], partes[3],
                        partes[4], partes[5], partes[6], partes[7]
                    });
                }
            }

            lector.close();

        } catch (Exception e) {
            System.out.println("No se pudo leer el archivo: " + archivo);
        }
    }

    // Método para buscar un vehículo con los datos ingresados
    public static void buscarVehiculo(DefaultTableModel modelo, String departamento,
                                      String placa, String dpi, String nombre,
                                      String marca, String modeloTexto, String anio) {

        modelo.setRowCount(0); // Limpiar tabla

        String archivo = "Departamentos/" + departamento + "/" + departamento + "_vehiculos.txt";

        try {
            BufferedReader lector = new BufferedReader(new FileReader(archivo));
            String linea;

            lector.readLine(); // Saltar encabezado

            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(",");

                if (partes.length == 8) {
                    // Comparar cada campo solo si fue ingresado
                    boolean coincide = true;

                    if (!placa.isEmpty() && !partes[0].equalsIgnoreCase(placa)) coincide = false;
                    if (!dpi.isEmpty() && !partes[1].equalsIgnoreCase(dpi)) coincide = false;
                    if (!nombre.isEmpty() && !partes[2].equalsIgnoreCase(nombre)) coincide = false;
                    if (!marca.isEmpty() && !partes[3].equalsIgnoreCase(marca)) coincide = false;
                    if (!modeloTexto.isEmpty() && !partes[4].equalsIgnoreCase(modeloTexto)) coincide = false;
                    if (!anio.isEmpty() && !partes[5].equalsIgnoreCase(anio)) coincide = false;

                    if (coincide) {
                        modelo.addRow(new Object[]{
                            partes[0], partes[1], partes[2], partes[3],
                            partes[4], partes[5], partes[6], partes[7]
                        });
                    }
                }
            }

            lector.close();

        } catch (Exception e) {
            System.out.println("Error al buscar en el archivo: " + archivo);
        }
    }
}