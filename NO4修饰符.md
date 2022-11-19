## 一、静态修饰符

### 1.概述

```
static是静态修饰符，表示静态的意思,可以修饰类中成员(成员方法、成员变量、代码块等)。

static修饰的内容是与对象无关的，即可以不通过对象操作。意思是不管有没有在堆内存创建空间，static修饰的内容都是存在的，存在在方法区。
```



![1659265552336](typora-user-images\1659265552336.png)



### 2.修饰成员变量

```
静态的内容推荐使用类名调用
```



```java
public class Chinese {
    String name;
    static String country;
}

public class Test {
    public static void main(String[] args) {
        //定义一个中国人类
        //        属性:姓名,国籍。

        Chinese c = new Chinese();
        Chinese c2 = new Chinese();
        System.out.println(c.name + "==" + c.country);
        System.out.println(c2.name + "==" + c2.country);
        System.out.println("--------");

        c.name="张三";
        c.country="CHINA";
        System.out.println(c.name + "==" + c.country);
        System.out.println(c2.name + "==" + c2.country);
        System.out.println("--------");

        //静态的内容推荐使用类名调用。
        Chinese.country="中国";
        System.out.println(c.name + "==" + c.country);
        System.out.println(c2.name + "==" + c2.country);
    }
```



### 3.注意事项

- 1.静态方法可以直接访问静态变量和静态方法。

- 2.静态方法不能直接访问普通成员变量或成员方法。
  因为：静态内容优先于非静态内容存在。在调用静态的方法的时候,不能保证对象是一定存在的。而非静态的内容,必须通过对象来调用。

- 3.成员方法可以直接访问类变量或静态方法。

- 4.静态方法中，不能使用this关键字。

  因为：调用当前方法的可能不是对象,而是类。不能用this代表类，this就是用来代表对象的。

```Java
public class Test2 {
    public static void main(String[] args) {
    }

    //非静态内容
    int num1 = 10;

    public void method1() {
        System.out.println("method1");
    }

    //静态内容
    static int num2 = 10;

    public static void method2() {
        System.out.println("static method2");
    }


    //静态的方法
    public static void staticShow() {
        //静态方法可以直接访问静态变量和静态方法。
        System.out.println(num2);
        method2();

        //静态方法 不能 直接  访问普通成员变量或成员方法。
        //为什么？
        //静态内容  优先于非静态内容存在。
        //在调用静态的方法的时候,不能保证对象是一定存在的。而非静态的内容,必须通过对象来调用。

        //System.out.println(num1);//error
        //method1();//error

        //静态方法中，不能使用this关键字。
        //为什么?因为调用当前方法的可能不是对象,而是类。  难道要用this代表类。 this就是用来代表对象的。
        //this.method1();//error
    }

    //非静态的方法
    public void show() {
        //成员方法可以直接访问类变量或静态方法。
        System.out.println(num2);
        method2();
        //非静态方法  能 直接  访问普通成员变量或成员方法。
        //为什么？
        //因为当前的方法是非静态方法,必须通过对象来调用。
        System.out.println(this.num1);
        this.method1();
    }
```

### 4.static关键字的应用

```
public class MathUtils {
    //为了不让别人创建对象 将默认构造方法手动定义，并私有化
    private MathUtils(){
	}
    }
    
    MathUtils a=new MathUtils();//error 创建对象报错
```



## 二、接口

### 1.定义

#### 1.1定义格式

```
public interface 接口名称 {
    // 静态常量
    // 抽象方法
    // 默认方法
    // 静态方法
}

```

#### 1.2定义规则

```
静态常量格式:public static final 数据类型 变量名 = 值;    	//public static final可以省略
抽象方法格式：public abstract 返回值 方法名(参数列表);		//abstract 可以省略，供实现类重写
默认方法格式：public default 返回值 方法名(参数列表) { //执行语句  }	//default 不可省略，供实现类调用或者实现类重写
静态方法格式：public  static 返回值 方法名(参数列表) { //执行语句  }	//static 不可省略

```

#### 1.3代码

```
public interface MyInter {

    // 常量:使用public static final修饰,而这三个修饰符可以省略不写
    public static final int NUM = 10;

    // 抽象方法:使用public abstract修饰,而这两个修饰符可以省略不写
    public abstract void method1();

    // 默认方法: 使用public default修饰,public可以省略不写,default不可以省略
    public default void method2(){
        System.out.println("MyInter 默认方法method2");
    }

    // 静态方法:使用public static修饰,public可以省略不写,static不可以省略
    public static void method3(){
        System.out.println("MyInter 静态方法method3");
    }
}

public class Test {
    public static void main(String[] args) {
        System.out.println(MyInter.NUM);// 10
        // MyInter.NUM = 20;// 编译报错
    }
}
```

### 2.类与接口的实现

#### 2.1介绍

```
类与接口的关系为实现关系，即类实现接口，该类可以称为接口的实现类。

实现的动作类似继承，只是关键字不同，实现使用 implements关键字。
```

#### 2.2实现格式 [ ]表示可选操作

```
public [abstract] class 类名 [extends 父类名] [implements 接口名1,接口名2,接口名3... ]{
   
}
```

#### 2.3实现情况分类

```
单实现

多实现

继承并实现
```

#### 2.4实现格式:

```
单实现
public class 实现类名 implements 接口名{}

多实现
public class 实现类名 implements 接口名1,接口名2,接口名3,...{}

先继承后实现
public class 实现类名 extends 父类名 implements 接口名1,接口名2,接口名3,...{}
```



### 3.接口中成员的访问特点  

```
接口中成员的访问特点:
    接口中常量: 主要供接口名直接访问,也可以被实现类继承
    接口中抽象方法:就是供实现类重写的
    接口中默认方法:可以供实现类对象直接调用,或者在实现类中重写
    接口中静态方法:只供接口名直接访问,不可以被实现类继承
    接口中私有方法:只能在接口内部方法中调用,不可以被实现类继承
```



案例:

```java
public interface IA {
    // 常量: 使用public static final修饰
    public static final int NUM = 10;

    // 抽象方法: 使用public abstract修饰
    public abstract void method1();

    // 默认方法: 使用public default修饰
    public default void method2(){
        System.out.println("IA接口的默认方法method2");
        // method4();
    }

    // 静态方法: 使用public static修饰
    public static void method3(){
        System.out.println("IA接口的静态方法method3");
    }

    // 私有方法: 使用private修饰
     //private void method4(){}
     //private static void method5(){}
}



public class Imp implements IA {
    @Override
    public void method1() {
        System.out.println("实现类重写接口中的抽象方法method1");
    }

    @Override
    public void method2() {
        System.out.println("实现类重写接口中的默认方法method2");
    }
}



public class Test {
    public static void main(String[] args) {
        /*
            接口中成员的访问特点:
                接口中常量: 主要供接口名直接访问,也可以被实现类继承
                接口中抽象方法:就是供实现类重写的
                接口中默认方法:可以供实现类对象直接调用,或者在实现类中重写
                接口中静态方法:只供接口名直接访问,不可以被实现类继承
                接口中私有方法:只能在接口内部方法中调用,不可以被实现类继承

         */
        // 访问接口中的常量
        System.out.println(IA.NUM);// 10
        System.out.println(Imp.NUM);// 10

        // 访问接口中的抽象方法
        Imp imp = new Imp();
        imp.method1();

        // 访问接口中的默认方法
        imp.method2();

        // 访问接口中的静态方法
        IA.method3();
    }
}

```

### 4.抽象类和接口的练习(有问题)

#### 4.1需求:

通过实例进行分析和代码演示**抽象类和接口**的用法。

1、举例：

​	     犬：  抽象父类

​				行为：吼叫；吃饭；  

​	    缉毒犬：继承犬类,实现缉毒的接口

​				 行为：吼叫；吃饭；缉毒；

​        缉毒的接口:  缉毒的功能

**抽象类: 如果同一群事物共同的功能就定义在抽象类中**

- ```
  **（抽象类不是就可以通过抽象方法重写方法来重写功能吗，为什么还要接口）**
  ```

  

**接口: 如果不同类型的事物共同拥有的功能就定义在接口中,让这些事物的类实现该接口**

#### 4.2思路

```
父类:子类共同属性和行为。

抽象类:有子类实现各不相同的方法。

接口:事物可能具备的方法。
```

#### 4.3分析   

```
 吼叫和吃饭是所有狗一定具备的功能，应该定义在父类中(父类中抽取了子类共有的属性和行为)

 缉毒是狗可能拥有的功能，属于狗可能拥有的额外功能，应该定义到接口中。
```

