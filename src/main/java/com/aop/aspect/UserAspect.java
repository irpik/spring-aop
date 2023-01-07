package com.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class UserAspect {

    @Pointcut("execution(* com.aop.service.UserService.createUser(..))")
    public void createUserPointcut(){}

    //@Before("execution(* com.aop.service.UserService.createUser(..))")
    @Before("createUserPointcut()")
    public void createUserBefore(JoinPoint joinPoint){
        System.err.println("Create Before Method " + joinPoint.getArgs()[0]); //metotun aldığı parametler dizi şeklinde gelir sırası ile istenilen alınır.
    }
    //@After("execution(* com.aop.service.UserService.createUser(..))")
    @After("createUserPointcut()")
    public void createUserAfter(JoinPoint joinPoint){
        System.err.println("Create After Method");
    }

    //@AfterReturning(pointcut = "createUserPointcut()", returning = "userCreateReturn")
    @AfterReturning(pointcut = "execution(* com.aop.service.UserService.createUser(..))", returning = "userCreateReturn")
    public void createUserReturn(JoinPoint joinPoint, Object userCreateReturn){
        System.err.println("AfterReturning " + userCreateReturn);
    }

    @AfterThrowing(pointcut = "execution(* com.aop.service.UserService.createUser(..))", throwing = "error")
    public void createUserThrowing(JoinPoint joinPoint, Throwable error) throws Throwable{
        System.err.println("AfterThrowing " + error);
    }

    @Around("createUserPointcut()")
    public void createUserAround(ProceedingJoinPoint proceedingJoinPoint){
        System.err.println("Around start");
        Object[] args = proceedingJoinPoint.getArgs();

        Object result = proceedingJoinPoint.proceed(args);
        System.err.println("Around End " + result);
    }
    //Çalışma sırası
    /*
    Around start
    Create Before Method User(id=4, name=Demo, surname=Demo, age=18)
    createUser
    AfterReturning Kullanıcı kaydedildi.
    Create After Method
    Around End Kullanıcı kaydedildi.
    */

    //################################
    //Genel methot kullanımı

    //@Pointcut("execution(* com.aop.service.*.*(..))") //service paketinin altındaki tüm class'lar, class'ların içindeki tüm metotlar ve metotların içindeki herhangi tüm parametleri al.
    //@Pointcut("execution(* com.aop.service.UserService.*(int,..))") //UserService class'ındaki tüm metotlar ve ilk parametre değeri int olan sonraki parametreler ne olursa olsun.
    //@Pointcut("execution(public * com.aop.service.*.*(..))")
    //@Pointcut("execution(public String com.aop.service.*.*(..))")
    @Pointcut("within(com.aop.service.*)") //paketin içindeki tüm class'lar
    public void allAopService(){}

}
