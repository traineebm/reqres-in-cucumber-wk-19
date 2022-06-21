@SANITY
Feature: As a user I would like to check the CRUD operations on this application

  Scenario: I want to create a new user
    Given I am on homepage of the given url
    When  I send POST request using a valid payload to create a new user
    Then  I get valid status code 201
    And   I verify if the user has been added to the application

  Scenario: I want to update details for newly created user
    When  I send PUT request using a valid payload to update an user information
    Then  I  will get valid status code 200
    And   I verify if  the user details has been updated

  Scenario: I want to delete  details for newly created user
    When I send Delete request to the the application using userId
    Then I will get status code 204
    And  I verify if user details has been deleted from records