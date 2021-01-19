package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * The Class ClienteBuzon.
 */
public class ClienteBuzon {

	/**
	 * La clase Del cliente que será la encargada de crear el socket y leer 
	 * los datos e ir mostrándolos por la 
	 * consola en caso de que reciba un 3 cerrara la conexión del cliente
	 *
	 * @param args the arguments
	 * 
	 */
	public static void main(String[] args)  {
		try {
			Scanner scn = new Scanner(System.in);
			/**
			 * Creando servidor
			 **/
			InetAddress ip = InetAddress.getByName("localhost");
			Socket clienteSocket = new Socket(ip, 5555);
			/**
			 * Almacena los input y el output del cliente
			 **/
			DataInputStream dis = new DataInputStream(clienteSocket.getInputStream());
			DataOutputStream dos = new DataOutputStream(clienteSocket.getOutputStream());
			System.out.println("Creando socket cliente");
			System.out.println("Estableciendo la conexión");
			String mensaje;
			while (true) {
				do {
					String received = dis.readUTF();
					System.out.println(received);
				} while (dis.available() > 0);
				mensaje = scn.nextLine();
				dos.writeUTF(mensaje);
				dos.flush();
				if (mensaje.equals("3")) {
					System.out.println("Closing this connection : " + clienteSocket);
					clienteSocket.close();
					System.out.println("Connection closed");
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
