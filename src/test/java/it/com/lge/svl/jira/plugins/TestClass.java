package it.com.lge.svl.jira.plugins;


import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by sunjoo on 2015. 4. 15..
 */
public class TestClass {
    public static void main(String[] args){
        HttpClient client = new DefaultHttpClient();
        String username = "allessunjoo.park";
        String password = "Udtq09IpYdYWKEPsOigOrUx9M3nErGp6Kct+rPDOzw";
        String auth = username + ":" + password;
        String encodedAuth = Base64.encodeBase64String(auth.getBytes());
        HttpGet httpGet = new HttpGet("http://wall.lge.com:8110/a/changes/?q=message:WEBOSDEFEC-7643");
        httpGet.addHeader("Authorization", "Basic " + encodedAuth);
        System.out.println("sdf");
    }
}
