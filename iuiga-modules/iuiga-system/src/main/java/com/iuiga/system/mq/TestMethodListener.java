package com.iuiga.system.mq;

//@Component
//public class TestMethodListener {
//    // 绑定现有队列
//    @RabbitListener(queues = {"test"})
//    public void testQueue(String message) {
//        System.out.println("Queue: test, message: " + JSON.toJSONString(message));
//    }
//
//    // 使用bindings监听的交换机或队列如果不存在会自动创建，如果存在则会报异常
//    @RabbitListener(bindings = @QueueBinding(
//            exchange = @Exchange(value = "testExchange", durable = "true", type = "topic"),
//            value = @Queue(value = "testExchangeQueue", durable = "true"),
//            key = "testKey123"
//    ))
//    public void testExchange(String message) {
//        System.out.println("Exchange: testExchange, Queue: testExchangeQueue, message: " + JSON.toJSONString(message));
//    }
//}
