package cn.learn.login.web;

import cn.learn.login.dao.UserDao;
import cn.learn.login.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/LoginServlet")
public class LoginServlet extends javax.servlet.http.HttpServlet {

   @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        this.doGet(request,response);
    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        /*//2.获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //3.封装user对象
        User loginUser = new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);*/

        //2.获取所有请求参数
        Map<String, String[]> map = request.getParameterMap();
        //3.创建user对象
        User loginUser = new User();
        //3.2使用Beanutils封装
        try {
            BeanUtils.populate(loginUser,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //4.调用UserDao的login方法
        UserDao dao = new UserDao();
        User user = dao.login(loginUser);

        //5.判断user
        if (user == null){
            //登录失败
            request.getRequestDispatcher("/FailServlet").forward(request,response);
        }else {
            //登陆成功
            //存储数据
            request.setAttribute("user",user);
            //转发
            request.getRequestDispatcher("/SuccessServlet").forward(request,response);
        }











    }
}
