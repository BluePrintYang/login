package cn.learn.login.dao;

import cn.learn.login.domain.User;
import cn.learn.login.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 操作数据库中的user表
 */
public class UserDao {

    //声明JDBCTemplate公用
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     *登陆方法
     * @param loginUser
     * @return
     */
    public User login(User loginUser){
        try {
            //1.编写sql
            String sql = "select * from user where username = ? and password = ?";
            //2.调用query方法
            User user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUsername(), loginUser.getPassword());

            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();//记录日志
            return null;
        }

    }
}
