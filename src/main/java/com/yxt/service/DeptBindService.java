package com.yxt.service;

import com.yxt.domain.entity.PageData;

import java.util.List;

/**
 * Created by yxt06 on 2018/12/13.
 */
public interface DeptBindService {

    List<PageData> deptList() throws Exception;

    List<PageData> buildList() throws Exception;

    List<PageData> deptBindRoom(PageData pd) throws Exception;

    List<PageData> roomByDept(PageData pd) throws Exception;
}
