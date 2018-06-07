package com.ouym.libpermission.aspectjs;

import android.app.Fragment;
import android.content.Context;
import android.os.Build;

import com.ouym.libpermission.LPBaseActivity;
import com.ouym.libpermission.RequestPermission;
import com.ouym.libpermission.Utils;
import com.ouym.libpermission.annotations.PermissionCheck;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Aspect
public class PermissionCheckAspectj {


    @Pointcut("execution(@com.ouym.libpermission.annotations.PermissionCheck * *(..))")
    public void methodNeedPermission() {
    }

    @Around("methodNeedPermission()")
    public Object aroundJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Class<?> type = method.getReturnType();

        if (type.isPrimitive() && !type.toString().equals("void")){
            throw new RuntimeException("PermissionCheck注解的方法返回值不能是基本数据类型");
        }

        PermissionCheck annotation = method.getAnnotation(PermissionCheck.class);
        String[] permissions = annotation.value();

        Object object = joinPoint.getTarget();
        if (!Fragment.class.isInstance(object)
                && !android.support.v4.app.Fragment.class.isInstance(object)
                && LPBaseActivity.class.isInstance(object)) {
            throw new RuntimeException("PermissionCheck注解只能用在V4-Fragment和Activity上");
        }

        if (object instanceof Fragment ){
            object = ((Fragment) object).getActivity();
        } else if (object instanceof android.support.v4.app.Fragment){
            object = ((android.support.v4.app.Fragment) object).getActivity();
        }

        if (!(object instanceof LPBaseActivity)){
            throw new RuntimeException("请继承LPBaseActivity");
        }

        Context target = (Context) object;
        String[] permissionNotGranted = Utils.getPermissionNotGranted(target, permissions);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && permissionNotGranted.length > 0){
            dealWithPermission(joinPoint, target, permissionNotGranted);
            return null;
        } else {
            return joinPoint.proceed();
        }
    }

    private void dealWithPermission(ProceedingJoinPoint joinPoint, Context activity, String[] permissions) throws Throwable {
        RequestPermission reqPermission = new RequestPermission();
        //TODO 低于23的都执行不到这里来
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            reqPermission.doPermissionGant(joinPoint, activity, permissions);
        }
    }
}