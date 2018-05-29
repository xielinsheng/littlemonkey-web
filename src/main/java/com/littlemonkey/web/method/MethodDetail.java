package com.littlemonkey.web.method;

import com.littlemonkey.utils.base.GenericType;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.TreeMap;

/**
 * @Author: xls
 * @Description: packaging method detail
 * @Date: Created in 16:02 2018/4/4
 * @Version: 1.0
 */
public class MethodDetail implements AnnotatedElement {

    /**
     * <p>target method</p>
     */
    private Method method;

    /**
     * <p>method params generic detail</p>
     * <p>treeMap ensure param order,map key is param name,map vue is param generic detail</p>
     */
    private TreeMap<String, GenericType> paramDetailMap;

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public TreeMap<String, GenericType> getParamDetailMap() {
        return paramDetailMap;
    }

    public void setParamDetailMap(TreeMap<String, GenericType> paramDetailMap) {
        this.paramDetailMap = paramDetailMap;
    }

    @Override
    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        return this.method.getAnnotation(annotationClass);
    }

    @Override
    public Annotation[] getAnnotations() {
        return this.method.getAnnotations();
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return this.method.getDeclaredAnnotations();
    }


    @Override
    public String toString() {
        return "MethodDetail{" +
                "method=" + method +
                ", paramDetailMap=" + paramDetailMap +
                '}';
    }


}


