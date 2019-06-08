package proto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BBC extends Crawler {

	public BBC(ArrayList<String> key) throws Exception {
		// 李얠쑝�젮怨� �븯�뒗 �쎒�럹�씠吏��쓽 url �엯�젰
		String url = "https://www.bbc.co.uk/search?q=";
		data = new Article();
		// url�쓣 �떞�쓣 document
		Document doc = null;
		Elements element = null;

		//李얠쑝�젮�뒗 �떒�뼱媛� �룷�븿�맂 �쎒�럹�씠吏� 媛��졇�삤湲�
		for (int i = 0; i < key.size(); i++) {
			String urlTmp = url + key.get(i) + "&filter=sport&suggid=";
			try {
				doc = Jsoup.connect(urlTmp).get(); 
			}

			catch (IOException e) {
				e.printStackTrace();
			}

			//article �깭洹� 媛��졇�삤湲�
			element = doc.select("article");
			for (Element el : element.select("article")) {
				//h1 �깭洹몄쓽 a�깭洹� 媛��졇�삤湲�(�젣紐�)
				data.setHeadline(el.select("h1 a").text());
				//湲곗궗�쓽 url 媛��졇�삤湲�
				Elements elUrl = el.select("a[href]");
				data.setUrl(elUrl.first().absUrl("href"));
				//湲곗궗�쓽 �궇吏� 媛��졇�삤湲�
				String temp = el.select(".display-date").text();
				System.out.println(el.select(".display-date").text());
				data.setDate(changeDate(temp));
				data.setSite("BBC");
			}
		}
	}
	
	//YYYYMMDD �삎�떇 留욎떠二쇨린
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

}