# EasyMarathon
����README


##DAO���ã�
��Connection conn = DaoBase.getConnection(true);

����true������������false��������������������Ҫ�����޸����ݵ�Ӧ����������Ȼ���ֶ�commit��

����daoʱҪcatch SQLexception��catch�˱�ʾsqlִ�г����⣬����ֱ�ӷ���ϵͳ����

���������������Ҫ�ֶ�conn.commit()�����������exceptionҪconn.rollback()
