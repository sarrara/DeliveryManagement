package pidev.elbey.RestControllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pidev.elbey.Services.IServiceMapbox;

import java.io.IOException;
import java.util.Map;
@RestController
@AllArgsConstructor
public class MapBoxController {
    private IServiceMapbox iServiceMapBox;

    @GetMapping("/MapBox/{Longitude1}/{Latitude1}/{Longitude2}/{Latitude2}")
    public Map<String , String> getInfo(@PathVariable String Longitude1 ,@PathVariable String Latitude1 ,@PathVariable String Longitude2 , @PathVariable String Latitude2) throws  IOException {
        return iServiceMapBox.CalculeDistance(Longitude1,Latitude1,Longitude2,Latitude2);
    }

}
