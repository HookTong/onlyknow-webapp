package com.onlyknow.platform.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "user_info", schema = "onlyknow_platform", catalog = "")
public class UserInfoEntity {
    private int userId;
    private String userName;
    private String userNickname;
    private String userType;
    private String userPassword;
    private String userPhone;
    private String userEmail;
    private String sex;
    private Integer age;
    private Date birthDate;
    private String headPortraitUrl;
    private String homepageUrl;
    private String tag;
    private Integer meWatch;
    private Integer meAttention;
    private Integer meIntegral;
    private Integer meArticle;
    private boolean withoutApprova;
    private Date editDate;
    private Date reDate;

    public enum UserType{
        APP,ONLY_KNOW
    }

    @Id
    @Column(name = "USER_ID")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
    @Column(name = "USER_NICKNAME")
    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    @Basic
    @Column(name = "USER_TYPE")
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Basic
    @Column(name = "USER_PASSWORD")
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Basic
    @Column(name = "USER_PHONE")
    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Basic
    @Column(name = "USER_EMAIL")
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Basic
    @Column(name = "SEX")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "AGE")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "BIRTH_DATE")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Basic
    @Column(name = "HEAD_PORTRAIT_URL")
    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl;
    }

    @Basic
    @Column(name = "HOMEPAGE_URL")
    public String getHomepageUrl() {
        return homepageUrl;
    }

    public void setHomepageUrl(String homepageUrl) {
        this.homepageUrl = homepageUrl;
    }

    @Basic
    @Column(name = "TAG")
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Basic
    @Column(name = "ME_WATCH")
    public Integer getMeWatch() {
        return meWatch;
    }

    public void setMeWatch(Integer meWatch) {
        this.meWatch = meWatch;
    }

    @Basic
    @Column(name = "ME_ATTENTION")
    public Integer getMeAttention() {
        return meAttention;
    }

    public void setMeAttention(Integer meAttention) {
        this.meAttention = meAttention;
    }

    @Basic
    @Column(name = "ME_INTEGRAL")
    public Integer getMeIntegral() {
        return meIntegral;
    }

    public void setMeIntegral(Integer meIntegral) {
        this.meIntegral = meIntegral;
    }

    @Basic
    @Column(name = "ME_ARTICLE")
    public Integer getMeArticle() {
        return meArticle;
    }

    public void setMeArticle(Integer meArticle) {
        this.meArticle = meArticle;
    }

    @Basic
    @Column(name = "WITHOUT_APPROVA")
    public boolean isWithoutApprova() {
        return withoutApprova;
    }

    public void setWithoutApprova(boolean withoutApprova) {
        this.withoutApprova = withoutApprova;
    }

    @Basic
    @Column(name = "EDIT_DATE")
    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    @Basic
    @Column(name = "RE_DATE")
    public Date getReDate() {
        return reDate;
    }

    public void setReDate(Date reDate) {
        this.reDate = reDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfoEntity that = (UserInfoEntity) o;
        return userId == that.userId &&
                withoutApprova == that.withoutApprova &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(userNickname, that.userNickname) &&
                Objects.equals(userType, that.userType) &&
                Objects.equals(userPassword, that.userPassword) &&
                Objects.equals(userPhone, that.userPhone) &&
                Objects.equals(userEmail, that.userEmail) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(age, that.age) &&
                Objects.equals(birthDate, that.birthDate) &&
                Objects.equals(headPortraitUrl, that.headPortraitUrl) &&
                Objects.equals(homepageUrl, that.homepageUrl) &&
                Objects.equals(tag, that.tag) &&
                Objects.equals(meWatch, that.meWatch) &&
                Objects.equals(meAttention, that.meAttention) &&
                Objects.equals(meIntegral, that.meIntegral) &&
                Objects.equals(meArticle, that.meArticle) &&
                Objects.equals(editDate, that.editDate) &&
                Objects.equals(reDate, that.reDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, userName, userNickname, userType, userPassword, userPhone, userEmail, sex, age, birthDate, headPortraitUrl, homepageUrl, tag, meWatch, meAttention, meIntegral, meArticle, withoutApprova, editDate, reDate);
    }
}
