package com.onlyknow.platform.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "comment", schema = "onlyknow_platform", catalog = "")
public class CommentEntity {
    private int comId;

    public final static String KEY_USER_NAME = "userName";
    private String userName;

    public final static String KEY_CARD_ID = "cardId";
    private int cardId;
    private String message;
    private Integer comPraise;
    private Date comDate;

    private String nickName = "";
    private String avatar = "";
    private boolean isPraise = false;

    @Id
    @Column(name = "COM_ID")
    public int getComId() {
        return comId;
    }

    public void setComId(int comId) {
        this.comId = comId;
    }

    @Basic
    @Column(name = "USER_NAME")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "CARD_ID")
    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    @Basic
    @Column(name = "MESSAGE")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "COM_PRAISE")
    public Integer getComPraise() {
        return comPraise;
    }

    public void setComPraise(Integer comPraise) {
        this.comPraise = comPraise;
    }

    @Basic
    @Column(name = "COM_DATE")
    public Date getComDate() {
        return comDate;
    }

    public void setComDate(Date comDate) {
        this.comDate = comDate;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isPraise() {
        return isPraise;
    }

    public void setPraise(boolean praise) {
        isPraise = praise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntity that = (CommentEntity) o;
        return comId == that.comId &&
                cardId == that.cardId &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(message, that.message) &&
                Objects.equals(comPraise, that.comPraise) &&
                Objects.equals(comDate, that.comDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(comId, userName, cardId, message, comPraise, comDate);
    }
}
