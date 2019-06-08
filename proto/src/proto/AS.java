package proto;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AS extends Crawler{
	
	public AS(ArrayList<String> key) {
		data = new Article();
		String url = "https://as.com/futbol/"; // 李얠쑝�젮怨� �븯�뒗 �쎒�럹�씠吏��쓽 url �엯�젰
		
		//url�쓣 �떞�쓣 document
		Document doc = null;

		try {
			//Jsoup�뿉 url �꽔湲�
			doc = Jsoup.connect(url).execute().parse();
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		//div �깭洹몄쓽  pntc-content �겢�옒�뒪 媛��졇�삤湲�
		for (Element el : doc.select("div.pntc-content")) { 
			//h2�깭洹몄쓽 title�겢�옒�뒪 (湲곗궗�쓽 �젣紐�)
			el.select("h2.title");
			//�젣紐⑹뿉 李얠쑝�젮�뒗 �궎�썙�뱶媛� �엳�쓣寃쎌슦
			for (int i = 0; i < key.size(); i++) {
				if ((el.select("a").text().contains(key.get(i).toLowerCase()))) {
					//湲곗궗 �젣紐�
					data.setHeadline(el.select("h2 a").text());
					//湲곗궗 url
					Elements elUrl = el.select("h2 a");
					data.setUrl(elUrl.first().absUrl("href"));
					//湲곗궗 �궇吏�
					String temp = el.select("div p a .fecha").text();
					data.setDate(changeDate(temp));
					data.setSite("AS");
				}
			}
		}
	}

	//YYMMDD �삎�떇 留욎떠二쇨린
	public int changeDate(String date) {
		int formdate = 0;
		String sp[] = date.split("/");
		formdate += Integer.parseInt(sp[2]) * 10000;
		formdate += Integer.parseInt(sp[1]) * 100;
		formdate += Integer.parseInt(sp[0]);

		return formdate;
	}

}