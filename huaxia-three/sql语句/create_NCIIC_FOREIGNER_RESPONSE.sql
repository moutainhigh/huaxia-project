-- Create table
create table NCIIC_FOREIGNER_RESPONSE
(
  UUID         CHAR(32) not null,
  CRT_TIME     TIMESTAMP(6),
  CRT_USER     VARCHAR2(32),
  TRN_ID       CHAR(32) ,
  MESSAGE_BODY CLOB
)
-- Add comments to the table 
comment on table NCIIC_FOREIGNER_RESPONSE
  is '外国人永久居留证响应表';
-- Add comments to the columns 
comment on column NCIIC_FOREIGNER_RESPONSE.UUID
  is '记录编号';
comment on column NCIIC_FOREIGNER_RESPONSE.CRT_TIME
  is '创建时间';
comment on column NCIIC_FOREIGNER_RESPONSE.CRT_USER
  is '创建用户';
comment on column NCIIC_FOREIGNER_RESPONSE.TRN_ID
  is '交易编号';
comment on column NCIIC_FOREIGNER_RESPONSE.MESSAGE_BODY
  is '响应报文体';
