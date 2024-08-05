package jiemian;

public class student {
    private int banji,sno;
    private String name,sex,dianhua;
    public student(int banji,int sno,String name,String sex,String dianhua){
        this.sno=sno;
        this.name=name;
        this.sex=sex;
        this.banji=banji;
        this.dianhua=dianhua;
    }
    public int getSno(){
        return sno;
    }
    public void setSno(int sno){
        this.sno=sno;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getSex(){
        return sex;
    }
    public void setSex(String sex){
        this.sex=sex;
    }
    public int getBanji(){
        return banji;
    }
    public void setBanji(String zhuanye){
        this.banji=banji;
    }
    public String getDianhua(){
        return dianhua;
    }
    public void setDianhua(String dianhua){
        this.dianhua=dianhua;
    }
}
