package cn.hugeterry.coderfun.model.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/2/15 19:29
 */
public class Results {
    /**
     * "_id": "5872f952421aa9315ea7991f",
     * "createdAt": "2017-01-09T10:45:38.791Z",
     * "desc": "\u7c7b Instagram \u7684\u8272\u5f69\u6e10\u53d8\u6548\u679c\u3002",
     * "images": [
     * "http://img.gank.io/404bcd23-6d5c-440d-b985-ddf85b171239"
     * ],
     * "publishedAt": "2017-01-09T11:46:59.821Z",
     * "source": "chrome",
     * "type": "Android",
     * "url": "https://github.com/Taishi-Y/InstagramLikeColorTransitionAndroid",
     * "used": true,
     * "who": "\u4ee3\u7801\u5bb6"
     */
    private String _id;
    private String _ns;
    private String createdAt;
    private String desc;
    private List<String> images = new ArrayList<>();
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private String used;
    private String who;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_ns() {
        return _ns;
    }

    public void set_ns(String _ns) {
        this._ns = _ns;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}
