Feature: Validating Place APIs
  @AddPlace @Regression
  Scenario Outline: Verify if a place is successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<address>" and "<language>"
    When User calls "addPlaceAPI" with "post" Http request
    Then API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify "place_id" created maps to "<name>" using "getPlaceAPI"
    Examples:
      | name  |          address       | language |
      | Mars  | Mars, Milky Way Galaxy | Martian  |
#      | Earth | Earth, Milky Way Galaxy| English  |

#  mvn test -Dcucumber.options="--tags @AddPlace"   <---for maven test through terminal
  @DeletePlace @Regression
  Scenario: Verify if Delete Place functionality is working
    Given Delete Place Payload
    When User calls "deletePlaceAPI" with "post" Http request
    Then API call is success with status code 200
    And "status" in response body is "OK"

