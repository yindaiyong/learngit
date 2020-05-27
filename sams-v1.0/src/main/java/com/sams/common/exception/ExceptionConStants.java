package com.sams.common.exception;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;
import com.sams.common.utils.Reflections;


public class ExceptionConStants {
	
	public static final String validMsg_0000 = "校验成功";//处理成功
	public static final String retCode_0000 = "0000";//处理成功编码
	public static final String retMsg_0000 = "处理成功";//处理成功
	public static final String retCode_S00001 = "S00001";
	public static final String retMsg_S00001 = "行情处理页面传入的交易日期为空";
	public static final String retCode_S00002 = "S00002";
	public static final String retMsg_S00002 = "根据日期获取渠道产品信息没有记录";
	public static final String retCode_S00003 = "S00003";
	public static final String retMsg_S00003= "根据日期获取渠道产品信息为空";
	public static final String retCode_S00004 = "S00004";
	public static final String retMsg_S00004 = "查询数据异常";
	public static final String retCode_S00005 = "S00005";
	public static final String retMsg_S00005= "行情处理获取的产品类型为空";
	public static final String retCode_S00006 = "S00006";
	public static final String retMsg_S00006= "根据交易日期、产品类型获取非交易日信息为空";
	
	public static final String retCode_S00007 = "S00007";
	public static final String retMsg_S00007= "根据交易日期、产品类型判断是非交易日信息";
	
	public static final String retCode_S00008 = "S00008";
	public static final String retMsg_S00008= "渠道产品信息入库基金行情处理临时信息表异常";
	
	public static final String retCode_S00009 = "S00009";
	public static final String retMsg_S00009= "根据接口字段基本信息生成行情文件内容及数据入库失败";
	
	
	public static final String retCode_S00010 = "S00010";
	public static final String retMsg_S00010= "更新基金动态信息数据生成状态失败";
	
	public static final String retCode_S00011 = "S00011";
	public static final String retMsg_S00011= "插入临时表时数据异常";
	
	public static final String retCode_S00012 = "S00012";
	public static final String retMsg_S00012= "产品所对应的货币基金万份收益为空";

	public static final String retCode_S00013 = "S00013";
	public static final String retMsg_S00013= "产品所对应的货币基金万份收益为0";

	public static final String retCode_S00014 = "S00014";
	public static final String retMsg_S00014= "产品所对应的货币基金七日年化收益率为空";

	public static final String retCode_S00015 = "S00015";
	public static final String retMsg_S00015= "产品所对应的货币基金七日年化收益率为0";
	
	public static final String retCode_S00016 = "S00016";
	public static final String retMsg_S00016= "该渠道下无产品需要发送行情";
	
	public static final String retCode_0316 = "0316";
	
	public static final String retCode_S00017 = "S00017";
	public static final String retMsg_S00017= "该产品类型募集期不允许多次购买！";
	
	public static final String retCode_S00018 = "S00018";
	public static final String retMsg_S00018= "该产品未在ta表CRM_TFUNDDAY取到基金净值日期的值";
	
	public static final String retCode_S00019 = "S00019";
	public static final String retMsg_S00019= "该产品未在ta表CRM_TFUNDDAY取到基金净值的值";
	
	public static final String retCode_S00020 = "S00020";
	public static final String retMsg_S00020= "产品未在开放日表中取到非工作日数据，请查看产品非工作日配置";
	
	
	public static final String retCode_T00001 = "T00001";
	public static final String retMsg_T00001= "清理账户申请临时表失败";
	
	public static final String retCode_T00002 = "T00002";
	public static final String retMsg_T00002= "清理交易申请临时表失败";
	
	public static final String retCode_T00003 = "T00003";
	public static final String retMsg_T00003= "清理非居民涉税临时表失败";
	
	public static final String retCode_T00004 = "T00004";
	public static final String retMsg_T00004= "清理电子合同信息临时表失败";
	
	
	public static final String retCode_T00005 = "T00005";
	public static final String retMsg_T00005= "插入账户申请临时表失败";
	
	public static final String retCode_T00006 = "T00006";
	public static final String retMsg_T00006= "插入交易申请临时表失败";
	
	public static final String retCode_T00007 = "T00007";
	public static final String retMsg_T00007= "插入非居民涉税临时表失败";
	
	public static final String retCode_T00008 = "T00008";
	public static final String retMsg_T00008= "插入电子合同信息临时表失败";
	
	public static final String retCode_T00009 = "T00009";
	public static final String retMsg_T00009= "需要插入临时表的类型不匹配";
	
	public static final String retCode_T00010 = "T00010";
	public static final String retMsg_T00010= "投资人基金帐号为销户状态，不能进行交易";
	
	
	public static final String retCode_T00011 = "T00011";
	public static final String retMsg_T00011= "此基金当前不允许认/申购";
	
	public static final String retCode_T00012 = "T00012";
	public static final String retMsg_T00012= "此基金当前不允许赎回";
	
	
	public static final String retCode_T00013 = "T00013";
	public static final String retMsg_T00013= "查询交易申请信息无记录";
	
	public static final String retCode_A00013 = "A00013";
	public static final String retMsg_A00013= "查询用户申请信息无记录";
	
	public static final String retCode_T00014 = "T00014";
	public static final String retMsg_T00014= "该产品只能在运作期进行赎回";
	
	public static final String retCode_T00015= "T00015";
	public static final String retMsg_T00015= "可用份额不足";
	
	public static final String retCode_T00016= "T00016";
	public static final String retMsg_T00016= "不符合最低赎回份额";
	
	
	public static final String retCode_T00017= "T00017";
	public static final String retMsg_T00017= "不符合赎回级差";
	
	public static final String retCode_T00019= "T00019";
	public static final String retMsg_T00019= "根据渠道编码、基金编号查询产品信息表无记录";
	
	public static final String retCode_T00020= "T00020";
	public static final String retMsg_T00020= "根据渠道编码、基金编号查询产品收益率信息表无记录,无法收益类别与收益率";
	
	
	public static final String retCode_T00021= "T00021";
	public static final String retMsg_T00021= "账户未开户成功，交易不成功";
	
	
	public static final String retCode_T00022= "T00022";
	public static final String retMsg_T00022= "单笔首次申购低于申购下限";
	
	
	public static final String retCode_T00023= "T00023";
	public static final String retMsg_T00023= "不符合申购级差";
	
	
	public static final String retCode_T00024= "T00024";
	public static final String retMsg_T00024= "低于最低申购金额";
	
	
	public static final String retCode_T00025= "T00025";
	public static final String retMsg_T00025= "低于个人首次购买最低金额";
	
	
	public static final String retCode_T00026= "T00026";
	public static final String retMsg_T00026= "高于个人首次购买最高金额";
	
	public static final String retCode_T00027= "T00027";
	public static final String retMsg_T00027= "批量更新交易申请表的状态、错误描述失败";
	
	
	public static final String retCode_T00028= "T00028";
	public static final String retMsg_T00028= "个人认购金额在300W以下，且认购总个数大于50份时,TA系统可用合同不足";
	
	
	public static final String retCode_T00029= "T00029";
	public static final String retMsg_T00029= "基金未成立";
	
	
	public static final String retCode_T00030 = "T00030";
	public static final String retMsg_T00030= "插入非居民确认表失败";
	
	public static final String retCode_T00031 = "T00031";
	public static final String retMsg_T00031= "清理非居民确认表失败";
	
	public static final String retCode_T00032 = "T00032";
	public static final String retMsg_T00032= "账户申请关联电子合同验证失败";
	
	public static final String retCode_T00033 = "T00033";
	public static final String retMsg_T00033= "交易申请关联电子合同验证失败";
	
	public static final String retCode_T00034 = "T00034";
	public static final String retMsg_T00034= "账户申请及交易申请关联电子合同验证失败";
	
	
	public static final String retCode_T00035 = "T00035";
	public static final String retMsg_T00035= "插入合同确认表失败";
	
	public static final String retCode_T00036 = "T00036";
	public static final String retMsg_T00036= "清理合同确认表失败";
	
	
	public static final String retCode_T00037 = "T00037";
	public static final String retMsg_T00037= "获取渠道中登版本信息为空";
	
	public static final String retCode_T00038 = "T00038";
	public static final String retMsg_T00038= "查询非居民涉税临时表信息无记录";
	
	public static final String retCode_T00039 = "T00039";
	public static final String retMsg_T00039= "非居民涉税字段校验信息数据丢失";
	
	public static final String retCode_T00040 = "T00040";
	public static final String retMsg_T00040= "插入非居民涉税申请表信息失败";
	
	public static final String retCode_T00041 = "T00041";
	public static final String retMsg_T00041= "清理非居民涉税申请表失败";
	
	public static final String retCode_T00042 = "T00042";
	public static final String retMsg_T00042= "查询非居民涉税申请表信息无记录";
	
	public static final String retCode_T00043 = "T00043";
	public static final String retMsg_T00043= "合同信息字段校验信息数据丢失";
	
	public static final String retCode_T00044 = "T00044";
	public static final String retMsg_T00044= "查询合同信息申请表信息无记录";
	
	public static final String retCode_T00045 = "T00045";
	public static final String retMsg_T00045= "查询交易申请表信息无记录";
	
	
	public static final String retCode_T00046 = "T00046";
	public static final String retMsg_T00046= "查询交易申请信息无记录";
	
	public static final String retCode_T00047 = "T00047";
	public static final String retMsg_T00047= "基金账户不存在";
	
	public static final String retCode_T00048 = "T00048";
	public static final String retMsg_T00048= "产品代码错误";

	public static final String retCode_T00100 = "T00100";
	public static final String retMsg_T00100= "投资人基金交易帐号/基金账号不存在";
	
	public static final String retCode_T00122 = "T00122";
	public static final String retMsg_T00122= "交易失败,交易没有对应的电子合同/电子合同为失败";
	
		
	public static final String retCode_0380 = "0380";
	public static final String retMsg_0380 = "基金停止交易";
	
	public static final String retCode_0381 = "0381";
	public static final String retMsg_0381 = "基金停止申购";
	
	public static final String retCode_0382 = "0382";
	public static final String retMsg_0382 = "基金停止赎回";
	
	public static final String retCode_0376 = "0376";
	public static final String retMsg_0376 = "基金终止";
	
	public static final String retCode_0371 = "0371";
	public static final String retMsg_0371 = "基金发行";
	
	public static final String retCode_0370 = "0370";
	public static final String retMsg_0370 = "基金可申购赎回";
	
	public static final String retCode_0361 = "0361";
	public static final String retMsg_0361 = "基金不是发行状态";
	
	public static final String retCode_0362 = "0362";
	public static final String retMsg_0362 = "基金不是可申购赎回和停止赎回状态";
	
	public static final String retCode_0363 = "0363";
	public static final String retMsg_0363 = "基金不是可申购赎回和停止申购状态";
	
	public static final String retCode_T00049 = "T00049";
	public static final String retMsg_T00049= "获取基金性质为空异常终端";
	
	public static final String retCode_T00050 = "T00050";
	public static final String retMsg_T00050= "获取合同信息为空异常终端";
	
	
	public static final String retCode_T00051 = "T00051";
	public static final String retMsg_T00051 ="请分配T+N产品";
	
	
	public static final String retCode_T00052 = "T00052";
	public static final String retMsg_T00052 ="发送交易申请失败";
	
	public static final String retCode_T00053 = "T00053";
	public static final String retMsg_T00053 ="超最高账面份额";
	
	public static final String retCode_T00054 = "T00054";
	public static final String retMsg_T00054 ="低于个人追加购买最低金额";
	
	public static final String retCode_T00055 = "T00055";
	public static final String retMsg_T00055="高于个人追加购买最高金额";
	
	public static final String retCode_T00056 = "T00056";
	public static final String retMsg_T00056="低于机构首次购买最低金额";
	
	
	public static final String retCode_T00057 = "T00057";
	public static final String retMsg_T00057="高于机构追加购买最高金额";
	
	
	
	public static final String retCode_T00058 = "T00058";
	public static final String retMsg_T00058 ="低于机构追加购买最低金额";
	
	public static final String retCode_T00059 = "T00059";
	public static final String retMsg_T00059="高于机构追加购买最高金额";
	
	public static final String retCode_T00060 = "T00060";
	public static final String retMsg_T00060="不满足个人首次购买级差金额";
	
	public static final String retCode_T00061 = "T00061";
	public static final String retMsg_T00061="不满足个人追加购买级差金额";
	
	
	public static final String retCode_T00062 = "T00062";
	public static final String retMsg_T00062="不满足机构首次购买级差金额";
	
	public static final String retCode_T00063 = "T00063";
	public static final String retMsg_T00063="不满足机构追加购买级差金额";
	
	public static final String retCode_T00064 = "T00064";
	public static final String retMsg_T00064 ="高于最高账面份额";
	
	public static final String retCode_T00094 = "T00094";
	public static final String retMsg_T00094 ="低于最低账面份额";
	
	public static final String retCode_T00018 = "T00018";
	public static final String retMsg_T00018 ="持仓/可用份额为0";
	
	public static final String retCode_T00065 = "T00065";
	public static final String retMsg_T00065 ="低于个人最低赎回份额";
	
	public static final String retCode_T00066 = "T00066";
	public static final String retMsg_T00066 ="低于机构最低赎回份额";
	
	public static final String retCode_T00067 = "T00067";
	public static final String retMsg_T00067="不满足个人赎回级差";
	
	public static final String retCode_T00068 = "T00068";
	public static final String retMsg_T00068="不满足机构赎回级差金额";
	
	public static final String retCode_T00092 = "T00092";
	public static final String retMsg_T00092="赎回后不满足个人最低账面份额";
	
	public static final String retCode_T00093 = "T00093";
	public static final String retMsg_T00093="赎回后不满足机构最低账面份额";
	
	
	
	public static final String retCode_T00070 = "T00070";
	public static final String retMsg_T00070="此产品不允许认购确认";
	
	public static final String retCode_T00071 = "T00071";
	public static final String retMsg_T00071="此产品不允许申购确认";
	
	public static final String retCode_T00072 = "T00072";
	public static final String retMsg_T00072="此产品不允许赎回确认";
	
	public static final String retCode_T00095 = "T00095";
	public static final String retMsg_T00095="赎回确认的份额/金额为空或与申请份额不一致";
	
	public static final String retCode_T00096 = "T00096";
	public static final String retMsg_T00096="申购确认的金额/份额为空或与申购的金额不一致";
	
	public static final String retCode_T00073 = "T00073";
	public static final String retMsg_T00073="04文件交易确认时，【申购】申请记录数和确认数不一致";
	
	public static final String retCode_T00101 = "T00101";
	public static final String retMsg_T00101="04文件交易确认时，【赎回】申请记录数和确认数不一致";
	
	public static final String retCode_T00107 = "T00107";
	public static final String retMsg_T00107="04文件交易确认时，【多币种赎回】申请记录数和确认数不一致";
	
	public static final String retCode_T00074 = "T00074";
	public static final String retMsg_T00074="04文件交易确认时，TA系统获取【强制赎回】失败";
	
	public static final String retCode_T00075 = "T00075";
	public static final String retMsg_T00075="04文件交易确认时，TA系统获取【认购结果】数据失败";
	
	public static final String retCode_T00104 = "T00104";
	public static final String retMsg_T00104="04文件交易确认时，TA系统获取【权益/fof认购结果】数据失败";
	
	public static final String retCode_T00106 = "T00106";
	public static final String retMsg_T00106="04文件交易确认时，TA系统获取【多币种认购结果】数据失败";
	
	public static final String retCode_T00109 = "T00109";
	public static final String retMsg_T00109="确认文件中TASERIALNO有重复！";
	
	public static final String retCode_T00110 = "T00110";
	public static final String retMsg_T00110="多币种类产品没有设置相应的产品确认日期,请前往设置！";
	
	public static final String retCode_T00111 = "T00111";
	public static final String retMsg_T00111="该产品没有设置相应币种的产品信息";
	
	public static final String retCode_T00114 = "T00114";
	public static final String retMsg_T00114="该条撤销申请没有相对应的成功的原申请";
	
	
	public static final String retCode_T00076 = "T00076";
	public static final String retMsg_T00076="04文件交易确认时，TA系统获取【基金清盘】数据失败";
	
	
	public static final String retCode_T00077 = "T00077";
	public static final String retMsg_T00077="04文件交易确认时，TA系统获取固收到期数据不唯一";
	
	
	public static final String retCode_T00078 = "T00078";
	public static final String retMsg_T00078="06文件红宝石七天产品获取分红数据异常，请联系管理员";
	
	public static final String retCode_T00079 = "T00079";
	public static final String retMsg_T00079="06文件现金管理类产品获取分红数据异常，请联系管理员";
	
	
	public static final String retCode_T00080 = "T00080";
	public static final String retMsg_T00080="06文件固守类产品获取分红数据异常，请联系管理员";
	
	public static final String retCode_T00081 = "T00081";
	public static final String retMsg_T00081="06文件固收类产品获取分红数据，本地合同记录数与TA记录数不一致，请联系管理员";
	
	public static final String retCode_T00082 = "T00082";
	public static final String retMsg_T00082="06文件红宝石七天产品获取分红数据，本地合同记录数与TA记录数不一致，请联系管理员";
	
	public static final String retCode_T00083 = "T00083";
	public static final String retMsg_T00083="06文件现金管理类产品获取分红数据，本地合同记录数与TA记录数不一致，请联系管理员";
	
	
	public static final String retCode_T00084 = "T00084";
	public static final String retMsg_T00084="06文件固收类产品获取分红数据合同重复，请联系管理员";
	
	
	public static final String retCode_T00085 = "T00085";
	public static final String retMsg_T00085="06文件红宝石七天产品获取分红数据合同重复，请联系管理员";
	
	public static final String retCode_T00086= "T00086";
	public static final String retMsg_T00086="06文件现金管理类产品获取分红数据合同重复，请联系管理员";
	
	
	public static final String retCode_T00087= "T00087";
	public static final String retMsg_T00087="非货币类产品对账数据不一致，请联系管理员";
	
	public static final String retCode_T00088= "T00088";
	public static final String retMsg_T00088="【非货币类】有认购结果数据应有对账数据，请联系管理员";
	
	public static final String retCode_T00089= "T00089";
	public static final String retMsg_T00089="有货币类产品对账数据不一致，请联系管理员";
	
	public static final String retCode_T00102= "T00102";
	public static final String retMsg_T00102="获取【货币类】产品对账数据失败，请联系管理员";
	
	public static final String retCode_T00103= "T00103";
	public static final String retMsg_T00103="获取【权益/FOF类】产品对账数据不一致，请联系管理员";
	
	public static final String retCode_T00112= "T00112";
	public static final String retMsg_T00112="获取【多币种类】产品对账数据不一致，请联系管理员";
	
	public static final String retCode_T00118= "T00118";
	public static final String retMsg_T00118="获取【丰利F类】产品对账数据不一致，请联系管理员";
	
	public static final String retCode_T00123= "T00123";
	public static final String retMsg_T00123="获取浦发渠道【每日新增收益】有误,请联系管理员";
	
	public static final String retCode_T00105 = "T00105";
	public static final String retMsg_T00105 = "成立数据有申请金额与确认金额不一致记录,请检查";

	public static final String retCode_T00108 = "T00108";
	public static final String retMsg_T00108 = "份额明细数据入库异常";
	
	public static final String retCode_T00090= "T00090";
	public static final String retMsg_T00090="收益级别异常，请检查";
	
	
	public static final String retCode_T00099 = "T00099";
	public static final String retMsg_T00099= "无投资人基金帐号，不能进行交易";

	public static final String retCode_T00113 = "T00113";
	public static final String retMsg_T00113= "分红数据为空，未付收益金额不能为空";
	
	public static final String retCode_T00115 = "T00115";
	public static final String retMsg_T00115= "【丰利F类】有认购结果数据应有对账数据，请联系管理员";
	
	public static final String retCode_T00116 = "T00116";
	public static final String retMsg_T00116= "06文件【丰利F类】获取分红数据异常，请联系管理员";
	
	public static final String retCode_T00117 = "T00117";
	public static final String retMsg_T00117= "06文件【丰利F类】获取分红数据，本地合同记录数与TA记录数不一致，请联系管理员";
	
	public static final String retCode_T00119 = "T00119";
	public static final String retMsg_T00119= "26文件获取份额明细数据,【可用份额】数据有误,请联系管理员";
	
	public static final String retCode_T00120 = "T00120";
	public static final String retMsg_T00120= "26文件获取份额明细数据,【基金总份额】数据有误,请联系管理员";
	
	public static final String retCode_T00121 = "T00121";
	public static final String retMsg_T00121= "26文件获取份额明细数据为空,请联系管理员";
	
	
	public static final String retMsg_first="首次"; 
	
	
	
	public static final String retStatus_info="info"; //警告状态
	public static final String retStatus_error="error";//提示
	public static final String retCode_FT0001="FT0001";
	public static final String retMsg_FT0001="ftpClient拒绝连接,请检查IP地址与端口";
	public static final String retCode_FT0002="FT0002";
	public static final String retMsg_FT0002="ftpClient拒绝登陆,请检查用户名与密码";
	public static final String retCode_FT0003="FT0003";
	public static final String retMsg_FT0003="ftpClient状态不可用";
	public static final String retCode_FT0004="FT0004";
	public static final String retMsg_FT0004="FTP目录无法生成";
	public static final String retCode_FT0005="FT0005";
	public static final String retMsg_FT0005="上传目录正确，不需要生成新的目录";
	public static final String retCode_FT0006="FT0006";
	public static final String retMsg_FT0006="FTP拿到的文件不为空但没有数据";
	public static final String retCode_FT0007="FT0007";
	public static final String retMsg_FT0007="本地文件为空";
	public static final String retCode_FT0008="FT0008";
	public static final String retmsg_FT0008="不为空但没有数据";
	public static final String retCode_FT0010="FT0010";
	public static final String retMsg_FT0010="文件输入流创建失败";
	public static final String retCode_FT0011="FT0011";
	public static final String retMsg_FT0011="文件输出流创建失败";
	public static final String retCode_FT0012="FT0012";
	public static final String retMsg_FT0012="FTP相对应与本地相对应目录下的文件数据为空";
	public static final String retCode_FT0013="FT0013";
	public static final String retMsg_FT0013="下载失败,FTPUtils里面的retrieveAndCheckFile方法异常";
	public static final String retCode_FT0014="FT0014";
	public static final String retMsg_FT0014="上传失败";
	public static final String retCode_FT0015="FT0015";
	public static final String retMsg_FT0015="下载文件个数与预期文件个数不一致，不予下载";
	public static final String retCode_FT0016="FT0016";
	public static final String retMsg_FT0016="下载文件名称与预期文件名称不一致，不予下载";
	public static final String retCode_FT0017="FT0017";
	public static final String retMsg_FT0017="账户申请表该日期已有数据发送TA,禁止重复操作";
	public static final String retCode_FT0018="FT0018";
	public static final String retMsg_FT0018="交易申请表该日期已有数据发送TA,禁止重复操作";
	public static final String retCode_FT0019="FT0019";
	public static final String retMsg_FT0019="上传文件名称与预期不符，不予上传";
	public static final String retCode_FT0020="FT0020";
	public static final String retMsg_FT0020="拿FTP目录下的文件出错";
	public static final String retCode_FT0021="FT0021";
	public static final String retMsg_FT0021="FTP目录文件与预期上传文件名称不一致，请重新上传";
	public static final String retCode_FT0022="FT0022";
	public static final String retMsg_FT0022="未扫描到下载数据，请稍后重试";
	public static final String retCode_FT0023="FT0023";
	public static final String retMsg_FT0023="上传文件失败，请重新上传";
	public static final String retCode_FT0024="FT0024";
	public static final String retMsg_FT0024="切换读取深圳通日志目录失败，请检查深圳通日志参数以及权限";
	public static final String retCode_FT0025="FT0025";
	public static final String retMsg_FT0025="日志文件输入流为空，请检查日志前后缀参数配置以及日志文件名称";
	
	
	//接口字段校验
	public static final String retCode_FI0003 = "FI0003";
	public static final String retMsg_FI0003 = "该字段为必填字段";
	public static final String retCode_FI0004 = "FI0004";
	public static final String retMsg_FI0004 = "该字段长度有误";
	public static final String retCode_FI0005 = "FI0005";
	public static final String retMsg_FI0005 = "该字段格式不正确";
	public static final String retCode_FI0006 = "FI0006";
	public static final String retMsg_FI0006 = "该字段数据不再数据字典内容中";
	public static final String retCode_FI0007 = "FI0007";
	public static final String retMsg_FI0007 = "四个号码不可都为空";
	public static final String retCode_FI0008 = "FI0008";
	public static final String retMsg_FI0008 = "文件中的发送人编码/接收人编码不正确";
	public static final String retCode_FI0009 = "FI0009";
	public static final String retMsg_FI0009 = "文件名称不正确";
	public static final String retCode_FI0010 = "FI0010";
	public static final String retMsg_FI0010 = "正则表达式解析异常";
	
	public static final String retCode_9999 = "9999";
	public static final String retMsg_9999 = "校验不通过";
	//文件数据校验
	public static final String retCode_FD0001 = "FD0001";
	public static final String retMsg_FD0001 = "文件写入异常";
	
	//读取文件
	public static final String retCode_RF0001 = "RF0001";
	public static final String retMsg_RF0001 = "读取的文件类型与实际文件类型不一致";
	public static final String retCode_RF0002 = "RF0002";
	public static final String retMsg_RF0002 = "读取文件字段个数与规定的不一致";
	public static final String retCode_RF0010 = "RF0010";
	public static final String retMsg_RF0010 = "读取文件字段个数与累计的不一致";
	public static final String retCode_RF0003 = "RF0003";
	public static final String retMsg_RF0003 = "解析数据行出错";
	public static final String retCode_RF0004 = "RF0004";
	public static final String retMsg_RF0004 = "该文件数据行数不正确";
	public static final String retCode_RF0005 = "RF0005";
	public static final String retMsg_RF0005 = "索引文件指向的文件不存在";
	public static final String retCode_RF0006 = "RF0006";
	public static final String retMsg_RF0006 = "该文件夹没有对应的文件";
	public static final String retCode_RF0007 = "RF0007";
	public static final String retMsg_RF0007 = "文件读取字段与设置字段不一致";
	public static final String retCode_RF0008 = "RF0008";
	public static final String retMsg_RF0008 = "该文件没有数据";
	public static final String retCode_RF0009 = "RF0009";
	public static final String retMsg_RF0009 = "读取的数据行数量和实际数据行数量不一致";
	public static final String retCode_RF0011 = "RF0011";
	public static final String retMsg_RF0011 = "该文件没有结束标识";
	public static final String retCode_RF0012 = "RF0012";
	public static final String retMsg_RF0012 = "根据渠道编号没有找到对应的渠道信息";
	public static final String retCode_RF0013 = "RF0013";
	public static final String retMsg_RF0013 = "没有获取到本地的读取文件路径";
	public static final String retCode_RF0014 = "RF0014";
	public static final String retMsg_RF0014 = "索引文件不包含选择的文件路径";
	public static final String retCode_RF0015 = "RF0015";
	public static final String retMsg_RF0015 = "索引文件不存在";
	
	public static final String retCode_LA0001 = "LA0001";
	public static final String retMsg_LA0001 = "磁盘空间不足，请及时清理";
	//用户
	public static final String retCode_A00001 = "A00001";
	public static final String retMsg_A00001 = "该用户已开户，请直接登录";
	public static final String retCode_A00002 = "A00002";
	public static final String retMsg_A00002 = "该用户不存在，请先开户";
	public static final String retCode_A00003 = "A00003";
	public static final String retMsg_A00003 = "该用户已是销户状态";
	public static final String retCode_A00004 = "A00004";
	public static final String retMsg_A00004 = "用户还有份额，请全额赎回之后在进行注销！";
	public static final String retCode_A00005 = "A00005";
	public static final String retMsg_A00005 = "该用户销户成功。";
	public static final String retCode_A00006 = "A00006";
	public static final String retMsg_A00006 = "该用户开户成功。";
	public static final String retCode_A00007 = "A00007";
	public static final String retMsg_A00007 = "插入用户确认表失败";
	public static final String retCode_A00008 = "A00008";
	public static final String retMsg_A00008 = "修改账户信息表失败";
	public static final String retCode_A00009 = "A00009";
	public static final String retMsg_A00009 = "插入用户状态表失败";
	public static final String retCode_A00010 = "A00010";
	public static final String retMsg_A00010 = "确认数据为空";
	public static final String retCode_A00011 = "A00011";
	public static final String retMsg_A00011 = "修改确认表状态失败";
	public static final String retCode_A00012 = "A00012";
	public static final String retMsg_A00012 = "查询渠道信息为空";
	public static final String retCode_A00014 = "A00014";
	public static final String retMsg_A00014 = "查询的接口字段信息为空";
	public static final String retCode_A00015 = "A00015";
	public static final String retMsg_A00015 = "数据基础校验有错误";
	public static final String retCode_A00016 = "A00016";
	public static final String retMsg_A00016 = "申请数据有重复";
	public static final String retCode_A00017 = "A00017";
	public static final String retMsg_A00017 = "查询的接口字段为空";
	public static final String retCode_A00018 = "A00018";
	public static final String retMsg_A00018 = "在切日表中不存在该渠道切日信息";
	public static final String retCode_A00019 = "A00019";
	public static final String retMsg_A00019 = "申请中业务编码为空，或者不在字典中";
	public static final String retCode_A00022 = "A00022";
	public static final String retMsg_A00022 = "不支持该个人证件类型";
	public static final String retCode_A00023 = "A00023";
	public static final String retMsg_A00023 = "不支持该机构证件类型";
	public static final String retCode_A00024 = "A00024";
	public static final String retMsg_A00024 = "身份证号码长度错误,规定为18/15位！";
	public static final String retCode_A00025 = "A00025";
	public static final String retMsg_A00025 = "身份证号码格式不正确！";
	public static final String retCode_A00026 = "A00026";
	public static final String retMsg_A00026 = "94文件生成的记录数与申请的记录数不一致！";
	
	public static final String retCode_E00001 = "E00001";
	public static final String retMsg_E00001 = "小额合同数不够";
	public static final String retCode_E00002 = "E00002";
	public static final String retMsg_E00002 = "该产品没有行情数据，不能交易";
	
	
	public static final String retCode_A00020 = "A00020";
	public static final String retMsg_A00020 = "业务校验错误，不能交易";
	
	
	public static final String retCode_T00069 = "T00069";
	public static final String retMsg_T00069 ="检查到产品未配置信息,请配置或强制通过";
	
	public static final String retCode_T00091 = "T00091";
	public static final String retMsg_T00091 ="检查到申请单编号重复信息,请检查或强制通过";
	
	//中登接口
	public static final String INSERT = "新增";
	public static final String DELETE = "删除";
	public static final String UPDATE = "修改";
	public static final String CHECK = "复核";
	public static final String IMPORT = "导入";
	public static final String EXPORT = "导出";
	
	public static final String RETMSG_CSDC01 = "该接口信息已存在。";
	public static final String RETMSG_CSDC02 = "接口信息成功。";
	public static final String RETMSG_CSDC03 = "接口信息失败。";
	
	
	public static final String retCode_List0001 = "List0001";
	public static final String retMsg_List0001 = "在用渠道为空";
	
	public static final String retCode_List0002 = "List0002";
	public static final String retMsg_List0002 = "获取行情处理流程记录集为空";
	
	public static final String retCode_List0003 = "List0003";
	public static final String retMsg_List0003 = "判断是否超额通过产品取对应的TA的产品代码没数据";
	
	
	public static final String retCode_List0004 = "List0004";
	public static final String retMsg_List0004 = "产品超额,请联系管理员是否添加额度";
	
	public static final String retCode_true = "true";
	public static final String retMsg_true = "行情处理判断是否超额通过TA取产品的已买总额时超额";
	
	public static final String retCode_false = "false";
	public static final String retMsg_false = "行情处理判断是否超额通过TA取产品的已买总额时未超额";
	
	
	public static final String retCode_List0005 = "List0005";
	public static final String retMsg_List0005= "不允许个人购买";
	
	public static final String retCode_List0006 = "List0006";
	public static final String retMsg_List0006= "不允许机构购买";
	
	public static final String retCode_List0007 = "List0007";
	public static final String retMsg_List0007= "此产品不允许认购";
	
	public static final String retCode_List0008 = "List0008";
	public static final String retMsg_List0008= "此产品不允许申购";
	
	public static final String retCode_List0009 = "List0009";
	public static final String retMsg_List0009= "此产品只能到期赎回";
	
	public static final String retCode_A00021 = "A00021";
	public static final String retMsg_A00021 = "04文件处理异常";
	
	public static final String retCode_ipo = "ipoException";
	public static final String retMsg_ipo = "临时表非货币类取处于募集期的产品信息有误";
	
	public static final String retCode_pro = "proException";
	public static final String retMsg_pro = "临时表非货币类取处于募集期之后的产品信息有误";
	
	
	public static Map<String,Object> getRetObject(String retCode){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("retCode", Reflections.getFieldValue(ExceptionConStants.class, "retCode_"+retCode).toString());
		map.put("retMsg", Reflections.getFieldValue(ExceptionConStants.class, "retMsg_"+retCode).toString());
		if(retCode_0000.equals(retCode)){
			map.put("retStatus", retStatus_info);
		}else{
			map.put("retStatus", retStatus_error);
		}
		return map;
	}
	
	public static Map<String,Object> getRetObject(String retCode,Object obj){
		Map<String,Object> map=new HashMap<String, Object>();
		map.putAll(getRetObject(retCode));
		map.remove("RETVALUE");
		map.put("RETVALUE", obj);
		return map;
	}
	
	public static Map<String,Object> getRetObjects(String retCode,String retMsg){
		Map<String,Object> map=new HashMap<String, Object>();
		map.putAll(getRetObject(retCode));
		if(!StringUtils.isBlank(retMsg)){
			map.remove("retMsg");
			map.put("retMsg", retMsg);
		}
		return map;
	}
	
	
	public static void main(String[] args) {
		
		System.out.println(getRetObject("0000",Lists.newArrayList()));
/*		Class c = ExceptionConStants.class;
        *//**
         * 成员变量也是对象
         * java.lang.reflect.Field
         * getFields()方法获取的是所有的public 的成员变量的信息
         * getDeclaredFields 获取的是该类自己声明的成员变量的信息
         *//*
        // Field[] fs = c.getFields();
        Field fs;
		try {
			fs = c.getDeclaredField("retCode_0000");
			Object obj= fs.get(c);
		       System.out.println(obj);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
       
	}
	
	
	
	
 }
