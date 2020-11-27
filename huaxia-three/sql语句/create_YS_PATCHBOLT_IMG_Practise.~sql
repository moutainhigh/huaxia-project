-- Create table
create table YS_PATCHBOLT_IMG_Practise
(
  AUTO_ID       VARCHAR2(32) not null,
  APP_ID        VARCHAR2(32),
  PATCH_AUTO_ID VARCHAR2(32),
  IMG_URL       VARCHAR2(200),
  IMG_NAME      VARCHAR2(100),
  IMG_SIZE      NUMBER,
  IMG_TYPE      VARCHAR2(10),
  CRT_DATE      DATE,
  CRT_USER      VARCHAR2(10),
  IS_UPLOAD_YX  CHAR(1) default '0',
  TO_YX_TIME    DATE,
  TO_YX_URL     VARCHAR2(200),
  CRT_STATUS    CHAR(1) default '0',
  BATCH_CODE    VARCHAR2(32),
  SEQUENCE_NUM  NUMBER,
  IS_RETURN     CHAR(1) default '0'
)
-- Add comments to the table 
comment on table YS_PATCHBOLT_IMG_Practise
  is '补件影像图片表';
-- Add comments to the columns 
comment on column YS_PATCHBOLT_IMG_Practise.AUTO_ID
  is '序列值 PK';
comment on column YS_PATCHBOLT_IMG_Practise.APP_ID
  is '申请件编号';
comment on column YS_PATCHBOLT_IMG_Practise.PATCH_AUTO_ID
  is '对应补件表ID';
comment on column YS_PATCHBOLT_IMG_Practise.IMG_URL
  is '图片地址';
comment on column YS_PATCHBOLT_IMG_Practise.IMG_NAME
  is '图片名称';
comment on column YS_PATCHBOLT_IMG_Practise.IMG_SIZE
  is '图片大小';
comment on column YS_PATCHBOLT_IMG_Practise.IMG_TYPE
  is '图片类型';
comment on column YS_PATCHBOLT_IMG_Practise.CRT_DATE
  is '上传图片日期';
comment on column YS_PATCHBOLT_IMG_Practise.CRT_USER
  is '上传图片用户';
comment on column YS_PATCHBOLT_IMG_Practise.IS_UPLOAD_YX
  is '是否成功提交到影像 0失败 1成功';
comment on column YS_PATCHBOLT_IMG_Practise.TO_YX_TIME
  is '提交到影像时间';
comment on column YS_PATCHBOLT_IMG_Practise.TO_YX_URL
  is '影像存放地址';
comment on column YS_PATCHBOLT_IMG_Practise.CRT_STATUS
  is '当前图片所处状态 0本地 1影像';
comment on column YS_PATCHBOLT_IMG_Practise.BATCH_CODE
  is '上传影像批次号';
comment on column YS_PATCHBOLT_IMG_Practise.SEQUENCE_NUM
  is '对应影像序号';
comment on column YS_PATCHBOLT_IMG_Practise.IS_RETURN
  is '是否已接收返回状态0:否 1:是';
-- Create/Recreate primary, unique and foreign key constraints 
alter table YS_PATCHBOLT_IMG_Practise
  add constraint YS_PATCHBOLT_IMG_Practise_ID primary key (AUTO_ID)
-- Create/Recreate indexes 
create index YS_IMG_Practise_APPID on YS_PATCHBOLT_IMG_Practise (APP_ID)
