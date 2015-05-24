package com.zeekie.stock.respository;

import org.apache.ibatis.annotations.Param;

import com.zeekie.stock.entity.BaseEntrustDO;
import com.zeekie.stock.entity.CombassetDO;
import com.zeekie.stock.mybatis.MyBatisRepository;
import com.zeekie.stock.service.homes.entity.EntrustEntity;

@MyBatisRepository
public interface DealMapper {

	/**
	 * 插入委托交易
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public void insertEntrust(EntrustEntity entity) throws Exception;

	/**
	 * 基础委托信息
	 * 
	 * @param nickname
	 * @return
	 * @throws Exception
	 */
	public BaseEntrustDO queryEntrustInfo(@Param("nickname") String nickname)
			throws Exception;

	/**
	 * 账户资金查询 注释：目前查询数据库即可。没有再次调用接口
	 * 
	 * @param nickname
	 * @return
	 */
	public CombassetDO queryCombasset(@Param("nickname") String nickname)
			throws Exception;

}