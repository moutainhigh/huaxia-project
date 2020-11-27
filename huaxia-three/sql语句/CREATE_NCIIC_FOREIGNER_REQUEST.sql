-- Create table
create table NCIIC_FOREIGNER_REQUEST
(
  UUID            CHAR(32) not null,
  CRT_TIME        TIMESTAMP(6) not null,
  CRT_USER        VARCHAR2(32),
  TRN_ID          CHAR(32),
  REQUEST_CHANNEL VARCHAR2(16),
  QUERY_MODE      CHAR(1),
  CERT_TYPE       VARCHAR2(2),
  CERT_NO         VARCHAR2(18),
  NAME            VARCHAR2(64),
  BIRTH           VARCHAR2(32),
  EXPIRY_DATE     VARCHAR2(32)
)
-- Add comments to the table 
comment on table NCIIC_FOREIGNER_REQUEST
  is '外国人永久居留证请求信息';
-- Add comments to the columns 
comment on column NCIIC_FOREIGNER_REQUEST.UUID
  is '记录编号';
comment on column NCIIC_FOREIGNER_REQUEST.CRT_TIME
  is '创建时间';
comment on column NCIIC_FOREIGNER_REQUEST.CRT_USER
  is '创建用户';
comment on column NCIIC_FOREIGNER_REQUEST.TRN_ID
  is '交易编号';
comment on column NCIIC_FOREIGNER_REQUEST.REQUEST_CHANNEL
  is '请求渠道';
comment on column NCIIC_FOREIGNER_REQUEST.QUERY_MODE
  is '查询模式';
comment on column NCIIC_FOREIGNER_REQUEST.CERT_TYPE
  is '被查询者证件类型';
comment on column NCIIC_FOREIGNER_REQUEST.CERT_NO
  is '被查询者证件号码';
comment on column NCIIC_FOREIGNER_REQUEST.NAME
  is '被查询者姓名';
comment on column NCIIC_FOREIGNER_REQUEST.BIRTH
  is '被查询者出生日期';
comment on column NCIIC_FOREIGNER_REQUEST.EXPIRY_DATE
  is '被查询者证件有效期';

