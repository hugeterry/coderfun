package cn.hugeterry.coderfun.model.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.ArrayList;
import java.util.List;

import cn.hugeterry.coderfun.model.beans.Results;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/2/21 01:43
 */
@Table(database = CoderfunDB.class)
public class FunDbBean extends BaseModel {

    @PrimaryKey(autoincrement = true)
    int id=1602;


    public List<Results> part_list = new ArrayList<>();


    public List<List<Results>> real_list = new ArrayList<>();


    public List<Results> girly_list = new ArrayList<>();

}
