package com.littlemonkey.web.controller;

import com.littlemonkey.web.context.CurrentHttpServletHolder;
import com.littlemonkey.web.method.Method;
import com.littlemonkey.web.request.DefaultRequestBody;
import com.littlemonkey.web.utils.WebUtils2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/api")
public class ApiController {

    /**
     * <p>get list or get item</p>
     *
     * @param serviceName
     * @param id
     */
    @GetMapping(value = {"/{serviceName}", "/{serviceName}/{id}"})
    public void doGet(@PathVariable(name = "serviceName") String serviceName, @PathVariable(name = "id", required = false) Long id) {
        DefaultRequestBody defaultRequestBody = new DefaultRequestBody(serviceName, Objects.isNull(id) ? Method.get.name() : Method.list.name());
        defaultRequestBody.setContent(WebUtils2.getQueryString(CurrentHttpServletHolder.getCurrentRequest()))
                .setRequestMethod(RequestMethod.valueOf(CurrentHttpServletHolder.getCurrentRequest().getMethod()));
    }

    /**
     * <p>add item</p>
     *
     * @param serviceName
     */
    @PostMapping(value = "/{serviceName}", consumes = "application/json")
    public void doPost(@PathVariable(name = "serviceName") String serviceName) {
        DefaultRequestBody defaultRequestBody = new DefaultRequestBody(serviceName, Method.save.name());
        defaultRequestBody.setContent(WebUtils2.getRequestBodyJson(CurrentHttpServletHolder.getCurrentRequest()))
                .setRequestMethod(RequestMethod.valueOf(CurrentHttpServletHolder.getCurrentRequest().getMethod()));
    }

    /**
     * <p>update item</p>
     *
     * @param serviceName
     */
    @PostMapping(value = "/{serviceName}", consumes = "application/json")
    public void doPut(@PathVariable(name = "serviceName") String serviceName) {
        DefaultRequestBody defaultRequestBody = new DefaultRequestBody(serviceName, Method.update.name());
        defaultRequestBody.setContent(WebUtils2.getRequestBodyJson(CurrentHttpServletHolder.getCurrentRequest()))
                .setRequestMethod(RequestMethod.valueOf(CurrentHttpServletHolder.getCurrentRequest().getMethod()));
    }

    /**
     * <p>delete item</p>
     *
     * @param serviceName
     * @param id
     */
    @DeleteMapping(value = "/{serviceName}/{id}")
    public void doDelete(@PathVariable(name = "serviceName") String serviceName, @PathVariable(name = "id") Long id) {
        DefaultRequestBody defaultRequestBody = new DefaultRequestBody(serviceName, Method.remove.name());
        defaultRequestBody.setContent(WebUtils2.getRequestBodyJson(CurrentHttpServletHolder.getCurrentRequest()))
                .setRequestMethod(RequestMethod.valueOf(CurrentHttpServletHolder.getCurrentRequest().getMethod()));
    }

    /**
     * <p>other get request</p>
     *
     * @param serviceName
     * @param methodName
     */
    @GetMapping(value = "/{serviceName}/{methodName}")
    public void otherGet(@PathVariable(name = "serviceName") String serviceName, @PathVariable(name = "methodName") String methodName) {
        DefaultRequestBody defaultRequestBody = new DefaultRequestBody(serviceName, methodName);
        defaultRequestBody.setContent(WebUtils2.getQueryString(CurrentHttpServletHolder.getCurrentRequest()))
                .setRequestMethod(RequestMethod.valueOf(CurrentHttpServletHolder.getCurrentRequest().getMethod()));
    }

    /**
     * <p>other post request</p>
     *
     * @param serviceName
     * @param methodName
     */
    @PostMapping(value = "/{serviceName}/{methodName}", consumes = "application/json")
    public void otherPost(@PathVariable(name = "serviceName") String serviceName, @PathVariable(name = "methodName") String methodName) {
        DefaultRequestBody defaultRequestBody = new DefaultRequestBody(serviceName, methodName);
        defaultRequestBody.setContent(WebUtils2.getRequestBodyJson(CurrentHttpServletHolder.getCurrentRequest()))
                .setRequestMethod(RequestMethod.valueOf(CurrentHttpServletHolder.getCurrentRequest().getMethod()));
    }
}
