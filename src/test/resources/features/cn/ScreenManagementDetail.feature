Feature: iCare screen management detail
  In order to direct the problem of the specific screen
  As an operation and maintenance
  I need to check detail information and topology from the screen detail page

  @test
  Scenario Outline: Check the information of the PC and device
    Given Login iCare "iCare-cn" page with username "123456" and password "123456"
    When Go to the navigator "显示屏管理"
    And Go to the detail of the "<index>" monitor
    Then The screen base information should be"<screen_base_info>"
    Examples:
      | index | screen_base_info               |
      | 1     | MSD300 地址：中国陕西西安 分区：西北区        |
      | 2     | 世纪金花地铁口广告屏 地址：西安 分区：新分区        |
      | 3     | 华城国际吊装屏 地址：陕西省,西安市,科技二路 分区：新分区 |

  @test
  Scenario: Check the images info of running receive cards
    Given Login iCare "iCare-cn" page with username "123456" and password "123456"
    When Go to the navigator "显示屏管理"
    And Go to the detail of the "2" monitor
    And Switch the card tab to "接收卡"
    Then The receive card number should be "88"

  @test
  Scenario Outline: Check basic information of receive card
    Given Login iCare "iCare-cn" page with username "123456" and password "123456"
    When Go to the navigator "显示屏管理"
    And Go to the detail of the "<index>" monitor
    And Switch the card tab to "接收卡"
    Then The basic information of receive cards should be "<basic-info>"
    Examples:
      | index | basic-info |
      | 1     | 接收卡总数量: 36 |
      | 2     | 接收卡总数量: 88 |
      | 3     | 接收卡总数量: 16 |

  @test
  Scenario Outline: Check the visual link and link number with specific color of receive cards
    Given Login iCare "iCare-cn" page with username "123456" and password "123456"
    When Go to the navigator "显示屏管理"
    And Go to the detail of the "<index>" monitor
    And Switch the card tab to "接收卡"
    Then The number card status with "状态: 正常" should be "<green-num>"
    And The number card status with "状态: 冗余" should be "<yellow-num>"
    And The number card status with "状态: 无信号" should be "<red-num>"
    Examples:
      | index | green-num | yellow-num | red-num |
      | 1     | 1         | 0          | 35      |
      | 2     | 44        | 41         | 3       |
      | 3     | 4         | 0          | 12      |

  @test
  Scenario Outline: Check the basic screen information in screen detail
    Given Login iCare "iCare-cn" page with username "123456" and password "123456"
    When Go to the navigator "显示屏管理"
    And Go to the detail of the "<index>" monitor
    Then The general screen/send cards/receive cards base information in detail page should be "<send-card-info>"
    Examples:
      | index | send-card-info                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
      | 1     | 型号：MSD300（发送卡）序号：1状态：DVI异常信号源：DVI网口数：2固件版本：V4.7.3.0SN：1906160030011440-00MAC：1C872C420387时区：(UTC+08:00) 北京，重庆，香港特别行政区，乌鲁木齐上报时间：2021-02-26                                                                                                                                                                                                                                                                                                                                               |
      | 2     | 型号：MCTRL1600序号：1状态：DVI异常信号源：DVI网口数：16固件版本：V1.0.3.0SN：120B0500C3690000-00MAC：B06EBFC57B12时区：(UTC+08:00) 北京，重庆，香港特别行政区，乌鲁木齐上报时间：2021-01-22^型号：MCTRL1600序号：2状态：DVI异常信号源：DVI网口数：16固件版本：V1.0.3.0时区：(UTC+08:00) 北京，重庆，香港特别行政区，乌鲁木齐上报时间：2021-01-22^型号：MCTRL1600序号：3状态：DVI异常信号源：DVI网口数：16固件版本：V1.0.3.0SN：120B0500C3690000-00MAC：B06EBFC57B12时区：(UTC+08:00) 北京，重庆，香港特别行政区，乌鲁木齐上报时间：2021-01-22^型号：MCTRL1600序号：4状态：DVI异常信号源：DVI网口数：16固件版本：V1.0.3.0时区：(UTC+08:00) 北京，重庆，香港特别行政区，乌鲁木齐上报时间：2021-01-22 |