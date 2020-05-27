--昨日的对仗数据（昨天没有对仗数据就取今日的130）-142-150=今日ta持仓

select count(*) from (
    select T2.RC_AVAILABLE_VOL-T1.erc_confirmed_vol countflag
      from  D_FUND_ACCOUNT_RECON_CFM T2,(select nvl(p4.erc_confirmed_vol, p1.erc_confirmed_vol) - nvl(p2.erc_confirmed_vol, 0)-nvl(p3.erc_confirmed_vol, 0) erc_confirmed_vol,
            p4.erc_channel_code,
            p4.erc_transaction_account_id,
            p4.erc_fund_code
       from (
            select a.rc_available_vol  erc_confirmed_vol,
            a.rc_channel_code erc_channel_code,
            a.rc_fund_code erc_fund_code,
          a.rc_transaction_account_id erc_transaction_account_id
           from d_fund_account_recon_cfm a ,p_product_info p 
       where a.rc_channel_code = p.pi_channel_code 
       and a.rc_fund_code = p.pi_channel_product_code 
       and p.pi_product_type in ('4','5','6')
       and p.pi_valid_flag not in ('00','99')
            and a.rc_channel_code = 'TTTNETJSYH'
            and a.rc_trans_date = '20200327' 
            union 
            select sum(c.erc_confirmed_vol) erc_confirmed_vol,
                    c.erc_channel_code,
                    c.erc_fund_code,
          c.erc_transaction_account_id
               from sams.d_exchange_req_cfm c
              where c.erc_business_code = '130'
              and c.erc_channel_code='TTTNETJSYH'
              and c.erc_trans_date = '20200330'
              and (select p.pi_product_type from sams.p_product_info p where p.pi_channel_code = c.erc_channel_code and p.pi_channel_product_code = c.erc_fund_code and p.pi_valid_flag not in ('00','99')) in ('4','5','6')
              group by c.erc_channel_code,
                       c.erc_transaction_account_id,
                       c.erc_fund_code             
            )p4
        left join (select sum(c.erc_confirmed_vol) erc_confirmed_vol,
                    c.erc_channel_code,
                    c.erc_transaction_account_id,
                    c.erc_fund_code
               from d_exchange_req_cfm c
              where c.erc_business_code = '130'
              and c.erc_channel_code='TTTNETJSYH'
              group by c.erc_channel_code,
                       c.erc_transaction_account_id,
                       c.erc_fund_code) p1
            on p1.erc_channel_code = p4.erc_channel_code
            and p1.erc_fund_code = p4.erc_fund_code
            and p1.erc_transaction_account_id = p4.erc_transaction_account_id
       
       left join (select nvl(sum(c.erc_confirmed_vol), 0) erc_confirmed_vol,
                         c.erc_channel_code,
                         c.erc_transaction_account_id,
                         c.erc_fund_code
                    from d_exchange_req_cfm c
                   where c.erc_business_code = '142'
                    and c.erc_channel_code='TTTNETJSYH'
                    and c.erc_transaction_cfm_date='20200330'
                   group by c.erc_channel_code,
                            c.erc_transaction_account_id,
                            c.erc_fund_code) p2
         on p4.erc_channel_code = p2.erc_channel_code
        and p4.erc_transaction_account_id = p2.erc_transaction_account_id
        and p4.erc_fund_code = p2.erc_fund_code
        
        left join (select nvl(sum(c.erc_confirmed_vol), 0) erc_confirmed_vol,
                         c.erc_channel_code,
                         c.erc_transaction_account_id,
                         c.erc_fund_code
                    from d_exchange_req_cfm c
                   where c.erc_business_code = '150'
                   and c.erc_channel_code='TTTNETJSYH'
                   and c.erc_transaction_cfm_date='20200330'
                   group by c.erc_channel_code,
                            c.erc_transaction_account_id,
                            c.erc_fund_code) p3
         on p4.erc_channel_code = p3.erc_channel_code
        and p4.erc_transaction_account_id = p3.erc_transaction_account_id
        and p4.erc_fund_code = p3.erc_fund_code
    ) T1
     where T2.rc_channel_code = T1.erc_channel_code
     and T2.RC_TRANSACTION_ACCOUNT_ID = T1.erc_transaction_account_id
     and T2.rc_fund_code = T1.erc_fund_code
     and T2.RC_CHANNEL_CODE='TTTNETJSYH'
     and T2.RC_TRANS_DATE='20200330'
     )pp
where pp.countflag != 0 ;




