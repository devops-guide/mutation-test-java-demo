# mutation-test-java-demo
目前代码库中有4个测试类

1. `unit.DataObjectTest` 纯数据类测试
2. `unit.DataObjectValidationServiceTest` stub 外部依赖场景
3. `unit.DataObjectValidationService_InjectMocks_Test`  场景同2，使用 mockito 注解初始化
4. `unit.ImportServiceTest` 使用 spy verify 函数调用

