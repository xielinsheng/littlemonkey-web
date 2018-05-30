package com.littlemonkey.web.method.build.impl;

import com.google.common.collect.Lists;
import com.littlemonkey.utils.base.Constants;
import com.littlemonkey.utils.lang.StringUtils;
import com.littlemonkey.web.method.MethodDetail;
import com.littlemonkey.web.method.build.MethodBuildProvider;
import com.littlemonkey.web.param.RequestDetail;
import com.littlemonkey.web.request.RestfulRequestBody;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Component("restfulMethodBuildProviderImpl")
public class RestfulMethodBuildProviderImpl implements MethodBuildProvider {
    @Override
    public Object[] buildParams(RequestDetail requestDetail) {
        MethodDetail methodDetail = requestDetail.getTargetMethodDetail();
        RestfulRequestBody restfulRequestBody = (RestfulRequestBody) requestDetail.getRequestBody();
        if (requestDetail.getRequestMethodType().equals(RequestMethod.GET)
                || requestDetail.getRequestMethodType().equals(RequestMethod.PUT)
                || requestDetail.getRequestMethodType().equals(RequestMethod.DELETE)) {
            if (StringUtils.endsWith(restfulRequestBody.getContent(), Constants.AD)) {
                restfulRequestBody.setContent(StringUtils.join(restfulRequestBody.getContent(),
                        StringUtils.join("id", Constants.EQUAL), restfulRequestBody.getId()));
            } else {
                restfulRequestBody.setContent(StringUtils.join(restfulRequestBody.getContent(),
                        StringUtils.join(Constants.AD, "id", Constants.EQUAL), restfulRequestBody.getId()));
            }
        }
        List<Object> params = Lists.newArrayListWithCapacity(methodDetail.getMethod().getParameterTypes().length);
        this.resolve(requestDetail, methodDetail, restfulRequestBody, params);
        return params.toArray();
    }
}
