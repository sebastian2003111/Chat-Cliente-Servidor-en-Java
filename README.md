# 💬 Chat Cliente-Servidor en Java con Concurrencia

Este proyecto implementa un sistema de **chat multiusuario** desarrollado en **Java**.  
Se utiliza el modelo **cliente-servidor con sockets TCP** y **concurrencia mediante hilos**, lo que permite que múltiples clientes se conecten al servidor y se comuniquen entre sí en tiempo real.  

Este proyecto fue realizado como parte de la **actividad académica de Lenguaje de Programación I**.  

---

## 📌 Objetivos del proyecto
- Comprender el uso de **sockets TCP** en Java para la comunicación entre procesos.  
- Implementar la **concurrencia** usando hilos para manejar múltiples clientes simultáneamente.  
- Simular un **chat en red** entre varias computadoras y dispositivos móviles.  
- Aplicar la teoría de **comunicación cliente-servidor** en un caso práctico.  

---

## 🚀 Características principales
✅ Servidor TCP que acepta múltiples clientes en paralelo.  
✅ Manejo de clientes con **hilos** para comunicación concurrente.  
✅ Los clientes pueden conectarse desde:  
   - Otra PC en la misma red.  
   - Un celular con **Termux**.  
✅ Uso de **comandos**:  
   - `salir` → desconecta al cliente del chat.  
✅ Comunicación en tiempo real entre todos los clientes conectados.  

---

## ⚙️ Requisitos
- **Java JDK 17** o superior instalado.  
- **Visual Studio Code / NetBeans** (o cualquier IDE de preferencia).  
- Conexión en la misma red local (LAN o WiFi).  
- (Opcional) **Termux** en Android para ejecutar el cliente en un celular.  

---

## ▶️ Ejecución del proyecto

### 1️⃣ Compilar los archivos
Desde la carpeta principal del proyecto:  
```bash
javac -d target/classes src/com/comunicacion_tcp/*.java
