Feature: Test the Login and register for vnnox China


  @complete @regression
  Scenario: Login failed with incorrect password
    Given I open the "nova cloud platform" page "vnnox-cn"
    When Login with username "447673603@qq.com" and password "123456"

