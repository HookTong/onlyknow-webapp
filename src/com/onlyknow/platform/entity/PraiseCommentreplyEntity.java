package com.onlyknow.platform.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "praise_commentreply", schema = "onlyknow_platform", catalog = "")
public class PraiseCommentreplyEntity {
    private int crpId;
    private String userName;
    private int comrId;
    private Date crpDate;

    @Id
    @Column(name = "CRP_ID")
    public int getCrpId() {
        return crpId;
    }

    public void setCrpId(int crpId) {
        this.crpId = crpId;
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
    @Column(name = "COMR_ID")
    public int getComrId() {
        return comrId;
    }

    public void setComrId(int comrId) {
        this.comrId = comrId;
    }

    @Basic
    @Column(name = "CRP_DATE")
    public Date getCrpDate() {
        return crpDate;
    }

    public void setCrpDate(Date crpDate) {
        this.crpDate = crpDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PraiseCommentreplyEntity that = (PraiseCommentreplyEntity) o;
        return crpId == that.crpId &&
                comrId == that.comrId &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(crpDate, that.crpDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(crpId, userName, comrId, crpDate);
    }
}
