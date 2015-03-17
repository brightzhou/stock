package com.zeekie.stock.service.dao;

import java.util.Map;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class MapResultHandler<K, V> implements ResultHandler {

	private final Map mappedResults;

	private final String mapKey;

	private final ObjectFactory objectFactory;

	private final ObjectWrapperFactory objectWrapperFactory;

	private final String mapValue;

	public MapResultHandler(String mapKey, String mapValue,
			ObjectFactory objectFactory,
			ObjectWrapperFactory objectWrapperFactory) {
		this.objectFactory = objectFactory;
		this.objectWrapperFactory = objectWrapperFactory;
		this.mappedResults = ((Map) objectFactory.create(Map.class));
		this.mapKey = mapKey;
		this.mapValue = mapValue;
	}

	@Override
	public void handleResult(ResultContext context) {
		Object value = context.getResultObject();
		Object key = null;
		if (value instanceof Map) {
			Map<K, V> temp = (Map<K, V>) value;
			value = temp.get(this.mapValue);
			key = temp.get(this.mapKey);
		}

		if (key == null) {
			MetaObject mo = MetaObject.forObject(context.getResultObject(),
					this.objectFactory, this.objectWrapperFactory);
			key = mo.getValue(this.mapKey);

		}
		this.mappedResults.put(key, value);

	}

	public Map<K, V> getMappedResults() {
		return this.mappedResults;
	}

}
