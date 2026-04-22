# 前端对接文档

（服务端地址待定）

---

## 认证机制

#### 1. 微信登录流程

```
1. 小程序调用 wx.login() 获取 code
2. 将 code 发送到后端 /api/user/wxLogin
3. 后端返回 userId 和 token
4. 前端存储 token（wx.setStorageSync('token', token)）
5. 后续请求在 header 中携带 token
```

#### 2. Token 使用方式

需要认证的接口，在请求头中添加：

```javascript
header: {
  'Authorization': 'Bearer ' + token
}
```

token 中已经包含了用户身份信息，后端会自动解析，**无需额外传 userId**。

#### 3. 接口认证情况

**需要 Token 的接口**：
- POST `/api/item` - 发布商品
- GET `/api/item/my` - 我的发布
- PUT `/api/item/{id}/status` - 更新商品状态
- POST `/api/order` - 创建订单
- GET `/api/order/my` - 我的订单
- PUT `/api/order/{id}/status` - 更新订单状态
- GET `/api/im/getUserSig` - 获取 IM 签名

**无需 Token 的接口**：
- POST `/api/user/wxLogin` - 微信登录
- GET `/api/item/list` - 商品列表
- GET `/api/item/{id}` - 商品详情

---

## 请求格式

除认证相关内容放在 header 中外，其余请求参数全部放在 data 段中：

```json
data: {
  ...
}
```

---

## 返回格式

所有接口统一返回以下格式：

```json
{
  "code": 200,
  "message": "success",
  "data": { ... }
}
```

**状态码说明**：
- `200` - 成功
- `400` - 请求参数错误
- `401` - 未认证（token 无效或过期）
- `403` - 无权限
- `404` - 资源不存在
- `500` - 服务器错误

---

## 接口列表

使用 RESTful API
下文中：

- 请求参数：指放在请求内容 JSON 里的参数
- 路径参数：指放在请求 URL 中的参数

## 用户模块

#### 1.1 微信登录

**接口地址**：`POST /api/user/wxLogin`
**是否需要认证**：否

**请求参数**：
```json
{
  "code": "微信登录返回的code"
}
```

**返回示例**：
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "userId": 1,
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
  }
}
```

---

## 商品模块

#### 2.1 发布商品

**接口地址**：`POST /api/item`
**是否需要认证**：是

**请求参数**：
```json
{
  "title": "商品标题",
  "description": "商品描述",
  "price": 99.99,
  "imageUrl": "图片URL"
}
```

**字段说明**：
- `title`：必填，商品标题
- `description`：选填，商品描述
- `price`：必填，价格（数字类型）
- `imageUrl`：选填，图片URL

**注意**：`sellerId`（卖家ID）由后端从 token 中获取，前端不需要传

**返回示例**：
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "itemId": 123
  }
}
```

---

#### 2.2 商品列表（分页）

**接口地址**：`GET /api/item/list?page={}&size={}`
**是否需要认证**：否

**路径参数**：
- `page`：页码，默认 1
- `size`：每页数量，默认 10

**返回示例**：
```json
{
  "code": 200,
  "message": "success",
  "data": [
    {
      "id": 1,
      "title": "二手自行车",
      "description": "9成新",
      "price": 200.00,
      "imageUrl": "https://...",
      "sellerId": 10,
      "status": 1,
      "createTime": "2026-04-22T10:30:00",
      "updateTime": "2026-04-22T10:30:00"
    }
  ]
}
```


---

#### 2.3 商品详情

**接口地址**：`GET /api/item/{id}`
**是否需要认证**：否

**路径参数**：
- `id`：商品ID

**返回示例**：
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "id": 1,
    "title": "二手自行车",
    "description": "9成新，骑行流畅",
    "price": 200.00,
    "imageUrl": "https://...",
    "sellerId": 10,
    "status": 1,
    "createTime": "2026-04-22T10:30:00",
    "updateTime": "2026-04-22T10:30:00"
  }
}
```

---

#### 2.4 我的发布

**接口地址**：`GET /api/item/my`
**是否需要认证**：是

**注意**：`sellerId` 由后端从 token 中获取，前端不需要传

**返回示例**：
```json
{
  "code": 200,
  "message": "success",
  "data": [
    {
      "id": 1,
      "title": "二手自行车",
      "description": "9成新",
      "price": 200.00,
      "imageUrl": "https://...",
      "sellerId": 10,
      "status": 1,
      "createTime": "2026-04-22T10:30:00",
      "updateTime": "2026-04-22T10:30:00"
    }
  ]
}
```

---

#### 2.5 更新商品状态

**接口地址**：`PUT /api/item/{id}/status`
**是否需要认证**：是

**路径参数**：
- `id`：商品ID

**请求参数**：
```json
{
  "status": 1
}
```

**商品状态说明**：
- `1` - 在售
- `2` - 已售出
- `3` - 已下架

**注意**：只有商品发布者本人可以更新状态

**返回示例**：
```json
{
  "code": 200,
  "message": "success",
  "data": null
}
```

---

## 订单模块

#### 3.1 创建订单

**接口地址**：`POST /api/order`
**是否需要认证**：是

**请求参数**：
```json
{
  "itemId": 123
}
```

**注意**：`buyerId`（买家ID）由后端从 token 中获取，前端不需要传

**返回示例**：
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "orderId": 456
  }
}
```

---

#### 3.2 我的订单

**接口地址**：`GET /api/order/my`
**是否需要认证**：是

**注意**：后端会返回当前用户作为买家或卖家的所有订单

**返回示例**：
```json
{
  "code": 200,
  "message": "success",
  "data": [
    {
      "id": 1,
      "itemId": 123,
      "buyerId": 10,
      "sellerId": 20,
      "status": 1,
      "createTime": "2026-04-22T10:30:00",
      "updateTime": "2026-04-22T10:30:00"
    }
  ]
}
```

---

#### 3.3 更新订单状态

**接口地址**：`PUT /api/order/{id}/status`
**是否需要认证**：是

**路径参数**：
- `id`：订单ID

**请求参数**：
```json
{
  "status": 2
}
```

**订单状态说明**：
- `1` - 待确认
- `2` - 已确认
- `3` - 已完成
- `4` - 已取消

**注意**：只有订单的买家或卖家可以更新状态

**返回示例**：
```json
{
  "code": 200,
  "message": "success",
  "data": null
}
```

---

## 聊天模块

计划使用 **腾讯云IM（TIMSDK）** 实现聊天功能。

后端只需提供 UserSig 生成接口。

前端集成可以参考官方文档：[微信小程序集成指南](https://cloud.tencent.com/document/product/269/37411)

#### 4.1 获取 IM 签名

**接口地址**：`GET /api/im/getUserSig`
**是否需要认证**：是

**参数**：无

**返回示例**：
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "userId": "123",
    "userSig": "eJw1jdEKgjAYhV8l7Lq0*bfNXYWQRZJQXnQXhf6ZoTZtToTefRtdHc75-M45..."
  }
}
```

**字段说明**：
- `userId`：用户ID（字符串格式）
- `userSig`：腾讯云 IM 签名，有效期 180 天


---

## 约定

#### 安全性

1. **敏感参数从 token 获取**
   `sellerId`、`buyerId` 等敏感参数由后端从 JWT token 中解析，前端不要传递

2. **Token 存储**
   使用 `wx.setStorageSync('token', token)` 存储 token

3. **Token 过期处理**
   当接口返回 401 时，需要重新登录

#### 数据格式

1. **价格字段**
   `price` 字段为数字类型，不是字符串

2. **时间格式**
   后端返回的时间格式为 ISO 8601（如 `2026-04-22T10:30:00`）

3. **ID 字段**
   所有 ID 字段均为数字类型

---
