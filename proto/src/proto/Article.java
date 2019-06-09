package proto;

import java.util.ArrayList;

public class Article {

   public Article() {
      // �����ڸ� ���� �ν��Ͻ� ������ ��̸���Ʈ �ν��Ͻ� ����
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


   //��� ��簡 ����Ǿ��ִ��� �˷���
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
   
   public void sortFn() {
		int best = 0;
		int tmp = 0;
		for(int i = 0; i < Headline.size(); i++) {
			best = date.get(i);
			for(int j = i; j < Headline.size(); j++) {
				if (date.get(j) > best) {
					best =  date.get(j);
					tmp = date.get(i);
					date.set(i, date.get(j));
					date.set(j, tmp);
				}
			}
		}
	}
}