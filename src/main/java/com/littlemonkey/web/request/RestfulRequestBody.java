package com.littlemonkey.web.request;


import com.littlemonkey.web.annotation.MethodBuildClass;
import com.littlemonkey.web.method.build.impl.RestfulMethodBuildProviderImpl;

@MethodBuildClass(target = RestfulMethodBuildProviderImpl.class)
public class RestfulRequestBody extends DefaultRequestBody {

    private long id;

    public RestfulRequestBody(String serviceName, String methodName, String content) {
        super(serviceName, methodName, content);
    }

    @Override
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public String getMethodName() {
        return methodName;
    }

    @Override
    public String getContent() {
        return content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "RestfulRequestBody{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
