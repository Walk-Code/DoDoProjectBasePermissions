# **基础权限组件**
> 使用方式（并未在中央仓库中存放，需要存放在私服）
```java
<dependency>
    <groupId>com.dodo.project.base.permissions</groupId>
    <artifactId>dodo-base-permissions</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```
---

## **主要实现功能**
1. 路由全局主要理由`Aspect`和`Around`来对指定的包中的controller进行扫包，通过AOP
来控制路由的权限。
2. 基于`session`封装权限工具类。
3. 后台项目模块的显示主要基于自定义`jsp tag`来现象组件的显示，使用(伪代码)：
```java
<%@ taglib prefix="auth" uri="http://auth.dodo.com/tags" %>
<auth:hasPermission name="/systemRoles/delRole">
    <a class="btn btn-danger btn-xs" ng-click="del(item.id)">删除</a>
</auth:hasPermission>
```