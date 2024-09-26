## 本项目实现的最终作用是基于SSM公司企业绩效考核管理系统
## 分为1个角色
### 第1个角色为管理员角色，实现了如下功能：
 - 个人信息查看
 - 出勤管理
 - 员工管理
 - 奖励与罚款
 - 工资设置
 - 管理员登录
 - 缴税设置
 - 部门管理
 - 银行工资发放
## 数据库设计如下：
# 数据库设计文档

**数据库名：** ssm_qyjxkhsys

**文档版本：** 


| 表名                  | 说明       |
| :---: | :---: |
| [dept](#dept) |  |
| [dept_item](#dept_item) |  |
| [s_eval](#s_eval) |  |
| [s_evalitem](#s_evalitem) |  |
| [s_hz_config](#s_hz_config) |  |
| [s_item](#s_item) |  |
| [s_notice](#s_notice) |  |
| [s_opinion](#s_opinion) |  |
| [s_user](#s_user) |  |
| [week](#week) | 周报 |

**表名：** <a id="dept">dept</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   varchar   | 255 |   0    |    N     |  Y   |       | 主键ID  |
|  2   | name |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 名字  |

**表名：** <a id="dept_item">dept_item</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   varchar   | 255 |   0    |    N     |  Y   |       | 主键ID  |
|  2   | dept |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  3   | item |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  4   | qz |   double   | 23 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="s_eval">s_eval</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | ID |   varchar   | 255 |   0    |    N     |  Y   |       | 主键  |
|  2   | name |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 名字  |
|  3   | userId |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 用户ID  |
|  4   | month |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 月份  |
|  5   | gmtTime |   datetime   | 19 |   0    |    Y     |  N   |   NULL    |   |
|  6   | total |   double   | 23 |   0    |    Y     |  N   |   NULL    | 总计  |

**表名：** <a id="s_evalitem">s_evalitem</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | ID |   varchar   | 255 |   0    |    N     |  Y   |       | 主键  |
|  2   | itemId |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  3   | score |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  4   | evalId |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="s_hz_config">s_hz_config</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | code |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  2   | value |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 值  |

**表名：** <a id="s_item">s_item</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | ID  |
|  2   | name |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 名字  |
|  3   | project |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  4   | target |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  5   | standard |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  6   | endTime |   datetime   | 19 |   0    |    Y     |  N   |   NULL    | 结束时间  |
|  7   | score |   int   | 10 |   0    |    Y     |  N   |   NULL    | 成绩  |
|  8   | gmtTime |   datetime   | 19 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="s_notice">s_notice</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | ID  |
|  2   | name |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 名字  |
|  3   | description |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 描述  |
|  4   | gmtTime |   datetime   | 19 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="s_opinion">s_opinion</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | ID  |
|  2   | userId |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 用户ID  |
|  3   | content |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 内容  |
|  4   | gmtTime |   datetime   | 19 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="s_user">s_user</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | ID |   varchar   | 255 |   0    |    N     |  Y   |       | 主键  |
|  2   | name |   varchar   | 255 |   0    |    N     |  N   |       | 名字  |
|  3   | account |   varchar   | 255 |   0    |    N     |  N   |       | 账户  |
|  4   | password |   varchar   | 255 |   0    |    N     |  N   |       | 密码  |
|  5   | login_count |   bigint   | 20 |   0    |    Y     |  N   |   NULL    |   |
|  6   | last_login_time |   datetime   | 19 |   0    |    Y     |  N   |   NULL    |   |
|  7   | admin |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  8   | role_id |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 角色ID  |
|  9   | gmtTime |   datetime   | 19 |   0    |    Y     |  N   |   NULL    |   |
|  10   | sex |   int   | 10 |   0    |    Y     |  N   |   NULL    | 性别  |
|  11   | joinTime |   datetime   | 19 |   0    |    Y     |  N   |   NULL    |   |
|  12   | phone |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 手机号码  |
|  13   | ADDRESS |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 地址  |
|  14   | job |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  15   | dept |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="week">week</a>

**说明：** 周报

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | ID  |
|  2   | userId |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 用户ID  |
|  3   | title |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 标题  |
|  4   | content |   varchar   | 2000 |   0    |    Y     |  N   |   NULL    | 内容  |
|  5   | gmtTime |   datetime   | 19 |   0    |    Y     |  N   |   NULL    |   |

