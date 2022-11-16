

day24

## 1.ServletContext

### 1.补充：MIME 类型

MIME (Multipurpose Internet Mail Extensions) 是描述消息内容类型的标准，用来表示文档、文件或字节流的性质和格式。

MIME 消息能包含文本、图像、音频、视频以及其他应用程序专用的数据。

浏览器通常使用 MIME 类型（而不是文件扩展名）来确定如何处理URL，因此 We b服务器在响应头中添加正确的 MIME 类型非常重要。如果配置不正确，浏览器可能会无法解析文件内容，网站将无法正常工作，并且下载的文件也会被错误处理。

#### 语法

MIME 类型通用结构：

```
type/subtype
```

[MIME 类型 | 菜鸟教程 (runoob.com)](https://www.runoob.com/http/mime-types.html)



## 2.request

```
/*
    请求转发：一种服务器内部资源的跳转方式  对于客户端浏览器而言只发送了一次请求
        示例：用户在客户端浏览器请求A资源，A资源无法处理，将请求交给了服务器内部的B资源，这个过程就称为请求转发
        案例需求：
            用户请求request07，request07处理不了，将请求交给request08
        实现：request.getRequestDispatcher("转发地址").forward(request,response);
        特点：
            1.请求转发属于服务器内部资源跳转，对于客户端浏览器而言，只发送了一次请求，请求地址栏不会发送改变，请求信息不会丢失
            2.请求转发只能转发到本项目中的资源,不能转发到外部项目资源
            3.请求转发可以访问到WEB-INF里面的资源     【浏览器直接访问不行的】
            4.请求转发的路径一般建议写相对路径,不建议写绝对路径  【原因是：会将转发地址直接放在当前web应用虚拟路径下进行查找】
    作为域对象使用：
        作用范围：一次请求间有效  可以在一次请求中实现数据共享
        使用场景：只能用在请求转发中
        API：
            存：setAttribute(String name,Object value)
            取：getAttribute(String name)
            删：removeAttribute(String name)
 */
```

```
/*
    请求转发：一种服务器内部资源的跳转方式  对于客户端浏览器而言只发送了一次请求
        示例：用户在客户端浏览器请求A资源，A资源无法处理，将请求交给了服务器内部的B资源，这个过程就称为请求转发
        案例需求：
            用户请求request07，request07处理不了，将请求交给request08
        实现：
 */
```

![1662347510588](C:\Users\Jasper\AppData\Roaming\Typora\typora-user-images\1662347510588.png)

```
String userAgent = request.getHeader("user-agent");
```



## 3.response

```
/*
    操作响应头：服务器告诉浏览器要做什么
        API：setHeader(String name,String value)
        常用的响应头：
            location：设置重定向的地址
        需求：当浏览器请求服务器上response02，response02无法处理，告诉浏览器需要请求response03进行处理
        重定向概念：客户端浏览器根据服务器要求 重新发起一次新的请求
        重定向实现：response.sendRedirect(重定向的地址);
        特点：
            1. 重定向发生在客户端浏览器，有两次请求
            2. 重定向的地址栏路径改变
            3. 重定向可以访问项目内部资源【相对路径】，也可以访问项目外部资源【写绝对路径(带域名/ip地址的, 如果是同一个项目里面的,域名/ip地址可以省略)】
            4. 重定向不能重定向到WEB-INF下的资源   【WEB-INF下的资源 浏览器不能直接访问】
            5. 把数据存到request里面, 重定向不可用 【重定向是2次请求】

 */
```

![1662347519161](C:\Users\Jasper\AppData\Roaming\Typora\typora-user-images\1662347519161.png)

```
response.setHeader("refresh","秒数;url=跳转的路径"); //几秒之后跳转到指定的路径上
response.setHeader("refresh","5; url=http://www.baidu.com");
```



## 4.转发（forward）和重定向(redirect)的区别？

```
转发（forward）和重定向(redirect)的区别？
            1.转发属于服务器内部资源跳转  重定向属于客户端浏览器发起一次新的请求
            2.转发发送一次请求 地址栏不发生改变 请求数据也不会丢失；重定向发送2次请求，地址栏会发送改变 请求数据会丢失
            3.转发只能访问项目内的资源；重定向既可以访问项目内的资源，也可以访问项目外的资源
            4.转发可以访问WEB-INF下的资源；重定向不可以
            5.转发建议使用相对路径，重定向建议使用绝对路径【访问项目外的使用绝对路径 项目内部资源使用相对路径】
```



## 5.关闭冲突的tomcat端口号8080

![1662469935637](C:\Users\Jasper\AppData\Roaming\Typora\typora-user-images\1662469935637.png)



## 6.创建新数据库时在URL加时区

![1662470026326](C:\Users\Jasper\AppData\Roaming\Typora\typora-user-images\1662470026326.png)



day25

## 7.Cookie的概念和作用

![1662548747961](C:\Users\Jasper\AppData\Roaming\Typora\typora-user-images\1662548747961.png)

### 7.1案例-记录用户各自的上次访问时间



![1662548778034](C:\Users\Jasper\AppData\Roaming\Typora\typora-user-images\1662548778034.png)



## 8.Session钝化、活化

![1662550500790](C:\Users\Jasper\AppData\Roaming\Typora\typora-user-images\1662550500790.png)

