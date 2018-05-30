package com.littlemonkey.web.method;

import com.littlemonkey.utils.base.GenericType;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: xls
 * @Description:
 * @Date: Created in 16:29 2018/4/4
 * @Version: 1.0
 */
public class MethodParameter {
    private String parameterName;
    private RequestMethod requestMethod;
    private GenericType genericType;

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public GenericType getGenericType() {
        return genericType;
    }

    public void setGenericType(GenericType genericType) {
        this.genericType = genericType;
    }


    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
    }
}
