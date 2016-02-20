package cn.hugeterry.coderfun.model.beans;

import java.util.List;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/2/15 19:41
 */
public class DataResults {
    /**
     *{"error":false,
     * "results":[{"who":"MVP","publishedAt":"2016-02-15T03:49:24.372Z","desc":"一个轻量级的YouTube","type":"Android","url":"https://github.com/theScrabi/NewPipe","used":true,"objectId":"56c1401a71cfe4005c961746","createdAt":"2016-02-15T03:03:54.236Z","updatedAt":"2016-02-15T03:49:25.335Z"},{"who":"MVP","publishedAt":"2016-02-15T03:49:24.370Z","desc":"The missing bridge between Java and native C++","type":"Android","url":"https://github.com/bytedeco/javacpp","used":true,"objectId":"56c13f0da34131005b81bddd","createdAt":"2016-02-15T02:59:25.798Z","updatedAt":"2016-02-15T03:49:26.327Z"},{"who":"有时放纵","publishedAt":"2016-02-15T03:49:24.369Z","desc":"自动将App的布局截图到各个设备上","type":"Android","url":"https://github.com/fastlane/screengrab","used":true,"objectId":"56c139327db2a200599cd825","createdAt":"2016-02-15T02:34:26.161Z","updatedAt":"2016-02-15T03:49:26.327Z"},{"who":"Jason","publishedAt":"2016-02-15T03:49:24.364Z","desc":"《Kotlin for android developers》中文版翻译","type":"Android","url":"https://github.com/wangjiegulu/kotlin-for-android-developers-zh","used":true,"objectId":"56c1354f75c4cd7bcfcb5f89","createdAt":"2016-02-15T02:17:51.665Z","updatedAt":"2016-02-15T03:49:25.329Z"},{"who":"有时放纵","publishedAt":"2016-02-15T03:49:24.362Z","desc":"一个变形的toolbar，可以随时折叠和展开","type":"Android","url":"https://github.com/badoualy/morphy-toolbar","used":true,"objectId":"56c0215832132c0052b50812","createdAt":"2016-02-14T06:40:24.422Z","updatedAt":"2016-02-15T03:49:25.328Z"},{"who":"Jason","publishedAt":"2016-02-04T07:14:01.798Z","desc":"工具类库","type":"Android","url":"https://github.com/CommonUtils/android","used":true,"objectId":"56b1c0d175c4cd7bcf3f3b6f","createdAt":"2016-02-03T08:56:49.059Z","updatedAt":"2016-02-04T07:14:02.717Z"},{"who":"Jason","publishedAt":"2016-02-04T07:14:01.797Z","desc":"一头headerviewpager可以固定和滚动的片段","type":"Android","url":"https://github.com/XavierSAndroid/MagicHeaderViewPager","used":true,"objectId":"56b1bef775c4cd7bcf3f2bb0","createdAt":"2016-02-03T08:48:55.519Z","updatedAt":"2016-02-04T07:14:03.464Z"},{"who":"Jason","publishedAt":"2016-02-04T07:14:01.794Z","desc":"通过手势模拟的iOS3DTouch效果","type":"Android","url":"https://github.com/nantaphop/HoverTouchView","used":true,"objectId":"56b1bde775c4cd7bcf3f2256","createdAt":"2016-02-03T08:44:23.205Z","updatedAt":"2016-02-04T07:14:02.494Z"},{"who":"wuzheng","publishedAt":"2016-02-04T07:14:01.792Z","desc":"CodeBoy微信抢红包外挂","type":"Android","url":"http://www.happycodeboy.com/index.php/archives/10/","used":true,"objectId":"56b199ae79bc44005049216b","createdAt":"2016-02-03T06:09:50.919Z","updatedAt":"2016-02-04T07:14:02.518Z"},{"who":"Jason","publishedAt":"2016-02-04T07:14:01.788Z","desc":"Gradle In Action 的中文版","type":"Android","url":"https://github.com/LippiOuYang/GradleInActionZh","used":true,"objectId":"56add6f5c4c971005323747c","createdAt":"2016-01-31T09:42:13.635Z","updatedAt":"2016-02-04T07:14:02.317Z"}]}
     */
    private boolean error;
    private List<Results> results;

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
