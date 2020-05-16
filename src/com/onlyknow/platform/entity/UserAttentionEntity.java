package com.onlyknow.platform.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "user_attention", schema = "onlyknow_platform", catalog = "")
public class UserAttentionEntity {
    private int uatId;
    private String userNameMain;
    private String userNameRete;
    private Date uatDate;

    private String nickName = "";
    private String avatar = "";
    private String tag = "";

    @Id
    @Column(name = "UAT_ID")
    public int getUatId() {
        return uatId;
    }

    public void setUatId(int uatId) {
        this.uatId = uatId;
    }

    @Basic
    @Column(name = "USER_NAME_MAIN")
    public String getUserNameMain() {
        return userNameMain;
    }

    public void setUserNameMain(String userNameMain) {
        this.userNameMain = userNameMain;
    }

    @Basic
    @Column(name = "USER_NAME_RETE")
    public String getUserNameRete() {
        return userNameRete;
    }

    public void setUserNameRete(String userNameRete) {
        this.userNameRete = userNameRete;
    }

    @Basic
    @Column(name = "UAT_DATE")
    public Date getUatDate() {
        return uatDate;
    }

    public void setUatDate(Date uatDate) {
        this.uatDate = uatDate;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAttentionEntity that = (UserAttentionEntity) o;
        return uatId == that.uatId &&
                Objects.equals(userNameMain, that.userNameMain) &&
                Objects.equals(userNameRete, that.userNameRete) &&
                Objects.equals(uatDate, that.uatDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uatId, userNameMain, userNameRete, uatDate);
    }
}
