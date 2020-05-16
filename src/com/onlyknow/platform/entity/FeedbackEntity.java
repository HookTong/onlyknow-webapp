package com.onlyknow.platform.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "feedback", schema = "onlyknow_platform", catalog = "")
public class FeedbackEntity {
    private int fbId;
    private String userName;
    private String fbDescribe;
    private String fbImage;
    private Date fbDate;

    @Id
    @Column(name = "FB_ID")
    public int getFbId() {
        return fbId;
    }

    public void setFbId(int fbId) {
        this.fbId = fbId;
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
    @Column(name = "FB_DESCRIBE")
    public String getFbDescribe() {
        return fbDescribe;
    }

    public void setFbDescribe(String fbDescribe) {
        this.fbDescribe = fbDescribe;
    }

    @Basic
    @Column(name = "FB_IMAGE")
    public String getFbImage() {
        return fbImage;
    }

    public void setFbImage(String fbImage) {
        this.fbImage = fbImage;
    }

    @Basic
    @Column(name = "FB_DATE")
    public Date getFbDate() {
        return fbDate;
    }

    public void setFbDate(Date fbDate) {
        this.fbDate = fbDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedbackEntity that = (FeedbackEntity) o;
        return fbId == that.fbId &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(fbDescribe, that.fbDescribe) &&
                Objects.equals(fbImage, that.fbImage) &&
                Objects.equals(fbDate, that.fbDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(fbId, userName, fbDescribe, fbImage, fbDate);
    }
}
