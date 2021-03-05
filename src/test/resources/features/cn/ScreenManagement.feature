Feature: iCare screen management
  In order to solve the breakdown of the screen
  As an operation and maintenance
  I need to get all information from the screen list

  @Complete
  Scenario: Check the data of the monitor management
    Given Login iCare "iCare-cn" page with username "123456" and password "123456"
    When Go to the navigator "显示屏管理"
    Then The table looks like
      | 序号 | 显示屏名称      | 运行状态 | 备份文件 | 质保期限                    | 注册时间                |
      | 1  | MSD300     | 故障   | --   | --                      | 2021-02-25 11:45:29 |
      | 1  | 世纪金花地铁口广告屏 | 故障   | --   | 2021/01/14 ~ 2021/02/12 | 2021-01-05 10:30:38 |
      | 2  | 世纪金花地铁口广告屏 | 离线   | --   | 2021/02/19 ~ 2021/02/20 | 2020-09-14 17:36:57 |

  @test
  Scenario: Check the status of the screen
    Given Login iCare "iCare-cn" page with username "123456" and password "123456"
    When Go to the navigator "显示屏管理"
    Then The screen status should be "3全部 0正常 2故障 0风险 1离线"

  @test
  Scenario: Check searching function with specific screens
    Given Login iCare "iCare-cn" page with username "123456" and password "123456"
    When Go to the navigator "显示屏管理"
    And Search screen with specific name "msd"
    Then The record number of searching result should be "1"
