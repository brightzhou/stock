package com.zeekie.stock.respository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zeekie.stock.entity.BaseEntrustDO;
import com.zeekie.stock.entity.CUrrentGuessProductDO;
import com.zeekie.stock.entity.CombassetDO;
import com.zeekie.stock.entity.CurrentEntrustDO;
import com.zeekie.stock.entity.GuessHistoryDO;
import com.zeekie.stock.entity.GuessProductDO;
import com.zeekie.stock.entity.ProductDO;
import com.zeekie.stock.entity.StockCodeDO;
import com.zeekie.stock.entity.WinerDO;
import com.zeekie.stock.mybatis.MyBatisRepository;
import com.zeekie.stock.service.homes.entity.EntrustEntity;
import com.zeekie.stock.service.homes.entity.EntrustQueryEntity;
import com.zeekie.stock.web.GuessDetailPage;
import com.zeekie.stock.web.GuessPage;
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
	public BaseEntrustDO queryEntrustInfo(@Param("nickname") String nickname, @Param("entrustNo") String entrustNo)
			throws Exception;

	/**
	 * 账户资金查询 注释：目前查询数据库即可。没有再次调用接口
	 * 
	 * @param nickname
	 * @return
	 */
	public CombassetDO queryCombasset(@Param("nickname") String nickname) throws Exception;

	/**
	 * 查询当日委托
	 * 
	 * @param nickname
	 * @return CurrentEntrustDO
	 */
	public List<CurrentEntrustDO> queryEntrust(@Param("nickname") String nickname) throws Exception;

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
	public List<CurrentEntrustDO> queryEntrustHistory(@Param("nickname") String nickname,
			@Param("startDate") String startDate, @Param("endDate") String endDate) throws Exception;

	/**
	 * 委托公共查询接口
	 * 
	 * @param entrustDO
	 * @return
	 * @throws Exception
	 */
	public List<CurrentEntrustDO> queryEntrustComm(CurrentEntrustDO entrustDO) throws Exception;

	/**
	 * 通过组合ID查询交易信息
	 * 
	 * @param combineId
	 * @param entrustTime
	 * @return
	 * @throws Exception
	 */
	public List<CurrentEntrustDO> queryTradeInfoByCombineId(@Param("combineId") String combineId,
			@Param("entrustTime") String entrustTime) throws Exception;

	/**
	 * 查询禁止买卖标志
	 * 
	 * @param nickname
	 * @return
	 * @throws Exception
	 */
	public String queryStopFlag(@Param("nickname") String nickname) throws Exception;

	/**
	 * 获取行数
	 * 
	 * @param stockCode
	 * @return
	 */
	public long queryStockCodeNum(@Param("stockCode") String stockCode) throws Exception;

	/**
	 * 获取列表
	 * 
	 * @param stockPage
	 * @return
	 */
	public List<StockCodeDO> queryStockCode(StockCodePage stockPage) throws Exception;

	/**
	 * 
	 * @return
	 */
	public List<String> queryAllStockCode();

	/**
	 * 设置产品状态
	 * 
	 * @param string
	 * @param string2
	 */
	public void updateProductStatus(@Param("code") String string, @Param("status") String string2) throws Exception;

	/**
	 * 获取产品
	 * 
	 * @param nickname
	 * @return
	 */
	public ProductDO queryProduct(@Param("nickname") String nickname) throws Exception;

	/**
	 * 更新哈哈币
	 * 
	 * @param nickname
	 * @param cash
	 * @param num
	 * @throws Exception
	 */
	public void updateHhb(@Param("nickname") String nickname, @Param("num") String num) throws Exception;

	/**
	 * 获取竞猜产品列表总数
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public long queryGuessproductTotal(@Param("date") String date) throws Exception;

	/**
	 * 获取竞猜产品列表
	 * 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	public List<GuessProductDO> queryGuessproduct(GuessPage product) throws Exception;

	/**
	 * 保存竞猜彩票
	 * 
	 * @param pumpedPercent
	 * 
	 * @param string
	 * 
	 * @param string
	 * @param string2
	 */
	public void saveGuessProduct(@Param("code") String code, @Param("name") String name,
			@Param("purchaseNum") String purchaseNum, @Param("pumpedPercent") String pumpedPercent) throws Exception;

	/**
	 * 获取哈哈币的单价
	 * 
	 * @return
	 */
	public String queryUnitPrice() throws Exception;

	/**
	 * 猜猜看
	 * 
	 * @param nickname
	 * @param num
	 * @param type
	 * @param bidCode
	 */
	public void updateGuess(@Param("nickname") String nickname, @Param("num") String num, @Param("type") String type,
			@Param("bidCode") String bidCode) throws Exception;

	/**
	 * 获取当前竞猜
	 * 
	 * @param nickname
	 * @return
	 */
	public CUrrentGuessProductDO queryCurrentGuessProduct(@Param("nickname") String nickname);

	public void updateGuessResult(@Param("type") String type, @Param("code") String code) throws Exception;

	/**
	 * 更新所有猜测结果
	 * 
	 * @param type
	 * @param code
	 */
	public void updateGuessRecord(@Param("type") String type, @Param("code") String code) throws Exception;

	/**
	 * 更新钱包哈哈币
	 * 
	 * @param code
	 * 
	 * @throws Exception
	 */
	public void addHhb(@Param("code") String code) throws Exception;

	/**
	 * 查看哈哈币是否够投注
	 * 
	 * @param nickname
	 * @param num
	 * @return
	 * @throws Exception
	 */
	public String queryHhb(@Param("nickname") String nickname, @Param("num") String num) throws Exception;

	/**
	 * 扣除哈哈币用于投注
	 * 
	 * @param nickname
	 * @param num
	 */
	public void modifyhhb(@Param("nickname") String nickname, @Param("num") String num) throws Exception;

	/**
	 * 卖出哈哈币，计算钱包余额
	 * 
	 * @param nickname
	 * @param num
	 * @param cash
	 */
	public void modifyhhbAndBalance(@Param("nickname") String nickname, @Param("num") String num,
			@Param("cash") String cash) throws Exception;

	/**
	 * 获取历史押注
	 * 
	 * @param userId
	 * @param offset
	 * @return
	 */
	public List<GuessHistoryDO> getHistoryGuess(@Param("userId") String userId, @Param("offset") String offset);

	/**
	 * 用户签到表
	 * 
	 * @param userId
	 * @throws Exception
	 */
	public void updateSignTable(@Param("userId") String userId) throws Exception;

	/**
	 * 查询签到情况
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public String querySignFlag(@Param("userId") String userId) throws Exception;

	/**
	 * 签到获取哈哈币
	 * 
	 * @param userId
	 */
	public void updateHhbbyId(@Param("userId") String userId) throws Exception;

	public long queryGuessDetailCount(@Param("bidCode") String bidCode) throws Exception;

	public List<GuessHistoryDO> queryGuessDetail(GuessDetailPage bizCode);

	/**
	 * 交易日设置状体为
	 */
	public void updateGuessStatus();

	/**
	 * 获取状体
	 * 
	 * @return
	 */
	public String queryGuessStatus();

	/**
	 * 查询哈哈币赢取的买家
	 * 
	 * @param code
	 * @return
	 */
	public List<WinerDO> queryWiner(@Param("code") String code);

}