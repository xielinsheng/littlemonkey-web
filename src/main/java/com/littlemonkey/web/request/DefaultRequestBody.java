package com.littlemonkey.web.request;

import com.littlemonkey.web.annotation.MethodBuildClass;
import com.littlemonkey.web.method.build.impl.DefaultMethodBuildProviderImpl;

/**
 * @Author: xls
 * @Description:
 * @Date: Created in 20:32 2018/4/3
 * @Version: 1.0
 */
@MethodBuildClass
public class DefaultRequestBody implements RequestBody {

    protected String serviceName;
    protected String methodName;
    protected String content;

    public DefaultRequestBody(String serviceName, String methodName, String content) {
        this.serviceName = serviceName;
        this.methodName = methodName;
        this.content = content;
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
    public String toString() {
        return "DefaultRequestBody{" +
                "serviceName='" + serviceName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
