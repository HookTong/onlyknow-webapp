package com.onlyknow.platform.manager.card;

import com.onlyknow.platform.OKProjectConfig;
import com.onlyknow.platform.entity.*;
import com.onlyknow.platform.entity.app.OKUserCommentCardBean;
import com.onlyknow.platform.entity.app.OKCardRelatedBean;
import com.onlyknow.platform.entity.app.OKSearchBean;
import com.onlyknow.platform.manager.OKBaseManager;
import com.onlyknow.platform.manager.comment.OKCommentManager;
import com.onlyknow.platform.manager.user.OKUserManager;
import com.onlyknow.platform.utils.OKStringUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OKCardManager extends OKBaseManager {

    public void saveOrUpdateCard(CardInfoEntity entity) {
        Session session = open();

        session.beginTransaction();

        session.clear();

        session.flush();

        session.saveOrUpdate(entity);

        session.getTransaction().commit();
    }

    public void saveOrUpdateCardPraise(PraiseCardEntity entity) {
        Session session = open();

        session.beginTransaction();

        session.clear();

        session.flush();

        session.saveOrUpdate(entity);

        session.getTransaction().commit();
    }

    public void saveOrUpdateCardWatch(UserWatchEntity entity) {
        Session session = open();

        session.beginTransaction();

        session.clear();

        session.flush();

        session.saveOrUpdate(entity);

        session.getTransaction().commit();
    }

    public void saveOrUpdateCardBrowsing(CardBrowsingEntity entity) {
        Session session = open();

        session.beginTransaction();

        session.clear();

        session.flush();

        session.saveOrUpdate(entity);

        session.getTransaction().commit();
    }

    public boolean addCard(CardInfoEntity entity, String name) {
        if (entity == null || OKStringUtil.isEmpty(entity.getCardType()) || OKStringUtil.isEmpty(name)) return false;

        OKUserManager userManager = new OKUserManager();

        UserInfoEntity userInfoEntity = userManager.getUserInfoByPlatForm(name);

        if (userInfoEntity == null) return false;

        entity.setUserName(userInfoEntity.getUserName());
        entity.setTitleImageUrl(userInfoEntity.getHeadPortraitUrl());
        entity.setTitleText(userInfoEntity.getUserNickname());
        entity.setBrowsingCount(0);
        entity.setPraiseCount(0);
        entity.setWatchCount(0);
        entity.setCommentCount(0);

        // 设置是否需要审批
        boolean b = userInfoEntity.isWithoutApprova();
        entity.setApproveBy(b ? 1 : 0);
        entity.setApproveInfo(b ? OKProjectConfig.HAVE_APPROVE_INFO : OKProjectConfig.NO_APPROVE_INFO);
        entity.setCreateDate(new Date());

        saveOrUpdateCard(entity);

        userInfoEntity.setMeIntegral(userInfoEntity.getMeIntegral() + OKProjectConfig.ADD_CARD_INTEGRAL);
        userInfoEntity.setMeArticle(userInfoEntity.getMeArticle() + 1);

        userManager.saveOrUpdate(userInfoEntity);

        return true;
    }

    public CardInfoEntity getCardInfoById(int id) {
        Session session = open();

        session.beginTransaction();

        session.clear();

        session.flush();

        CardInfoEntity cardInfoEntity = session.get(CardInfoEntity.class, id);

        session.getTransaction().commit();

        return cardInfoEntity;
    }

    public List<CardInfoEntity> list(boolean ignoreApprove, int page, int size) {
        Session session = open();

        session.beginTransaction();

        Query<CardInfoEntity> query;

        if (ignoreApprove) {
            query = session.createQuery("from CardInfoEntity info order by info.cardId desc", CardInfoEntity.class);
        } else {
            query = session.createQuery("from CardInfoEntity info where info.approveBy=1 order by info.cardId desc", CardInfoEntity.class);
        }

        if (page == 1) {
            query.setFirstResult(0);
        } else {
            query.setFirstResult(page * size - size + 1);
        }

        query.setMaxResults(size);

        session.getTransaction().commit();

        return query.getResultList();
    }

    public List<OKUserCommentCardBean> listByComment(String username, int page, int size) {
        OKCommentManager commentManager = new OKCommentManager();

        List<CommentEntity> list = commentManager.listBySingleCardId(username, page, size);

        if (list == null || list.size() == 0) return null;

        List<OKUserCommentCardBean> resultList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            CommentEntity commentEntity = list.get(i);

            CardInfoEntity cardInfoEntity = getCardInfoById(commentEntity.getCardId());

            if (cardInfoEntity != null) {
                OKUserCommentCardBean bean = new OKUserCommentCardBean();
                bean.setCardBean(cardInfoEntity);
                bean.setCommentBean(commentEntity);
                resultList.add(bean);
            }
        }

        return resultList;
    }

    public List<CardInfoEntity> listByHome(boolean isApproveIn, String username, int page, int size) {
        Session session = open();

        session.beginTransaction();

        Query<CardInfoEntity> query;

        if (isApproveIn) {
            query = session.createQuery("from CardInfoEntity info where info.userName=? and info.approveBy!=1 order by info.cardId desc", CardInfoEntity.class);
        } else {
            query = session.createQuery("from CardInfoEntity info where info.userName=? and info.approveBy=1 order by info.cardId desc", CardInfoEntity.class);
        }

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

    public List<CardInfoEntity> listByHot(int size) {
        Session session = open();

        session.beginTransaction();

        Query<CardInfoEntity> query = session.createQuery("from CardInfoEntity info where info.approveBy=1 order by info.browsingCount desc", CardInfoEntity.class);

        query.setFirstResult(0);

        query.setMaxResults(size);

        session.getTransaction().commit();

        return query.getResultList();
    }

    public List<CardInfoEntity> listByNear(String name, double longitude, double latitude, int page, int size) {
        OKUserManager userManager = new OKUserManager();

        List<String> nearUserList = userManager.getNearUserNameByLocation(name, longitude, latitude);

        if (nearUserList == null || nearUserList.size() == 0) return null;

        Session session = open();

        session.beginTransaction();

        Query<CardInfoEntity> query = session.createQuery("from CardInfoEntity info where info.approveBy=1 and info.userName in (:names) order by info.cardId desc", CardInfoEntity.class);

        query.setParameterList("names", nearUserList);

        if (page == 1) {
            query.setFirstResult(0);
        } else {
            query.setFirstResult(page * size - size + 1);
        }

        query.setMaxResults(size);

        session.getTransaction().commit();

        return query.getResultList();
    }

    public List<CardInfoEntity> listByWatch(String name, int page, int size) {
        Session session = open();

        session.beginTransaction();

        Query<CardInfoEntity> query = session.createQuery("from CardInfoEntity info where info.approveBy=1 and info.cardId in (select uw.cardId from UserWatchEntity uw where uw.userName=?) order by info.cardId desc", CardInfoEntity.class);

        query.setParameter(0, name);

        if (page == 1) {
            query.setFirstResult(0);
        } else {
            query.setFirstResult(page * size - size + 1);
        }

        query.setMaxResults(size);

        session.getTransaction().commit();

        return query.getResultList();
    }

    public List<CardInfoEntity> allListByName(String name) {
        Session session = open();

        session.beginTransaction();

        Query<CardInfoEntity> query = session.createQuery("from CardInfoEntity info where info.userName=? order by info.cardId asc", CardInfoEntity.class);

        query.setParameter(0, name);

        session.getTransaction().commit();

        return query.getResultList();
    }

    public boolean updateCardTitleImage(String name, String url) {
        Session session = open();

        session.beginTransaction();

        Query query = session.createQuery("update CardInfoEntity info set info.titleImageUrl=? where info.userName=?");

        query.setParameter(0, url);
        query.setParameter(1, name);

        int i = query.executeUpdate();

        session.getTransaction().commit();

        // 清理缓存
        session.beginTransaction();

        session.clear();

        session.flush();

        session.getTransaction().commit();

        return i != -1 ? true : false;
    }

    public boolean updateCardTitleText(String name, String titleText) {
        Session session = open();

        session.beginTransaction();

        Query query = session.createQuery("update CardInfoEntity info set info.titleText=? where info.userName=?");

        query.setParameter(0, titleText);
        query.setParameter(1, name);

        int i = query.executeUpdate();

        session.getTransaction().commit();

        // 清理缓存
        session.beginTransaction();

        session.clear();

        session.flush();

        session.getTransaction().commit();

        return i != -1 ? true : false;
    }

    public boolean deleteCard(int id, String name, String pass) {
        OKUserManager userManager = new OKUserManager();

        UserInfoEntity userInfoEntity = userManager.logInPlatForm(name, pass);

        if (userInfoEntity == null) return false;

        CardInfoEntity entity = getCardInfoById(id);

        if (entity == null) return false;

        if (!entity.getUserName().equals(name)) return false;

        deleteCard(entity);

        userInfoEntity.setMeArticle(userInfoEntity.getMeArticle() - 1);

        userManager.saveOrUpdate(userInfoEntity);

        return true;
    }

    private void deleteWatch(UserWatchEntity entity) {
        Session session = open();

        session.beginTransaction();

        session.delete(entity);

        session.getTransaction().commit();
    }

    private void deleteCard(CardInfoEntity entity) {
        Session session = open();

        session.beginTransaction();

        session.delete(entity);

        session.getTransaction().commit();
    }

    public boolean watchCard(int id, String name) {
        OKUserManager userManager = new OKUserManager();

        UserInfoEntity userInfoEntity = userManager.getUserInfoByPlatForm(name);

        if (userInfoEntity == null) return false;

        CardInfoEntity cardInfoEntity = getCardInfoById(id);

        if (cardInfoEntity == null) return false;

        Session session = open();

        session.beginTransaction();

        Query<UserWatchEntity> query = session.createQuery("from UserWatchEntity info where info.cardId=? and info.userName=?", UserWatchEntity.class);

        query.setParameter(0, id);
        query.setParameter(1, name);

        session.getTransaction().commit();

        UserWatchEntity entity = query.uniqueResult();

        if (entity != null) return false;

        entity = new UserWatchEntity();
        entity.setCardId(id);
        entity.setUserName(name);
        entity.setUwDate(new Date());

        saveOrUpdateCardWatch(entity);

        cardInfoEntity.setWatchCount(cardInfoEntity.getWatchCount() + 1);

        saveOrUpdateCard(cardInfoEntity);

        userInfoEntity.setMeWatch(userInfoEntity.getMeWatch() + 1);
        userInfoEntity.setMeIntegral(userInfoEntity.getMeIntegral() + OKProjectConfig.WATCH_INTEGRAL);

        userManager.saveOrUpdate(userInfoEntity);

        return true;
    }

    public boolean deleteWatch(int id, String name, String pass) {
        OKUserManager userManager = new OKUserManager();

        UserInfoEntity userInfoEntity = userManager.logInPlatForm(name, pass);

        if (userInfoEntity == null) return false;

        CardInfoEntity cardInfoEntity = getCardInfoById(id);

        if (cardInfoEntity == null) return false;

        Session session = open();

        session.beginTransaction();

        Query<UserWatchEntity> query = session.createQuery("from UserWatchEntity info where info.cardId=? and info.userName=?", UserWatchEntity.class);

        query.setParameter(0, id);
        query.setParameter(1, name);

        session.getTransaction().commit();

        UserWatchEntity entity = query.uniqueResult();

        if (entity == null) return false;

        deleteWatch(entity);

        cardInfoEntity.setWatchCount(cardInfoEntity.getWatchCount() - 1);

        saveOrUpdateCard(cardInfoEntity);

        userInfoEntity.setMeWatch(userInfoEntity.getMeWatch() - 1);

        userManager.saveOrUpdate(userInfoEntity);

        return true;
    }

    public boolean praiseCard(int id, String name) {
        OKUserManager userManager = new OKUserManager();

        UserInfoEntity userInfoEntity = userManager.getUserInfoByPlatForm(name);

        if (userInfoEntity == null) return false;

        CardInfoEntity cardInfoEntity = getCardInfoById(id);

        if (cardInfoEntity == null) return false;

        Session session = open();

        session.beginTransaction();

        Query<PraiseCardEntity> query = session.createQuery("from PraiseCardEntity info where info.cardId=? and info.userName=?", PraiseCardEntity.class);

        query.setParameter(0, id);
        query.setParameter(1, name);

        session.getTransaction().commit();

        PraiseCardEntity entity = query.uniqueResult();

        if (entity != null) return false;

        entity = new PraiseCardEntity();
        entity.setCardId(id);
        entity.setUserName(name);
        entity.setCpDate(new Date());

        saveOrUpdateCardPraise(entity);

        cardInfoEntity.setPraiseCount(cardInfoEntity.getPraiseCount() + 1);

        saveOrUpdateCard(cardInfoEntity);

        userInfoEntity.setMeIntegral(userInfoEntity.getMeIntegral() + OKProjectConfig.PRAISE_INTEGRAL);

        userManager.saveOrUpdate(userInfoEntity);

        return true;
    }

    public List<OKSearchBean> searchCard(String search, int page, int size) {

        Session session = open();

        session.beginTransaction();

        Query<CardInfoEntity> query = session.createQuery("from CardInfoEntity ci where ci.userName like ? or ci.titleText like ? or ci.contentTitleText like ? or ci.contentText like ? order by ci.cardId asc", CardInfoEntity.class);

        query.setParameter(0, "%" + search + "%");
        query.setParameter(1, "%" + search + "%");
        query.setParameter(2, "%" + search + "%");
        query.setParameter(3, "%" + search + "%");

        if (page == 1) {
            query.setFirstResult(0);
        } else {
            query.setFirstResult(page * size - size + 1);
        }

        query.setMaxResults(size);

        session.getTransaction().commit();

        List<CardInfoEntity> list = query.getResultList();

        if (list == null) return null;

        List<OKSearchBean> searchBeanList = new ArrayList<>();
        OKSearchBean bean = null;
        for (int i = 0; i < list.size(); i++) {
            bean = new OKSearchBean();
            bean.setCardBean(list.get(i));
            bean.setType(OKSearchBean.SEARCH_TYPE.CARD);
            searchBeanList.add(bean);
        }

        return searchBeanList;
    }

    public OKCardRelatedBean cardRelated(int id, String name) {
        OKUserManager userManager = new OKUserManager();

        UserInfoEntity userInfoEntity = userManager.getUserInfoByPlatForm(name);

        if (userInfoEntity == null) return null;

        OKCardRelatedBean bindBean = new OKCardRelatedBean();

        CardInfoEntity cardInfoEntity = getCardInfoById(id);

        if (cardInfoEntity == null) {
            bindBean.setCardRemove(true);
            bindBean.setAttention(false);
            bindBean.setPraise(false);
            bindBean.setWatch(false);
            bindBean.setWatchCount(0);
            bindBean.setPraiseCount(0);
            bindBean.setCommentCount(0);
            bindBean.setCardId(id);
            bindBean.setUserName(userInfoEntity.getUserName());
            bindBean.setTag(userInfoEntity.getTag());

            return bindBean;
        }

        Session session = open();

        session.beginTransaction();

        // 点赞检查
        Query<PraiseCardEntity> praiseCardEntityQuery = session.createQuery("from PraiseCardEntity info where info.cardId=? and info.userName=?", PraiseCardEntity.class);

        praiseCardEntityQuery.setParameter(0, id);
        praiseCardEntityQuery.setParameter(1, name);

        session.getTransaction().commit();

        PraiseCardEntity praiseCardEntity = praiseCardEntityQuery.uniqueResult();

        if (praiseCardEntity != null) {
            bindBean.setPraise(true);
        } else {
            bindBean.setPraise(false);
        }

        session.beginTransaction();

        // 收藏检查
        Query<UserWatchEntity> userWatchEntityQuery = session.createQuery("from UserWatchEntity info where info.cardId=? and info.userName=?", UserWatchEntity.class);

        userWatchEntityQuery.setParameter(0, id);
        userWatchEntityQuery.setParameter(1, name);

        session.getTransaction().commit();

        UserWatchEntity userWatchEntity = userWatchEntityQuery.uniqueResult();

        if (userWatchEntity != null) {
            bindBean.setWatch(true);
        } else {
            bindBean.setWatch(false);
        }

        session.beginTransaction();

        // 卡片发表用户关注检查
        Query<UserAttentionEntity> userAttentionEntityQuery = session.createQuery("from UserAttentionEntity info where info.userNameMain=? and info.userNameRete=?", UserAttentionEntity.class);

        userAttentionEntityQuery.setParameter(0, name);
        userAttentionEntityQuery.setParameter(1, cardInfoEntity.getUserName());

        session.getTransaction().commit();

        UserAttentionEntity userAttentionEntity = userAttentionEntityQuery.uniqueResult();

        if (userAttentionEntity != null) {
            bindBean.setAttention(true);
        } else {
            bindBean.setAttention(false);
        }

        bindBean.setCardId(id);
        bindBean.setUserName(name);
        bindBean.setTag(userInfoEntity.getTag());
        bindBean.setPraiseCount(cardInfoEntity.getPraiseCount());
        bindBean.setWatchCount(cardInfoEntity.getWatchCount());
        bindBean.setCommentCount(cardInfoEntity.getCommentCount());

        return bindBean;
    }

    public boolean cardBrowsing(int id, String name) {
        OKUserManager userManager = new OKUserManager();

        UserInfoEntity userInfoEntity = userManager.getUserInfoByPlatForm(name);

        if (userInfoEntity == null) return false;

        CardInfoEntity cardInfoEntity = getCardInfoById(id);

        if (cardInfoEntity == null) return false;

        Session session = open();

        session.beginTransaction();

        // 卡片浏览检查
        Query<CardBrowsingEntity> query = session.createQuery("from CardBrowsingEntity info where info.userName=? and info.cardId=?", CardBrowsingEntity.class);

        query.setParameter(0, name);
        query.setParameter(1, id);

        session.getTransaction().commit();

        CardBrowsingEntity cardBrowsingEntity = query.uniqueResult();

        if (cardBrowsingEntity != null) return true;

        cardBrowsingEntity = new CardBrowsingEntity();
        cardBrowsingEntity.setCardId(id);
        cardBrowsingEntity.setUserName(name);
        cardBrowsingEntity.setCbDate(new Date());

        saveOrUpdateCardBrowsing(cardBrowsingEntity);

        cardInfoEntity.setBrowsingCount(cardInfoEntity.getBrowsingCount() + 1);

        saveOrUpdateCard(cardInfoEntity);

        return true;
    }
}
