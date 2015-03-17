package com.zeekie.stock.service.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.executor.result.DefaultResultContext;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class Mapper<K, V> extends SqlSessionDaoSupport {

	/****
	 * 返回Map对象<String,String>
	 * 
	 * @param paramString
	 *            执行的语句 配置
	 * @param para
	 *            参数
	 * @param mapKey
	 *            所返集合的key;此名字与查询语句中的列名对应,表示已那个字段作为key
	 * @param mapValue
	 *            所返集合的value,此名字与查询语句中的列名对应,表示已那个字段作为value
	 * @return
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<K, V> getMap(String paramString, BaseParam para, String mapKey,
			String mapValue) {
		ObjectFactory objectFactory = this.getSqlSession().getConfiguration()
				.getObjectFactory();
		List list = this.getSqlSession().selectList(paramString, para,
				RowBounds.DEFAULT);
		ObjectWrapperFactory objectWrapperFactory = this.getSqlSession()
				.getConfiguration().getObjectWrapperFactory();

		MapResultHandler mapResultHandler = new MapResultHandler(mapKey,
				mapValue, objectFactory, objectWrapperFactory);

		DefaultResultContext context = new DefaultResultContext();
		for (Iterator i$ = list.iterator(); i$.hasNext();) {
			Object o = i$.next();
			context.nextResultObject(o);
			mapResultHandler.handleResult(context);
		}
		return mapResultHandler.getMappedResults();
	}
}
