# 令牌桶限流
## 描述
以一定的速率向令牌桶中增加令牌，请求端从令牌桶中获取令牌；
* 当令牌桶内有足够令牌时，则获取令牌正常请求
* 当令牌桶内无足够令牌时，则限制请求进入
* 当令牌桶内令牌达到最大的限流数，则停止生产令牌
## 业务场景
运营推广部门某次策划上线秒杀或者优惠活动，经测试人员估算压力测试，大约在一个小时内进来100万+用户访问，系统吞吐量固定的情况下，为保障Java服务端正常运行不崩溃，需要对正常访问用户进行限流处理，大约每秒响应1000个请求。
请问限流的系统如何设计，给出具体的实现？
## redis实现
* 核心思路：采用list数据结构，模拟生产消费模型，右进左出
* 实现细节：定时任务每毫秒生成一个令牌，1秒内会生成1000个令牌，支持1000个/s个请求，可根据实际业务调整
