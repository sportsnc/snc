package proto;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MailOnline {
   private Article data = null;
	
   public MailOnline(ArrayList<String> key) {
	   
	   data = new Article();
	   
	  //url 가져오기
      String url = "https://www.dailymail.co.uk/home/search.html?offset=0&size=50&sel=site&searchPhrase=";
      url += key.get(0).toLowerCase();
      for(int i=1;i<key.size();i++) {
         url += "+"+key.get(i).toLowerCase();
      }
      url += "&sort=recent&type=article&days=all";
      
      
      Document doc = null;
      try {
         doc = Jsoup.connect(url).get();
      } catch (IOException e) {
         e.printStackTrace();
      }
      //div태그에 sch-result 클래스 가져오기
      for (Element el : doc.select("div .sch-result")) {
    	 //h3태그의 a태그
         data.setHeadline(el.select("h3 a").text());
          //h3태그의 a태그
         Elements elUrl = el.select("h3 a");
         data.setUrl(elUrl.first().absUrl("href"));
         //h4태그
         String temp = el.select("h4").text();
         data.setDate(changeDate(temp));
         data.setSite("MailOnline");

      }
      }

      public static int changeDate(String date) {
      int formdate = 0;
      String sp[] = date.split("-");
      
      String sp_r[] = sp[1].split(" ");
      
      formdate += Integer.parseInt(sp_r[3].substring(2,4))* 10000;
      switch (sp_r[1]) {
      case "January":
           formdate += 100;
           break;
        case "February":
           formdate += 200;
           break;
        case "March":
           formdate += 300;
           break;
        case "April":
           formdate += 400;
           break;
        case "May":
           formdate += 500;
           break;
        case "June":
           formdate += 600;
           break;
        case "July":
           formdate += 700;
           break;
        case "August":
           formdate += 800;
           break;
        case "September":
           formdate += 900;
           break;
        case "October":
           formdate += 1000;
           break;
        case "November":
           formdate += 1100;
           break;
        case "December":
           formdate += 1200;
           break;

        }
      if(sp_r[2].length()==3) {
         formdate += Integer.parseInt(sp_r[2].substring(0,1));
      }
      else {
         formdate += Integer.parseInt(sp_r[2].substring(0,2));
      }
      return formdate;
   }
      
   public Article getArticle() {
	   return data;
   }
}