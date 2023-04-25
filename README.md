# JdbcPool
数据库连接池
## 使用方法

### 一、maven中添加标签导入依赖

```xml
<dependency>
    <groupId>com.lee</groupId>
    <artifactId>mysql-pool</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 二、在根目录下新建资源文件

*   资源文件名：jdbc-config.properties
*   内容要求：
    1.  driver:注册驱动
    2.  url:数据库链接
    3.  user:数据库用户名
    4.  password:数据库密码
    5.  length:连接池中最大连接数,默认值为10，可不填
*   代码：
    ```properties
    #必填
    driver=
    url=
    user=
    password=
    #选填，连接池中最大连接数，默认值为10
    length=
    ```

### 三、具体方法调用

*   提前加载连接池，一般整个程序只用使用一次
    ```java
    Class.forName("com.lee.JdbcPool");
    ```

*   获取连接
    ```java
    Connection con = JdbcPool.getConnection();
    ```

*   把单个连接放回连接池
    ```java
    JdbcPool.closeConnection(con);
    ```

*   关闭连接池
    ```java
    JdbcPool.closePool();
    ```
