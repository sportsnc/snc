package proto;

import java.util.ArrayList;

public class Transmission {
	//ũ�Ѹ� ����
	private Article data = new Article();
	private Crawler[] crawling = {null, null, null, null, null, null};
	//�����ڸ� ���� �� ũ�Ѹ� ����� �ν��Ͻ� ����. ũ�Ѹ� ������ �ν��Ͻ� ������ ���ÿ� �����ڸ� ���� ũ�Ѹ� ����
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
	//ui���� ��ƼŬ ��ü�� �Ѱ��ֱ� ���� getter
	public Article getArt() {
		return data;
	}
	
		
}