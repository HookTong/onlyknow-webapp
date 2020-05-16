package com.onlyknow.platform.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "card_browsing", schema = "onlyknow_platform", catalog = "")
public class CardBrowsingEntity {
    private int cbId;
    private int cardId;
    private String userName;
    private Date cbDate;

    @Id
    @Column(name = "CB_ID")
    public int getCbId() {
        return cbId;
    }

    public void setCbId(int cbId) {
        this.cbId = cbId;
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
    @Column(name = "USER_NAME")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "CB_DATE")
    public Date getCbDate() {
        return cbDate;
    }

    public void setCbDate(Date cbDate) {
        this.cbDate = cbDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardBrowsingEntity that = (CardBrowsingEntity) o;
        return cbId == that.cbId &&
                cardId == that.cardId &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(cbDate, that.cbDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cbId, cardId, userName, cbDate);
    }
}
