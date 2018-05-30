package com.littlemonkey.web.method.build.impl;

import com.google.common.collect.Lists;
import com.littlemonkey.utils.base.GenericType;
import com.littlemonkey.web.context.SpringContextHolder;
import com.littlemonkey.web.method.MethodCacheHolder;
import com.littlemonkey.web.method.MethodDetail;
import com.littlemonkey.web.method.MethodParameter;
import com.littlemonkey.web.method.build.MethodBuildProvider;
import com.littlemonkey.web.method.resolver.WebHandlerMethodArgResolver;
import com.littlemonkey.web.param.RequestDetail;
import com.littlemonkey.web.request.RequestBody;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiConsumer;

@Component("defaultMethodBuildProviderImpl")
public class DefaultMethodBuildProviderImpl implements MethodBuildProvider {
    @Override
    public Object[] buildParams(RequestDetail requestDetail) {
        MethodDetail methodDetail = requestDetail.getTargetMethodDetail();
        RequestBody requestBody = requestDetail.getRequestBody();
        String[] beanNames = SpringContextHolder.getBeanNamesForType(WebHandlerMethodArgResolver.class);
        List<Object> params = Lists.newArrayListWithCapacity(methodDetail.getMethod().getParameterTypes().length);
        methodDetail.getParamDetailMap().forEach(new BiConsumer<String, GenericType>() {
            @Override
            public void accept(String paramName, GenericType genericType) {
                MethodParameter methodParameter = new MethodParameter();
                methodParameter.setGenericType(genericType);
                methodParameter.setParameterName(paramName);
                methodParameter.setRequestMethod(requestDetail.getRequestMethodType());
                for (String beanName : beanNames) {
                    WebHandlerMethodArgResolver webHandlerMethodArgResolver = SpringContextHolder.getBean(beanName);
                    if (webHandlerMethodArgResolver.supportsParameter(methodDetail)) {
                        params.add(webHandlerMethodArgResolver.resolveArgument(methodParameter, requestBody.getContent()));
                        break;
                    }
                }
            }
        });
        return params.toArray();
    }
}
