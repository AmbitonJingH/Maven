package com.atguigu.imperial.court.dao.api;

import com.atguigu.imperial.court.entity.Memorials;

import java.util.List;

public interface MemorialsDao {

    List<Memorials> getAllMemorialsDigest();

    Memorials getMemorialsById(String memorialsId);

    void updateMemorialsStatusToRead(String memorialsId);

    void updateMemorialsFeedBack(String memorialsId, String feedbackContent);
}
