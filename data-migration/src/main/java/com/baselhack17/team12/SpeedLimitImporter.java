package com.baselhack17.team12;

import static org.json.XML.toJSONObject;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

/**
 * //TODO write here something nicer.
 */
public class SpeedLimitImporter {


    public static void main(String[] args) throws ParserConfigurationException {
        HibernateUtils utils = new HibernateUtils();

        ObjectReader listMapper = new ObjectMapper().readerFor(List.class);
        ObjectReader mapMapper = new ObjectMapper().readerFor(Map.class);

        HttpHost openStreetMap = new HttpHost("openstreetmap.org", 80, "http");

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpHost nominatim = new HttpHost("nominatim.openstreetmap.org", 80, "http");

        List<streets> streets = utils.getStreets();
        for (com.baselhack17.team12.streets street : streets) {
            HttpGet getOSMIdByStreetName = new HttpGet("/search/?street=" + street.getStreetName() + "&city=basel&format=json");
            try {
                CloseableHttpResponse execute = httpClient.execute(nominatim, getOSMIdByStreetName);
                InputStream inputStream = execute.getEntity().getContent();
                List<Object> objectList = listMapper.readValue(inputStream);
                Map map = (Map) objectList.get(0);
                String osmId = map.get("osm_id").toString();
                String lat = map.get("lat").toString();
                String lon = map.get("lon").toString();
                String area = map.get("type").toString();

                HttpGet getSpeedLimitByOsmId = new HttpGet("/api/0.6/way/" + osmId);
                CloseableHttpResponse execute1 = httpClient.execute(openStreetMap, getSpeedLimitByOsmId);
                InputStream inputStream1 = execute1.getEntity().getContent();

                JSONObject xmlJSONObj = toJSONObject(IOUtils.toString(inputStream1));
                String jsonPrettyPrintString = xmlJSONObj.toString(4);
                Map xmlMap = mapMapper.readValue(jsonPrettyPrintString);
                Map osm = (Map) xmlMap.get("osm");
                Map way = (Map) osm.get("way");
                List tags = (List) way.get("tag");
                String maxSpeed;
                for (Object tag : tags) {
                    Map tag1 = (Map) tag;
                    Object key = tag1.get("k");
                    if (key.toString().equals("maxspeed")) {
                        maxSpeed = (String) tag1.get("v");
                    }
                }

                // persist
                //:TODO: persist here dude
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
