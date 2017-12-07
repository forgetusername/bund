package b;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;

public class TT {
	
//	private static final String DOMAIN = "iguba.eastmoney.com";
	private static final String DOMAIN = "guba.eastmoney.com";
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		String cookie = "emstat_bc_emcount=17803652942018460805; st_pvi=15175095154165; st_si=83095836082192; EmPaVCodeCo=98b5d4cafbf442628d9feaba6556c8ff; ct=JwR-HOY1iSKC-aFCzIhhu08wn6gLIljxEXYkPb4UDV0OzeoM01IGxHVI-kQrNMLj1zgV04doc899KBvXv5vRVgWBZ-QqHPRsFFGgEel3Zr-UHNxOXXLMuSUc8K7hAF-dRE3sMz_GmK6NbN0t84ODUAt6VOOBNpf9muAeFHadCJk; ut=FobyicMgeV4ukx1pWSXZAr-QsOs2CMRPlJq0TISRGzL07Jkz1X3lH9Q_m-M1F43yV_NOZJGEptZwGnypcrXtoYbVL9KWFnkuFjmi1nQAgzeDLyhCb41W5pe6th-b3UHK7ijTeKwkqR9reFnfirtAGF4EB_gkf13Xl3j8LqYuBP4ZDQTM17j-bhEog_7dGa7OinQysa_3YRDg3lS_0IIyPY9fGXejy4bk-YFF-c8skEpFSB2HRtfORmPvn12QPEd_nOArmOuOAoD-kaDbh2CPRoLVE545iOk-; pi=6091094906702030%3bm6091094906702030%3b%e9%97%ae%e9%81%93%e5%a4%a9%3bODqSL8wSomMbozJH5KOEci6Vfh%2fN8RoKILjfGGUrDzDLO0pDqMyICWfZ60O0oDmBTD0Ew8MbGcHfXTXhRdKU3fJURrFgkv%2fXKjkCb2mJsfyic40RX1aa9CJ4uN%2bxNoe3g2mHkZleVI57zQJp%2f5h4jEZ14qSQU24s7dhm4Vi2id2dQmV9U6R7F4mIswMkuZJJNNOzZz1j%3bxGyFbYtwHd9wSVCkhHRDSZewh53ndhpp91viyeMYUjbNANsXTS0g%2bef82Ea%2b43kHMqVEp8LHZexMZNEsp1wCjy9eEpwL2exG0WQ%2flHiDWFwWfyb79mj0Wxsvt4DaKttm4Bb9bMDLOz7xQLAMxi3N47doqLqT6g%3d%3d; uidal=6091094906702030%e9%97%ae%e9%81%93%e5%a4%a9; sid=114711039; vtpst=|; emstat_ss_emcount=28_1512668397_197888644; qgqp_b_id=e139d6c89fb936112506f3804dd865ef";
//		System.out.println(getUserName(cookie));
//		String postId = getLastPostId(cookie);
//		System.out.println(postId);
		String code = "of001938";
//		System.out.println(existsPost(code, postId, cookie));
		System.out.println(sendPost("wait me", "at 2018", code, cookie));
	}
	
	public static boolean sendPost(String title, String text, String code, String cookie) {
		String url = "http://guba.eastmoney.com/action.aspx";
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("action", "add3"));
		nvps.add(new BasicNameValuePair("yuan_id", "0"));
		nvps.add(new BasicNameValuePair("title", title));
		nvps.add(new BasicNameValuePair("text", text));
		nvps.add(new BasicNameValuePair("code", code));
		nvps.add(new BasicNameValuePair("pdf", ""));
		nvps.add(new BasicNameValuePair("state", "0"));
		nvps.add(new BasicNameValuePair("pic", ""));
		nvps.add(new BasicNameValuePair("postvalid", "1"));
		nvps.add(new BasicNameValuePair("yzm_id", ""));
		nvps.add(new BasicNameValuePair("yzm", ""));
		nvps.add(new BasicNameValuePair("quanxian", "0"));
		nvps.add(new BasicNameValuePair("step", "1"));
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Referer", "http://guba.eastmoney.com/list," + code + ".html");
			httpPost.setHeader("Origin", "http://guba.eastmoney.com");
			httpPost.setHeader("Host", "guba.eastmoney.com");
			httpPost.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/61.0.3163.100 Chrome/61.0.3163.100 Safari/537.36");
			httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
			
			// {"result":true,"id":735268858,"autozc":0,"post_state":2,"appeal_state":0,"user_state":30,"error":"","error_code":"0"}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
			HttpResponse response = getHttpResponse(cookie, httpPost);
			System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
			return response.getStatusLine().getStatusCode() == 200;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean existsPost(String code, String postId, String cookie) {
		String url = "http://guba.eastmoney.com/news," + code + "," + postId + ".html";
		try {
			HttpResponse response = getHttpResponse(cookie, new HttpGet(url));
			return response.getStatusLine().getStatusCode() == 200;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static String getLastPostId(String cookie) {
		String url = "http://iguba.eastmoney.com/myart";
		try {
			String html = getHtml(url, cookie);
			Pattern p = Pattern.compile("post_id\":(\\w+),");
			Matcher matcher = p.matcher(html);
			if (matcher.find()) {
				return matcher.group(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static HttpResponse getHttpResponse( String cookie, HttpUriRequest request) throws ClientProtocolException, IOException {
		CookieStore cookieStore = getCookieStore(cookie);
		CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		HttpResponse response = client.execute(request);
		return response;
	}
	
	public static String getHtml(String url, String cookie) throws ClientProtocolException, IOException {
		HttpResponse response = getHttpResponse(cookie, new HttpGet(url));
		return EntityUtils.toString(response.getEntity(), "utf-8");
	}
	
	public static String getUserName(String cookie) {
		String url = "http://iguba.eastmoney.com/myart";
		try {
			String html = getHtml(url, cookie);
			String username = Jsoup.parse(html).select(".username").text();
			if (username == null || username.trim().equals("")) {
				return null;
			}
			return username;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static CookieStore getCookieStore(String cookieStr) {
		CookieStore cookieStore = new BasicCookieStore();
		String[] keyValues = cookieStr.split(";");
		for (String keyValue : keyValues) {
			String[] kv = keyValue.split("=");
			BasicClientCookie cookie = new BasicClientCookie(kv[0], kv[1]);
			cookie.setDomain(DOMAIN);
			cookieStore.addCookie(cookie);
		}
		return cookieStore;
	}

}