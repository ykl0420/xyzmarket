# 校易助（xyz）- 校园二手交易平台（微信小程序版）

## 项目性质
这是一个**软件工程课小组作业项目**，用于教学目的。团队成员均为初学者，需要边做边学。

## 技术栈
- **后端**：Java 17 + Spring Boot 3.x + MyBatis（注解方式） + MySQL 8.x
- **前端**：微信小程序
- **构建工具**：Maven
- **开发工具**：IntelliJ IDEA
- **认证方式**：微信登录 + JWT
- **IM方案**：腾讯云IM（TIMSDK）

**重要**：不使用 MyBatis-Plus，使用原生 MyBatis 注解方式编写 SQL，让用户学习 SQL 基础。

## 核心功能
1. 微信登录（openid 体系）
2. 商品发布
3. 商品列表浏览（分页）
4. 商品详情查看
5. 我的发布管理
6. 商品状态更新
7. 订单创建（无支付）
8. 我的订单查询
9. 订单状态更新

## 明确不做的功能
- ❌ 支付系统
- ❌ 后端聊天系统（使用第三方IM）
- ❌ 复杂推荐算法
- ❌ 账号密码登录

## 项目结构
```
com.example.xyzmarket
├── controller        // 接口层
│   ├── UserController.java      // 微信登录
│   ├── ItemController.java      // 商品管理
│   └── OrderController.java     // 订单管理（新增）
├── service          // 业务层（接口 + 实现）
│   ├── UserService.java
│   ├── ItemService.java
│   ├── OrderService.java        // 新增
│   └── impl/
├── mapper           // 数据访问层（MyBatis 注解）
│   ├── UserMapper.java
│   ├── ItemMapper.java
│   └── OrderMapper.java         // 新增
├── entity           // 实体类
│   ├── User.java               // openid/nickname/avatarUrl
│   ├── Item.java               // sellerId
│   └── Order.java              // 新增
├── dto              // 请求参数封装
│   ├── WxLoginDTO.java         // 仅含 code
│   ├── ItemDTO.java            // 不含 sellerId（从 token 获取）
│   └── OrderDTO.java           // 仅含 itemId（buyerId 从 token 获取）
├── vo               // 返回对象
├── config           // 配置类
│   ├── MyBatisConfig.java      // MyBatis 配置
│   ├── WxConfig.java           // 微信配置
│   └── SecurityConfig.java     // JWT 拦截器配置
├── interceptor      // 拦截器
│   └── JwtInterceptor.java     // JWT 认证拦截器
├── util             // 工具类
│   ├── HttpClientUtil.java     // 调用微信 API
│   └── JwtUtil.java            // JWT 生成/解析
├── common           // 通用类（Result, ErrorCode）
└── exception        // 异常处理
```

## 数据库设计
### user 表
- id, openid（唯一）, nickname, avatar_url, phone, create_time, update_time

### item 表
- id, title, description, price, image_url, seller_id, status, create_time, update_time

### orders 表
- id, item_id, buyer_id, seller_id, status, create_time, update_time

## 接口认证规则
### 需要 JWT 认证的接口
- POST `/api/item` - 发布商品
- GET `/api/item/my` - 我的发布
- PUT `/api/item/{id}/status` - 更新商品状态
- POST `/api/order` - 创建订单
- GET `/api/order/my` - 我的订单
- PUT `/api/order/{id}/status` - 更新订单状态

### 无需认证的接口
- POST `/api/user/wxLogin` - 微信登录
- GET `/api/item/list` - 商品列表
- GET `/api/item/{id}` - 商品详情

## 开发规范
- 统一使用 `/api` 前缀
- RESTful 风格接口
- 统一返回格式 Result<T>
- 敏感参数（userId/buyerId/sellerId）从 JWT token 获取，不由前端传入
- 使用 @Valid 进行参数校验
- 使用 MyBatis 注解编写 SQL（@Select、@Insert、@Update、@Delete）

## 教学重点
用户需要通过这个项目学习：
1. **SQL 基础**（SELECT、INSERT、UPDATE、DELETE、WHERE、ORDER BY、LIMIT）
2. **MyBatis 注解**（@Select、@Insert、@Update、@Delete、@Options、@Param）
3. Spring Boot 基础框架搭建
4. RESTful API 设计
5. 分层架构（Controller-Service-Mapper-Database）
6. 数据库设计与操作
7. JWT 认证机制
8. 微信小程序登录流程

## 实现方式
- **Mapper 层**：使用 MyBatis 注解编写 SQL（需要用户自己实现）
- **Service 层**：业务逻辑（需要用户自己实现）
- **Controller 层**：接口暴露（需要用户自己实现）
- 其他基础设施代码已提供（实体类、配置类、通用类、工具类等）

## Agent 协作方式
- 提供骨架代码，Mapper/Service/Controller 的关键逻辑留空让用户实现
- **重点引导用户学习 SQL 和 MyBatis 注解**
- 引导用户理解每一层的职责
- 解释代码的作用和原理
- 帮助用户调试问题
- 提供 SQL 示例和 MyBatis 注解使用方法
- 强调安全性：敏感参数必须从 token 获取
