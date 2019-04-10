package zixun.xcb.example.zixun.model.bean.zhihu;

import com.google.gson.annotations.SerializedName;

import java.util.List;


/*
   知乎日报Api：https://github.com/izzyleung/ZhihuDailyPurify/wiki/%E7%9F%A5%E4%B9%8E%E6%97%A5%E6%8A%A5-API-%E5%88%86%E6%9E%90
   借鉴源代码:一之APP
 */
public class ZhihuDetailBean {

    /*
      body : HTML 格式的新闻
      image-source : 图片的内容提供方。为了避免被起诉非法使用图片，在显示图片时最好附上其版权信息。
      title : 新闻标题
      image : 获得的图片同 最新消息 获得的图片分辨率不同。这里获得的是在文章浏览界面中使用的大图。
      share_url : 供在线查看内容与分享至 SNS 用的 URL
      js : 供手机端的 WebView(UIWebView) 使用
      recommenders : 这篇文章的推荐者
      ga_prefix : 供 Google Analytics 使用
      section : 栏目的信息
      thumbnail : 栏目的缩略图
      id : 该栏目的 id
      name : 该栏目的名称
      type : 新闻的类型
      id : 新闻的 id
      css : 供手机端的 WebView(UIWebView) 使用
            可知，知乎日报的文章浏览界面利用 WebView(UIWebView) 实现
     */
    @SerializedName("body")
    private String body;
    @SerializedName("image_source")
    private String image_source;
    @SerializedName("title")
    private String title;
    @SerializedName("image")
    private String image;
    @SerializedName("share_url")
    private String share_url;
    @SerializedName("ga_prefix")
    private String ga_prefix;
    @SerializedName("type")
    private int type;
    @SerializedName("id")
    private int id;
    @SerializedName("js")
    private List<String> js;
    @SerializedName("css")
    private List<String> css;


}
