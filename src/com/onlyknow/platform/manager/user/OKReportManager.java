package com.onlyknow.platform.manager.user;

import com.onlyknow.platform.entity.*;
import com.onlyknow.platform.manager.OKBaseManager;
import com.onlyknow.platform.manager.card.OKCardManager;
import com.onlyknow.platform.manager.comment.OKCommentManager;
import com.onlyknow.platform.manager.comment.OKCommentReplyManager;
import org.hibernate.Session;

import java.util.Date;

public class OKReportManager extends OKBaseManager {

    public void saveOrUpdate(ReportEntity entity) {
        Session session = open();

        session.beginTransaction();

        session.saveOrUpdate(entity);

        session.getTransaction().commit();
    }

    private final String TYPE_REPORT_CARD = "REPORT_CARD";

    public boolean reportCard(String name, int cardId, String msg) {

        OKUserManager userManager = new OKUserManager();

        UserInfoEntity userInfoEntity = userManager.getUserInfoByPlatForm(name);

        if (userInfoEntity == null) return false;

        OKCardManager cardManager = new OKCardManager();

        CardInfoEntity cardInfoEntity = cardManager.getCardInfoById(cardId);

        if (cardInfoEntity == null) return false;

        ReportEntity reportEntity = new ReportEntity();

        reportEntity.setUserName(name);
        reportEntity.setPassiveId(cardId);
        reportEntity.setRpType(TYPE_REPORT_CARD);
        reportEntity.setRpMessage(msg);
        reportEntity.setRpDate(new Date());

        saveOrUpdate(reportEntity);

        return true;
    }

    private final String TYPE_REPORT_USER = "REPORT_USER";

    public boolean reportUser(String name, String reportName, String msg) {
        OKUserManager userManager = new OKUserManager();

        UserInfoEntity userInfoEntity = userManager.getUserInfoByPlatForm(name);

        if (userInfoEntity == null) return false;

        userInfoEntity = null;
        userInfoEntity = userManager.getUserInfoByPlatForm(reportName);

        if (userInfoEntity == null) return false;

        ReportEntity reportEntity = new ReportEntity();

        reportEntity.setUserName(name);
        reportEntity.setPassiveId(userInfoEntity.getUserId());
        reportEntity.setRpType(TYPE_REPORT_USER);
        reportEntity.setRpMessage(msg);
        reportEntity.setRpDate(new Date());

        saveOrUpdate(reportEntity);

        return true;
    }

    private final String TYPE_REPORT_COMMENT = "REPORT_COMMENT";

    public boolean reportComment(String name, int comId, String msg) {
        OKUserManager userManager = new OKUserManager();

        UserInfoEntity userInfoEntity = userManager.getUserInfoByPlatForm(name);

        if (userInfoEntity == null) return false;

        OKCommentManager commentManager = new OKCommentManager();

        CommentEntity commentEntity = commentManager.getCommentById(comId);

        if (commentEntity == null) return false;

        ReportEntity reportEntity = new ReportEntity();

        reportEntity.setUserName(name);
        reportEntity.setPassiveId(comId);
        reportEntity.setRpType(TYPE_REPORT_COMMENT);
        reportEntity.setRpMessage(msg);
        reportEntity.setRpDate(new Date());

        saveOrUpdate(reportEntity);

        return true;
    }

    private final String TYPE_REPORT_COMMENT_REPLY = "REPORT_COMMENT_REPLY";

    public boolean reportCommentReply(String name, int comrId, String msg) {
        OKUserManager userManager = new OKUserManager();

        UserInfoEntity userInfoEntity = userManager.getUserInfoByPlatForm(name);

        if (userInfoEntity == null) return false;

        OKCommentReplyManager commentReplyManager = new OKCommentReplyManager();

        CommentReplyEntity commentReplyEntity = commentReplyManager.getCommentReplyById(comrId);

        if (commentReplyEntity == null) return false;

        ReportEntity reportEntity = new ReportEntity();

        reportEntity.setUserName(name);
        reportEntity.setPassiveId(comrId);
        reportEntity.setRpType(TYPE_REPORT_COMMENT_REPLY);
        reportEntity.setRpMessage(msg);
        reportEntity.setRpDate(new Date());

        saveOrUpdate(reportEntity);

        return true;
    }
}
