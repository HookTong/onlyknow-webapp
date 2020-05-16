package com.onlyknow.platform.manager.comment;

import com.onlyknow.platform.OKProjectConfig;
import com.onlyknow.platform.entity.CardInfoEntity;
import com.onlyknow.platform.entity.CommentEntity;
import com.onlyknow.platform.entity.PraiseCommentEntity;
import com.onlyknow.platform.entity.UserInfoEntity;
import com.onlyknow.platform.manager.OKBaseManager;
import com.onlyknow.platform.manager.card.OKCardManager;
import com.onlyknow.platform.manager.user.OKUserManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class OKCommentManager extends OKBaseManager {

    public void saveOrUpdateComment(CommentEntity entity) {
        Session session = open();

        session.beginTransaction();

        session.clear();

        session.flush();

        session.saveOrUpdate(entity);

        session.getTransaction().commit();
    }

    public void saveOrUpdatePraiseComment(PraiseCommentEntity entity) {
        Session session = open();

        session.beginTransaction();

        session.clear();

        session.flush();

        session.saveOrUpdate(entity);

        session.getTransaction().commit();
    }

    public List<CommentEntity> listByCardId(int cardId, String name, int page, int size) {
        Session session = open();

        session.beginTransaction();

        Query<CommentEntity> query = session.createQuery("from CommentEntity ce where ce.cardId=? order by ce.comId asc ", CommentEntity.class);

        query.setParameter(0, cardId);

        if (page == 1) {
            query.setFirstResult(0);
        } else {
            query.setFirstResult(page * size - size + 1);
        }

        query.setMaxResults(size);

        session.getTransaction().commit();

        List<CommentEntity> list = query.getResultList();

        if (list == null) return null;

        OKUserManager userManager = new OKUserManager();
        UserInfoEntity userInfoEntity = null;
        CommentEntity entity = null;

        for (int i = 0; i < list.size(); i++) {
            entity = list.get(i);

            userInfoEntity = userManager.getUserInfoByPlatForm(entity.getUserName());

            if (userInfoEntity == null) continue;

            entity.setAvatar(userInfoEntity.getHeadPortraitUrl());
            entity.setNickName(userInfoEntity.getUserNickname());

            entity.setPraise(isPraise(entity.getComId(), name));

            list.set(i, entity);
        }

        return list;
    }

    public List<CommentEntity> listBySingleCardId(String username, int page, int size) {
        Session session = open();

        session.beginTransaction();

        Query<CommentEntity> query = session.createQuery("from CommentEntity ce where ce.comId in (select max(co.comId) from CommentEntity co where co.userName=? group by co.cardId) order by ce.cardId desc", CommentEntity.class); // HQL语句，它类似于SQL语句

        query.setParameter(0, username);

        if (page == 1) {
            query.setFirstResult(0);
        } else {
            query.setFirstResult(page * size - size + 1);
        }

        query.setMaxResults(size);

        session.getTransaction().commit();

        return query.getResultList();
    }

    public CommentEntity getCommentById(int comId) {
        Session session = open();

        session.beginTransaction();

        session.clear();

        session.flush();

        CommentEntity entity = session.get(CommentEntity.class, comId);

        session.getTransaction().commit();

        return entity;
    }

    public boolean addComment(String username, int cardId, String msg) {
        OKCardManager cardManager = new OKCardManager();

        CardInfoEntity cardInfoEntity = cardManager.getCardInfoById(cardId);

        if (cardInfoEntity == null) return false;

        OKUserManager userManager = new OKUserManager();

        UserInfoEntity userInfoEntity = userManager.getUserInfoByPlatForm(username);

        if (userInfoEntity == null) return false;

        CommentEntity entity = new CommentEntity();
        entity.setUserName(username);
        entity.setCardId(cardId);
        entity.setMessage(msg);
        entity.setComPraise(0);
        entity.setComDate(new Date());

        saveOrUpdateComment(entity);

        cardInfoEntity.setCommentCount(cardInfoEntity.getCommentCount() + 1);

        cardManager.saveOrUpdateCard(cardInfoEntity);

        return true;
    }

    public boolean praiseComment(String name, int comId) {
        OKUserManager userManager = new OKUserManager();

        UserInfoEntity userInfoEntity = userManager.getUserInfoByPlatForm(name);

        if (userInfoEntity == null) return false;

        CommentEntity commentEntity = getCommentById(comId);

        if (commentEntity == null) return false;

        Session session = open();

        session.beginTransaction();

        Query<PraiseCommentEntity> query = session.createQuery("from PraiseCommentEntity pc where pc.userName=? and pc.comId=?", PraiseCommentEntity.class); // HQL语句，它类似于SQL语句

        query.setParameter(0, name);
        query.setParameter(1, comId);

        session.getTransaction().commit();

        PraiseCommentEntity entity = query.uniqueResult();

        if (entity != null) return false;

        entity = new PraiseCommentEntity();
        entity.setComId(comId);
        entity.setUserName(name);
        entity.setCpDate(new Date());

        saveOrUpdatePraiseComment(entity);

        commentEntity.setComPraise(commentEntity.getComPraise() + 1);

        saveOrUpdateComment(commentEntity);

        userInfoEntity.setMeIntegral(userInfoEntity.getMeIntegral() + OKProjectConfig.PRAISE_INTEGRAL);

        userManager.saveOrUpdate(userInfoEntity);

        return true;
    }

    public boolean isPraise(int comId, String name) {
        Session session = open();

        session.beginTransaction();

        Query<PraiseCommentEntity> query = session.createQuery("from PraiseCommentEntity pc where pc.userName=? and pc.comId=?", PraiseCommentEntity.class);

        query.setParameter(0, name);
        query.setParameter(1, comId);

        session.getTransaction().commit();

        PraiseCommentEntity entity = query.uniqueResult();

        if (entity == null) return false;

        return true;
    }
}
