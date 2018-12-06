package com.yxt.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yxt.dao.DaoSupport;
import com.yxt.domain.entity.PageData;
import com.yxt.domain.entity.Person;
import com.yxt.officialVehicl.core.jwt.UserInfoFactory;
import com.yxt.officialVehicl.management.user.entity.User;
import com.yxt.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yuhao.wang on 2017/6/19.
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private DaoSupport dao;

    @Override
    public Page<PageData> findByPage(int pageNo, int pageSize, PageData pd) throws Exception {
        return null;
    }

    @Override
    public PageData findByid(BigInteger id) throws Exception {
        return (PageData) dao.findForObject("PersonMapper.selectByPrimaryKey", id);
    }

    @Override
    public boolean update(PageData pd) throws Exception {
        int a = (int) dao.update("PersonMapper.updateByPrimaryKey", pd);
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
        int a = (int) dao.update("PersonMapper.updateByPrimaryKey", pd);
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
        int a = (int) dao.update("PersonMapper.deleteByPrimaryKey", pd);
        if(a == 1){
            return true;
        }else{
            return false;
        }
    }

}
