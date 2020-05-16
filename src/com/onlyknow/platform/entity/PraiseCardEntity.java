package com.onlyknow.platform.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "praise_card", schema = "onlyknow_platform", catalog = "")
public class PraiseCardEntity {
    private int cpId;
    private String userName;
    private int cardId;
    private Date cpDate;

    @Id
    @Column(name = "CP_ID")
    public int getCpId() {
        return cpId;
    }

    public void setCpId(int cpId) {
        this.cpId = cpId;
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
    @Column(name = "CP_DATE")
    public Date getCpDate() {
        return cpDate;
    }

    public void setCpDate(Date cpDate) {
        this.cpDate = cpDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PraiseCardEntity that = (PraiseCardEntity) o;
        return cpId == that.cpId &&
                cardId == that.cardId &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(cpDate, that.cpDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cpId, userName, cardId, cpDate);
    }
}
