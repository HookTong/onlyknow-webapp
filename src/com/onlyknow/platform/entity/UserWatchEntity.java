package com.onlyknow.platform.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "user_watch", schema = "onlyknow_platform", catalog = "")
public class UserWatchEntity {
    private int uwId;
    private String userName;
    private int cardId;
    private Date uwDate;

    @Id
    @Column(name = "UW_ID")
    public int getUwId() {
        return uwId;
    }

    public void setUwId(int uwId) {
        this.uwId = uwId;
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
    @Column(name = "UW_DATE")
    public Date getUwDate() {
        return uwDate;
    }

    public void setUwDate(Date uwDate) {
        this.uwDate = uwDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserWatchEntity that = (UserWatchEntity) o;
        return uwId == that.uwId &&
                cardId == that.cardId &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(uwDate, that.uwDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uwId, userName, cardId, uwDate);
    }
}
