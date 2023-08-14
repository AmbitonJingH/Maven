package com.atguigu.imperial.court.service.api;
/*
 * @author  AmbitionJingH
 * @date  2023/8/14 18:05
 * @version 1.0
 */

import com.atguigu.imperial.court.entity.Emp;

public interface EmpService {
    Emp getEmpByLoginAccount(String loginAccount, String loginPassword);
}
