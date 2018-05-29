package com.littlemonkey.web.request;

import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;

/**
 * @Author: xls
 * @Description:
 * @Date: Created in 20:32 2018/4/3
 * @Version: 1.0
 */
public class DefaultRequestBody implements RequestBody {

    private RequestMethod requestMethod;
    private String serviceName;
    private String methodName;
    private String content;

    public DefaultRequestBody(String serviceName, String methodName) {
        this.serviceName = serviceName;
        this.methodName = methodName;
    }

    public DefaultRequestBody setContent(String content) {
        this.content = content;
        return this;
    }

    public DefaultRequestBody setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
        return this;
    }

    @Override
    public String getServiceName() {
        return this.serviceName;
    }

    @Override
    public String getMethodName() {
        return this.methodName;
    }

    @Override
    public String getContent() {
        return this.content;
    }

    @Override
    public RequestMethod getRequestMethod() {
        return this.requestMethod;
    }

    @Override
    public String toString() {
        return "DefaultRequestBody{" +
                "serviceName='" + serviceName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
