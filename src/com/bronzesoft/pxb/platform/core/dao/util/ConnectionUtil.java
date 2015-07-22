package com.bronzesoft.pxb.platform.core.dao.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;

import com.bronzesoft.pxb.platform.core.dao.vo.ConnectionVo;

@SuppressWarnings("unchecked")
public class ConnectionUtil {

	public static Map<String, BasicDataSource> dataSources = null;

	@SuppressWarnings("deprecation")
	public static Connection getConnection(ConnectionVo param)
			throws SQLException {
		if (dataSources == null) {
			dataSources = new HashMap<String, BasicDataSource>();
		}

		Connection conn = null;
		if (param != null) {
			BasicDataSource ds = dataSources.get(param.getUrl());

			if (ds != null) {
				conn = ds.getConnection();
			} else {
				ds = new BasicDataSource();

				// dataSource information
				ds.setDriverClassName(param.getDriverClassName());
				ds.setUrl(param.getUrl());
				ds.setUsername(param.getUsername());
				ds.setPassword(param.getPassword());

				// max active
				if (param.getMaxActive() > 0) {
					ds.setMaxActive(param.getMaxActive());
				}

				// max idle
				if (param.getMaxIdle() > 0) {
					ds.setMaxIdle(param.getMaxIdle());
				}

				// max wait
				if (param.getMaxWait() > 0) {
					ds.setMaxWait(param.getMaxWait());
					ds.setRemoveAbandoned(true);
					ds.setRemoveAbandonedTimeout(param.getMaxWait() / 100 / 2);
				}

				conn = ds.getConnection();
				dataSources.put(param.getUrl(), ds);
			}
		}

		return conn;
	}

}
