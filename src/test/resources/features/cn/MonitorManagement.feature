Feature: iCare screen management
  In order to solve the breakdown of the screen
  As an operation and maintenance
  I need to get all information from the screen list

  @complete
  Scenario: Check the data of the monitor management
    Given Login iCare "iCare-cn" page with username "123456" and password "123456"
    When Go to the navigator "显示屏管理"
    Then The table looks like
      | 序号 | 显示屏                                 | 质保期限                    | 所属客户      | 运行状态 | 屏体监控详情 |
      | 1  | 名称：世纪金花地铁口广告屏注册：2021-01-05 02:30:38 | 2021/01/14 ~ 2021/02/12 | 客户：123456 | 离线   | 查看详情   |
      | 2  | 名称：华城国际吊装屏注册：2020-09-14 09:36:57    | 2021/02/19 ~ 2021/02/20 | 客户：eeeeee | 离线   | 查看详情   |

  @complete
  Scenario: Check the status of the screen
    Given Login iCare "iCare-cn" page with username "123456" and password "123456"
    When Go to the navigator "显示屏管理"
    Then The screen status should be "0正常 0故障 0风险 2离线"

  @complete
  Scenario Outline: Check the information of the PC and device
    Given Login iCare "iCare-cn" page with username "123456" and password "123456"
    When Go to the navigator "显示屏管理"
    And Go to the detail of the "<index>" monitor
    Then The work information should be "<work>"
    And The send card information should be "<card>"
    Examples:
      | index | work                                                                                                                                                               | card                                                                                                                                                                                                                                     |
      | 1     | CPU： 17%Windows(C)： 38.89G(可用)/108.93G新加卷(D)： 196.93G(可用)/231.21G新加卷(E)： 81.26G(可用)/124.37GNovaLCT版本： NovaLCT V5.4.0.T3系统版本： Microsoft Windows 10 家庭中文版 6.2.9200.0 | 型号： MCTRL1600SN： 120B0500C3690000-00MAC： B06EBFC57B12固件版本： V1.0.3.0PCB版本号： V2输入源： DVI网口数： 16设备状态： 正常终端时区： (UTC+08:00) 北京，重庆，香港特别行政区，乌鲁木齐终端时间： 2021-01-22 17:48:28                                                                        |
      | 2     |                                                                                                                                                                    | 型号： SenderSN： MIHKdddKGBKJG-ASMAC： MIHKdddKGBKJGCPU： 11%磁盘(C)： 38.00G(可用)/80.01G磁盘(D)： 163.19G(可用)/200.01G磁盘(E)： 275.69G(可用)/300.01G磁盘(F)： 285.54G(可用)/351.48G系统版本： --软件版本： --FPGA版本号： --PCB版本号： --输入源： --网口数： 2设备状态： 正常终端时区： --终端时间： -- |

  @Complete
  Scenario: Check the images info of running receive cards
    Given Login iCare "iCare-cn" page with username "123456" and password "123456"
    When Go to the navigator "显示屏管理"
    And Go to the detail of the "1" monitor
    And Switch the card tab to "接收卡"
    Then The receive card number should be "88"
    And The status of the receive cards should be "正常冗余无信号"

  @Complete
  Scenario Outline: Check basic information of receive card
    Given Login iCare "iCare-cn" page with username "123456" and password "123456"
    When Go to the navigator "显示屏管理"
    And Go to the detail of the "<index>" monitor
    Then The basic information of receive cards should be "<basic-info>"
    Examples:
      | index | basic-info                                                      |
      | 1     | 接收卡总数量: 88接收卡型号: 17678接收卡MCU: V4.5.3.0，共1个接收卡FPGA: V4.5.3.0，共1个 |
      | 2     | 接收卡总数量: 16接收卡型号: --接收卡MCU: 接收卡FPGA:                             |

  @test
  Scenario Outline: Check the visual link and link number with specific color of receive cards
    Given Login iCare "iCare-cn" page with username "123456" and password "123456"
    When Go to the navigator "显示屏管理"
    And Go to the detail of the "<index>" monitor
    And Switch the visual of receive cards to the radio "卡间连接"
    Then The visual line number should be "<line-num>"
    And The line number with color Green, Yellow and Red should be "<green-yellow-red>"
    Examples:
      | index | line-num | green-yellow-red |
      | 1     | 86       | 44,40,2          |
      | 2     | 15       | 4,0,11           |