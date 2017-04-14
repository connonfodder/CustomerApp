package com.aadhk.customer.data.contract;

import com.aadhk.customer.bean.Company;
import com.aadhk.customer.bean.Search;
import com.aadhk.customer.bean.SearchRequest;
import com.aadhk.library.ui.BaseModel;
import com.aadhk.library.ui.BasePresenter;
import com.aadhk.library.ui.BaseView;

import java.util.List;

import rx.Observable;

/**
 * Created by jack on 05/12/2016.
 * 接口定义: 热点由服务器根据前一天自动生成，一般为10个
 * 个人纪录: 上一次搜索后获取下拉数据，点击单项进入餐厅或者根据名称搜索菜式才为有效搜索动作，将之上传服务器
 * 一次性拿到热点(10) +  个人纪录(max(20))
 * 热点         customerId= 0
 * 个人纪录     customerId > 0
 *
 * 搜索分为餐厅名称搜索和菜式风格搜索
 * 搜索类型
 Type=3时， type=1 & 2 = 3, 优先搜索餐厅数据，再搜索菜式风格，比例为2:1,按照maxCount来算详细个数，默认为10:5;
 Type=4时，为下图点击菜式风格结果时，根据菜式风格搜索餐厅，type=4;
 Type=8时，为个人/热点搜索，或者下图的搜索按钮，既搜索餐厅名称又搜索菜式名称
         特别说明: 当type=4或者8时需要将keyword加入数据库中添加搜索记录。
 */

public interface SearchContract {

    interface Model extends BaseModel {
        Observable<List<Search>> fetchHotAndHistroySearch();
        Observable<List<Company>> search(SearchRequest request);
        Observable<List<Search>> deleteRecord(long userId);
    }

    interface View extends BaseView {
        void fetchHotAndHistroySearchResult(List<Search> result);
        void searchResult(List<Company> result, int type);
    }

    abstract class Presenter extends BasePresenter<SearchContract.View, SearchContract.Model> {
        public abstract void fetchHotAndHistroySearchRequest();  //根据餐厅/菜市关键词搜索餐厅  type=8
        public abstract void searchRequest(SearchRequest request);  //手动输入时搜索(type=3) 和 点击菜市风格时搜索(type=4)
        public abstract void deleteRecordRequest(long userId);
    }
}
