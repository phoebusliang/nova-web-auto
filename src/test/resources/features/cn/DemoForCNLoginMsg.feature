Feature: Test the Login and register for vnnox China


  @complete @regression
  Scenario: check error message when login failed
    Given I open the "nova cloud platform" page "vnnox-jp"
    When Login with username "asdfa" and password "123456"
    Then The Login message should be "账号和密码错误"