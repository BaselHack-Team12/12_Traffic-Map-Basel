package com.baselhack17.team12;

import static java.lang.System.exit;
import static java.lang.System.setOut;
import static org.json.XML.toJSONObject;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import com.fasterxml.jackson.core.JsonParser;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
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

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);

        HttpHost openStreetMap = new HttpHost("openstreetmap.org", 80, "http");

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpHost nominatim = new HttpHost("nominatim.openstreetmap.org", 80, "http");

        List<streets> streets = utils.getStreets();
        for (com.baselhack17.team12.streets street : streets) {
            if (street.getSpeedlimit() == 0) {
                HttpGet getOSMIdByStreetName = new HttpGet("/search/?street=" + street.getStreetName() + "&city=basel&format=json");
                try {
                    CloseableHttpResponse execute = httpClient.execute(nominatim, getOSMIdByStreetName);
                    String json = IOUtils.toString(execute.getEntity().getContent());
                    if (StringUtils.isBlank(json)) {
                        continue;
                    }
                    if (json.equals("[]")) {
                        continue;
                    }

                    List<Object> objectList = listMapper.readValue(json);
                    Map map = null;
                    try {
                        map = (Map) objectList.get(0);
                    } catch (Exception e) {
                        System.out.println("here");
                    }
                    String osmId = map.get("osm_id").toString();
                    String lat = map.get("lat").toString();
                    String lon = map.get("lon").toString();
                    String area = map.get("type").toString();

                    HttpGet getSpeedLimitByOsmId = new HttpGet("/api/0.6/way/" + osmId);
                    CloseableHttpResponse execute1 = httpClient.execute(openStreetMap, getSpeedLimitByOsmId);
                    InputStream inputStream1 = execute1.getEntity().getContent();

                    JSONObject xmlJSONObj = toJSONObject(IOUtils.toString(inputStream1));
                    String jsonPrettyPrintString = xmlJSONObj.toString(4);
                    try {
                        if (StringUtils.isBlank(jsonPrettyPrintString)) {
                            continue;
                        }
                        if (StringUtils.equals(jsonPrettyPrintString, "{}")) {
                            continue;
                        }

                        Map xmlMap = mapMapper.readValue(jsonPrettyPrintString);
                        Map osm = (Map) xmlMap.get("osm");
                        Map way = (Map) osm.get("way");
                        if (way.get("tag") instanceof List) {
                            List tags = (List) way.get("tag");
                        } else if (way.get("tag") instanceof Map) {
                            Map tag = (Map) way.get("tag");
                            System.out.println("here");
                            continue;
                        } else {
                            System.out.println("");
                        }

                        String maxSpeed = "-1";
                        for (Object tag : (List) way.get("tag")) {
                            if (tag instanceof Map) {
                                Map tag1 = (Map) tag;
                                Object key = tag1.get("k");
                                if (key.toString().equals("maxspeed")) {
                                    maxSpeed = tag1.get("v").toString();
                                }
                            } else {
                                System.out.println("here");
                            }
                        }

                        utils.updateStreet(street.getId(), maxSpeed, lon, lat, area);
                        System.out.println("Persisting streetId: " + street.getId());

                    } catch (Exception e) {
                        System.out.println("debug session");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Failed for some reason: " + street.getStreetName());
                }
            }

        }
        System.out.println("Done.");
        exit(0);
    }
}
