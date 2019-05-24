package proto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BBC {
	Article data = null;

	public BBC(ArrayList<String> key) throws Exception {
		// 찾으려고 하는 웹페이지의 url 입력
		String url = "https://www.bbc.co.uk/search?q=";
		data = new Article();
		// url을 담을 document
		Document doc = null;
		Elements element = null;

		//찾으려는 단어가 포함된 웹페이지 가져오기
		for (int i = 0; i < key.size(); i++) {
			String urlTmp = url + key.get(i) + "&filter=sport&suggid=";
			try {
				doc = Jsoup.connect(urlTmp).get(); 
			}

			catch (IOException e) {
				e.printStackTrace();
			}

			//article 태그 가져오기
			element = doc.select("article");
			for (Element el : element.select("article")) {
				//h1 태그의 a태그 가져오기(제목)
				data.setHeadline(el.select("h1 a").text());
				//기사의 url 가져오기
				Elements elUrl = el.select("a[href]");
				data.setUrl(elUrl.first().absUrl("href"));
				//기사의 날짜 가져오기
				String temp = el.select(".display-date").text();
				System.out.println(el.select(".display-date").text());
				data.setDate(changeDate(temp));
				data.setSite("BBC");
			}
		}
	}
	
	//YYYYMMDD 형식 맞춰주기
	public int changeDate(String date) {
		int formdate = 0;
		String sp[] = date.split(" ");
		System.out.println(sp[0] + sp[1] + sp[2]);
		formdate += Integer.parseInt(sp[2]) % 100 * 10000;
		formdate += Integer.parseInt(sp[0]);

		switch (sp[1]) {
		case "January":
		case "Jan":
			formdate += 100;
			break;
		case "February":
		case "Feb":
			formdate += 200;
			break;
		case "March":
		case "Mar":
			formdate += 300;
			break;
		case "April":
		case "Apr":
			formdate += 400;
			break;
		case "May":
			formdate += 500;
			break;
		case "June":
		case "Jun":
			formdate += 600;
			break;
		case "July":
		case "Jul":
			formdate += 700;
			break;
		case "August":
		case "Aug":
			formdate += 800;
			break;
		case "September":
		case "Sep":
			formdate += 900;
			break;
		case "October":
		case "Oct":
			formdate += 1000;
			break;
		case "November":
		case "Nov":
			formdate += 1100;
			break;
		case "December":
		case "Dec":
			formdate += 1200;
			break;

		}

		return formdate;

	}

	public Article getArticle() {
		return data;
	}
}