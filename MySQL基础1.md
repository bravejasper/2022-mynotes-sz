### DDL操作数据库

- 语法:

  ```sql
  创建数据库: create database 数据库名 [character set 字符编码][collate 校对规则];-- []表示可选
  查看数据库:
  	查看所有数据库: show databases;
  	查看数据库结构: show create database 数据库名;
  删除数据库: drop database 数据库名;
  修改数据库: alter database 数据库名 character set 字符编码; 注意:1.数据库名不能修改 2.只能修改数据库的编码,是utf8,不是utf-8
  其他操作:
  	切换数据库:  use 数据库名;
  	查看正在使用的数据库: select database();
  ```

- 案例:

  ```sql
  -- 练习:创建一个名字为day17_1的数据库,默认编码utf-8
  create database day17_1;
  -- 练习:创建一个名字为day17_2的数据库,指定编码为gbk
  create database day17_2 character set gbk;
  -- 练习: 查询所有的校对规则
  show collation;
  -- 练习:查询所有的数据库
  show databases;
  -- 练习:查询day17_1和day17_2数据库的定义结构
  show create database day17_1;
  show create database day17_2;
  -- 练习:删除day17_1数据库
  drop database day17_1;
  -- 练习:修改day17_2数据库的编码为utf8
  alter database day17_2 character set utf8;-- 注意这里是utf8,不是utf-8
  -- 练习:切换到day17_1数据库
  use day17_1;
  -- 练习:查询正在使用的数据库
  select database();
  
  ```

  

### 第一章-DDL操作表

#### 1.1 创建表【重点】

##### 1.语法

```sql
create table 表名(
   字段名 字段类型 [约束],
   字段名 字段类型 [约束],
   ........
   字段名 字段类型 [约束]
);
-- 注意: 小括号中最后定义的字段后面不要加逗号
```

##### 2. 类型 

![1670407193522](Typoraphoto/1670407193522.png)

1. **整型  一般使用int 或者bigint**

2. 浮点/双精度型  

   - **默认的范围  float或者double**
   - 指定范围     float(M,D)  eg: float(4,2)  表达的范围:  -99.99~99.99

3. 字符串

   - 固定长度  char(n)  	eg: char(20), 最大能存放20个字符.  ‘aaa      ’, 还是占20个字符的空间
   - **可变长度  varchar(n) eg:varchar(20), 最大能存放20个字符.  ‘aaa’, 占3个字符的空间**

   > 一般使用varchar(n) 节省空间;  如果长度(eg:身份证)是固定的话 可以使用char(n) 性能高一点 

4. 关于大文件

   - 一般在数据库里面很少存文件的内容, 一般存文件的路径
   - 一般不使用二进制存, 使用varchar(n)存文件的路径

5. 日期

   - **DATE         只有日期**
   - **DATETIME 日期和时间**

##### 3. 约束  

- 即规则,规矩 限制;  
- 作用：保证用户插入的数据保存到数据库中是符合规范的

| 约束 | 约束关键字  |
| ---- | ----------- |
| 主键 | primary key |
| 唯一 | unique      |
| 非空 | not null    |

**约束种类:** 

- **not null: 非空** ;  eg:  username varchar(40)  not null    username这个字段不能为空，必须要有数据，否则会添加失败
- **unique:唯一约束**, 后面的数据不能和前面重复;  eg: cardNo varchar(18) unique;    cardNo字段不能出现重复的数据

- **primary key；主键约束(非空+唯一)**;    一般用在表的id列上面.  每一张表基本上都有id列的, id列作为记录的唯一标识的 
- **auto_increment: ==自动增长**,必须是设置了primary key之后,才可以使用auto_increment==（它会自增，所以可以设置数据为null，也并不会冲突）
- `id int  primary key   auto_increment` id不需要我们自己维护了, 插入数据的时候直接插入null, 自动的增长进行填充进去, 避免重复了.  

注意:

1. 先设置了primary key 再能设置auto_increment
2. 只有当设置了auto_increment 才可以插入null 自己维护 否则插入null,会报错
3. 一般开发中id主键会设置为自动增长

##### 4.练习

- 创建一张学生表(含有id字段,姓名字段,性别字段. id为主键自动增长)

  ```sql
  create table student(
  	id int primary key auto_increment,
  	name varchar(70),
  	sex varchar(13)
  );
  
  ```

  

#### 1.2 查看表【了解】

- 语法:

  ```java
  查看所有的表: show tables;
  查看表的定义结构: desc  表名；
  ```

- 案例:

  ```sql
  -- 练习:查看day17_1数据库中所有的表
  show tables;
  -- 练习:查看day17_1数据库中student表的结构
  desc student;
  
  ```

  

#### 1.3 修改表【掌握】

##### 1.语法

- 增加一列:  `alter table 表名 add 字段名 字段类型 字段约束;`
- 修改列的类型约束:`alter table 表名 modify 字段名 字段类型 字段约束;`
- 修改列的名称，类型，约束:`alter table 表名 change 旧字段名  新列名 字段类型 字段约束;`
- 删除一列: `alter table 表名 drop 字段名;`
- 修改表名: `rename table 旧表名 to 新表名;`

##### 2.练习

```sql
-- 练习:给学生表增加一个grade字段
alter table student add grade varchar(40);

-- 练习:给学生表的sex字段改成字符串类型 
alter table student modify sex varchar(23);

-- 练习:给学生表的grade字段修改成class字段
alter table student change grade class varchar(20);

-- 练习:把class字段删除
alter table student drop class;

-- 练习:把学生表修改成老师表(了解)
rename table student to teacher;

```



#### 1.4 删除表【掌握】

- 语法:  `drop table 表名;`

- 案例:

  ```sql
  -- 练习: 删除teacher表
  drop table teacher;
  ```

  

### 第二章-DML操作表记录-增删改【重点】

- 准备工作: 创建一张商品表(商品id,商品名称,商品价格,商品数量.) 

```sql
create table product(
	pid int primary key auto_increment,  -- 只有设置了auto_increment id列才可以赋值为null
	pname varchar(40) not null,
	price double,
	num int
);
```

#### 2.1 插入记录

- 语法:

  ```java
  插入指定列: insert into 表名(列名,列名,...)  values(值,值,...);
  插入所有列: insert into 表名 values(值,值,...);
  -- 注意:
  -- - 字段名与值的类型、个数、顺序要一一对应。
  -- - 值不要超出列定义的长度。
  -- - 插入的日期和字符串，使用引号括起来。 
  -- - 插入特定的列:没有赋值的列,系统自动赋为null(前提是当前列没有设置not null 约束,否则会报错)
  -- - 默认所有列插入,values里面必须给表中每一个字段赋值,一般主键给一个null
  ```

  

- 案例:

  ```sql
  -- 练习: 指定pname,price列插入记录
  insert into product(pname,price) values('Mac',11000);
  
  -- 练习: 指定price列插入记录---错误的需求
  insert into product(price) values(20000);-- 报错
  
  -- 练习: 指定所有列插入记录
  insert into product values(null,"MacBook",20000,3);
  
  ```

- 数据准备:  **批量插入记录**

  ```sql
  方式一:
  insert into product values(null,'苹果电脑',18000.0,10);
  insert into product values(null,'华为5G手机',30000,20);
  insert into product values(null,'小米手机',1800,30);
  insert into product values(null,'iPhonex',8000,10);
  insert into product values(null,'苹果电脑',8000,100);
  insert into product values(null,'iPhone7',6000,200);
  insert into product values(null,'iPhone6s',4000,1000);
  insert into product values(null,'iPhone6',3500,100);
  insert into product values(null,'iPhone5s',3000,100);
  insert into product values(null,'方便面',4.5,1000);
  insert into product values(null,'咖啡',11,200); 
  insert into product values(null,'矿泉水',3,500);
  
  方式二:
  insert into product values(null,'苹果电脑',18000.0,10),
  (null,'华为5G手机',30000,20),
  (null,'小米手机',1800,30),
  (null,'iPhonex',8000,10),
  (null,'苹果电脑',8000,100),
  (null,'iPhone7',6000,200),
  (null,'iPhone6s',4000,1000),
  (null,'iPhone6',3500,100),
  (null,'iPhone5s',3000,100),
  (null,'方便面',4.5,1000),
  (null,'咖啡',11,200),
  (null,'矿泉水',3,500);
  ```

  

- 命令行插入中文数据报错:-----了解

 ![img](../../heimastudy/studymaterial/javaseAdvance/day14_MySql%E5%9F%BA%E7%A1%80/01_%E7%AC%94%E8%AE%B0/img/tu_3.png)

- 关闭服务,  net stop MySql
- 在数据库软件的安装目录下面, 修改配置文件 my.ini中客户端的编码为gbk

![1535858746067](../../heimastudy/studymaterial/javaseAdvance/day14_MySql%E5%9F%BA%E7%A1%80/01_%E7%AC%94%E8%AE%B0/img/1535858746067.png)

- 重新打开命令行,开启服务, net start MySql



#### 2.2 更新记录

- 语法:  `update  表名 set 字段名=值,字段名=值,... where 条件; `

- 练习

  - 将所有商品的价格修改为5000元

  - 将商品名是Mac的价格修改为18000元

  - 将商品名是Mac的价格修改为17000,数量修改为5

  - 将商品名是方便面的商品的价格在原有基础上增加2元

    ```sql
    --   - 将所有商品的价格修改为5000元
    update product set price = 5000;
    
    --   - 将商品名是Mac的价格修改为18000元
    update product set price = 18000 where pname='Mac';
    
    --   - 将商品名是Mac的价格修改为17000,数量修改为6
    update product set price = 17000,num=6 where pname='Mac';
    
    --   - 将商品名是方便面的商品的价格在原有基础上增加2元
    update product set price = price + 2  where pname='方便面';
    
    ```

    





#### 2.3 删除记录

- 语法

  - 方式一:  `delete from 表名 where 条件;`
  - 方式二:  `truncate table 表名;`

  

- 练习

  - 删除表中名称为’Mac’的记录

  - 删除价格小于5001的商品记录

  - 删除表中的所有记录

    ```sql
    --   - 删除表中名称为’Mac’的记录
    delete from product where pname='Mac';
    
    --   - 删除价格小于5001的商品记录
    delete from product where price < 5001;
    
    --   - 删除表中的所有记录
    delete from product;-- 一条一条记录删除,再添加记录,id是从之前最后一条记录的id+1开始
    truncate table product;-- 直接删除整张表,然后再创建一张一模一样的新的空表,再添加记录,id就是从1开始
    
    ```

    

- 注意:

  delete  和truncate区别【面试题】  

  - DELETE 删除表中的数据，表结构还在;  删除记录后再新增数据，id会续上。（ 删除的记录可以找回）
  - TRUNCATE 删除是把表直接DROP掉，然后再创建一个同样的新表(空)。删除的记录不可以找回，id不会续上，会重新从1开始。

  工作里面的删除 

  - 物理删除: 真正的删除了, 数据不在, 使用delete就属于物理删除
  - 逻辑删除: 没有真正的删除, 数据还在. 搞一个标记, 其实逻辑删除是更新  eg: state字段   1 启用   0禁用
  - 工作里面一般使用逻辑删除用的多 