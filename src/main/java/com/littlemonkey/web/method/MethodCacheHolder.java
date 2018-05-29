package com.littlemonkey.web.method;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.littlemonkey.utils.base.GenericType;
import com.littlemonkey.utils.reflection.ReflectionUtils2;
import com.littlemonkey.web.context.SpringContextHolder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * @Author: xls
 * @Description:
 * @Date: Created in 16:07 2018/4/4
 * @Version: 1.0
 */
public class MethodCacheHolder {

    private static final Map<String, MethodDetail> map = Maps.newConcurrentMap();

    private static void setMethod(String key, MethodDetail methodDetail) {
        map.put(key, methodDetail);
    }

    private static MethodDetail getMethod(String key) {
        return map.get(key);
    }

    private static void setMethod(String serviceName, String methodName, MethodDetail methodDetail) {
        setMethod(serviceName + "_" + methodName, methodDetail);
    }

    private static MethodDetail getMethod(String serviceName, String methodName) {
        return getMethod(serviceName + "_" + methodName);
    }

    public static MethodDetail getTargetMethod(String serviceName, String methodName) {
        Assert.notNull(serviceName, "serviceName is not null.");
        Assert.notNull(methodName, "methodName is not null.");
        if (Objects.isNull(MethodCacheHolder.getMethod(serviceName, methodName))) {
            //所有public方法,包含继承
            List<Method> methodList = Lists.newArrayList(SpringContextHolder.getType(serviceName).getMethods());
            methodList.forEach((Method method) -> {
                if (StringUtils.endsWithIgnoreCase(methodName, method.getName())) {
                    Map<String, GenericType> paramDetailMap = Maps.newTreeMap();
                    String[] parameterNames = ReflectionUtils2.getParameterNames(method);
                    List<GenericType> genericTypeList = ReflectionUtils2.getGenericType(method);
                    for (int i = 0; i < parameterNames.length; i++) {
                        paramDetailMap.put(parameterNames[i], genericTypeList.get(i));
                    }
                    MethodDetail methodDetail = new MethodDetail();
                    methodDetail.setMethod(method);
                    methodDetail.setParamDetailMap((TreeMap<String, GenericType>) paramDetailMap);
                    MethodCacheHolder.setMethod(serviceName, methodName, methodDetail);
                }
            });
        }
        return MethodCacheHolder.getMethod(serviceName, methodName);
    }


}
