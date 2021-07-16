package cn.edu.zucc.wyk31801026.enums;


/**
 * @author WYK
 */


/**
 * 省份的枚举类
 */
public enum  Province {

    BEIJING("北京",1),
    SHANGHAI("上海",2),
    TIANJIN("天津",3),
    CHONGQING("重庆",4),
    XIANGGANG("香港",5),
    AOMEN("澳门",6),
    ANHUI("安徽",7),
    FUJIAN("福建",8),
    GUANGDONG("广东",9),
    GUANGXI("广西",10),
    GUIZHOU("贵州",11),
    GANSU("甘肃",12),
    HAINAN("海南",13),
    HEBEI("河北",14),
    HENAN("河南",15),
    HEILONGJIANG("黑龙江",16),
    HUBEI("湖北",17),
    JILIN("吉林",18),
    JIANGSU("江苏",19),
    JIANGXI("江西",20),
    LIAONING("辽宁",21),
    NEIMENGGU("内蒙古",22),
    NINGXIA("宁夏",23),
    QINGHAI("青海",24),
    SHANXIQIN("陕西",25),
    SHANXIJIN("山西",26),
    SHANDONG("山东",27),
    SICHUANG("四川",28),
    TAIWAN("台湾",29),
    XIZANG("西藏",30),
    YUNNAN("云南",31),
    ZHEJIANG("浙江",32);

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
