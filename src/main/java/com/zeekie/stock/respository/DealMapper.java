package com.zeekie.stock.respository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zeekie.stock.entity.BaseEntrustDO;
import com.zeekie.stock.entity.CombassetDO;
import com.zeekie.stock.entity.CurrentEntrustDO;
import com.zeekie.stock.entity.StockCodeDO;
import com.zeekie.stock.mybatis.MyBatisRepository;
import com.zeekie.stock.service.homes.entity.EntrustEntity;
import com.zeekie.stock.service.homes.entity.EntrustQueryEntity;
import com.zeekie.stock.web.StockCodePage;

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
	public BaseEntrustDO queryEntrustInfo(@Param("nickname") String nickname,
			@Param("entrustNo") String entrustNo) throws Exception;

	/**
	 * 账户资金查询 注释：目前查询数据库即可。没有再次调用接口
	 * 
	 * @param nickname
	 * @return
	 */
	public CombassetDO queryCombasset(@Param("nickname") String nickname)
			throws Exception;

	/**
	 * 查询当日委托
	 * 
	 * @param nickname
	 * @return CurrentEntrustDO
	 */
	public List<CurrentEntrustDO> queryEntrust(
			@Param("nickname") String nickname) throws Exception;

	/**
	 * 更新当前委托信息
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public void updateEntrust(EntrustQueryEntity entity) throws Exception;

	/**
	 * 获取历史委托信息
	 * 
	 * @param nickname
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws Exception
	 */
	public List<CurrentEntrustDO> queryEntrustHistory(
			@Param("nickname") String nickname,
			@Param("startDate") String startDate,
			@Param("endDate") String endDate) throws Exception;

	/**
	 * 委托公共查询接口
	 * 
	 * @param entrustDO
	 * @return
	 * @throws Exception
	 */
	public List<CurrentEntrustDO> queryEntrustComm(CurrentEntrustDO entrustDO)
			throws Exception;

	/**
	 * 通过组合ID查询交易信息
	 * 
	 * @param combineId
	 * @param entrustTime
	 * @return
	 * @throws Exception
	 */
	public List<CurrentEntrustDO> queryTradeInfoByCombineId(
			@Param("combineId") String combineId,
			@Param("entrustTime") String entrustTime) throws Exception;

	/**
	 * 查询禁止买卖标志
	 * 
	 * @param nickname
	 * @return
	 * @throws Exception
	 */
	public String queryStopFlag(@Param("nickname") String nickname)
			throws Exception;

	/**
	 * 获取行数
	 * 
	 * @param stockCode
	 * @return
	 */
	public long queryStockCodeNum(@Param("stockCode") String stockCode)
			throws Exception;

	/**
	 * 获取列表
	 * 
	 * @param stockPage
	 * @return
	 */
	public List<StockCodeDO> queryStockCode(StockCodePage stockPage)
			throws Exception;

	/**
	 * 
	 * @return
	 */
	public List<String> queryAllStockCode();

}