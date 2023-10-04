package ten.give.common.sms;

import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


// 미사용

public class SMS {

    @Value("${sms.FromPhone}")
    private String fromPhone;

    public  void sendSMS(String toPhone, String code) throws Exception {


        String targetUrl = "http://api.coolsms.co.kr/messages/v4/send";
        String parameters = "{\"message\":{\"to\":\" " +  fromPhone + "\",\"from\":\" " + toPhone + "\",\"text\":\""+ code +"\",\"type\":\"SMS\"}}";

        URL url = new URL(targetUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("POST");

        con.setRequestProperty("Authorization", "HMAC-SHA256 apiKey=NCSWC7AOOEBXIDJ8, date=2019-07-01T00:41:48Z, salt=jqsba2jxjnrjor, signature=1779eac71a24cbeeadfa7263cb84b7ea0af1714f5c0270aa30ffd34600e363b4");
        con.setRequestProperty("Content-Type", "application/json");

        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(parameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line;
        StringBuffer response = new StringBuffer();
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();

    }
}