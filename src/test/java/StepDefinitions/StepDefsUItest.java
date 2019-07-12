package StepDefinitions;

import Utilities.Driver;
import Utilities.MySQLJDBC;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class StepDefsUItest {
    WebDriver driver = Driver.getDriver();

    private String expectedTitle = "";
    static RequestSpecification request;
    static Response response;

    @Given("^user is authorized to create post$")
    public void user_is_authorized_to_create_post() {
        request = given().relaxedHTTPSValidation().auth().preemptive()
                .basic("admin", "admin").contentType(ContentType.JSON);
    }

    @When("^user create post with title and content and status$")
    public void user_create_post_with_title_and_content_and_status(DataTable dataTable) {
        Map<String, String> reqMap = new HashMap<>();
        reqMap.put("title", "post with java code to post with update with assertion with MYSQLMehmet");
        reqMap.put("content", "posting the contetnt with java code MYSQL");
        reqMap.put("status", "publish");
//        RequestSpecification  request= given().relaxedHTTPSValidation().auth().preemptive().basic("admin", "admin")
//                .contentType(ContentType.JSON);
        response = request.when().body(reqMap).log().all().post();
        response.then().log().all().statusCode(201);


    }

    @Then("^gets (\\d+) status code$")
    public void gets_status_code(int status)  throws SQLException {
        MySQLJDBC.establishConnection();
        String postSQL= MySQLJDBC.runSQLQuery("select post_title, post_content from wp_posts \n" +
                "where post_title='new title with put new body' and post_content='using postman created new post to test DB'\n").toString();
        System.out.println(postSQL);
        driver.get("https://www.medet.dev.cc/wp-login.php?loggedout=true");
        expectedTitle = driver.getTitle();

        driver.findElement(By.id("user_login")).sendKeys("admin");
        driver.findElement(By.id("user_pass")).sendKeys("admin" + Keys.ENTER);
        driver.findElement(By.xpath("//div[.='Posts']")).click();

        List<WebElement> allTitles = driver.findElements(By.xpath("row-title"));
        for(WebElement s :allTitles){
            s.getText();
            Assert.assertTrue(s.getText().contains(postSQL));
        }
       // Assert.assertEquals(expectedTitle,postSQL);



    }

    @Then("^user verifies the post  in UI$")
    public void user_verifies_the_post_in_UI() throws Throwable {
        driver.get("https://www.medet.dev.cc/wp-login.php?loggedout=true");
        driver.findElement(By.id("user_login")).sendKeys("admin");
        driver.findElement(By.id("user_pass")).sendKeys("admin" + Keys.ENTER);
        driver.findElement(By.xpath("//div[.='Posts']")).click();
        driver.findElement(By.xpath("//a[@class='page-title-action']")).click();
        driver.findElement(By.xpath("//textarea[@id='post-title-0']")).sendKeys("Created post via UI by JAVA code");
        //driver.findElement(By.xpath("//div[@class='components-autocomplete']")).sendKeys("created content with java code");
        Actions action=new Actions(driver);
        //action.doubleClick(driver.findElement((By.xpath("//button[@class='components-button editor-post-publish-panel__toggle is-button is-primary']")))).perform();
        driver.findElement((By.xpath("//button[@class='components-button editor-post-publish-panel__toggle is-button is-primary']"))).click();
Thread.sleep(3000);
        driver.findElement((By.xpath("/html//div[@id='editor']/div[@class='components-drop-zone__provider']/div[@class='components-navigate-regions']/div/div[3]/div/div[@role='region']//div[@class='editor-post-publish-panel__header-publish-button']/button[@type='button']"))).click();
Thread.sleep(4000);

    }

}