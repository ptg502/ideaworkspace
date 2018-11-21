package com.aaa.sm.service;

import com.aaa.sm.entity.Emp;

import java.util.List;
import java.util.Map;

/**
 * className:EmpService
 * discriptoin:
 * author:zz
 * createTime:2018-11-09 16:15
 */
public interface EmpService {
    /**
     * 员工列表方法
     * @param map
     * @return
     */
    List<Emp> getList(Map map);

    /**
     * 调用存储过程，根据部门编号获取人员列表
     * @return
     */
    List<Emp> getProListByDeptNo(Integer deptNo);
    /**
     * 员工添加
     * @param mep
     * @return
     */
    int add(Emp mep);
}
