package com.atguigu.imperial.court.service.impl;
/*
 * @author  AmbitionJingH
 * @date  2023/8/14 23:30
 * @version 1.0
 */

import com.atguigu.imperial.court.dao.api.MemorialsDao;
import com.atguigu.imperial.court.dao.impl.MemorialsDaoImpl;
import com.atguigu.imperial.court.entity.Memorials;
import com.atguigu.imperial.court.service.api.MemorialsService;

import java.util.List;

public class MemorialsServiceImpl implements MemorialsService {
    private MemorialsDao memorialsDao = new MemorialsDaoImpl();

    @Override
    public List<Memorials> getAllMemorialsDigest() {
        return memorialsDao.getAllMemorialsDigest();
    }

    @Override
    public Memorials getMemorialsById(String memorialsId) {
        return memorialsDao.getMemorialsById(memorialsId);
    }
}
