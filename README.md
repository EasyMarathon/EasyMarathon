# EasyMarathon
易跑README


##DAO调用：
先Connection conn = DaoBase.getConnection(true);

参数true代表不启用事务，false代表启用事务（理论上需要增加修改数据的应该启用事务，然后手动commit）

调用dao时要catch SQLexception，catch了表示sql执行出问题，可以直接返回系统错误

如果启用了事务，需要手动conn.commit()，如果捕获了exception要conn.rollback()

##文件夹分布
bean里放通用的基础数据结构，pojo类这种

dao就是和数据库打交道了

service负责主要业务逻辑，调用dao获取数据

action包括和微信沟通的部分（建议纯servlet写，struts无用），以及需要通过网页访问的部分（比如以后个人中心之类的功能，照片上传等，这个用struts），需要把接收的数据封装成统一格式给service  
比如微信发来的xml，用dom4j解析成map以后赋值给MsgBean，然后把MsgBean交给Service，以及把Service返回的数据封装回MsgBean（注意发送ID与接收ID互换）

test里面放测试内容