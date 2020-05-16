package com.onlyknow.platform.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "report", schema = "onlyknow_platform", catalog = "")
public class ReportEntity {
    private int rpId;
    private String userName;
    private String rpType;
    private int passiveId;
    private String rpMessage;
    private Date rpDate;

    @Id
    @Column(name = "RP_ID")
    public int getRpId() {
        return rpId;
    }

    public void setRpId(int rpId) {
        this.rpId = rpId;
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
    @Column(name = "RP_TYPE")
    public String getRpType() {
        return rpType;
    }

    public void setRpType(String rpType) {
        this.rpType = rpType;
    }

    @Basic
    @Column(name = "PASSIVE_ID")
    public int getPassiveId() {
        return passiveId;
    }

    public void setPassiveId(int passiveId) {
        this.passiveId = passiveId;
    }

    @Basic
    @Column(name = "RP_MESSAGE")
    public String getRpMessage() {
        return rpMessage;
    }

    public void setRpMessage(String rpMessage) {
        this.rpMessage = rpMessage;
    }

    @Basic
    @Column(name = "RP_DATE")
    public Date getRpDate() {
        return rpDate;
    }

    public void setRpDate(Date rpDate) {
        this.rpDate = rpDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportEntity that = (ReportEntity) o;
        return rpId == that.rpId &&
                passiveId == that.passiveId &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(rpType, that.rpType) &&
                Objects.equals(rpMessage, that.rpMessage) &&
                Objects.equals(rpDate, that.rpDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(rpId, userName, rpType, passiveId, rpMessage, rpDate);
    }
}
