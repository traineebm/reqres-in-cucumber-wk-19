package in.reqres.usersinfo;

import in.reqres.testbase.TestBase;
import in.reqres.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasValue;


@RunWith(SerenityRunner.class)
public class UserCRUDTest extends TestBase {

    static String name = "Meena" + TestUtils.getRandomValue();
    static String job = "Api Testing" + TestUtils.getRandomValue();
    static String email = "eve.holt@reqres.in" + TestUtils.getRandomValue();
    static String password = "cityslicka" + TestUtils.getRandomValue();
    static int userId;


    @Steps
    UserSteps userSteps;

    @Title("This will create a new user")
    @Test
    public void test001() {
        ValidatableResponse response = userSteps.createUser(name, job);
        response.log().all().statusCode(201);
        userId = response.log().all().extract().path("id");
        System.out.println(userId);
    }

    @Title("Verify if the user was added to the records list")
    @Test
    public void test002(){
        String first_name = "Meena";
        HashMap<String, Object> userMap = userSteps.getUserInfoByFirstName(first_name);
        Assert.assertThat(userMap, hasValue(first_name)); //Matchers
        userId = (int)userMap.get("id");
        System.out.println(userId);
    }

    @Title("This will login a user")
    @Test
    public void test003() {
        HashMap<String, ?> tokenMap = userSteps.userLogin(email, password);
        Assert.assertThat(tokenMap,hasKey("token"));
        System.out.println(tokenMap);
    }

    @Title("Update the user information and verify the updated information")
    @Test
    public  void test004(){
        name = name + "_updated";
        ValidatableResponse response = userSteps.updateUserWithPut(userId,name,job).log().all().statusCode(200);
    }

    @Title("Delete the student and verify if the student is deleted!")
    @Test
    public void test005(){
         userSteps.deleteUser(userId).log().all().statusCode(204);
    }

}
