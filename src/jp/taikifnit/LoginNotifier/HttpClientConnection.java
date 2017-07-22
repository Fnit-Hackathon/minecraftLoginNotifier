package jp.taikifnit.LoginNotifier;

import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


import com.oracle.javafx.jmx.json.JSONException;
import org.bukkit.Bukkit;

/**
 * Created by taikifnit on 2017/01/03.
 */

public class HttpClientConnection {

    static String urlStr;
    static String token;
    static String channel;
    static String username;

    public HttpClientConnection(String url, String token, String channel, String username) {
        this.urlStr = url;
        this.token = token;
        this.channel = channel;
        this.username = username;
    }

    public static void post(String message) throws MalformedURLException, ProtocolException, IOException{

        URL url = new URL(urlStr);

        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8));
            //writer.write("token="+ token +"&channel=" + channel + "&text="+ message + "&username=" + username);
            writer.write("payload={\"channel\": " + channel + "\", " + username + ": \"" + username + "\", \"text\": \"" + message + "\", \"icon_emoji\": \":ghost:\"}");

            writer.flush();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (InputStreamReader isr = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(isr)) {
                    String line;
                    while((line = reader.readLine()) != null) {
                        System.out.println("[LoginNotifier] " + line);
                    }
                }
            }
            else {

            }
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
