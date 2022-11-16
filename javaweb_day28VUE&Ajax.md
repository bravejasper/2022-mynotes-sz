## VUE&Element

**今日目标：**

> * 能够使用VUE中常用指令和插值表达式
> * 能够使用VUE生命周期函数 mounted
> * 能够进行简单的 Element 页面修改
> * 能够完成查询所有功能
> * 能够完成添加功能

## 一、VUE

### 1.1  概述

==Vue 是一套前端框架，免除原生JavaScript中的DOM操作，简化书写。==

我们之前也学习过后端的框架 `Mybatis` ，`Mybatis` 是用来简化 `jdbc` 代码编写的；而 `VUE` 是前端的框架，是用来简化 `JavaScript` 代码编写的。前一天我们做了一个综合性的案例，里面进行了大量的DOM操作，如下

<img src="assets/image-20210831112115508.png" alt="image-20210831112115508" style="zoom:70%;" />

学习了 `VUE` 后，这部分代码我们就不需要再写了。那么 `VUE` 是如何简化 DOM 书写呢？

==基于MVVM(Model-View-ViewModel)思想，实现数据的双向绑定，将编程的关注点放在数据上。==之前我们是将关注点放在了 DOM 操作上；而要了解 `MVVM` 思想，必须先聊聊 `MVC` 思想，如下图就是 `MVC` 思想图解

<img src="assets/image-20210831113940588.png" alt="image-20210831113940588" style="zoom:70%;" />

C 就是咱们 js 代码，M 就是数据，而 V 是页面上展示的内容，如下图是我们之前写的代码

<img src="assets/image-20210831114227585.png" alt="image-20210831114227585" style="zoom:70%;" />

`MVC` 思想是没法进行双向绑定的。双向绑定是指当数据模型数据发生变化时，页面展示的会随之发生变化，而如果表单数据发生变化，绑定的模型数据也随之发生变化。接下来我们聊聊 `MVVM` 思想，如下图是三个组件图解

<img src="assets/image-20210831114805052.png" alt="image-20210831114805052" style="zoom:80%;" />

![1661737281908](C:\Users\Jasper\AppData\Roaming\Typora\typora-user-images\1661737281908.png)

图中的 `Model` 就是我们的数据，`View` 是视图，也就是页面标签，用户可以通过浏览器看到的内容；`Model` 和 `View` 是通过 `ViewModel` 对象进行双向绑定的，而 `ViewModel` 对象是 `Vue` 提供的。接下来让大家看一下双向绑定的效果，下图是提前准备的代码，输入框绑定了 `username` 模型数据，而在页面上也使用 `{{}}` 绑定了 `username` 模型数据

<img src="assets/image-20210831115645528.png" alt="image-20210831115645528" style="zoom:70%;" />

![1661737325726](C:\Users\Jasper\AppData\Roaming\Typora\typora-user-images\1661737325726.png)

通过浏览器打开该页面可以看到如下页面

<img src="assets/image-20210831115902537.png" alt="image-20210831115902537" style="zoom:80%;" />

当我们在输入框中输入内容，而输入框后面随之实时的展示我们输入的内容，这就是双向绑定的效果。

### 1.2  快速入门

Vue 使用起来是比较简单的，总共分为如下三步：

1. **新建 HTML 页面，引入 Vue.js文件**

   ```html
   <script src="js/vue.js"></script>
   ```

2. **在JS代码区域，创建Vue核心对象，进行数据绑定**

   ```js
   new Vue({
       el: "#app",
       data() {
           return {
               username: ""
           }
       }
   });
   ```

   创建 Vue 对象时，需要传递一个 js 对象，而该对象中需要如下属性：

   * `el` ： 用来指定哪儿些标签受 Vue 管理。 该属性取值 `#app` 中的 `app` 需要是受管理的标签的id属性值
   * `data` ：用来定义数据模型
   * `methods` ：用来定义函数。这个我们在后面就会用到

3. **编写视图**

   ```html
   <div id="app">
       <input name="username" v-model="username" >
       {{username}}
   </div>
   ```

   `{{}}` 是 Vue 中定义的 `插值表达式` ，在里面写数据模型，到时候会将该模型的数据值展示在这个位置。

**整体代码如下：**

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div id="app">
    <input v-model="username">
    <!--插值表达式-->
    {{username}}
</div>
<script src="js/vue.js"></script>
<script>
    //1. 创建Vue核心对象
    new Vue({
        el:"#app",
        data(){  // data() 是 ECMAScript 6 版本的新的写法
            return {
                username:""
            }
        }

        /*data:{
                username:""
            }
        */
    });

</script>
</body>
</html>
```

### 1.3  Vue 指令

**指令：**HTML 标签上带有 v- 前缀的特殊属性，不同指令具有不同含义。例如：v-if，v-for…

常用的指令有：

| **指令**  | **作用**                                            |
| --------- | --------------------------------------------------- |
| v-bind    | 为HTML标签绑定属性值，如设置  href , css样式等      |
| v-model   | 在表单元素上创建双向数据绑定                        |
| v-on      | 为HTML标签绑定事件                                  |
| v-if      | 条件性的渲染某元素，判定为true时渲染,否则不渲染     |
| v-else    |                                                     |
| v-else-if |                                                     |
| v-show    | 根据条件展示某元素，区别在于切换的是display属性的值 |
| v-for     | 列表渲染，遍历容器的元素或者对象的属性              |

接下来我们挨个学习这些指令

#### 1.3.1  v-bind & v-model 指令

<img src="assets/image-20210831150101736.png" alt="image-20210831150101736" style="zoom:70%;" />

* **v-bind**

  该指令可以给标签原有属性绑定模型数据。这样模型数据发生变化，标签属性值也随之发生变化

  例如：

  ```html
  <a v-bind:href="url">百度一下</a>
  ```

  上面的 `v-bind:"`  可以简化写成 `:`  ，如下：

  ```html
  <!--
  	v-bind 可以省略
  -->
  <a :href="url">百度一下</a>
  ```

* **v-model**

  该指令可以给表单项标签绑定模型数据。这样就能实现双向绑定效果。例如：

  ```html
  <input name="username" v-model="username">
  ```

**代码演示：**

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div id="app">
    <a v-bind:href="url">点击一下</a>
    <a :href="url">点击一下</a>
    <input v-model="url">
</div>

<script src="js/vue.js"></script>
<script>
    //1. 创建Vue核心对象
    new Vue({
        el:"#app",
        data(){
            return {
                username:"",
                url:"https://www.baidu.com"
            }
        }
    });
</script>
</body>
</html>
```

通过浏览器打开上面页面，并且使用检查查看超链接的路径，该路径会根据输入框输入的路径变化而变化，这是因为超链接和输入框绑定的是同一个模型数据

<img src="assets/image-20210831150945931.png" alt="image-20210831150945931" style="zoom:80%;" />

#### 1.3.2  v-on 指令

<img src="assets/image-20210831151231955.png" alt="image-20210831151231955" style="zoom:70%;" />

我们在页面定义一个按钮，并给该按钮使用 `v-on` 指令绑定单击事件，html代码如下

```html
<input type="button" value="一个按钮" v-on:click="show()">
```

而使用 `v-on` 时还可以使用简化的写法，将 `v-on:` 替换成 `@`，html代码如下

```html
<input type="button" value="一个按钮" @click="show()">
```

上面代码绑定的 `show()` 需要在 Vue 对象中的 `methods` 属性中定义出来

```js
new Vue({
    el: "#app",
    methods: {
        show(){
            alert("我被点了");
        }
    }
});
```

> ==注意：`v-on:` 后面的事件名称是之前原生事件属性名去掉on。==
>
> 例如：
>
> * 单击事件 ： 事件属性名是 onclick，而在vue中使用是 `v-on:click`
> * 失去焦点事件：事件属性名是 onblur，而在vue中使用时 `v-on:blur`

**整体页面代码如下：**

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div id="app">
    <input type="button" value="一个按钮" v-on:click="show()"><br>
    <input type="button" value="一个按钮" @click="show()">
</div>
<script src="js/vue.js"></script>
<script>
    //1. 创建Vue核心对象
    new Vue({
        el:"#app",
        data(){
            return {
                username:"",
            }
        },
        methods:{
            show(){
                alert("我被点了...");
            }
        }
    });
</script>
</body>
</html>
```

#### 1.3.3  条件判断指令

<img src="assets/image-20210831151904081.png" alt="image-20210831151904081" style="zoom:70%;" />

接下来通过代码演示一下。在 Vue中定义一个 `count` 的数据模型，如下

```js
//1. 创建Vue核心对象
new Vue({
    el:"#app",
    data(){
        return {
            count:3
        }
    }
});
```

现在要实现，当 `count` 模型的数据是3时，在页面上展示 `div1` 内容；当 `count` 模型的数据是4时，在页面上展示 `div2` 内容；`count` 模型数据是其他值时，在页面上展示 `div3`。这里为了动态改变模型数据 `count` 的值，再定义一个输入框绑定 `count` 模型数据。html 代码如下：

```html
<div id="app">
    <div v-if="count == 3">div1</div>
    <div v-else-if="count == 4">div2</div>
    <div v-else>div3</div>
    <hr>
    <input v-model="count">
</div>
```

**整体页面代码如下：**

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div id="app">
    <div v-if="count == 3">div1</div>
    <div v-else-if="count == 4">div2</div>
    <div v-else>div3</div>
    <hr>
    <input v-model="count">
</div>

<script src="js/vue.js"></script>
<script>
    //1. 创建Vue核心对象
    new Vue({
        el:"#app",
        data(){
            return {
                count:3
            }
        }
    });
</script>
</body>
</html>
```

通过浏览器打开页面并在输入框输入不同的值，效果如下

<img src="assets/image-20210831154300325.png" alt="image-20210831154300325" style="zoom:80%;" />

然后我们在看看 `v-show` 指令的效果，如果模型数据 `count ` 的值是3时，展示 `div v-show` 内容，否则不展示，html页面代码如下

```html
<div v-show="count == 3">div v-show</div>
<br>
<input v-model="count">
```

浏览器打开效果如下：

<img src="assets/image-20210831154547780.png" alt="image-20210831154547780" style="zoom:80%;" />

通过上面的演示，发现 `v-show` 和 `v-if` 效果一样，那它们到底有什么区别呢？我们根据浏览器的检查功能查看源代码

<img src="assets/image-20210831154759672.png" alt="image-20210831154759672" style="zoom:80%;" />

通过上图可以看出 `v-show` 不展示的原理是给对应的标签添加 `display` css属性，并将该属性值设置为 `none` ，这样就达到了隐藏的效果。而 `v-if` 指令是条件不满足时根本就不会渲染。

#### 1.3.4  v-for 指令

<img src="assets/image-20210831155204829.png" alt="image-20210831155204829" style="zoom:80%;" />

这个指令看到名字就知道是用来遍历的，该指令使用的格式如下：

```html
<标签 v-for="变量名 in 集合模型数据">
    {{变量名}}
</标签>
```

> ==注意：需要循环那个标签，`v-for` 指令就写在那个标签上。==

如果在页面需要使用到集合模型数据的索引，就需要使用如下格式：

```html
<标签 v-for="(变量名,索引变量) in 集合模型数据">
    <!--索引变量是从0开始，所以要表示序号的话，需要手动的加1-->
   {{索引变量 + 1}} {{变量名}}
</标签>
```

**代码演示：**

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div id="app">
    <div v-for="addr in addrs">
        {{addr}} <br>
    </div>

    <hr>
    <div v-for="(addr,i) in addrs">
        {{i+1}}--{{addr}} <br>
    </div>
</div>

<script src="js/vue.js"></script>
<script>

    //1. 创建Vue核心对象
    new Vue({
        el:"#app",
        data(){
            return {
                addrs:["北京","上海","西安"]
            }
        }
    });
</script>
</body>
</html>
```

通过浏览器打开效果如下

<img src="assets/image-20210831155837801.png" alt="image-20210831155837801" style="zoom:80%;" />

### 1.4  生命周期 

生命周期的八个阶段：每触发一个生命周期事件，会自动执行一个生命周期方法，这些生命周期方法也被称为钩子方法。

<img src="assets/image-20210831160239294.png" alt="image-20210831160239294" style="zoom:80%;" />

下图是 Vue 官网提供的从创建 Vue 到效果 Vue 对象的整个过程及各个阶段对应的钩子函数

<img src="assets/image-20210831160335496.png" alt="image-20210831160335496" style="zoom:80%;" />

看到上面的图，大家无需过多的关注这张图。这些钩子方法我们只关注 `mounted` 就行了。

`mounted`：挂载完成，Vue初始化成功，HTML页面渲染成功。而以后我们会在该方法中==发送异步请求，加载数据。==

## 二、Ajax

### 2.1  概述

==`AJAX` (Asynchronous JavaScript And XML)：异步的 JavaScript 和 XML。==

我们先来说概念中的 `JavaScript` 和 `XML`，`JavaScript` 表明该技术和前端相关；`XML` 是指以此进行数据交换。而这两个我们之前都学习过。

#### 2.1.1  作用

AJAX 作用有以下两方面：

1. **与服务器进行数据交换**：通过AJAX可以给服务器发送请求，服务器将数据直接响应回给浏览器。如下图

我们先来看之前做功能的流程，如下图：

<img src="../../day27-filter&listener/01_笔记/assets/image-20210823235114367.png" alt="image-20210823235114367" style="zoom:70%;" />

如上图，`Servlet` 调用完业务逻辑层后将数据存储到域对象中，然后跳转到指定的 `jsp` 页面，在页面上使用 `EL表达式` 和 `JSTL` 标签库进行数据的展示。

而我们学习了AJAX 后，就可以==使用AJAX和服务器进行通信，以达到使用 HTML+AJAX来替换JSP页面==了。如下图，浏览器发送请求servlet，servlet 调用完业务逻辑层后将数据直接响应回给浏览器页面，页面使用 HTML 来进行数据展示。

<img src="../../day27-filter&listener/01_笔记/assets/image-20210823235006847.png" alt="image-20210823235006847" style="zoom:70%;" />

2. **异步交互**：可以在==不重新加载整个页面==的情况下，与服务器交换数据并==更新部分网页==的技术，如：搜索联想、用户名是否可用校验，等等…

<img src="../../day27-filter&listener/01_笔记/assets/image-20210824000706401.png" alt="image-20210824000706401" style="zoom:80%;" />

上图所示的效果我们经常见到，在我们输入一些关键字（例如 `奥运`）后就会在下面联想出相关的内容，而联想出来的这部分数据肯定是存储在百度的服务器上，而我们并没有看出页面重新刷新，这就是 ==更新局部页面== 的效果。再如下图：

<img src="../../day27-filter&listener/01_笔记/assets/image-20210824001015706.png" alt="image-20210824001015706" style="zoom:80%;" />

我们在用户名的输入框输入用户名，当输入框一失去焦点，如果用户名已经被占用就会在下方展示提示的信息；在这整个过程中也没有页面的刷新，只是在局部展示出了提示信息，这就是 ==更新局部页面== 的效果。

#### 3.1.2  同步和异步

知道了局部刷新后，接下来我们再聊聊同步和异步:

* 同步发送请求过程如下

<img src="../../day27-filter&listener/01_笔记/assets/image-20210824001443897.png" alt="image-20210824001443897" style="zoom:80%;" />

​	浏览器页面在发送请求给服务器，在服务器处理请求的过程中，浏览器页面不能做其他的操作。只能等到服务器响应结束后才能，浏览器页面才能继续做其他的操作。

* 异步发送请求过程如下

  <img src="../../day27-filter&listener/01_笔记/assets/image-20210824001608916.png" alt="image-20210824001608916" style="zoom:80%;" />

  浏览器页面发送请求给服务器，在服务器处理请求的过程中，浏览器页面还可以做其他的操作。

### 2.2  快速入门

#### 2.2.1 服务端实现

在项目的创建 `com.itheima.web.servlet` ，并在该包下创建名为  `AjaxServlet` 的servlet

```java
@WebServlet("/ajaxServlet")
public class AjaxServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse responseonse) throws ServletException, IOException {
        //1. 响应数据
        responseonse.getWriter().write("hello ajax~");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse responseonse) throws ServletException, IOException {
        this.doGet(request, responseonse);
    }
}
```

#### 2.2.2  客户端实现

在 `webapp` 下创建名为 `01-ajax-demo1.html` 的页面，在该页面书写 `ajax` 代码

* 创建核心对象，不同的浏览器创建的对象是不同的

  ```js
   var xhttp;
  if (window.XMLHttpRequest) {
      xhttp = new XMLHttpRequest();
  } else {
      // code for IE6, IE5
      xhttp = new ActiveXObject("Microsoft.XMLHTTP");
  }
  ```

* 发送请求

  ```js
  //建立连接
  xhttp.open("GET", "http://localhost:8080/ajax-demo/ajaxServlet");
  //发送请求
  xhttp.send();
  ```

* 获取响应

  ```js
  xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
          // 通过 this.responseonseText 可以获取到服务端响应的数据
          alert(this.responseonseText);
      }
  };
  ```

**完整代码如下：**

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<script>
    //1. 创建核心对象
    var xhttp;
    if (window.XMLHttpRequest) {
        xhttp = new XMLHttpRequest();
    } else {
        // code for IE6, IE5
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    //2. 发送请求
    xhttp.open("GET", "http://localhost:8080/ajax-demo/ajaxServlet");
    xhttp.send();

    //3. 获取响应
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            alert(this.responseonseText);
        }
    };
</script>
</body>
</html>
```

#### 2.2.3  测试

在浏览器地址栏输入 `http://localhost:8080/ajax-demo/01-ajax-demo1.html` ，在 `01-ajax-demo1.html`加载的时候就会发送 `ajax` 请求，效果如下

<img src="../../day27-filter&listener/01_笔记/assets/image-20210824005956117.png" alt="image-20210824005956117" style="zoom:67%;" />

我们可以通过 `开发者模式` 查看发送的 AJAX 请求。在浏览器上按 `F12` 快捷键

<img src="../../day27-filter&listener/01_笔记/assets/image-20210824010247642.png" alt="image-20210824010247642" style="zoom:80%;" />

这个是查看所有的请求，如果我们只是想看 异步请求的话，点击上图中 `All` 旁边的 `XHR`，会发现只展示 Type 是 `xhr` 的请求。如下图：

<img src="../../day27-filter&listener/01_笔记/assets/image-20210824010438260.png" alt="image-20210824010438260" style="zoom:80%;" /> 

### 2.3 案例

需求：在完成用户注册时，当用户名输入框失去焦点时，校验用户名是否在数据库已存在

<img src="../../day27-filter&listener/01_笔记/assets/image-20210824201415745.png" alt="image-20210824201415745" style="zoom:60%;" />

#### 2.3.1  分析

* **前端完成的逻辑**
  1.  给用户名输入框绑定光标失去焦点事件 `onblur`
  2.  发送 ajax请求，携带username参数
  3.  处理响应：是否显示提示信息
* **后端完成的逻辑**
  1. 接收用户名
  2. 调用service查询User。此案例是为了演示前后端异步交互，所以此处我们不做业务逻辑处理
  3. 返回标记

整体流程如下：

<img src="../../day27-filter&listener/01_笔记/assets/image-20210829183854285.png" alt="image-20210829183854285" style="zoom:80%;" />

#### 2.3.2  后端实现

在 `com.ithiema.web.servlet` 包中定义名为 `SelectUserServlet`  的servlet。代码如下：

```java
@WebServlet("/selectUserServlet")
public class SelectUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse responseonse) throws ServletException, IOException {
        //1. 接收用户名
        String username = request.getParameter("username");
        //2. 调用service查询User对象，此处不进行业务逻辑处理，直接给 flag 赋值为 true，表明用户名占用
        boolean flag = true;
        //3. 响应标记
        responseonse.getWriter().write("" + flag);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse responseonse) throws ServletException, IOException {
        this.doGet(request, responseonse);
    }
}
```

#### 2.3.3  前端实现

将 `02-资料\2-验证用户名案例\1. 静态页面` 下的文件整体拷贝到项目下 `webapp` 下。并在 `register.html` 页面的 `body` 结束标签前编写 `script` 标签，在该标签中实现如下逻辑

**第一步：给用户名输入框绑定光标失去焦点事件 `onblur`**

```js
//1. 给用户名输入框绑定 失去焦点事件
document.getElementById("username").onblur = function () {
    
}
```

**第二步：发送 ajax请求，携带username参数**

在 `第一步` 绑定的匿名函数中书写发送 ajax 请求的代码

```js
//2. 发送ajax请求
//2.1. 创建核心对象
var xhttp;
if (window.XMLHttpRequest) {
    xhttp = new XMLHttpRequest();
} else {
    // code for IE6, IE5
    xhttp = new ActiveXObject("Microsoft.XMLHTTP");
}
//2.2. 发送请求
xhttp.open("GET", "http://localhost:8080/ajax-demo/selectUserServlet);
xhttp.send();

//2.3. 获取响应
xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        //处理响应的结果
    }
};
```

由于我们发送的是 GET 请求，所以需要在 URL 后拼接从输入框获取的用户名数据。而我们在 `第一步` 绑定的匿名函数中通过以下代码可以获取用户名数据

```js
// 获取用户名的值
var username = this.value;  //this ： 给谁绑定的事件，this就代表谁
```

而携带数据需要将 URL 修改为：

```js
xhttp.open("GET", "http://localhost:8080/ajax-demo/selectUserServlet?username="+username);
```

**第三步：处理响应：是否显示提示信息**

当 `this.readyState == 4 && this.status == 200` 条件满足时，说明已经成功响应数据了。

此时需要判断响应的数据是否是 "true" 字符串，如果是说明用户名已经占用给出错误提示；如果不是说明用户名未被占用清除错误提示。代码如下

```js
//判断
if(this.responseonseText == "true"){
    //用户名存在，显示提示信息
    document.getElementById("username_err").style.display = '';
}else {
    //用户名不存在 ，清楚提示信息
    document.getElementById("username_err").style.display = 'none';
}
```

**综上所述，前端完成代码如下：**

```js
//1. 给用户名输入框绑定 失去焦点事件
document.getElementById("username").onblur = function () {
    //2. 发送ajax请求
    // 获取用户名的值
    var username = this.value;

    //2.1. 创建核心对象
    var xhttp;
    if (window.XMLHttpRequest) {
        xhttp = new XMLHttpRequest();
    } else {
        // code for IE6, IE5
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    //2.2. 发送请求
    xhttp.open("GET", "http://localhost:8080/ajax-demo/selectUserServlet?username="+username);
    xhttp.send();

    //2.3. 获取响应
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            //alert(this.responseonseText);
            //判断
            if(this.responseonseText == "true"){
                //用户名存在，显示提示信息
                document.getElementById("username_err").style.display = '';
            }else {
                //用户名不存在 ，清楚提示信息
                document.getElementById("username_err").style.display = 'none';
            }
        }
    };
}
```

## 三、axios

Axios 对原生的AJAX进行封装，简化书写。

Axios官网是：`https://www.axios-http.cn`

### 3.1  基本使用

axios 使用是比较简单的，分为以下两步：

* 引入 axios 的 js 文件

  ```html
  <script src="js/axios-0.18.0.js"></script>
  ```

* 使用axios 发送请求，并获取响应结果

  * 发送 get 请求

    ```js
    axios({
        method:"get",
        url:"http://localhost:8080/ajax-demo1/aJAXDemo1?username=zhangsan"
    }).then(function (response){
        alert(response.data);
    })
    ```

  * 发送 post 请求

    ```js
    axios({
        method:"post",
        url:"http://localhost:8080/ajax-demo1/aJAXDemo1",
        data:"username=zhangsan"
    }).then(function (response){
        alert(response.data);
    });
    ```

`axios()` 是用来发送异步请求的，小括号中使用 js 对象传递请求相关的参数：

* `method` 属性：用来设置请求方式的。取值为 `get` 或者 `post`。
* `url` 属性：用来书写请求的资源路径。如果是 `get` 请求，需要将请求参数拼接到路径的后面，格式为： `url?参数名=参数值&参数名2=参数值2`。
* `data` 属性：作为请求体被发送的数据。也就是说如果是 `post` 请求的话，数据需要作为 `data` 属性的值。

`then()` 需要传递一个匿名函数。我们将 `then()` 中传递的匿名函数称为 ==回调函数==，意思是该匿名函数在发送请求时不会被调用，而是在成功响应后调用的函数。而该回调函数中的 `response` 参数是对响应的数据进行封装的对象，通过 `response.data` 可以获取到响应的数据。

### 3.2  快速入门

#### 3.2.1  后端实现

定义一个用于接收请求的servlet，代码如下：

```java
@WebServlet("/axiosServlet")
public class AxiosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse responseonse) throws ServletException, IOException {
        System.out.println("get...");
        //1. 接收请求参数
        String username = request.getParameter("username");
        System.out.println(username);
        //2. 响应数据
        responseonse.getWriter().write("hello Axios~");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse responseonse) throws ServletException, IOException {
        System.out.println("post...");
        this.doGet(request, responseonse);
    }
}
```

#### 3.2.2  前端实现

* 引入 js 文件

  ```js
  <script src="js/axios-0.18.0.js"></script>
  ```

* 发送 ajax 请求

  * get 请求

    ```js
    axios({
        method:"get",
        url:"http://localhost:8080/ajax-demo/axiosServlet?username=zhangsan"
    }).then(function (response) {
        alert(response.data);
    })
    ```

  * post 请求

    ```js
    axios({
        method:"post",
        url:"http://localhost:8080/ajax-demo/axiosServlet",
        data:"username=zhangsan"
    }).then(function (response) {
        alert(response.data);
    })
    ```

**整体页面代码如下：**

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<script src="js/axios-0.18.0.js"></script>
<script>
    //1. get
   /* axios({
        method:"get",
        url:"http://localhost:8080/ajax-demo/axiosServlet?username=zhangsan"
    }).then(function (response) {
        alert(response.data);
    })*/

    //2. post  在js中{} 表示一个js对象，而这个js对象中有三个属性
    axios({
        method:"post",
        url:"http://localhost:8080/ajax-demo/axiosServlet",
        data:"username=zhangsan"
    }).then(function (response) {
        alert(response.data);
    })
</script>
</body>
</html>
```

### 3.3  请求方法别名

为了方便起见， Axios 已经为所有支持的请求方法提供了别名。如下：

* `get` 请求 ： `axios.get(url[,config])`

* `delete` 请求 ： `axios.delete(url[,config])`

* `head` 请求 ： `axios.head(url[,config])`

* `options` 请求 ： `axios.option(url[,config])`

* `post` 请求：`axios.post(url[,data[,config])`

* `put` 请求：`axios.put(url[,data[,config])`

* `patch` 请求：`axios.patch(url[,data[,config])`

而我们只关注 `get` 请求和 `post` 请求。

入门案例中的 `get` 请求代码可以改为如下：

```js
axios.get("http://localhost:8080/ajax-demo/axiosServlet?username=zhangsan").then(function (response) {
    alert(response.data);
});
```

入门案例中的 `post` 请求代码可以改为如下：

```js
axios.post("http://localhost:8080/ajax-demo/axiosServlet","username=zhangsan").then(function (response) {
    alert(response.data);
})
```

###  3.4 使用细节

#### 细节1：箭头函数

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!--注意：axios的引入要在vue的后面-->
    <script src="js/vuejs-2.5.16.js"></script>
    <script src="js/axios-0.18.0.js"></script>
</head>
<body>
    <div id="app">
        {{users}}
    </div>
<script>
var vm = new Vue({
		//声明vue使用的区域
		el:"#app",
		//声明数据
		data:{
            users:[]
		},
		//声明函数
		methods:{

		},
        created:function () {
            //1.发送ajax请求
            //1.1：使用普通函数进行请求成功处理  this不表示vue实例
            //1.2：使用箭头函数进行请求成功处理  this表示的就是vue实例
            //如果出现编写箭头函数报错  原因一般是你当前工具中配置的js版本较低 选择ES6+即可
            //settings--language--JavaScript--ECMAScript6+
            //1.3：使用axios发送post请求
            axios.post("http://localhost:8080/ajax-demo/axiosServlet").then(response=>{
                //在axios中  使用箭头函数时  此时this表示的就是vue实例 基于ES6的新特性
                //response：表示后台处理完成响应信息   response.data表示后台具体响应的数据
                this.users = response.data;
            });
        }
	});
</script>
</body>
</html>
```

#### 细节二：参数传递和异常处理

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!--注意：axios的引入要在vue的后面-->
    <script src="js/vuejs-2.5.16.js"></script>
    <script src="js/axios-0.18.0.js"></script>
</head>
<body>
    <div id="app">
        {{users}}
    </div>
<script>
var vm = new Vue({
		//声明vue使用的区域
		el:"#app",
		//声明数据
		data:{
            users:[]
		},
		//声明函数
		methods:{

		},
        created:function () {
            //1.参数传递
            //get请求方式参数传递  一般在url后使用?进行参数拼接
            /*axios.get("/ajax-demo/axiosServlet?username=zs").then(response=>{
                this.users = response.data;
            });*/
            //post请求方式参数传递
            /*axios.post("/ajax-demo/axiosServlet", {"username":"ls"}).then(response=>{
                //response表示后台响应的信息  response.data才表示后台处理真正响应的数据
                this.users=response.data;
            });*/
            //2.异常处理  如果代码没有异常或者说不想进行异常处理 可以不需要使用catch()
            axios.post("/ajax-demo/axiosServlet", {"username":"ls"}).then(response=>{
                //response表示后台响应的信息  response.data才表示后台处理真正响应的数据
                this.users=response.data;
            }).catch(error=>{
                //error.response :表示错误信息
                console.log(error.response);
                alert("服务器异常！");
            });
        }
	});
</script>
</body>
</html>
```

