package com.onlyknow.platform.entity.app;

import com.onlyknow.platform.entity.CardInfoEntity;
import com.onlyknow.platform.entity.UserInfoEntity;

public class OKSearchBean {

    public enum SEARCH_TYPE {
        USER, CARD, ALL;
    }

    private SEARCH_TYPE Type;

    private UserInfoEntity userInfoBean;

    private CardInfoEntity cardBean;

    public SEARCH_TYPE getType() {
        return Type;
    }

    public void setType(SEARCH_TYPE type) {
        Type = type;
    }

    public UserInfoEntity getUserInfoBean() {
        return userInfoBean;
    }

    public void setUserInfoBean(UserInfoEntity userInfoBean) {
        this.userInfoBean = userInfoBean;
    }

    public CardInfoEntity getCardBean() {
        return cardBean;
    }

    public void setCardBean(CardInfoEntity cardBean) {
        this.cardBean = cardBean;
    }
}
