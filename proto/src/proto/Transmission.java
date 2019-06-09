package proto;

import java.util.ArrayList;

public class Transmission {
	//크롤링 모듈들
	private Article data = new Article();
	private Crawler[] crawling = {null, null, null, null, null, null};
	//생성자를 통해 각 크롤링 모듈의 인스턴스 생성. 크롤링 모듈들은 인스턴스 생성과 동시에 생성자를 통해 크롤링 시작
	public Transmission(ArrayList<String> key, ArrayList<String> whichSite, int sort) throws Exception {
		
		for(int i = 0; i < whichSite.size(); i++) {
			switch (whichSite.get(i)) {
			case "AS":
				crawling[i] = new AS(key);
				break;
			case "TheSun":
				crawling[i] = new TheSun(key);
				break;
			case "SkySports":
				crawling[i] = new SkySports(key);
				break;
			case "BBC":
				crawling[i] = new BBC(key);
				break;
			case "MailOnline":
				crawling[i] = new MailOnline(key);
				break;
			case "NBCSports":
				crawling[i] = new NBCSports(key);
				break;
			}
		}
		for (int j = 0; j < whichSite.size(); j++) {
			for (int i = 0; i < crawling[j].getArticle().getHowManyData(); i++) {
				data.setDate(crawling[j].getArticle().getDate(i));
				data.setHeadline(crawling[j].getArticle().getHeadline(i));
				data.setUrl(crawling[j].getArticle().getUrl(i));
				data.setSite(crawling[j].getArticle().getSite(i));
			}
		}
		
		if (sort == 1) {
			data.sortFn();
			
		}
	}
	//ui모듈로 아티클 객체를 넘겨주기 위한 getter
	public Article getArt() {
		return data;
	}
	
		
}