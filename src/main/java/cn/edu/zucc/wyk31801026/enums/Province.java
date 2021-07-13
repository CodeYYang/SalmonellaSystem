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
    GUANGDONG("广东",8),
    GUANGXI("广西",9),
    GUIZHOU("贵州",10),
    GANSU("甘肃",11),
    HAINAN("海南",12),
    HEBEI("河北",13),
    HENAN("河南",14),
    HEILONGJIANG("黑龙江",15),
    HUBEI("湖北",16),
    JILIN("吉林",17),
    JIANGSU("江苏",18),
    JIANGXI("江西",19),
    LIAONING("辽宁",20),
    NEIMENGGU("内蒙古",21),
    NINGXIA("宁夏",22),
    QINGHAI("青海",23),
    SHANXIQIN("陕西",24),
    SHANXIJIN("山西",25),
    SHANDONG("山东",26),
    SICHUANG("四川",27),
    TAIWAN("台湾",28),
    XIZANG("西藏",29),
    YUNNAN("云南",30),
    ZHEJIANG("浙江",31);

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
