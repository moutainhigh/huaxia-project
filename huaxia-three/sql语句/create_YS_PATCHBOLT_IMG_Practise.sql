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
  is '����Ӱ��ͼƬ��';
-- Add comments to the columns 
comment on column YS_PATCHBOLT_IMG_Practise.AUTO_ID
  is '����ֵ PK';
comment on column YS_PATCHBOLT_IMG_Practise.APP_ID
  is '��������';
comment on column YS_PATCHBOLT_IMG_Practise.PATCH_AUTO_ID
  is '��Ӧ������ID';
comment on column YS_PATCHBOLT_IMG_Practise.IMG_URL
  is 'ͼƬ��ַ';
comment on column YS_PATCHBOLT_IMG_Practise.IMG_NAME
  is 'ͼƬ����';
comment on column YS_PATCHBOLT_IMG_Practise.IMG_SIZE
  is 'ͼƬ��С';
comment on column YS_PATCHBOLT_IMG_Practise.IMG_TYPE
  is 'ͼƬ����';
comment on column YS_PATCHBOLT_IMG_Practise.CRT_DATE
  is '�ϴ�ͼƬ����';
comment on column YS_PATCHBOLT_IMG_Practise.CRT_USER
  is '�ϴ�ͼƬ�û�';
comment on column YS_PATCHBOLT_IMG_Practise.IS_UPLOAD_YX
  is '�Ƿ�ɹ��ύ��Ӱ�� 0ʧ�� 1�ɹ�';
comment on column YS_PATCHBOLT_IMG_Practise.TO_YX_TIME
  is '�ύ��Ӱ��ʱ��';
comment on column YS_PATCHBOLT_IMG_Practise.TO_YX_URL
  is 'Ӱ���ŵ�ַ';
comment on column YS_PATCHBOLT_IMG_Practise.CRT_STATUS
  is '��ǰͼƬ����״̬ 0���� 1Ӱ��';
comment on column YS_PATCHBOLT_IMG_Practise.BATCH_CODE
  is '�ϴ�Ӱ�����κ�';
comment on column YS_PATCHBOLT_IMG_Practise.SEQUENCE_NUM
  is '��ӦӰ�����';
comment on column YS_PATCHBOLT_IMG_Practise.IS_RETURN
  is '�Ƿ��ѽ��շ���״̬0:�� 1:��';
-- Create/Recreate primary, unique and foreign key constraints 
alter table YS_PATCHBOLT_IMG_Practise
  add constraint YS_PATCHBOLT_IMG_Practise_ID primary key (AUTO_ID)
-- Create/Recreate indexes 
create index YS_IMG_Practise_APPID on YS_PATCHBOLT_IMG_Practise (APP_ID)
