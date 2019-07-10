package Runner;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/TestFeatures/wpPostMedet.feature",
        glue = "StepDefinitions",
       tags = "@UiTest",
       // tags={"@UiTest","@ApiTest"},
        dryRun = false
)
public class runner {
    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "https://www.techtorial.dev.cc/wp-json";
        RestAssured.basePath = "/wp/v2/posts/";
    }

}
