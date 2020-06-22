--04文件丰利F类130取值逻辑
select TT.* from (
    select ER_CHANNEL_CODE CHANNELCODE,
       ER_TRANS_DATE  BUSINESSDATE,
       ER_APP_SHEET_SERIAL_NO APPSHEETSERIALNO,
       ER_FUND_CODE FUNDCODE,
       ER_LARGE_REDEMPTION_FLAG LARGEREDEMPTIONFLAG,
       ER_TRANSACTION_DATE TRANSACTIONDATE,
       ER_TRANSACTION_TIME TRANSACTIONTIME,
       ER_TRANSACTION_ACCOUNT_ID TRANSACTIONACCOUNTID,
       ER_DISTRIBUTOR_CODE DISTRIBUTORCODE,
       ER_APPLICATION_VOL APPLICATIONVOL,
       ER_APPLICATION_AMOUNT APPLICATIONAMOUNT,
       ER_TA_ACCOUNT_ID TAACCOUNTID,
       ER_DISCOUNT_RATE_OF_COMM DISCOUNTRATEOFCOMMISSION,
       ER_DEPOSIT_ACCT DEPOSITACCT,
       ER_REGION_CODE REGIONCODE,
       ER_CURRENCY_TYPE CURRENCYTYPE,
       ER_BRANCH_CODE BRANCHCODE,
       ER_ORIGINAL_APP_SHEET_NO ORIGINALAPPSHEETNO,
       ER_ORIGINAL_SUBS_DATE ORIGINALSUBSDATE,
       ER_INDIVIDUAL_OR_INSTITUTION INDIVIDUALORINSTITUTION,
       ER_VALID_PERIOD VALIDPERIOD,
       ER_DAYS_REDEMPTION_IN_ADVANCE DAYSREDEMPTIONINADVANCE,
       ER_REDEMPTION_DATE_IN_ADVANCE REDEMPTIONDATEINADVANCE,
       ER_ORIGINAL_SERIAL_NO ORIGINALSERIALNO,
       ER_DATE_OF_PERIODIC_SUBS DATEOFPERIODICSUBS,
       ER_TA_SERIAL_NO TASERIALNO,
       ER_TERM_OF_PERIODIC_SUBS TERMOFPERIODICSUBS,
       ER_FUTURE_BUY_DATE FUTUREBUYDATE,
       ER_TARGET_DISTRIBUTOR_CODE TARGETDISTRIBUTORCODE,
       ER_CHARGE CHARGE,
       ER_TARGET_BRANCH_CODE TARGETBRANCHCODE,
       ER_TARGET_TRANSACT_ACCT_CODE TARGETTRANSACTIONACCOUNTID,
       ER_TARGET_REGION_CODE TARGETREGIONCODE,
       ER_DIVIDEND_RATIO DIVIDENDRATIO,
       ER_SPECIFICATION SPECIFICATION,
       ER_CODE_OF_TARGET_FUND CODEOFTARGETFUND,
       ER_TOTAL_BACKEND_LOAD TOTALBACKENDLOAD,
       ER_SHARE_CLASS SHARECLASS,
       ER_ORIGINAL_CFM_DATE ORIGINALCFMDATE,
       ER_DETAIL_FLAG DETAILFLAG,
       ER_ORIGINAL_APP_DATE ORIGINALAPPDATE,
       ER_DEF_DIVIDEND_METHOD DEFDIVIDENDMETHOD,
       ER_FROZEN_CAUSE FROZENCAUSE,
       ER_FREEZING_DEADLINE FREEZINGDEADLINE,
       ER_VARIETY_CODE_PERIODIC_SUB VARIETYCODEOFPERIODICSUBS,
       ER_SERIAL_NO_PERIODIC_SUB SERIALNOOFPERIODICSUBS,
       ER_RATION_TYPE RATIONTYPE,
       ER_TARGET_TA_ACCOUNT_ID TARGETTAACCOUNTID,
       ER_TARGET_REGISTRAR_CODE TARGETREGISTRARCODE,
       ER_NET_NO NETNO,
       ER_CUSTOMER_NO CUSTOMERNO,
       ER_TARGET_SHARE_TYPE TARGETSHARETYPE,
       ER_RATION_PROTOCOL_NO RATIONPROTOCOLNO,
       ER_BEGIN_DATE_PERIODIC_SUBS BEGINDATEOFPERIODICSUBS,
       ER_END_DATE_OF_PERIODIC_SUBS ENDDATEOFPERIODICSUBS,
       ER_SEND_DAY_OF_PERIODIC_SUBS SENDDAYOFPERIODICSUBS,
       ER_BROKER BROKER,
       ER_SALES_PROMOTION SALESPROMOTION,
       ER_ACCEPT_METHOD ACCEPTMETHOD,
       ER_FORCE_REDEMPTION_TYPE FORCEREDEMPTIONTYPE,
       ER_TAKE_INCOME_FLAG TAKEINCOMEFLAG,
       ER_PURPOSE_OF_PE_SUBS PURPOSEOFPESUBS,
       ER_FREQUENCY_OF_PE_SUBS FREQUENCYOFPESUBS,
       ER_PERIOD_SUB_TIME_UNIT PERIODSUBTIMEUNIT,
       ER_BATCH_NUM_OF_PESUBS BATCHNUMOFPESUBS,
       ER_CAPITAL_MODE CAPITALMODE,
       ER_DETAIL_CAPTICAL_MODE DETAILCAPTICALMODE,
       ER_BACKENLOAD_DISCOUNT BACKENLOADDISCOUNT,
       ER_COMBINE_NUM COMBINENUM,
       ER_FUTURE_SUBSCRIBE_DATE FUTURESUBSCRIBEDATE,
       ER_TRADING_METHOD TRADINGMETHOD,
       ER_LARGE_BUY_FLAG LARGEBUYFLAG,
       ER_CHARGE_TYPE CHARGETYPE,
       ER_SPECIFY_RATE_FEE SPECIFYRATEFEE,
       ER_SPECIFY_FEE  SPECIFYFEE,
       ER_PRO_YIELD_TYPE PROYIELDTYPE,
       ER_PRO_YIELD     PROYIELD,
       ER_IN_CONTRACT  INCONTRACT,
       ER_OUT_CONTRACT  OUTCONTRACT,
       ER_VALID_FLAG  VALIDFLAG,
       ER_RETURN_CODE RETURNCODE,
       ER_EXPECTED_CFM_DATE EXPECTEDCFMDATE,
       ER_ERROR_DEC ERRORDEC,
       ER_TA_PRODUCT_CODE TAPRODUCTCODE,
       ER_BATCH_NO  BATCHNO,
       to_char(nvl(T.D_CDATE, sysdate), 'YYYYMMDD') as DCDATE,
       nvl(T.F_CONFIRMBALANCE, 0) CONFIRMEDAMOUNT,
       nvl(T.F_CONFIRMSHARES, 0) CONFIRMEDVOL,
       '1' FROMTAFLAG,
       T.C_REQUESTNO REQUESTNO,
       (select F_NETVALUE from (select k.F_NETVALUE,k.C_FUNDCODE from datacenter.CRM_TFUNDDAY@sams_dc_dblink k order by k.D_DATE DESC )  WHERE ROWNUM = 1 and  C_FUNDCODE = er_ta_product_code) AS NAV,
       '130' BUSINESSCODE,--BUSINESSCODE
       '20200519' DOWNLOADDATE,--TRANSDATE
       '20200519' TRANSDATE--TRANSDATE
       from sams.d_exchange_req , (
        select R.c_outrequestno,
             t.F_CONFIRMBALANCE,
             t.F_CONFIRMSHARES,
             t.C_REQUESTNO,
             t.D_CDATE,
             t.f_netvalue from  
        datacenter.TA_TREQUEST@sams_dc_dblink R, (select c.F_CONFIRMBALANCE,
             c.F_CONFIRMSHARES,
             c.C_REQUESTNO,
             c.D_CDATE,
             c.f_netvalue from datacenter.TA_TCONFIRM@sams_dc_dblink C
             where C.c_businflag in ('50', '02')
             --查询日期为上一个工作日到当前交易日  -->
             AND TO_CHAR(c.D_CDATE, 'YYYYMMDD') >'20200518'--LASTDATE
              AND TO_CHAR(c.D_CDATE, 'YYYYMMDD') <= '20200519'--TRANSDATE
              ) t WHERE R.C_REQUESTNO=t.C_REQUESTNO
         ) T
     where er_ta_serial_no=T.c_outrequestno
       and er_return_code = '0000'
       and ER_CHANNEL_CODE ='TTTNETRFYH'--CHANNELCODE
       and er_valid_flag='01'
        )TT 
       where exists (select 1
                      from (
                      --create by yindy 替换开始  -->
                      select e.er_ta_serial_no taserialno
                  from sams.d_exchange_req e,(select distinct c.cp_ta_product_code fundcode,P.PI_IPO_BEGIN_DATE,P.PI_IPO_END_DATE
                  ,p.pi_channel_product_code  
                  from sams.p_product_info p, sams.p_channel_product c
                   where p.pi_channel_product_code = c.cp_channel_product_code
                   and p.pi_channel_code=c.cp_channel_code
                   and c.cp_channel_code = 'TTTNETRFYH'--CHANNELCODE
                   and c.cp_valid_flag = '01'
                   and c.cp_check_state = '01'
                   and to_date(p.PI_PRO_SETUP_DATE,'yyyyMMdd')<=to_date('20200519','yyyyMMdd')--TRANSDATE
                   AND to_date(p.PI_PRO_END_DATE,'yyyyMMdd')>=to_date('20200519','yyyyMMdd')--TRANSDATE
                     and p.pi_valid_flag not in('00','99')
                     and p.pi_check_state = '01'
                     and p.pi_product_type='0' --PRODUCTTYPE
                     )T2
                   where e.er_ta_product_code=T2.fundcode and T2.pi_channel_product_code = e.er_fund_code 
                   and e.ER_RETURN_CODE = '0000'
                   and e.er_channel_code = 'TTTNETRFYH' --CHANNELCODE
                 -- 该状态为，取出过成立数据之后，把标志值为3 -->
                   and e.er_prement_data_stat != '3'
                 and e.er_business_code = '020' AND E.ER_TRANS_DATE BETWEEN T2.PI_IPO_BEGIN_DATE AND T2.PI_IPO_END_DATE
                      -- 替换结束 -->
                      ) temp
                     where temp.taserialno = TT.TASERIALNO);