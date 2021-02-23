Feature: iCare screen management
  In order to solve the breakdown of the screen
  As an operation and maintenance
  I need to get all information from the screen list

  @Complete
  Scenario: Check the data of the monitor management
    Given Login iCare "iCare-cn" page with username "123456" and password "123456"
    When Go to the navigator "显示屏管理"
    Then The table looks like
      | 序号 | 显示屏名称      | 质保期限                    | 运行状态                   | 备份文件 | 注册时间                | 操作 |
      | 1  | 世纪金花地铁口广告屏 | 2021/01/14 ~ 2021/02/12 | 故障：输入源异常 接收卡异常 接收卡网口冗余 | --   | 2021-01-05 02:30:38 | 详情 |
      | 2  | 华城国际吊装屏    | 2021/02/19 ~ 2021/02/20 | 离线                     | --   | 2020-09-14 09:36:57 | 详情 |

  @Complete
  Scenario: Check the status of the screen
    Given Login iCare "iCare-cn" page with username "123456" and password "123456"
    When Go to the navigator "显示屏管理"
    Then The screen status should be "0正常 1故障 0风险 1离线"