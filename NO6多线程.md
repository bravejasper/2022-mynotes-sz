## 一、Thread类

```
概述:
   java.lang.Thread类代表**线程**，所有的线程对象都必须是Thread类或其子类的实例
   每个线程的作用是完成一定的任务，实际上就是执行一段程序流即一段顺序执行的代码
   Java使用线程执行体来代表这段程序流，在Tread线程中，使用run()方法代表线程执行体
构造方法
    public Thread():创建一个新的线程对象,默认名称
    public Thread(String name):创建一个指定名字的新的线程对象    
       
    public Thread(Runnable target):创建一个带有指定任务的线程对象,通过参数Runnable指定任务
    public Thread(Runnable target,String name):创建一个带有指定任务的线程对象并指定线程名字
常用方法
    public String getName():获取当前线程名称
    public void start():导致此线程开始执行; Java虚拟机调用此线程的run方法
    public void run():此线程要执行的任务在此处定义代码
    public static void sleep(long millis):使当前正在执行的线程以指定的毫秒数暂停执行
    public static Thread currentThread()  :返回对当前正在执行的线程对象的引用
通过Thread类的api,可以指定创建线程有2种方式:
1.通过继承Thread类的方式
2.通过实现Runnable接口的方式
```





## 二、创建并启动多线程方式的区别

### 1.通过继承

```
创建一个对象就在堆区开辟一块空间，并放入一个run()方法。只是这4个方法是一样的。每个人使用1个任务对象。
mt1\mt2\mt3\mt4既是任务也是对象，如果有一个出错很容易影响全部。
```

![1659534615399](typora-user-images\1659534615399.png)

### 2.通过实现

```
在内存中创建一个mr对象，并创建4个线程对象，然后将mr任务传入4个线程对象中。4个对象使用同一个任务对象。
mr是对象，t1\t2\t3\t4仅是任务，解耦度高，不容易出错。
```

![1659534419239](typora-user-images\1659534419239.png)