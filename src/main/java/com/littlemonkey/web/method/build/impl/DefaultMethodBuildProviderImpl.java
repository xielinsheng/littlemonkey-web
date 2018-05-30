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
import com.littlemonkey.web.request.DefaultRequestBody;
import com.littlemonkey.web.request.RequestBody;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiConsumer;

@Component("defaultMethodBuildProviderImpl")
public class DefaultMethodBuildProviderImpl implements MethodBuildProvider {
    @Override
    public Object[] buildParams(RequestDetail requestDetail) {
        MethodDetail methodDetail = requestDetail.getTargetMethodDetail();
        DefaultRequestBody defaultRequestBody = (DefaultRequestBody) requestDetail.getRequestBody();
        List<Object> params = Lists.newArrayListWithCapacity(methodDetail.getMethod().getParameterTypes().length);
        this.resolve(requestDetail, methodDetail, defaultRequestBody, params);
        return params.toArray();
    }
}
