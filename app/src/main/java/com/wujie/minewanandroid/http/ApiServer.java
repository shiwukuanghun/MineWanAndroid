package com.wujie.minewanandroid.http;


import com.wujie.minewanandroid.bean.BannerBean;
import com.wujie.minewanandroid.bean.BaseBean;
import com.wujie.minewanandroid.bean.HomeBean;
import com.wujie.minewanandroid.bean.HttpsRequest;
import com.wujie.minewanandroid.bean.KnowledgeBean;
import com.wujie.minewanandroid.bean.NavigationBean;
import com.wujie.minewanandroid.bean.PageListDataBean;
import com.wujie.minewanandroid.bean.ProjectItemBean;
import com.wujie.minewanandroid.bean.ProjectTypeBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by HuangBin on 2018/11/15 10:44.
 * Description：
 */

public interface ApiServer {

    @GET("article/list/{page}/json")
    Observable<BaseBean<PageListDataBean<HomeBean>>> getHomeList(@Path("page") int page);

    @GET("banner/json")
    Observable<BaseBean<List<BannerBean>>> getBanner();

    @POST("user/login")
    @FormUrlEncoded
    Observable<BaseBean<Object>> login(@Field("username") String username, @Field("password") String password);

    //注册
    @POST("user/register")
    @FormUrlEncoded
    Observable<BaseBean<Object>> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);

//Latitude=39.922705&Longitude=116.416636&start=0&productName=华为&limit=10
    @POST("shop/queryNearShop")
    Observable<Object> getResult(@Body HttpsRequest httpsRequest);
//    Observable<Object> getReuslt(@Query("Latitude") String Latitude, @Query("Longitude") String Longitude, @Query("start") int start, @Query("productName") String productName, @Query("limit") int limit);


    @POST("lg/collect/{id}/json")
    Observable<BaseBean<Object>> collectArticle(@Path("id") int id);


    @POST("lg/collect/{id}/json")
    Observable<BaseBean<Object>> collectInsideArticle(@Path("id") int id);

    @POST("lg/uncollect_originId/{id}/json")
    Observable<BaseBean<Object>> unCollectArticle(@Path("id") int id);

    @GET("tree/json")
    Observable<BaseBean<List<KnowledgeBean>>> getKnowledge();

    @GET("navi/json")
    Observable<BaseBean<List<NavigationBean>>> getNavigation();

    @GET("project/tree/json")
    Observable<BaseBean<List<ProjectTypeBean>>> getProjectType();

    @GET("project/list/1/json")
    Observable<BaseBean<PageListDataBean<ProjectItemBean>>> getProjectList(@Query("cid") int cid);
}
