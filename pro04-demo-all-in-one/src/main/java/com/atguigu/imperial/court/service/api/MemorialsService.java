package com.atguigu.imperial.court.service.api;
/*
 * @author  AmbitionJingH
 * @date  2023/8/14 23:30
 * @version 1.0
 */

import com.atguigu.imperial.court.entity.Memorials;

import java.util.List;

public interface MemorialsService {
    List<Memorials> getAllMemorialsDigest();

    Memorials getMemorialsById(String memorialsId);

    void updateMemorialsStatusToRead(String memorialsId);

    void updateMemorialsFeedBack(String memorialsId, String feedbackContent);
}
