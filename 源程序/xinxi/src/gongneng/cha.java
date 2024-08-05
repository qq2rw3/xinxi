package gongneng;

import jiemian.shujuku;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class cha {
    //查询全体学生信息
    public static void p(DefaultTableModel de){
        Connection con=null;
        PreparedStatement pre=null;
        ResultSet re=null;
        try{
            shujuku.cxforname();
            con=shujuku.cxgetConnection();
            String s="select * from student ";
            pre=con.prepareStatement(s);
            re=pre.executeQuery();
            int sum=0;
            while(re.next()){
                sum++;
            }
            re=pre.executeQuery();
            Object a[]=new Object[5];
            sum=0;
            while(re.next()){
                a[0]=Integer.valueOf(re.getInt(1));
                a[1]=Integer.valueOf(re.getInt(2));
                a[2]=re.getString(3);
                a[3]=re.getString(4);
                a[4]=re.getString(5);
                sum++;
                de.addRow(a);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            shujuku.cxclose(con,pre,re);
        }
    }
    //根据五个条件查学生信息，判断有无此学生信息
    public static boolean p(String s[],String sql){
        Connection con=null;
        PreparedStatement pre=null;
        ResultSet re=null;
        try{
            shujuku.cxforname();
            con=shujuku.cxgetConnection();
            pre=con.prepareStatement(sql);
            pre.setString(1,s[0]);pre.setString(2,s[1]);
            pre.setString(3,s[2]);pre.setString(4,s[3]);
            pre.setString(5,s[4]);
            re=pre.executeQuery();
            if(re.next()){
                return true;
            }
            else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            shujuku.cxclose(con,pre,re);
        }
        return false;
    }
    //根据俩条件查学生信息
    public static void p(DefaultTableModel jt, String s1, String s2,String sql){
        Connection con=null;
        PreparedStatement pre=null;
        ResultSet re=null;
        try{
            shujuku.cxforname();
            con=shujuku.cxgetConnection();
            pre=con.prepareStatement(sql);
            pre.setString(1,s1);
            pre.setString(2,s2);
            re=pre.executeQuery();
            int sum=0;
            while(re.next()){
                sum++;
            }
            re=pre.executeQuery();
            Object a[]=new Object[5];
            sum=0;
            while(re.next()){
                a[0]=Integer.valueOf(re.getInt(1));
                a[1]=Integer.valueOf(re.getInt(2));
                a[2]=re.getString(3);
                a[3]=re.getString(4);
                a[4]=re.getString(5);
                sum++;
                jt.addRow(a);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        shujuku.cxclose(con,pre,re);
    }
    //根据一条件查学生信息
    public static void p(DefaultTableModel jt, String banji,String sql){
        Connection con=null;
        PreparedStatement pre=null;
        ResultSet re=null;
        try{
            shujuku.cxforname();
            con=shujuku.cxgetConnection();
            pre=con.prepareStatement(sql);
            pre.setString(1,banji);
            re=pre.executeQuery();
            int sum=0;
            while(re.next()){
                sum++;
            }
            re=pre.executeQuery();
            Object a[]=new Object[5];
            sum=0;
            while(re.next()){
                a[0]=Integer.valueOf(re.getInt(1));
                a[1]=Integer.valueOf(re.getInt(2));
                a[2]=re.getString(3);
                a[3]=re.getString(4);
                a[4]=re.getString(5);
                sum++;
                jt.addRow(a);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        shujuku.cxclose(con,pre,re);
    }
    //查询全体教师的信息
    public static void j(DefaultTableModel de){
        Connection con=null;
        PreparedStatement pre=null;
        ResultSet re=null;
        try{
            shujuku.cxforname();
            con=shujuku.cxgetConnection();
            String s="select * from teacher ";
            pre=con.prepareStatement(s);
            re=pre.executeQuery();
            int sum=0;
            while(re.next()){
                sum++;
            }
            re=pre.executeQuery();
            Object a[]=new Object[4];
            sum=0;
            while(re.next()){
                a[0]=re.getString(1);
                a[1]=re.getString(2);
                a[2]=re.getString(3);
                a[3]=re.getString(4);
                sum++;
                de.addRow(a);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            shujuku.cxclose(con,pre,re);
        }
    }
    //根据一个条件查教师信息
    public static void j(DefaultTableModel jt, String n,String sql){
        Connection con=null;
        PreparedStatement pre=null;
        ResultSet re=null;
        try{
            shujuku.cxforname();
            con=shujuku.cxgetConnection();
            pre=con.prepareStatement(sql);
            pre.setString(1,n);
            re=pre.executeQuery();
            int sum=0;
            while(re.next()){
                sum++;
            }
            re=pre.executeQuery();
            Object a[]=new Object[4];
            sum=0;
            while(re.next()){
                a[0]=re.getString(1);
                a[1]=re.getString(2);
                a[2]=re.getString(3);
                a[3]=re.getString(4);
                sum++;
                jt.addRow(a);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        shujuku.cxclose(con,pre,re);
    }
    //根据四个条件查教师信息,判断有无此教师信息
    public static boolean j(String s[],String sql) {
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet re = null;
        try {
            shujuku.cxforname();
            con = shujuku.cxgetConnection();
            pre = con.prepareStatement(sql);
            pre.setString(1, s[0]);
            pre.setString(2, s[1]);
            pre.setString(3, s[2]);
            pre.setString(4, s[3]);
            re = pre.executeQuery();
            int sum;
            if (re.next()) {
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            shujuku.cxclose(con, pre, re);
        }
        return false;
    }
}
