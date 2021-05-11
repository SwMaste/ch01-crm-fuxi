package com.sw.crm.workbench.service.impl;

import com.sw.crm.utils.SqlSessionUtil;
import com.sw.crm.workbench.dao.ActivityDao;
import com.sw.crm.workbench.domain.Activity;
import com.sw.crm.workbench.service.ActivityService;

/**
 * @MASTER 统治世界的霸主 常狗狗
 * @date 2021/5/6
 */
public class ActivityServiceImpl implements ActivityService {
    private ActivityDao ad = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);
    @Override
    public boolean save(Activity activity) {
        int count = ad.save(activity);
        if (count != 1){
            return false;
        }
        return true;
    }
}
