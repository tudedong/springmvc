**@Security注解之权限控制**

数据准备：3个url和4个用户

注解添加：

在controller上添加@Security("admin")

在handler01方法上添加@Security(value = {"aaa"})，在handler02方法上添加@Security(value = {"bbb","ccc"})

权限控制规则：

若在handler方法上没有添加@Security注解，则权限控制controller上添加的@Security完成；

若在handler方法上添加@Security注解，则权限控制有handler方法上的@Security来完成。

```java
测试权限验证：
【1】uri：/demo/handle00
    只在controller上添加@Security("admin")
http://localhost:8080/demo/handle00?username=admin (可访问)
http://localhost:8080/demo/handle00?username=aaa (不可访问)
http://localhost:8080/demo/handle00?username=bbb (不可访问)
http://localhost:8080/demo/handle00?username=ccc (不可访问)

【2】uri：/demo/handle01
    handler01方法上添加@Security(value = {"aaa"})
http://localhost:8080/demo/handle01?username=admin (不可访问)
http://localhost:8080/demo/handle01?username=aaa  (可访问)
http://localhost:8080/demo/handle01?username=bbb (不可访问)
http://localhost:8080/demo/handle01?username=ccc (不可访问)

【3】uri：/demo/handle02
    handler02方法上添加@Security(value = {"bbb","ccc"})
http://localhost:8080/demo/handle02?username=admin  (不可访问)
http://localhost:8080/demo/handle02?username=aaa (不可访问)
http://localhost:8080/demo/handle02?username=bbb  (可访问)
http://localhost:8080/demo/handle02?username=ccc  (可访问)
```

