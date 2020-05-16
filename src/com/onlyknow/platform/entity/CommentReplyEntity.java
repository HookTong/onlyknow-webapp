package com.onlyknow.platform.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "comment_reply", schema = "onlyknow_platform", catalog = "")
public class CommentReplyEntity {
    private int comrId;
    private int comId;
    private String userName;
    private String message;
    private Integer comrPraise;
    private Date comrDate;

    private String nickName = "";
    private String avatar = "";
    private boolean isPraise = false;

    @Id
    @Column(name = "COMR_ID")
    public int getComrId() {
        return comrId;
    }

    public void setComrId(int comrId) {
        this.comrId = comrId;
    }

    @Basic
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
    @Column(name = "MESSAGE")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "COMR_PRAISE")
    public Integer getComrPraise() {
        return comrPraise;
    }

    public void setComrPraise(Integer comrPraise) {
        this.comrPraise = comrPraise;
    }

    @Basic
    @Column(name = "COMR_DATE")
    public Date getComrDate() {
        return comrDate;
    }

    public void setComrDate(Date comrDate) {
        this.comrDate = comrDate;
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
        CommentReplyEntity that = (CommentReplyEntity) o;
        return comrId == that.comrId &&
                comId == that.comId &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(message, that.message) &&
                Objects.equals(comrPraise, that.comrPraise) &&
                Objects.equals(comrDate, that.comrDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(comrId, comId, userName, message, comrPraise, comrDate);
    }
}
