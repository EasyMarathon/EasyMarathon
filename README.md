# EasyMarathon
����README


##DAO���ã�
��Connection conn = DaoBase.getConnection(true);

����true������������false��������������������Ҫ�����޸����ݵ�Ӧ����������Ȼ���ֶ�commit��

����daoʱҪcatch SQLexception��catch�˱�ʾsqlִ�г����⣬����ֱ�ӷ���ϵͳ����

���������������Ҫ�ֶ�conn.commit()�����������exceptionҪconn.rollback()

##�ļ��зֲ�
bean���ͨ�õĻ������ݽṹ��pojo������

dao���Ǻ����ݿ�򽻵���

service������Ҫҵ���߼�������dao��ȡ����

action������΢�Ź�ͨ�Ĳ��֣����鴿servletд��struts���ã����Լ���Ҫͨ����ҳ���ʵĲ��֣������Ժ��������֮��Ĺ��ܣ���Ƭ�ϴ��ȣ������struts������Ҫ�ѽ��յ����ݷ�װ��ͳһ��ʽ��service  
����΢�ŷ�����xml����dom4j������map�Ժ�ֵ��MsgBean��Ȼ���MsgBean����Service���Լ���Service���ص����ݷ�װ��MsgBean��ע�ⷢ��ID�����ID������

test����Ų�������