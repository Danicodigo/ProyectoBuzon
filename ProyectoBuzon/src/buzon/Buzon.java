package buzon;

import java.util.ArrayList;
/**
 * Clase Buzon.
 * Se encarga de modificar los datos del buzon 
 * @author Dani Kuradchyk
 */
public class Buzon {

	/** Guardara los mensajes del buzon en la clase DuplicateMap */
	private DuplicateMap<String, String> mensajesBuzon;

	/**
	 * Constructor del buzon
	 */
	public Buzon() {
		this.mensajesBuzon = new DuplicateMap<>();
	}

	/**
	 * Metodo que se encarga de llamar al metodo put para enviar mensajes a la clase DuplicateMap
	 *
	 * @param usuario the usuario
	 * @param mensaje the mensaje
	 */
	public synchronized void enviarMensaje(String usuario, String mensaje) {
		mensajesBuzon.put(usuario, mensaje);
	}

	/**
	 * Metodo que se encarga de recibir los mensajes de la que se encuentren guardados 
	 * en la clase DuplicateMap de un usuario en concreto
	 *
	 * @param usuario el usuario que quiere ver sus mensajes
	 * @return array que guarda los mensajes
	 */
	public synchronized ArrayList<String> verMensajes(String usuario) {
		return mensajesBuzon.get(usuario);
	}
	
	/**
	 * obtiene todos los mensajes guardados.
	 *
	 * @return the mensajesBuzon
	 */
	public DuplicateMap<String, String> getMensajesBuzon() {
		return mensajesBuzon;
	}

	/**
	 * Sets the mensajesBuzon.
	 *
	 * @param mensajes the mensajes
	 */
	public void setMensajesBuzon(DuplicateMap<String, String> mensajes) {
		this.mensajesBuzon = mensajes;
	}
	
	/**
	 * Borrar mensaje buzon se encarga de borrar los mensajes de el ususario pasado por parametros.
	 *
	 * @param usuario el usuario que quiere borrar los mensajes
	 */
	public synchronized void borrarMensajeBuzon(String usuario) {
		mensajesBuzon.delete(usuario);
	}

}
