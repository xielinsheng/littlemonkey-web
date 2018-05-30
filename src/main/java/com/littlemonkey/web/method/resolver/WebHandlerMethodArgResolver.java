package com.littlemonkey.web.method.resolver;

import com.littlemonkey.web.method.MethodParameter;

import java.lang.reflect.AnnotatedElement;


public interface WebHandlerMethodArgResolver {

    boolean supportsParameter(AnnotatedElement annotatedElement);

    Object resolveArgument(MethodParameter methodParameter, String content);
}
