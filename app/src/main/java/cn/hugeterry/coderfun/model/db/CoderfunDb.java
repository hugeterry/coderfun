package cn.hugeterry.coderfun.model.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/2/21 01:36
 */
@Database(name = CoderfunDb.NAME,version = CoderfunDb.VERSION)
public class CoderfunDb {
    public static final String NAME = "coderfun";

    public static final int VERSION = 1;
}
