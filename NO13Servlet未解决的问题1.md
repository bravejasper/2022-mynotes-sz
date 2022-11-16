问题：

#### 问题1：字节输入输出流

获取字节输入流不太明白，需要看一下以前的字节输入输出流内容

![1662193934284](C:\Users\Jasper\AppData\Roaming\Typora\typora-user-images\1662193934284.png)



#### 问题2：为什么先回滚再异常

day24 登录注册案例：LoginServlet.java

```
 //3.判断用户是否为null
            User user1 = userDao.select(user);
            if (user1==null){
                //3.1为null:登录失败
                response.getWriter().print("登录失败！");
            }else {
                //3.2不为null：登录成功
                response.getWriter().print("登录成功！");
            }
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
            response.getWriter().print("服务器异常！");
        }finally {
            is.close();
            sqlSession.close();
        }
```



#### 问题3：已解决

```
解决方法：
本来正常的mybatis程序：突然报这个错java.lang.IllegalArgumentException: Parameter Maps collection does not contain value for而且报错

总结下原因：mapper.xml 中IDE提示有parameterMap属性而这个属性已经早废弃了且应该使用parameterType，如果不小心写成parameterMap那就中招了！
```

[Mybatis之parameterMap和resultMap使用详解_空圆小生的博客-CSDN博客_mybatis parametermap](https://blog.csdn.net/weixin_45680962/article/details/107213638?csdn_share_tail={"type"%3A"blog"%2C"rType"%3A"article"%2C"rId"%3A"107213638"%2C"source"%3A"weixin_59022513"})

```
报错信息：
java.lang.IllegalArgumentException: Parameter Maps collection does not contain value for com.itheima.dao.UserDao.user

org.apache.ibatis.builder.IncompleteElementException: Could not find parameter map com.itheima.dao.UserDao.user
```

![1662469399243](C:\Users\Jasper\AppData\Roaming\Typora\typora-user-images\1662469399243.png)