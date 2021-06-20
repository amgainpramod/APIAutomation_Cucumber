Feature: Application Login
  Scenario: Home page default login
    Given User is on NetBanking landing page
    When User login to application with username "<username>" and password "<password>"
    Then Home page is populated
    And Cards are displayed is "true"

  Scenario: Home page default login
    Given User is on NetBanking landing page
    When User login to application with username "PraShi" and password "1234"
    Then Home page is populated
    And Cards are displayed is "false"