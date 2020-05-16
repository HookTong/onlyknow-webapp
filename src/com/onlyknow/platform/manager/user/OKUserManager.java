package com.onlyknow.platform.manager.user;

import com.google.gson.Gson;
import com.onlyknow.platform.OKConstants;
import com.onlyknow.platform.OKProjectConfig;
import com.onlyknow.platform.OKServiceResult;
import com.onlyknow.platform.entity.UserAttentionEntity;
import com.onlyknow.platform.entity.UserInfoEntity;
import com.onlyknow.platform.entity.UserLocationEntity;
import com.onlyknow.platform.entity.app.OKSearchBean;
import com.onlyknow.platform.manager.OKBaseManager;
import com.onlyknow.platform.manager.card.OKCardManager;
import com.onlyknow.platform.utils.OKAutoEvaluationUtil;
import com.onlyknow.platform.utils.OKLogUtil;
import com.onlyknow.platform.utils.OKStringUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OKUserManager extends OKBaseManager {

    public void saveOrUpdate(UserInfoEntity entity) {
        Session session = open();

        session.beginTransaction();

        session.saveOrUpdate(entity);

        session.getTransaction().commit();
    }

    public void saveOrUpdateAttention(UserAttentionEntity entity) {
        Session session = open();

        session.beginTransaction();

        session.saveOrUpdate(entity);

        session.getTransaction().commit();
    }

    public void saveOrUpdateLocation(UserLocationEntity entity) {
        Session session = open();

        session.beginTransaction();

        session.clear();

        session.flush();

        session.saveOrUpdate(entity);

        session.getTransaction().commit();
    }

    public UserInfoEntity getUserInfoByPlatForm(String name) {
        Session session = open();

        session.beginTransaction();

        session.clear();

        session.flush();

        Query<UserInfoEntity> query = session.createQuery("from UserInfoEntity info where info.userName=?", UserInfoEntity.class);

        query.setParameter(0, name);

        UserInfoEntity entity = query.uniqueResult();

        session.getTransaction().commit();

        return entity;
    }

    // 调用该方法获取到的userInfoEntity不能用于保存操作!!切记
    public UserInfoEntity getUserInfoByAppName(String name) {
        Session session = open();

        session.beginTransaction();

        session.clear();

        session.flush();

        Query<UserInfoEntity> query = session.createQuery("from UserInfoEntity info where info.userName=?", UserInfoEntity.class);

        query.setParameter(0, name);

        UserInfoEntity entity = query.uniqueResult();

        session.getTransaction().commit();

        if (entity == null) return null;

        UserInfoEntity appEntity = new UserInfoEntity();
        appEntity.setUserName(entity.getUserName());
        appEntity.setUserPassword("");
        appEntity.setTag(entity.getTag());
        appEntity.setEditDate(entity.getEditDate());
        appEntity.setReDate(entity.getReDate());
        appEntity.setMeIntegral(entity.getMeIntegral());
        appEntity.setHeadPortraitUrl(entity.getHeadPortraitUrl());
        appEntity.setAge(entity.getAge());
        appEntity.setBirthDate(entity.getBirthDate());
        appEntity.setUserEmail(entity.getUserEmail());
        appEntity.setUserPhone(entity.getUserPhone());
        appEntity.setSex(entity.getSex());
        appEntity.setUserNickname(entity.getUserNickname());
        appEntity.setMeAttention(entity.getMeAttention());
        appEntity.setMeWatch(entity.getMeWatch());
        appEntity.setMeArticle(entity.getMeArticle());
        appEntity.setHomepageUrl(entity.getHomepageUrl());
        appEntity.setUserId(-1);
        appEntity.setUserType(entity.getUserType());
        appEntity.setWithoutApprova(entity.isWithoutApprova());

        OKLogUtil.print("userInfo:" + appEntity.toString());

        return appEntity;
    }

    public String getAvatarByUserName(String name) {
        Session session = open();

        session.beginTransaction();

        Query<UserInfoEntity> query = session.createQuery("from UserInfoEntity info where info.userName=?", UserInfoEntity.class); // HQL语句，它类似于SQL语句

        query.setParameter(0, name);

        session.getTransaction().commit();

        UserInfoEntity entity = query.uniqueResult();

        if (entity != null) {
            return entity.getHeadPortraitUrl();
        } else {
            return null;
        }
    }

    public int getUserIdByUserName(String name) {
        Session session = open();

        session.beginTransaction();

        Query<UserInfoEntity> query = session.createQuery("from UserInfoEntity info where info.userName=?", UserInfoEntity.class); // HQL语句，它类似于SQL语句

        query.setParameter(0, name);

        session.getTransaction().commit();

        UserInfoEntity entity = query.uniqueResult();

        if (entity != null) {
            return entity.getUserId();
        } else {
            return -1;
        }
    }

    public boolean isWithoutApprovalByUserName(String name) {
        Session session = open();

        session.beginTransaction();

        Query<UserInfoEntity> query = session.createQuery("from UserInfoEntity info where info.userName=?", UserInfoEntity.class); // HQL语句，它类似于SQL语句

        query.setParameter(0, name);

        session.getTransaction().commit();

        UserInfoEntity entity = query.uniqueResult();

        if (entity != null) {
            return entity.isWithoutApprova();
        } else {
            return false;
        }
    }

    public String getNickNameByUserName(String name) {
        Session session = open();

        session.beginTransaction();

        Query<UserInfoEntity> query = session.createQuery("from UserInfoEntity info where info.userName=?", UserInfoEntity.class); // HQL语句，它类似于SQL语句

        query.setParameter(0, name);

        session.getTransaction().commit();

        UserInfoEntity entity = query.uniqueResult();

        if (entity != null) {
            return entity.getUserNickname();
        } else {
            return null;
        }
    }

    public List<UserAttentionEntity> listByAttention(String name, int page, int size) {
        Session session = open();

        session.beginTransaction();

        Query<UserAttentionEntity> query = session.createQuery("from UserAttentionEntity ua where ua.userNameMain=? order by ua.uatId asc", UserAttentionEntity.class);

        query.setParameter(0, name);

        if (page == 1) {
            query.setFirstResult(0);
        } else {
            query.setFirstResult(page * size - size + 1);
        }

        query.setMaxResults(size);

        session.getTransaction().commit();

        List<UserAttentionEntity> list = query.getResultList();

        if (list == null) return null;

        UserAttentionEntity entity = null;
        UserInfoEntity userInfoEntity = null;
        for (int i = 0; i < list.size(); i++) {
            entity = list.get(i);
            userInfoEntity = getUserInfoByPlatForm(entity.getUserNameRete());

            if (userInfoEntity == null) continue;

            entity.setAvatar(userInfoEntity.getHeadPortraitUrl());
            entity.setNickName(userInfoEntity.getUserNickname());
            entity.setTag(userInfoEntity.getTag());

            list.set(i, entity);
        }

        return list;
    }

    public List<UserInfoEntity> getNearUserInfoByLocation(String name, double longitude, double latitude) {
        Session session = open();

        session.beginTransaction();

        Query<UserLocationEntity> query = session.createQuery("from UserLocationEntity ul where ul.userName!=?", UserLocationEntity.class);

        query.setParameter(0, name);

        session.getTransaction().commit();

        List<UserLocationEntity> ulList = query.getResultList();

        List<UserInfoEntity> list = new ArrayList<>();

        UserLocationEntity entity = null;

        double distance = 0;

        for (int i = 0; i < ulList.size(); i++) {

            entity = ulList.get(i);

            distance = OKAutoEvaluationUtil.getDistance(latitude, longitude, entity.getUlDimension(), entity.getUlLongitude());

            if (distance <= OKProjectConfig.NEAR_DISTANCE) {

                UserInfoEntity userInfoEntity = getUserInfoByPlatForm(entity.getUserName());

                list.add(userInfoEntity);

            }
        }

        return list;
    }

    public List<String> getNearUserNameByLocation(String name, double longitude, double latitude) {
        Session session = open();

        session.beginTransaction();

        session.clear();

        session.flush();

        Query<UserLocationEntity> query = session.createQuery("from UserLocationEntity ul where ul.userName!=?", UserLocationEntity.class);

        query.setParameter(0, name);

        session.getTransaction().commit();

        List<UserLocationEntity> ulList = query.getResultList();

        List<String> list = new ArrayList<>();

        UserLocationEntity entity = null;

        double distance = 0;

        for (int i = 0; i < ulList.size(); i++) {

            entity = ulList.get(i);

            distance = OKAutoEvaluationUtil.getDistance(latitude, longitude, entity.getUlDimension(), entity.getUlLongitude());

            if (distance <= OKProjectConfig.NEAR_DISTANCE) {

                list.add(entity.getUserName());

            }
        }

        return list;
    }

    public UserInfoEntity logInPlatForm(String name, String pass) {
        Session session = open();

        session.beginTransaction();

        session.clear();

        session.flush();

        Query<UserInfoEntity> query = session.createQuery("from UserInfoEntity ui where ui.userName=? and ui.userPassword=?", UserInfoEntity.class); // HQL语句，它类似于SQL语句

        query.setParameter(0, name);

        query.setParameter(1, pass);

        UserInfoEntity entity = query.uniqueResult();

        session.getTransaction().commit();

        return entity;
    }

    public OKServiceResult registered(UserInfoEntity entity) {
        OKServiceResult serviceResult = new OKServiceResult();

        if (OKStringUtil.isEmpty(entity.getUserName()) || entity.getUserName().length() < 6 || entity.getUserName().length() > 12) {
            serviceResult.clear();

            serviceResult.setSuccess(false);
            serviceResult.setCode(OKProjectConfig.RESULT_CODE_REGISTERED_NAME_LENGTH_ERROR);
            serviceResult.setMsg(OKProjectConfig.RESULT_MSG_REGISTERED_NAME_LENGTH_ERROR);
            serviceResult.setTime(OKConstants.getNowTime());
            serviceResult.setData(new Gson().toJson(entity));

            return serviceResult;
        }

        if (OKStringUtil.isEmpty(entity.getUserPassword()) || entity.getUserPassword().length() < 6 || entity.getUserPassword().length() > 12) {
            serviceResult.clear();

            serviceResult.setSuccess(false);
            serviceResult.setCode(OKProjectConfig.RESULT_CODE_REGISTERED_PASS_LENGTH_ERROR);
            serviceResult.setMsg(OKProjectConfig.RESULT_MSG_REGISTERED_PASS_LENGTH_ERROR);
            serviceResult.setTime(OKConstants.getNowTime());
            serviceResult.setData(new Gson().toJson(entity));

            return serviceResult;
        }

        if (!"NAN".equalsIgnoreCase(entity.getSex()) && !"NV".equalsIgnoreCase(entity.getSex())) {
            serviceResult.clear();

            serviceResult.setSuccess(false);
            serviceResult.setCode(OKProjectConfig.RESULT_CODE_REGISTERED_SEX_FORMAT_ERROR);
            serviceResult.setMsg(OKProjectConfig.RESULT_MSG_REGISTERED_SEX_FORMAT_ERROR);
            serviceResult.setTime(OKConstants.getNowTime());
            serviceResult.setData(new Gson().toJson(entity));

            return serviceResult;
        }

        Session session = open();

        session.beginTransaction();

        // 用户名检查
        Query<UserInfoEntity> query = session.createQuery("from UserInfoEntity ui where ui.userName=?", UserInfoEntity.class);

        query.setParameter(0, entity.getUserName());

        session.getTransaction().commit();

        UserInfoEntity eqEntity = query.uniqueResult();

        if (eqEntity != null) {
            serviceResult.clear();

            serviceResult.setSuccess(false);
            serviceResult.setCode(OKProjectConfig.RESULT_CODE_REGISTERED_NAME_EXIST_ERROR);
            serviceResult.setMsg(OKProjectConfig.RESULT_MSG_REGISTERED_NAME_EXIST_ERROR);
            serviceResult.setTime(OKConstants.getNowTime());
            serviceResult.setData(new Gson().toJson(entity));

            return serviceResult;
        }

        session.beginTransaction();

        // 号码检查
        query = session.createQuery("from UserInfoEntity ui where ui.userPhone=?", UserInfoEntity.class);

        query.setParameter(0, entity.getUserPhone());

        session.getTransaction().commit();

        eqEntity = null;
        eqEntity = query.uniqueResult();

        if (eqEntity != null) {
            serviceResult.clear();

            serviceResult.setSuccess(false);
            serviceResult.setCode(OKProjectConfig.RESULT_CODE_REGISTERED_PHONE_EXIST_ERROR);
            serviceResult.setMsg(OKProjectConfig.RESULT_MSG_REGISTERED_PHONE_EXIST_ERROR);
            serviceResult.setTime(OKConstants.getNowTime());
            serviceResult.setData(new Gson().toJson(entity));

            return serviceResult;
        }

        session.beginTransaction();

        // 邮件检查
        query = session.createQuery("from UserInfoEntity ui where ui.userEmail=?", UserInfoEntity.class);

        query.setParameter(0, entity.getUserEmail());

        session.getTransaction().commit();

        eqEntity = null;
        eqEntity = query.uniqueResult();

        if (eqEntity != null) {
            serviceResult.clear();

            serviceResult.setSuccess(false);
            serviceResult.setCode(OKProjectConfig.RESULT_CODE_REGISTERED_EMAIL_EXIST_ERROR);
            serviceResult.setMsg(OKProjectConfig.RESULT_MSG_REGISTERED_EMAIL_EXIST_ERROR);
            serviceResult.setTime(OKConstants.getNowTime());
            serviceResult.setData(new Gson().toJson(entity));

            return serviceResult;
        }

        // 验证通过开始注册

        entity.setReDate(new Date());

        entity.setWithoutApprova(false);

        entity.setMeArticle(0);

        entity.setMeIntegral(OKProjectConfig.NEW_USER_INTEGRAL);

        entity.setMeWatch(0);

        entity.setMeAttention(0);

        entity.setUserType(UserInfoEntity.UserType.APP.toString());

        entity.setEditDate(new Date());

        saveOrUpdate(entity);

        serviceResult.clear();

        serviceResult.setSuccess(true);
        serviceResult.setCode(OKProjectConfig.RESULT_CODE_SUCCESS);
        serviceResult.setMsg(OKProjectConfig.RESULT_MSG_SUCCESS);
        serviceResult.setTime(OKConstants.getNowTime());
        serviceResult.setData(new Gson().toJson(entity));

        return serviceResult;
    }

    public boolean addAttention(String name, String attentionName) {
        UserInfoEntity userInfoEntity;

        userInfoEntity = getUserInfoByPlatForm(name);

        if (userInfoEntity == null) return false;

        userInfoEntity = getUserInfoByPlatForm(attentionName);

        if (userInfoEntity == null) return false;

        Session session = open();

        session.beginTransaction();

        Query<UserAttentionEntity> query = session.createQuery("from UserAttentionEntity ua where ua.userNameMain=? and ua.userNameRete=?", UserAttentionEntity.class); // HQL语句，它类似于SQL语句

        query.setParameter(0, name);

        query.setParameter(1, attentionName);

        session.getTransaction().commit();

        UserAttentionEntity userAttentionEntity = query.uniqueResult();

        if (userAttentionEntity != null) return false;

        userAttentionEntity = new UserAttentionEntity();

        userAttentionEntity.setUserNameMain(name);
        userAttentionEntity.setUserNameRete(attentionName);
        userAttentionEntity.setUatDate(new Date());

        saveOrUpdateAttention(userAttentionEntity);

        userInfoEntity = getUserInfoByPlatForm(name);

        userInfoEntity.setMeAttention(userInfoEntity.getMeAttention() + 1);
        userInfoEntity.setMeIntegral(userInfoEntity.getMeIntegral() + OKProjectConfig.ATTENTION_INTEGRAL);

        saveOrUpdate(userInfoEntity);

        return true;
    }

    public boolean deleteAttention(String name, String pass, String attentionName) {

        UserInfoEntity userInfoEntity = logInPlatForm(name, pass);

        if (userInfoEntity == null) return false;

        Session session = open();

        session.beginTransaction();

        Query<UserAttentionEntity> query = session.createQuery("from UserAttentionEntity ua where ua.userNameMain=? and ua.userNameRete=?", UserAttentionEntity.class); // HQL语句，它类似于SQL语句

        query.setParameter(0, name);

        query.setParameter(1, attentionName);

        session.getTransaction().commit();

        UserAttentionEntity userAttentionEntity = query.uniqueResult();

        if (userAttentionEntity == null) return false;

        session.beginTransaction();

        session.delete(userAttentionEntity);

        session.getTransaction().commit();

        // 清理缓存
        session.beginTransaction();

        session.clear();

        session.flush();

        session.getTransaction().commit();

        // 更新用户关注数量
        userInfoEntity.setMeAttention(userInfoEntity.getMeAttention() - 1);

        saveOrUpdate(userInfoEntity);

        return true;
    }

    public boolean updateInfo(String name, String pass, UserInfoEntity entity) {

        UserInfoEntity mainEntity = logInPlatForm(name, pass);

        if (mainEntity == null) return false;

        if (!OKStringUtil.isEmpty(entity.getUserNickname())) {
            mainEntity.setUserNickname(entity.getUserNickname());
        }

        if (!OKStringUtil.isEmpty(entity.getTag())) {
            mainEntity.setTag(entity.getTag());
        }

        if (!OKStringUtil.isEmpty(entity.getSex()) && ("NAN".equalsIgnoreCase(entity.getSex()) || "NV".equalsIgnoreCase(entity.getSex()))) {
            mainEntity.setSex(entity.getSex());
        }

        if (!OKStringUtil.isEmpty(entity.getUserPhone())) {
            mainEntity.setUserPhone(entity.getUserPhone());
        }

        if (!OKStringUtil.isEmpty(entity.getUserEmail())) {
            mainEntity.setUserEmail(entity.getUserEmail());
        }

        if (entity.getBirthDate() != null) {
            mainEntity.setBirthDate(entity.getBirthDate());
            mainEntity.setAge(OKAutoEvaluationUtil.getAge(entity.getBirthDate()));
        }

        mainEntity.setEditDate(new Date());

        saveOrUpdate(mainEntity);

        if (!OKStringUtil.isEmpty(entity.getUserNickname())) {

            OKCardManager cardManager = new OKCardManager();

            boolean b = cardManager.updateCardTitleText(name, entity.getUserNickname());

            OKLogUtil.print("update card title text:" + b);

        }

        return true;
    }

    public boolean updateAvatar(String name, String pass, String url) {

        if (OKStringUtil.isEmpty(url)) return false;

        UserInfoEntity userInfoEntity = logInPlatForm(name, pass);

        if (userInfoEntity == null) return false;

        userInfoEntity.setHeadPortraitUrl(url);

        userInfoEntity.setEditDate(new Date());

        saveOrUpdate(userInfoEntity);

        OKCardManager cardManager = new OKCardManager();

        boolean b = cardManager.updateCardTitleImage(name, url);

        OKLogUtil.print("update card title image:" + b);

        return true;

    }

    public boolean updateLocation(String name, String pass, double longitude, double latitude) {
        boolean b = userAuthentication(name, pass);

        if (!b) return false;

        Session session = open();

        session.beginTransaction();

        Query<UserLocationEntity> query = session.createQuery("from UserLocationEntity ul where ul.userName=?", UserLocationEntity.class);

        query.setParameter(0, name);

        session.getTransaction().commit();

        UserLocationEntity userLocationEntity = query.uniqueResult();

        if (userLocationEntity == null) userLocationEntity = new UserLocationEntity();

        userLocationEntity.setUserName(name);
        userLocationEntity.setUlLongitude(longitude);
        userLocationEntity.setUlDimension(latitude);
        userLocationEntity.setUlDate(new Date());

        saveOrUpdateLocation(userLocationEntity);

        return true;
    }

    public List<OKSearchBean> searchUser(String search, int page, int size) {
        Session session = open();

        session.beginTransaction();

        Query<UserInfoEntity> query = session.createQuery("from UserInfoEntity ui where ui.userName like ? or ui.userNickname like ? order by ui.userId asc", UserInfoEntity.class);

        query.setParameter(0, "%" + search + "%");
        query.setParameter(1, "%" + search + "%");

        if (page == 1) {
            query.setFirstResult(0);
        } else {
            query.setFirstResult(page * size - size + 1);
        }

        query.setMaxResults(size);

        session.getTransaction().commit();

        List<UserInfoEntity> list = query.getResultList();

        if (list == null) return null;

        List<OKSearchBean> searchBeanList = new ArrayList<>();

        OKSearchBean bean = null;

        UserInfoEntity userInfoEntity = null;

        UserInfoEntity appEntity = null;

        for (int i = 0; i < list.size(); i++) {

            userInfoEntity = list.get(i);

            appEntity = new UserInfoEntity();
            appEntity.setUserName(userInfoEntity.getUserName());
            appEntity.setUserPassword("");
            appEntity.setTag(userInfoEntity.getTag());
            appEntity.setEditDate(userInfoEntity.getEditDate());
            appEntity.setReDate(userInfoEntity.getReDate());
            appEntity.setMeIntegral(userInfoEntity.getMeIntegral());
            appEntity.setHeadPortraitUrl(userInfoEntity.getHeadPortraitUrl());
            appEntity.setAge(userInfoEntity.getAge());
            appEntity.setBirthDate(userInfoEntity.getBirthDate());
            appEntity.setUserEmail(userInfoEntity.getUserEmail());
            appEntity.setUserPhone(userInfoEntity.getUserPhone());
            appEntity.setSex(userInfoEntity.getSex());
            appEntity.setUserNickname(userInfoEntity.getUserNickname());
            appEntity.setMeAttention(userInfoEntity.getMeAttention());
            appEntity.setMeWatch(userInfoEntity.getMeWatch());
            appEntity.setMeArticle(userInfoEntity.getMeArticle());
            appEntity.setHomepageUrl(userInfoEntity.getHomepageUrl());
            appEntity.setUserId(-1);
            appEntity.setUserType(userInfoEntity.getUserType());
            appEntity.setWithoutApprova(userInfoEntity.isWithoutApprova());

            // 保存到搜索对象中
            bean = new OKSearchBean();
            bean.setUserInfoBean(appEntity);
            bean.setType(OKSearchBean.SEARCH_TYPE.USER);
            searchBeanList.add(bean);

        }

        return searchBeanList;
    }

    public boolean userAuthentication(String name, String pass) {
        UserInfoEntity entity = logInPlatForm(name, pass);

        if (entity == null) return false;

        return true;
    }
}
