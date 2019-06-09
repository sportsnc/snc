package proto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NBCSports extends Crawler {

	public NBCSports(ArrayList<String> key) {
		String url = "https://www.nbcsports.com/search?search_api_views_fulltext=";

		data = new Article();

		Document doc = null;
		Elements element = null;

		for (int i = 0; i < key.size(); i++) {
			String urlTmp = url + key.get(i);
			
			try {
				doc = Jsoup.connect(urlTmp).get();
				// Document에 url 페이지의 데이터를 가져온다.
			} catch (IOException e) {
				e.printStackTrace();
			} // Document에 url 페이지의 데이터를 가져온다.
			// css선택자를 이용해 html에서 원하는 정보를 선택.
			
			element = doc.select("ol.teasers-list__list");
			for (Element el : element.select("li")) {
				if (el.select("h3").select("a").text().toLowerCase().contains(key.get(i).toLowerCase())) {
					data.setHeadline(el.select("h3").select("a").text());
					Elements elUrl = el.select("a[href]");
					data.setUrl(elUrl.first().absUrl("href"));
					String temp = el.select(".date-display-single").text();
					data.setDate(changeDate(temp));
					data.setSite("NBCSports");
				}
			}

		}

	}

	public int changeDate2(String date) {
		date = date.substring(0, 2) + date.substring(2 + 1);
		date = date.substring(0, 4) + date.substring(4 + 1);
		String year = date.substring(4, 8);
		String month = date.substring(2, 4);
		String day = date.substring(0, 2);
		String fdate = year + month + day;

		int mydate = Integer.parseInt(fdate);
		return mydate;
	}

	public int changeDate(String date) {
		int formdate = 0;
		String sp[] = date.split(",");
	
		String year = sp[2].split(" ")[1];
		String month = sp[1].split(" ")[1];
		String day = sp[1].split(" ")[2];
		formdate += Integer.parseInt(year) * 10000;
		formdate += Integer.parseInt(day);
		


		switch (month) {
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
