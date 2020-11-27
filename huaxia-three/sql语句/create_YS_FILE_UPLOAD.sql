-- Create table
create table YS_FILE_UPLOAD
(
  FILE_ID        VARCHAR2(32) not null,
  FILE_NAME      VARCHAR2(200),
  FILE_PATH      VARCHAR2(300),
  FILE_DESCRIBE  VARCHAR2(100),
  UPLOAD_DATE    DATE,
  OPERAT_CODE    VARCHAR2(32),
  OPERAT_PERSON  VARCHAR2(32)
)
-- Create/Recreate primary, unique and foreign key constraints 
alter table YS_FILE_UPLOAD
  add constraint PK_YS_FILE_UPLOAD primary key (FILE_ID)
-- Add comments to the table 
comment on table YS_FILE_UPLOAD
  is '预审上传文件表';
-- Add comments to the columns 
comment on column YS_FILE_UPLOAD.FILE_ID
  is '文件编号';
comment on column YS_FILE_UPLOAD.FILE_NAME
  is '文件名字';
comment on column YS_FILE_UPLOAD.FILE_PATH
  is '存储路径';
  comment on column YS_FILE_UPLOAD.FILE_DESCRIBE
  is '文件描述';
comment on column YS_FILE_UPLOAD.UPLOAD_DATE
  is '上传日期';
comment on column YS_FILE_UPLOAD.OPERAT_CODE
  is '当前操作人';
  comment on column YS_FILE_UPLOAD.OPERAT_PERSON
  is '当前操作人姓名';