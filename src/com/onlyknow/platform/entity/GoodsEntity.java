package com.onlyknow.platform.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "goods", schema = "onlyknow_platform", catalog = "")
public class GoodsEntity {
    private int gdId;
    private String gdName;
    private String gdType;
    private String gdDescribe;
    private int gdPrice;
    private String gdIntroduction;
    private String gdIconUrl;
    private String gdImageUrl;
    private Date gdDate;

    private boolean isGoodsBy = false;

    public enum GoodsType {
        APPROVAL, ORDINARY
    }

    @Id
    @Column(name = "GD_ID")
    public int getGdId() {
        return gdId;
    }

    public void setGdId(int gdId) {
        this.gdId = gdId;
    }

    @Basic
    @Column(name = "GD_NAME")
    public String getGdName() {
        return gdName;
    }

    public void setGdName(String gdName) {
        this.gdName = gdName;
    }

    @Basic
    @Column(name = "GD_TYPE")
    public String getGdType() {
        return gdType;
    }

    public void setGdType(String gdType) {
        this.gdType = gdType;
    }

    @Basic
    @Column(name = "GD_DESCRIBE")
    public String getGdDescribe() {
        return gdDescribe;
    }

    public void setGdDescribe(String gdDescribe) {
        this.gdDescribe = gdDescribe;
    }

    @Basic
    @Column(name = "GD_PRICE")
    public int getGdPrice() {
        return gdPrice;
    }

    public void setGdPrice(int gdPrice) {
        this.gdPrice = gdPrice;
    }

    @Basic
    @Column(name = "GD_INTRODUCTION")
    public String getGdIntroduction() {
        return gdIntroduction;
    }

    public void setGdIntroduction(String gdIntroduction) {
        this.gdIntroduction = gdIntroduction;
    }

    @Basic
    @Column(name = "GD_ICON_URL")
    public String getGdIconUrl() {
        return gdIconUrl;
    }

    public void setGdIconUrl(String gdIconUrl) {
        this.gdIconUrl = gdIconUrl;
    }

    @Basic
    @Column(name = "GD_IMAGE_URL")
    public String getGdImageUrl() {
        return gdImageUrl;
    }

    public void setGdImageUrl(String gdImageUrl) {
        this.gdImageUrl = gdImageUrl;
    }

    @Basic
    @Column(name = "GD_DATE")
    public Date getGdDate() {
        return gdDate;
    }

    public void setGdDate(Date gdDate) {
        this.gdDate = gdDate;
    }

    public boolean isGoodsBy() {
        return isGoodsBy;
    }

    public void setGoodsBy(boolean goodsBy) {
        isGoodsBy = goodsBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodsEntity that = (GoodsEntity) o;
        return gdId == that.gdId &&
                gdPrice == that.gdPrice &&
                Objects.equals(gdName, that.gdName) &&
                Objects.equals(gdType, that.gdType) &&
                Objects.equals(gdDescribe, that.gdDescribe) &&
                Objects.equals(gdIntroduction, that.gdIntroduction) &&
                Objects.equals(gdIconUrl, that.gdIconUrl) &&
                Objects.equals(gdImageUrl, that.gdImageUrl) &&
                Objects.equals(gdDate, that.gdDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(gdId, gdName, gdType, gdDescribe, gdPrice, gdIntroduction, gdIconUrl, gdImageUrl, gdDate);
    }
}
