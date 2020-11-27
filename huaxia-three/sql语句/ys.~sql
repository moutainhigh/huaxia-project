select table_name from all_all_tables where table_name like '%YS%';
select * from YS_AP_USER;
select * from YS_PATCHBOLT_IMG order by crt_date desc;
select * from YS_PATCHBOLT_IMG_Practise order by crt_date desc;
--drop table YS_PATCHBOLT_IMG_Practise;
select * from YS_AP_BANK_LEVEL;
select * from YS_AP_SALES_RISKLEVEL order by crt_time desc;
---练习上传文件----------
--alter table upload_excel_practise drop column APP_ID ;
select * from upload_excel_practise  order by crt_time desc;
select count(*) from upload_excel_practise;
alter table upload_excel_practise modify ( FIELD01      VARCHAR2(256));
alter table upload_excel_practise modify ( FIELD02      VARCHAR2(256));
alter table upload_excel_practise modify ( FIELD03      VARCHAR2(256));
alter table upload_excel_practise modify ( FIELD04      VARCHAR2(256));
		select
			FILE_ID, FILE_NAME, FILE_PATH, BIG_CATEGORY, SMALL_CATEGORY, FILE_DESCRIBE,
			RIGHT_CONTROL,
			UPLOAD_DATE, OPERAT_CODE, OPERAT_PERSON
		from opas_file_upload a
 
select * from OPAS_AP_DICT s where s.dict_code = 'ZDWDDL'    ;
select * from OPAS_AP_DICT s where s.dict_code = 'ZDWDXL'    ;

select * from OPAS_AP_DICT s where s.dict_code = 'ALLOT_RXBD'    ;
select * from OPAS_AP_DICT a left join OPAS_AP_DICT_DETAIL s  on a.dict_id = s.dict_id where a.dict_code = 'ALLOT_RXBD' ;

	select
		FILE_UPLOAD_CONTROL
		from OPAS_AP_USER
		where USER_CODE = 'liuwei'
select * from OPAS_AP_USER where user_name = '刘恒';
update opas_ap_user set file_upload_control = 'A' where user_code = 'liuwei'; 
select distinct
		FILE_UPLOAD_CONTROL
		from OPAS_AP_USER
		where USER_CODE = '文亚辉'
select * from OPAS_AP_USER where user_name = '刘恒';
select  *	from OPAS_AP_USER where USER_CODE = '亚辉'
select * from OPAS_FILE_UPLOAD  order by upload_date desc;


select APPID, CURRUSER, crt_date, ALLOT_VERSION as ALLOTVERSION
		  from (select b.APP_ID as APPID,
		               b.CURR_OPT_USER as CURRUSER,
		               a.crt_date,
		               b.ALLOT_VERSION
		          from ys_allot_apply_allot b, OPAS_BIZ_INP_APPL_C1 a
		         where b.app_id = a.app_id
		           AND b.SUBMIT_STATUS = '0'
		           AND b.SUBMIT_TYPE = '0'
		           AND b.DEL_STATUS = '0'
		           
		        )
		 where rownum <=20 order by crt_date, APPID
     
     select APPID, CURRUSER, crt_date, ALLOT_VERSION as ALLOTVERSION
		  from (select b.APP_ID as APPID,
		               b.CURR_OPT_USER as CURRUSER,
		               a.crt_date,
		               b.ALLOT_VERSION
		          from ys_allot_apply_allot b, OPAS_BIZ_INP_APPL_C1 a
		         where b.app_id = a.app_id
		           AND b.SUBMIT_STATUS = '0'
		           AND b.SUBMIT_TYPE = '0'
		           AND b.DEL_STATUS = '0'
		           
		        ) order by crt_date, APPID 
 select * from ys_allot_apply_allot order by crt_time desc;
  select * from YS_ALLOT_APPLY_ALLOT_HIS order by crt_time desc;
  select * from OPAS_BIZ_INP_APPL_C1 order by crt_time desc;
select * from OPAS_AP_USER order by crt_date desc;
select * from OPAS_APPLY_LIFE_CICLE order by crt_date desc;
select count(*)  from OPAS_APPLY_LIFE_CICLE;
select * from YS_QUEUE_HOUR_LOG order by crt_time desc;
update OPAS_AP_USER set user_password = '670b14728ad9902aecba32e22fa4f6bd' where user_Code ='u001';
update OPAS_AP_USER set user_password = '670b14728ad9902aecba32e22fa4f6bd' where user_Code ='sp222';
select * from opas_apply_life_cicle where app_id = '240709BA11P01591' order by crt_date asc;
select * from opas_apply_life_cicle where app_id = '230714B911P02341' order by crt_date asc;
select * from opas_apply_life_cicle where app_id = '190726BA11SV5651' order by crt_date asc;



SELECT c.APP_ID,
			   c.APP_PROD,
		       c.C1_CNAME,
		       c.C1_IDNBR,
		       c.C1_CONAME,
		       c.QUICK_CARD_FLAG,
		       c.EXIST_CARD_FLAG as IS_HAVECARD_CUST,
		       a.YDJ_FLAG,
		       a.PROCESS_ID,
		       a.MATCHING_CARD_FLAG,
               (YS_WORKHOUR(a.LST_TEAM_DATE,sysdate) + 
               	  nvl((select q.QUEUE_HOUR from YS_QUEUE_HOUR_LOG q where a.app_id=q.app_id
				          
                  ),0)
                ) as QUE_HOURS,
                nvl((select workday(trunc(a.fraud_date),trunc(sysdate)) from dual),0) as GRO_DATE,
		       (select count(r.REMARK_ID) from OPAS_ALLOT_APPLY_REMARK r where r.APP_ID = a.APP_ID) as REMARK
		  from ys_ALLOT_APPLY_ALLOT a
		  left join OPAS_BIZ_INP_APPL_C1 c on c.app_id = a.app_id
		 WHERE a.SUBMIT_STATUS = '0'
		   AND a.SUBMIT_TYPE = '0'
		   AND a.SYN_FLAG = '0'
       and  a.app_id = '190804A811P02191' ;
       
       SELECT c.APP_ID,
			   c.APP_PROD,
		       c.C1_CNAME,
		       c.C1_IDNBR,
		       c.C1_CONAME,
		       c.QUICK_CARD_FLAG,
		       c.EXIST_CARD_FLAG as IS_HAVECARD_CUST,
		       a.YDJ_FLAG,
		       a.PROCESS_ID,
		       a.MATCHING_CARD_FLAG,
               (YS_WORKHOUR(a.LST_TEAM_DATE,sysdate) + 
               	  nvl((select q.QUEUE_HOUR from YS_QUEUE_HOUR_LOG q where a.app_id=q.app_id
				          
                  ),0)
                ) as QUE_HOURS,
                 TO_CHAR(a.MODIFY_DATE, 'YYYY-MM-DD') as GRO_DATE,
		       (select count(r.REMARK_ID) from OPAS_ALLOT_APPLY_REMARK r where r.APP_ID = a.APP_ID) as REMARK
		  from ys_ALLOT_APPLY_ALLOT a
		  left join OPAS_BIZ_INP_APPL_C1 c on c.app_id = a.app_id
       left join
   opas_fqz_big_response  f on f.app_id = a.app_id  AND f.fqz_requesttype = 'AF1005'   AND f.fqz_requesttype = 'AF1005'
		 WHERE a.SUBMIT_STATUS = '0'
		   AND a.SUBMIT_TYPE = '0'
		   AND a.SYN_FLAG = '0'
       and  a.app_id = '190804A811P02191' 
      
  select * from ys_allot_apply_allot where app_id = '190804A811P02191' ;
  opas_allot_apply_allot
  OPAS_BIZ_INP_APPL_C1
  ----2020/8/24 11:22	test				200116A953P58041
  ----2020/8/27	AntiFraud				2008180922WJF001
  ----2019/5/11	AntiFraud				190726BA32PR6401

  select * from opas_fqz_big_response where fqz_requesttype = 'AF1005' and FQZ_value ='D' for update;
    select * from opas_fqz_big_response where app_id = '1905291622P12371';
        AND f.fqz_requesttype = 'AF1005'
         AND f.FQZ_value ='D'
         
         	190804A811P02161
1905291622P12161
2001121622P22581
         
         SELECT c.APP_ID,
			   c.APP_PROD,
		       c.C1_CNAME,
		       c.C1_IDNBR,
		       c.C1_CONAME,
		       c.QUICK_CARD_FLAG,
		       c.EXIST_CARD_FLAG as IS_HAVECARD_CUST,
		       a.YDJ_FLAG,
		       a.PROCESS_ID,
		       a.MATCHING_CARD_FLAG,
	       
               (YS_WORKHOUR(a.LST_TEAM_DATE,sysdate) + 
               	  nvl((select q.QUEUE_HOUR from YS_QUEUE_HOUR_LOG q where a.app_id=q.app_id
				        	AND q.QUEUE_FLAG = '1'
					        AND q.CRT_USER = '201388'
                  ),0)
                ) as QUE_HOURS,
                TO_CHAR(a.MODIFY_DATE, 'YYYY-MM-DD') as GRO_DATE,
		       (select count(r.REMARK_ID) from OPAS_ALLOT_APPLY_REMARK r where r.APP_ID = a.APP_ID) as REMARK,
           TO_CHAR((select count(*) from opas_fqz_big_response  f where f.app_id = a.app_id  AND f.fqz_requesttype = 'AF1005'   AND f.fqz_requesttype = 'AF1005'
     )) as needTelcheck
		  from ys_ALLOT_APPLY_ALLOT a
		  left join OPAS_BIZ_INP_APPL_C1 c on c.app_id = a.app_id
		 
		 WHERE a.SUBMIT_STATUS = '0'
		   AND a.SUBMIT_TYPE = '0'
		   AND a.SYN_FLAG = '0'
		
				AND a.CURR_STATUS = '3'
		   
				AND a.CURR_NODE = '01'
   
				AND a.CURR_OPT_USER =  '201388'
  
				AND a.DEL_STATUS = '0'
		
				ORDER BY QUICK_CARD_FLAG desc, needTelcheck desc,GRO_DATE desc

 select app_id,curr_status from ys_ALLOT_APPLY_ALLOT where curr_node = '01' order by crt_time desc ;
 select * from ys_ALLOT_APPLY_ALLOT order by crt_time desc ;
  select  c.c4_abuser from OPAS_BIZ_INP_APPL_C1 c where c.APP_ID = '200806A836P07011' ;
  select * from OPAS_AP_USER where USER_CODE = '1634962';
    select  c.APP_ID from OPAS_BIZ_INP_APPL_C1 c where  c.c4_abuser = '201388' ;
    select * from YS_AP_USER where USER_CODE = '355188';
   select  c.c4_abuser,count(c4_abuser) as cc  from OPAS_BIZ_INP_APPL_C1 c group by c4_abuser order by cc desc
   select * from opas_fqz_big_response where app_id = '200616A836S00021';
   select * from opas_fqz_big_response group by app_id 
 select  a.C1_CONAME  from OPAS_BIZ_INP_APPL_C1 a ;
 
    
         SELECT c.APP_ID,
			   c.APP_PROD,
		       c.C1_CNAME,
		       c.C1_IDNBR,
		       c.C1_CONAME,
		       c.QUICK_CARD_FLAG,
		       c.EXIST_CARD_FLAG as IS_HAVECARD_CUST,
		       a.YDJ_FLAG,
		       a.PROCESS_ID,
		       a.MATCHING_CARD_FLAG,
	       
               (YS_WORKHOUR(a.LST_TEAM_DATE,sysdate) + 
               	  nvl((select q.QUEUE_HOUR from YS_QUEUE_HOUR_LOG q where a.app_id=q.app_id
				        	AND q.QUEUE_FLAG = '1'
					        AND q.CRT_USER = '201388'
                  ),0)
                ) as QUE_HOURS,
                TO_CHAR(a.MODIFY_DATE, 'YYYY-MM-DD') as GRO_DATE,
		       (select count(r.REMARK_ID) from OPAS_ALLOT_APPLY_REMARK r where r.APP_ID = a.APP_ID) as REMARK,
           TO_CHAR((select count(*) from opas_fqz_big_response  f where f.app_id = a.app_id  AND f.fqz_requesttype = 'AF1003'   AND f.FQZ_value ='D'
     )) as needTelcheck
		  from ys_ALLOT_APPLY_ALLOT a
		  left join OPAS_BIZ_INP_APPL_C1 c on c.app_id = a.app_id
		 
		 WHERE a.SUBMIT_STATUS = '0'
		   AND a.SUBMIT_TYPE = '0'
		   AND a.SYN_FLAG = '0'
		
				AND a.CURR_STATUS = '3'
		   
				AND a.CURR_NODE = '01'
   
				AND a.CURR_OPT_USER =  '201388'
  
				AND a.DEL_STATUS = '0'
		
				ORDER BY QUICK_CARD_FLAG desc, needTelcheck desc,GRO_DATE desc;
        
        SELECT count(*)
		  from ys_ALLOT_APPLY_ALLOT a
		  left join OPAS_BIZ_INP_APPL_C1 c on c.app_id = a.app_id
		 WHERE a.SUBMIT_STATUS = '0'
		   AND a.SUBMIT_TYPE = '0'
		   AND a.SYN_FLAG = '0'
				AND a.CURR_STATUS = '3'
				AND a.CURR_NODE = '01'
				AND a.CURR_OPT_USER =  '201388'
				AND a.DEL_STATUS = '0';
----------------查询筛选条件--------------------
		SELECT c.app_id,
           c.c4_apbatch,
	         c.c5_jctype,
           c.c4_abuser,
           c.C1_CONAME,
             TO_CHAR((select count(*) from opas_fqz_big_response  f where f.app_id = a.app_id  AND f.fqz_requesttype = 'AF1003'   AND f.FQZ_value ='D'
     )) as needTelcheck,
     fqz.FQZ_VALUE
		  from ys_ALLOT_APPLY_ALLOT a
		  left join OPAS_BIZ_INP_APPL_C1 c on c.app_id = a.app_id
      left join opas_fqz_big_response fqz on   fqz.app_id = a.app_id AND fqz.fqz_type = 'AF_ALL'  AND fqz.fqz_requesttype = 'AF1003'
		 WHERE a.SUBMIT_STATUS = '0'
		   AND a.SUBMIT_TYPE = '0'
		   AND a.SYN_FLAG = '0'
				AND a.CURR_STATUS = '3'
				AND a.CURR_NODE = '01'
				AND a.CURR_OPT_USER =  '201388'
				AND a.DEL_STATUS = '0'
        
				ORDER BY QUICK_CARD_FLAG desc, needTelcheck desc;
 --------------------更新造件-------
         select * from opas_fqz_big_response where  app_id in(
         '1905121622P21891',
'2001121622P21881',
'1905121622P21901',
'2001121622P21871',
'2001121622P21901'
         ) for update;
-----------------------------------------
		SELECT c.app_id,
           c.c4_apbatch,
	         c.c5_jctype,
           c.c4_abuser,
           c.C1_CONAME,
             TO_CHAR((select count(*) from opas_fqz_big_response  f where f.app_id = a.app_id  AND f.fqz_requesttype = 'AF1003'   AND f.FQZ_value ='D'
     )) as needTelcheck,
     fqz.FQZ_VALUE
		  from ys_ALLOT_APPLY_ALLOT a
		  left join OPAS_BIZ_INP_APPL_C1 c on c.app_id = a.app_id
      left join opas_fqz_big_response fqz on   fqz.app_id = a.app_id AND fqz.fqz_type = 'AF_ALL'  AND fqz.fqz_requesttype = 'AF1003'
		 WHERE a.SUBMIT_STATUS = '0'
		   AND a.SUBMIT_TYPE = '0'
		   AND a.SYN_FLAG = '0'
				AND a.CURR_STATUS = '3'
				AND a.CURR_NODE = '01'
				AND a.CURR_OPT_USER =  '201388'
				AND a.DEL_STATUS = '0'
        
     --   AND c.c4_apbatch = 'QT'
     --   AND c.c5_jctype = 'QT'
       -- AND c.c4_abuser = '9113013'
       -- AND c.C1_CONAME like '%公司'
       -- AND fqz.FQZ_VALUE = 'A'
				ORDER BY QUICK_CARD_FLAG desc, needTelcheck desc;

        	SELECT count(*)
		  from ys_ALLOT_APPLY_ALLOT a
		  left join OPAS_BIZ_INP_APPL_C1 c on c.app_id = a.app_id
		   left join opas_fqz_big_response fqz on   fqz.app_id = a.app_id AND fqz.fqz_type = 'AF_ALL'
          AND fqz.fqz_requesttype = 'AF1003'
		 WHERE a.SUBMIT_STATUS = '0'
		   AND a.SUBMIT_TYPE = '0'
		   AND a.SYN_FLAG = '0'
       		AND a.CURR_STATUS = '3'
				AND a.CURR_NODE = '01'
				AND a.CURR_OPT_USER =  '201388'
				AND a.DEL_STATUS = '0'
        AND c.C1_CONAME like '%公司'
--------------------------文件上传-------------------------------------
select * from OPAS_FILE_UPLOAD order by upload_date desc;
select * from OPAS_FILE_UPLOAD_LOG order by operat_date desc;

select * from YS_FILE_UPLOAD order by upload_date desc;
select * from YS_FILE_UPLOAD_LOG order by operat_date desc;
comment on column YS_FILE_UPLOAD_LOG.OPERAT_TYPE
  is '日志类型1新增 2删除 3更新';
select * from OPAS_KEY_VALUE_CONFIGURE order by CRT_DATE desc;
select VALUE_CONTENT
		from OPAS_KEY_VALUE_CONFIGURE	
		where KEY_NAME = 	'ys_report_path';
    select VALUE_CONTENT
		from OPAS_KEY_VALUE_CONFIGURE	
		where KEY_NAME = 	'YS_SAVE_PATH';
    
select * from OPAS_KEY_VALUE_CONFIGURE order by crt_date desc;
    
    select * from XM_GJJ_DKXX ;
        select * from XM_GJJ_JCXX ;
            select * from XM_GJJ_TQXX ;
      DELETE FROM XM_GJJ_DKXX WHERE APP_ID = i_app_id;
      DELETE FROM XM_GJJ_JCXX WHERE APP_ID = i_app_id;
      DELETE FROM XM_GJJ_TQXX WHERE APP_ID = i_app_id;
      DELETE FROM XM_INFO WHERE APP_ID = i_app_id;
      DELETE FROM XM_PERSONAL_
      select * from sys_user;
       opas_biz_inp_appl_c1    C5_ABCODE
select  * from opas_biz_inp_appl_c1 ;
 select * from ys_ap_user where user_code = '201388';
select * from ys_ap_user_org where user_id = '2e2c07286add47788cfc172a13e8fdfb';
select * from ys_ap_org where org_id = '0d33284f42d349e899119fc28498f123';
select * from  ys_ap_bank_level where group_code = 'FHZHOU';
      
       select * from ys_ap_user where user_code = 'liuwei';
select * from ys_ap_user_org where user_id = '4a3f6a5099c7429881c521152b3b9673';
select * from ys_ap_org where org_id = '0d33284f42d349e899119fc28498f123';
select * from  ys_ap_bank_level where group_code = 'FHZHOU'; 
select * from  ys_ap_bank_level where C5_ABCODE = '90499'; 
select  * from opas_biz_inp_appl_c1 where app_id = '190804A811P02211';

select * from OPAS_CREDIT_TELCHECK_LIST order by create_time desc;
select * from OPAS_CREDIT_TELCHECK_LIST_HIS order by create_time desc;
select count(*) from OPAS_CREDITINFO_LIST order by create_time desc;
select * from OPAS_CREDITINFO_LIST order by create_time desc;
select distinct curr_status from OPAS_CREDITINFO_LIST ;
select count(*) from OPAS_CREDITINFO_LIST where curr_status = '0';
select count(*) from OPAS_CREDITINFO_LIST where curr_status = '1';
select count(*) from OPAS_CREDITINFO_LIST where curr_status is null;
comment on column OPAS_CREDITINFO_LIST.CURR_STATUS
  is '当前状态 1 启用状态 2 停用状态';
select * from OPAS_CREDIT_TELCHECK_LIST order by create_time desc;
  	SELECT *	from OPAS_CREDITINFO_LIST where company_name = '华夏乐视';
  update OPAS_CREDITINFO_LIST set company_name = '华夏乐视' where auto_id = '3ac82538df5043ac84cc77e26a2012ce';
  select
		AUTO_ID, APP_ID, CRT_DATE, CRT_USER,MARCH_RESULT
		from OPAS_KEYFILED_MARCHINFO ORDER BY CRT_DATE DESC;
  select * 
		from OPAS_KEYFILED_MARCHINFO ;
     select COUNT(*)
		from OPAS_KEYFILED_MARCHINFO;
    -- 标准卡是有  华夏华夏华夏银行 没有 华夏华夏银行
  	SELECT *	from OPAS_CREDITINFO_LIST where company_name = '华夏华夏华夏银行'; 
        	SELECT *	from OPAS_CREDITINFO_LIST where company_name = '华夏华夏银行';
   -- 易达金卡是有  华夏华夏银行 没有 华夏华夏华夏银行
    	SELECT *	from OPAS_CREDIT_TELCHECK_LIST where company_name = '华夏华夏银行' ;
      	SELECT *	from OPAS_CREDIT_TELCHECK_LIST where company_name = '华夏华夏华夏银行';
  select * from OPAS_BIZ_INP_APPL_C1;
   --标准卡  华夏华夏银行
   select * from OPAS_KEYFILED_MARCHINFO where app_id = '200918BA11SJ8661' for update;
   --易达金 华夏华夏华夏银行
   select * from OPAS_KEYFILED_MARCHINFO where app_id = '200811A811P00021' for update;
   
   select * from OPAS_KEYFILED_MARCHINFO where app_id = '200918BA11SJ8661' ;
     select * from OPAS_KEYFILED_MARCHINFO where app_id = '200811A811P00021';
------上传文件
select * from OPAS_LOW_RISK_LIST_TEMP;
select * from OPAS_LOW_RISK_LIST   order by crt_time asc;
select * from OPAS_LOW_RISK_LIST   order by crt_time desc;
--193 10767360 5383777 32474857 60857797 34498750
select count(*) from OPAS_LOW_RISK_LIST;
--96032 193 1719573 13933515 1 41928481 32474857 22999167
select count(*) from OPAS_LOW_RISK_LIST_TEMP; 
--193 5383768 5383772 18929316 49358215 22999168
select count(*) from OPAS_LOW_RISK_LIST where LIST_TYPE = '1';
--alter table  OPAS_LOW_RISK_LIST_temp1 rename to OPAS_LOW_RISK_LIST
NCIIC_FOREIGNER_INFO
--alter table OPAS_LOW_RISK_LIST rename to OPAS_LOW_RISK_LIST_temp2
--alter table OPAS_LOW_RISK_LIST_temp1 rename to OPAS_LOW_RISK_LIST
select count(*) from OPAS_LOW_RISK_LIST_TEMP1;
--select count(*) from OPAS_LOW_RISK_LIST_TEMP1;
--drop table OPAS_LOW_RISK_LIST_TEMP1
select count(*) from ys_ALLOT_APPLY_ALLOT;
select * from OPAS_LOW_RISK_LIST_TEMP   order by crt_time asc;
select * from OPAS_LOW_RISK_LIST_TEMP   order by crt_time desc;
truncate table OPAS_LOW_RISK_LIST_TEMP;
select count(*) from OPAS_LOW_RISK_LIST where LIST_TYPE = '2';
--delete from OPAS_LOW_RISK_LIST;
--delete from OPAS_LOW_RISK_LIST_TEMP;
select * from opas_low_risk_list where uuid = '9c4033caea4d4de7a1d228f5afbb8cba';
ab08bf96f66c4c20be8c673bac7414e9
select * from opas_low_risk_list where uuid = 'e00b656b6b734312bfec3e308c443729';
delete from OPAS_LOW_RISK_LIST;

select * from OPAS_LOWRISK_BATCHFILE_INFO order by input_time desc;
update OPAS_LOWRISK_BATCHFILE_INFO SET INPUT_COUNTS = 0, REMARK = 'success' where id in ( select id from( select id from OPAS_LOWRISK_BATCHFILE_INFO order by INPUT_TIME desc ) where rownum = 1 ) 
--alter table OPAS_LOWRISK_BATCHFILE_INFO modify  INPUT_COUNTS NUMBER(9);
comment on column OPAS_LOWRISK_BATCHFILE_INFO.FILE_STATUS
  is '上传状态 1 待查询 2查询成功 3 查询失败';
  select file_name  from(
   select 
	    file_name
	  from OPAS_LOWRISK_BATCHFILE_INFO
	
     order by INPUT_TIME desc
     ) where rownum = 1;
     
      select id  from(
   select 
	    id
	  from OPAS_LOWRISK_BATCHFILE_INFO
	
     order by INPUT_TIME desc
     ) where rownum = 1;
     
       select *  from(
   select 
	    *
	  from OPAS_LOWRISK_BATCHFILE_INFO
	
     order by INPUT_TIME desc
     ) where rownum = 1;
     
--115
select count(*) from OPAS_LOWRISK_BATCHFILE_INFO order by input_time desc;

select * from OPAS_KEY_VALUE_CONFIGURE;   
     
     OPAS_LOW_RISK_LIST_RENAME
     PROC_FN_KEYINFO
     
     	select APPID, CURRUSER, crt_date, ALLOT_VERSION as ALLOTVERSION
		  from (select b.APP_ID as APPID,
		               b.CURR_OPT_USER as CURRUSER,
		               a.crt_date,
		               b.ALLOT_VERSION
		          from ys_allot_apply_allot b, OPAS_BIZ_INP_APPL_C1 a
		         where b.app_id = a.app_id
		           AND b.SUBMIT_STATUS = '0'
		           AND b.SUBMIT_TYPE = '0'
		           AND b.DEL_STATUS = '0'
		           <if test="appId != null and appId !='' ">
		                AND a.APP_ID = #{appId,jdbcType=VARCHAR}
		           </if>
		           <if test="idnbr != null and idnbr !='' ">
		                AND a.C1_IDNBR = #{idnbr,jdbcType=VARCHAR}
		           </if>
		           <if test="currNode != null and currNode !='' ">
		                AND b.CURR_NODE = #{currNode}
		           </if>
		           <if test="currStatus != null and currStatus !='' ">
		                AND b.CURR_STATUS = #{currStatus}
		           </if>
		           <if test="currOptGroup != null and currOptGroup !='' ">
		                AND b.CURR_OPT_AREA = #{currOptGroup}
		           </if>
		        )
		 where rownum &lt;=20 order by crt_date, APPID
     
     select * from ys_ALLOT_APPLY_ALLOT;
     
     
     
     	select APPID,LST_TEAM_DATE, QUICK_CARD_FLAG,CURRUSER, crt_date, workday(trunc(FRAUD_DATE),trunc(sysdate)) as GRODATE
		  from (select b.APP_ID as APPID,
		               b.CURR_OPT_USER as CURRUSER,
		               a.crt_date,
                   b.LST_TEAM_DATE,
		               b.ALLOT_VERSION,
                   b.FRAUD_DATE,
                   a.QUICK_CARD_FLAG
		          from ys_allot_apply_allot b, OPAS_BIZ_INP_APPL_C1 a
		         where b.app_id = a.app_id
		           AND b.SUBMIT_STATUS = '0'
		           AND b.SUBMIT_TYPE = '0'
		           AND b.DEL_STATUS = '0'
		          
		        )
		 where rownum <= 20 order by 
      QUICK_CARD_FLAG desc,
        GRODATE desc,
        TRUNC(LST_TEAM_DATE,'MI') asc,
        substr(APPID,8,3) asc,
        substr(APPID,7,1) desc,
        substr(APPID,1,6) asc,
        substr(APPID,11,5) asc,
        substr(APPID,16,1) desc
     
     OPAS_LOW_RISK_LIST_RENAME
     opas_allot_apply_allot
     opas_out_file_check 
     
     	update OPAS_LOWRISK_BATCHFILE_INFO
		set 
			INPUT_COUNTS = 1234,
			REMARK = '12341234'

		where id in (
		 select id  from(
   select 
	    id
	  from OPAS_LOWRISK_BATCHFILE_INFO
     order by INPUT_TIME desc
     ) where rownum = 1
		)
    
    select APPID, CURRUSER, crt_date, ALLOT_VERSION as ALLOTVERSION,FRAUD_DATE
		  from (select b.APP_ID as APPID,
		               b.CURR_OPT_USER as CURRUSER,
		               a.crt_date,
		               b.ALLOT_VERSION,
		                  b.FRAUD_DATE
		          from ys_allot_apply_allot b, OPAS_BIZ_INP_APPL_C1 a
		         where b.app_id = a.app_id
		           AND b.SUBMIT_STATUS = '0'
		           AND b.SUBMIT_TYPE = '0'
		           AND b.DEL_STATUS = '0'
	
		        )
		 where rownum <= 20 order by FRAUD_DATE DESC, crt_date, APPID
     select APPID, CURRUSER, crt_date, ALLOT_VERSION as ALLOTVERSION,FRAUD_DATE from 
     (select b.APP_ID as APPID, b.CURR_OPT_USER as CURRUSER, a.crt_date, b.ALLOT_VERSION,
      b.FRAUD_DATE from ys_allot_apply_allot b, OPAS_BIZ_INP_APPL_C1 
      a where b.app_id = a.app_id AND b.SUBMIT_STATUS = '0' AND 
      b.SUBMIT_TYPE = '0' AND b.DEL_STATUS = '0' AND b.CURR_NODE = '01' 
      AND b.CURR_STATUS = '1' AND b.CURR_OPT_AREA = 'K1001' )
       where rownum <=20 order by FRAUD_DATE DESC, crt_date, APPID 

       
       select APP_ID,APP_PROD,C1_CNAME,C1_IDNBR,C1_CONAME,
       QUICK_CARD_FLAG,IS_HAVECARD_CUST,YDJ_FLAG,PROCESS_ID,
       MATCHING_CARD_FLAG ,QUE_HOURS,TO_CHAR(GRO_DATE,'YYYY-MM-DD') 
       GRO_DATE,needTelcheck from ( SELECT c.APP_ID, c.APP_PROD, c.C1_CNAME, 
       c.C1_IDNBR, c.C1_CONAME, c.QUICK_CARD_FLAG, c.EXIST_CARD_FLAG as IS_HAVECARD_CUST,
        a.YDJ_FLAG, a.PROCESS_ID, a.MATCHING_CARD_FLAG, 
        (YS_WORKHOUR(a.LST_TEAM_DATE,sysdate) + nvl((select q.QUEUE_HOUR from YS_QUEUE_HOUR_LOG q 
        where a.app_id=q.app_id AND q.QUEUE_FLAG = '1' AND q.CRT_USER = '201938' ),0) ) as QUE_HOURS, 
        a.FRAUD_DATE as GRO_DATE, (select count(r.REMARK_ID) from OPAS_ALLOT_APPLY_REMARK r where r.APP_ID = a.APP_ID) as REMARK, 
        TO_CHAR((select count(*) from opas_fqz_big_response f where f.app_id = a.app_id AND f.fqz_requesttype = 'AF1003' AND f.FQZ_value ='D' )) as needTelcheck 
        from ys_ALLOT_APPLY_ALLOT a left join OPAS_BIZ_INP_APPL_C1 c on c.app_id = a.app_id left join 
        opas_fqz_big_response fqz on fqz.app_id = a.app_id AND fqz.fqz_type = 'AF_ALL' AND fqz.fqz_requesttype = 'AF1003' 
        WHERE a.SUBMIT_STATUS = '0' AND a.SUBMIT_TYPE = '0' AND a.SYN_FLAG = '0' AND a.CURR_STATUS = '3' AND a.CURR_NODE = '01' AND
         a.CURR_OPT_USER = '201938' AND a.DEL_STATUS = '0' ORDER BY QUICK_CARD_FLAG desc,needTelcheck desc,GRO_DATE desc ) 
--刘伟
insert into camsonline.YS_AP_MENU (MENU_ID, MENU_NAME, MENU_LEVEL, MENU_PARENT_ID, MENU_URL, STATUS, CRT_DATE, CRT_TIME, CRT_USER, LST_UPD_USER, LST_UPD_DATE, LST_UPD_TIME, BATCH_DATE, REC_STATUS, SCR_LEVEL, QUEUE_FLAG, NODE_NO, SORT_FLAG)
values ('ce5b4fde943943adb26dc713b18a2536', '文件管理', '1', '2a3c1526ac4849d4bc757a42ae42b022', 'web/authorize/systemset.html', null, to_date('08-09-2020 17:17:46', 'dd-mm-yyyy hh24:mi:ss'), to_timestamp('08-09-2020 17:17:46.229595', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null, to_date('08-09-2020 17:17:46', 'dd-mm-yyyy hh24:mi:ss'), to_timestamp('08-09-2020 17:17:46.229595', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0', '00', null, null, null);
insert into camsonline.YS_AP_MENU (MENU_ID, MENU_NAME, MENU_LEVEL, MENU_PARENT_ID, MENU_URL, STATUS, CRT_DATE, CRT_TIME, CRT_USER, LST_UPD_USER, LST_UPD_DATE, LST_UPD_TIME, BATCH_DATE, REC_STATUS, SCR_LEVEL, QUEUE_FLAG, NODE_NO, SORT_FLAG)
values ('1af67bc619b047bbb03b98ab271a8941', '文档查看', '1', '2a3c1526ac4849d4bc757a42ae42b022', 'web/authorize/userfileview.html', null, to_date('10-09-2020 16:11:02', 'dd-mm-yyyy hh24:mi:ss'), to_timestamp('10-09-2020 16:11:02.177088', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null, to_date('10-09-2020 16:11:02', 'dd-mm-yyyy hh24:mi:ss'), to_timestamp('10-09-2020 16:11:02.177088', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0', '00', null, null, null);
commit;
insert into camsonline.OPAS_AP_MENU (MENU_ID, MENU_NAME, MENU_LEVEL, MENU_PARENT_ID, MENU_URL, STATUS, CRT_DATE, CRT_TIME, CRT_USER, LST_UPD_USER, LST_UPD_DATE, LST_UPD_TIME, BATCH_DATE, REC_STATUS, SCR_LEVEL, QUEUE_FLAG, NODE_NO, SORT_FLAG)
values ('fdbd4aa9faa8497e837c6b241428c947', '低风险客户名单库', '1', '6144776f71d942c68d3a0597dfb37652', 'web/sysparm/lowRiskCustomers.html', null, to_date('19-10-2020 16:36:41', 'dd-mm-yyyy hh24:mi:ss'), to_timestamp('19-10-2020 16:36:41.311997', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null, to_date('19-10-2020 16:36:41', 'dd-mm-yyyy hh24:mi:ss'), to_timestamp('19-10-2020 16:36:41.311997', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0', '00', null, null, null);
commit;
select * from YS_AP_MENU where menu_id = 'ce5b4fde943943adb26dc713b18a2536';
select * from YS_AP_MENU where menu_id = '1af67bc619b047bbb03b98ab271a8941';
select * from OPAS_AP_MENU where menu_id = 'fdbd4aa9faa8497e837c6b241428c947';

--delete from YS_AP_MENU where menu_id = 'ce5b4fde943943adb26dc713b18a2536';
--delete from YS_AP_MENU where menu_id = '1af67bc619b047bbb03b98ab271a8941';
--delete from OPAS_AP_MENU where menu_id = 'fdbd4aa9faa8497e837c6b241428c947';
insert into camsonline.OPAS_KEY_VALUE_CONFIGURE (AUTO_ID, KEY_NAME, VALUE_CONTENT, REMARK, CRT_USER, CRT_DATE, LST_UPD_USER, LST_UPD_DATE)
values (SYS_GUID(), 'NEWLOWRISKTEMPPATH22', '/app/opas/lowristlisttemp/', '审批文件解析完临时存放的目录', 'admin', SYSDATE, 'admin', SYSDATE);
commit;
select * from OPAS_KEY_VALUE_CONFIGURE order by crt_date desc;

insert into camsonline.OPAS_KEY_VALUE_CONFIGURE (AUTO_ID, KEY_NAME, VALUE_CONTENT, REMARK, CRT_USER, CRT_DATE, LST_UPD_USER, LST_UPD_DATE)
values (SYS_GUID(), 'NEWLOWRISKTEMPPATH', '/app/opas/lowristlisttemp/', '审批文件解析完临时存放的目录', 'admin', SYSDATE, 'admin', SYSDATE);
commit;
insert into camsonline.OPAS_KEY_VALUE_CONFIGURE (AUTO_ID, KEY_NAME, VALUE_CONTENT, REMARK, CRT_USER, CRT_DATE, LST_UPD_USER, LST_UPD_DATE)
values (SYS_GUID(), 'NEWLOWRISKPATH', '/app/opas/lowristlist/', '审批上传低风险名单库的TXT文件路劲', 'admin', SYSDATE, 'admin', SYSDATE);
commit;
insert into camsonline.OPAS_KEY_VALUE_CONFIGURE (AUTO_ID, KEY_NAME, VALUE_CONTENT, REMARK, CRT_USER, CRT_DATE, LST_UPD_USER, LST_UPD_DATE)
values (SYS_GUID(), 'ys_report_path', '/camsys/ys/pdf/report/', '预审上传pdf文档临时存放的文件路径', 'admin', SYSDATE, 'admin', SYSDATE);
commit;
insert into camsonline.OPAS_KEY_VALUE_CONFIGURE (AUTO_ID, KEY_NAME, VALUE_CONTENT, REMARK, CRT_USER, CRT_DATE, LST_UPD_USER, LST_UPD_DATE)
values (SYS_GUID(), 'YS_SAVE_PATH', '/camsys/ys/pdf/upload/', '预审上传pdf文件路径', 'admin', SYSDATE, 'admin', SYSDATE);
commit;
-- 一个数组，每个元素类型是  varchar2(128)
create or replace type varchar_array is Table of varchar2(128);
--drop table tb1324;
-- Create table
create  table tb1324
(
  field01       VARCHAR2(128) not null
)
create or replace procedure test1(arr in varchar_array) is
begin
     FOR i IN arr.first..arr.last LOOP
     insert into tb1324 values(arr(i));
     END LOOP;
end test1;

create or replace procedure test1(arr in varchar_array) is
begin
     FOR i IN 1 .. arr.count LOOP
     insert into tb1324 values(arr(i));
     END LOOP;
end test1;

declare 
 type num_array is Table of NUMBER INDEX BY BINARY_INTEGER;
funcs_array num_array;
BEGIN
 funcs_array(1) := '1';
funcs_array(2) := '2';
funcs_array(3) := '3';
funcs_array(4) := '4';
funcs_array(5) := '5';
--for i in 1.. funcs_array.count 
--loop
 --dbms_output.put_line('i=' || i || ',funcs_array = '|| funcs_array(i));
 --end loop;
 
  FOR i IN funcs_array.first..funcs_array.last LOOP
     dbms_output.put_line('i=' || i || ',funcs_array = '|| funcs_array(i));
     END LOOP;
end;

select * from PBOC_BATCH_QUARTZ;
select * from  opas_key_value_configure

OPAS_PO_ALLOT2

OPAS_LOW_RISK_LIST_INSERT

create or replace procedure opas_low_risk_list_insert(list in opas_low_risk_list_t) is
begin
     FOR i IN list.first..list.last LOOP
     insert into OPAS_LOW_RISK_LIST_temp(UUID, CRT_USER, CRT_TIME,CUS_NAME,CRED_NO, MOBILE, COMPANY_NAME,LIST_TYPE, STATUS)
     values(
           sys_guid(),
			list(i).CRT_USER,
      sysdate,
			list(i).CUS_NAME,
			list(i).CRED_NO,
			list(i).MOBILE,
			list(i).COMPANY_NAME,
			list(i).LIST_TYPE,
			list(i).STATUS
     );
     END LOOP;
end opas_low_risk_list_insert;
create or replace type opas_low_risk_List_type force as object(
            CRT_USER     VARCHAR2(32),
            CUS_NAME     VARCHAR2(30),
            CRED_NO      VARCHAR2(19),
            MOBILE       VARCHAR2(12),
            COMPANY_NAME VARCHAR2(150),
            LIST_TYPE    VARCHAR2(2),
            STATUS       VARCHAR2(3)
 )
------消户前风险管控名单--------------------
select * from opas_close_ACCT_risk_list order by CRT_TIME desc; 
select * from opas_close_ACCT_risk_list order by CRT_TIME asc; 
select * from sys_user for update;


