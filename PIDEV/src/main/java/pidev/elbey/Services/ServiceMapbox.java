package pidev.elbey.Services;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapbox.api.geocoding.v5.MapboxGeocoding;
import com.mapbox.api.geocoding.v5.models.GeocodingResponse;
import jdk.nashorn.internal.codegen.CompilerConstants;
import org.springframework.stereotype.Service;

import javax.security.auth.callback.Callback;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
@Service
public class ServiceMapbox implements IServiceMapbox{

    @Override
    public Map<String , String> CalculeDistance(String Longitude1 , String Latitude1 , String Longitude2 , String Latitude2) throws IOException {
        URL url = new URL("https://api.mapbox.com/directions/v5/mapbox/driving/"+Longitude1+"%2C"+
                Latitude1+"%3B"+Longitude2+"%2C"+
                Latitude2+"?access_token=pk.eyJ1IjoibmEzbmUzYSIsImEiOiJjbGYwaW01MngwOWVjM3FsdmUwNmU3Y2xtIn0.83iR4fC8Cz83-3pEoK4nPg");
        Map<String, String> result = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        Object object = objectMapper.readValue(url, Object.class);
        ObjectMapper mapper = new ObjectMapper();
        String jsonstr = mapper.writeValueAsString(object);
        JsonNode json = mapper.readTree(jsonstr);
        JsonNode routes = json.get("routes");
        JsonNode firstRoute = routes.get(0);
        JsonNode legs = firstRoute.get("legs");
        JsonNode firstLeg = legs.get(0);
        JsonNode distance = firstLeg.get("distance");
        JsonNode duration = firstLeg.get("duration");


        int secs = (duration.asInt())%60;
        int mins = (duration.asInt()/60) %60;
        int heurs = (duration.asInt()/(60*60)) %60;

        double d = distance.asDouble()/1000;
        String Duration = heurs+" hour"+" "+mins+" "+"minutes,"+" "+secs+" "+"Secondes ";
        String Distance = " "+d+" "+"km";
        result.put("Distance" , Distance);
        result.put("Duration" , Duration);
        return result;
    }

}