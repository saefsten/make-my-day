package com.mmd.MakeMyDay;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MapsController {

    @GetMapping("/maps")
    public Map maps(RestTemplate restTemplate){
        String directions = restTemplate.getForObject("https://maps.googleapis.com/maps/api/directions/json?origin=Hornsgatan+54+11821+Stockholm&destination=Bondegatan+30+11633+Stockholm&key=AIzaSyDeJB3i-GWRq8X5zGKea6mLtFnthe8uc2M", String.class);
        Map<String, Object> map = new Gson()
                .fromJson(directions, new TypeToken<HashMap<String, Object>>() {
                }.getType());

        List<Map> routes = getNestedValue(map, "routes");
        Map map1 = routes.get(0);
        List<Map> legs = getNestedValue(map1, "legs");
        Map map2 = legs.get(0);
        Map<String, String> distance = (Map)map2.get("distance");
        Map<String, String> duration = (Map)map2.get("duration");
        Map<String, Double> end_location = (Map)map2.get("end_location");
        Map<String, Double> start_location = (Map)map2.get("start_location");
        Map<String, String> results = new HashMap<>();
        results.put("duration", duration.get("text"));
        results.put("distance", distance.get("text"));
        results.put("latitude_start", start_location.get("lat").toString());
        results.put("longitude_start", start_location.get("lng").toString());
        results.put("latitude_end", end_location.get("lat").toString());
        results.put("longitude_end", end_location.get("lng").toString());
        return results;
    }


    @GetMapping("/location")
    public Map location(RestTemplate restTemplate) {
        String loc = restTemplate.getForObject("https://maps.googleapis.com/maps/api/geocode/json?address=Hornsgatan+54+11821+Stockholm&key=AIzaSyDeJB3i-GWRq8X5zGKea6mLtFnthe8uc2M", String.class);
        Map<String, Object> map = new Gson()
                .fromJson(loc, new TypeToken<HashMap<String, Object>>() {
                }.getType());
        List<Map> results = getNestedValue(map,"results");
        Map map1 = results.get(0);
        Map<String, Object> geometry = getNestedValue(map1, "geometry");
        Map<String, Double> coordinates = (Map)geometry.get("location");
        return coordinates;
    }


    public static <T> T getNestedValue(Map map, String... keys) {
        Object value = map;

        for (String key : keys) {
            value = ((Map) value).get(key);
        }

        return (T) value;
    }

//    @PostMapping("/maps")
//    String maps1(Model model, @RequestParam String street1, String nr1, String postal1, String city1, String street2, String nr2, String postal2, String city2 ){
//        String str = "https://maps.googleapis.com/maps/api/directions/json?origin=";
//        str+= street1+'+'+nr1+'+'+postal1+'+'+city1+'&';
//        str+= "destination="+street2+'+'+nr2+'+'+postal2+'+'+city2;
//        str+= "&key=AIzaSyAEBZaeUpV59xV1vHJhmT8UIKnw8S1GO50";
//        model.addAttribute("url", str);
//
//        return "mapsResult";
//    }

    // https://maps.googleapis.com/maps/api/directions/json?origin=Disneyland&destination=Universal+Studios+Hollywood&key=AIzaSyAEBZaeUpV59xV1vHJhmT8UIKnw8S1GO50
}
