package com.sams.batchfile.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sams.batchfile.service.RollBackChannelService;
import com.sams.custom.mapper.RollBackChannelMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @ClassName RollBackChannelServiceImpl
 * 描述 : 回滚渠道下所有数据信息
 * @Author weijunjie
 * @Date 2019/12/17 9:16
 */
@Service
public class RollBackChannelServiceImpl implements RollBackChannelService {

    @Autowired
    private RollBackChannelMapper rollBackChannelMapper;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    @Transactional(rollbackFor=Exception.class)
    public String rollbackChannel(String channelCode){
        String res = "清除成功";
        JSONObject restJSON = new JSONObject();
        if(StringUtils.isBlank(channelCode)) return "渠道code为null，删除失败";
        log.info("开始执行清除渠道{}数据操作",channelCode);
        try {
            int c = 0;
            c = rollBackChannelMapper.deleteFromProductInfo(channelCode);
            log.info("删除渠道{}下产品信息表数据{}条",channelCode,c);
            restJSON.put("产品信息表",c);
            c = rollBackChannelMapper.deleteFromChannelProduct(channelCode);
            log.info("删除渠道{}下渠道产品关联关系表数据{}条",channelCode,c);
            restJSON.put("渠道产品关联关系表",c);
            c = rollBackChannelMapper.deleteFromCPR(channelCode);
            log.info("删除渠道{}下产品收益率表数据{}条",channelCode,c);
            restJSON.put("收益率表",c);
            c = rollBackChannelMapper.deleteFromAccountStat(channelCode);
            log.info("删除渠道{}下账户状态表数据{}条",channelCode,c);
            restJSON.put("账户状态表",c);
            c = rollBackChannelMapper.deleteFromContractInfo(channelCode);
            log.info("删除渠道{}下合同信息表数据{}条",channelCode,c);
            restJSON.put("合同信息表",c);
            c = rollBackChannelMapper.deleteFromAccountReq(channelCode);
            log.info("删除渠道{}下账户申请表数据{}条",channelCode,c);
            restJSON.put("账户申请表",c);
            c = rollBackChannelMapper.deleteFromAccountReqCfm(channelCode);
            log.info("删除渠道{}下账户确认表数据{}条",channelCode,c);
            restJSON.put("账户确认表",c);
            c = rollBackChannelMapper.deleteFromExchangeReq(channelCode);
            log.info("删除渠道{}下交易申请表数据{}条",channelCode,c);
            restJSON.put("交易申请表",c);
            c = rollBackChannelMapper.deleteFromExchangeReqCfm(channelCode);
            log.info("删除渠道{}下交易确认表数据{}条",channelCode,c);
            restJSON.put("交易确认表",c);
            c = rollBackChannelMapper.deleteFromContractReq(channelCode);
            log.info("删除渠道{}下电子合同申请表数据{}条",channelCode,c);
            restJSON.put("电子合同申请表",c);
            c = rollBackChannelMapper.deleteFromContractCfm(channelCode);
            log.info("删除渠道{}下电子合同确认表数据{}条",channelCode,c);
            restJSON.put("电子合同确认表",c);
            c = rollBackChannelMapper.deleteFromRTIR(channelCode);
            log.info("删除渠道{}下非居民申请表数据{}条",channelCode,c);
            restJSON.put("非居民申请表",c);
            c = rollBackChannelMapper.deleteFromRTIC(channelCode);
            log.info("删除渠道{}下非居民确认表数据{}条",channelCode,c);
            restJSON.put("非居民确认表",c);
            c = rollBackChannelMapper.deleteFromDividendCfm(channelCode);
            log.info("删除渠道{}下分红表数据{}条",channelCode,c);
            restJSON.put("分红表",c);
            c = rollBackChannelMapper.deleteFromPOD(channelCode);
            log.info("删除渠道{}下非交易日表数据{}条",channelCode,c);
            restJSON.put("非交易日表",c);
            c = rollBackChannelMapper.deleteFromAccountRecon(channelCode);
            log.info("删除渠道{}下对账表数据{}条",channelCode,c);
            restJSON.put("对账表",c);
            c = rollBackChannelMapper.deleteFromFundVolCfm(channelCode);
            log.info("删除渠道{}下26文件数据表数据{}条",channelCode,c);
            restJSON.put("26文件",c);
            c = rollBackChannelMapper.deleteFromAccountInfo(channelCode);
            log.info("删除渠道{}下账户信息数据表数据{}条",channelCode,c);
            restJSON.put("账户信息",c);
            res += ":"+restJSON.toJSONString();
        }catch (Exception e){
            log.error("回滚渠道数据发生异常，{}",e);
            res = "回滚失败，联系管理员";
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return res;
    }
}