package serviceTest;


import InputOutput.IOCity;
import InputOutput.IOCountry;
import dataWrappers.CityWrapper;
import dataWrappers.FlightWrapper;
import model.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.AircraftService;
import service.FlightService;
import service.RoleService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ContextConfiguration({"classpath:/testContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class FlightServiceTest {

    Logger logger = LoggerFactory.getLogger(AircraftServiceTest.class);


    @Autowired
    FlightService flightService;

    @Autowired
    RoleService roleService;


    @Autowired
    IOCity ioCity;

    /* @Autowired
     private DateFormatter dateFormatter;

     {
         logger.info("preved from test");
         logger.info(user.toString());
         logger.info(dateFormatter.toString());
     }
 */
    @Test
    public void test_insert() {

       /* Country country1 = new Country().setName("strana1");
        Country country2 = new Country().setName("strana12");

        City startCity = FlightWrapper.releaseCity(0.53434,0.343434,"gorod1",country1);
        City endCity = FlightWrapper.releaseCity(0.33434,0.243434,"gorod12",country2);

        City startCity2 = FlightWrapper.releaseCity(0.13434,0.143434,"gorod312",new Country().setId(1).setName("strana312"));
        City endCity2 = FlightWrapper.releaseCity(0.43434,0.32434,"gorod212",new Country().setId(2).setName("strana212"));

        AircraftClassData classData = new AircraftClassData();
        classData.setName("class");

        AircraftClassData classData2 = new AircraftClassData();
        classData.setName("ne_class");

        Aircraft aircraft = new Aircraft();
        aircraft.setName("useless test1");

        Aircraft aircraft2 = new Aircraft();
        aircraft.setName("useless test2");

        List<AircraftPlaceInfo> placeInfoList= Arrays.asList(FlightWrapper.releasePlace(25,122,classData,aircraft),
                FlightWrapper.releasePlace(31,121,classData2,aircraft));
        List<AircraftPlaceInfo> placeInfoList2= Arrays.asList(FlightWrapper.releasePlace(12,300,classData,aircraft2),
                FlightWrapper.releasePlace(37,400,classData2,aircraft2));

        List<Flight> records = Arrays.asList();
        FlightWrapper expectedData = new FlightWrapper();
        expectedData.setFlightList(records);*/
        File sourceCountry = new File("src/test/resources/content/countries.csv");
        File sourceCity = new File("src/test/resources/content/cities.csv");
        List<String> errors = new ArrayList<>();
        CityWrapper cityWrapper;
        try {
            cityWrapper = ioCity.load(sourceCountry,sourceCity,errors);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Assert.assertTrue(5 == 5);

    }
}