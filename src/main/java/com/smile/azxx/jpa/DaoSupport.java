/**
 * 
 */
package com.smile.azxx.jpa;

import com.smile.azxx.entity.common.Page;
import com.smile.azxx.entity.common.PageObject;
import com.smile.azxx.util.GenericsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.jdbc.core.InterruptibleBatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterDisposer;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author smile 2012-8-20
 */
public class DaoSupport<T> implements Dao<T> {

	public Logger log = LoggerFactory.getLogger(getClass());
	public Class<T> entityClass = GenericsUtils.getSuperClassGenricType(this
			.getClass());

	protected EntityManager getEm() throws NullPointerException {
		EntityManager _em = null;
		try {
			Field t3 = this.getClass().getDeclaredField("em");
			_em = (EntityManager) t3.get(this);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			Assert.isNull(_em, "no such field find!");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		if (_em == null) {
			throw new NullPointerException(
					"em is null , make sure that there is a field named em is defined in your daoimpl");
		}
		return _em;
	}

	private String getDeleteAllQueryString() {
		return QueryUtils.getQueryString("delete from %s x",
				this.entityClass.getName());
	}

	private String getCountQueryString() {
		String countQuery = String.format("select count(%s) from %s x",
				new Object[] { "*", "%s" });
		return QueryUtils
				.getQueryString(countQuery, this.entityClass.getName());
	}

	public void delete(Serializable id) {
		delete(findOne(id));
	}

	public void delete(Object entity) {
		getEm().remove(
				getEm().contains(entity) ? entity : getEm().merge(entity));
		this.flush();
	}

	public void delete(Iterable entities) {
		if (entities == null)
			return;
		Object entity;
		for (Iterator i$ = entities.iterator(); i$.hasNext(); delete(entity))
			entity = i$.next();
		this.flush();
	}

	public void deleteInBatch(Iterable entities) {
		if (null == entities || !entities.iterator().hasNext()) {
			return;
		} else {
			QueryUtils.applyAndBind(
					QueryUtils.getQueryString("delete from %s x",
							this.entityClass.getName()), entities, getEm())
					.executeUpdate();
			return;
		}
	}

	public void deleteAll() {
		getEm().createQuery(getDeleteAllQueryString()).executeUpdate();
	}

	public T findOne(Serializable id) {
		Assert.notNull(id, "The given id must not be null!");
		return getEm().find(this.entityClass, id);
	}

	public boolean exists(Serializable id) {
		String placeholder = "*";
		String entityName = this.entityClass.getName();
		String idAttributeName = "id";
		String existsQuery = String.format(
				"select count(%s) from %s x where x.%s = :id", new Object[] {
						placeholder, entityName, idAttributeName });
		Query query = getEm().createQuery(existsQuery);
		query.setParameter("id", id);
		return ((Long) query.getSingleResult()).longValue() == 1L;
	}

	public List findAll(Sort sort) {
		return getQuery(null, sort).getResultList();
	}

	public Object findOne(Specification spec) {
		return getQuery(spec, (Sort) null).getSingleResult();
	}

	public List findAll(Specification spec) {
		return getQuery(spec, (Sort) null).getResultList();
	}

	public List findAll(Specification spec, Sort sort) {
		return getQuery(spec, sort).getResultList();
	}

	public long count() {
		return ((Long) getEm().createQuery(getCountQueryString())
				.getSingleResult()).longValue();
	}

	public long count(Specification spec) {
		return ((Long) getCountQuery(spec).getSingleResult()).longValue();
	}

	public T save(Object entity) {
		getEm().persist(entity);
		this.flush();
		return (T) entity;
	}

	public void update(Object entity) {
		getEm().merge(entity);
		this.flush();
	}

	public T saveAndFlush(Object entity) {
		Object result = save(entity);
		flush();
		return (T) result;
	}

	public List saveIterable(Iterable entities) {
		List result = new ArrayList();
		if (entities == null)
			return result;
		Object entity;
		for (Iterator i$ = entities.iterator(); i$.hasNext(); result
				.add(save(entity)))
			entity = i$.next();

		return result;
	}

	public void flush() {
		getEm().flush();
	}

	private TypedQuery getQuery(Specification spec, Pageable pageable) {
		CriteriaBuilder builder = getEm().getCriteriaBuilder();
		CriteriaQuery query = builder.createQuery(this.entityClass);
		Root root = applySpecificationToCriteria(spec, query);
		query.select(root);
		if (pageable != null)
			query.orderBy(QueryUtils.toOrders(pageable.getSort(), root, builder));
		return getEm().createQuery(query);
	}

	private TypedQuery getQuery(Specification spec, Sort sort) {
		CriteriaBuilder builder = getEm().getCriteriaBuilder();
		CriteriaQuery query = builder.createQuery(this.entityClass);
		Root root = applySpecificationToCriteria(spec, query);
		query.select(root);
		if (sort != null)
			query.orderBy(QueryUtils.toOrders(sort, root, builder));
		return getEm().createQuery(query);
	}

	private TypedQuery getCountQuery(Specification spec) {
		CriteriaBuilder builder = getEm().getCriteriaBuilder();
		CriteriaQuery query = builder.createQuery();
		Root root = applySpecificationToCriteria(spec, query);
		query.select(builder.count(root));
		return getEm().createQuery(query);
	}

	private Root applySpecificationToCriteria(Specification spec,
                                              CriteriaQuery query) {
		Assert.notNull(query);
		Root root = query.from(this.entityClass);
		if (spec == null)
			return root;
		CriteriaBuilder builder = getEm().getCriteriaBuilder();
		javax.persistence.criteria.Predicate predicate = spec.toPredicate(root,
				query, builder);
		if (predicate != null)
			query.where(predicate);
		return root;
	}

	public List findAll() {
		return getQuery(null, (Sort) null).getResultList();
	}

	/**
	 * 分页查询
	 * 
	 * @param hql
	 *            查询语句
	 * @param isSQL
	 *            是否是源生sql
	 * @param oriCount
	 *            是否直接使用sql count
	 * @param start
	 *            开始
	 * @param limit
	 *            数量
	 * @param values
	 *            参数
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Page pagedQuery(String hql, boolean isSQL, boolean oriCount,
						   int start, int limit, Object... values) {
		int pageNo = start / limit + 1;
		Assert.hasText(hql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		// Count查询, 数据库在发现count的时候自动去除order by子句，无需在进行去除子句操作，不会影响效率
		String countQueryString = " select count(*) " + removeSelect(hql);
		String oriCountString = " select count(*) from (" + hql + ") count_tmp";
		Query query = null;
		long totalCount = 0;
		if (oriCount) {
			try {
				query = getQuery(oriCountString, isSQL, values);
				totalCount = Long.parseLong(query.getSingleResult().toString());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else {
			try {
				query = getQuery(countQueryString, isSQL, values);
				totalCount = Long.parseLong(query.getSingleResult().toString());
			} catch (Exception e) {
				e.printStackTrace();
				log.warn("Query deleted select count failed.");// 使用直接将查询转化为子查询的方式进行查询操作
				try {
					query = getQuery(oriCountString, isSQL, values);
					totalCount = Long.parseLong(query.getSingleResult()
							.toString());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		if (totalCount < 1)
			return new PageObject();
		// 实际查询返回分页对象
		query = getQuery(hql, isSQL, values);
		log.info("FirstResult=" + start + ";MaxResults=" + limit);
		query.setFirstResult(start);
		query.setMaxResults(limit);
		List list = null;
		list = query.getResultList();
		if (list == null) {
			list = new ArrayList();
		}
		return new PageObject(list, totalCount, pageNo, limit);
	}

	/**
	 * 生成jpa query对象
	 * 
	 * @param
	 * @param isSQL
	 * @param values
	 * @return
	 */
	protected Query getQuery(String queryStr, boolean isSQL, Object... values) {
		return getQuery(getEm(), queryStr, isSQL, values);
	}

	/**
	 * 生成jpa query对象
	 * 
	 * @param
	 * @param isSQL
	 * @param values
	 * @return
	 */
	protected Query getQuery(EntityManager em, String queryStr, boolean isSQL,
                             Object... values) {
		Query q = null;
		if (isSQL) {
			q = em.createNativeQuery(queryStr);
		} else {
			q = em.createQuery(queryStr);
		}
		if (values != null && values.length > 0) {
			for (int i = 0; i < values.length; i++) {
				q.setParameter(i + 1, values[i]);
			}
		}
		return q;
	}

	public Page pagedQuery(String hql, int start, int limit, Object... values) {
		return pagedQuery(hql, false, false, start, limit, values);
	}

	public Page pagedQuery(String hql, int start, int limit,
                           boolean oriHqlCount, Object... values) {
		return pagedQuery(hql, false, true, start, limit, values);
	}

	public Page pagedSQLQuery(String sql, int start, int limit,
                              Object... values) {
		return pagedQuery(sql, true, false, start, limit, values);
	}

	public Page pagedSQLQuery(String sql, int start, int limit,
                              boolean oriSqlCount, Object... values) {
		return pagedQuery(sql, true, true, start, limit, values);
	}

	/**
	 * 去除hql的select 子句，未考虑union的情况,用于pagedQuery.
	 * 
	 * @see #pagedQuery(String,int,int, Object[])
	 */
	private static String removeSelect(String hql) {
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf(" from ");
		if (beginPos != -1) {
			return hql.substring(beginPos);
		} else {
			return hql;
		}
	}

	/**
	 * 批量更新
	 * 
	 * @param tmp
	 * @param sql
	 * @param pss
	 * @return
	 */
	public int[] batchUpdate(JdbcTemplate tmp, String sql,
                             final MyBatchPreparedStatementSetter pss) {
		return batchUpdate(tmp, sql, pss, false);
	}

	/**
	 * 批量更新
	 * 
	 * @param tmp
	 * @param sql
	 * @param pss
	 * @param forceUnbatch
	 *            true，强制不进行批量preparestatement(Oracle返回更新条数不正确的问题)
	 * @return
	 */
	public int[] batchUpdate(JdbcTemplate tmp, String sql,
                             final MyBatchPreparedStatementSetter pss, boolean forceUnbatch) {
		if (forceUnbatch) {
			return tmp.execute(sql, new PreparedStatementCallback<int[]>() {
				public int[] doInPreparedStatement(PreparedStatement ps)
						throws SQLException {
					try {
						int batchSize = pss.getBatchSize();
						InterruptibleBatchPreparedStatementSetter ipss = (pss instanceof InterruptibleBatchPreparedStatementSetter ? (InterruptibleBatchPreparedStatementSetter) pss
								: null);
						List<Integer> rowsAffected = new ArrayList<Integer>();
						for (int i = 0; i < batchSize; i++) {
							pss.setValues(ps, i);
							if (ipss != null && ipss.isBatchExhausted(i)) {
								break;
							}
							rowsAffected.add(ps.executeUpdate());
						}
						int[] rowsAffectedArray = new int[rowsAffected.size()];
						for (int i = 0; i < rowsAffectedArray.length; i++) {
							rowsAffectedArray[i] = rowsAffected.get(i);
						}
						return rowsAffectedArray;
					} finally {
						if (pss instanceof ParameterDisposer) {
							((ParameterDisposer) pss).cleanupParameters();
						}
					}
				}
			});
		} else {
			return tmp.batchUpdate(sql, pss);
		}
	}

	// /**
	// * 去除hql的orderby 子句，用于pagedQuery.
	// *
	// * @see #pagedQuery(String,int,int,Object[])
	// */
	// private static String removeOrders(String hql) {
	// Assert.hasText(hql);
	// Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",
	// Pattern.CASE_INSENSITIVE);
	// Matcher m = p.matcher(hql);
	// StringBuffer sb = new StringBuffer();
	// while (m.find()) {
	// m.appendReplacement(sb, "");
	// }
	// m.appendTail(sb);
	// return sb.toString();
	// }
}
