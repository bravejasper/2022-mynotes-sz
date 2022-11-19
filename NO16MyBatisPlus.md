day38MyBatisPlus

### 1.MyBatisPlus的CRUD操作问题问题问题？？？

![1663037906204](typora-user-images\1663037906204.png)



![1663037691585](typora-user-images\1663037691585.png)

应用：瑞吉项目一

使用SpringBoot+MyBatisPlus开发项目
		基本的增伤改查功能service和dao层都无需写代码
		Controller---->Service---->Dao
						Service   			Dao
						save				    insert
						removeById 	 deleteById
						update			   updateById
						list				 	  selectList
						getById			  selectById
						getOne			  selectOne
						page				  selectPage



问题1：因为serializable是序列化，所以要加L，但是为什么？**

![1663037717656](typora-user-images\1663037717656.png)

![1663037844385](C:\Users\Jasper\AppData\Roaming\Typora\typora-user-images\1663037844385.png)



### 2.修改@Autowired警告

```
@Autowired
    private UserDao userDao;

//增加
    @Test
    public void testInsert(){
        User user=new User();  //不修改的话，User会爆红，但是运行并不会有问题
        user.setName("智慧化");
        }
```

![1663047729824](typora-user-images\1663047729824.png)

