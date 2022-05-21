package clase15.laboratorio;


public interface DAO<K, E> {
	
	boolean save(E element);

}
