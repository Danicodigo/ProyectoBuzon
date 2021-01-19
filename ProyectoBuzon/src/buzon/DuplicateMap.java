package buzon;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * La clase DuplicateMap se encarga de recibir dos parametros  que son la K que es la llave y V que es el valor 
 * para de esa forma poder llamar a esta clase siempre que queramos añadir un nuevo valor a la misma llave para de esa forma poder 
 * guardar los mensajes.
 *
 * @param <K> the key type
 * @param <V> the value type
 * @author Dani Kuradchyk
 */
public class DuplicateMap<K, V> {

	/** El hash map que se encargara de guardar los mensajes de los distintos usuarios*/
	private HashMap<K, ArrayList<V>> m = new HashMap<>();

	/**
	 * Metodo que se encarga de comprobar si la llave introducida existe para en caso de que exsista añadir 
	 * el valor en el array de la misma llave o en caso contrario añadir un nuevo valor en el Hashmap con los dos parametros
	 *
	 * @param k the k
	 * @param v the v
	 */
	public void put(K k, V v) {
		if (m.containsKey(k)) {
			m.get(k).add(v);
		} else {
			ArrayList<V> arr = new ArrayList<>();
			arr.add(v);
			m.put(k, arr);
		}
	}

	/**
	 *Recoge los valores de la llave pasada por parametros
	 *
	 * @param k the k
	 * @return the array list
	 */
	public ArrayList<V> get(K k) {
		return m.get(k);
	}


	/**
	 * Gets the.
	 *
	 * @param k the k
	 * @param index the index
	 * @return the v
	 */
	public V get(K k, int index) {
		return m.get(k).size() - 1 < index ? null : m.get(k).get(index);
	}
	
	/**
	 * Metodo que se encarga de borrar los datos del valor llave del hashMap
	 *
	 * @param k es la llave del hashMap
	 */
	public void delete(K k) {
		m.remove(k);
	}
}