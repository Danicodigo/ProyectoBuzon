package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import buzon.Buzon;
import cliente.ClienteHilo;

/**
 * The Class ServidorBuzon.
 */
public class ServidorBuzon {

	/**
	 * El servidor se va a encargar de crear los hilos del programa
	 * y crear el servidor.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			Buzon buzon = new Buzon();
			System.out.println("Creando socket servidor");
			ServerSocket server = new ServerSocket();
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			server.bind(addr);
			while (true) {
				Socket socket = null;
				try {
					/**
					 * obtiene los clientes
					 **/
					socket = server.accept();
					System.out.println("Un nuevo cliente está conectado: " + socket);
					/** obteniendo input y output streams**/
					DataInputStream dis = new DataInputStream(socket.getInputStream());
					DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
					System.out.println("Asignando nuevo hilo para el cliente");
					/**
					 * Creando los hilos
					 **/
					Thread t = new ClienteHilo(socket, dis, dos, buzon);
					/**
					 * Iniciando el hilo
					 **/
					t.start();
					System.out.println("Hilo creado");
				} catch (Exception e) {
					socket.close();
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
