select count(*) from (
       select t2.rc_available_vol - t.rc_confirmed_vol countflag,T.rc_FUND_code
              from sams.d_fund_account_recon_cfm  t2,
              ( select nvl(p4.rc_confirmed_vol, 0) +
                           nvl(p1.erc_confirmed_vol, 0) -
                           nvl(p2.erc_confirmed_vol, 0) rc_confirmed_vol,
                           p4.rc_channel_code,
                           p4.rc_transaction_account_id,
                                p4.rc_fund_code  from 
                   (select a.rc_available_vol  rc_confirmed_vol,
               a.rc_channel_code   rc_channel_code,
               a.rc_fund_code rc_fund_code,
               a.rc_transaction_account_id rc_transaction_account_id
                              from sams.d_fund_account_recon_cfm a, sams.p_product_info p
                             where a.rc_channel_code = p.pi_channel_code
                               and a.rc_fund_code = p.pi_channel_product_code
                               and p.pi_product_type in ('8')
                               and p.pi_valid_flag = '01'
                               and a.rc_channel_code = 'TTTNETB3'--CHANNELCODE
                               and a.rc_trans_date = '20200327'--LASTDATE
                 ) p4--上一个工作日的对账数据
                left join (select sum(c.erc_confirmed_vol) erc_confirmed_vol,
                       c.erc_channel_code,
                       c.erc_transaction_account_id,
                       c.erc_fund_code
                  from sams.d_exchange_req_cfm c
                 where c.erc_business_code = '130'
                   and c.erc_channel_code = 'TTTNETB3'--CHANNELCODE
                   and c.erc_return_code = '0000'
                   and to_date(c.erc_transaction_cfm_date,'yyyyMMdd') = to_date('20200330', 'yyyyMMdd')--TRANSDATE
                 group by c.erc_channel_code,
                c.erc_transaction_account_id,
                c.erc_fund_code) p1--当日130数据
              ON P1.erc_channel_code = p4.rc_channel_code
             AND p1.erc_transaction_account_id =
                 p4.rc_transaction_account_id
             AND p1.erc_fund_code = p4.rc_fund_code
       left join (select nvl(sum(c.erc_confirmed_vol), 0) erc_confirmed_vol,
                 c.erc_channel_code,
                 c.erc_transaction_account_id,
                 c.erc_fund_code
                 from sams.d_exchange_req_cfm c
                 where c.erc_business_code = '024'
                   and c.erc_channel_code = 'TTTNETB3'--CHANNELCODE
                   and c.erc_return_code = '0000'
                   and to_date(c.erc_transaction_cfm_date,'yyyyMMdd') = to_date('20200330', 'yyyyMMdd')--TRANSDATE
                 group by c.erc_channel_code,
                  c.erc_transaction_account_id,
                  c.erc_fund_code) p2--当日124数据
                  on p4.rc_channel_code = p2.erc_channel_code
                 and p4.rc_transaction_account_id =
                  p2.erc_transaction_account_id
                 and p4.rc_fund_code = p2.erc_fund_code 
              ) t 
             where T2.rc_channel_code = T.rc_channel_code
                           and T2.rc_transaction_account_id = T.rc_transaction_account_id
                           and T2.rc_fund_code = T.rc_fund_code
                           and T2.rc_channel_code = 'TTTNETB3'--CHANNELCODE
                           and T2.rc_trans_date = '20200330'--TRANSDATE
        ) pp where  countflag != 0
