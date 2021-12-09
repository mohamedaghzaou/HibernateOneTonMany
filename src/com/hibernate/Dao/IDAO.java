package com.hibernate.Dao;

import java.util.List;

public interface IDAO<T, V> {

	T findById(V id);

	List<T> findAll();

	T update(T obj);

	boolean deleteById(V id);

	T save(T obj);

}
