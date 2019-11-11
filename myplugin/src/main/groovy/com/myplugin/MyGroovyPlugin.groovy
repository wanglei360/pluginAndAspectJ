package com.myplugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class MyGroovyPlugin implements Plugin<Project>{
    @Override
    void apply(Project project) {//需要重写这个方法
        System.out.println("-------------")
        System.out.println("内容")
        System.out.println("-------------")
    }
}