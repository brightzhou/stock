package com.zeekie.stock.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.FloatTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.zeekie.stock.util.StringUtil;

public class TwoDecimalFloatTypeHander extends FloatTypeHandler {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i,
			Float parameter, JdbcType jdbcType) throws SQLException {
		ps.setFloat(i, parameter);
	}

	@Override
	public Float getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		// TODO Auto-generated method stub
		return StringUtil.keepTwoDecimalFloat(rs.getFloat(columnName));
	}

	@Override
	public Float getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return StringUtil.keepTwoDecimalFloat(cs.getFloat(columnIndex));
	}

	@Override
	public Float getResult(ResultSet rs, String columnName) throws SQLException {
		// TODO Auto-generated method stub
		return StringUtil.keepTwoDecimalFloat(rs.getFloat(columnName));
	}
}
