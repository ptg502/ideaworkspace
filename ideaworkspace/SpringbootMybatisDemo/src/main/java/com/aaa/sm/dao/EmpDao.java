package com.aaa.sm.dao;

import com.aaa.sm.entity.Emp;

import java.util.List;
import java.util.Map;

/**
 * className:EmpDao
 * discriptoin:
 * author:zz
 * createTime:2018-11-09 15:50
 */
public interface EmpDao {

    /**
     * 员工列表方法
     * @param map
     * @return
     */
     List<Emp> getList(Map map);

    /**
     * 调用存储过程，根据部门编号获取人员列表
     * @param map
     * @return
     */
     List<Emp> getProListByDeptNo(Map map);
    /**
     * 员工添加
     * @param mep
     * @return
     */
    int add(Emp mep);
}
