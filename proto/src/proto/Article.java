package proto;

import java.util.ArrayList;

public class Article {

   public Article() {
      // 생성자를 통해 인스턴스 생성시 어레이리스트 인스턴스 생성
      date = new ArrayList<Integer>();
      Headline = new ArrayList<String>();
      Url = new ArrayList<String>();
      Site = new ArrayList<String>();
   }

   private ArrayList<Integer> date;
   private ArrayList<String> Headline;
   private ArrayList<String> Url;
   private ArrayList<String> Site;

   //setter methods
   public void setDate(int num) {
      date.add(num);
   }

   public void setHeadline(String head) {
      Headline.add(head);
   }

   public void setUrl(String url) {
      Url.add(url);
   }
   
   public void setSite(String site) {
      Site.add(site);
   }


   //몇개의 기사가 저장되어있는지 알려줌
   public int getHowManyData() {
      return Headline.size();
   }

   //getter methods
   public int getDate(int num) {
      return date.get(num);
   }

   public String getHeadline(int num) {
      return Headline.get(num);
   }

   public String getUrl(int num) {
      return Url.get(num);
   }
   public String getSite(int num) {
      return Site.get(num);
   }
}