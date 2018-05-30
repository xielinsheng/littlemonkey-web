package com.littlemonkey.web.method.build;

import com.littlemonkey.web.param.RequestDetail;

public interface MethodBuildProvider {
    Object[] buildParams(RequestDetail requestDetail);
}
