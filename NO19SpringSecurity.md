### 1.修改日志级别

​		默认打印的springsecurity的日志级别是 info级别，如果想观察到更详细的日志信息，可以在**application.yml** 里面修改日志的打印级别。如果info看不到日志，可以尝试再设置低一些级别：

```
比如： debug 或者  trace 级别
日志级别：fatal严重错误>error异常>warn警告>info>debug>trace跟踪
```

一般开发阶段设debug或trace方便查看调试或跟踪

### 2.SpringSecurity快速入门

1.创建SpringInitializr模块（项目），命名springsecurity01_quickstart（注意都要小写！）

2.修改（添加）如下pom.xml

```java
<parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.6.0</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <properties>
        <java.version>1.8</java.version>
    </properties>
    <dependencies>
<!--web起步依赖 stater：起步依赖 封装了spring-webmvc、servlet-api、jackson-databind、tomcat-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!--SpringSecurity起步依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!--lombok-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>

        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.67</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
```

如果是复制粘贴pom.xml，将 <artifactId>的id名改为项目名

```java
 <groupId>com.itheima</groupId>
    <artifactId>springsecurity01_quickstart</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>springsecurity01_quickstart</name>
    <description>Demo project for Spring Boot</description>
```

3.新建com.itheima.controller.Controller01.java

```java
@RestController
public class Controller01 {
@RequestMapping("/show")
    public String show(){
    System.out.println("调用了Controller01的show方法。。。");
    return "Hello SpringSecurity";
}
}
```

4.将修改application.yml

```
#如果需要修改默认的登录用户名和密码，即可输入如下内容，不改就可以留着空文件
spring:
  security:
    user:
      name: admin
      password: 666
```

5.点击Springsecurity01QuickstartApplication.java左边的绿色三角运行

6.访问http://localhost:8080/show

7.默认用户名是：user，默认密码在idea控制台获取



### 3.

![1663755070779](typora-user-images\1663755070779.png)