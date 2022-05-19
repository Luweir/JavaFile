public class example2 {
    AccessInfo accessInfo = new AccessInfo();
        accessInfo.setBotAppId(0); // 管理端的BotAppId
        accessInfo.setBotToken(""); // 管理端的BotToken
    // 使用沙盒模式
        accessInfo.useSandBoxMode();
    // 创建实例
    BotCore bot = new BotCore(accessInfo);
    // 获取API管理器
    ApiManager api = bot.getApiManager();
    // 注册AT消息相关事件
        bot.registerAtMessageEvent();
    // 设置事件处理器
    IEventHandler handler = new IEventHandler(api);
    // handler.setRemoveAt(false); // 取消删除消息中的艾特
        bot.setEventHandler(handler);
    // 启动
        bot.start();
}
