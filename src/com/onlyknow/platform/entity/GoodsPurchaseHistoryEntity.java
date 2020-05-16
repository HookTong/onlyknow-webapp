package com.onlyknow.platform.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "goods_purchase_history", schema = "onlyknow_platform", catalog = "")
public class GoodsPurchaseHistoryEntity {
    private int prId;
    private int gdId;
    private String userName;
    private int prConsume;
    private Date prDate;

    @Id
    @Column(name = "PR_ID")
    public int getPrId() {
        return prId;
    }

    public void setPrId(int prId) {
        this.prId = prId;
    }

    @Basic
    @Column(name = "GD_ID")
    public int getGdId() {
        return gdId;
    }

    public void setGdId(int gdId) {
        this.gdId = gdId;
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
    @Column(name = "PR_CONSUME")
    public int getPrConsume() {
        return prConsume;
    }

    public void setPrConsume(int prConsume) {
        this.prConsume = prConsume;
    }

    @Basic
    @Column(name = "PR_DATE")
    public Date getPrDate() {
        return prDate;
    }

    public void setPrDate(Date prDate) {
        this.prDate = prDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodsPurchaseHistoryEntity that = (GoodsPurchaseHistoryEntity) o;
        return prId == that.prId &&
                gdId == that.gdId &&
                prConsume == that.prConsume &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(prDate, that.prDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(prId, gdId, userName, prConsume, prDate);
    }
}
