package com.leanway.jsoup_data;

/**
 * @author Xibo_Yue
 * @time 2017/3/6 10:02
 */
public class News {
    private String title;
    private String issueTime;
    private String summary;
    private String newsUrl;
    private String titleImageUrl;


    public News() {
    }

    public News(String issueTime, String newsUrl, String summary, String title, String
            titleImageUrl) {
        this.issueTime = issueTime;
        this.newsUrl = newsUrl;
        this.summary = summary;
        this.title = title;
        this.titleImageUrl = titleImageUrl;
    }

    public String getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(String issueTime) {
        this.issueTime = issueTime;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitleImageUrl() {
        return titleImageUrl;
    }

    public void setTitleImageUrl(String titleImageUrl) {
        this.titleImageUrl = titleImageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
