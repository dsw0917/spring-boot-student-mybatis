package com.yxt.base;

import com.yxt.domain.entity.PageData;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author yxt06
 * @date 2018/12/3
 */
public class BaseController {

    public PageData getPageData() {
        return new PageData(this.getRequest());
    }

    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        return request;
    }
}
