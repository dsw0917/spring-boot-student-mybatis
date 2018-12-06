package com.yxt.service.impl;

import com.github.pagehelper.Page;
import com.yxt.dao.DaoSupport;
import com.yxt.domain.entity.PageData;
import com.yxt.service.RoomService;
import com.yxt.officialVehicl.core.jwt.UserInfoFactory;
import com.yxt.officialVehicl.management.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author yxt06
 * @date 2018/12/4
 */
@Service
public class RoomServiceImpl implements RoomService{
    @Autowired
    private DaoSupport dao;

    @Override
    public Page<PageData> findByPage(int pageNo, int pageSize, PageData pd) throws Exception {
        return null;
    }

    @Override
    public PageData findByid(BigInteger id) throws Exception {
        return (PageData) dao.findForObject("RoomMapper.selectByPrimaryKey", id);
    }

    @Override
    public boolean update(PageData pd) throws Exception {
        int a = (int) dao.update("RoomMapper.updateByPrimaryKey", pd);
        if(a == 1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean save(PageData pd) throws Exception {
        User u = UserInfoFactory.getCurrentUser();
        pd.put("created_by", u.getId());
        pd.put("created_time", new Date());
        int a = (int) dao.update("RoomMapper.updateByPrimaryKey", pd);
        if(a == 1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean delete(PageData pd) throws Exception {
        User u = UserInfoFactory.getCurrentUser();
        pd.put("updated_time", new Date());
        pd.put("updated_by", u.getId());
        int a = (int) dao.update("RoomMapper.deleteByPrimaryKey", pd);
        if(a == 1){
            return true;
        }else{
            return false;
        }
    }
}
