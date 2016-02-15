package cn.hugeterry.coderfun.beans;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/2/15 19:29
 */
public class Results {
    /**
     "who": "MVP",
     "publishedAt": "2016-02-15T03:49:24.372Z",
     "desc": "一个轻量级的YouTube",
     "type": "Android",
     "url": "https://github.com/theScrabi/NewPipe",
     "used": true,
     "objectId": "56c1401a71cfe4005c961746",
     "createdAt": "2016-02-15T03:03:54.236Z",
     "updatedAt": "2016-02-15T03:49:25.335Z"
     */
    private String who;
    private String publishedAt;
    private String desc;
    private String type;
    private String url;
    private String used;
    private String objectId;
    private String createdAt;
    private String updatedAt;

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
