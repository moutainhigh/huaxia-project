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
  is '��������þ���֤��Ӧ��';
-- Add comments to the columns 
comment on column NCIIC_FOREIGNER_RESPONSE.UUID
  is '��¼���';
comment on column NCIIC_FOREIGNER_RESPONSE.CRT_TIME
  is '����ʱ��';
comment on column NCIIC_FOREIGNER_RESPONSE.CRT_USER
  is '�����û�';
comment on column NCIIC_FOREIGNER_RESPONSE.TRN_ID
  is '���ױ��';
comment on column NCIIC_FOREIGNER_RESPONSE.MESSAGE_BODY
  is '��Ӧ������';
