Feature: iCare screen management
  In order to solve the breakdown of the screen
  As an operation and maintenance
  I need to get all information from the screen list

  @complete
  Scenario: Check the data of the monitor management
    Given I open the "nova iCare" page "iCare-cn"
    When Login with username "123456" and password "123456"
    And Go to the navigator "显示屏管理"
    Then The table looks like
      | 序号 | 显示屏                                        | 质保期限                    | 所属客户      | 运行状态 | 屏体监控详情 |
      | 1  | 名称：COM4-屏1注册：2021-01-05 02:30:38           | 2021/01/14 ~ 2021/02/12 | 客户：123456 | 离线   | 查看详情   |
      | 2  | 名称：MIHKdddKGBKJG-lq1注册：2020-09-14 09:36:57 | 2021/02/19 ~ 2021/02/20 | 客户：eeeeee | 离线   | 查看详情   |

  @complete
  Scenario: Check the status of the screen
    Given I open the "nova iCare" page "iCare-cn"
    When Login with username "123456" and password "123456"
    And Go to the navigator "显示屏管理"
    Then The screen status should be "在线: 0正常 0故障 0风险 离线: 2离线"
