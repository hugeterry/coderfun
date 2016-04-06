package cn.hugeterry.coderfun.model.beans;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/2/15 19:29
 */
public class Results {
    /**
     "_id": "570320ed6776596340b1d1ed",
     "_ns": "ganhuo",
     "createdAt": "2016-04-05T10:20:29.315Z",
     "desc": "\u5c01\u88c5\u90a3\u4e9b\u4e8b-RecyclerView\u5c01\u88c5\u5b9e\u8df5",
     "publishedAt": "2016-04-05T10:45:46.487Z",
     "source": "chrome",
     "type": "Android",
     "url": "http://www.jianshu.com/p/a6f158d1a9c9",
     "used": true,
     "who": "AndWang"
     */
    private String _id;
    private String _ns;
    private String createdAt;
    private String desc;
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
