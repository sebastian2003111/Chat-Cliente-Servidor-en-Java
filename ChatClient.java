package com.comunicacion_tcp;

import java.io.*;
import java.net.*;
import java.util.Scanner;

// Cliente de chat que se conecta al servidor
public class ChatClient {
    // ⚠️ Aquí pon la IP del servidor si corres desde otra PC o celular
    private static final String SERVER = "10.78.124.137";  // IP del servidor
    private static final int PORT = 1234; // Puerto del servidor

    // Método principal para iniciar el cliente
    public static void main(String[] args) {

        // Usamos try-with-resources para manejar automáticamente el cierre de recursos
        try (
            Socket socket = new Socket(SERVER, PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Flujo de entrada
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // Flujo de salida
            Scanner scanner = new Scanner(System.in) // Para leer entrada del usuario
        ) {
            System.out.println("Conectado al chat en " + SERVER + ":" + PORT);
            System.out.print("Ingresa tu nombre: ");
            String nombre = scanner.nextLine();
            out.println(nombre + " se ha unido al chat.");

            // Hilo para recibir mensajes
            Thread recibir = new Thread(() -> {
                // 
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) { // Lee mensajes del servidor
                        System.out.println(msg);
                    }
                } catch (IOException e) { // Manejo de error
                    System.out.println("Conexión cerrada.");
                }
            });
            recibir.start();

            // Enviar mensajes
            while (true) {
                String mensaje = scanner.nextLine();
                if (mensaje.equalsIgnoreCase("salir")) {
                    out.println(nombre + " ha salido del chat.");
                    socket.close(); // 🚨 Cerramos la conexión del cliente
                    break;
                }
                out.println(nombre + ": " + mensaje);
            }
            // Esperamos a que el hilo de recibir termine antes de salir
        } catch (IOException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }
}
