**************************************理工资讯*****************************************

*--------------------------------用到的技术---------------------------------*
框架:  SpringBoot,SpringMVC
数据库:  MySQL
ORM框架:  Mybatis
模板语言:  Velocity
主要使用语言:  Java
配置语言:  Yaml
数据格式:  Json

*---------------------------------功能-------------------------------------*
一.注册:
1.用户名合法性检测(长度,敏感词,重复,特殊字符)
2.密码长度要求
3.密码salt加密,密码强度检测(MD5库)
4.用户邮件/短信激活

二.登录/登出
登陆:服务器密码校验/三方校验回调,token登记
        服务器token关联userid
        客户端存储token(app存储本地,浏览器存储cookie)
    服务端/客户端token有效期设置(记住登录)
token可以是sessionid,或者是cookie里的一个key
登出:
服务端.客户端token删除
session清理

三.页面浏览
客户端:带有token的HTTP请求
服务端:
    根据token获取用户id
    根据用户id获取用户真实信息
    用户和页面访问权限处理
    渲染页面/跳转页面

四.Interceptor
HanderInteceptor:
preHandle
postHandle
afterCompletion
链路回调思想:

五.未登录跳转
使用拦截器

六.数据安全性
1.HTTPS注册页
2.公钥加密私钥解密,支付宝h5页面的支付密码加密
3.用户密码salt防止破解(CSDN,网易邮箱未加密密码泄露)
4.token有效期
5.单一平台的单点登陆,登录IP异常
6.用户的权限判断
7.天假验证码机制,防止爆破和批量注册

七.AJAX
1.页面不刷新
2.体验更好
3.传输数据更少
4.APP/网站通用

八.热部署
加入dev tools

九.图片上传
通过multipartfile将图片的二进制流上传

十.将图片上传服务器
冗余备份,统一访问,CDN缓存同步,多机器并发



*---------------------------------Note-------------------------------------*
1.使用yaml配置了MySQL,等到项目全部部署后,再尝试使用Druid数据源部署
*==Mybatis==*:
(1)注解方式:首先在pom.xml中引入依赖mysql-connector-java,mybatis-spring-boot-starter
(2)在application中添加配置并引入配置文件mybatis-config.xml(开启驼峰命名法,这个bug找了一个晚上),定义model
定义接口mapper(注意head_url这类字段名的书写)
(3)在InitDataBaseTests中添加测试用例
(4)也可以使用注解+xml方式:创建NewsDAO.java,在相同包目录下定义同名的xml(即resource下创建相同的包名,并添加xml文件)

因为xml配置mybatis出现问题,所以另寻新路:
Mybatis配置动态SQL:
在dao的同目录下,配置相应的SQLProvider
并在dao中对应的方法上,注明SelectProvider所属的类,以及对应的方法名称

*==Velocity==*
最终还是选择用了velocity,springboot1.4.0之后就不能使用velocity了,所以将springboot版本换为1.3.5
然后导入velocity jar包,在application.yml中配置velocity,引入velocity的toolbox


*==MD5加密算法==*
优点:
1、压缩性:任意长度的数据，算出的MD5值长度都是固定的。
2、容易计算:从原数据计算出MD5值很容易(算法简单)
3、抗修改性:对原数据进行任何改动，哪怕只修改1个字节，所得到的MD5值都有很大区别。
4、强抗碰撞:已知原数据和其MD5值，想找到一个具有相同MD5值的数据（即伪造数据）是非常困难的。
5、不可逆计算反推


*-----------------------------------难题--------------------------------*
1.Mybatis xml配置问题:这个问题找了很久...知道真相的我眼泪掉下来
在resources目录下添加xml,可是target文件夹dao中并没有xml文件,所以导致springboot运行
起来,找不到对应的dao中的方法.所以把xml配置文件放在target文件夹下对应包中就可以了
2.Mybatis动态SQL不具有ORDERBY DESC和LIMIT ,只有默认的ASC
  要想使用desc、limit,难道只能用xml吗？？？




*------------------------------------亮点--------------------------------*
1.在initDatabase中添加测试用例
2.使用Java MD5加密算法,并且自己实现了MD5工具类
3.图片存储CDN中

*----------------------------------待完成----------------------------------*
1.使用统一的数据格式 例如json
2.加入人机验证





