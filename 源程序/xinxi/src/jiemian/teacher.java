package jiemian;

public class teacher {
    private String name,sex,dianhua,shouke;
    public teacher(String name,String sex,String dianhua,String shouke){
        this.name=name;
        this.sex=sex;
        this.dianhua=dianhua;
        this.shouke=shouke;
    }
    public String getName(){
        return name;
    }
    public String getSex(){
        return sex;
    }
    public String getDianhua(){
        return dianhua;
    }
    public String getShouke(){
        return shouke;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setSex(String sex){
        this.sex=sex;
    }
    public void setDianhua(String dianhua){
        this.dianhua=dianhua;
    }
    public void setShouke(String shouke){
        this.shouke=shouke;
    }
}
