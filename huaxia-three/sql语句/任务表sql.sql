delete from TASK_CALL_INFO where task_type='0000';
commit;
delete from TASK_CALL_INFO where task_type='0001';
commit;
delete from TASK_CALL_INFO where task_type='0002';
commit;
delete from TASK_CALL_INFO where task_type='0003';
commit;
delete from TASK_CALL_INFO;
commit;
delete from task_call_info_his;
commit;

insert into TASK_CALL_INFO values('0001','0001','0', SYS_GUID(),SYSDATE,'1','1',substr( SYS_GUID(),0,16),'1','1','18612995529','18612995529','1','1','1','1','1','1','19-2�� -19 11.22.55.000000 ����',1,1);
insert into TASK_CALL_INFO values('0001','0001','0', SYS_GUID(),SYSDATE,'1','1', substr( SYS_GUID(),0,16),'1','1','18612995529','18612995529','1','1','1','1','1','1','19-2�� -19 11.22.55.000000 ����',1,1);
insert into TASK_CALL_INFO values('0001','0001','0', SYS_GUID(),SYSDATE,'1','1',substr( SYS_GUID(),0,16),'1','1','18612995529','18612995529','1','1','1','1','1','1','19-2�� -19 11.22.55.000000 ����',1,1);
commit;
insert into TASK_CALL_INFO values('0002','0002','0', SYS_GUID(),SYSDATE,'1','1',substr( SYS_GUID(),0,16),'1','1','18612995529','18612995529','1','1','1','1','1','1','19-2�� -19 11.22.55.000000 ����',1,1);
insert into TASK_CALL_INFO values('0002','0002','0', SYS_GUID(),SYSDATE,'1','1', substr( SYS_GUID(),0,16),'1','1','18612995529','18612995529','1','1','1','1','1','1','19-2�� -19 11.22.55.000000 ����',1,1);
insert into TASK_CALL_INFO values('0002','0002','0', SYS_GUID(),SYSDATE,'1','1',substr( SYS_GUID(),0,16),'1','1','18612995529','18612995529','1','1','1','1','1','1','19-2�� -19 11.22.55.000000 ����',1,1);
commit;
insert into TASK_CALL_INFO values('0003','0003','0',SYS_GUID(),SYSDATE,'1','1',substr( SYS_GUID(),0,16),'1','1','18612995529','18612995529','1','1','1','1','1','1','19-2�� -19 11.22.55.000000 ����',1,1);
insert into TASK_CALL_INFO values('0003','0003','0',SYS_GUID(),SYSDATE,'1','1',substr( SYS_GUID(),0,16),'1','1','18612995529','18612995529','1','1','1','1','1','1','19-2�� -19 11.22.55.000000 ����',1,1);
insert into TASK_CALL_INFO values('0003','0003','0',SYS_GUID(),SYSDATE,'1','1',substr( SYS_GUID(),0,16),'1','1','18612995529','18612995529','1','1','1','1','1','1','19-2�� -19 11.22.55.000000 ����',1,1);
commit;

select * from TASK_CALL_INFO for update;
select * from task_call_info_his;
MULBOR_BASE
select * from MULBOR_BASE  where app_id = '190710A623P00031';
select * from MULBOR_RISK_ITEM where app_id = '190710A623P00031';
select ANTIFRAUD_C82D025AF778 from MULBOR_ANTIFRAUD_INDE where app_id = '190710A623P00031';
select * from MULBOR_BLACK_LIST  where app_id = '190710A623P00031';
select * from MULBOR_DESCREDIT_COUNT  where app_id = '190710A623P00031';
select * from MULBOR_GREY_LIST  where app_id = '190710A623P00031';

select * from MULBOR_BASE  order by crt_time desc;
select * from MULBOR_RISK_ITEM order by crt_time desc;
select *  from MULBOR_ANTIFRAUD_INDE  order by crt_time desc;
select * from MULBOR_BLACK_LIST  order by crt_time desc;
select * from MULBOR_DESCREDIT_COUNT order by crt_time desc;
select * from MULBOR_GREY_LIST order by crt_time desc;

delete from MULBOR_GREY_LIST;
delete from MULBOR_DESCREDIT_COUNT;
delete from MULBOR_BLACK_LIST;
delete from MULBOR_ANTIFRAUD_INDE;
delete from MULBOR_RISK_ITEM;
delete from MULBOR_BASE;
commit;

select * from FICO_BIG_DATA_HUB;
delete from FICO_BIG_DATA_HUB;

select * from OPAS_SEA_AIR_DATA;
delete from OPAS_SEA_AIR_DATA;

delete from BAIRONG_ALS_BASE;
delete from BAIRONG_ALS_D7;
delete from BAIRONG_ALS_D15;
delete from BAIRONG_ALS_M1;
delete from BAIRONG_ALS_M3;
delete from BAIRONG_ALS_M6;
delete from BAIRONG_ALS_M12;
delete from BAIRONG_ALS_FST;
delete from BAIRONG_ALS_LST;
commit;
select count(*) from BAIRONG_ALS_BASE;
select * from BAIRONG_ALS_BASE where 1=1 order by crt_time desc;
select * from BAIRONG_ALS_BASE;
select * from BAIRONG_ALS_D7;
select * from BAIRONG_ALS_D15;
select * from BAIRONG_ALS_M1;
select * from BAIRONG_ALS_M3;
select * from BAIRONG_ALS_M6;
select * from BAIRONG_ALS_M12;
select * from BAIRONG_ALS_FST;
select * from BAIRONG_ALS_LST;

select count(*) from BAIRONG_ALS_BASE;

update TASK_CALL_STATUS set task_status = '0' where uuid ='3                               ';
select task_status from TASK_CALL_STATUS order by crt_time desc where uuid ='3                               ';

select * from TASK_CALL_STATUS order by crt_time desc for update;

select * from TASK_STATUS_HISTORY order by crt_time desc;
select count(1) from TASK_STATUS_HISTORY;
select * from task_call_worker for update;
insert into task_call_worker values('000202','��������þ���֤��Ϣ�˲����','com.huaxia.cams.worker.WST000202TaskWorker','');
insert into task_call_worker values('F00202','��������þ���֤��Ϣ�˲���񸽿�','com.huaxia.cams.worker.WSTF00202TaskWorker','');
-----------------------------------------------PAD����---------------------------------------------
select * from NCIIC_XP_INFO
    bairong_als_trn_batch_detail
PROC_TOOL_CLEAN_DTJD
PROC_TOOL_CLEAN_DTJD

select * from OPAS_PBOC_PERSONAL_INFO;
select * from V$NLS_PARAMETERS;

insert into task_call_status values( sys_guid(),sysdate,sysdate,'liuwei', sys_guid(),'000201','0',to_char(sysdate,'yyyymmdd')||lpad(round(dbms_random.value(1,99999999)),8,0),'01','410221199505293452','��ΰ','18612995529','1','1',0,0);
commit;
select * from task_call_status order by crt_time desc;

select * from BAIRONG_ALS_BASE where crt_user = 'CAMS' and crt_time between sysdate-1   and  sysdate order by crt_time desc;

select * from task_call_status where app_id = '1904019623P00012';
select * from task_status_history where app_id = '1904019623P00012' and task_type = '001101';
select * from task_status_history order by crt_time desc;
select * from POLICE_SIMPLIFY_INFO where INSTR(ERROR_MESSAGE,'�����޴˺�')>0  ;

select * from TRD_OPERATOR_ONLINE where 1=1 order by crt_time desc;
select to_char(sysdate,'yyyymmdd')||lpad(round(dbms_random.value(1,9999999999)),10,0) from dual;
select * from TRD_OPERATOR_ONLINE order by crt_time desc;

select count(*) from TRD_OPERATOR_ONLINE order by crt_time desc;
500407

 select count(*)  as coun from bairong_als_base  where   (CRT_TIME BETWEEN	sysdate -1 AND sysdate) 
  
  select * from sys_user; 
  
  select * from opas_biz_inp_appl_c1 ;
  
insert into task_call_status values( sys_guid(),sysdate,sysdate,'liuwei', sys_guid(),'000700','0',to_char(sysdate,'yyyymmdd')||lpad(round(dbms_random.value(1,99999999)),8,0),'01','610628198304020051','����','18777182225','1','1',0,0);
insert into task_call_status values( sys_guid(),sysdate,sysdate,'liuwei', sys_guid(),'000700','0',to_char(sysdate,'yyyymmdd')||lpad(round(dbms_random.value(1,99999999)),8,0),'01','132332197309133013','�ַ��','13722866448','1','1',0,0);
insert into task_call_status values( sys_guid(),sysdate,sysdate,'liuwei', sys_guid(),'000700','0',to_char(sysdate,'yyyymmdd')||lpad(round(dbms_random.value(1,99999999)),8,0),'01','132323197303060013','����ͤ','13930193601','1','1',0,0);
insert into task_call_status values( sys_guid(),sysdate,sysdate,'liuwei', sys_guid(),'000700','0',to_char(sysdate,'yyyymmdd')||lpad(round(dbms_random.value(1,99999999)),8,0),'01','130626198001011855','���','13810519424','1','1',0,0);
insert into task_call_status values( sys_guid(),sysdate,sysdate,'liuwei', sys_guid(),'000700','0',to_char(sysdate,'yyyymmdd')||lpad(round(dbms_random.value(1,99999999)),8,0),'01','13018219870224572X','�����','15131185527','1','1',0,0);
insert into task_call_status values( sys_guid(),sysdate,sysdate,'liuwei', sys_guid(),'000700','0',to_char(sysdate,'yyyymmdd')||lpad(round(dbms_random.value(1,99999999)),8,0),'01','130132198110245730','��־��','13315984245','1','1',0,0);
insert into task_call_status values( sys_guid(),sysdate,sysdate,'liuwei', sys_guid(),'000700','0',to_char(sysdate,'yyyymmdd')||lpad(round(dbms_random.value(1,99999999)),8,0),'01','130626198001015291','����','13810519424','1','1',0,0);
insert into task_call_status values( sys_guid(),sysdate,sysdate,'liuwei', sys_guid(),'000700','0',to_char(sysdate,'yyyymmdd')||lpad(round(dbms_random.value(1,99999999)),8,0),'01','130626197901016972','��ʮ��','13810519424','1','1',0,0);
insert into task_call_status values( sys_guid(),sysdate,sysdate,'liuwei', sys_guid(),'000700','0',to_char(sysdate,'yyyymmdd')||lpad(round(dbms_random.value(1,99999999)),8,0),'01','13062619800101057X','����','13810519424','1','1',0,0);
insert into task_call_status values( sys_guid(),sysdate,sysdate,'liuwei', sys_guid(),'000700','0',to_char(sysdate,'yyyymmdd')||lpad(round(dbms_random.value(1,99999999)),8,0),'01','360426197410101736','�����','13979250908','1','1',0,0);
insert into task_call_status values( sys_guid(),sysdate,sysdate,'liuwei', sys_guid(),'000700','0',to_char(sysdate,'yyyymmdd')||lpad(round(dbms_random.value(1,99999999)),8,0),'01','130626197901018791','��ʮ��','13810519424','1','1',0,0);
insert into task_call_status values( sys_guid(),sysdate,sysdate,'liuwei', sys_guid(),'000700','0',to_char(sysdate,'yyyymmdd')||lpad(round(dbms_random.value(1,99999999)),8,0),'01','532901199202072697','���۱�','15287202804','1','1',0,0);
insert into task_call_status values( sys_guid(),sysdate,sysdate,'liuwei', sys_guid(),'000700','0',to_char(sysdate,'yyyymmdd')||lpad(round(dbms_random.value(1,99999999)),8,0),'01','330104198511284134','����','13732297900','1','1',0,0);
insert into task_call_status values( sys_guid(),sysdate,sysdate,'liuwei', sys_guid(),'000700','0',to_char(sysdate,'yyyymmdd')||lpad(round(dbms_random.value(1,99999999)),8,0),'01','362133198007170014','����','15579706919','1','1',0,0);
insert into task_call_status values( sys_guid(),sysdate,sysdate,'liuwei', sys_guid(),'000700','0',to_char(sysdate,'yyyymmdd')||lpad(round(dbms_random.value(1,99999999)),8,0),'01','130626198501015676','����Ʒ','13810519424','1','1',0,0);
insert into task_call_status values( sys_guid(),sysdate,sysdate,'liuwei', sys_guid(),'000700','0',to_char(sysdate,'yyyymmdd')||lpad(round(dbms_random.value(1,99999999)),8,0),'01','332623196806143610','������','13575865430','1','1',0,0);
insert into task_call_status values( sys_guid(),sysdate,sysdate,'liuwei', sys_guid(),'000700','0',to_char(sysdate,'yyyymmdd')||lpad(round(dbms_random.value(1,99999999)),8,0),'01','610221197904286119','���','1886870851','1','1',0,0);
commit;
select * from task_call_status order by crt_time desc;



insert into task_call_status values( sys_guid(),sysdate,sysdate,'liuwei', sys_guid(),'000700','0',to_char(sysdate,'yyyymmdd')||lpad(round(dbms_random.value(1,99999999)),8,0),'01','511524199003037213','��̲�','18557546116','1','1',0,0);
--------------------------�����������Ƶ�sql���-----------------------------------------to_char(sysdate,'yyyymmdd')||lpad(round(dbms_random.value(1,9999999999)),10,0)
----��������  test �����������Ŀ-----------------
declare 
       appid varchar2(200):= '';
       i  number;
begin
 i:=0;
 while i<1 loop
 select to_char(sysdate,'yyyymmdd')||lpad(round(dbms_random.value(1,99999999)),8,0) into appid from dual;
 insert into task_call_status values( sys_guid(),sysdate,sysdate,'liuwei', sys_guid(),'000202','0',appid,'01','410221199505293452','��ΰ','18612995524','1','1',0,0);
 insert into opas_biz_inp_appl_c1(APP_ID,C1_BIRTH,C5_IDTE1) values(appid,trunc(sysdate),trunc(sysdate+1));
 commit;
  i:= i+1;
 end loop;
end;
dbms_output.put_line(appid);
select * from task_call_status order by crt_time desc;
select * from task_status_history order by crt_time desc;
select APP_ID,C1_BIRTH,C5_IDTE1 from opas_biz_inp_appl_c1 order by crt_time desc;

declare 
       appid varchar2(200):= '';
       i  number;
begin
 i:=0;
 while i<1 loop
 select to_char(sysdate,'yyyymmdd')||lpad(round(dbms_random.value(1,99999999)),8,0) into appid from dual;
 insert into task_call_status values( sys_guid(),sysdate,sysdate,'liuwei', sys_guid(),'F00202','0',appid,'01','410221199505293452','��ΰ','18612995524','1','1',0,0);
 insert into opas_biz_inp_appl_c2(APP_ID,C2_BIRTH,C5_IDTE2) values(appid,trunc(sysdate),trunc(sysdate+1));
 commit;
  i:= i+1;
 end loop;
end;
select * from task_call_status order by crt_time desc;
select * from task_status_history order by crt_time desc;
select APP_ID,C2_BIRTH,C5_IDTE2 from opas_biz_inp_appl_c2 order by crt_time desc;

select * from NCIIC_FOREIGNER_INFO order by crt_time desc;
--5
select count(*) from task_call_status t where t.TASK_type = '000202' and t.TASK_STATUS = '0';
update task_call_status t set TASK_STATUS='80' where t.TASK_type = 'F00202' and t.TASK_STATUS = '0';
select count(*) from NCIIC_FOREIGNER_INFO order by crt_time desc;
	SELECT to_char(C1_BIRTH,'yyyymmdd') as birth,to_char(C5_IDTE1+1,'yyyymmdd') as EXPIRY_DATE  FROM OPAS_BIZ_INP_APPL_C1 WHERE APP_ID = '2020061029253259' and ROWNUM = '1';
----------------------------------------------------------
insert into opas_biz_inp_appl_c1(APP_ID,C1_BIRTH,C5_IDTE1) values('2',trunc(sysdate),trunc(sysdate));
select trunc(sysdate) from dual;
select * from opas_biz_inp_appl_c1 order by crt_time desc;
--------����ȶ�-----------------------------------
select * from POLICE_XP_INFO order by crt_time desc;
--------������������18----------------------------------
select * from POLICE_XP_INFO order by crt_time desc;
select count(*) from POLICE_XP_INFO order by crt_time desc;
------------------------------------------------------���ڶ�ͷ���-----------------------------------------------
select  * from BAIRONG_ALS_BASE where crt_time between sysdate-1   and  sysdate order by crt_time desc;
------------------------------------------------------ͬ�ܶ�ͷ���-----------------------------------------------
select * from MULBOR_BASE where   crt_time between sysdate-1   and  sysdate order by crt_time desc;

select * from MULBOR_GREY_LIST where   crt_time between sysdate-1   and  sysdate order by crt_time desc;
select * from MULBOR_DESCREDIT_COUNT where   crt_time between sysdate-1   and  sysdate order by crt_time desc;
select * from MULBOR_BLACK_LIST where   crt_time between sysdate-1   and  sysdate order by crt_time desc;
select * from MULBOR_ANTIFRAUD_INDE where   crt_time between sysdate-1   and  sysdate order by crt_time desc;
select * from MULBOR_RISK_ITEM where   crt_time between sysdate-1   and  sysdate order by crt_time desc;
select * from MULBOR_BASE where   crt_time between sysdate-1   and  sysdate order by crt_time desc;

-------------------------------------��ѯ�쳣--------------------------------
select * from task_call_status where task_status='0' order by crt_time desc;
------------------------------------------------------����ʱ��-----------------------------------------------
select * from TRD_OPERATOR_ONLINE where 1=1 order by crt_time desc;

POLICE_SIMPLIFY_INFO 
NCIIC_FOREIGNER_INFO
alter table NCIIC_FOREIGNER_INFO add (CARD_FLAG         CHAR(1) default '0');

  
alter table NCIIC_FOREIGNER_INFO add (CERT_TYPE   VARCHAR2(2));
comment on column NCIIC_FOREIGNER_INFO.CERT_TYPE
  is '֤�����ͣ�07��������þ������֤��90�۰ľ������֤��91̨������ס֤';
  
comment on column NCIIC_FOREIGNER_INFO.CARD_FLAG
  is '�����������ֱ�־��0������1����';
  	SELECT to_char(C2_BIRTH,'yyyymmdd'),to_char(C5_IDTE2,'yyyymmdd')  FROM OPAS_BIZ_INP_APPL_C2 WHERE APP_ID = #{appId, jdbcType=CHAR} and ROWNUM = '1'

    
