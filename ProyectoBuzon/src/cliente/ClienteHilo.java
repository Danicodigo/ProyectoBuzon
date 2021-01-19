package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import buzon.Buzon;


/**
 * The Class ClienteHilo esta clase se encarga de darle la funcionalidad a los clientes.
 * 
 * @author Dani Kuradchyk
 */
public class ClienteHilo extends Thread {
	
	/** dis es el inputStream  */
	private final DataInputStream dis;
	
	/** The dos es el output */
	private final DataOutputStream dos;
	
	/** The socket es el socket */
	private final Socket socket;
	
	/** The usuario es el nombre de usuario */
	private String usuario;
	
	/** The buzon es la variable del buzon*/
	private Buzon buzon;

	/**
	 * Constructor del CLienteHilo
	 *
	 * @param socket is the socket
	 * @param dis the dis guarda el input
	 * @param dos the dos guarda el outbut
	 * @param buzon the buzon  guarda el buzon
	 */
	public ClienteHilo(Socket socket, DataInputStream dis, DataOutputStream dos, Buzon buzon) {
		this.socket = socket;
		this.dis = dis;
		this.dos = dos;
		this.buzon = buzon;
	}

	/**
	 * El metodo run se va a encargar de hacerle peticiones al  cliente mostrandole el menu para que
	 * pueda elegir una de las opciones que ofrece el programa usando la variable buzon para poder guardar o 
	 * almacenar los mensajes.
	 */
	@Override
	public void run() {
		boolean con = false;
		String tec;
		try {
			dos.writeUTF("¿Cual es su nombre de usuario?");
			this.usuario = this.dis.readUTF();
			dos.writeUTF("Hola " + this.usuario + "\n");
			dos.flush();
			while (!con) {
				menu();
				tec = dis.readUTF();
				switch (tec) {
				case "1":
					dos.writeUTF("Introduce el destinatario: ");
					String destinatario = dis.readUTF();
					dos.writeUTF("Introduce el mensaje: ");
					String mensaje = dis.readUTF();
					mensaje += " - Enviado por: " + usuario;
					buzon.enviarMensaje(destinatario, mensaje);
					dos.writeUTF("Mensaje enviado\n");
					break;
				case "2":
					ArrayList<String> msg = buzon.verMensajes(usuario);
					if (msg == null) {
				
						dos.writeUTF("No tiene mensajes\n");
					} else {
						for (int i = 0; i < msg.size(); i++) {
							dos.writeUTF(msg.get(i));
							dos.flush();
						}
						buzon.borrarMensajeBuzon(usuario);
					}
					break;
				case "3":
					this.socket.close();
					System.out.println("Conexion cerrada");
					con = true;
					break;
				default:
					dos.writeUTF("Introduce una opcion correcta");
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.dis.close();
			this.dos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Muestra el menu
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void menu() throws IOException {
		dos.writeUTF("Selecciona una opcion");
		dos.writeUTF("---------------------------");
		dos.writeUTF("(1) Escribir mensaje");
		dos.writeUTF("(2) Ver mensajes nuevos");
		dos.writeUTF("(3) Salir");
		dos.flush();
	}

	/**
	 * Gets the usuario.
	 *
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Sets the usuario.
	 *
	 * @param usuario the new usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Gets the buzon.
	 *
	 * @return the buzon
	 */
	public Buzon getBuzon() {
		return buzon;
	}

	/**
	 * Sets the buzon.
	 *
	 * @param buzon the new buzon
	 */
	public void setBuzon(Buzon buzon) {
		this.buzon = buzon;
	}

	/**
	 * Gets the dis.
	 *
	 * @return the dis
	 */
	public DataInputStream getDis() {
		return dis;
	}

	/**
	 * Gets the dos.
	 *
	 * @return the dos
	 */
	public DataOutputStream getDos() {
		return dos;
	}

	/**
	 * Gets the socket.
	 *
	 * @return the socket
	 */
	public Socket getSocket() {
		return socket;
	}

	
}
