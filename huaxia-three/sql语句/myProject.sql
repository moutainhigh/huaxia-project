------------------------------自己新建的项目-------------------------
select * from Test_Oracle;
select count(*)  from Test_Oracle;

-- Create table
create table TEST_Batch
(
  UUID       CHAR(32) not null,
  CRT_TIME   TIMESTAMP(6),
  CRT_USER   VARCHAR2(32),
  USERNAME   VARCHAR2(2000),
  PASSWORD   VARCHAR2(2000),
  SEX        VARCHAR2(2000),
  BIRTHDAY   TIMESTAMP(6),
  NATION     VARCHAR2(2000),
  EDUCATION  VARCHAR2(2000),
  SCHOLL     VARCHAR2(2000),
  PROFESSION VARCHAR2(2000),
  MOBILE     VARCHAR2(2000),
  CERT_NO    VARCHAR2(2000),
  CITY       VARCHAR2(2000),
  INTRODUCT  VARCHAR2(2000),
  status     VARCHAR2(2)
)
-- Add comments to the table 
comment on table TEST_Batch
  is '测试练习批量';
select * from TEST_Batch;
insert into TEST_Batch(
  UUID      ,
  CRT_TIME  ,
  CRT_USER  ,
  USERNAME  ,
  PASSWORD  ,
  SEX       ,
  BIRTHDAY  ,
  NATION    ,
  EDUCATION ,
  SCHOLL    ,
  PROFESSION,
  MOBILE    ,
  CERT_NO   ,
  CITY      ,
  INTRODUCT ,
  status    
  ) 
  values (sys_guid(), sysdate,'liuwei','liuwei','abcd.1234','男',sysdate-20,'汉','本科','北京大学','计算机','18612995529',to_char(sysdate,'yyyymmdd')||lpad(round(dbms_random.value(1,9999999999)),10,0),'北京','10','0')
  ;
  select * from TEST_Batch;
-------测试oracle的for循环-------
  declare
  x number;
  begin
    x := 100;
    <<repeat_loop>>
    x :=x-1;
    Dbms_Output.put_line(x);
    IF x>0 THEN
        GOTO repeat_loop;
    END IF;
   end;
---------插入大数据------------
  declare
  x number;
  begin
    x := 1000000;
    <<repeat_loop>>
    x :=x-1;
  insert into TEST_Batch(
  UUID      ,
  CRT_TIME  ,
  CRT_USER  ,
  USERNAME  ,
  PASSWORD  ,
  SEX       ,
  BIRTHDAY  ,
  NATION    ,
  EDUCATION ,
  SCHOLL    ,
  PROFESSION,
  MOBILE    ,
  CERT_NO   ,
  CITY      ,
  INTRODUCT ,
  status    
  ) 
  values (sys_guid(), sysdate,'liuwei','liuwei','abcd.1234','男',sysdate-20,'汉','本科','北京大学','计算机','18612995529',to_char(sysdate,'yyyymmdd')||lpad(round(dbms_random.value(1,9999999999)),10,0),'北京','10','0')
  ;
  commit;
    IF x>0 THEN
        GOTO repeat_loop;
    END IF;
   end;
   select * from TEST_Batch order by crt_time desc;
      select count(*) from TEST_Batch;
      
   	SELECT 
			 UUID        ,
			 CRT_TIME    ,
			 CRT_USER    ,
			 username    ,
			 password    ,
			 sex         ,
			 birthday    ,
			 nation      ,
			 education   ,
			 scholl      ,
			 profession  ,
			 mobile      ,
			 cert_no     ,
			 city        ,
			 introduct    ,
			 status
		FROM TEST_Batch
    
      	SELECT 
			 UUID        ,
			 CRT_TIME    ,
			 CRT_USER    ,
			 username    ,
			 password    ,
			 sex         ,
			 birthday    ,
			 nation      ,
			 education   ,
			 scholl      ,
			 profession  ,
			 mobile      ,
			 cert_no     ,
			 city        ,
			 introduct    ,
			 status
		FROM TEST_Batch where rownum <10000
    
select * from Test_Oracle order by crt_time desc;
select count(*) from Test_Oracle;
select count(*) from TEST_Batch;
select count(*) from TEST_Batch