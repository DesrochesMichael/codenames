package codenames.dao;

import java.util.List;

public interface IDAO<T, Id> {

	public  List<T> findAll();

	public T finByID(Id id);

	public T save(T entity);

	public void delete(T entity);

	public void deleteByID(Id id);


}