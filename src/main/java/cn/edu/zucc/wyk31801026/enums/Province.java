package cn.edu.zucc.wyk31801026.enums;


/**
 * @author WYK
 */


/**
 * 省份的枚举类
 */
public enum  Province {

    BEIJING("Bei Jing",1),
    SHANGHAI("Shang Hai",2),
    TIANJIN("Tian Jin",3),
    CHONGQING("Chong Qing",4),
    XIANGGANG("Xiang Gang",5),
    AOMEN("Ao Men",6),
    ANHUI("An Hui",7),
    FUJIAN("Fu Jian",8),
    GUANGDONG("Guang Dong",9),
    GUANGXI("Guang Xi",10),
    GUIZHOU("Gui Zhou",11),
    GANSU("Gan Su",12),
    HAINAN("Hai Nan",13),
    HEBEI("He Bei",14),
    HENAN("He Nan",15),
    HEILONGJIANG("Hei Long Jiang",16),
    HUBEI("Hu Bei",17),
    HUNAN("Hu Nan",18),
    JILIN("Ji Lin",19),
    JIANGSU("Jiang Su",20),
    JIANGXI("Jiang Xi",21),
    LIAONING("Liao Ning",22),
    NEIMENGGU("Nei Meng Gu",23),
    NINGXIA("Ning Xia",24),
    QINGHAI("Qing Hai",25),
    SHANXIQIN("Shaan Xi",26),
    SHANXIJIN("Shan Xi",27),
    SHANDONG("Shan Dong",28),
    SICHUANG("Si Chuang",29),
    TAIWAN("Tai Wan",30),
    XIZANG("Xi Zang",31),
    XINJIANG("Xin Jiang",32),
    YUNNAN("Yun Nan",33),
    ZHEJIANG("Zhe Jiang",34);

    private String name;
    private Integer id;

    Province(String name,Integer id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static String getNameById(Integer id)
    {
        for (Province province:Province.values()){
            if (id.equals((province.getId()))){
                return province.getName();
            }
        }
        return null;
    }

    public static Integer getIdByName(String name)
    {
        for (Province province:Province.values()){
            if (name.equals((province.getName()))){
                return province.getId();
            }
        }
        return null;
    }
}
