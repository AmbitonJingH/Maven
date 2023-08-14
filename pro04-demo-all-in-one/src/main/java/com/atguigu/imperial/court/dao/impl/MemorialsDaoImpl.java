package com.atguigu.imperial.court.dao.impl;

import com.atguigu.imperial.court.dao.BaseDao;
import com.atguigu.imperial.court.dao.api.MemorialsDao;
import com.atguigu.imperial.court.entity.Memorials;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MemorialsDaoImpl extends BaseDao<Memorials> implements MemorialsDao {

    @Override
    public List<Memorials> getAllMemorialsDigest() {
        String sql = "select memorials_id memorialsId,\n" +
                "       memorials_title memorialsTitle,\n" +
                "       concat(left(memorials_content, 10), \"...\") memorialsContentDigest,\n" +
                "       emp_name memorialsEmpName,\n" +
                "       memorials_create_time memorialsCreateTime,\n" +
                "       memorials_status memorialsStatus\n" +
                "from t_memorials m left join  t_emp e on m.memorials_emp=e.emp_id;";
        List<Memorials> memorialsList = getBeanList(sql, Memorials.class);
        return memorialsList;
    }

    @Override
    public Memorials getMemorialsById(String memorialsId) {
        String sql = "select memorials_id memorialsId,\n" +
                "       memorials_title memorialsTitle,\n" +
                "       memorials_content memorialsContent,\n" +
                "       emp_name memorialsEmpName,\n" +
                "       memorials_create_time memorialsCreateTime,\n" +
                "       memorials_status memorialsStatus,\n" +
                "       feedback_time feedbackTime,\n" +
                "       feedback_content feedbackContent\n" +
                "from t_memorials m left join  t_emp e on m.memorials_emp=e.emp_id where memorials_id=?;";
        return getSingleBean(sql, Memorials.class,memorialsId);
    }
}
