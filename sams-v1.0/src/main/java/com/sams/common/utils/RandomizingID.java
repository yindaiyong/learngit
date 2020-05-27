package com.sams.common.utils;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.sams.common.scanner.SpringUtils;
import com.sams.custom.mapper.InterfaceColInfoMapper;


public class RandomizingID {
	
	@Autowired
	private InterfaceColInfoMapper interfaceColInfoMapper;
	
    /**
     * 随机号时间格式
     */
    private String timeFormatter = "yyyyMMddHHmmss";
 
    private SimpleDateFormat sdf = null;
    
    private static SimpleDateFormat nextSecond = new SimpleDateFormat("SSS");
 
    /**
     * 秒（一秒有多少毫秒）
     */
    private long secondUnit = 2000;
 
    /**
     * 随机数字位数
     */
    private int randomNumLength = 3;
 
    /**
     * 编号前缀
     */
    private String prefix = "";
 
    /**
     * 是否颠倒字符串
     */
    private boolean reverse = false;
 
    /**
     * 上一次生成时间
     */
    private String preGenTime = "";
 
    /**
     * 当前时间最大请求数量
     */
    private double maxRequest;
 
    /**
     * 数字格式
     */
    private String numFormat;
 
    /**
     * 当前秒内生成订单号的随机数字
     */
    private Set<Long> currentSecondRandomNos = Collections.synchronizedSet(new HashSet<Long>());
 
    public RandomizingID() {
        sdf = new SimpleDateFormat(timeFormatter);
    }
 
    /**
     * @param randomNumLength 随机数字位数
     */
    public RandomizingID(int randomNumLength) {
        this.randomNumLength = randomNumLength;
        this.maxRequest = Math.pow(10, this.randomNumLength);
        this.numFormat = MessageFormat.format("%0{0}d", randomNumLength);
    }
 
    /**
     * @param timeFormatter   时间格式
     * @param randomNumLength 随机数字位数
     * @param reverse         是否颠倒
     */
    public RandomizingID(String timeFormatter, int randomNumLength, boolean reverse) {
        if (StringUtils.isNotBlank(timeFormatter)) {
            this.timeFormatter = timeFormatter.trim();
            sdf = new SimpleDateFormat(this.timeFormatter);
        }
        this.randomNumLength = randomNumLength;
        this.reverse = reverse;
        this.maxRequest = Math.pow(10, this.randomNumLength);
        this.numFormat = MessageFormat.format("%0{0}d", randomNumLength);
    }
 
    /**
     * @param prefix          前缀
     * @param timeFormatter   时间格式
     * @param randomNumLength 随机数字位数
     * @param reverse         是否颠倒
     */
    public RandomizingID(String prefix, String timeFormatter, int randomNumLength, boolean reverse) {
        if (StringUtils.isNotBlank(prefix))
            this.prefix = prefix.trim();
        if (StringUtils.isNotBlank(timeFormatter)) {
            this.timeFormatter = timeFormatter.trim();
            sdf = new SimpleDateFormat(this.timeFormatter);
        }
        this.randomNumLength = randomNumLength;
        this.reverse = reverse;
        this.maxRequest = Math.pow(10, this.randomNumLength);
        this.numFormat = MessageFormat.format("%0{0}d", randomNumLength);
    }
 
    /**
     * 生成随机号
     *
     * @return
     */
    public synchronized String genNewId() {
        if (currentSecondRandomNos.size() >= maxRequest) {
            try {
                Thread.currentThread();
                //计算当前秒距离下一秒还有多少毫秒
                long sleepTime = secondUnit - Long.valueOf(nextSecond.format(DateUtils.getOracleSysDate()));
                Thread.sleep(sleepTime);// 休息到下一秒
               // Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }                   
        }
        // 20200507 生成的号码与直销重复    时间往后加200年
        Calendar c = Calendar.getInstance();
        Date date = DateUtils.getOracleSysDate(); //当前时间
        c.setTime(date);
        c.add(Calendar.YEAR, 200);
        String currentTime = sdf.format(c.getTime());
        
        
        if (!preGenTime.equals(currentTime)) {
            preGenTime = currentTime;
            currentSecondRandomNos.clear();
        }
        long random = getRandom();// 获取序列
        currentSecondRandomNos.add(random);
 
        String formated = String.format(numFormat, random);
        String id = currentTime + formated;
        if (reverse)
            id = StringUtils.reverse(id);
        return prefix + id;
    }
    
    
    /**
     * 生成日期加序列
     *
     * @return
     */
    public  String genNextSequence() {
    	interfaceColInfoMapper=(InterfaceColInfoMapper) SpringUtils.getBean(InterfaceColInfoMapper.class);
        /*if (currentSecondRandomNos.size() >= maxRequest) {
            try {
                Thread.currentThread();
                //计算当前秒距离下一秒还有多少毫秒
                long sleepTime = secondUnit - Long.valueOf(nextSecond.format(new Date()));
                Thread.sleep(sleepTime);// 休息到下一秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }                   
        }*/
        String currentTime = sdf.format(DateUtils.getOracleSysDate());
        if (!preGenTime.equals(currentTime)) {
            preGenTime = currentTime;
            currentSecondRandomNos.clear();
        }
        String sequenceId=interfaceColInfoMapper.selectNextSequence();
        if(sequenceId.length()>=4){
        	sequenceId=sequenceId.substring(sequenceId.length()-4);
        }else{
        	sequenceId=String.format("%04d", Integer.parseInt(sequenceId));
        }
        long random = Long.parseLong(sequenceId);
        currentSecondRandomNos.add(random);
 
        String formated = String.format("%04d", random);
        String id = currentTime + formated;
        if (reverse)
            id = StringUtils.reverse(id);
        return prefix + id;
    }
 
    /**
     * 获得随机数字
     *
     * @return
     */
    private Long getRandom() {
    	// 20200110 yindaiyong 用随机数会有重复
    	interfaceColInfoMapper=(InterfaceColInfoMapper) SpringUtils.getBean(InterfaceColInfoMapper.class);
        Long sequenceId = interfaceColInfoMapper.selectRandomSequence();
        return sequenceId;
    }
 
    public static void main(String[] args) {
/*        RandomizingID random = new RandomizingID("", "yyyyMMddHHmmss", 6, false);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            System.out.println(random.genNewId());
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);*/
    	
    	
    	BigDecimal applicationAmount=new BigDecimal(1000000);
    	BigDecimal indiBidsDiffAmtBig=new BigDecimal(10000);	
    	
    	BigDecimal DiffAmtBig=applicationAmount.divideAndRemainder(indiBidsDiffAmtBig)[1];
    	
    	
    	System.out.print(DiffAmtBig.toString().substring(DiffAmtBig.toString().indexOf(".")+1));
    }
 
} 
