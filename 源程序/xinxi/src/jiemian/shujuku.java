package jiemian;
import java.sql.*;

public class shujuku {
    static String fn="com.mysql.cj.jdbc.Driver";
    static String url = "jdbc:mysql://127.0.0.1:3306/xinxi";//192.168.1.220
    static String user = "root";
    static String password = "123456";
    public static void cxforname() throws ClassNotFoundException {
        Class.forName(fn);
    }
    public static Connection cxgetConnection(){
        Connection con=null;
        try{
            con=DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    public static void cxclose(Connection con,Statement sta,ResultSet re){
        if(re!=null){
            try{
                re.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(sta!=null){
            try{
                sta.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(con!=null){
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
