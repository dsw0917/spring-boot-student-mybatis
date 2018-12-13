package com.yxt.service;

import com.github.pagehelper.Page;
import com.yxt.domain.entity.PageData;

import java.util.List;

/**
 * Created by yxt06 on 2018/12/7.
 */
public interface BindService {

    List<PageData> deptList() throws Exception;

    Page<PageData> roomListBydeptid(PageData pd) throws Exception;

    List<PageData> personListBydeptid(PageData pd) throws Exception;

    List<PageData> personsByRoom(PageData pd) throws Exception;

    void personBindRoom(PageData pd) throws Exception;
}
