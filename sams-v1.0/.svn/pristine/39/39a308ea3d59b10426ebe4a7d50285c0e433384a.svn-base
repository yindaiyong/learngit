select 'TTTNETRFYH' CHANNELCODE,
       '20200521' TRANSDATE, --TRANSDATE
       T5.F_REMAINSHARES AVAILABLEVOL,
       T5.F_REMAINSHARES TOTALVOLOFDISTRIBUTORINTA,
       '20200521' TRANSACTIONCFMDATE, --TRANSDATE
       T6.DCI_FUND_CODE FUNDCODE,
       T6.DCI_TRANS_ACTION_ACCOUNT TRANSACTIONACCOUNTID,
       T6.DCI_DISTRIBUTOR_CODE DISTRIBUTORCODE,
       T6.DCI_TA_ACCOUNT_ID TAACCOUNTID,
       '' TOTALFROZENVOL,
       T6.DCI_BRANCH_CODE BRANCHCODE,
       to_char(sysdate, 'yyyyMMddHHmmss'), --|| sams.seq_taserialno_new.nextval  TASERIALNO,
       '' TOTALBACKENDLOAD,
       '0' SHARECLASS,
       '0' DETAILFLAG,
       '' ACCOUNTSTATUS,
       '' SHAREREGISTERDATE,
       '0.00' UNDISTRIBUTEMONETARYINCOME,
       '0' UNDISTRIBUTEMONETARYINCOMEFLAG,
       '' GUARANTEEDAMOUNT,
       '' SOURCETYPE,
       '' DEFDIVIDENDMETHOD
  from (select sum(T3.F_REMAINSHARES) F_REMAINSHARES,
               T4.DCI_TA_ACCOUNT_ID TAACCOUNTID,
               T4.DCI_TRANS_ACTION_ACCOUNT TRANSACTIONACCOUNTID,
               T4.DCI_FUND_CODE FUNDCODE
          from (select *
                  from (SELECT T.L_CONTRACTSERIALNO,
                               T.C_FUNDCODE,
                               T.D_SHAREBEGIN,
                               -- t.f_remainshares -->
                               --œyÔ‡ŽìÔö¼Ó×Ö¶Î  f_remainshares_sams 11.25à]¼þ-->
                               t.f_remainshares_sams F_REMAINSHARES
                          FROM datacenter.TJ_CONTRACTDETAILS@sams_dc_dblink       T,
                               datacenter.TA_TTRUSTCONTRACTDETAILS@sams_dc_dblink T2
                         WHERE T.L_CONTRACTSERIALNO = T2.L_SERIALNO
                           AND T2.C_TRUSTAGENCYNO = 'TTTNETRFYH') TT
                 where exists
                 (select 1
                          from (
                                --create by yindy º¯ÊýÌæ»»  -->
                                select distinct c.CP_TA_PRODUCT_CODE fundcode
                                  from sams.p_product_info    p,
                                       sams.p_channel_product c
                                 where p.pi_channel_product_code =
                                       c.cp_channel_product_code
                                   and p.pi_channel_code = c.cp_channel_code
                                   and c.cp_channel_code = 'TTTNETRFYH'
                                   and c.cp_valid_flag = '01'
                                   and c.cp_check_state = '01'
                                      -- and p.pi_valid_flag not in('00','99','02') -->
                                   and p.pi_valid_flag not in ('00', '99')
                                   and p.pi_check_state = '01'
                                   and p.pi_product_type in
                                       ('4', '5', '6', '7', '8', '9')
                                   and to_date(p.PI_PRO_SETUP_DATE, 'yyyyMMdd') <
                                       to_date('20200521', 'yyyyMMdd') --TRANSDATE
                                   and not exists
                                 (select 1
                                          from (select distinct t.erc_fund_code fundcode
                                                  from sams.d_exchange_req_cfm t
                                                 where t.erc_business_code = '142'
                                                   and t.erc_channel_code =
                                                       'TTTNETRFYH') tempin
                                         where tempin.fundcode =
                                               p.pi_channel_product_code)
                                
                                -- Ìæ»»½áÊø -->
                                ) temp
                         where temp.fundcode = TT.C_FUNDCODE)
                   and tt.d_sharebegin < to_date('20200521', 'yyyy/mm/dd')) T3,
               sams.D_CONTRACT_INFO T4 --TRANSDATE
         where T4.DCI_VALID_FLAG = '01'
           and T3.L_CONTRACTSERIALNO = T4.DCI_IN_CONTRACT
           and T4.DCI_CHANNEL_CODE = 'TTTNETRFYH'
         group by T4.DCI_CHANNEL_CODE,
                  t4.DCI_TA_ACCOUNT_ID,
                  T4.DCI_TRANS_ACTION_ACCOUNT,
                  T4.DCI_FUND_CODE) T5,
       (select distinct i.DCI_TA_ACCOUNT_ID,
                        i.DCI_TRANS_ACTION_ACCOUNT,
                        i.DCI_FUND_CODE,
                        i.DCI_DISTRIBUTOR_CODE,
                        i.DCI_BRANCH_CODE
          from (select *
                  from sams.D_CONTRACT_INFO o
                 where exists
                 (select 1
                          from (
                                --create by yindy º¯ÊýÌæ»»  -->
                                select distinct c.CP_TA_PRODUCT_CODE fundcode,
                                                 p.pi_Channel_Product_Code
                                  from sams.p_product_info    p,
                                        sams.p_channel_product c
                                 where p.pi_channel_product_code =
                                       c.cp_channel_product_code
                                   and p.pi_channel_code = c.cp_channel_code
                                   and c.cp_channel_code = 'TTTNETRFYH'
                                   and c.cp_valid_flag = '01'
                                   and c.cp_check_state = '01'
                                      -- and p.pi_valid_flag not in('00','99','02') -->
                                   and p.pi_valid_flag not in ('00', '99')
                                   and p.pi_check_state = '01'
                                   and p.pi_product_type in
                                       ('4', '5', '6', '7', '8', '9')
                                   and to_date(p.PI_PRO_SETUP_DATE, 'yyyyMMdd') <
                                       to_date('20200521', 'yyyyMMdd') --TRANSDATE
                                   and not exists
                                -(select 1
                                          from (select distinct t.erc_fund_code fundcode
                                                  from sams.d_exchange_req_cfm t
                                                 where t.erc_business_code in('142')
                                                   and t.erc_channel_code ='TTTNETRFYH') tempin
                                         where tempin.fundcode = p.pi_channel_product_code)
                                
                                -- Ìæ»»½áÊø -->
                                ) temp
                         where temp.fundcode = o.dci_product_code
                           and o.dci_fund_code = temp.pi_Channel_Product_Code)
                   AND O.DCI_VALID_FLAG = '01') i
         where i.DCI_CHANNEL_CODE = 'TTTNETRFYH'
         group by i.DCI_CHANNEL_CODE,
                  i.DCI_TA_ACCOUNT_ID,
                  i.DCI_TRANS_ACTION_ACCOUNT,
                  i.DCI_FUND_CODE,
                  i.dci_distributor_code,
                  i.dci_branch_code) T6
 where T5.TAACCOUNTID = T6.DCI_TA_ACCOUNT_ID
   and T5.TRANSACTIONACCOUNTID = T6.DCI_TRANS_ACTION_ACCOUNT
   and T5.FUNDCODE = T6.DCI_FUND_CODE
