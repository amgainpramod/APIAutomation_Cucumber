package testresources;

import pojoclasses.AddPlace;
import pojoclasses.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace addPlacePayload(String name, String address, String language){
        //Creating object of AddPlace class to set the parameters in the body

        AddPlace addPlace = new AddPlace();
        addPlace.setAccuracy(50);
        addPlace.setName(name);
        addPlace.setPhone_number("(+91) 983 893 3937");
        addPlace.setAddress(address);
        addPlace.setWebsite("http://google.com");
        addPlace.setLanguage(language);

        //Adding type lists
        List<String> myList = new ArrayList<>();
        myList.add("shoe park");
        myList.add("shop");
        addPlace.setTypes(myList);

        //adding location object with lat and lng
        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        addPlace.setLocation(location);

        return addPlace;
    }

    public String deletePlacePayload(String place_id){
        return "{\n" +
                "\"place_id\": \""+place_id+"\"\n" +
                "}";
    }
}
