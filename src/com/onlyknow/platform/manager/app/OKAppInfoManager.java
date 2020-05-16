package com.onlyknow.platform.manager.app;

import com.onlyknow.platform.entity.AppInfoEntity;
import com.onlyknow.platform.manager.OKBaseManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class OKAppInfoManager extends OKBaseManager {

    public void saveOrUpdate(AppInfoEntity entity) {

        Session session = open();

        session.beginTransaction();

        session.saveOrUpdate(entity);

        session.getTransaction().commit();
    }

    public void delete(AppInfoEntity entity) {

        Session session = open();

        session.beginTransaction();

        session.delete(entity);

        session.getTransaction().commit();

    }

    public AppInfoEntity getNewAppInfo() {

        Session session = open();

        session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<AppInfoEntity> cq = cb.createQuery(AppInfoEntity.class);

        Root<AppInfoEntity> root = cq.from(AppInfoEntity.class);

        cq.select(root).orderBy(cb.asc(root.get(AppInfoEntity.KEY_APP_ID)));

        Query<AppInfoEntity> query = session.createQuery(cq);

        session.getTransaction().commit();

        List<AppInfoEntity> list = query.getResultList();

        if (list == null || list.size() == 0) return null;

        return list.get(list.size() - 1);

    }

    public List<AppInfoEntity> list() {

        Session session = open();

        session.beginTransaction();

        Query<AppInfoEntity> query = session.createQuery("from AppInfoEntity info order by info.appId asc ", AppInfoEntity.class); // HQL语句，它类似于SQL语句

        session.getTransaction().commit();

        List<AppInfoEntity> list = query.getResultList();

        return list;

    }
}
