package sample;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class webCrawler {

    public ArrayList<String> data = new ArrayList<>();
    public ArrayList<Double> result = new ArrayList<>();

    public webCrawler() {}

    public void setResult() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        HttpGet request = new HttpGet("https://rate.bot.com.tw/xrt");
        request .setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36" );

        try {
            response = httpClient.execute(request);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity httpEntity = response.getEntity();
                String html = EntityUtils.toString(httpEntity, "utf-8");

                Document document = Jsoup.parse(html);
                Element ie11andabove = document.getElementById("ie11andabove");
                Elements curtype = ie11andabove.select("tr");
                for (Element cur : curtype){
                    Elements curdata = cur.getElementsByClass("rate-content-cash text-right print_hide");
                    for (Element d : curdata){
                        data.add(d.text());
                    }
                }
            }
            else {
                System.out.println("Not 200");
                System.out.println(EntityUtils.toString(response.getEntity(), "utf-8" ));
            }
        }
        catch (ClientProtocolException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            HttpClientUtils.closeQuietly(response);
            HttpClientUtils.closeQuietly(httpClient);
        }

        for (int i = 1; i < data.size(); i+=2){
            if (i == 17)
                continue;
            result.add(Double.parseDouble(data.get(i)));
        }
    }

    public ArrayList<Double> getResult(){
        return result;
    }
}
