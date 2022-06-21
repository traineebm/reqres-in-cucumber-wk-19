package in.reqres.usersinfo;

import in.reqres.constants.EndPoints;
import in.reqres.model.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class UserSteps {

        @Step("Creating user with name : {0}, job: {1}")
        public ValidatableResponse createUser(String name, String job){

            UserPojo userPojo = new UserPojo();
            userPojo.setName(name);
            userPojo.setJob(job);

            return SerenityRest.given().log().all()
                    .contentType(ContentType.JSON)
                    .body(userPojo)
                    .when()
                    .post(EndPoints.GET_ALL_USERS)
                    .then();
        }

        @Step("Getting the user information with firstName: {0}")
        public HashMap<String, Object> getUserInfoByFirstName(String first_name){
            String p1 = "data.findAll{it.first_name =='";
            String p2 = "'}.get(0)";

            return SerenityRest.given().log().all()
                    .when()
                    .get(EndPoints.GET_ALL_USERS)
                    .then()
                    .statusCode(200)
                    .extract()
                    .path("p1 + first_name + p2");
        }

        @Step("Login User with email : {0}, password: {1}")
        public HashMap<String, Object> userLogin(String email, String pwd){
            UserPojo userPojo = new UserPojo();
            userPojo.setEmail(email);
            userPojo.setPassword(pwd);

            return SerenityRest.given().log().all()
                    .contentType(ContentType.JSON)
                    .body(userPojo)
                    .when()
                    .post(EndPoints.USER_LOGIN)
                    .then()
                    .statusCode(200)
                    .extract().path("");
        }

        @Step("Updating user records with userID: {0}, name: {1}, job: {2}")
        public ValidatableResponse updateUserWithPut(int userId, String name, String job){

            UserPojo userPojo = new UserPojo();
            userPojo.setName(name);
            userPojo.setJob(job);

            return SerenityRest.given().log().all()
                    .header("Content-Type", "application/json")
                    .pathParam("userID", userId)
                    .body(userPojo)
                    .when()
                    .put(EndPoints.UPDATE_USER_BY_ID)
                    .then();
        }

        @Step("Deleting user record with UserId: {0}")
        public ValidatableResponse deleteUser(int userId){
            return SerenityRest.given().log().all()
                    .pathParam("userID", userId)
                    .when()
                    .delete(EndPoints.DELETE_USER_BY_ID)
                    .then();
        }

    }
