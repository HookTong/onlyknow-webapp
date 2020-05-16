package com.onlyknow.platform.manager.comment;

import com.onlyknow.platform.OKProjectConfig;
import com.onlyknow.platform.entity.CommentEntity;
import com.onlyknow.platform.entity.CommentReplyEntity;
import com.onlyknow.platform.entity.PraiseCommentreplyEntity;
import com.onlyknow.platform.entity.UserInfoEntity;
import com.onlyknow.platform.manager.OKBaseManager;
import com.onlyknow.platform.manager.user.OKUserManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class OKCommentReplyManager extends OKBaseManager {

    public void saveOrUpdateCommentReply(CommentReplyEntity entity) {
        Session session = open();

        session.beginTransaction();

        session.clear();

        session.flush();

        session.saveOrUpdate(entity);

        session.getTransaction().commit();
    }

    public void saveOrUpdateCommentReplyPraise(PraiseCommentreplyEntity entity) {
        Session session = open();

        session.beginTransaction();

        session.clear();

        session.flush();

        session.saveOrUpdate(entity);

        session.getTransaction().commit();
    }

    public CommentReplyEntity getCommentReplyById(int id) {
        Session session = open();

        session.beginTransaction();

        session.clear();

        session.flush();

        CommentReplyEntity entity = session.get(CommentReplyEntity.class, id);

        session.getTransaction().commit();

        return entity;
    }

    public List<CommentReplyEntity> listByComId(int comId, String name, int page, int size) {
        Session session = open();

        session.beginTransaction();

        Query<CommentReplyEntity> query = session.createQuery("from CommentReplyEntity ce where ce.comId=? order by ce.comrId asc ", CommentReplyEntity.class); // HQL语句，它类似于SQL语句

        query.setParameter(0, comId);

        if (page == 1) {
            query.setFirstResult(0);
        } else {
            query.setFirstResult(page * size - size + 1);
        }

        query.setMaxResults(size);

        session.getTransaction().commit();

        List<CommentReplyEntity> list = query.getResultList();

        if (list == null) return null;

        OKUserManager userManager = new OKUserManager();
        UserInfoEntity userInfoEntity = null;
        CommentReplyEntity entity = null;

        for (int i = 0; i < list.size(); i++) {
            entity = list.get(i);

            userInfoEntity = userManager.getUserInfoByPlatForm(entity.getUserName());

            if (userInfoEntity == null) continue;

            entity.setAvatar(userInfoEntity.getHeadPortraitUrl());
            entity.setNickName(userInfoEntity.getUserNickname());

            entity.setPraise(isPraise(entity.getComrId(), name));

            list.set(i, entity);
        }

        return list;
    }

    public boolean addCommentReply(String username, int comId, String msg) {
        OKCommentManager commentManager = new OKCommentManager();

        CommentEntity commentEntity = commentManager.getCommentById(comId);

        if (commentEntity == null) return false;

        OKUserManager userManager = new OKUserManager();

        UserInfoEntity userInfoEntity = userManager.getUserInfoByPlatForm(username);

        if (userInfoEntity == null) return false;

        CommentReplyEntity entity = new CommentReplyEntity();
        entity.setUserName(username);
        entity.setComId(comId);
        entity.setMessage(msg);
        entity.setComrPraise(0);
        entity.setComrDate(new Date());

        saveOrUpdateCommentReply(entity);

        return true;
    }

    public boolean praiseCommentReply(String name, int comrId) {
        OKUserManager userManager = new OKUserManager();

        UserInfoEntity userInfoEntity = userManager.getUserInfoByPlatForm(name);

        if (userInfoEntity == null) return false;

        CommentReplyEntity commentReplyEntity = getCommentReplyById(comrId);

        if (commentReplyEntity == null) return false;

        Session session = open();

        session.beginTransaction();

        Query<PraiseCommentreplyEntity> query = session.createQuery("from PraiseCommentreplyEntity pc where pc.userName=? and pc.comrId=?", PraiseCommentreplyEntity.class); // HQL语句，它类似于SQL语句

        query.setParameter(0, name);
        query.setParameter(1, comrId);

        session.getTransaction().commit();

        PraiseCommentreplyEntity entity = query.uniqueResult();

        if (entity != null) return false;

        entity = new PraiseCommentreplyEntity();
        entity.setComrId(comrId);
        entity.setUserName(name);
        entity.setCrpDate(new Date());

        saveOrUpdateCommentReplyPraise(entity);

        commentReplyEntity.setComrPraise(commentReplyEntity.getComrPraise() + 1);

        saveOrUpdateCommentReply(commentReplyEntity);

        userInfoEntity.setMeIntegral(userInfoEntity.getMeIntegral() + OKProjectConfig.PRAISE_INTEGRAL);

        userManager.saveOrUpdate(userInfoEntity);

        return true;
    }

    public boolean isPraise(int comrId, String name) {
        Session session = open();

        session.beginTransaction();

        Query<PraiseCommentreplyEntity> query = session.createQuery("from PraiseCommentreplyEntity pc where pc.userName=? and pc.comrId=?", PraiseCommentreplyEntity.class);

        query.setParameter(0, name);
        query.setParameter(1, comrId);

        session.getTransaction().commit();

        PraiseCommentreplyEntity entity = query.uniqueResult();

        if (entity == null) return false;

        return true;
    }
}
