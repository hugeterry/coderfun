package cn.hugeterry.coderfun.utils;

import android.content.Context;
import android.content.Intent;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/2/22 21:43
 */
public class ShareUtils {
    public static void shareText(Context context, String shareText) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT, shareText+ " 来自「趣刻」APP");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, "分享"));
    }
}