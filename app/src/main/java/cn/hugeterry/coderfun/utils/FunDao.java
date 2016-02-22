package cn.hugeterry.coderfun.utils;

import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;

import cn.hugeterry.coderfun.model.beans.Results;
import cn.hugeterry.coderfun.model.db.GirlyDbBean;
import cn.hugeterry.coderfun.model.db.PartDbBean;
import cn.hugeterry.coderfun.model.db.RealDbBean;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/2/21 14:57
 */
public class FunDao {
    public static void addFun_Part(List<Results> results) {
        Delete.table(PartDbBean.class);
        for (Results result : results) {
            PartDbBean partDbBean = new PartDbBean();
            partDbBean.who = result.getWho();
            partDbBean.publishedAt = result.getPublishedAt();
            partDbBean.desc = result.getDesc();
            partDbBean.type = result.getType();
            partDbBean.url = result.getUrl();
            partDbBean.used = result.getUsed();
            partDbBean.objectId = result.getObjectId();
            partDbBean.createdAt = result.getCreatedAt();
            partDbBean.updatedAt = result.getUpdatedAt();
            partDbBean.save();
        }

    }

    public static List<Results> getFun_Part() {
        List<Results> results = new ArrayList<>();
        List<PartDbBean> partDbBeen = new Select().from(PartDbBean.class).queryList();
        for (int i = 0; i < partDbBeen.size(); i++) {
            Results result = new Results();
            result.setWho(partDbBeen.get(i).who);
            result.setPublishedAt(partDbBeen.get(i).publishedAt);
            result.setDesc(partDbBeen.get(i).desc);
            result.setType(partDbBeen.get(i).type);
            result.setUrl(partDbBeen.get(i).url);
            result.setUsed(partDbBeen.get(i).used);
            result.setObjectId(partDbBeen.get(i).objectId);
            result.setCreatedAt(partDbBeen.get(i).createdAt);
            result.setUpdatedAt(partDbBeen.get(i).updatedAt);
            results.add(result);
        }
        return results;
    }

    public static void addFun_Girly(List<Results> results) {
        Delete.table(GirlyDbBean.class);
        for (Results result : results) {
            GirlyDbBean girlyDbBean = new GirlyDbBean();
            girlyDbBean.who = result.getWho();
            girlyDbBean.publishedAt = result.getPublishedAt();
            girlyDbBean.desc = result.getDesc();
            girlyDbBean.type = result.getType();
            girlyDbBean.url = result.getUrl();
            girlyDbBean.used = result.getUsed();
            girlyDbBean.objectId = result.getObjectId();
            girlyDbBean.createdAt = result.getCreatedAt();
            girlyDbBean.updatedAt = result.getUpdatedAt();
            girlyDbBean.save();
        }

    }

    public static List<List<Results>> getFun_Real() {
        List<RealDbBean> realDbBeen = new Select().from(RealDbBean.class).queryList();
        List<List<Results>> results_list = new ArrayList<>();
        for (int i = 0; i < realDbBeen.size() / 3; i++) {
            List<Results> results = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                int k= 3*i+j;
                Results result = new Results();
                result.setWho(realDbBeen.get(k).who);
                result.setPublishedAt(realDbBeen.get(k).publishedAt);
                result.setDesc(realDbBeen.get(k).desc);
                result.setType(realDbBeen.get(k).type);
                result.setUrl(realDbBeen.get(k).url);
                result.setUsed(realDbBeen.get(k).used);
                result.setObjectId(realDbBeen.get(k).objectId);
                result.setCreatedAt(realDbBeen.get(k).createdAt);
                result.setUpdatedAt(realDbBeen.get(k).updatedAt);
                results.add(result);
            }
            results_list.add(results);
        }
        return results_list;
    }

    public static void addFun_Real(List<List<Results>> results) {
        Delete.table(RealDbBean.class);
        for (List<Results> result : results) {
            for (int i = 0; i < result.size(); i++) {
                RealDbBean realDbBean = new RealDbBean();
                realDbBean.who = result.get(i).getWho();
                realDbBean.publishedAt = result.get(i).getPublishedAt();
                realDbBean.desc = result.get(i).getDesc();
                realDbBean.type = result.get(i).getType();
                realDbBean.url = result.get(i).getUrl();
                realDbBean.used = result.get(i).getUsed();
                realDbBean.objectId = result.get(i).getObjectId();
                realDbBean.createdAt = result.get(i).getCreatedAt();
                realDbBean.updatedAt = result.get(i).getUpdatedAt();
                realDbBean.save();
            }
        }

    }

    public static List<Results> getFun_Girly() {
        List<Results> results = new ArrayList<>();
        List<GirlyDbBean> girlyDbBeen = new Select().from(GirlyDbBean.class).queryList();
        for (int i = 0; i < girlyDbBeen.size(); i++) {
            Results result = new Results();
            result.setWho(girlyDbBeen.get(i).who);
            result.setPublishedAt(girlyDbBeen.get(i).publishedAt);
            result.setDesc(girlyDbBeen.get(i).desc);
            result.setType(girlyDbBeen.get(i).type);
            result.setUrl(girlyDbBeen.get(i).url);
            result.setUsed(girlyDbBeen.get(i).used);
            result.setObjectId(girlyDbBeen.get(i).objectId);
            result.setCreatedAt(girlyDbBeen.get(i).createdAt);
            result.setUpdatedAt(girlyDbBeen.get(i).updatedAt);
            results.add(result);
        }
        return results;
    }
}
