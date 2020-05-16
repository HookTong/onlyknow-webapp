package com.onlyknow.platform.entity.app;

import com.onlyknow.platform.entity.CardInfoEntity;
import com.onlyknow.platform.entity.CommentEntity;

public class OKUserCommentCardBean {

    private CardInfoEntity cardEntity;

    private CommentEntity commentEntity;

    public CardInfoEntity getCardEntity() {
        return cardEntity;
    }

    public void setCardBean(CardInfoEntity mOKCardBean) {
        this.cardEntity = mOKCardBean;
    }

    public CommentEntity getCommentEntity() {
        return commentEntity;
    }

    public void setCommentBean(CommentEntity mOKCommentBean) {
        this.commentEntity = mOKCommentBean;
    }
}
