package com.onlyknow.platform.manager.app;

import com.onlyknow.platform.entity.ImageCarouselAdEntity;
import com.onlyknow.platform.manager.OKBaseManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class OKCarouselAdManager extends OKBaseManager {

    public void saveOrUpdate(ImageCarouselAdEntity entity) {

        Session session = open();

        session.beginTransaction();

        session.saveOrUpdate(entity);

        session.getTransaction().commit();

    }

    public ImageCarouselAdEntity getNewCarouselAd() {

        Session session = open();

        session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<ImageCarouselAdEntity> cq = cb.createQuery(ImageCarouselAdEntity.class);

        Root<ImageCarouselAdEntity> root = cq.from(ImageCarouselAdEntity.class);

        cq.select(root).orderBy(cb.asc(root.get(ImageCarouselAdEntity.KEY_GROUP_ID)));

        Query<ImageCarouselAdEntity> query = session.createQuery(cq);

        session.getTransaction().commit();

        List<ImageCarouselAdEntity> list = query.getResultList();

        if (list == null || list.size() == 0) return null;

        return list.get(list.size() - 1);

    }

    public List<ImageCarouselAdEntity> list() {

        Session session = open();

        session.beginTransaction();

        Query<ImageCarouselAdEntity> query = session.createQuery("from ImageCarouselAdEntity ca order by ca.groupId asc ", ImageCarouselAdEntity.class); // HQL语句，它类似于SQL语句

        session.getTransaction().commit();

        List<ImageCarouselAdEntity> list = query.getResultList();

        return list;

    }
}
