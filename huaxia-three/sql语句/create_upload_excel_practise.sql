-- Create table
create table upload_excel_practise
(
  AUTO_ID      VARCHAR2(32) not null,
  APP_ID        VARCHAR2(32),
  field01    VARCHAR2(12),
  field02     VARCHAR2(32),
  field03    VARCHAR2(6),
  field04   VARCHAR2(6),
  CRT_USER     VARCHAR2(32),
  CRT_TIME     TIMESTAMP(6),
  LST_UPD_USER VARCHAR2(32),
  LST_UPD_TIME TIMESTAMP(6)
)
-- Add comments to the table 
comment on table upload_excel_practise
  is '上传文件练习表-刘伟';
-- Add comments to the columns 
comment on column upload_excel_practise.AUTO_ID
  is 'pk';
comment on column upload_excel_practise.APP_ID
  is 'appid';
comment on column upload_excel_practise.field01
  is '字段1';
comment on column upload_excel_practise.field02
  is '字段2';
comment on column upload_excel_practise.field03
  is '字段3';
comment on column upload_excel_practise.field04
  is '字段4';
comment on column upload_excel_practise.CRT_USER
  is '创建人';
comment on column upload_excel_practise.CRT_TIME
  is '创建时间';
comment on column upload_excel_practise.LST_UPD_USER
  is '最后修改人';
comment on column upload_excel_practise.LST_UPD_TIME
  is '最后修改时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table upload_excel_practise
  add constraint PK_upload_excel_practise_ID primary key (AUTO_ID)
-- Create/Recreate indexes 
create index upload_excel_practise_01 on upload_excel_practise (field01)
create index upload_excel_practise_02 on upload_excel_practise (field02)
 
