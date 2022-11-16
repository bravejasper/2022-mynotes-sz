### 1.问题：Collection接口

```
//  把集合中的元素，存储到数组中
        //  `public Object[] toArray()`: 把集合中的元素，存储到数组中
        Object[] arr = col.toArray();
        System.out.println(Arrays.toString(arr));
```

```
Arrays.toString(arr)
这个不理解
```

## 二、迭代

### 1.概念

- 概述：迭代即Collection集合元素的通用获取方式。**在取元素之前先要判断集合中有没有元素，如果有，就把这个元素取出来，继续再判断，如果还有就再取出来。一直把集合中的所有元素全部取出。**这种取出方式专业术语称为迭代。
- 迭代的步骤:
  - 获取迭代器对象
  - 使用迭代器对象判断集合中是否有元素可以取出
  - 如果有元素可以取出,就直接取出来该元素,如果没有元素可以取出,就结束迭代

### 2.迭代器的使用

#### Iterator迭代器对象的常用方法

- `public boolean hasNext()`:如果仍有元素可以迭代，则返回 true。
- `public E next()`:返回迭代的下一个元素。
- `void remove() `删除当前迭代出来的元素

```
//Iterator<数据类型> 迭代器名=集合名.iterator();
Iterator<String> a=coll.iterator();
while (a.hasNext()){
    String e2=a.next();
    System.out.println("e2"+e2);
}
```

## 三、增强for

概述:  增强for循环(foreach循环)，是JDK1.5以后出来的一个高级for循环，专门用来遍历数组和Collection集合

原理:  内部基于Iterator迭代器实现，所以在遍历的过程中，不能对集合中的元素进行增删操作，否则抛出ConcurrentModificationException并发修改异常 

格式:

```java
for(数据类型 变量名 :  数组名\集合名){
    
}

 // 快捷键: 数组名.for
        for (String e : arr) {
            System.out.println("e:" + e);
        }
```

## 四、引用类型数据转换

```
引用类型转换分类
              向上转型:子类类型向父类类型转换的过程，称为向上转型。这个过程是默认的。
                       格式:父类类型  变量名 = new 子类类型()  或 子类对象引用;
                        Dog d = new Dog();
                        Animal anl = new Dog();
                        Animal anl = d;
              向下转型:父类类型向子类类型转换的过程，称为向下转型，这个过程需要强制执行。
                    格式:子类类型 变量名 = (子类类型) 父类类型变量;
                    要求: 父类类型的变量必须指向的是前面子类类型的对象

              类型转换判断:  使用instanceof关键字
                   格式: 变量名 instanceof 数据类型
                   执行:
                        如果变量指向的对象是属于后面的数据类型,那就返回true
                        如果变量指向的对象不属于后面的数据类型,那就返回false
                        
                         */
        // 向上转型: 父类类型的引用指向子类类型的对象
        Animal anl1 = new Dog();
        anl1.eat();
        // System.out.println(anl1.num);// 编译报错
        // anl1.lookHome();// 编译报错

        //Dog d = new Dog();
        //Animal anl2 = d;

        System.out.println("=======");
        // 改变anl1指向的对象
        anl1 = new Cat();

        // 向下转型: 子类类型向父类类型转换的过程
        if (anl1 instanceof Dog){
            Dog dog = (Dog) anl1;
            System.out.println(dog.num);
            dog.lookHome();
        }
```

