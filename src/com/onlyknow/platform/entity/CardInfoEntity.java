package com.onlyknow.platform.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "card_info", schema = "onlyknow_platform", catalog = "")
public class CardInfoEntity {
    private int cardId;
    private String cardType;
    private String userName;
    private String titleText;
    private String titleImageUrl;
    private String contentImageUrl;
    private String contentTitleText;
    private String contentText;
    private String labelling;
    private String messageLink;
    private Integer praiseCount;
    private Integer watchCount;
    private Integer commentCount;
    private Integer browsingCount;
    private int approveBy;
    private String approveInfo;
    private Date createDate;

    @Id
    @Column(name = "CARD_ID")
    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    @Basic
    @Column(name = "CARD_TYPE")
    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
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
    @Column(name = "TITLE_TEXT")
    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    @Basic
    @Column(name = "TITLE_IMAGE_URL")
    public String getTitleImageUrl() {
        return titleImageUrl;
    }

    public void setTitleImageUrl(String titleImageUrl) {
        this.titleImageUrl = titleImageUrl;
    }

    @Basic
    @Column(name = "CONTENT_IMAGE_URL")
    public String getContentImageUrl() {
        return contentImageUrl;
    }

    public void setContentImageUrl(String contentImageUrl) {
        this.contentImageUrl = contentImageUrl;
    }

    @Basic
    @Column(name = "CONTENT_TITLE_TEXT")
    public String getContentTitleText() {
        return contentTitleText;
    }

    public void setContentTitleText(String contentTitleText) {
        this.contentTitleText = contentTitleText;
    }

    @Basic
    @Column(name = "CONTENT_TEXT")
    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    @Basic
    @Column(name = "LABELLING")
    public String getLabelling() {
        return labelling;
    }

    public void setLabelling(String labelling) {
        this.labelling = labelling;
    }

    @Basic
    @Column(name = "MESSAGE_LINK")
    public String getMessageLink() {
        return messageLink;
    }

    public void setMessageLink(String messageLink) {
        this.messageLink = messageLink;
    }

    @Basic
    @Column(name = "PRAISE_COUNT")
    public Integer getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(Integer praiseCount) {
        this.praiseCount = praiseCount;
    }

    @Basic
    @Column(name = "WATCH_COUNT")
    public Integer getWatchCount() {
        return watchCount;
    }

    public void setWatchCount(Integer watchCount) {
        this.watchCount = watchCount;
    }

    @Basic
    @Column(name = "COMMENT_COUNT")
    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    @Basic
    @Column(name = "BROWSING_COUNT")
    public Integer getBrowsingCount() {
        return browsingCount;
    }

    public void setBrowsingCount(Integer browsingCount) {
        this.browsingCount = browsingCount;
    }

    @Basic
    @Column(name = "APPROVE_BY")
    public int getApproveBy() {
        return approveBy;
    }

    public void setApproveBy(int approveBy) {
        this.approveBy = approveBy;
    }

    @Basic
    @Column(name = "APPROVE_INFO")
    public String getApproveInfo() {
        return approveInfo;
    }

    public void setApproveInfo(String approveInfo) {
        this.approveInfo = approveInfo;
    }

    @Basic
    @Column(name = "CREATE_DATE")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardInfoEntity that = (CardInfoEntity) o;
        return cardId == that.cardId &&
                approveBy == that.approveBy &&
                Objects.equals(cardType, that.cardType) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(titleText, that.titleText) &&
                Objects.equals(titleImageUrl, that.titleImageUrl) &&
                Objects.equals(contentImageUrl, that.contentImageUrl) &&
                Objects.equals(contentTitleText, that.contentTitleText) &&
                Objects.equals(contentText, that.contentText) &&
                Objects.equals(labelling, that.labelling) &&
                Objects.equals(messageLink, that.messageLink) &&
                Objects.equals(praiseCount, that.praiseCount) &&
                Objects.equals(watchCount, that.watchCount) &&
                Objects.equals(commentCount, that.commentCount) &&
                Objects.equals(browsingCount, that.browsingCount) &&
                Objects.equals(approveInfo, that.approveInfo) &&
                Objects.equals(createDate, that.createDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cardId, cardType, userName, titleText, titleImageUrl, contentImageUrl, contentTitleText, contentText, labelling, messageLink, praiseCount, watchCount, commentCount, browsingCount, approveBy, approveInfo, createDate);
    }

    private List<CardImage> imageList;

    public static class CardImage {
        private String url = "";
        private long size;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public long getSize() {
            return size;
        }

        public void setSize(long size) {
            this.size = size;
        }
    }
}
