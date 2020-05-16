package com.onlyknow.platform.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "app_info", schema = "onlyknow_platform", catalog = "")
public class AppInfoEntity {
    public final static String KEY_APP_ID = "appId";
    private int appId;
    private String appVersion;
    private String appName;
    private String appUrl;
    private String appDescribe;
    private String appUa;
    private String appImageUrl;
    private String appSize;
    private boolean appIsMandatory;
    private Date appDate;

    @Id
    @Column(name = "APP_ID")
    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    @Basic
    @Column(name = "APP_VERSION")
    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    @Basic
    @Column(name = "APP_NAME")
    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    @Basic
    @Column(name = "APP_URL")
    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    @Basic
    @Column(name = "APP_DESCRIBE")
    public String getAppDescribe() {
        return appDescribe;
    }

    public void setAppDescribe(String appDescribe) {
        this.appDescribe = appDescribe;
    }

    @Basic
    @Column(name = "APP_UA")
    public String getAppUa() {
        return appUa;
    }

    public void setAppUa(String appUa) {
        this.appUa = appUa;
    }

    @Basic
    @Column(name = "APP_IMAGE_URL")
    public String getAppImageUrl() {
        return appImageUrl;
    }

    public void setAppImageUrl(String appImageUrl) {
        this.appImageUrl = appImageUrl;
    }

    @Basic
    @Column(name = "APP_SIZE")
    public String getAppSize() {
        return appSize;
    }

    public void setAppSize(String appSize) {
        this.appSize = appSize;
    }

    @Basic
    @Column(name = "APP_IS_MANDATORY")
    public boolean isAppIsMandatory() {
        return appIsMandatory;
    }

    public void setAppIsMandatory(boolean appIsMandatory) {
        this.appIsMandatory = appIsMandatory;
    }

    @Basic
    @Column(name = "APP_DATE")
    public Date getAppDate() {
        return appDate;
    }

    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppInfoEntity that = (AppInfoEntity) o;
        return appId == that.appId &&
                appIsMandatory == that.appIsMandatory &&
                Objects.equals(appVersion, that.appVersion) &&
                Objects.equals(appName, that.appName) &&
                Objects.equals(appUrl, that.appUrl) &&
                Objects.equals(appDescribe, that.appDescribe) &&
                Objects.equals(appUa, that.appUa) &&
                Objects.equals(appImageUrl, that.appImageUrl) &&
                Objects.equals(appSize, that.appSize) &&
                Objects.equals(appDate, that.appDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(appId, appVersion, appName, appUrl, appDescribe, appUa, appImageUrl, appSize, appIsMandatory, appDate);
    }
}
