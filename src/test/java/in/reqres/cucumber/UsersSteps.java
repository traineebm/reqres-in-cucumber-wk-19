package in.reqres.cucumber;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import in.reqres.usersinfo.UserSteps;
import in.reqres.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasValue;

public class UsersSteps {

    static String name = "Meena" + TestUtils.getRandomValue();
    static String job = "Api Testing" + TestUtils.getRandomValue();
    static int userId;
    static ValidatableResponse response;

    @Steps
    UserSteps userSteps;

    @Given("^I am on homepage of the given url$")
    public void iAmOnHomepageOfTheGivenUrl() {

    }

    @When("^I send POST request using a valid payload to create a new user$")
    public void iSendPOSTRequestUsingAValidPayloadToCreateANewUser() {
        response = userSteps.createUser(name, job);
    }

    @Then("^I get valid status code (\\d+)$")
    public void iGetValidStatusCode(int code) {
        response.log().all().statusCode(code);
    }

    @And("^I verify if the user has been added to the application$")
    public void iVerifyIfTheUserHasBeenAddedToTheApplication() {
        name = "Mona";
        HashMap<String, ?> userMap = userSteps.getUserInfoByFirstName(name);
        Assert.assertThat(userMap, hasValue(userId));
        System.out.println(userId);
    }

    @When("^I send PUT request using a valid payload to update an user information$")
    public void iSendPUTRequestUsingAValidPayloadToUpdateAnUserInformation() {
        name = name + "_updated";
        job = job + "Backend Testing";
        userId = 509;
        response = userSteps.updateUserWithPut(userId,name,job);
    }

    @Then("^I  will get valid status code (\\d+)$")
    public void iWillGetValidStatusCode(int code) {
        response.log().all().statusCode(code);
    }

    @And("^I verify if  the user details has been updated$")
    public void iVerifyIfTheUserDetailsHasBeenUpdated() {
        response.body("name", equalTo(name), "job", equalTo(job));
    }

    @When("^I send Delete request to the the application using userId$")
    public void iSendDeleteRequestToTheTheApplicationUsingUserId() {
        userId = 509;
        response = userSteps.deleteUser(userId);
    }

    @Then("^I will get status code (\\d+)$")
    public void iWillGetStatusCode(int code) {
        response.log().all().statusCode(code);
    }

    @And("^I verify if user details has been deleted from records$")
    public void iVerifyIfUserDetailsHasBeenDeletedFromRecords() {
        response.log().all().statusCode(404);
    }
}
