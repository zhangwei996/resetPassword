package com.bestsch.resetPsd.controller;

import com.bestsch.resetPsd.util.Page;
import org.apache.commons.lang.StringUtils;

import java.util.List;


/**
 * @author Zhangwei
 * @description 进行信息校验什么的。
 * @date 2020-03-12
 */
public class BaseController {

    protected Page getPageVO(String page, String pageCount) {
        Page pageVO = new Page();
        if (StringUtils.isNumeric(pageCount)) {
            int num = Integer.valueOf(pageCount);
            /*if (num>50) {
                pageVO.setPageSize(50);
            }*/
            pageVO.setPageSize(Double.valueOf(pageCount).intValue());
        }
        if (StringUtils.isNumeric(page)) {
            pageVO.setCurrent(page);
        }
        return pageVO;
    }
    protected List pageBySubList(List list, String page, String pageCount) {
        Page pageVO = this.getPageVO(page, pageCount);
        Integer pagesize = pageVO.getPageSize();
        Integer currentPage = pageVO.getCurrent() == 0 ? 1 : pageVO.getCurrent();
        int totalcount = list.size();
        if (totalcount == 0) return null;
        int pagecount = 0;
        List<String> subList;
        int m = totalcount % pagesize;
        if (m > 0) {
            pagecount = totalcount / pagesize + 1;
        } else {
            pagecount = totalcount / pagesize;
        }
        if (m == 0) {
            subList = list.subList((currentPage - 1) * pagesize, pagesize * (currentPage));
        } else {
            if (currentPage == pagecount) {
                subList = list.subList((currentPage - 1) * pagesize, totalcount);
            } else {
                subList = list.subList((currentPage - 1) * pagesize, pagesize * (currentPage));
            }
        }
        return subList;
    }
}
