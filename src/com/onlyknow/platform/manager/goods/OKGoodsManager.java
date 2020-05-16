package com.onlyknow.platform.manager.goods;

import com.onlyknow.platform.entity.GoodsEntity;
import com.onlyknow.platform.entity.GoodsPurchaseHistoryEntity;
import com.onlyknow.platform.entity.UserInfoEntity;
import com.onlyknow.platform.manager.OKBaseManager;
import com.onlyknow.platform.manager.user.OKUserManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class OKGoodsManager extends OKBaseManager {

    public void saveOrUpdateGoods(GoodsEntity entity) {
        Session session = open();

        session.beginTransaction();

        session.clear();

        session.flush();

        session.saveOrUpdate(entity);

        session.getTransaction().commit();
    }

    public void saveOrUpdateGoodsHis(GoodsPurchaseHistoryEntity entity) {
        Session session = open();

        session.beginTransaction();

        session.clear();

        session.flush();

        session.saveOrUpdate(entity);

        session.getTransaction().commit();
    }

    public GoodsEntity getGoodsById(int id) {
        Session session = open();

        session.beginTransaction();

        session.clear();

        session.flush();

        GoodsEntity entity = session.get(GoodsEntity.class, id);

        session.getTransaction().commit();

        return entity;
    }

    public List<GoodsEntity> list(String name, int page, int size) {
        Session session = open();

        session.beginTransaction();

        Query<GoodsEntity> queue = session.createQuery("from GoodsEntity gd order by gd.gdId asc", GoodsEntity.class);

        if (page == 1) {
            queue.setFirstResult(0);
        } else {
            queue.setFirstResult(page * size - size + 1);
        }

        queue.setMaxResults(size);

        session.getTransaction().commit();

        List<GoodsEntity> list = queue.getResultList();

        if (list == null) return null;

        GoodsEntity goodsEntity = null;
        for (int i = 0; i < list.size(); i++) {
            goodsEntity = list.get(i);

            goodsEntity.setGoodsBy(isGoodsBuy(goodsEntity.getGdId(), name));

            list.set(i, goodsEntity);
        }

        return list;
    }

    public boolean goodsBuy(int gdId, String name, String pass) {
        OKUserManager userManager = new OKUserManager();

        UserInfoEntity userInfoEntity = userManager.logInPlatForm(name, pass);

        if (userInfoEntity == null) return false;

        GoodsEntity goodsEntity = getGoodsById(gdId);

        if (goodsEntity == null) return false;

        if (userInfoEntity.getMeIntegral() < goodsEntity.getGdPrice()) return false;

        Session session = open();

        session.beginTransaction();

        Query<GoodsPurchaseHistoryEntity> query = session.createQuery("from GoodsPurchaseHistoryEntity gh where gh.userName=? and gh.gdId=?", GoodsPurchaseHistoryEntity.class);

        query.setParameter(0, name);
        query.setParameter(1, gdId);

        session.getTransaction().commit();

        GoodsPurchaseHistoryEntity goodsPurchaseHistoryEntity = query.uniqueResult();

        if (goodsPurchaseHistoryEntity != null) return false;

        goodsPurchaseHistoryEntity = new GoodsPurchaseHistoryEntity();

        goodsPurchaseHistoryEntity.setGdId(gdId);
        goodsPurchaseHistoryEntity.setUserName(name);
        goodsPurchaseHistoryEntity.setPrConsume(goodsEntity.getGdPrice());
        goodsPurchaseHistoryEntity.setPrDate(new Date());

        saveOrUpdateGoodsHis(goodsPurchaseHistoryEntity);

        userInfoEntity.setMeIntegral(userInfoEntity.getMeIntegral() - goodsEntity.getGdPrice());

        if (goodsEntity.getGdType().equalsIgnoreCase(GoodsEntity.GoodsType.APPROVAL.toString())) {
            userInfoEntity.setWithoutApprova(true);
        }

        userManager.saveOrUpdate(userInfoEntity);

        return true;
    }

    public boolean isGoodsBuy(int gdId, String name) {
        Session session = open();

        session.beginTransaction();

        session.clear();

        session.flush();

        Query<GoodsPurchaseHistoryEntity> queue = session.createQuery("from GoodsPurchaseHistoryEntity gh where gh.userName=? and gh.gdId=?", GoodsPurchaseHistoryEntity.class);

        queue.setParameter(0, name);
        queue.setParameter(1, gdId);

        session.getTransaction().commit();

        GoodsPurchaseHistoryEntity entity = queue.uniqueResult();

        if (entity == null) return false;

        return true;
    }
}
