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
  is 'Ԥ���ϴ��ļ���־��';
-- Add comments to the columns 
comment on column YS_FILE_UPLOAD_LOG.ID
  is 'Ψһ�������';
comment on column YS_FILE_UPLOAD_LOG.FILE_ID
  is '�ļ����';
comment on column YS_FILE_UPLOAD_LOG.FILE_NAME
  is '�ļ�����';
  comment on column YS_FILE_UPLOAD_LOG.OPERAT_TYPE
  is '��־����';
comment on column YS_FILE_UPLOAD_LOG.OPERAT_CODE
  is '��ǰ������';
comment on column YS_FILE_UPLOAD_LOG.OPERATOR
  is '��ǰ����������';
  comment on column YS_FILE_UPLOAD_LOG.OPERAT_DATE
  is '��־��¼����';