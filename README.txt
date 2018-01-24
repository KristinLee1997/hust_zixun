**************************************理工资讯*****************************************
SpringBoot+MySQL+MyBatis+Yaml+Freemarker+SpringMVC

*--------------------------Note-----------------------------------*:
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

哈哈哈 找到xml配置mybatis出错的原因了:
在resources目录下添加xml,可是target文件夹dao中并没有xml文件,所以导致springboot运行
起来,找不到对应的dao中的方法

*==Freemarker==*:
freemarker获取当前时间${.now},时间格式化${vo.news.createdDate?string("yyyy年MM月dd日")}
freemarker list循环索引号<#list vos as vo>${vs_index}</#list>



*-----------------------------------难题--------------------------------*
1.Mybatis xml配置问题
2.Mybatis动态SQL不具有ORDERBY DESC和LIMIT ,只有默认的ASC
  要想使用desc、limit,难道只能用xml吗？？？




*------------------------------------亮点--------------------------------*
1.在initDatabase中添加测试用例



*----------------------------------未完成----------------------------------*
1.首页时间分块

