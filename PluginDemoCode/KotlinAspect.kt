package com.jni.myapplication


import android.util.Log
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before

/**
 * java 和 kotlin都能用
 */
@Aspect
class KotlinAspect {

    @Before("execution(* btnClik(..))")
    fun before(joinPoint: JoinPoint) {
        Log.d("asdfasdf", "之前执行")
    }

    @After("execution(* btnClik(..))")
    fun after(joinPoint: JoinPoint) {
        Log.d("asdfasdf", "之后执行")
    }
}