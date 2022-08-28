
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class apiTest {

    public static void main(String[] args) {
        getListUser();
        createUser();
    }

    @Test
    public static void getListUser() {
        given().queryParam("page","2").
                when().get("https://reqres.in/api/users")
                .then().log().all()
                .assertThat().statusCode(200);
    }

    public  static void createUser(){
        JSONObject userPayload = new JSONObject();
        userPayload.put("name", "JACK");
        userPayload.put("job", "HR");

        given().header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(userPayload.toJSONString())
                .when().post("https://reqres.in/api/users").
                then().log().status().log().body()
                .assertThat()
                .statusCode(201)
                .body("name", Matchers.equalTo(userPayload.get("name")))
                .body("job", Matchers.equalTo(userPayload.get("job")));
    }

}
