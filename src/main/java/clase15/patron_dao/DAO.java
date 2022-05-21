package clase15.patron_dao;

public interface DAO<E, K> {

	void save(E element);
	
	E get(K key);
	
	boolean update(E element);
	
	boolean delete(E element);
}
