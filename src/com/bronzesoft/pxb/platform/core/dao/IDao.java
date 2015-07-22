package com.bronzesoft.pxb.platform.core.dao;

import java.io.Serializable;
import java.util.List;

import com.bronzesoft.pxb.platform.exception.DBException;

@SuppressWarnings("unchecked")
public interface IDao {

	/**
	 * 获取JPA对象
	 * 
	 * @param entityClass 对象类的类型
	 * @param id 对象ID
	 * @return
	 * @throws DBException
	 */
	public Object getObject(Class entityClass, Serializable id)
			throws DBException;

	/**
	 * 保存JPA对象
	 * 
	 * @param entity
	 * @throws DBException
	 */
	public void saveObject(Object entity) throws DBException;
	
	/**
	 * 更新JPA对象
	 * 
	 * @param entity
	 * @throws DBException
	 */
	public void updateObject(Object entity) throws DBException;
	
	/**
	 * 删除JPA对象
	 * 
	 * @param entityClass 对象类的类型
	 * @param id 对象ID
	 * @throws DBException
	 */
	public void removeObject(Class entityClass, Serializable id)
			throws DBException;

	/**
	 * 通过HQL查询数据，无分页
	 * 
	 * @param hql HQL语句
	 * @param values HQL语句参数
	 * @return
	 * @throws DBException
	 */
	public List query(String hql, Object[] values) throws DBException;

	/**
	 * 通过HQL查询数据
	 * 
	 * @param hql HQL语句
	 * @param targetPage 当前分页
	 * @param pageSize 分页大小
	 * @param values HQL语句参数
	 * @return
	 * @throws DBException
	 */
	public List query(String hql, int targetPage, int pageSize, Object[] values)
			throws DBException;

	/**
	 * 通过HQL更新数据
	 * 
	 * @param hql HQL语句
	 * @param values HQL语句参数
	 * @throws DBException
	 */
	public void update(String hql, Object[] values) throws DBException;

	/**
	 * 通过HQL删除数据
	 * 
	 * @param hql HQL语句
	 * @param values HQL语句参数
	 * @throws DBException
	 */
	public void remove(String hql, Object[] values) throws DBException;

	

}
