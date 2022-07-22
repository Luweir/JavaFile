package DesignPatterns.Factory.proxy.Demo2;

public class UserServiceProxy implements UserService {
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void add() {
        log("add");
        userService.add();
    }

    @Override
    public void delete() {
        log("delete");
        userService.delete();
    }

    @Override
    public void update() {
        log("update");
        userService.update();
    }

    @Override
    public void query() {
        log("query");
        userService.query();
    }

    // 如果需要加 日志方法  没用代理时需要在UserServiceImpl这个业务层中的每个方法加 打印日志；
    // 用了代理后 只需要在代理里面加入一个公共方法即可
    public void log(String msg) {
        System.out.println("使用了" + msg);
    }
}
