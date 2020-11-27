select * from MULBOR_TRN_REQUEST ;
select * from MULBOR_MSG_RESPONSE;
select * from MULBOR_BASE for update ;
select count(*) from MULBOR_BASE;
select count(1) from MULBOR_BASE;MULBOR_TRN_REQUEST
select * from MULBOR_BASE order by crt_time desc;
select * from MULBOR_TRN_REQUEST order by crt_time desc;
select * from MULBOR_MSG_RESPONSE order by crt_time desc;
select * from MULBOR_BASE where uuid = 'ea009f666bab2516016bab2516cb0000';
select * from MULBOR_BASE where crt_user = 'CAMS';
select * from MULBOR_RISK_ITEM;
select * from MULBOR_ANTIFRAUD_INDE;
select * from MULBOR_BLACK_LIST;
select * from MULBOR_DESCREDIT_COUNT;
select * from MULBOR_GREY_LIST;
select * from mulbor_base for update
select * from MULBOR_TRN_SINGLE where trn_id = 'EA009F666CFB9CD1016CFB9DF3450005';
select * from MULBOR_TRN_REQUEST where trn_id = 'EA009F666CFB9CD1016CFB9DF3450005';
select * from mulbor_base where trn_id = 'EA009F666CFB9CD1016CFB9DF3450005';
select count(*)from mulbor_base
delete from MULBOR_GREY_LIST;
delete from MULBOR_DESCREDIT_COUNT;
delete from MULBOR_BLACK_LIST;
delete from MULBOR_ANTIFRAUD_INDE;
delete from MULBOR_RISK_ITEM;
delete from MULBOR_BASE;
commit;
delete * from MULBOR_TRN_REQUEST;
commit;
delete * from MULBOR_MSG_RESPONSE;
commit;

select * from FICO_BIG_DATA_HUB;
select * from FICO_TRN_REQUEST;
select * from Fico_MSG_RESPONSE;

delete from FICO_BIG_DATA_HUB;
delete from FICO_TRN_REQUEST;
delete from Fico_MSG_RESPONSE;

     select * from HNAIR_SIEBEL_MEMBER;
     select * from HNAIR_TRN_REQUEST ;
     select * from HNAIR_MSG_RESPONSE;
         
    delete from HNAIR_SIEBEL_MEMBER;
    delete from HNAIR_TRN_REQUEST;
    delete from HNAIR_MSG_RESPONSE;

select * from MULBOR_TRN_BATCH;
select * from MULBOR_TRN_BATCH_FILE;
select * from MULBOR_TRN_BATCH_DETAIL;
select count(*) from MULBOR_TRN_SINGLE;
select count(*) from MULBOR_TRN_SINGLE where query_status = '1';
select count(*) from MULBOR_TRN_BATCH_DETAIL where query_status = '0';
update MULBOR_TRN_BATCH_DETAIL set query_status = '0' where query_status = '1' ;
select count(*) from MULBOR_TRN_BATCH_DETAIL where query_status = '1';
select * from MULBOR_TRN_BATCH_DETAIL where query_status = '0';
select * from MULBOR_TRN_BATCH_DETAIL where query_status = '0'  for update ;
update mulbor_trn_batch_detail set cert_no = '130430199202120021' where cert_No = '1130430199202120021';
select count(*) from MULBOR_TRN_BATCH_DETAIL where query_status = '1';
select count(*) from MULBOR_TRN_BATCH_DETAIL where query_status = '0';
select * from MULBOR_TRN_SINGLE;

select count(*) from MULBOR_TRN_BATCH ;
select count(*) from MULBOR_TRN_BATCH_FILE;
select count(*) from MULBOR_TRN_BATCH_DETAIL ;

select * from MULBOR_TRN_SINGLE order by crt_time desc;
select count(*) from MULBOR_TRN_SINGLE;
select count(*) from  MULBOR_TRN_BATCH_DETAIL;
select * from TASK_QUERY_SETTING;
select * from MULBOR_TRN_REQUEST order by crt_time desc;
select * from MULBOR_MSG_RESPONSE;
select count(*) from MULBOR_TRN_BATCH_DETAIL ;
SELECT COUNT(*) FROM MULBOR_TRN_BATCH_DETAIL WHERE QUERY_STATUS = '0' OR QUERY_STATUS = '1' ;
SELECT COUNT(*) FROM MULBOR_TRN_BATCH_DETAIL WHERE QUERY_STATUS = '0' ;
delete from MULBOR_TRN_BATCH;
delete from MULBOR_TRN_BATCH_FILE;
delete from MULBOR_TRN_BATCH_DETAIL;
delete from MULBOR_TRN_SINGLE;
delete from MULBOR_FILE_SERVER;
commit;
delete * from MULBOR_TRN_REQUEST;
commit;
delete * from MULBOR_MSG_RESPONSE;
commit;
MULBOR_TRN_SINGLE

select * from MULBOR_BASE  order by crt_time desc;
select * from MULBOR_RISK_ITEM order by crt_time desc;
select *  from MULBOR_ANTIFRAUD_INDE  order by crt_time desc;
select * from MULBOR_BLACK_LIST  order by crt_time desc;
select * from MULBOR_DESCREDIT_COUNT order by crt_time desc;
select * from MULBOR_GREY_LIST order by crt_time desc;


select * from TASK_CALL_STATUS;

select t.source_code, t.channel_code, t.query_count, to_char(t.query_date, 'yyyy-mm-dd') from RPT_QUERY_COUNT_DAY t 
where t.source_code = '000700' group by t.source_code, t.channel_code, t.query_date, t.query_count order by t.channel_code,t.query_date;

select * from CONF_QUERY_COUNT;
select * from CONF_QUERY_COUNT for update;
select * from SYS_DATASOURCE for update;
select * from SYS_CHANNEL;
select * from TASK_QUERY_SETTING;

select * from TASK_QUERY_SETTING for update;
select * from MULBOR_BASE;

delete from conf_query_count where uuid = '8CBDF55813F032CEE0531907806A92EA';
delete from conf_query_count where source_code = '000100';
delete from CONF_QUERY_COUNT where source_code = '001500' and channel_code = '00';
delete from CONF_QUERY_COUNT where source_code = '001500' and channel_code = '01';
delete from CONF_QUERY_COUNT where source_code = '001500' and channel_code = '10';
select * from CONF_QUERY_COUNT;


	SELECT DICT_CODE,DICT_NAME FROM SYS_DICT SY 
		WHERE SY.PARENT_ID=(SELECT DICT_ID FROM SYS_DICT S WHERE S.DICT_CODE =#{groupCode})
  select * from TRN_QUERY_REVIEW;
  select count(*) from TRN_QUERY_REVIEW;
--482
select count(1) from bairong_als_base t 
where t.crt_time >= to_date('20190705000000','yyyymmddhh24miss') and t.crt_time <= to_date('20190705235959','yyyymmddhh24miss'); 
select count(1) from BAIRONG_ALS_TRN_BATCH_DETAIL t 
where t.crt_time >= to_date('20190705000000','yyyymmddhh24miss') and t.crt_time <= to_date('20190705000000','yyyymmddhh24miss'); 
select count(*) from BAIRONG_ALS_TRN_BATCH_DETAIL ;
select * from BAIRONG_ALS_TRN_BATCH_DETAIL;
update BAIRONG_ALS_TRN_BATCH_DETAIL set Query_status = '1' where rownum<1000;
SELECT MAX_COUNT,START_TIME,END_TIME FROM TASK_QUERY_SETTING
		WHERE
		TASK_TYPE = #{taskType}
		AND SYS_ID = #{sysId}
    
select * from CONF_QUERY_COUNT for update;
SELECT MAX_COUNT,START_TIME,END_TIME FROM TASK_QUERY_SETTING 
		 WHERE 
		 TASK_TYPE = #{taskType}
		 AND SYS_ID = #{sysId}
     
SELECT SOURCE_CODE,CHANNEL_CODE,MAX_COUNT,START_DATE,END_DATE,TO_CHAR(START_DATE,'YYYY-MM-DD HH24:MI:SS') START_TIME_STRING,TO_CHAR(END_DATE,'YYYY-MM-DD HH24:MI:SS') END_TIME_STRING
       FROM CONF_QUERY_COUNT
       WHERE SOURCE_CODE =  #{sourceCode,jdbcType=CHAR}
       AND CHANNEL_CODE = #{channelCode,jdbcType=CHAR}
       
UPDATE CONF_QUERY_COUNT
		   SET UPD_TIME = SYSDATE,
		       UPD_USER = 'liuwei2',
		       MAX_COUNT = 0,
		       START_DATE = SYSDATE, 
		       END_DATE = SYSDATE 
	     WHERE SOURCE_CODE =  001700
       AND CHANNEL_CODE = 00
       
UPDATE CONF_QUERY_COUNT
		   SET UPD_TIME = SYSDATE,
		       UPD_USER = #{updateUser,jdbcType=VARCHAR},
		       MAX_COUNT = #{maxCount,jdbcType=INTEGER},
		       START_TIME = TO_DATE(#{startTime}||'00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 
		       END_TIME = TO_DATE(#{endTime}||'23:59:59', 'YYYY-MM-DD HH24:MI:SS') 
	     WHERE SOURCE_CODE =  #{sourceCode,jdbcType=CHAR}
       AND CHANNEL_CODE = #{channelCode,jdbcType=CHAR}

UPDATE TASK_QUERY_SETTING
		   SET UPD_TIME = SYSDATE,
		       UPD_USER = #{updateUser,jdbcType=VARCHAR},
		       MAX_COUNT = #{maxCount,jdbcType=INTEGER},
		       START_DATE = TO_DATE(#{startTime}||'00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 
		       END_DATE = TO_DATE(#{endTime}||'23:59:59', 'YYYY-MM-DD HH24:MI:SS') 
		 WHERE TASK_TYPE = #{taskType,jdbcType=CHAR}

INSERT INTO CONF_QUERY_COUNT (
			UUID,CRT_TIME,CRT_USER,SOURCE_CODE,CHANNEL_CODE,MAX_COUNT,START_DATE,END_DATE
		) VALUES (
			SYS_GUID(),SYSDATE,'liuwei',
			'001700',
			'00',
			1000000,
			SYSDATE,
			SYSDATE
		)
 delete from CONF_QUERY_COUNT where SOURCE_CODE ='001700';
INSERT INTO CONF_QUERY_COUNT (
			UUID,CRT_TIME,CRT_USER,SOURCE_CODE,CHANNEL_CODE,MAX_COUNT,START_DATE,END_DATE
		) VALUES (
			SYS_GUID(),SYSDATE,#{createUser,jdbcType=VARCHAR},
			#{sourceCode,jdbcType=CHAR},
			#{channelCode,jdbcType=VARCHAR},
			#{maxCount,jdbcType=INTEGER},
			TO_DATE(#{startTime}||'00:00:00','YYYY-MM-DD HH24:MI:SS'),
			TO_DATE(#{endTime}||'23:59:59','YYYY-MM-DD HH24:MI:SS')
		)
    INSERT INTO TASK_QUERY_SETTING (
			UUID,CRT_TIME,CRT_USER,SYS_ID,TASK_TYPE,MAX_COUNT,START_TIME,END_TIME
		) VALUES (
			SYS_GUID(),SYSDATE,#{createUser,jdbcType=VARCHAR},
			#{sysId,jdbcType=CHAR},
			#{taskType,jdbcType=VARCHAR},
			#{maxCount,jdbcType=INTEGER},
			TO_DATE(#{startTime}||'00:00:00','YYYY-MM-DD HH24:MI:SS'),
			TO_DATE(#{endTime}||'23:59:59','YYYY-MM-DD HH24:MI:SS')
		)

SELECT SOURCE_CODE,CHANNEL_CODE,MAX_COUNT,START_DATE,END_DATE,TO_CHAR(START_DATE,'YYYY-MM-DD HH24:MI:SS') START_TIME_STRING,TO_CHAR(END_DATE,'YYYY-MM-DD HH24:MI:SS') END_TIME_STRING
       FROM CONF_QUERY_COUNT
       WHERE SOURCE_CODE =  '001500'
       AND CHANNEL_CODE = '00'
SELECT SOURCE_CODE,CHANNEL_CODE,MAX_COUNT,START_DATE,END_DATE,TO_CHAR(START_DATE,'YYYY-MM-DD HH24:MI:SS') START_TIME_STRING,TO_CHAR(END_DATE,'YYYY-MM-DD HH24:MI:SS') END_TIME_STRING
       FROM CONF_QUERY_COUNT
       WHERE SOURCE_CODE =  #{sourceCode,jdbcType=CHAR}
       AND CHANNEL_CODE = #{channelCode,jdbcType=CHAR}
delete from BAIRONG_ALS_BASE;
delete from BAIRONG_ALS_D7;
delete from BAIRONG_ALS_D15;
delete from BAIRONG_ALS_M1;
delete from BAIRONG_ALS_M3;
delete from BAIRONG_ALS_M6;
delete from BAIRONG_ALS_M12;
delete from BAIRONG_ALS_FST;
delete from BAIRONG_ALS_LST;
delete from BAIRONG_ALS_TRN_REQUEST;
delete from BAIRONG_ALS_MSG_RESPONSE;
commit;
select count(*) from BAIRONG_ALS_BASE;
select * from BAIRONG_ALS_BASE where 1=1 order by crt_time desc;
select * from BAIRONG_ALS_BASE ;
select * from BAIRONG_ALS_D7;
select * from BAIRONG_ALS_D15;
select * from BAIRONG_ALS_M1;
select * from BAIRONG_ALS_M3;
select * from BAIRONG_ALS_M6;
select * from BAIRONG_ALS_M12;
select * from BAIRONG_ALS_FST;
select * from BAIRONG_ALS_LST;
     -- Add comments to the table 
comment on table BAIRONG_ALS_LST
  is '百融时间最近表';
select * from BAIRONG_ALS_TRN_REQUEST ;
select * from BAIRONG_ALS_MSG_RESPONSE order by crt_time desc;
select * from BAIRONG_MSG_RESPONSE  order by crt_time desc;
select count(*) from BAIRONG_ALS_MSG_RESPONSE;
select * from BAIRONG_ALS_MSG_RESPONSE where crt_user='CAMS'  order by crt_time desc;
----------------------------------------------------------------------------百融-----------------------------------------------------------
select count(*) from BAIRONG_ALS_TRN_REQUEST where crt_user = 'CAMS' and crt_time between sysdate-1   and  sysdate;
select count(*) from BAIRONG_ALS_BASE where crt_user = 'CAMS' and crt_time between sysdate-1   and  sysdate;
select count(*) from bairong_als_msg_response where crt_user = 'CAMS' and crt_time between sysdate-1   and  sysdate;
bairong_als_trn_batch_detail

 alter table BAIRONG_ALS_TRN_REQUEST modify(CERT_NO        VARCHAR2(32));
 alter table BAIRONG_ALS_TRN_REQUEST modify(MOBILE        VARCHAR2(32));
 alter table BAIRONG_ALS_TRN_BATCH_DETAIL modify(CERT_NO        VARCHAR2(32));
 alter table BAIRONG_ALS_TRN_BATCH_DETAIL modify(MOBILE        VARCHAR2(32));
  alter table BAIRONG_ALS_BASE modify(CERT_NO        VARCHAR2(32));
 alter table BAIRONG_ALS_BASE modify(MOBILE        VARCHAR2(32));


64 1044753 1350049
select count(*) from BAIRONG_ALS_TRN_BATCH_DETAIL where query_status = '0';
select count(*) from BAIRONG_ALS_TRN_BATCH_DETAIL where query_status = '1';
select count(*) from BAIRONG_ALS_TRN_BATCH_DETAIL where query_status = '9';

select * from BAIRONG_ALS_BASE  where   crt_time between sysdate-1   and  sysdate order by crt_time desc;
select * from BAIRONG_ALS_D7 where   crt_time between sysdate-1   and  sysdate order by crt_time desc;
select * from BAIRONG_ALS_D15 where   crt_time between sysdate-1   and  sysdate order by crt_time desc;
select * from BAIRONG_ALS_M1 where   crt_time between sysdate-1   and  sysdate order by crt_time desc;
select * from BAIRONG_ALS_M3 where   crt_time between sysdate-1   and  sysdate order by crt_time desc;
select * from BAIRONG_ALS_M6 where   crt_time between sysdate-1   and  sysdate order by crt_time desc;
select * from BAIRONG_ALS_M12 where   crt_time between sysdate-1   and  sysdate order by crt_time desc;
select * from BAIRONG_ALS_FST where   crt_time between sysdate-1   and  sysdate order by crt_time desc;
select * from BAIRONG_ALS_LST where   crt_time between sysdate-1   and  sysdate order by crt_time desc;
select * from BAIRONG_ALS_MSG_RESPONSE where   crt_time between sysdate-1   and  sysdate order by crt_time desc;

select * from sys_User for update;

update BAIRONG_ALS_TRN_BATCH_DETAIL set query_status = '8' where query_status = '0';

update BAIRONG_ALS_TRN_BATCH_DETAIL set query_status = '9' where query_status = '0';

select count(*) from BAIRONG_ALS_TRN_REQUEST where   crt_time between sysdate-1   and  sysdate order by crt_time desc;

select * from BAIRONG_ALS_TRN_REQUEST where   crt_time between sysdate-1   and  sysdate order by crt_time desc;
select * from BAIRONG_ALS_BASE where crt_user = 'CAMS' and crt_time between sysdate-1   and  sysdate order by crt_time desc;



select  count(*) from BAIRONG_ALS_TRN_REQUEST where   crt_time between sysdate-1   and  sysdate order by crt_time desc;
select  count(*) from BAIRONG_ALS_BASE where crt_time between sysdate-1   and  sysdate order by crt_time desc;
select  count(*) from bairong_als_msg_response where crt_time between sysdate-1   and  sysdate order by crt_time desc;
69 3  68
135 69 134


select * from BAIRONG_ALS_TRN_BATCH_DETAIL order by crt_time desc;
select * from BAIRONG_ALS_TRN_REQUEST order by crt_time desc;
select * from  BAIRONG_ALS_BASE order by crt_time desc;

select * from BAIRONG_ALS_TRN_BATCH_DETAIL order by crt_time desc;
select * from BAIRONG_ALS_TRN_REQUEST order by crt_time desc;
select * from  BAIRONG_ALS_BASE order by crt_time desc;

select * from sys_user for update ;

select count(*) from BAIRONG_ALS_TRN_BATCH_DETAIL order by crt_time desc;
select count(*) from BAIRONG_ALS_TRN_REQUEST order by crt_time desc;
select count(*) from  BAIRONG_ALS_BASE order by crt_time desc;

EA009F666EA15E48016EA15E48530000
select * from BAIRONG_ALS_TRN_BATCH_DETAIL where query_status = '0';

select count(*) from BAIRONG_ALS_TRN_BATCH_DETAIL where query_status = '0';
select count(*) from BAIRONG_ALS_TRN_BATCH_DETAIL where query_status = '9';
update BAIRONG_ALS_TRN_BATCH_DETAIL set query_status = '9' where query_status = '0';

select * from BAIRONG_ALS_TRN_REQUEST order by crt_time desc;
select* from BAIRONG_ALS_BASE order by crt_time desc;
select * from sys_user for update;
select count(*) from BAIRONG_ALS_MSG_RESPONSE;

select * from BAIRONG_ALS_TRN_BATCH_DETAIL ;

select count(*) from BAIRONG_ALS_TRN_BATCH_DETAIL ;
select count(*) from BAIRONG_ALS_TRN_BATCH_DETAIL where query_status = '0';
select count(*) from BAIRONG_ALS_TRN_BATCH_DETAIL where query_status = '1';
select * from BAIRONG_ALS_TRN_BATCH_DETAIL order by crt_time desc;
select count(*) from BAIRONG_ALS_BASE;
select count(1) from BAIRONG_ALS_TRN_REQUEST;
select count(1) from BAIRONG_ALS_TRN_REQUEST  where request_channel = '01';
select * from BAIRONG_ALS_TRN_BATCH_DETAIL where query_status = '0';
update BAIRONG_ALS_TRN_BATCH_DETAIL set query_status = '1' where query_status = '2';
select count(*) from BAIRONG_ALS_BASE;
select * from BAIRONG_ALS_TRN_REQUEST where request_channel = 'PLAZE';
select * from BAIRONG_ALS_TRN_REQUEST where request_channel = '01';
update  BAIRONG_ALS_TRN_REQUEST set  request_channel = '01' where  request_channel = 'PLAZE' ;

SELECT UUID, CRT_TIME, CRT_USER, TRN_ID, MESSAGE_BODY
  FROM BAIRONG_ALS_MSG_RESPONSE D
 WHERE EXISTS (SELECT 1
          FROM BAIRONG_ALS_BASE S
         WHERE S.TRN_ID = D.TRN_ID
           AND CERT_NO = '1'
           AND MOBILE = '18612995529'
           AND NAME = '18612995529')
-----------------------------------------------PAD人像---------------------------------------------
select * from nciic_info;
select * from nciic_xp_info for update;
select * from nciic_xp_request;
select * from nciic_xp_response;
select * from  NCIIC_POLICE_QUERY_LOG;

select * from unicom_phone_trn_request  where   (CRT_TIME BETWEEN	sysdate -1 AND sysdate) order by crt_time desc;
select * from unicom_phone_msg_response  where   (CRT_TIME BETWEEN	sysdate -1 AND sysdate) order by crt_time desc;
select * from unicom_phone_data  where   (CRT_TIME BETWEEN	sysdate -1 AND sysdate) order by crt_time desc;
12 6 6 
select * from PHONE_TRN_SINGLE for update;
select * from PHONE_TRN_BATCH_DETAIL for update;
  SELECT '00110010' TASK_TYPE, (SELECT  COUNT(*) FROM unicom_phone_data WHERE TRN_ID IN (
		     SELECT TRN_ID FROM  unicom_phone_trn_request WHERE REQUEST_CHANNEL = 'CAMS'
         )) TASK_COUNT
		     FROM DUAL where 1 =1
         
          SELECT '00110010' TASK_TYPE, (SELECT  COUNT(*) FROM unicom_phone_data WHERE TRN_ID IN (
		     SELECT TRN_ID FROM  unicom_phone_trn_request WHERE REQUEST_CHANNEL = 'WANGSHEN'
         )) TASK_COUNT
		     FROM DUAL where 1 =1
         
select * from unicom_online_trn_request;
select * from unicom_online_data;
 SELECT '00130010' TASK_TYPE, (SELECT  COUNT(*) FROM unicom_online_data WHERE TRN_ID IN (
		     SELECT TRN_ID FROM  unicom_online_trn_request WHERE REQUEST_CHANNEL = 'CAMS'
         )) TASK_COUNT
		     FROM DUAL where 1 =1
         
select * from SIMPOLICE_TRN_SINGLE order by crt_time desc;
select * from SIMPOLICE_TRN_BATCH order by crt_time desc;
select * from SIMPOLICE_TRN_BATCH_FILE order by crt_time desc;
select * from SIMPOLICE_TRN_BATCH_DETAIL order by crt_time desc;
select * from NCIIC_INFO order by crt_time desc;
select * from SIMPOLICE_QUERY_LOG order by crt_time desc;

select * from NCIIC_INFO;

select  count(*)  from SIMPOLICE_TRN_SINGLE;
select  count(*)  from SIMPOLICE_TRN_BATCH;
select  count(*)  from SIMPOLICE_TRN_BATCH_FILE;
select  count(*)  from SIMPOLICE_TRN_BATCH_DETAIL;
select  count(*)  from SIMPOLICE_QUERY_LOG;
select  count(*)  from NCIIC_INFO;


select * from FILE_SERVER_INFO;
select count(*) from FILE_SERVER_INFO;
select * from TRN_QUERY_REVIEW;
select count(*) from TRN_QUERY_REVIEW;

select  *  from SIMPOLICE_TRN_SINGLE;
select  *  from SIMPOLICE_TRN_BATCH;
select  *  from SIMPOLICE_TRN_BATCH_FILE;
select  *  from SIMPOLICE_TRN_BATCH_DETAIL;
select  *  from SIMPOLICE_QUERY_LOG;
select  *  from NCIIC_INFO;

select count(*) from nciic_police_single;
select * from nciic_police_single order by crt_time desc;
select * from nciic_police_batch;
select * from nciic_police_batch_file;
select * from nciic_police_batch_detail ;
select * from nciic_police_batch_detail for update;
select * from nciic_police_batch_detail order by crt_time desc;
select * from nciic_police_query_log order by crt_time desc;
delete from nciic_police_query_log where uuid = '8F20F6E3D3A206DFE0531907806A7E7F';
select * from nciic_info for update ;
select count(*) from nciic_info;
select count(*) from nciic_police_batch_detail;
select count(*) from nciic_police_single;
select SYS_GUID() from dual;

select * from NCIIC_TRN_REQUEST where trn_id = '8F1F48D47BA66B9EE0531907806A26DE';
select * from nciic_info where trn_id = '8F1F48D47BA66B9EE0531907806A26DE';
select count(*) from nciic_info;
	SELECT TRN_ID,TO_CHAR(CRT_TIME, 'YYYY-MM-DD HH24:MI:SS') CRT_TIME,GMSFHM,RESULT_GMSFHM,XM,RESULT_XM,XP 
		  FROM NCIIC_INFO
		 WHERE TRN_ID = '8F56078A4D915EF3E0531907806AD459'
select * from nciic_police_single where cert_no = '2341234123asds';

select * from nciic_info where  trn_id in (
select trn_id from nciic_police_single where cert_no = '2341234123asds'
)

				SELECT TRN_ID,TO_CHAR(CRT_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CRT_TIME,GMSFHM,RESULT_GMSFHM,XM,RESULT_XM,XP
				  FROM NCIIC_INFO where  TRN_ID IN (
				  select TRN_ID from nciic_police_single 
	        where 
						 cert_no = '410221199505293456'
             
           union 
         select TRN_ID from nciic_police_batch_detail 
	       where 
						 cert_no = '410221199505293456'
             and query_status = '1'
				  )

		select sysdate from dual;
    UPDATE NCIIC_POLICE_BATCH_DETAIL
		set
		         QUERY_STATUS = '0'
		         , QUERY_TIME = sysdate
		  
			 WHERE UUID = '8EF691705BCF75EBE0531907806AA40F'
      
      select * from TASK_QUERY_SETTING;
      update TASK_QUERY_SETTING set end_time =(sysdate+100);
      update TASK_QUERY_SETTING set max_count =1005156;
      select * from CONF_QUERY_COUNT;
 delete  from CONF_QUERY_COUNT;    
 select * from CONF_QUERY_COUNT;
 delete from conf_query_count where source_code = '000700';
 select * from PBOC_TRN_QUERY_REVIEW;
 select * from BANK_REPORT_MARK_INFO;
 select * from FILE_SERVER_INFO;
 select * from TRN_QUERY_REVIEW;
 15303160584     8E40E30A0CA43DEDE0538E06806A0150 106.128.31.134
select * from sys_user for update;
 
 select * from sys_user for update;
 insert into sys_user value(
 select 
 sys_guid(),
 	CRT_TIME                  ,
  CRT_USER                  ,
  USER_ID                   ,
  USER_ACCT                 ,
  USER_PASS                 ,
  LAST_LOGIN_TIME           ,
  STATUS                    ,
  FIRST_LOGIN               ,
  PASS_EXPIRE_TIME          ,
  IP                        ,
  STAFF_ID                  ,
  STAFF_NAME                ,
  CERT_TYPE                 ,
  CERT_NO                   ,
  MOBILE                    ,
  DEPARTMENT                ,
  TEAM                      ,
  POST                      ,
  PBOC_AUTH                 ,
  PBOC_AUTH_QUERY           ,
  PBOC_AUTH_QUERY_TIME      ,
  PBOC_DAY_QUERY_MAX_COUNT  ,
  PBOC_DAY_SEARCH_MAX_COUNT ,
  UPD_TIME                  ,
  UPD_USER                  
 
  from sys_user where user_acct = 'liuwei'
 );
 
 select sys_guid() from dual;
 
 select * from PBOC_TRN_SINGLE_REVIEW order by crt_time desc for update;
 select * from sys_user;
 select * from PBOC_BATCH_RESPONSE;
 select * from PBOC_TRN_BATCH_REVIEW;
 select * from PBOC_TRN_SINGLE_REVIEW where review_status ='3';
  select count(*) from PBOC_TRN_SINGLE_REVIEW 
  select * from FILE_SERVER_INFO where uuid = '8FFB2CA23CD05D81E0531907806A34AC' order by crt_time desc;
   select * from FILE_SERVER_INFO where uuid = '8FA6AAF350390F15E0531907806A1938' order by crt_time desc;
  select * from BANK_TASK_CALL_PLAZE order by crt_time desc;
  delete from BANK_TASK_CALL_PLAZE where  lst_opt_user = 'liuwei';
    select count(*) from BANK_TASK_CALL_PLAZE;
    select count(*)from PBOC_TRN_BATCH_REVIEW;
      select * from PBOC_TRN_BATCH_REVIEW order by crt_time desc;
    select * from SYS_USER  for update
  SELECT  *
		  FROM FILE_SERVER_INFO  order by crt_time desc; 
		 WHERE SOURCE_ID ='201812151019034872'
   select source_name, source_path from FILE_SERVER_INFO where uuid = '8FAA41116624180CE0531907806A32CC' ; for update order by crt_time desc; 
   
  select count(*) from FILE_SERVER_INFO;
  select count(*) from PBOC_BATCH_RESPONSE;
 select count(*) from PBOC_TRN_BATCH_REVIEW;
 select count(*) from PBOC_TRN_SINGLE_REVIEW;
 PBOC_TRN_SINGLE_REVIEW PBOC_TRN_SINGLE_REVIEW 
 select * from  TRN_BATCH_REVIEW;
 select * from TRN_QUERY_REVIEW
 select * from  TASK_QUERY_SETTING;
 select * from SYS_USER where user_acct = 'liuwei';
 update SYS_USER set  pboc_auth_query_time = '1:00-23:00' where  user_acct = 'liuwei';
 
  select * from PBOC_TRN_SINGLE_REVIEW where cert_no = '12341234' and name = '1234';
   select * from PBOC_TRN_SINGLE_REVIEW  where trn_id = '8F951D68452966F4E0531907806A1A5B';
  select * from BANK_TASK_CALL_PLAZE order by crt_time desc;
  select *  from PBOC_TRN_BATCH_REVIEW;
   select * from FILE_SERVER_INFO order by crt_time desc;
  select * from FILE_SERVER_INFO a where a.source_path='group1/M00/00/00/aoAH0FwUYuyAMqc_AAcP63W9WbM764.pdf';
  select * from SYS_LOG_QUERY  order by crt_time desc; 
  select * from SYS_CONFIG_SETTING;
  update SYS_CONFIG_SETTING set max_count = 100000;
  当天累计查询达到阈值
  select * from SYS_LOG_QUERY where ip = '106.128.31.134' order by crt_time desc; 
    select * from SYS_LOG_QUERY where staff_name = '李志国' order by crt_time desc; 
  select * from SYS_USER;
  select  * from SYS_USER where staff_name = '李志国' for update;
  delete from SYS_LOG_QUERY where ip = '106.128.31.134';
  18645168202
  admin:15303160584 106.128.31.134
  
      alter table bairong_als_trn_batch_detail add( sequen         number(12));
      select * from BAIRONG_ALS_TRN_BATCH_DETAIL order by crt_time desc;
      
      alter table bairong_als_trn_batch_detail modify(MOBILE        VARCHAR2(11))
        alter table bairong_als_trn_batch_detail modify( CERT_NO       VARCHAR2(18))
        alter table bairong_als_trn_batch_detail modify( CARD_ACCOUNT  VARCHAR2(12))
        update bairong_als_trn_batch_detail set CARD_ACCOUNT = substrb(CARD_ACCOUNT,12) where length(card_account)>12;
      alter table bairong_als_trn_batch_detail drop column sequen ;
        
        select * from bairong_als_base;
        alter table bairong_als_base add( sequen         number(12));
        comment on column bairong_als_base.sequen
  is '批量查询序号';
    select * from bairong_als_trn_request;
        alter table bairong_als_trn_request add( sequen         number(12));
        comment on column bairong_als_trn_request.sequen
  is '批量查询序号';
  
  
  
      comment on column BAIRONG_ALS_TRN_BATCH_DETAIL.CARD_ACCOUNT
  is '批量发卡账号';
  comment on column BAIRONG_ALS_TRN_BATCH_DETAIL.sequen
  is '批量查询序号';
      
      select * from bairong_als_base;
      
  select  * from SYS_USER  where user_acct = 'liuwei' for update ;
  
  select * from  RPT_QUERY_COUNT_DAY;
  select * from  CONF_QUERY_COUNT for update;
  select * from  SYS_DATASOURCE;
  select * from  SYS_CHANNEL;
 
  select * from  RPT_QUERY_COUNT_DAY where source_code = '000100' and channel_code = '10';
  
    rpt_query_count_day1
    
  select distinct source_code from RPT_QUERY_COUNT_DAY;
  
  select * from BANK_REPORT_MARK_INFO;
  
  select * from sys_user;
  	SELECT TRN_ID,TO_CHAR(CRT_TIME, 'YYYY-MM-DD HH24:MI:SS') CRT_TIME,GMSFHM,RESULT_GMSFHM,XM,RESULT_XM,XP 
		  FROM NCIIC_INFO
		 WHERE TRN_ID = '8F56078A4D915EF3E0531907806AD459'
    	SELECT * FROM NCIIC_INFO order by crt_time desc WHERE TRN_ID = '8F56078A4D915EF3E0531907806AD459';
     select * from nciic_police_batch_detail WHERE TRN_ID = '8F56078A4D915EF3E0531907806AD459';
     select * from nciic_trn_request t  order by crt_time desc 
     select * from NCIIC_MSG_RESPONSE t  order by crt_time desc 

	SELECT * FROM NCIIC_INFO  for update
     select count(*) from nciic_trn_request;
     select count(*) from NCIIC_MSG_RESPONSE;
     select * from nciic_police_single where trn_id = '907244E194DB75CAE0538E06806A9605';
     select * from nciic_police_single order by crt_time desc;
     select * from nciic_police_batch;
     select * from nciic_police_batch_file;
     select * from nciic_police_batch_detail ;
     select * from nciic_police_query_log ;
     select  *  from NCIIC_INFO where trn_id = '8FE1A92FC5A47E04E0531907806AFDA1';
     select count(*) from nciic_police_batch_detail;
     
     select * from BANK_REPORT_MARK_INFO 622926198501293785;
     select * from TASK_QUERY_SETTING;
     select * from  CONF_QUERY_COUNT for update;
   select SYSDATE from dual;
   select * from sys_dict;
       select  count(*) from NCIIC_INFO;
        select  count(*) from NCIIC_INFO group by trn_id  having count(*) >1;
          select  trn_id from NCIIC_INFO group by trn_id  having count(*) >1;
         select * from SYS_CONFIG_SETTING;
         select * from SYS_LOG_QUERY;
            select * from sys_dict;
            select * from sys_dict where dict_name  like '%查询动作%';
           select * from sys_dict where dict_name 
            select * from sys_dict where dict_name  like '%ACHIEVEMENT_BANK%';
           select * from sys_dict where dict_name 
           select * from     
  
  
		SELECT '00010000' TASK_TYPE, COUNT(1) TASK_COUNT
		  FROM PBOC_TRN_SINGLE_REVIEW
		 WHERE REVIEW_STATUS = '3'
		   AND QUERY_FROM = '0'
		  
		UNION
		SELECT '00010001' TASK_TYPE, COUNT(1) TASK_COUNT
		  FROM PBOC_TRN_SINGLE_REVIEW
		 WHERE REVIEW_STATUS = '3'
		   AND QUERY_FROM = '1'
		   
		UNION
		SELECT '00010010' TASK_TYPE, COUNT(1) TASK_COUNT
		  FROM BANK_REPORT_MARK_INFO
		 WHERE SOURCE = '1'
	
  select  *  FROM PBOC_TRN_SINGLE_REVIEW
		 WHERE REVIEW_STATUS = '3'
		   AND QUERY_FROM = '1'
		    select * from  RPT_QUERY_COUNT_DAY where source_code = '000100' and channel_code = '10';
        
    select * from PBOC_TRN_SINGLE_REVIEW where query_from = '0' order by crt_time desc;
    select * from PBOC_TRN_SINGLE_REVIEW where query_from = '1' order by crt_time desc;
    
    select * from PBOC_TRN_SINGLE_REVIEW where query_from = '0' and review_status = '3' order by crt_time desc;
    select * from PBOC_TRN_SINGLE_REVIEW where query_from = '1' and review_status = '3' order by crt_time desc;
    select * from BANK_REPORT_MARK_INFO;
    
    select a.old_data + b.d_data - c.max_count
  from (select sum(r.query_count) old_data
          from rpt_query_count_day r
         where r.source_code = '000100'
           and r.channel_code = '10'
           and r.query_date > (select start_date
                                 from CONF_QUERY_COUNT c
                                where c.source_code = '000100'
                                  and c.channel_code = '10')
           and r.query_date < trunc(sysdate-1)) a,
       (select count(1) d_data
          from  BANK_REPORT_MARK_INFO a
         where a.crt_time >= trunc(sysdate - 1)
           and SOURCE = '1') b,
       (select c.max_count
          from CONF_QUERY_COUNT c
         where c.source_code = '000100'
           and c.channel_code = '10') c ;
           
             select a.old_data + b.d_data
  from (select sum(r.query_count) old_data
          from rpt_query_count_day r
         where r.source_code = '000100'
           and r.channel_code = '10'
           and r.query_date > (select start_date
                                 from CONF_QUERY_COUNT c
                                where c.source_code = '000100'
                                  and c.channel_code = '10')
           and r.query_date < trunc(sysdate-1)) a,
       (select count(1) d_data
          from  BANK_REPORT_MARK_INFO a
         where a.crt_time >= trunc(sysdate - 1)
           and SOURCE = '1') b
           
     comment on column BANK_REPORT_MARK_INFO.SOURCE
  is '0 单条 1 信审 2 批量';
           
    select sysdate-1 from dual;
    
    SELECT '00010000' TASK_TYPE, COUNT(1) TASK_COUNT
		  FROM BANK_REPORT_MARK_INFO
		 WHERE SOURCE = '0'
     select * from sys_user for update;
     update sys_user set status = '1' ;
     select sysdate from dual;
     select * from NCIIC_POLICE_QUERY_LOG;
     
       SELECT 
			 COUNT(1) 
		  FROM BAIRONG_ALS_TRN_BATCH_DETAIL
		  WHERE  QUERY_STATUS = '0' 
      
      select * from SYS_DICT where dict_code = 'CERT_TYPE';
       select * from SYS_DICT where PARENT_ID = '2c0189c20ab640f9894004bb1f237a7d';
             select * from SYS_DICT where dict_code = 'DUTY_BANK';
       select * from SYS_DICT where PARENT_ID = '15b843f35fee41dba232d1507b63aad0';
          select * from SYS_DICT where dict_code = 'GMJJ_BANK';
       select * from SYS_DICT where PARENT_ID = 'e938b6f814e44dfbb36b093c8925681a';
       
       select * from bank_report_mark_info;
       
       	SELECT
		BP.COMPANY,BP.UNIT_CHARACTER,BP.COMP_ADDR,BP.UNIT_PHONE,
		BP.JOB,BP.INDUSTRY_TYPE,BP.OCCUPATION_CODE,BP.TECHNICAL_TITLE,
		BP.CUR_COMP_WORK_STAT_Y,BP.INFO_UPD_TIME
		FROM BANK_REPORT_MARK_INFO B
		LEFT JOIN
		BANK_PROFESSION_INFO BP ON B.UNIQUE_RELID = BP.UNIQUE_RELID
		WHERE B.UNIQUE_RELID = '9B8855A1ACD4D700E0538E06806A2497'
         	SELECT
		BP.COMPANY,BP.UNIT_CHARACTER,BP.COMP_ADDR,BP.UNIT_PHONE,
		BP.JOB,BP.INDUSTRY_TYPE,BP.OCCUPATION_CODE,BP.TECHNICAL_TITLE,
		BP.CUR_COMP_WORK_STAT_Y,BP.INFO_UPD_TIME
		FROM BANK_REPORT_MARK_INFO B
		LEFT JOIN
		BANK_PROFESSION_INFO BP ON B.UNIQUE_RELID = BP.UNIQUE_RELID
		WHERE B.UNIQUE_RELID = '7B9CFAAD57A268E4E0531907806A9666'
    
       select * from rpt_query_count_day;
       
     select NVL(( select sum(r.query_count) old_data
          from rpt_query_count_day r
         where r.source_code =  '001500'
           and r.channel_code = '01'),0) from dual;
       
         select a.old_data + b.d_data
  from (select sum(r.query_count) old_data
          from rpt_query_count_day r
         where r.source_code =  '000100'
           and r.channel_code = '01'
           and r.query_date >= (select start_date
                                 from CONF_QUERY_COUNT c
                                where c.source_code =  '000100'
                                  and c.channel_code ='01')
           and r.query_date <= trunc(sysdate-1)) a,
       (select count(1) d_data
          from  BANK_REPORT_MARK_INFO a
         where a.crt_time >=trunc(sysdate - 1)
           and SOURCE ='000100') b
           
  select * from NCIIC_POLICE_QUERY_LOG for update;
  select * from nciic_info;
    select count(*)  from nciic_info;
  select * from mulbor_base;
  select * from  SYS_QUERY_AUTHORIZE;
  SELECT T1.QUERY_USER USERNAME,T1.QUERY_PASS PASSWORD,T2.DICT_CODE SOURCE
  select crt_time，USERNAME，PASSWORD，SOURCE from (
		SELECT T1.crt_time crt_time, T1.QUERY_USER USERNAME,T1.QUERY_PASS PASSWORD,T2.DICT_CODE SOURCE     FROM SYS_QUERY_AUTHORIZE T1, SYS_DICT T2    WHERE T1.QUERY_SOURCE = T2.DICT_CODE      AND T2.PARENT_ID = '7EEE9DF9386A6A0FE0531907806A091F'      AND T2.DICT_CODE = '01'                           order by T1.crt_time desc ) where  rownum<2   
    select * from BAIRONG_ALS_BATCH;
     select * from BAIRONG_ALS_TRN_BATCH
     select * from SYS_QUERY_AUTHORIZE;
     select * from BAIRONG_ALS_LST;
   select count(*) from MULBOR_BASE;
     select * from MULBOR_BASE;
     BAIRONG_ALS_TRN_BATCH_DETAIL
    select   ((SELECT 
			 COUNT(1) 
		  FROM MULBOR_TRN_BATCH_DETAIL
		  WHERE  QUERY_STATUS = '0'   OR QUERY_STATUS = '1' ) + 2) from dual
PROC_FN_QUERY_COUNT_BRDTJD1
select * from ID5_INFO where trn_id = '402881956EF2E744016EFCF1EA840219';
select * from ID5_INFO order by crt_time desc;
select count(*)   from ID5_INFO;
select * from id5_info order by crt_time desc;
select * from ID5_TRN_REQUEST  order by crt_time desc;
select count(*) from id5_info;
select count(*) from ID5_EDU_TRN_SINGLE ;
select * from ID5_EDU_TRN_SINGLE where trn_id = 'EA009F666CF10A35016CF10E79A20004';

SELECT
		USERNAME,IDENTITYCARD,GRADUATE,EDUCATIONDEGREE,ENROLDATE,SPECIALITYNAME,
		GRADUATETIME,STUDYRESULT,STUDYSTYLE,SCHOOLTYPE,PHOTO,HISNAME,SCHOOLCITY,SCHOOLTRADE,
		ORGANIZATION,SCHOOLNATURE,SCHOOLCATEGORY,LEVEL_,EDUCATIONAPPROACH,CREATEDATE,
		CREATEYEAR,ACADEMICIANNUM,BSHLDZNUM,BSDNUM,SSDNUM,ZDXKNUM,LKLQPC,WKLQPC,DSTUDYSTYLE,
		IDCORICT2,BIRTHDAY2,SEX2,NO,CRT_TIME,CRT_USER,QUERY_STATUS,QUERY_RESULT,TRN_ID
		FROM id5_info
		WHERE TRN_ID = 'EA009F666CF10A35016CF10E79A20004';
    
    select TRN_ID from (
		SELECT *  FROM ID5_INFO WHERE
		IDENTITYCARD='13010319820210188X'
		AND CRT_TIME
		BETWEEN sysdate-1
		AND sysdate order by crt_time desc
		) where rownum <2
    
     SELECT 
			 COUNT(1) 
		   FROM MULBOR_BASE
			WHERE crt_user = 'BATCH'
      
      select * from sys_user for update;
      select * from BANK_REPORT_MARK_INFO order by crt_time ;
      select * from BANK_CUR_QUERY_REQUEST;
        select * from sys_dict
        where dict_id in
          (  select parent_id from sys_dict where dict_name  like '%查询机构%');
    select * from sys_user for update;
   select * from BAIRONG_ALS_BASE where 1=1 order by crt_time desc;
   select * from unicom_online_data;
   
   ds_unicom_001300_count
ds_NCIIC_000201_count
select * from nciic_info where INSTR(ERROR_MESSAGE,'库中无此号')>0  ;
select * from nciic_info where trn_id = '8FE1A92FC7967E04E0531907806AFDA1' for update;
select * from unicom_online_data where 1=1 order by crt_time desc;8F557DEFD14C5313E0531907806AC1DF
   select * from NCIIC_XP_REQUEST order by crt_time desc;
   select * from unicom_online_data order by crt_time desc;
   select * from sys_user where user_pass = '91B4D142823F7D20C5F08DF69122DE43F35F057A988D9619F6D3138485C9A203'  for update ;
   select * from ID5_EDU_QUERY_LOG for update ;where  cert_no = null for update; 
   select * from sys_user;
   select * from NCIIC_INFO;
   
  	SELECT 
		UUID,
		CRT_TIME,
		CRT_USER,
		TRN_ID,
		MESSAGE_BODY
		FROM
		BAIRONG_ALS_MSG_RESPONSE
		WHERE TRN_ID IN
		(
		SELECT TRN_ID FROM
		BAIRONG_ALS_BASE WHERE(  CRT_TIME BETWEEN
		sysdate -8 AND sysdate)
		) order by crt_time desc;
    select * from UNICOM_ONLINE_DATA  where   (CRT_TIME BETWEEN	sysdate -1 AND sysdate) order by crt_time desc;
    select * from UNICOM_ONLINE_TRN_REQUEST  where   (CRT_TIME BETWEEN	sysdate -1 AND sysdate) order by crt_time desc;
    select * from UNICOM_ONLINE_MSG_RESPONSE  where   (CRT_TIME BETWEEN	sysdate -1 AND sysdate) order by crt_time desc;
   3 11 8
    alter table UNICOM_ONLINE_MSG_RESPONSE add( CERT_NO         VARCHAR2(18));
    alter table UNICOM_ONLINE_MSG_RESPONSE add( NAME            VARCHAR2(64));
    alter table UNICOM_ONLINE_MSG_RESPONSE add( MOBILE          VARCHAR2(16));
    comment on column UNICOM_ONLINE_MSG_RESPONSE.CERT_NO
      is '证件号码';
    comment on column UNICOM_ONLINE_MSG_RESPONSE.NAME
      is '姓名';
    comment on column UNICOM_ONLINE_MSG_RESPONSE.MOBILE
      is '手机号码';
      
      SELECT 	CERT_NO,NAME,MOBILE,CRT_USER, TRN_ID, MESSAGE_BODY
		  FROM UNICOM_ONLINE_MSG_RESPONSE D
      WHERE CERT_NO = #{certNo}
		           AND MOBILE = #{mobile}
		           AND NAME = #{name}
      ORDER BY D.CRT_TIME DESC
      
      SELECT 	CERT_NO,NAME,MOBILE,CRT_USER, TRN_ID, MESSAGE_BODY
		  FROM UNICOM_ONLINE_MSG_RESPONSE D
      WHERE CERT_NO = ''
		           AND MOBILE = ''
		           AND NAME =''
               AND (CRT_TIME BETWEEN	sysdate -1 AND sysdate)
      ORDER BY D.CRT_TIME DESC
      
     SELECT 	CERT_NO,NAME,MOBILE,CRT_USER, TRN_ID, MESSAGE_BODY
		  FROM UNICOM_ONLINE_MSG_RESPONSE D
      WHERE CERT_NO = ''
		           AND MOBILE = ''
		           AND NAME =''
               AND (CRT_TIME BETWEEN	sysdate -1 AND sysdate)
      ORDER BY D.CRT_TIME DESC
      
	SELECT
		CERT_NO,NAME,MOBILE,
		CRT_USER,
		TRN_ID,
		MESSAGE_BODY
		FROM
		BAIRONG_ALS_MSG_RESPONSE
		WHERE TRN_ID IN
		(
		SELECT TRN_ID FROM
		BAIRONG_ALS_BASE WHERE CERT_NO = #{certNo} AND
		mobile = #{mobile} AND
		name = #{name} AND (CRT_TIME BETWEEN
		sysdate -1 AND sysdate)
		)

    select * from UNICOM_ONLINE_TRN_REQUEST where   (CRT_TIME BETWEEN	sysdate -1 AND sysdate) order by crt_time desc;
    select * from UNICOM_ONLINE_DATA  where   (CRT_TIME BETWEEN	sysdate -1 AND sysdate)  order by crt_time desc;
    select * from UNICOM_ONLINE_MSG_RESPONSE  where   (CRT_TIME BETWEEN	sysdate -1 AND sysdate)  order by crt_time desc;
    22 16 15
    select count(*) as coun from UNICOM_ONLINE_TRN_REQUEST where   (CRT_TIME BETWEEN	sysdate -1 AND sysdate) 
    union 
    select count(*)  as coun from UNICOM_ONLINE_DATA  where   (CRT_TIME BETWEEN	sysdate -1 AND sysdate) 
    union
    select count(*)  as coun from UNICOM_ONLINE_MSG_RESPONSE  where   (CRT_TIME BETWEEN	sysdate -1 AND sysdate) ;
   19 9 9 
      select count(*) as coun from BAIRONG_ALS_TRN_REQUEST where   (CRT_TIME BETWEEN	sysdate -1 AND sysdate) 
    union 
    select count(*)  as coun from bairong_als_base  where   (CRT_TIME BETWEEN	sysdate -1 AND sysdate) 
    union
    select count(*)  as coun from BAIRONG_ALS_MSG_RESPONSE  where   (CRT_TIME BETWEEN	sysdate -1 AND sysdate) ;
   21 0 21
    select * from BAIRONG_ALS_TRN_REQUEST where   (CRT_TIME BETWEEN	sysdate -1 AND sysdate) order by crt_time desc;
    select * from bairong_als_base  where   (CRT_TIME BETWEEN	sysdate -1 AND sysdate)  order by crt_time desc;
    select * from BAIRONG_ALS_MSG_RESPONSE  where   (CRT_TIME BETWEEN	sysdate -1 AND sysdate)  order by crt_time desc;
    select * from bairong_als_trn_batch_detail where   (CRT_TIME BETWEEN	sysdate -1 AND sysdate)  order by crt_time desc;
      select count(*) from bairong_als_trn_batch_detail where   (CRT_TIME BETWEEN	sysdate -1 AND sysdate)  order by crt_time desc;
    select count(*) from bairong_als_trn_batch_detail;   
     select * from bairong_als_trn_batch_detail where trn_id = 'EA009F666CB2CDDD016CB2CE13760004' for update order by crt_time desc;
     select * from sys_user for update;
    10733
    select * from CONF_QUERY_COUNT;
    08162C523044DAC99F02AF3405B507375AA8A13AC181B0E20C7517BCE8A05D27
    SELECT T.CERT_NO,T.NAME,T.TRN_ID,T.MOBILE,T.CARD_ACCOUNT,
    21B68A7A99E3B4A8AF5365B9757F2D5E6A7CB7B9A9CE79AFC340DD5484F246C0
		TO_CHAR(T.CRT_TIME,
		'YYYY-MM-DD HH24:MI:SS') CRT_TIME,
		TO_CHAR(T.QUERY_TIME, 'YYYY-MM-DD
		HH24:MI:SS') QUERY_TIME,
		T.QUERY_STATUS
		FROM
		BAIRONG_ALS_TRN_BATCH_DETAIL T
		WHERE T.BATCH_FILE_ID='060f1b0e54594571aa5aef05a4bac4a9'
		ORDER BY
		T.CRT_TIME DESC
    F16B4154AA9062A7EB1F0028718EC1E88F7CDEDA1CE45331E3EF7CE16E8885A3
 
    select * from BANK_REPORT_MARK_INFO;
    select * from sys_user for update;
select * from CONF_QUERY_COUNT for update;
select * from SYS_DATASOURCE for update;
select * from SYS_CHANNEL;
select * from TASK_QUERY_SETTING;

select * from sys_user for update;

select * from SYS_LOG_QUERY order by crt_time desc;

  SELECT 
	  NVL(MAX(COUNT(CRT_TIME)),0)
	  FROM SYS_LOG_QUERY
	  WHERE
	  QUERY_ACTION='6'
	  AND QUERY_TYPE = '1'
	  AND CRT_USER = 'liuwei2'
	  AND
	  TO_DATE(TO_CHAR(CRT_TIME, 'YYYY-MM-DD'),'YYYY-MM-DD')  
	  BETWEEN 
	  TO_DATE(TO_CHAR(ADD_MONTHS(SYSDATE,-3), 'YYYY-MM-DD'),'YYYY-MM-DD')
	  AND
	  TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD'),'YYYY-MM-DD')
	  GROUP BY TO_CHAR(CRT_TIME,'YYYY-MM-DD')


select * from CONF_QUERY_COUNT ;
select * from SYS_DATASOURCE;
select * from SYS_CHANNEL;
select * from TASK_QUERY_SETTING;
ds_NCIIC_000201_count
    
select * from NCIIC_XP_INFO;
    
    DS_UNICOM_001300_COUNT
    ds_unicom_001101_count
    select  * from CONF_QUERY_COUNT;
    alter table UNICOM_ONLINE_MSG_RESPONSE drop (CERT_NO,NAME,MOBILE);
    
    
    select CRT_USER,TRN_ID,MESSAGE_BODY from (
    select b.* from unicom_online_trn_request a,UNICOM_ONLINE_MSG_RESPONSE b where a.trn_id=b.trn_id
    and a.cert_no='210108200001011188' and a.name='李嘉' and a.mobile='18618127065' AND (b.CRT_TIME BETWEEN
		sysdate -1 AND sysdate) order by a.crt_time desc ) c where rownum=1;
    
     select CRT_USER,TRN_ID,MESSAGE_BODY from (
    select b.* from unicom_online_trn_request a,UNICOM_ONLINE_MSG_RESPONSE b where a.trn_id=b.trn_id
    and a.cert_no='210108200001011188' and a.name='李嘉' and a.mobile='18618127065' order by a.crt_time desc ) c where rownum=1;
    
      --重新统计联通在网时长
  delete rpt_query_count_day  where source_code ='001101';     
 --单条
insert into rpt_query_count_day
  select sys_guid(), sysdate, '001101', '00', count(1), trunc(crt_time)
    from unicom_online_data a
   where a.crt_user = 'SINGLE'
   and crt_time<trunc(sysdate)
   group by trunc(crt_time);
--批量
insert into rpt_query_count_day
  select sys_guid(), sysdate, '001101', '01', count(1), trunc(crt_time)
    from unicom_online_data a
   where a.crt_user = 'BATCH'
   and crt_time<trunc(sysdate)
   group by trunc(crt_time);
   --审批
  insert into rpt_query_count_day
    select sys_guid(), sysdate, '001101', '10', count(1), trunc(crt_time)
      from unicom_online_data a
     where a.crt_user = 'CAMS'
     and crt_time<trunc(sysdate)
     group by trunc(crt_time);
     
     commit;

select * from rpt_query_count_day order by crt_time desc;
select * from sys_User for update;
--------------------------------------测试数量显示存储过程的函数----------------------------------------------------------
delete  from conf_query_count;
select * from conf_query_count for update;
truncate table conf_query_count ;
create table conf_query_count_copy as select * from conf_query_count;
insert into conf_query_count select * from conf_query_count_copy;
select * from SYS_RESOURCE where rsrc_name like '%人像%';
-----------------------------------1 人像比对----------------------------------------------------------
select * from nciic_xp_info order by crt_time desc;
select * from nciic_xp_request order by crt_time desc;
select * from nciic_xp_response order by crt_time desc;
--这些表的数量： 298 372 298 -----
select count(1) from nciic_xp_info order by crt_time desc;
select count(1) from nciic_xp_request order by crt_time desc;
select count(1) from nciic_xp_response order by crt_time desc;
-----------------------------------1 百融多头借贷----------------------------------------------------------
select  count(*) from BAIRONG_ALS_TRN_REQUEST where   crt_time between sysdate-1   and  sysdate order by crt_time desc;
select  count(*) from BAIRONG_ALS_BASE where crt_time between sysdate-1   and  sysdate order by crt_time desc;
select  count(*) from bairong_als_msg_response where crt_time between sysdate-1   and  sysdate order by crt_time desc;
----------------13 9 10---------------------------------
select  * from BAIRONG_ALS_TRN_REQUEST where   crt_time between sysdate-1   and  sysdate order by crt_time desc;
select  * from BAIRONG_ALS_BASE where crt_time between sysdate-1   and  sysdate order by crt_time desc;
select  * from bairong_als_msg_response where crt_time between sysdate-1   and  sysdate order by crt_time desc;
-----------------------------------1 同盾多头借贷----------------------------------------------------------
select count(*) from MULBOR_TRN_SINGLE where   crt_time between sysdate-1   and  sysdate order by crt_time desc;
select count(*) from MULBOR_BASE where   crt_time between sysdate-1   and  sysdate order by crt_time desc;
select count(*) from MULBOR_TRN_REQUEST where   crt_time between sysdate-1   and  sysdate order by crt_time desc;
select count(*) from MULBOR_MSG_RESPONSE where   crt_time between sysdate-1   and  sysdate order by crt_time desc;
select count(*)  from MULBOR_TRN_BATCH_DETAIL where   crt_time between sysdate-1   and  sysdate order by crt_time desc;
---------------------11 13 14-------------------------------------------------------------------------------------
select * from MULBOR_TRN_SINGLE where   crt_time between sysdate-1   and  sysdate order by crt_time desc;
select * from MULBOR_BASE where   crt_time between sysdate-1   and  sysdate order by crt_time desc;
select * from MULBOR_TRN_REQUEST where   crt_time between sysdate-1   and  sysdate order by crt_time desc;
select * from MULBOR_MSG_RESPONSE where   crt_time between sysdate-1   and  sysdate order by crt_time desc;

select * from MULBOR_GREY_LIST where   crt_time between sysdate-1   and  sysdate order by crt_time desc;
select * from MULBOR_DESCREDIT_COUNT where   crt_time between sysdate-1   and  sysdate order by crt_time desc;
select * from MULBOR_BLACK_LIST where   crt_time between sysdate-1   and  sysdate order by crt_time desc;
select * from MULBOR_ANTIFRAUD_INDE where   crt_time between sysdate-1   and  sysdate order by crt_time desc;
select * from MULBOR_RISK_ITEM where   crt_time between sysdate-1   and  sysdate order by crt_time desc;
select * from MULBOR_BASE where   crt_time between sysdate-1   and  sysdate order by crt_time desc;

select * from MULBOR_TRN_BATCH_DETAIL where   crt_time between sysdate-1   and  sysdate order by crt_time desc  for update;
select * from MULBOR_TRN_BATCH_DETAIL where  query_status = '0';
update MULBOR_TRN_BATCH_DETAIL set query_status = '9' where query_status = '0';
select count(*) from MULBOR_TRN_BATCH_DETAIL where  query_status = '0';

 ==>  Preparing: SELECT RPT_ID,RPT_MONTH,RPT_SOURCE,MNL_QUERY_SINGLE QUERY_SINGLE,MNL_QUERY_BATCH QUERY_BATCH,CNL_QUERY_CAMS QUERY_CAMS FROM (SELECT ROWNUM RN,RPT_ID,RPT_MONTH,RPT_SOURCE,MNL_QUERY_SINGLE,MNL_QUERY_BATCH,CNL_QUERY_CAMS FROM (SELECT RPT_ID,RPT_MONTH,RPT_SOURCE,MNL_QUERY_SINGLE,MNL_QUERY_BATCH,CNL_QUERY_CAMS FROM RPT_QUERY_COUNT WHERE RPT_SOURCE = '0001' ORDER BY RPT_MONTH DESC) WHERE ROWNUM <= ? * ?) WHERE RN > (? - 1) * ? ORDER BY RPT_MONTH DESC 
 
 select * from RPT_QUERY_COUNT;
 select * from  rpt_query_count_day order by crt_time desc;
 select * from bairong_als_base
 
 select dd.query_date_mon  "报表时间",
        sum(decode(channel_code, '00', con)) as "手动单条查询数量",
        sum(decode(channel_code, '01', con)) as "手动批量查询数量",
        sum(decode(channel_code, '10', con)) as "审批渠道查询数量"
   from (select to_char(query_date, 'yyyymm') query_date_mon,
                channel_code,
                sum(query_count) con
           from rpt_query_count_day
          where source_code = '000100'
          group by to_char(query_date, 'yyyymm'), channel_code) dd
  group by query_date_mon
  order by dd.query_date_mon

select * from (
   select dd.query_date_mon  RPT_MONTH,
        sum(decode(channel_code, '00', con)) as QUERY_SINGLE,
        sum(decode(channel_code, '01', con)) as QUERY_BATCH,
        sum(decode(channel_code, '10', con)) as QUERY_CAMS
   from (select to_char(query_date, 'yyyymm') query_date_mon,
                channel_code,
                sum(query_count) con
           from rpt_query_count_day 
          where source_code = '000100' 
          group by to_char(query_date, 'yyyymm'), channel_code) dd
  group by query_date_mon
  order by dd.query_date_mon desc 
  ) where  ( ROWNUM <3 and Rownum >0)
  select * from sys_user for update;
  
  select count(*) from (
  select dd.query_date_mon  RPT_MONTH,
        sum(decode(channel_code, '00', con)) as QUERY_SINGLE,
        sum(decode(channel_code, '01', con)) as QUERY_BATCH,
        sum(decode(channel_code, '10', con)) as QUERY_CAMS
   from (select to_char(query_date, 'yyyymm') query_date_mon,
                channel_code,
                sum(query_count) con
           from rpt_query_count_day 
          where source_code = '000100' 
          group by to_char(query_date, 'yyyymm'), channel_code) dd
  group by query_date_mon
  order by dd.query_date_mon desc )
  
  select * from sys_user order by crt_time desc;
  select * from SYS_CONFIG_SETTING;
  BAIRONG_ALS_BASE
  刘伟2
  select * from SYS_LOG_OPERATE order by crt_time desc;
  select * from bairong_als_base;
  
  select * from SYS_LOG_QUERY order by crt_time desc;
  --271
  select count(*) from SYS_LOG_QUERY;
  select * from SYS_CONFIG_SETTING;
  select * from sys_sms_template;
  select * from sys_user for update;
  
  	SELECT
		BR.RESIDENT_ADDR,BR.HOME_PHONE,
		BR.RESIDE_STATE,BR.INFO_UPD_TIME
		FROM BANK_REPORT_MARK_INFO B
		LEFT JOIN
		BANK_RESIDENCE_INFO BR ON B.UNIQUE_RELID = BR.UNIQUE_RELID
		WHERE B.UNIQUE_RELID = '176CFAAD57A268E4E0531907806A9678'
    
    select * from bairong_als_base 
    select * from bairong_al
    
    BANK_REPORT_MARK_INFO
    
    		SELECT
		BP.COMPANY,BP.UNIT_CHARACTER,BP.COMP_ADDR,BP.UNIT_PHONE,
		BP.JOB,BP.INDUSTRY_TYPE,BP.OCCUPATION_CODE,BP.TECHNICAL_TITLE,
		BP.CUR_COMP_WORK_STAT_Y,BP.INFO_UPD_TIME
		FROM BANK_REPORT_MARK_INFO B
		LEFT JOIN
		BANK_PROFESSION_INFO BP ON B.UNIQUE_RELID = BP.UNIQUE_RELID
		WHERE B.UNIQUE_RELID = '176CFAAD57A268E4E0531907806A9678'
    
   
    select * from BANK_REPORT_MARK_INFO order by crt_time;
    select * from BANK_CUR_QUERY_REQUEST order by crt_time;
    select * from BANK_DISSENT_HINT order by crt_time;
    select * from BANK_IDENTITY_PROFILE order by crt_time;
    select * from BANK_MARITAL_INFO order by crt_time;
    select * from BANK_CHEAT_PROOF_CAUTION order by crt_time;
    
    select * from bairong_als_trn_request;
    
    select count(*) from (
select  * from bairong_als_trn_batch_detail b where b.batch_file_id in(
select batch_file_id from bairong_als_trn_batch_file where batch_id in(
select batch_id from bairong_als_trn_batch where crt_time between to_date('2019-11-21 00:00:00','yyyy-mm-dd hh24:mi:ss') and 
to_date('2019-12-10 23:59:59','yyyy-mm-dd hh24:mi:ss')
)
)
) temp where temp.trn_id  not in(
select trn_id from bairong_als_base 
)
select object_name from user_objects where object_type = 'TABLE' and created between to_date('2020-01-01 00:00:00','yyyy-mm-dd hh24:mi:ss') and 
to_date('2020-06-08 23:59:59','yyyy-mm-dd hh24:mi:ss')
select * from NCIIC_FOREIGNER_INFO;
select * from bairong_als_msg_response

NCIIC_TRN_REQUEST
NCIIC_INFO
NCIIC_MSG_RESPONSE

NCIIC_XP_REQUEST
NCIIC_XP_INFO
NCIIC_XP_RESPONSE

select * from NCIIC_FOREIGNER_REQUEST;
select * from NCIIC_FOREIGNER_INFO;
select * from NCIIC_FOREIGNER_RESPONSE;
select * from NCIIC_FOREIGNER_RESPONSE;
select * from FICO_TRN_BATCH_DETAIL order by crt_time desc;
select count(*) from FICO_TRN_BATCH_DETAIL order by crt_time desc;

--delete from FICO_TRN_BATCH_DETAIL;
select * from sys_user for update ;
select * from BANK_REPORT_MARK_INFO order by crt_time desc; 
PBOC_TRN_SINGLE_REVIEW
insert into general_class_setting values('WST000202','com.huaxia.plaze.modules.nciic.webservice.ResidenceForeignersWebService','invoke','residenceForeignersWebService');
commit;
select * from general_class_setting;
--delete from general_class_setting where bean_name ='residenceForeignersWebService';
select * from NCIIC_FOREIGNER_REQUEST order by crt_time desc;
select * from NCIIC_FOREIGNER_INFO order by crt_time desc;
select * from NCIIC_FOREIGNER_RESPONSE order by crt_time desc;
--1337,1305,1305
select count(*) from NCIIC_FOREIGNER_REQUEST;
select count(*) from NCIIC_FOREIGNER_INFO;
select count(*) from NCIIC_FOREIGNER_RESPONSE;

alter table NCIIC_FOREIGNER_INFO add (CERT_TYPE   VARCHAR2(2));
comment on column NCIIC_FOREIGNER_INFO.CERT_TYPE
  is '证件类型，07外国人永久居留身份证，90港澳居民身份证，91台湾居民居住证';
  
    SELECT * FROM (SELECT MESSAGE_BODY  FROM NCIIC_FOREIGNER_RESPONSE
		WHERE CERT_NO=#{certNo,jdbcType = VARCHAR} 
		AND NAME=#{name,jdbcType = VARCHAR} 
	 ORDER BY CRT_TIME) WHERE ROWNUM = '1'
  
  select * from NCIIC_FOREIGNER_RESPONSE r where r.trn_id in(
          select trn_id from NCIIC_FOREIGNER_REQUEST t where t.cert_no ='410221199505293452' and t.name = '18612995529' and t.BIRTH ='19950529' and t.EXPIRY_DATE ='20950529'
  ) and ROWNUM = '1';
  select * from NCIIC_FOREIGNER_RESPONSE r where r.trn_id in(
          select trn_id from NCIIC_FOREIGNER_REQUEST t where t.cert_no ='410221199505293452' and t.name = '18612995529' and t.BIRTH ='19950529' and t.EXPIRY_DATE ='20950529'
  ) and ROWNUM = '1' order by crt_time desc;
   select * from NCIIC_FOREIGNER_RESPONSE r where r.trn_id in(
          select trn_id from NCIIC_FOREIGNER_REQUEST t where t.cert_no ='410221199505293452' and t.name = '18612995529' and t.BIRTH ='19950529' and t.EXPIRY_DATE ='20950529'
  );
  select * from (
   select * from NCIIC_FOREIGNER_RESPONSE r where r.trn_id in(
          select trn_id from NCIIC_FOREIGNER_REQUEST t where t.cert_no ='410221199505293452' and t.name = '18612995529' and t.BIRTH ='19950529' and t.EXPIRY_DATE ='20950529'
  ) order by crt_time desc
  ) where ROWNUM = '1';
  
  
  select * from ID5_INFO;
  select * from NCIIC_POLICE_QUERY_LOG where trn_id not in( 
   select trn_id from NCIIC_POLICE_SINGLE
   ) ;
   select * from NCIIC_POLICE_SINGLE where trn_id not in( 
   select trn_id from NCIIC_POLICE_QUERY_LOG
   ) ;
    select * from NCIIC_POLICE_QUERY_LOG order by crt_time desc;
  select * from NCIIC_POLICE_SINGLE order by crt_time desc;
  select * from sys_User for update;
  
 select * from SYS_LOG_LOGIN order by crt_time desc;
 select * from SYS_LOG_QUERY order by crt_time desc;
  select * from SYS_LOG_OPERATE order by crt_time desc;
   select * from SYS_USER;
   
 select * from SYS_TEAM;
 select * from SYS_USER;
 select * from SYS_ROLE;
 select * from  SYS_USER_ROLE
 ID5_EDU_TRN_BATCH
 ID5_EDU_TRN_BATCH_FILE
 
 
FICO_TRN_BATCH
FICO_TRN_BATCH_FILE
select *from FICO_TRN_BATCH_DETAIL;
select *from PBOC_TRN_BATCH_review;


FICO_TRN_BATCH
FICO_TRN_BATCH_FILE
select *from FICO_TRN_BATCH_DETAIL

                                                                                                                                                                                                                                                                                                      






