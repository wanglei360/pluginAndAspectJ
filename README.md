# 插件基本制作
### 1：创建一个 module
### 2：只留下 src/main文件夹 和 build.gradle,其它都删除
### 3:删除 build.gradle 里面所有的内容，然后填写以下内容,然后点击右上角的Sync Now
```
apply plugin: 'groovy'
apply plugin: 'maven'

sourceCompatibility = "1.8"
targetCompatibility = "1.8"

buildscript {
    repositories {
        mavenCentral()
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation localGroovy()
    implementation gradleApi()
    //以下为自己需要用到的导包
    implementation 'com.android.tools.build:gradle:3.4.2'
    implementation 'org.aspectj:aspectjtools:1.8.9'
    implementation 'org.aspectj:aspectjrt:1.9.3'
}
group = 'cn.my_plugin'  // 组名
version = '1.0.0' // 版本

//打包到本地或上传远程maven库
uploadArchives {
    repositories {
        mavenDeployer {
//            提交到远程maven库
//            repository(url: uri('')){
//                authentication(userName:'', password:'')//远程maven库的用户名和密码
//            }
//          提交到本地maven地址，自己随便指定一个就可以（要全路径）
            repository(url: uri('/plug'))
            pom.groupId = 'com.myplugin' // 组名  不能使用25行的group,否则报错，不明原因
            pom.artifactId = 'MyGroovyPlugin' // 插件名
            pom.version = '1.0.0' // 版本号  todo 不能使用26行的version,否则报错，不明原因
        }
    }
}
```
### 4:在main 目录下创建 groovy 文件夹，在该文件夹下创建的包名和.groovy文件
```
package com.myPlugin

import org.gradle.api.Plugin
import org.gradle.api.Project

public class MyGroovyPugin implements Plugin<Project> {
    @Override
    void apply(Project project) {//需要重写这个方法
         System.out.println("-------------")
        System.out.println("内容")
        System.out.println("-------------")
    }
}
```
### 5:在 main 目录下依次创建 resources -> META-INF -> gradle-plugins 文件夹，最后在 gradle-plugins文件夹下创建一个 MyGroovyPlugin.properties 文件，在该文件中填写
```
implementation-class=com.myPlugin.MyGroovyPugin//groovy文件的包名+文件名
```
这里的.properties之前的名字就是app下的 build.gradle 中引入时的名字,例如：apply plugin: ‘MyGroovyPlugin’
### 6:点击右侧Gradle->module名字->Tasks->upload->uploadArchives,就会在第一个代码块的指定的位置生成一个插件。
### 7:使用该插件的方法：
```
1:在项目的build.gradle中的buildscript->repositories下添加maven引入
    maven{
       url uri('/Users/wanglei/Downloads/MyApplication/custom_pugin_and_aspectj/plug')
        }
2：继续在该文件下的dependencies中添加引入
      //格式为：group:插件名:version，可在之前在写插件时的build.gradle中寻找这些内容
      classpath 'cn.my_plugin:MyGroovyPlugin:1.0.0'
3：app下的build.gradle中添加对该插件的引用，如：apply plugin: 'MyGroovyPlugin'

```
### 8：clean工程，就能在Run下看到，这是在MyGroovyPlugin.properties文件中写的输出内容
```
-------------
内容
-------------
```

