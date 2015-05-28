/**
 * 
 */
package com.project.forupets.repository.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author VyTKSE60964
 *
 */
public interface GenericDAO<T, K extends Serializable> {
	K save(T t);

	void saveOrUpdate(T t);

	void update(T t);

	void delete(T t);

	T find(K id);

	List<T> findAll();

	List<T> findAllWithOrder(String column, String sortType);

	List<T> findByProp(String prop, Object data);

	List<T> findByProps(Map<String, Object> props);

	List<Object[]> listAll(String[] columns);

	List<Object[]> listAll(String[] columns, int startIndex, int pageSize);

	List<Object[]> listAll(String[] columns, String[] names);

	public void update(String idFieldName, String orderFieldName,
			Map<Long, Integer> orders, int batchSize);
}
