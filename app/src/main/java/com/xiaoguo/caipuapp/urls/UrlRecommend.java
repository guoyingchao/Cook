package com.xiaoguo.caipuapp.urls;

/**
 * Created by lenovo on 2016/6/29.
 */
public class UrlRecommend {
    public static String URL_RCOMMEND = "http://apis.juhe.cn/cook/index?key=aa7585faee1e548d87a5e85ac5bde4b3&cid=";
    public static String URL_CATEROY = "http://apis.juhe.cn/cook/category?key=aa7585faee1e548d87a5e85ac5bde4b3";
    public static String URL_EVERY = "http://apis.juhe.cn/cook/queryid?key=aa7585faee1e548d87a5e85ac5bde4b3&id=";

    //商城的url
//    public static String URL_SHOP = "http://appcdn.1zhe.com/android/index_bak.php?v=2.2.7&m=goods&op=index&ac=channel_goods&channel_num=216&page=1&type_num=26&sort_name=&sort=desc&picsize=";

    //禾禾小镇,这是分类的接口
    public static String URL_SHOP = "http://www.hehe168.com/mapi.php?act=getCategories&c=2";
    //这是分类中肉蛋奶的接口,改变category_id，就可以改变具体的内容
    /**
     * 548--全部
     * 550--海鲜
     * 549--鲜肉
     * 551--生禽
     * 552--鲜蛋
     * 553--熟食
     */
    public static String[] URL_SHOP_ITEM = {"http://www.hehe168.com/mapi.php?act=getGoods&c=2&category_id=548&count=8",
            "http://www.hehe168.com/mapi.php?act=getGoods&c=2&category_id=550&count=8",
            "http://www.hehe168.com/mapi.php?act=getGoods&c=2&category_id=549&count=8",
            "http://www.hehe168.com/mapi.php?act=getGoods&c=2&category_id=551&count=8",
            "http://www.hehe168.com/mapi.php?act=getGoods&c=2&category_id=552&count=8",
            "http://www.hehe168.com/mapi.php?act=getGoods&c=2&category_id=553&count=8"};
//    public static String URL_SHOP_ITEM = "http://www.hehe168.com/mapi.php?act=getGoods&c=2&category_id=550&count=8";
}
