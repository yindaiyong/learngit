package com.sams.common.constant;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 全局常量
 */
public class Const {
	
	/**
	 * ServletContext中,保存在线用户Session信息
	 * 		 parm:'sessions'  value:HashMap<String,HttpSession>{Map中key:userId}
	 * session
	 * 		 parm:userId value:principals
	 */
	public static final String SESSION_USER_ID = "session_userId";//用户ID
	
	/**
	 * 用户状态
	 */
	public static final String USER_TATUS_OFF = "0";//停用
	
	public static final String CFM_WAY_1 = "1";
	
	/**
	 * 资源类型
	 */
	public static final String RESOURCE_MENU = "1"; // 菜单
	public static final String RESOURCE_BUTTON = "2"; // 按钮
	
	/**
	 * 批次号开关
	 */
	public static final String BatchNoOn = "1"; // 批次号开关开
	public static final String BatchNoOff = "0"; // 批次号开关开
	
	/**
	 * 资源状态
	 */
	public static final String RESOURCE_TATUS_ON = "1";//启用
	public static final String RESOURCE_TATUS_OFF = "0";//停用
	
	public static final String FIRST_TRADE="1";
	
	public static final String FUND_TYPE="fundType";

	/**
	 * 字典类型
	 */
	public static final String DICT_TYPE_LOG_TYPE = "logType"; //日志类型

	/**
	 * 日志类型
	 */
	public static final String LOG_TYPE_SYS = "system";//系统日志
	public static final String LOG_TYPE_LOGIN = "login";//登陆日志
	public static final String LOG_TYPE_EXCEPTION = "exception";//导常日志
	
	
	/**
	 * 数据源
	 */
	public static final String DATASOURCE_MASTER = "master";
	public static final String DATASOURCE_SLAVE = "slave";
	
	/**
	 * 产品状态
	 *  0-可申购赎回，1-发行
	 *	4-停止申购赎回
	 *	5-停止申购，6-停止赎回
	 *	8-基金终止，9-基金封闭 
	 *
	 */
	public static final String CAN_PURCHASE_REDEMPTION = "0";//0-可申购赎回
	public static final String PUBLISH = "1";//1-发行
	public static final String CANNOT_PURCHASE_REDEMPTION  = "4";//4-停止申购赎回
	public static final String CANNOT_PURCHASE = "5";//5-停止申购
	public static final String CANNOT_REDEMPTION = "6";//6-停止赎回
	public static final String FUND_TERMINATION = "8";//8-基金终止
	public static final String FUND_SEALING = "9";//9-基金封闭 
	
	/**
	 * 处理流程编码   000000 行情
	 */
	public static final String FUND_MARKET_PROCESS_CODE_000000 = "000000";//行情处理编码
    public static final String FUND_MARKET_PROCESS_CODE_000001 = "000001";//行情数据处理编码
	public static final String FUND_MARKET_PROCESS_CODE_000002 = "000002";//行情生成编码
	public static final String FUND_MARKET_PROCESS_CODE_000003 = "000003";//行情发送文件编码
	public static final String FUND_MARKET_PROCESS_CODE_000004 = "000004";//行情确认修改发送状态

	
	/**
	 * 确认流程
	 */
	public static final String ACCOUNT_DATA_STEP = "111111";//数据处理
	public static final String ACCOUNT_CONFIRM_STEP = "222222";//确认处理
	public static final String SEND_FILE_STEP = "333333";//发送处理
	
	
	
	public static final String INDIVIDUAL="1";//个人
	public static final String INSTITUTION="0";//机构
	
	/**
	 * FTP上传 下载目录
	 */
	
	public static final String FTP_UPLOAD="upload";
	
	public static final String FTP_DOWNLOAD="download";
	
	public static final String FTP_REMOTE_UPLOAD_DIR = "send";
	
	public static final String UPLOADSHNS07 = "fundday";
	
	public static final String UPLOADSHNSOTHER = "confirm";
	
	public static final String FTP_REMOTE_DOWNLOAD_DIR = "receive";

	
	/**
	 * 业务状态 01 成功  00 失败  （操作步骤 00删除 01 新增 02 修改 03 复核 04 暂停 05 启用 06 复制 10 停用 99产品删除）
	 */
	public static final String BUSINESS_STUTAS_01="01";
	
	public static final String BUSINESS_STUTAS_10="10";
	
	public static final String BUSINESS_STUTAS_00="00";
	
	public static final String BUSINESS_STUTAS_02="02";
	
	public static final String BUSINESS_STUTAS_03="03";
	
	public static final String BUSINESS_STUTAS_04="04";
	
	public static final String BUSINESS_STUTAS_05="05";
	
	public static final String BUSINESS_STUTAS_06="06";
	
	public static final String BUSINESS_STUTAS_99="99";
	
	//产品类型 1.丰利B类    3.红宝石7天   4.固收类  5.封闭净值类 6.t+n 7.权益类  8.多币种  9.FOF 0.丰利F
	public static final String FUND_TYPE_01="1";//丰利B
	public static final String FUND_TYPE_02="2";//丰利D
	public static final String FUND_TYPE_03="3";//红宝石
	public static final String FUND_TYPE_04="4";//固收
	public static final String FUND_TYPE_05="5";//封闭净值
	public static final String FUND_TYPE_06="6";//t+n
	public static final String FUND_TYPE_07="7";//权益类
	public static final String FUND_TYPE_08="8";//多币种
	public static final String FUND_TYPE_09="9";//FOF类
	public static final String FUND_TYPE_00="0";//丰利F类
	public static final String FUND_TYPE_123="1,2,3"; //货币类区分，用于查持仓
	public static final String FUND_TYPE_0456="0,4,5,6"; // 非货币类
	public static final String FUND_TYPE_789="7,8,9"; 
	public static final String FUND_TYPE_056="0,5,6"; //取142确认数据区分
	public static final String FUND_TYPE_79="7,9"; //取122,124确认区分
	public static final String FUND_TYPE_89="8,9"; //QD区分，用于查持仓
	public static final String FUND_TYPE_456789="4,5,6,7,8,9"; // 大类非货币类，对账取值
	public static final String FUND_TYPE_123789="1,2,3,7,8,9"; // 发交易区分首次
	
	public static final String FUND_TYPE_NAME="fundType";
	
	
	public static final String DTAE_FORMAT_YYYYMMDD="yyyyMMdd";
	
	public static final String FORWARD_DAY_15="15";
	/**
	 * 编码格式
	 */
	public static final String GB_18030 = "GB18030";
	
	/**
	 * 0-可转入，可转出
	 *	1-只可转入
	 *	2-只可转出
	 *	3-不可转换
	 */
	public static final String CONVERT_STATUS_00="0";
	public static final String CONVERT_STATUS_01="1";
	public static final String CONVERT_STATUS_02="2";
	public static final String CONVERT_STATUS_03="3";
	
	
	/**
	 * 文件类型
	 */
	public static final String FILE_TYPE_94="94";
	public static final String FILE_TYPE_07="07";
	public static final String FILE_TYPE_44="44";
	public static final String FILE_TYPE_02="02";
	public static final String FILE_TYPE_04="04";
	public static final String FILE_TYPE_05="05";
	public static final String FILE_TYPE_06="06";
	public static final String FILE_TYPE_R2="R2";
	public static final String FILE_TYPE_26="26";
	
	public static final String FILE_TYPE_01="01";
	public static final String FILE_TYPE_03="03";
	public static final String FILE_TYPE_43="43";
	public static final String FILE_TYPE_R1="R1";
	public static final String ALLFILETYPE="02,04,05,06,44,R2";
	
	
	public static final String SEND_FILE_TYPE = "02,04,05,06,07,44,94,R2,26";
	public static final String RECV_FILE_TYPE = "01,03,43,R1";
	/**
	 * 文件类型与实际存储类型匹配
	 */
	public static Map<String,String> fileTypeMap = new HashMap<String, String>(){
		private static final long serialVersionUID = 1L;
		{
			/**
			 * 21版本
			 */
			put(FILE_TYPE_01+FILE_VER_21,"00100121");
			put(FILE_TYPE_03+FILE_VER_21,"00302021");
			put(FILE_TYPE_43+FILE_VER_21,"04300021");
			put(FILE_TYPE_R1+FILE_VER_21,"09100021");
			
			put(FILE_TYPE_02+FILE_VER_21,"00210121");
			put(FILE_TYPE_04+FILE_VER_21,"00412021");
			put(FILE_TYPE_05+FILE_VER_21,"00500021");
			put(FILE_TYPE_06+FILE_VER_21,"00614321");
			put(FILE_TYPE_94+FILE_VER_21,"09402221");
			put(FILE_TYPE_07+FILE_VER_21,"00000721");
			put(FILE_TYPE_44+FILE_VER_21,"04400021");
			put(FILE_TYPE_R2+FILE_VER_21,"09200021");
			put(FILE_TYPE_26+FILE_VER_21,"02600021");
			
			/**
			 * 20版本
			 */
			put(FILE_TYPE_01+FILE_VER_20,"00100120");
			put(FILE_TYPE_03+FILE_VER_20,"00302020");
			put(FILE_TYPE_43+FILE_VER_20,"04300020");
			put(FILE_TYPE_R1+FILE_VER_20,"09100020");
			
			put(FILE_TYPE_02+FILE_VER_20,"00210120");
			put(FILE_TYPE_04+FILE_VER_20,"00412020");
			put(FILE_TYPE_05+FILE_VER_20,"00500020");
			put(FILE_TYPE_06+FILE_VER_20,"00614320");
			put(FILE_TYPE_94+FILE_VER_20,"09402220");
			put(FILE_TYPE_07+FILE_VER_20,"00000720");
			put(FILE_TYPE_44+FILE_VER_20,"04400020");
			put(FILE_TYPE_R2+FILE_VER_20,"09200020");
			
			/**
			 * 建设银行版本
			 */
			
			put(FILE_TYPE_01+FILE_VER_99,"00100199");
			put(FILE_TYPE_03+FILE_VER_99,"00302099");
			put(FILE_TYPE_02+FILE_VER_99,"00210199");
			put(FILE_TYPE_04+FILE_VER_99,"00412099");
			put(FILE_TYPE_05+FILE_VER_99,"00500099");
			put(FILE_TYPE_06+FILE_VER_99,"00614399");
			put(FILE_TYPE_07+FILE_VER_99,"00000799");
		}
	};
	
	/**
	 * 文件版本
	 */
	public static final String FILE_VER_20="20";
	public static final String FILE_VER_21="21";
	public static final String FILE_VER_99="99";
	
	public static final String FILE_ENTER="\r\n";
	
	public static final String FILE_OFDCFDAT="OFDCFDAT";
	
	public static final String FILE_OFDCFEND="OFDCFEND";
	
	public static final String FILE_OFDCFIDX="OFDCFIDX";
	
	public static final String FILE_999="999";
	
	public static final String FILE_TXT=".TXT";
	
	public static final String FILE_OFD_="OFD_";
	
	
	public static final String FIlE_="_";
	
	public static final String FILE_OFG="OFG";
	public static final String FILE_OFJ="OFJ";
	public static final String FILE_OFY="OFY";
	public static final String FILE_OFI="OFI";
	public static final String FILE_OFD="OFD";
	public static final String FILE_OFG_OK="OFGOK";
	public static final String FILE_OFJ_OK="OFJOK";
	public static final String FILE_OFY_OK="OFYOK";
	public static final String FILE_OFI_OK="OFIOK";
	public static final String FILE_OFD_OK="OFDOK";
	
	public static final String FILE_OK=".OK";
	public static final String FILE_BAK=".bak";
	public static final String FILE_DELETE="Delete";
	
	public static final String FILE_02_TXT="02.TXT";
	public static final String FILE_04_TXT="04.TXT";
	public static final String FILE_05_TXT="05.TXT";
	public static final String FILE_06_TXT="06.TXT";
	public static final String FILE_R2_TXT="R2.TXT";
	public static final String FILE_001="001";
	public static final String FILE_005="005";
	
	/**
	 * C	字符型
	   A	数字字符型，限于0—9 
	   N	数值型，其长度不包含小数点，可参与数值计算
	   TEXT	不定长文本
	 */
	public static final String FILED_TYPE_C="C";
	public static final String FILED_TYPE_A="A";
	public static final String FILED_TYPE_N="N";
	public static final String FILED_TYPE_TEXT="TEXT";
	

	/**
	 * FTP连接常量类
	 */
	public static final String FTP_IP="ftpip";
	public static final String FTP_PORT="ftpport";
	public static final String FTP_USER="ftpuser";
	public static final String FTP_PASSWD="ftppasswd";
	public static final String FTP_LOCALDIR="ftplocaldir";
	public static final String FTP_REMOTE="ftpremote";
	public static final String FUND_SEND_END_DATE="fundSendEndDate";
	public static final String FTP_LOG_DIR="ftpLogDir";
	public static final String FTP_FILE_DIR="ftpFileDir";
	public static final String FTP_LOG_PREFIX="ftpLogPrefix";
	public static final String FTP_LOG_POSTFIX="ftpLogPostfix";
	public static final String FTP_LOG=".log";
	public static final String FUND_FILETYPE="FILETYPE";
	
	public static final String CFM_MAPPING_FIELD_02 = "cfmMappingField02";
	
	public static final String CFM_MAPPING_FIELD_05 = "cfmMappingField05";
	
	public static final String CFM_MAPPING_FIELD_04 = "cfmMappingField04";
	
	public static final String CFM_MAPPING_FIELD_06 = "cfmMappingField06";
	
	public static final String CFM_MAPPING_FIELD_R2 = "cfmMappingFieldR2";
	
	public static final String CFM_MAPPING_FIELD_44 = "cfmMappingField44";
	
	public static final String CFM_MAPPING_FIELD_94 = "cfmMappingField94";
	
	public static final String CFM_MAPPING_FIELD_26 = "cfmMappingField26";
	
	public static final String SEX = "sex";
	
	public static final String DEALTYPE="dealType";
	
	public static final String BUSSINESSCODE = "bussinessCode";
	
	public static final String ZZIDENTITYTYPE = "zzIdentityType";
	
	public static final String GRIDENTITYTYPE = "grIdentityType";
	
	public static final String FILETYPE = "fileType";
	
	/**
	 * 业务类型
	 */
	//开户
	public static final String BUSINESS_001 = "001";
	//销户
	public static final String BUSINESS_002 = "002";
	//修改
	public static final String BUSINESS_003 = "003";
	//销户（目前只有浦发传009 == 002）
	public static final String BUSINESS_009 = "009";
	
	public static final String BUSINESS_020 = "020";
	public static final String BUSINESS_022 = "022";
	public static final String BUSINESS_024 = "024";
	public static final String BUSINESS_052 = "052"; //撤单
	
	public static final String BUSINESS_120 = "120";
	public static final String BUSINESS_122 = "122";
	public static final String BUSINESS_124 = "124";
	public static final String BUSINESS_152 = "152"; //撤单
	public static final String BUSINESSCODE_142 = "142";//强制赎回
	public static final String BUSINESSCODE_150 = "150";//清盘
	public static final String BUSINESSCODE_143 = "143";//分红发放
	public static final String BUSINESSCODE_130 = "130";
	
	public static final String TA_BUSINESS_01 = "01";	//TA认购
	public static final String TA_BUSINESS_02 = "02";	//TA申购
	public static final String TA_BUSINESS_03 = "03";	//TA赎回
	
	
	public static final String SH_BUSINESSCODE = "024";//赎回业务代码
	//赎回
	public static final String BUSINESS_REDEEM = "REDEEM";
	//申购
	public static final String BUSINESS_SUBSCRIBE = "SUBSCRIBE";
	
	/**
	 * 不用校验的字段
	 */
	public static final String NO_CHECK_FIELD = "ARTID,CRTID,CHANNELCODE,BUSINESSDATE";
	/**
	 * 需要转换类型的字段
	 */
	public static final String BIG_DECIMAL_FIELD = "ANNUALINCOME,FAMILYNUM,PENATES,STAFFNUM,TATRANSSTATUS,CONSHARERATIO,RESERVEDFIELD4,RESERVEDFIELD5";
	/**
	 * 调用接口的编码
	 */
	public static final String TransCode_100031 = "100031";//新开户
	public static final String TransCode_100032 = "100032";//新合同
	public static final String TransCode_100036 = "100036";//用户信息资料修改
	public static final String TransCode_100003 = "100003";//赎回
	public static final String TransCode_100005 = "100005";//是否是新合同
	public static final String TransCode_100006 = "100006";//客户持仓信息查询
	public static final String TransCode_100029 = "100029";//追加申购
	public static final String TransCode_100138 = "100138";//系统登录校验
	public static final String TransCode_100139 = "100139";//系统登录用户密码修改
	public static final String TransCode_100037 = "100037";//无检验赎回
	public static final String TransCode_100038 = "100038";//无检验追加申购
	public static final String TransCode_100039 = "100039";//客户购买标识查询
	public static final String TransCode_100040 = "100040";//开通交易渠道
	public static final String TransCode_100041 = "100041";//无检验新合同
	public static final String TransCode_100119 = "100119";//接收短信内容并发送
	public static final String TransCode_100137 = "100137";//客户资料信息查询
	public static final String TransCode_100454 = "100454";//是否含费标识发送
	public static final String TransCode_100442 = "100442";//是否含费标识发送
	public static final String TransCode_100434 = "100434";//信睿赎回接口
	public static final String TransCode_100510 = "100510";//修改合同字段接口
	public static final String TransCode_100512 = "100512";//修改TA回款账户信息
	public static final String Version = "1.0";//版本号
	public static final String ENCRYPT_PASSWORD = "PWMHK5VX5V1GW166E7LM7W62LM5KI5PJUQN5MUKK";//交易密码（加密）
	public static final String TRUSTCONTRACTID = "00000000000000000000"; //外部合同号 
	
	public static final String OPEN_CHANNEL_RET_CODE = "2000"; // 重复开通网上交易渠道返回的错误编码
	
	/**
	 *行情处理处理字段信息时不需要进行处理的字段
	 * */
	public static String notUsedCols = "SZTRECVPATH,CSDCVER,CHANNELCODE,TRADEDATE,BATCHNUMBER,PRODUCTTYPE,TAPRODUCTCODE,REMAINSHARES,MAXAMOUNTRAISED,PRODUCTCODE,PROSETUPDATE";
	/**
	 *行情处理处理字段信息时按要求进行补零的但实际上是补空格的字段
	 * */
	public static String  specialProcessing = "UPDATEDATE,DATEOFPERIODICSUBS,REGISTRATIONDATE,XRDATE,DEFDIVIDENDMETHOD,FUNDSPONSOR,TRADINGPRICE,DIVIDENTDATE,NEXTTRADEDATE";
	
	/**
	 *行情处理处理时临时表数据往行情信息里面赋值时候需要特殊处理的字段(临时表里没有，只能从产品表里面取)
	 * */
	public static String specialDealStrings = "INSTAPPSUBSAMNT,MINAMOUNTBYINST,INDIAPPSUBSAMOUNT,MINSUBSAMOUNTBYINDI,UNITSUBSAMOUNTBYINDI,UNITSUBSAMOUNTBYINST,MINBIDSAMOUNTBYINDI,MINBIDSAMOUNTBYINST,MINAPPBIDSAMOUNTBYIND,MINAPPBIDSAMOUNTBYINST";
	
	public static final String PROFITCLASS_0 = "0";//收益级别
	
	public static final String CURRENCY_CNY = "CNY";//人民币
	public static final String CURRENCY_CNY_CODE = "156";//人民币
	public static final String CURRENCY_HKD = "HKD";//港元
	public static final String CURRENCY_USD_CODE = "840";//美元
	public static final String CURRENCY_USD = "USD";//美元
	
	public static final String REDEEMTYPE_0 = "0";//赎回类型  0：普通赎回
	
	public static final String HBJJWFSYJE_0 = "0";//货币基金未付收益金额正负 0-正  1-负 
	
	public static final String DETAILFLAG_0 = "0";//明细标志	0-非明细，1-明细
	
	public static final String SHARECLASS_0 = "0";//收费方式	0-前收费，1-后收费
	
	
	public static final String BUSINFLAG_01 = "01";//业务代码 认购
	
	public static final String DIVIDENDPERUNIT = "0";//单位基金分红金额
	
	
	public static final String CHARGE = "0";//手续费
	
	public static final String AGENCYFEE = "0";//代理费
	
	public static final String TRANSFERFEE = "0";//过户费
	
	public static final String DRAWBONUSUNIT = "10000";//分红单位
	
	//分红类型	0-普通分红	1-质押基金分红	2-货币基金收益结转	3-保本基金赔付	4-专户到期处理
	public static final String DIVIDENDTYPE_0 = "0";
	public static final String DIVIDENDTYPE_1 = "1";
	public static final String DIVIDENDTYPE_2 = "2";
	public static final String DIVIDENDTYPE_3 = "3";
	public static final String DIVIDENDTYPE_4 = "4";
	
	public static final String ACHIEVEMENTPAY = "0";//业绩报酬
	
	public static final String ACHIEVEMENTCOMPEN = "0";//业绩补偿
	
	public static final String NAV = "1.0000";//基金单位净值
	
	public static final String BUSINFLAG = "74";
	
	public static final String DEFDIVIDENDMETHOD = "0";//默认分红方式 红利转投
	
	public static final String DefDividendMethod = "1";//现金分红
	
	
	
	
	
	//导出变量
	public static final String fileName = ".xlsx";
	public static final String titleColumn[] = {"icColCode","icColName","iiInterfaceName","icColDesc","icColType","icColLength","icColDecimal","icColRequ","icColRule","icColDict","icRequOnOff","icRuleOnOff","icColValue","icColOrder"};
	public static final String titleName[] = {"字段编码","字段名称","接口名称","字段描述","字段类型","长度","小数位","是否必填","格式化","数据字典","必填开关","规则开关","默认值","顺序"};
	public static final int titleSize[] = {12,25,20,25,12,12,12,12,12,12,12,12,12,12};
	
	/**
	 * 新增
	 */
	public static final String ACTION_ADD = "新增";
	
	/**
	 * 修改
	 */
	public static final String ACTION_EDIT = "修改";
	
	
	/**
	 * 删除
	 */
	public static final String ACTION_DEL = "删除";
	
	
	/**
	 * 复核
	 */
	public static final String ACTION_CHECK = "复核";
	
	/**
	 * 默认时间
	 */
	public static final String defaultTime="99991231";
	
	
	public static final String CONFIRMEDVOL="0.00";
	
	public static final String CONFIRMEDAMOUNT="0.00";
	
	//浦发银行渠道编号
	public static final String TTTNETB3="TTTNETB3";
	
	//基金净值日期
	public static final String UpdateDate="UPDATEDATE";
	
	//万份收益
	public static final String FundIncome="FUNDINCOME";
	
	//七日年化
	public static final String Yield="YIELD";
	
	//个人当日累计购买最大金额
	public static final String IndiDayMaxSumBuy="INDIDAYMAXSUMBUY";
	
	//机构当日累计购买最大金额
	public static final String InstDayMaxSumBuy="INSTDAYMAXSUMBUY";
	
	//基金净值
   public static final String StringNAV="NAV";
   
    //上海农商渠道编号
   public static final String CHANNELCODESHNS="TTTNETSHNS";

	
	
	
	
	public static Map<String,String> mapColToMap = new HashMap<String,String>(){
		private static final long serialVersionUID = 1L;
		{
			put("ACCTNOOFFMINCLEARINGAGENCY","ACCTNOFMINCLEARAGENCY");
			put("ACCTNOOFINVESTORINCLEARINGAGENCY","ACCTNOINVECLEARAGEN");
			put("ACCTNAMEOFFMINCLEARINGAGENCY","ACCTNAMEFICLEARAGENCY");
			put("ACCTNAMEOFINVESTORINCLEARINGAGENCY","ACCTNAMEINVECLEARAGEN");
			put("TARGETTRANSACTIONACCOUNTID","TARGETTRANSACTACCTCODE");
			put("DISCOUNTRATEOFCOMMISSION","DISCOUNTRATEOFCOMM");
			put("VARIETYCODEOFPERIODICSUBS","VARIETYCODEPERIODICSUB");		
			put("SERIALNOOFPERIODICSUBS","SERIALNOPERIODICSUB");	
			put("BEGINDATEOFPERIODICSUBS","BEGINDATEPERIODICSUBS");		
		}
	};
	public static LinkedHashMap<String,String> titleStatisticsMap = new LinkedHashMap<String, String>(){
		private static final long serialVersionUID = 1L;
		{
			put("CHANNELCODE","渠道编码");
			put("CHANNELNAME","渠道名称");
			put("FUNDCODE","产品代码");
			put("RG","认购总额");
			put("CONFIRMRG","认购确认");
			put("SG","申购总额");
			put("CONFIRMSG","申购确认");
			put("SH","赎回总额");
			put("CONFIRMSH","赎回确认");		
			put("CONFIRMCL","成立总额");	
			put("CONFIRMDQ","到期总额");
			put("CONFIRMQS","强赎总额");
		}
	};
	
	/**
	 * 重新生成序列号业务编码
	 */
	public static final String RE_SERIALNO_BUSICODE = "130,142,150";
	
	public static final String NO_CHECK_YIELD_FUND = "6005,C179";

	/**
	 * t+n产品分配停止步骤
	 */
	public static final String TN_BATCH_STOP_STEP = "13";
	/**
	 * 分配T+N停止的错误编码
	 */
	public static final String TN_ERROR_CODE = "T00051";
	
	/**
	 * 交易返回信息
	 */
	public static final String BUY_FIRST_SUCCESS = "首次购买成功！";
	public static final String BUY_APPEND_SUCCESS = "追加购买成功！";
	public static final String REDEEM_SUCCESS = "赎回成功！";
	public static final String CANCEL_SUCCESS = "撤单成功！";

	/**
	 * @Description 复核状态字段
	 **/
	public static final String NORMAL_CHECK_STATUS_00 = "00";
	public static final String NORMAL_CHECK_STATUS_01 = "01";
	
	/**
	 * 发送TA状态详情
	 */
	public static final String SEND_TO_TA_454 = "454";
	public static final String SEND_TO_TA_442 = "442";
	public static final String SEND_TO_TA_434 = "434";
	public static final String SEND_TO_TA_041 = "041";
	public static final String SEND_TO_TA_038 = "038";
	
	/**
	 * 产品类型
	 */
	public static final String MONEY = "money";
	
	public static final String NO_MONEY = "noMoney";
	
	
	//产品交易日表类型 -产品开放日
	public static final String OP_TYPE_000 = "000";
	//产品交易日表类型 -申购开放日
	public static final String OP_TYPE_022 = "022";
	//产品交易日表类型 -赎回开放日
	public static final String OP_TYPE_024 = "024";
	//产品交易日表类型 -认购确认日
	public static final String OP_TYPE_130 = "130";
	//产品交易日表类型 -赎回确认日
	public static final String OP_TYPE_124 = "124";
	
	/**
	 * 赎回申请的费用类型和业绩报酬
	 */
	public static final String FARE_TYPE_0 = "0";
	
	public static final String DEDUCT_FARE_TYPE_0 = "0";
	
	public static final String FARE_TYPE_1 = "1";
	
	public static final String DEDUCT_FARE_TYPE_1 = "1";
	
	/**
	 * S100041接口Flag值
	 */
	public static final String S100041Flag_0 = "0";
	public static final String S100041Flag_1= "1";
	public static final String S100041Flag_2 = "2";
	
	/**
	 * 批量更新返回标志 -1
	 */

	public static final int updateSuccess = -1;
	
	/**
	 * 账户、交易数据修改标志 01 交易 02 账户
	 */

	public static final String TransDataFlag = "01";
	public static final String AccountDataFlag = "02";

	/**
	 * 不校验电话号码的渠道
	 */
	public static final String NO_CHECK_PHONE_CHANNEL = "noCheckPhoneChannel";

	/**
	 * 读取日志判定文件上传成功
	 */
	public static final String READ_LOG_SUCCESS_MSG = "读取日志判定文件上传成功";
	
	/**
	 * 需要转换为20 的渠道编号
	 */
	public static final String EXCHANGE_CSDC_CHANNEL = "exchangeCsdcChannel";
	
	/**
	 * 发送tA需要上送特殊字段的渠道
	 */
	public static final String SPECIAL_FIELD_TO_TA_CHANNEL = "specialFieldToTAChannel";

}
