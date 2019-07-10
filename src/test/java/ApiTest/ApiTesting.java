package ApiTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ApiTesting {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://www.techtorial.dev.cc/wp-json";
        RestAssured.basePath = "/wp/v2/posts";
    }
    @Test
    public void getRequest() {
        given().relaxedHTTPSValidation().
                get("https://www.techtorial.dev.cc/wp-json/wp/v2/posts").
                then().log().all().
                statusCode(200);
    }
    @Test
    public void PostRequest() {
        Map<String, String> reqMap = new HashMap<>();
        reqMap.put("title", "post with java code");
        reqMap.put("content", "posting the contetnt with java code");
        reqMap.put("status", "publish");

        given().relaxedHTTPSValidation().auth().preemptive().basic("admin", "admin")
                .contentType(ContentType.JSON).when().body(reqMap).log().all().post().
                then().log().all().statusCode(201);
    }
    @Test
    public void PutRequest() {
        Map<String, String> reqMap = new HashMap<>();
        reqMap.put("status", "publish");

      RequestSpecification request = given().relaxedHTTPSValidation().auth().preemptive().basic("admin", "admin").contentType(ContentType.JSON);

      Response response= request.when().body(reqMap).log().all().put();

      response.then().log().all().statusCode(201);
    }
    @Test
    public void test1() {
        given().relaxedHTTPSValidation().
                get("https://www.techtorial.dev.cc/wp-json/wp/v2/posts").
                then().log().all().
                statusCode(200)
                .and()
                .body("id", hasItems(18, 14, 13, 6));

    }
    @Test
    public void test2() {
        given().relaxedHTTPSValidation().
                get("https://www.techtorial.dev.cc/wp-json/wp/v2/posts").
                then().log().all().
                statusCode(200)
                .and()
                .body("[0].id", equalTo(6));

    }
    @Test
    public void test3() {
        assertThat(5, equalTo(5));
        assertThat(5, not(equalTo(4)));
        List<Integer> numbers = Arrays.asList(4, 5, 6, 10, 12);
        assertThat(numbers, hasItems(10));
        //assertThat(numbers,contains(4,5,10));
        assertThat("Techtorial Academy", containsString("Academy"));

    }
    @Test
    public void test4() {
        given().relaxedHTTPSValidation().
                get("https://www.techtorial.dev.cc/wp-json/wp/v2/posts/14").
                then().log().all().
                statusCode(200)
                .and()
                .body("id", equalTo(14));
    }

    @Test
    public void test5() {
        given().relaxedHTTPSValidation().
                get("https://www.techtorial.dev.cc/wp-json/wp/v2/posts/{id}", 14).
                then().log().all().
                statusCode(200)
                .and()
                .body("id", equalTo(14))
                .body("date", equalTo("2019-07-06T02:24:44"))
                .body("date_gmt", equalTo("2019-07-06T02:24:44"))
                .body("guid.rendered", equalTo("https://www.medet.dev.cc/?p=14"))
                .body("modified", equalTo("2019-07-06T16:20:02"))
                .body("modified_gmt", equalTo("2019-07-06T16:20:02"))
                .body("slug", equalTo("jggjkh"))
                .body("status", equalTo("publish"))
                .body("type", equalTo("post"))
                .body("link", equalTo("https://www.medet.dev.cc/2019/07/06/jggjkh/"))
                .body("title.rendered", equalTo("jggjkh"))
                .body("content.rendered", equalTo("\n<p>kjkh</p>\n"))
                .body("content.protected", equalTo(false));
    }

    @Test
    public void test6() {
        given().relaxedHTTPSValidation().pathParam("id", "14").log().all().
                get("https://www.techtorial.dev.cc/wp-json/wp/v2/posts").
                then().log().all().
                statusCode(200)
                .and()
                .body("id", equalTo(14));
    }

    @Test
    public void test7() {
        given().relaxedHTTPSValidation().
                get("/posts").
                then().log().all().
                statusCode(200);
    }


}
