package com.wty.network.ok;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.ConnectionPool;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.http.HttpHeaders;
import org.springframework.util.CollectionUtils;

/**
 * @author peter
 * @date 2023/1/9 17:26
 */
public class Test {

    private final String GA_VERSION = "1";
    private final String GA_TID = "UA-149950393-1";
    private final String GA_TYPE = "event";
    private static final String GA_SCHEME = "https";
    private static final String GA_HOST = "www.google-analytics.com";
    private final String GA_PATH = "collect";
    private static final String GA4_PATH = "mp/collect";
    private static final String JSON_TYPE = "application/json;charset=utf-8";


    public static void main(String[] args) {
        GoogleAnalytics4AppRequest googleAnalytics4AppRequest = new GoogleAnalytics4AppRequest();
        googleAnalytics4AppRequest.setFirebaseAppId("ADF72EC42FD6442398B21C7AAD7F2798");
        googleAnalytics4AppRequest.setUserId(123456L);
        final Map<String ,Map<String, Object>> objectObjectHashMap = new HashMap<>();
        final Map<String, Object> objectObjectHashMap1 = new HashMap<>();
        objectObjectHashMap1.put("user_id", "1123456");
        objectObjectHashMap.put("lg_event_test_4", objectObjectHashMap1);
        googleAnalytics4AppRequest.setEvents(objectObjectHashMap);
        Ga4EventApiProp ga4EventApiProp = Ga4EventApiProp.builder()
                .webApiSecret("G2l-7kwGTViHMVNSF9r3dA")
                .webMeasurementId("G-1GTGTMWV27")
                .iosApiSecret("ssDO9KpITva8d-_WR4q4JQ")
                .iosFirebaseAppId("1:696088808922:ios:88e42210ec75bad92cc4e3")
                .androidApiSecret("Isq_zUfHR7uK_W7GyuH8bA")
                .androidFirebaseAppId("1:696088808922:android:44f417cd3c04ebaf2cc4e3")
                .build();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(15, TimeUnit.SECONDS);
        builder.connectionPool(new ConnectionPool(20, 5, TimeUnit.MINUTES));
        OkHttpClient okHttpClient =  builder.build();
        final RequestBody requestBody = buildPostAppBody(googleAnalytics4AppRequest);
        final Request httpRequest = new Request.Builder()
                .url(getGa4AndroidUrl(ga4EventApiProp))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.parse(JSON_TYPE).toString())
                .post(requestBody)
                .build();
        try {
            Response response = okHttpClient.newCall(httpRequest).execute();
            if (response.code() == 200 || response.code() == 204) {
            }
        } catch (Exception ex) {
        }
    }

    private static String getGa4AndroidUrl(Ga4EventApiProp ga4EventApiProp ) {
        return GA_SCHEME + "://" + GA_HOST + "/" + GA4_PATH + "?firebase_app_id=" + ga4EventApiProp.getAndroidFirebaseAppId() +"&api_secret=" + ga4EventApiProp.getAndroidApiSecret();
    }

    private static RequestBody buildPostAppBody(GoogleAnalytics4AppRequest googleAnalytics4AppRequest) {
        final HashMap<String, Object> appBody = new HashMap<>();
        appBody.put("app_instance_id", googleAnalytics4AppRequest.getFirebaseAppId());
        putCommonRequest(googleAnalytics4AppRequest, appBody);
        System.out.println(JsonStringConvertor.instance().map2Json(appBody));
        return RequestBody.Companion.create(JsonStringConvertor.instance().map2Json(appBody), MediaType.parse(JSON_TYPE));
    }

    private static void putCommonRequest(GoogleAnalytics4Request request, HashMap<String, Object> body) {
        if (Objects.nonNull(request.getUserId())) {
            body.put("user_id", "" + request.getUserId());
        }
        final ArrayList<Event> events = new ArrayList<>();
        if (!CollectionUtils.isEmpty(request.getEvents())) {
            request.getEvents().forEach((key, value) -> {
                events.add(Event.builder().name(key).params(value).build());
            });
            body.put("events", events);
        }
        if (!CollectionUtils.isEmpty(request.getUserProperties())) {
            final HashMap<String, Object> userProperties = new HashMap<>();
            request.getUserProperties().forEach((key, value) -> {
                userProperties.put(key, Properties.builder().value(value).build());
            });
            body.put("user_properties", userProperties);
        }
    }

    @Data
    @Builder
    public static class Ga4EventApiProp {

        private String webApiSecret;
        private String iosApiSecret;
        private String androidApiSecret;

        private String webMeasurementId;
        private String iosFirebaseAppId;
        private String androidFirebaseAppId;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Event {
        private String name;
        private Map<String, Object> params;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Properties {
        private Object value;
    }
}
