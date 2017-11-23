package serviceTest;


import model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.AircraftService;

@ContextConfiguration({"classpath:/testContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AircraftServiceTest {

    Logger logger = LoggerFactory.getLogger(AircraftServiceTest.class);


    @Autowired
    AircraftService aircraftService;

    /* @Autowired
     private DateFormatter dateFormatter;

     {
         logger.info("preved from test");
         logger.info(user.toString());
         logger.info(dateFormatter.toString());
     }
 */
    @Test
    public void test_true() {

        // Assert.assertThat(aircraftService, instanceOf(AircraftService.class));
        Assert.assertTrue(5 == 5);
    }
}
