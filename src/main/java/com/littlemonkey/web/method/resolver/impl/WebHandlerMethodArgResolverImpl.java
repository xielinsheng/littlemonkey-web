package com.littlemonkey.web.method.resolver.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.littlemonkey.utils.base.GenericType;
import com.littlemonkey.utils.collect.Collections3;
import com.littlemonkey.utils.lang.StringUtils;
import com.littlemonkey.utils.reflection.ReflectionUtils2;
import com.littlemonkey.web.method.MethodParameter;
import com.littlemonkey.web.method.resolver.WebHandlerMethodArgResolver;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONTokener;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.AnnotatedElement;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
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
        Map<String, String> queryMap;
        Object target;
        Object param = null;
        if (StringUtils.isNotBlank(queryString)) {
            List<NameValuePair> nameValuePairs = URLEncodedUtils.parse(content, StandardCharsets.UTF_8);
            queryMap = nameValuePairs.stream().collect(Collectors.toMap(NameValuePair::getName, NameValuePair::getValue));
        }
        if (StringUtils.isNotBlank(content)) {
            target = JSON.parse(content);
        }

        return param;
    }

    public static void main(String[] args) {
        System.out.println(Collections3.isContainer(List.class));
    }
}
