-- Create table
create table OPAS_LOWRISK_BATCHFILE_INFO
(
  ID           VARCHAR2(32),
  FILE_NAME    VARCHAR2(100),
  INPUT_TIME   DATE,
  INPUT_COUNTS NUMBER(10),
  OPERAT_CODE  VARCHAR2(32),
  OPERATOR     VARCHAR2(32),
  REMARK       VARCHAR2(4000),
  FILE_STATUS  CHAR(1)
)
-- Add comments to the columns 
comment on column OPAS_LOWRISK_BATCHFILE_INFO.FILE_NAME
  is '文件名';
comment on column OPAS_LOWRISK_BATCHFILE_INFO.INPUT_TIME
  is '上传时间';
comment on column OPAS_LOWRISK_BATCHFILE_INFO.INPUT_COUNTS
  is '导入条数';
comment on column OPAS_LOWRISK_BATCHFILE_INFO.OPERAT_CODE
  is '操作员编号';
comment on column OPAS_LOWRISK_BATCHFILE_INFO.OPERATOR
  is '操作员';
comment on column OPAS_LOWRISK_BATCHFILE_INFO.REMARK
  is '备注和失败信息';
comment on column OPAS_LOWRISK_BATCHFILE_INFO.FILE_STATUS
  is '上传状态';
-- Create/Recreate indexes 
create index IDX_LOWRISK_FILENAME on OPAS_LOWRISK_BATCHFILE_INFO (FILE_NAME)
