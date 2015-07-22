package com.bronzesoft.pxb.platform.core.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bronzesoft.pxb.platform.core.dao.IDao;
import com.bronzesoft.pxb.platform.exception.DBException;

@SuppressWarnings("unchecked")
public class Dao implements IDao {

	private final static int NO_CACHE = 0; // no cache;
	private final static int QUERY_CACHE = 1; // query cache;
	private final static int OBJECT_CACHE = 2; // object cache;
	
	private SessionFactory sessionFactory;

	private Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		System.out.println(session==null);
		return session;
	}
	
	private Session openSession() {
		return sessionFactory.openSession();
	}
	
	public Object getObject(Class entityClass, Serializable id)
			throws DBException {
		if (id == null) {
			return null;
		}
		return this.getSession().get(entityClass, id);
	}

	private Object loadObject(Class entityClass, Serializable id)
			throws DBException {
		return this.getSession().load(entityClass, id);
	}

	public List query(String hql, Object[] values) throws DBException {
		return this.query(hql, -1, -1, values);
	}

	public List query(String hql, int targetPage, int pageSize, Object[] values)
			throws DBException {
		return this.query(hql, targetPage, pageSize, 0, NO_CACHE, values);
	}

	private List query(String hql, int targetPage, int pageSize, int start,
			int cacheType, Object[] values) {
		Session session = null;
		try {
			start = start < 0 ? 0 : start;
			session = this.openSession();
			Query query = session.createQuery(hql);

			// split page
			if (targetPage > 0 && pageSize >= 0) {
				query.setFirstResult(start + (targetPage - 1) * pageSize);
				query.setMaxResults(pageSize);
			}

			// query parameters
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i, values[i]);
				}
			}

			// cache type
			switch (cacheType) {
			case NO_CACHE:
				query.setCacheable(false);
				return query.list();
			case OBJECT_CACHE:
				Iterator iterator = query.iterate();
				List retList = new ArrayList();
				while (iterator.hasNext()) {
					retList.add(iterator.next());
				}
				return retList;
			case QUERY_CACHE:
				query.setCacheable(true);
				return query.list();
			}

			// no cache
			return query.list();

		} catch (Exception e) {
			throw new DBException(e.getMessage(), e);
		} finally {
			session.close();
		}
	}

	public void saveObject(Object entity) throws DBException {
		try {
			this.getSession().save(entity);
		} catch (Exception e) {
			throw new DBException(e.getMessage());
		}
	}

	public void updateObject(Object entity) throws DBException {
		this.getSession().update(entity);
	}

	public void update(String hql, Object[] values) throws DBException {
		execute(hql, values);
	}

	private void execute(String hql, Object[] values) throws DBException {
		Session session = null;
		try {
			session = this.openSession();
			Query query = session.createQuery(hql);

			if (values != null) { // prevent null point exception
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i, values[i]);
				}
			}

			query.executeUpdate();
		} catch (Exception e) {
			throw new DBException(e.getMessage());
		} finally {
			session.close();
		}
	}

	public void remove(String hql, Object[] values) throws DBException {
		execute(hql, values);
	}

	public void removeObject(Class entityClass, Serializable id)
			throws DBException {
		Object o = this.loadObject(entityClass, id);
		this.removeObject(o);
	}

	private void removeObject(Object entity) throws DBException {
		this.getSession().delete(entity);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
