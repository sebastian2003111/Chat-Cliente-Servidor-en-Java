package com.comunicacion_tcp;

import java.io.*;
import java.net.*;
import java.util.*;

// Servidor de chat que maneja múltiples clientes
public class ChatServer {
    private static final int PORT = 1234; // Puerto del servidor
    private static Set<PrintWriter> clientes = new HashSet<>(); // Conjunto de flujos de salida de los clientes

    // Método principal para iniciar el servidor
    public static void main(String[] args) {
        System.out.println("Servidor de chat iniciado en el puerto " + PORT);

        // Usamos try-with-resources para manejar automáticamente el cierre del ServerSocket
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket socket = serverSocket.accept(); // Espera y acepta conexiones de clientes
                System.out.println("Nuevo cliente conectado: " + socket.getInetAddress());
                new ClientHandler(socket).start();
            }
            // 
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
    // Clase interna para manejar cada cliente
    private static class ClientHandler extends Thread {
        private Socket socket; // Socket del cliente
        private PrintWriter out; // Flujo de salida al cliente
        private BufferedReader in; // Flujo de entrada del cliente

        // Constructor que recibe el socket del cliente
        public ClientHandler(Socket socket) {
            this.socket = socket;
        }
        // Método que se ejecuta al iniciar el hilo
        public void run() {
            // Inicializamos los flujos de entrada y salida
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Flujo de entrada
                out = new PrintWriter(socket.getOutputStream(), true); // Flujo de salida

                synchronized (clientes) { // Añadimos el cliente a la lista
                    clientes.add(out);
                }
                // Bucle para recibir mensajes del cliente
                String mensaje;
                while ((mensaje = in.readLine()) != null) {
                    System.out.println("[" + socket.getInetAddress() + "] " + mensaje);

                    // 🚨 Si el mensaje indica que el usuario salió, rompemos el bucle
                    if (mensaje.contains("ha salido del chat")) {
                        break;
                    }

                    enviarATodos(mensaje);
                }
                // Manejo de desconexión del cliente
            } catch (IOException e) {
                System.out.println("Cliente desconectado: " + socket.getInetAddress());
            } finally {
                try { // Cerramos recursos
                    socket.close();
                } catch (IOException e) {}
                synchronized (clientes) {
                    clientes.remove(out);
                }
                System.out.println("Cliente eliminado: " + socket.getInetAddress());
            }
        }

        // Método para enviar un mensaje a todos los clientes conectados
        private void enviarATodos(String mensaje) {
            synchronized (clientes) {
                for (PrintWriter cliente : clientes) {
                    cliente.println(mensaje);
                }
            }
        }
    }
}
