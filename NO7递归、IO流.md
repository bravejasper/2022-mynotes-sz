## 一、File类

### 1 File类的概述和构造方法

#### 1.1File类的概述

`java.io.File` 类是用来表示文件或者文件夹路径，可以用于对文件和文件夹的创建、查找和删除等操作。

#### 1.2File类的构造方法

- `public File(String pathname) ` ：通过将给定的**路径名字符串**转换为抽象路径名来创建新的 File实例。  
- `public File(String parent, String child) ` ：从**父路径名字符串和子路径名字符串**创建新的 File实例。
- `public File(File parent, String child)` ：从**父抽象路径名和子路径名字符串**创建新的 File实例。  
- 注意: 
  - **创建File对象时,如果传入的文件或者文件夹路径不存在,依然不影响File对象的创建**

### 2.File类常用方法

#### 2.1获取功能的方法

- `public String getAbsolutePath() ` ：返回此File的绝对路径名字符串。
- ` public String getPath() ` ：将此File转换为路径名字符串。 **构造路径**
- `public String getName()`  ：返回由此File表示的文件或目录的名称。  
- `public long length()`  ：返回由此File表示的文件的长度。 **不能获取目录的长度。**

#### 2.2判断功能的方法

- `public boolean exists()` ：此File表示的文件或目录是否实际存在。
- `public boolean isDirectory()` ：此File表示的是否为目录。
- `public boolean isFile()` ：此File表示的是否为文件。
- 注意: 如果File对象表示的路径不存在,以上三个方法的返回值都是false

#### 2.3创建删除功能的方法

- `public boolean createNewFile()` ：当且仅当具有该名称的文件尚不存在时，创建一个新的空文件。 
- `public boolean delete()` ：删除由此File表示的文件或目录。  **删除文件,或者删除空文件夹, 不能删除非空文件夹**
- `public boolean mkdir()` ：创建由此File表示的目录。
- `public boolean mkdirs()` ：创建由此File表示的目录，包括任何必需但不存在的父目录。

#### 2.4遍历目录方法

- `public String[] list()` ：获取File目录中的所有子文件或子目录的名称。

- `public File[] listFiles()` ：获取File目录中的所有子文件或子目录的路径。
- **结论; 循环遍历返回的数组之前,建议做非空判断,避免空指针异常**



## 二、递归

### 1.