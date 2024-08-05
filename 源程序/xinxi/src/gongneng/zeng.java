package gongneng;

import jiemian.shujuku;
import jiemian.student;
import jiemian.teacher;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class zeng {
    //增加学生信息
    public static  void zengp(student stu){
        Connection con=null;
        PreparedStatement pre=null;
        ResultSet re=null;
        try{
            shujuku.cxforname();
            con=shujuku.cxgetConnection();
            String sql="insert into student values(?,?,?,?,?)";
            pre=con.prepareStatement(sql);
            pre.setInt(1,stu.getBanji());pre.setInt(2,stu.getSno());
            pre.setString(3,stu.getName());pre.setString(4,stu.getSex());
            pre.setString(5,stu.getDianhua());
            pre.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //增加教师信息
    public static  void zengj(teacher t){
        Connection con=null;
        PreparedStatement pre=null;
        ResultSet re=null;
        try{
            shujuku.cxforname();
            con=shujuku.cxgetConnection();
            String sql="insert into teacher values(?,?,?,?)";
            pre=con.prepareStatement(sql);
            pre.setString(1,t.getName());pre.setString(2,t.getSex());
            pre.setString(3,t.getDianhua());pre.setString(4,t.getShouke());
            pre.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
