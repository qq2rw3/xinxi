package gongneng;

import jiemian.shujuku;
import jiemian.student;
import jiemian.teacher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class gai {
    //改写学生数据
    public static void gaip(student stu1,student stu2){
        Connection con=null;
        PreparedStatement pre=null;
        ResultSet re=null;
        try{
            shujuku.cxforname();
            con=shujuku.cxgetConnection();
            String sql="update student set banji=? ,sno=?, name=?, sex=?,dianhua=? where banji=? and sno=? and name=? and sex=? and dianhua=?";
            pre=con.prepareStatement(sql);
            pre.setInt(1,stu2.getBanji());pre.setInt(2,stu2.getSno());
            pre.setString(3,stu2.getName());pre.setString(4,stu2.getSex());
            pre.setString(5,stu2.getDianhua());
            pre.setInt(6,stu1.getBanji());pre.setInt(7,stu1.getSno());
            pre.setString(8,stu1.getName());pre.setString(9,stu1.getSex());
            pre.setString(10,stu1.getDianhua());
            pre.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            shujuku.cxclose(con,pre,re);
        }
    }
    //改写教师数据
    public static void gaij(teacher t1, teacher t2){
        Connection con=null;
        PreparedStatement pre=null;
        ResultSet re=null;
        try{
            shujuku.cxforname();
            con=shujuku.cxgetConnection();
            String sql="update teacher set name=?, sex=?, dianhua=?, shouke=? where name=? and sex=? and dianhua=? and shouke=?";
            pre=con.prepareStatement(sql);
            pre.setString(1,t2.getName());pre.setString(2,t2.getSex());
            pre.setString(3,t2.getDianhua());pre.setString(4,t2.getShouke());
            pre.setString(5,t1.getName());pre.setString(6,t1.getSex());
            pre.setString(7,t1.getDianhua());pre.setString(8,t1.getShouke());
            pre.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            shujuku.cxclose(con,pre,re);
        }
    }
}
