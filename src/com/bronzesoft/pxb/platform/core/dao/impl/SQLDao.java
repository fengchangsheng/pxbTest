package com.bronzesoft.pxb.platform.core.dao.impl;

import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bronzesoft.pxb.platform.core.dao.ISQLDao;
import com.bronzesoft.pxb.platform.core.dao.util.ConnectionUtil;

@SuppressWarnings("unchecked")
public class SQLDao implements ISQLDao {

	private Connection conn;
	private PreparedStatement ps;

	public SQLDao() {
		try {
			this.conn = ConnectionUtil.getConnection(null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List search(String sql, Object... param) throws SQLException {
		if (conn == null) {
			return null;
		}

		ResultSet rs = null;
		List<Object> result = new ArrayList();

		try {
			ps = conn.prepareStatement(sql);

			if (param != null && param.length > 0) {
				for (int i = 1; i <= param.length; i++) {
					ps.setObject(i, param[i - 1]);
				}
			}

			rs = ps.executeQuery();

			ResultSetMetaData data = rs.getMetaData();
			int col = data.getColumnCount();

			if (col > 0) {
				if (col == 1) {
					while (rs.next()) {
						result.add(rs.getObject(1));
					}
				} else {
					Object[] res = null;

					while (rs.next()) {
						res = new Object[col];

						for (int i = 0; i < col; i++) {
							if (isClobOrText(data.getColumnTypeName(i + 1))) {
								res[i] = clob2String(rs.getClob(i + 1));
							} else if ("DATE".equalsIgnoreCase(data
									.getColumnTypeName(i + 1))) { // date type
								res[i] = rs.getTimestamp(i + 1);
							} else {
								res[i] = rs.getObject(i + 1);
							}
						}

						result.add(res);
					}
				}
			}
			return result;
		}

		finally {
			if (rs != null) {
				rs.close();
			}

			closeStatement();
		}
	}

	public void execute(String sb, List<Object> param) throws SQLException {
		try {
			if (conn == null) {
				return;
			}

			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sb);

			if (param != null) {
				for (int i = 0; i < param.size(); i++) {
					ps.setObject(i + 1, param.get(i));
				}
				ps.addBatch();
			}

			ps.executeBatch();
			conn.commit();

		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					throw e2;
				}
			}
			throw e;
		} finally {
			if (conn != null) {
				conn.setAutoCommit(true);
			}
			closeStatement();
		}
	}

	private void closeStatement() {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
			}
		}
	}

	private boolean isClobOrText(String dataType) {
		if (dataType == null) {
			return false;
		}

		dataType = dataType.toLowerCase().trim();
		if ("clob".equals(dataType) || "text".equals(dataType)) {
			return true;
		}

		return false;
	}

	private String clob2String(Clob clob) throws SQLException {
		if (clob == null) {
			return null;
		}

		Reader reader = clob.getCharacterStream();
		if (reader == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		char[] buf = new char[4096];
		try {
			for (int i = reader.read(buf); i > 0; i = reader.read(buf)) {
				sb.append(buf, 0, i);
			}
		} catch (IOException e) {
		}

		return sb.toString();
	}

}
