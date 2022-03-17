[asPectJ找到一个现成的挺好用](https://github.com/HujiangTechnology/gradle_plugin_android_aspectjx)
但是有个问题，gradle需要在7.0以下，现在好多第三方都没有适配gradle7.0以上，所以如果近期使用的话，可以降级gradle到7.0以下


# 插件基本制作
## 7.0以后的配置
###创建插件：
#### 1：创建一个空的module，只保留类和空的build.gradle
#### 2：编写插件：随便创建一个类
    public class DemoPlugins implements Plugin<Project> {
        @Override public void apply(Project project) {
            System.out.println("GreetingStandaloneGradlePlugins(standalone) ---> apply");
        }
    }
#### 3：编写build.gradle,参照pluginDemo中编写即可，都有注释
#### 4：生成插件：点击Gradle->pluginDemo(插件module名字)->Tasks->publishing->publish,即可生成插件

###使用插件：
#### 1：根目录settings.gradle-> pluginManagement添加代码块
    resolutionStrategy {
            eachPlugin {
                //todo 如果是指定的groupId
                if (requested.id.namespace == 'lugins') {
                    // groupId:artifactId:version
                    useModule("${requested.id.namespace}:${requested.id.name}:${requested.version}")
                }
            }
        }
    在pluginManagement ->repositories 添加maven引入
        maven {
            //这里是当前根目录下的plugDemo文件夹
            url = "../pluginAndAspectJ/plugDemo"
        }
#### 2：在使用插件的module中的build.gradle->plugins中添加以下代码
    // 对应的是插件module中build.gradle中的'groupId.artifactId'  version 'version'
    id 'lugins.dalone' version '1.0.0'

### 检查是否成功：
Gradle->app(使用插件的module名字)->Tasks->build->build
点击后就能在as的Build窗口中看到上面DemoPlugins类中打印的内容了












## 7.0以前的配置
### 1:创建一个 module
### 2:只留下 src/main文件夹 和 build.gradle,其它都删除
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
        url uri('/Users/wanglei/Documents/svnDemo/pluginAndAspectJ/plug')
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
## 感谢分享:

#### [AspectJ切入点语法详解](https://blog.csdn.net/zhengchao1991/article/details/53391244)
#### [AOP讲解](https://www.jianshu.com/p/0799aa19ada1)
#### [Plugin制作](http://www.10tiao.com/html/227/201709/2650241354/1.html)
