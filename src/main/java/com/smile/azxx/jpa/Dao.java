/**
 * jpa常用的操作方法封装接口
 */
package com.smile.azxx.jpa;

import com.smile.azxx.entity.common.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;

/**
 * @author smile
 *2012-8-20
 */
public interface Dao<T> {
	
public abstract void delete(Serializable id);
	
	public abstract void delete(Object entity);
	
	public abstract void delete(Iterable entities);
	
	public abstract void deleteInBatch(Iterable iterable);
	
	public abstract void deleteAll();
	
	public abstract T findOne(Serializable id);
	
	public abstract boolean exists(Serializable id);
	
	public abstract List findAll(Sort sort);
	
	public abstract Object findOne(Specification spec);
	
	public abstract List findAll();
	
	public abstract List findAll(Specification spec);
	
	
	public abstract List findAll(Specification spec, Sort sort);
	
	public abstract long count();
	
	public abstract long count(Specification spec);
	
	public abstract T save(Object entity);
	
	public abstract void update(Object entity);
	
	public abstract T saveAndFlush(Object entity);
	
	public abstract List saveIterable(Iterable entities);
	
	public abstract Page pagedQuery(String hql, int start, int limit,
									Object... values);
	
	public abstract Page pagedSQLQuery(String sql, int start, int limit,
                                       Object... values);
}	
