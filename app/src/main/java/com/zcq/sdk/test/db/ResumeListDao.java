package com.zcq.sdk.test.db;

import com.zcq.sdk.test.entity.ResumeInfo;

import java.util.List;

/**
 * @ClassName ResumeListDao
 * @Description TODO
 * @Author Chandler
 * @Date 2020/3/25 17:58
 * @Version 1.0
 */
public interface ResumeListDao {
    List<ResumeInfo> getResumeList();
}
