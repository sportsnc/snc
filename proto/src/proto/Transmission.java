package proto;

import java.util.ArrayList;

public class Transmission {
	//크롤링 모듈들
	private Article data = new Article();
	private TheSun thesunArticle = null;
	private AS AsArticle = null;
	private SkySports SSArticle = null;
	private MailOnline MoArticle = null;
	private BBC BBCArticle = null;
	
	//생성자를 통해 각 크롤링 모듈의 인스턴스 생성. 크롤링 모듈들은 인스턴스 생성과 동시에 생성자를 통해 크롤링 시작
	public Transmission(ArrayList<String> key) throws Exception {
		thesunArticle = new TheSun(key);
		AsArticle = new AS(key);
		SSArticle = new SkySports(key);
		MoArticle = new MailOnline(key);
		BBCArticle = new BBC(key);

		//크롤링된 데이터를 Transmission에서 생성한 아티클 객체에 저장
		for (int i = 0; i < thesunArticle.getArticle().getHowManyData(); i++) {
			data.setDate(thesunArticle.getArticle().getDate(i));
			data.setHeadline(thesunArticle.getArticle().getHeadline(i));
			data.setUrl(thesunArticle.getArticle().getUrl(i));
			data.setSite(thesunArticle.getArticle().getSite(i));
		}

		for (int i = 0; i < AsArticle.getArticle().getHowManyData(); i++) {
			data.setDate(AsArticle.getArticle().getDate(i));
			data.setHeadline(AsArticle.getArticle().getHeadline(i));
			data.setUrl(AsArticle.getArticle().getUrl(i));
			data.setSite(AsArticle.getArticle().getSite(i));
		}

		for (int i = 0; i < SSArticle.getArticle().getHowManyData(); i++) {
			data.setDate(SSArticle.getArticle().getDate(i));
			data.setHeadline(SSArticle.getArticle().getHeadline(i));
			data.setUrl(SSArticle.getArticle().getUrl(i));
			data.setSite(SSArticle.getArticle().getSite(i));
		}
		for (int i = 0; i < MoArticle.getArticle().getHowManyData(); i++) {
			data.setDate(MoArticle.getArticle().getDate(i));
			data.setHeadline(MoArticle.getArticle().getHeadline(i));
			data.setUrl(MoArticle.getArticle().getUrl(i));
			data.setSite(MoArticle.getArticle().getSite(i));
		}
		for (int i = 0; i < BBCArticle.getArticle().getHowManyData(); i++) {
			data.setDate(BBCArticle.getArticle().getDate(i));
			data.setHeadline(BBCArticle.getArticle().getHeadline(i));
			data.setUrl(BBCArticle.getArticle().getUrl(i));
			data.setSite(BBCArticle.getArticle().getSite(i));
		}
	}
	//ui모듈로 아티클 객체를 넘겨주기 위한 getter
	public Article getArt() {
		return data;
	}
}