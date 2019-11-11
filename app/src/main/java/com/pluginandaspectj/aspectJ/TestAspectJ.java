package com.pluginandaspectj.aspectJ;


import com.pluginandaspectj.util.ToastUtil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/*
https://www.jianshu.com/p/c66f4e3113b3
在AspectJ中常用的是before、after、around等。
before表示在JPoint执行之前，需要干的事情。
after表示的是在JPoint执行之后，
around表示的是在JPoint执行前后(包裹在内的意思)。


execution讲解：https://blog.csdn.net/zhengchao1991/article/details/53391244
 */
@Aspect
public class TestAspectJ {

    // 第一个*所在的位置表示的是返回值，*表示的是任意的返回值，
    // onClick()中的 .. 所在位置是方法参数的位置，.. 表示的是任意类型、任意个数的参数
    // * 表示的是通配
    @Pointcut("execution(* android.view.View.OnClickListener.onClick(..))")
    public void test1() {
    }

    //text包及所有子包下任何类的任何方法
    @Pointcut("execution(* com.pluginandaspectj.test..*.*(..))")
    public void test2() {
    }

    //指定实现了某一个接口下的所有方法
    @Pointcut("execution(* com.pluginandaspectj.HH+.*(..))")
    public void test3() {
    }

    @Around("test1()")
    public void test1(final ProceedingJoinPoint joinPoint) {
        new Thread() {
            @Override
            public void run() {
                try {
                    //执行的目标方法，运行在子线程中
                    joinPoint.proceed(joinPoint.getArgs());
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }.start();
    }

    @After("test2()")
    public void test2(final ProceedingJoinPoint joinPoint) {
        ToastUtil.showToast("织入包下的test");
    }

    @After("test3()")
    public void test3(final ProceedingJoinPoint joinPoint) {
        ToastUtil.showToast("织入接口test");
    }
}
