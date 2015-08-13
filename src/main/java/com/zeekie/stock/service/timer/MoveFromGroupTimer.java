package com.zeekie.stock.service.timer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sitong.thinker.common.util.mybatis.BatchMapper;

import com.zeekie.stock.chat.Hxhelper;
import com.zeekie.stock.entity.UserInfoDO;
import com.zeekie.stock.respository.AcountMapper;

@Service
@Transactional
public class MoveFromGroupTimer {

	static Logger log = LoggerFactory.getLogger(MoveFromGroupTimer.class);

	@Autowired
	private AcountMapper acount;

	@Autowired
	private BatchMapper mapper;

	public void moveFromgroup() throws RuntimeException {

		List<UserInfoDO> result;
		try {
			result = acount.getOnlineIn30DaysUser();
			if (null != result) {
				if (log.isDebugEnabled()) {
					log.debug("将时隔30天的用户移除群[{}]", result.size());
				}
				move(result);

				mapper.batchInsert(AcountMapper.class, "updateInGroupStatus",
						result);
			}
		} catch (Exception e1) {
			log.error(e1.getMessage(), e1);
			throw new RuntimeException();
		}

	}

	private void move(List<UserInfoDO> result) throws Exception {
		Hxhelper.deleteFromgroup(result);
	}

}
