package com.leanway.jsoup_data;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String path = "http://www.leanway.cn/interact/new/";
    private TextView html_text;
    private ArrayList<News> mNewses = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        html_text = (TextView) findViewById(R.id.html_text);
        try {
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    try {
                        String html = HtmlUtil.getHtml(path);
                        Message message = new Message();
                        message.what = 1;
                        Bundle bundle = new Bundle();
                        bundle.putString("data",html);
                        message.setData(bundle);
                        mHandler.sendMessage(message);
                    } catch (Exception e) {
                        //Toast.makeText(getApplicationContext(),"获取失败",Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }.start();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"获取失败",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    String data = msg.getData().getString("data");
                    jsoupData(data);
                    break;
            }
        }
    };

    private void jsoupData(String data) {
        News news = null;
        Elements summary = new Elements();
        Elements newsUrl = new Elements();
        Document doc = Jsoup.parse(data);
        Elements issueTime = doc.select("div.news-left li a.news-time");
        Elements newsTitle = doc.select("div.news-left li h3");
        Elements imageUrl  = doc.select("div.news-left li img");
        Elements newsInfo = doc.select("div.news-left li div.news-info a");
        for (int i = 0;i < newsInfo.size();i++){
            if(i==0 || i%2==0){
                newsUrl.add(newsInfo.get(i));
            }else {
                summary.add(newsInfo.get(i));
            }
        }
        for (int i = 0; i < issueTime.size(); i++){
            news = new News();
            news.setIssueTime(issueTime.get(i).text());
            news.setTitleImageUrl(imageUrl.get(i).attr("src"));
            news.setTitle(newsTitle.get(i).text());
            news.setSummary(summary.get(i).text());
            news.setNewsUrl(summary.get(i).attr("href"));
            mNewses.add(news);
        }
        int i = 0;
    }

}
