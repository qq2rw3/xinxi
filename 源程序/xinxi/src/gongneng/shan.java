package gongneng;

import jiemian.shujuku;
import jiemian.student;
import jiemian.teacher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class shan {
    //删学生的数据
    public static void shanp(student stu){
        Connection con=null;
        PreparedStatement pre=null;
        ResultSet re=null;
        try{
            shujuku.cxforname();
            con=shujuku.cxgetConnection();
            String sql="delete from student where banji=? and sno=? and name=? and sex=? and dianhua=?";
            pre=con.prepareStatement(sql);
            pre.setInt(1,stu.getBanji());pre.setInt(2,stu.getSno());
            pre.setString(3,stu.getName());pre.setString(4,stu.getSex());
            pre.setString(5,stu.getDianhua());
            pre.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //删教师的数据
    public static void shanj(teacher t){
        Connection con=null;
        PreparedStatement pre=null;
        ResultSet re=null;
        try{
            shujuku.cxforname();
            con=shujuku.cxgetConnection();
            String sql="delete from teacher where name=? and sex=? and dianhua=? and shouke=?";
            pre=con.prepareStatement(sql);
            pre.setString(1,t.getName());pre.setString(2,t.getSex());
            pre.setString(3,t.getDianhua());pre.setString(4,t.getShouke());
            pre.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
