package com.bronzesoft.pxb.platform.core.dao;

import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("unchecked")
public interface ISQLDao {

	public List search(String sql, Object... param) throws SQLException;

	public void execute(String sb, List<Object> param) throws SQLException;

}
