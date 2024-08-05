package jiemian;

import gongneng.cha;
import gongneng.gai;
import gongneng.shan;
import gongneng.zeng;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class ex1 extends JFrame {
    public ex1(){
        setSize(400,280);
        setTitle("登录界面");
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new GridLayout(5,1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //添加组件
        JPanel panel1=new JPanel();
        JLabel l1=new JLabel("欢迎来到信息管理系统");
        Font fnt = new Font("微软雅黑", Font.BOLD, 20);
        l1.setFont(fnt);
        panel1.add(l1);add(panel1);

        JPanel panel2=new JPanel();
        JLabel l2=new JLabel("用户名:");TextField t1=new TextField(20);
        panel2.add(l2);panel2.add(t1);
        add(panel2);

        JPanel panel3=new JPanel();
        JLabel l3=new JLabel("密   码:");TextField t2=new TextField(20);
        panel3.add(l3);panel3.add(t2);
        add(panel3);

        JPanel panel4=new JPanel();
        JLabel l4=new JLabel("你的身份是:");
        String com1[]={"管理员","教师","学生"};
        JComboBox com=new JComboBox(com1);//下拉框
        panel4.add(l4);panel4.add(com);
        add(panel4);

        JPanel panel5=new JPanel();
        JButton but2=new JButton("登录");
        JButton but3=new JButton("注册");
        panel5.add(but2);panel5.add(but3);
        add(panel5);
        but2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame cuowu=new JFrame();
                String s1=t1.getText();
                String s2=t2.getText();
                int cnm=com.getSelectedIndex();//获取下拉框中选择的索引;
                if(s1.equals("")||s2.equals("")) {
                    JOptionPane.showMessageDialog(cuowu, "对不起,请输入用户名或密码!");//消息提示框
                }
                else{
                    boolean denglu1=dlcg(s1,s2,cnm);
                    if(denglu1==true){
                        dispose();
                        JFrame a=new JFrame();
                        a.setSize(200,150);
                        a.setResizable(false);
                        a.setLocationRelativeTo(null);
                        a.setLayout(new GridLayout(2,1));
                        JPanel jpa1=new JPanel();JPanel jpa2=new JPanel();
                        JLabel jla=new JLabel("登录成功！");
                        JButton jba=new JButton("确定");
                        jpa1.add(jla);jpa2.add(jba);
                        a.add(jpa1);a.add(jpa2);
                        a.setVisible(true);
                        if(cnm==0){//管理员
                            ppp1 p1=new ppp1();
                            jba.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    a.dispose();
                                }
                            });
                            jba.addActionListener(p1);
                        }else if(cnm==1){//教师
                            ppp3 p1=new ppp3();
                            jba.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    a.dispose();
                                }
                            });
                            jba.addActionListener(p1);
                        }else{//学生
                            ppp4 p1=new ppp4();
                            jba.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    a.dispose();
                                }
                            });
                            jba.addActionListener(p1);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(cuowu, "用户名或密码或身份错误！");
                    }
                }
            }
        });
        ppp2 p2=new ppp2();
        but3.addActionListener(p2);
        setVisible(true);
    }
    //判断是否登录成功
    private boolean dlcg(String s1,String s2,int cnm){
        Connection con=null;
        PreparedStatement pre=null;
        ResultSet re=null;
        try{
            shujuku.cxforname();
            con=shujuku.cxgetConnection();
            String s3="select * from login where root=? and password=? and zhiye=?";
            pre=con.prepareStatement(s3);
            pre.setString(1,s1);pre.setString(2,s2);pre.setInt(3,cnm);
            re=pre.executeQuery();
            if(re.next()){
                return true;
            }
            else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            shujuku.cxclose(con,pre,re);
        }
        return false;
    }
}
//管理员端
class ppp1 implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame guan=new JFrame("管理员");
        guan.setSize(700,500);
        guan.setLocationRelativeTo(null);
        guan.setResizable(false);
        guan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guan.setLayout(new GridLayout(2,1));

        JPanel l1=new JPanel();
        JButton j1=new JButton("管理教师信息");
        l1.add(j1);
        guan.add(l1);
        j1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jiao=new JFrame("教师信息");
                jiao.setSize(700,500);
                jiao.setLocationRelativeTo(null);
                jiao.setResizable(false);
                jiao.setLayout(new GridLayout(2,1));

                JPanel jj=new JPanel();
                Object a[][]=new Object[0][4];
                String title[]={"姓名","性别","电话","授课"};
                DefaultTableModel de=new DefaultTableModel(a,title);
                JTable jt=new JTable(de){
                    public boolean isCellEditable(int row,int lie){
                        return false;
                    }
                };
                JScrollPane js=new JScrollPane(jt);
                js.setPreferredSize(new Dimension(500,230));
                jj.add(js);
                jiao.add(jj);
                cha.j(de);

                JPanel jpj=new JPanel();
                jpj.setLayout(new GridLayout(5,3));
                JPanel jpj1=new JPanel();
                JLabel jlj1=new JLabel("姓名:");JTextField jtj1=new JTextField(20);JButton zengg=new JButton("增加");
                jpj1.add(jlj1);jpj1.add(jtj1);jpj1.add(zengg);jpj.add(jpj1);
                JPanel jpj2=new JPanel();
                JLabel jlj2=new JLabel("性别:");JTextField jtj2=new JTextField(20);JButton shann=new JButton("删除");
                jpj2.add(jlj2);jpj2.add(jtj2);jpj2.add(shann);jpj.add(jpj2);
                JPanel jpj3=new JPanel();
                JLabel jlj3=new JLabel("电话:");JTextField jtj3=new JTextField(20);JButton chaa=new JButton("查找");
                jpj3.add(jlj3);jpj3.add(jtj3);jpj3.add(chaa);jpj.add(jpj3);
                JPanel jpj4=new JPanel();
                JLabel jlj4=new JLabel("授课:");JTextField jtj4=new JTextField(20);JButton gaii=new JButton("改动");
                jpj4.add(jlj4);jpj4.add(jtj4);jpj4.add(gaii);jpj.add(jpj4);
                JPanel jpj5=new JPanel();
                JButton qing=new JButton("清空");JButton quan=new JButton("查询全体教师");
                jpj5.add(quan);jpj5.add(qing);jpj.add(jpj5);
                jiao.add(jpj);
                quan.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cha.j(de);
                    }
                });
                zengg.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String s[]={jtj1.getText(), jtj2.getText(),jtj3.getText(),jtj4.getText()};
                        if(s[1].equals("男")||s[1].equals("女")) {
                            String sql = "select * from teacher where name=? and sex=? and dianhua=? and shouke=?";
                            if (cha.j(s, sql)) {
                                JOptionPane.showMessageDialog(new JFrame(), "您所插入的信息已经存在!");
                            } else {

                                de.addRow(s);
                                teacher teacher = new teacher(jtj1.getText(), jtj2.getText(), jtj3.getText(), jtj4.getText());
                                zeng.zengj(teacher);
                            }
                        }else{
                            JOptionPane.showMessageDialog(new JFrame(),"请输入正确的性别!");
                        }
                    }
                });
                shann.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int row=jt.getSelectedRow();
                        if(row==-1){
                            JOptionPane.showMessageDialog(new JFrame(),"请选择一项数据!");
                        }
                        de.removeRow(row);
                        teacher teacher=new teacher(jtj1.getText(),jtj2.getText(), jtj3.getText(),jtj4.getText());
                        shan.shanj(teacher);
                    }
                });
                gaii.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int row=jt.getSelectedRow();
                        if(row==-1){
                            JOptionPane.showMessageDialog(new JFrame(),"请选择一项数据!");
                        }
                        String s=jtj2.getText();
                        if(s.equals("男")||s.equals("女")) {
                            //1被改成2
                            teacher t1 = new teacher((String) jt.getValueAt(row, 0), (String) jt.getValueAt(row, 1), (String) jt.getValueAt(row, 2),
                                    (String) jt.getValueAt(row, 3));
                            teacher t2 = new teacher(jtj1.getText(), jtj2.getText(), jtj3.getText(), jtj4.getText());
                            gai.gaij(t1, t2);
                            jt.setValueAt(jtj1.getText(), row, 0);
                            jt.setValueAt(jtj2.getText(), row, 1);
                            jt.setValueAt(jtj3.getText(), row, 2);
                            jt.setValueAt(jtj4.getText(), row, 3);
                        }else {
                            JOptionPane.showMessageDialog(new JFrame(),"你的性别不对!");
                        }
                    }
                });
                chaa.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String c1=jtj1.getText();String c2=jtj2.getText();String c3=jtj3.getText();String c4=jtj4.getText();//姓名，性别，电话，授课
                        if(c1.equals("")&&c2.equals("")&&c3.equals("")&&c4.equals("")) {
                            JOptionPane.showMessageDialog(new JFrame(),"你所输入的信息过少!");
                        }
                        if(!c1.equals("")){
                            String sql="select * from teacher where name=?";
                            cha.j(de,c1,sql);return ;
                        }
                        if(!c2.equals("")){
                            String sql="select * from teacher where sex=?";
                            cha.j(de,c2,sql);return ;
                        }
                        if(!c3.equals("")){
                            String sql="select * from teacher where dianhua=?";
                            cha.j(de,c3,sql);return ;
                        }
                        if(!c4.equals("")){
                            String sql="select * from teacher where shouke=?";
                            cha.j(de,c4,sql);return ;
                        }
                    }
                });
                qing.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        de.setRowCount(0);
                    }
                });
                jt.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        int row=jt.getSelectedRow();
                        if(row==-1){
                            JOptionPane.showMessageDialog(new JFrame(),"请选择一项数据!");
                        }
                        String lie1= (String) jt.getValueAt(row,0);String lie2= (String) jt.getValueAt(row,1);
                        String lie3= (String) jt.getValueAt(row,2);String lie4= (String) jt.getValueAt(row,3);
                        jtj1.setText(String.valueOf(lie1));jtj2.setText(String.valueOf(lie2));
                        jtj3.setText(lie3);jtj4.setText(lie4);
                    }
                });
                jiao.setVisible(true);
            }
        });

        JPanel l2=new JPanel();
        JButton j2=new JButton("管理学生信息");
        l2.add(j2);
        guan.add(l2);
        j2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame stu=new JFrame("学生信息");
                stu.setSize(700,500);
                stu.setLocationRelativeTo(null);
                stu.setResizable(false);
                stu.setLayout(new GridLayout(2,1));

                JPanel jj=new JPanel();
                Object a[][]=new Object[0][5];
                String title[]={"班级","学号","姓名","性别","电话"};
                DefaultTableModel de=new DefaultTableModel(a,title);
                JTable jt=new JTable(de){//表格不能被编辑
                    public boolean isCellEditable(int row,int lie){
                        return false;
                    }
                };
                JScrollPane js=new JScrollPane(jt);
                js.setPreferredSize(new Dimension(500,230));
                jj.add(js);
                stu.add(jj);
                cha.p(de);

                JPanel jpj=new JPanel();
                jpj.setLayout(new GridLayout(6,3));
                JPanel jpj1=new JPanel();
                JLabel jlj1=new JLabel("班级:");JTextField jtj1=new JTextField(20);JButton zengg=new JButton("增加");
                jpj1.add(jlj1);jpj1.add(jtj1);jpj1.add(zengg);jpj.add(jpj1);
                JPanel jpj2=new JPanel();
                JLabel jlj2=new JLabel("学号:");JTextField jtj2=new JTextField(20);JButton shann=new JButton("删除");
                jpj2.add(jlj2);jpj2.add(jtj2);jpj2.add(shann);jpj.add(jpj2);
                JPanel jpj3=new JPanel();
                JLabel jlj3=new JLabel("姓名:");JTextField jtj3=new JTextField(20);JButton chaa=new JButton("查找");
                jpj3.add(jlj3);jpj3.add(jtj3);jpj3.add(chaa);jpj.add(jpj3);
                JPanel jpj4=new JPanel();
                JLabel jlj4=new JLabel("性别:");JTextField jtj4=new JTextField(20);JButton gaii=new JButton("改动");
                jpj4.add(jlj4);jpj4.add(jtj4);jpj4.add(gaii);jpj.add(jpj4);
                JPanel jpj5=new JPanel();
                JLabel jlj5=new JLabel("电话:");JTextField jtj5=new JTextField(20);JButton qing=new JButton("清空");
                jpj5.add(jlj5);jpj5.add(jtj5);jpj5.add(qing);jpj.add(jpj5);
                JPanel jpj6=new JPanel();
                JButton quan=new JButton("查找全体学生");jpj6.add(quan);jpj.add(jpj6);
                stu.add(jpj);
                quan.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cha.p(de);
                    }
                });
                zengg.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String s[]={jtj1.getText(), jtj2.getText(),jtj3.getText(),jtj4.getText(),jtj5.getText()};
                        if(s[3].equals("男")||s[3].equals("女")) {
                            String sql = "select * from student where banji=? and sno=? and name=? and sex=? and dianhua=?";
                            if (cha.p(s, sql)) {
                                JOptionPane.showMessageDialog(new JFrame(), "您所增加的信息已经存在!");
                            } else {
                                de.addRow(s);
                                student student = new student(Integer.parseInt(jtj1.getText()), Integer.parseInt(jtj2.getText()),
                                        jtj3.getText(), jtj4.getText(), jtj5.getText());
                                zeng.zengp(student);
                            }
                        }else {
                            JOptionPane.showMessageDialog(new JFrame(),"请输入正确的性别!");
                        }
                    }
                });
                shann.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int row=jt.getSelectedRow();
                        if(row==-1){
                            JOptionPane.showMessageDialog(new JFrame(),"请选择一项数据!");
                        }
                        de.removeRow(row);
                        student student=new student(Integer.parseInt(jtj1.getText()),Integer.parseInt(jtj2.getText()),
                                jtj3.getText(),jtj4.getText(),jtj5.getText());
                        shan.shanp(student);
                    }
                });
                gaii.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int row=jt.getSelectedRow();
                        if(row==-1){
                            JOptionPane.showMessageDialog(new JFrame(),"请选择一项数据!");
                        }
                        String s=jtj4.getText();
                        if(s.equals("男")||s.equals("女")) {
                            //1被改成2
                            student stu1 = new student((int) jt.getValueAt(row, 0), (int) jt.getValueAt(row, 1), (String) jt.getValueAt(row, 2),
                                    (String) jt.getValueAt(row, 3), (String) jt.getValueAt(row, 4));
                            student stu2 = new student(Integer.parseInt(jtj1.getText()), Integer.parseInt(jtj2.getText()),
                                    jtj3.getText(), jtj4.getText(), jtj5.getText());
                            gai.gaip(stu1, stu2);
                            jt.setValueAt(jtj1.getText(), row, 0);
                            jt.setValueAt(jtj2.getText(), row, 1);
                            jt.setValueAt(jtj3.getText(), row, 2);
                            jt.setValueAt(jtj4.getText(), row, 3);
                            jt.setValueAt(jtj5.getText(), row, 4);
                        }else {
                            JOptionPane.showMessageDialog(new JFrame(),"你的性别不对!");
                        }
                    }
                });
                chaa.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String c1=jtj1.getText();String c2=jtj2.getText();String c3=jtj3.getText();//班级，学号,姓名
                        String c4=jtj4.getText();String c5=jtj5.getText();//性别,电话;
                        if(c1.equals("")&&c3.equals("")&&c4.equals("")&&c5.equals("")){
                            JOptionPane.showMessageDialog(new JFrame(),"你输入的信息过少!");
                        }
                        if(!c2.equals("")&&!c1.equals("")){
                            String sql="select * from student where banji=? and sno =?";
                            cha.p(de,c1,c2,sql);return ;
                        }
                        if(!c3.equals("")&&!c1.equals("")){
                            String sql="select * from student where banji=? and name=?";
                            cha.p(de,c1,c3,sql);return ;
                        }
                        if(!c4.equals("")&&!c1.equals("")){
                            String sql="select * from student where banji=? and sex=?";
                            cha.p(de,c1,c4,sql);return ;
                        }
                        if(!c5.equals("")){
                            String sql="select * from student where dianhua=?";
                            cha.p(de,c5,sql);return ;
                        }
                        if(c2.equals("")&&c3.equals("")&&!c1.equals("")){
                            String sql="select * from student where banji=?";
                            cha.p(de,c1,sql);return ;
                        }
                        if(!c3.equals("")&&c1.equals("")){
                            String sql="select * from student where name=?";
                            cha.p(de,c3,sql);return ;
                        }
                        if(!c4.equals("")){
                            String sql="select * from student where sex=?";
                            cha.p(de,c4,sql);
                        }
                    }
                });
                qing.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        de.setRowCount(0);
                    }
                });
                jt.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        int row=jt.getSelectedRow();
                        if(row==-1){
                            JOptionPane.showMessageDialog(new JFrame(),"请选择一项数据!");
                        }
                        int lie1= (int) jt.getValueAt(row,0);int lie2= (int) jt.getValueAt(row,1);
                        String lie3= (String) jt.getValueAt(row,2);String lie4= (String) jt.getValueAt(row,3);
                        String lie5= (String) jt.getValueAt(row,4);
                        jtj1.setText(String.valueOf(lie1));jtj2.setText(String.valueOf(lie2));
                        jtj3.setText(lie3);jtj4.setText(lie4);jtj5.setText(lie5);
                    }
                });
                stu.setVisible(true);
            }
        });
        guan.setVisible(true);
    }
}
//教师端
class ppp3 implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame jiao=new JFrame("教师");
        jiao.setSize(700,500);
        jiao.setLocationRelativeTo(null);
        jiao.setResizable(false);
        jiao.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jiao.setLayout(new GridLayout(2,1));

        JPanel l1=new JPanel();
        JButton j1=new JButton("查教师信息");
        l1.add(j1);
        jiao.add(l1);
        j1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame stu=new JFrame("教师信息");
                stu.setSize(700,500);
                stu.setLocationRelativeTo(null);
                stu.setResizable(false);
                stu.setLayout(new GridLayout(2,1));

                JPanel jj=new JPanel();
                Object a[][]=new Object[0][4];
                String title[]={"姓名","性别","电话","授课"};
                DefaultTableModel de=new DefaultTableModel(a,title);
                JTable jt=new JTable(de){
                    public boolean isCellEditable(int row,int lie){
                        return false;
                    }
                };
                JScrollPane js=new JScrollPane(jt);
                js.setPreferredSize(new Dimension(500,230));
                jj.add(js);
                stu.add(jj);

                JPanel jj1=new JPanel(new GridLayout(5,1));
                JPanel jps1=new JPanel();
                JLabel jls1=new JLabel("请输入姓名:");JTextField jt1=new JTextField(20);
                jps1.add(jls1);jps1.add(jt1);
                jj1.add(jps1);
                JPanel jps3=new JPanel();
                JButton jb1=new JButton("查询") ;JButton jb2=new JButton("清空") ;
                jb2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        de.setRowCount(0);
                    }
                });
                jps3.add(jb1);jps3.add(jb2);
                jj1.add(jps3);
                stu.add(jj1);
                jb1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name=jt1.getText();
                        String s="select * from teacher where name=?";
                        cha.j(de,name,s);
                    }
                });
                stu.setVisible(true);
            }
        });

        JPanel l2=new JPanel();
        JButton j2=new JButton("管理学生信息");
        l2.add(j2);
        jiao.add(l2);
        j2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame stu=new JFrame("学生信息");
                stu.setSize(700,500);
                stu.setLocationRelativeTo(null);
                stu.setResizable(false);
                stu.setLayout(new GridLayout(2,1));

                JPanel jj=new JPanel();
                Object a[][]=new Object[0][5];
                String title[]={"班级","学号","姓名","性别","电话"};
                DefaultTableModel de=new DefaultTableModel(a,title);
                JTable jt=new JTable(de){
                    public boolean isCellEditable(int row,int lie){
                        return false;
                    }
                };
                JScrollPane js=new JScrollPane(jt);
                js.setPreferredSize(new Dimension(500,230));
                jj.add(js);
                stu.add(jj);
                cha.p(de);

                JPanel jpj=new JPanel();
                jpj.setLayout(new GridLayout(6,3));
                JPanel jpj1=new JPanel();
                JLabel jlj1=new JLabel("班级:");JTextField jtj1=new JTextField(20);JButton zengg=new JButton("增加");
                jpj1.add(jlj1);jpj1.add(jtj1);jpj1.add(zengg);jpj.add(jpj1);
                JPanel jpj2=new JPanel();
                JLabel jlj2=new JLabel("学号:");JTextField jtj2=new JTextField(20);JButton shann=new JButton("删除");
                jpj2.add(jlj2);jpj2.add(jtj2);jpj2.add(shann);jpj.add(jpj2);
                JPanel jpj3=new JPanel();
                JLabel jlj3=new JLabel("姓名:");JTextField jtj3=new JTextField(20);JButton chaa=new JButton("查找");
                jpj3.add(jlj3);jpj3.add(jtj3);jpj3.add(chaa);jpj.add(jpj3);
                JPanel jpj4=new JPanel();
                JLabel jlj4=new JLabel("性别:");JTextField jtj4=new JTextField(20);JButton gaii=new JButton("改动");
                jpj4.add(jlj4);jpj4.add(jtj4);jpj4.add(gaii);jpj.add(jpj4);
                JPanel jpj5=new JPanel();
                JLabel jlj5=new JLabel("电话:");JTextField jtj5=new JTextField(20);JButton qing=new JButton("清空");
                jpj5.add(jlj5);jpj5.add(jtj5);jpj5.add(qing);jpj.add(jpj5);
                JPanel jpj6=new JPanel();
                JButton quan=new JButton("查找全体学生");jpj6.add(quan);jpj.add(jpj6);
                stu.add(jpj);
                quan.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cha.p(de);
                    }
                });
                zengg.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String s[] = {jtj1.getText(), jtj2.getText(), jtj3.getText(), jtj4.getText(), jtj5.getText()};
                        if (s[3].equals("男") || s[3].equals("女")) {
                            String sql = "select * from student where banji=? and sno=? and name=? and sex=? and dianhua=?";
                            if (cha.p(s, sql)) {
                                JOptionPane.showMessageDialog(new JFrame(), "您所增加的信息已经存在!");
                            } else {
                                de.addRow(s);
                                student student = new student(Integer.parseInt(jtj1.getText()), Integer.parseInt(jtj2.getText()),
                                        jtj3.getText(), jtj4.getText(), jtj5.getText());
                                zeng.zengp(student);
                            }
                        }else{
                            JOptionPane.showMessageDialog(new JFrame(),"请输入正确的性别!");
                        }
                    }
                });
                shann.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int row=jt.getSelectedRow();
                        if(row==-1){
                            JOptionPane.showMessageDialog(new JFrame(),"请选择一项数据!");
                        }
                        de.removeRow(row);
                        student student=new student(Integer.parseInt(jtj1.getText()),Integer.parseInt(jtj2.getText()),
                                jtj3.getText(),jtj4.getText(),jtj5.getText());
                        shan.shanp(student);
                    }
                });
                gaii.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int row=jt.getSelectedRow();
                        if(row==-1){
                            JOptionPane.showMessageDialog(new JFrame(),"请选择一项数据!");
                        }
                        String s=jtj4.getText();
                        if(s.equals("男")||s.equals("女")) {
                            //1被改成2
                            student stu1 = new student((int) jt.getValueAt(row, 0), (int) jt.getValueAt(row, 1), (String) jt.getValueAt(row, 2),
                                    (String) jt.getValueAt(row, 3), (String) jt.getValueAt(row, 4));
                            student stu2 = new student(Integer.parseInt(jtj1.getText()), Integer.parseInt(jtj2.getText()),
                                    jtj3.getText(), jtj4.getText(), jtj5.getText());
                            gai.gaip(stu1, stu2);
                            jt.setValueAt(jtj1.getText(), row, 0);
                            jt.setValueAt(jtj2.getText(), row, 1);
                            jt.setValueAt(jtj3.getText(), row, 2);
                            jt.setValueAt(jtj4.getText(), row, 3);
                            jt.setValueAt(jtj5.getText(), row, 4);
                        }else {
                            JOptionPane.showMessageDialog(new JFrame(),"您的性别不对!");
                        }
                    }
                });
                chaa.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String c1=jtj1.getText();String c2=jtj2.getText();String c3=jtj3.getText();//班级，学号,姓名
                        String c4=jtj4.getText();String c5=jtj5.getText();//性别,电话;
                        if(c1.equals("")&&c3.equals("")&&c4.equals("")&&c5.equals("")){
                            JOptionPane.showMessageDialog(new JFrame(),"你输入的信息过少!");
                        }
                        if(!c2.equals("")&&!c1.equals("")){
                            String sql="select * from student where banji=? and sno =?";
                            cha.p(de,c1,c2,sql);return ;
                        }
                        if(!c3.equals("")&&!c1.equals("")){
                            String sql="select * from student where banji=? and name=?";
                            cha.p(de,c1,c3,sql);return ;
                        }
                        if(!c4.equals("")&&!c1.equals("")){
                            String sql="select * from student where banji=? and sex=?";
                            cha.p(de,c1,c4,sql);return ;
                        }
                        if(!c5.equals("")){
                            String sql="select * from student where dianhua=?";
                            cha.p(de,c5,sql);return ;
                        }
                        if(c2.equals("")&&c3.equals("")&&!c1.equals("")){
                            String sql="select * from student where banji=?";
                            cha.p(de,c1,sql);return ;
                        }
                        if(!c3.equals("")&&c1.equals("")){
                            String sql="select * from student where name=?";
                            cha.p(de,c3,sql);return ;
                        }
                        if(!c4.equals("")){
                            String sql="select * from student where sex=?";
                            cha.p(de,c4,sql);
                        }
                    }
                });
                qing.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        de.setRowCount(0);
                    }
                });
                jt.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        int row=jt.getSelectedRow();
                        if(row==-1){
                            JOptionPane.showMessageDialog(new JFrame(),"请选择一项数据!");
                        }
                        int lie1= (int) jt.getValueAt(row,0);int lie2= (int) jt.getValueAt(row,1);
                        String lie3= (String) jt.getValueAt(row,2);String lie4= (String) jt.getValueAt(row,3);
                        String lie5= (String) jt.getValueAt(row,4);
                        jtj1.setText(String.valueOf(lie1));jtj2.setText(String.valueOf(lie2));
                        jtj3.setText(lie3);jtj4.setText(lie4);jtj5.setText(lie5);
                    }
                });
                stu.setVisible(true);
            }
        });
        jiao.setVisible(true);
    }
}
//学生端
class ppp4 implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame stu=new JFrame("学生");
        stu.setSize(700,500);
        stu.setLocationRelativeTo(null);
        stu.setResizable(false);
        stu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        stu.setLayout(new GridLayout(2,1));

        JPanel jj=new JPanel();
        Object a[][]=new Object[0][5];
        String title[]={"班级","学号","姓名","性别","电话"};
        DefaultTableModel de=new DefaultTableModel(a,title);
        JTable jt=new JTable(de){
            public boolean isCellEditable(int row,int lie){
                return false;
            }
        };
        JScrollPane js=new JScrollPane(jt);
        js.setPreferredSize(new Dimension(500,230));
        jj.add(js);
        stu.add(jj);

        JPanel jj1=new JPanel(new GridLayout(5,1));
        JPanel jps1=new JPanel();
        JLabel jls1=new JLabel("请输入班级:");JTextField jt1=new JTextField(20);
        jps1.add(jls1);jps1.add(jt1);
        jj1.add(jps1);
        JPanel jps2=new JPanel();
        JLabel jls2=new JLabel("请输入学号:");JTextField jt2=new JTextField(20);
        jps2.add(jls2);jps2.add(jt2);
        jj1.add(jps2);
        JPanel jps3=new JPanel();
        JButton jb1=new JButton("查询") ;
        JButton jb2=new JButton("清空") ;
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                de.setRowCount(0);
            }
        });
        jps3.add(jb1);jps3.add(jb2);
        jj1.add(jps3);
        stu.add(jj1);
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String banji=jt1.getText();
                String xuehao=jt2.getText();
                String s="select * from student where banji=? and sno =?";
                cha.p(de,banji,xuehao,s);
                if(xuehao.equals("")){
                    String sql="select * from student where banji=?";
                    cha.p(de,banji,sql);
                }
            }
        });
        stu.setVisible(true);
    }
}
//注册账号
class ppp2 implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame a=new JFrame("注册账号");
        a.setResizable(false);
        a.setSize(400,280);
        a.setLocationRelativeTo(null);
        a.setLayout(new GridLayout(5,1));

        JPanel panela1=new JPanel();
        JLabel la1=new JLabel("用户名:");TextField ta1=new TextField(20);
        panela1.add(la1);panela1.add(ta1);
        a.add(panela1);

        JPanel panela2=new JPanel();
        JLabel la2=new JLabel("密   码:");TextField ta2=new TextField(20);
        panela2.add(la2);panela2.add(ta2);
        a.add(panela2);

        JPanel panela4=new JPanel();
        JLabel la4=new JLabel("你的身份是:");
        String com1[]={"管理员","教师","学生"};
        JComboBox coma=new JComboBox(com1);//下拉框
        panela4.add(la4);panela4.add(coma);
        a.add(panela4);

        JPanel panela3=new JPanel();
        JButton jb=new JButton("确认");
        panela3.add(jb);a.add(panela3);
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s1=ta1.getText();
                String s2=ta2.getText();
                if(s1.equals("")||s2.equals("")){
                    JOptionPane.showMessageDialog(new JFrame(),"你的用户名或者密码为空!");
                }else {
                    int ss1=coma.getSelectedIndex();//0是管理员，1是教师，2是学生;
                    Connection con=null;
                    PreparedStatement pre=null;
                    ResultSet re=null;
                    int root1=0;
                    try {
                        shujuku.cxforname();
                        con=shujuku.cxgetConnection();
                        String s3="select * from login where root=?";
                        pre=con.prepareStatement(s3);
                        pre.setString(1,s1);
                        re=pre.executeQuery();
                        if(re.next()) {
                            root1=1;
                            JOptionPane.showMessageDialog(new JFrame(), "该用户已经存在!");
                            ta1.setText("");ta2.setText("");
                        }
                    } catch (Exception p) {
                        p.printStackTrace();
                    }finally {
                        shujuku.cxclose(con,pre,re);
                    }
                    if(root1==0) {
                        try {
                            shujuku.cxforname();
                            con = shujuku.cxgetConnection();
                            String s3 = "insert into login values(?,?,?)";
                            pre = con.prepareStatement(s3);
                            pre.setString(1, s1);
                            pre.setString(2, s2);
                            pre.setInt(3, ss1);
                            pre.executeUpdate();
                        } catch (Exception p) {
                            p.printStackTrace();
                        } finally {
                            shujuku.cxclose(con, pre, re);
                        }
                        a.dispose();
                    }
                }
            }
        });
        a.setVisible(true);
    }
}
