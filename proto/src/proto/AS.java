package proto;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AS {
	private Article data = null;
	
	public AS(ArrayList<String> key) {
		String url = "https://as.com/futbol/"; // 찾으려고 하는 웹페이지의 url 입력
		
		//url을 담을 document
		Document doc = null;

		try {
			//Jsoup에 url 넣기
			doc = Jsoup.connect(url).execute().parse();
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		//div 태그의  pntc-content 클래스 가져오기
		for (Element el : doc.select("div.pntc-content")) { 
			//h2태그의 title클래스 (기사의 제목)
			el.select("h2.title");
			//제목에 찾으려는 키워드가 있을경우
			for (int i = 0; i < key.size(); i++) {
				if ((el.select("a").text().contains(key.get(i).toLowerCase()))) {
					//기사 제목
					data.setHeadline(el.select("h2 a").text());
					//기사 url
					Elements elUrl = el.select("h2 a");
					data.setUrl(elUrl.first().absUrl("href"));
					//기사 날짜
					String temp = el.select("div p a .fecha").text();
					data.setDate(changeDate(temp));
					data.setSite("AS");
				}
			}
		}
	}

	//YYMMDD 형식 맞춰주기
	public int changeDate(String date) {
		int formdate = 0;
		String sp[] = date.split("/");
		formdate += Integer.parseInt(sp[2]) * 10000;
		formdate += Integer.parseInt(sp[1]) * 100;
		formdate += Integer.parseInt(sp[0]);

		return formdate;
	}
	public Article getArticle() {
		return data;
	}
}