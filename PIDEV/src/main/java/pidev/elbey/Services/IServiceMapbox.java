package pidev.elbey.Services;

import java.io.IOException;
import java.util.Map;



    public interface IServiceMapbox {

        public Map<String , String> CalculeDistance(String Longitude1 , String Latitude1 , String Longitude2 , String Latitude2) throws IOException;
    }



