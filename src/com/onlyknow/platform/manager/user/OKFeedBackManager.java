package com.onlyknow.platform.manager.user;

import com.onlyknow.platform.OKProjectConfig;
import com.onlyknow.platform.entity.FeedbackEntity;
import com.onlyknow.platform.entity.UserInfoEntity;
import com.onlyknow.platform.manager.OKBaseManager;
import org.hibernate.Session;

import java.util.Date;

public class OKFeedBackManager extends OKBaseManager {

    public void saveOrUpdate(FeedbackEntity entity) {
        Session session = open();

        session.beginTransaction();

        session.saveOrUpdate(entity);

        session.getTransaction().commit();
    }

    public boolean addFeedBack(String name, String msg, String url) {
        OKUserManager userManager = new OKUserManager();

        UserInfoEntity entity = userManager.getUserInfoByPlatForm(name);

        if (entity == null) return false;

        FeedbackEntity feedbackEntity = new FeedbackEntity();
        feedbackEntity.setUserName(name);
        feedbackEntity.setFbDescribe(msg);
        feedbackEntity.setFbImage(url);
        feedbackEntity.setFbDate(new Date());

        saveOrUpdate(feedbackEntity);

        entity.setMeIntegral(entity.getMeIntegral() + OKProjectConfig.ADD_CARD_INTEGRAL);

        userManager.saveOrUpdate(entity);

        return true;
    }
}
