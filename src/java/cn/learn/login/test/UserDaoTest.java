package cn.learn.login.test;

import cn.learn.login.dao.UserDao;
import cn.learn.login.domain.User;
import org.junit.jupiter.api.Test;

public class UserDaoTest {

    @Test
    public void testLogin(){
        User loginuser = new User();
        loginuser.setUsername("superbaby");
        loginuser.setPassword("123");

        UserDao dao = new UserDao();
        User user = dao.login(loginuser);

        System.out.println(user);
    }
}
