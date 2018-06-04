package com.littlemonkey.web.method.resolver.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.littlemonkey.utils.base.GenericType;
import com.littlemonkey.utils.collect.Collections3;
import com.littlemonkey.utils.lang.NumberUtils2;
import com.littlemonkey.utils.lang.StringUtils;
import com.littlemonkey.web.method.MethodParameter;
import com.littlemonkey.web.method.resolver.WebHandlerMethodArgResolver;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;

import java.lang.reflect.AnnotatedElement;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class WebHandlerMethodArgResolverImpl implements WebHandlerMethodArgResolver {
    @Override
    public boolean supportsParameter(AnnotatedElement annotatedElement) {
        return Collections3.isEmpty(annotatedElement.getAnnotations());
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, String queryString, String content) {
        final GenericType genericType = methodParameter.getGenericType();
        final String parameterName = methodParameter.getParameterName();
        final Class tClass = genericType.getOwnerType();
        Map<String, String> queryBody = null;
        Object contentBody = new JSONObject();
        if (StringUtils.isNotBlank(queryString)) {
            List<NameValuePair> nameValuePairs = URLEncodedUtils.parse(queryString, StandardCharsets.UTF_8);
            queryBody = nameValuePairs.stream().collect(Collectors.toMap(NameValuePair::getName, NameValuePair::getValue));
        }
        if (StringUtils.isNotBlank(content)) {
            contentBody = JSON.parse(content);
        }
        if (!Collections3.isEmpty(queryBody) && contentBody instanceof JSONObject) {
            ((JSONObject) contentBody).putAll(queryBody);
        }
        if (tClass.isPrimitive()) {// 参数是个基本类型
            JSONObject jsonObject = (JSONObject) contentBody;
            String value = jsonObject.getString(parameterName);
            if (value == null) {
                return value;
            }
            if (NumberUtils2.isNumberClass(tClass)) {
                return NumberUtils2.parseNumber(value, tClass);
            } else if (tClass.equals(boolean.class) || tClass.equals(Boolean.class)) {
                return Boolean.parseBoolean(value);
            } else {
                return value.toCharArray()[0];
            }
        } else if (Collections3.isContainer(tClass)) {// 方法参数是个Collection 或者Array类型
            JSONArray jsonArray = (JSONArray) contentBody;
            List<GenericType> genericTypeList = genericType.getGenericTypeList();
            GenericType targetType = genericTypeList.get(0);
            List list = jsonArray.toJavaList(targetType.getOwnerType());
            if (tClass.isArray()) {// 是个array
                return list.toArray();
            } else {// 是个Collection
                return list;
            }
        } else {// 否则是个Map 或者 Bean
            JSONObject jsonObject = (JSONObject) contentBody;
            if (Collections3.isMap(tClass)) {// 参数是个map
                return jsonObject.getInnerMap();
            } else {// 参数是个bean
                return jsonObject.toJavaObject(tClass);
            }
        }
    }
}
