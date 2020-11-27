-- Create table
create table YS_FILE_UPLOAD_LOG
(
  ID          VARCHAR2(32) not null,
  FILE_ID     VARCHAR2(32) not null,
  FILE_NAME   VARCHAR2(200),
  OPERAT_TYPE VARCHAR2(2),
  OPERAT_CODE VARCHAR2(32),
  OPERATOR    VARCHAR2(20),
  OPERAT_DATE DATE
)
-- Create/Recreate primary, unique and foreign key constraints 
alter table YS_FILE_UPLOAD_LOG
  add constraint PK_YS_FILE_UPLOAD_LOG primary key (ID)
  -- Add comments to the table 
comment on table YS_FILE_UPLOAD_LOG
  is '预审上传文件日志表';
-- Add comments to the columns 
comment on column YS_FILE_UPLOAD_LOG.ID
  is '唯一编号主键';
comment on column YS_FILE_UPLOAD_LOG.FILE_ID
  is '文件编号';
comment on column YS_FILE_UPLOAD_LOG.FILE_NAME
  is '文件名字';
  comment on column YS_FILE_UPLOAD_LOG.OPERAT_TYPE
  is '日志类型';
comment on column YS_FILE_UPLOAD_LOG.OPERAT_CODE
  is '当前操作人';
comment on column YS_FILE_UPLOAD_LOG.OPERATOR
  is '当前操作人姓名';
  comment on column YS_FILE_UPLOAD_LOG.OPERAT_DATE
  is '日志记录日期';