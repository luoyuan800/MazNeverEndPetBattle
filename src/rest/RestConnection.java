package rest;

import json.JSON;
import util.Content;
import util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by luoyuan on 2016/6/18.
 */
public class RestConnection {
    private final static String BASE_URL = "https://api.bmob.cn/1/classes/";
    private final static String APP_KEY = "";
    private final static String API_KEY = "";

    public JSON updateObject(String table, String id, String body){
        try {
            URL url = new URL(BASE_URL + table + "/" + id);
            HttpURLConnection connection = getHttpURLConnection(url);
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);
            connection.getOutputStream().write(body.getBytes());
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = reader.readLine();
            while(line!=null){
                builder.append(line);
                line = reader.readLine();
            }
            reader.close();
            return new JSON(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Content.error("Open connection failed", e);
        }
        return null;
    }

    public JSON addObject(String table, String body){
        try {
            URL url = new URL(BASE_URL + table);
            HttpURLConnection connection = getHttpURLConnection(url);
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.getOutputStream().write(body.getBytes());
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = reader.readLine();
            while(line!=null){
                builder.append(line);
                line = reader.readLine();
            }
            reader.close();
            return new JSON(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Content.error("Open connection failed", e);
        }
        return  null;
    }

    public JSON queryObject(String table, String id) {
        try {
            URL url = new URL(BASE_URL + table + "/" + id);
            HttpURLConnection connection = getHttpURLConnection(url);
            connection.setRequestMethod("GET");
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = reader.readLine();
            StringBuffer sb = new StringBuffer();
            while (line != null) {
                sb.append(line);
                line = reader.readLine();
            }
            JSON json = new JSON(sb.toString());
            reader.close();
            return json;
        } catch (IOException e) {
            e.printStackTrace();
            Content.error("Open connection failed", e);
        }
        return null;
    }

    public JSON queryObjects(String table){
        return queryObjects(table, "updatedAt", 100);
    }

    public JSON queryObjects(String table,String order, Integer limit){
        try {
            URL url = new URL(BASE_URL + table + "?order=" + order + "&limit=" + limit);
            HttpURLConnection connection = getHttpURLConnection(url);
            connection.setRequestMethod("GET");
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = reader.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line);
                line = reader.readLine();
            }
            JSON json = new JSON(sb.toString());
            reader.close();
            return json;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Content.error("Open connection failed", e);
        } catch (IOException e) {
            e.printStackTrace();
            Content.error("Request failed", e);
        }
        return null;
    }

    public JSON queryObjects(String table, int limit){
        return queryObjects(table,"updatedAt", limit);
    }

    public JSON deleteObject(String table, String objectId){
        try {
            URL url = new URL(BASE_URL + table + "/" + objectId);
            HttpURLConnection connection = getHttpURLConnection(url);
            connection.setRequestMethod("DELETE");
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = reader.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line);
                line = reader.readLine();
            }
            JSON json = new JSON(sb.toString());
            reader.close();
            return json;
        } catch (IOException e) {
            e.printStackTrace();
            Content.error("Open connection failed", e);
        }
        return null;
    }

    private HttpURLConnection getHttpURLConnection(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("X-Bmob-Application-Id", APP_KEY);
        connection.setRequestProperty("X-Bmob-REST-API-Key", API_KEY);
        connection.setRequestProperty("Content-Type", "application/json");
        return connection;
    }

    public int getRowCount(String table){
        try {
            URL url = new URL(BASE_URL + table + "?count=1&limit=0");
            HttpURLConnection connection = getHttpURLConnection(url);
            connection.setRequestMethod("GET");
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = reader.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line);
                line = reader.readLine();
            }
            JSON json = new JSON(sb.toString());
            reader.close();
            json.parse();
            return StringUtils.toInt(json.getTokens().get(0).<String>getValue("count"));
        } catch (IOException e) {
            e.printStackTrace();
            Content.error("Open connection failed", e);
        }
        return 0;
    }

}
