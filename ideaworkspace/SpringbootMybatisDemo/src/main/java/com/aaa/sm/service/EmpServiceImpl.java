package com.aaa.sm.service;

import com.aaa.sm.dao.EmpDao;
import com.aaa.sm.entity.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:EmpServiceImpl
 * discriptoin:
 * author:zz
 * createTime:2018-11-09 16:16
 */
@Service
@Transactional
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpDao empDao;

    @Override
    public List<Emp> getList(Map map) {
        map.put("sort",map.get("sort")==null?"empno":map.get("sort"));
        map.put("order",map.get("order")==null?"asc":map.get("order"));
        return empDao.getList(map);
    }

    @Override
    public List<Emp> getProListByDeptNo(Integer deptNo) {
        Map map = new HashMap();
        map.put("deptNo",deptNo);
        //调用了根据存储过程获取列表方法之后
        empDao.getProListByDeptNo(map);
        //调用返回结果
        List<Emp> csrEmp = (List<Emp>) map.get("csrEmp");
        return csrEmp;
    }

    @Override
    public int add(Emp mep) {
        int add = empDao.add(mep);
       // System.out.println(1/0);
        return add;
    }
}
