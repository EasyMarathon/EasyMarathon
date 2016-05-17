# EasyMarathon
易跑README


##DAO调用：
先Connection conn = DaoBase.getConnection(true);

参数true代表不启用事务，false代表启用事务（理论上需要增加修改数据的应该启用事务，然后手动commit）

调用dao时要catch SQLexception，catch了表示sql执行出问题，可以直接返回系统错误

如果启用了事务，需要手动conn.commit()，如果捕获了exception要conn.rollback()
