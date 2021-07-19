package cn.edu.zucc.wyk31801026.enums;


/**
 * 省份的枚举类
 * @author WYK
 */
public enum  ProvinceChinese {

    BEIJING("Bei Jing", "北京"),
    SHANGHAI("Shang Hai","上海"),
    TIANJIN("Tian Jin","天津"),
    CHONGQING("Chong Qing","重庆"),
    XIANGGANG("Xiang Gang","香港"),
    AOMEN("Ao Men","澳门"),
    ANHUI("An Hui","安徽"),
    FUJIAN("Fu Jian","福建"),
    GUANGDONG("Guang Dong","广东"),
    GUANGXI("Guang Xi","广西"),
    GUIZHOU("Gui Zhou","贵州"),
    GANSU("Gan Su","甘肃"),
    HAINAN("Hai Nan","海南"),
    HEBEI("He Bei","河北"),
    HENAN("He Nan","河南"),
    HEILONGJIANG("Hei Long Jiang","黑龙江"),
    HUBEI("Hu Bei","湖北"),
    HUNAN("Hu Nan","湖南"),
    JILIN("Ji Lin","吉林"),
    JIANGSU("Jiang Su","江苏"),
    JIANGXI("Jiang Xi","江西"),
    LIAONING("Liao Ning","辽宁"),
    NEIMENGGU("Nei Meng Gu","内蒙古"),
    NINGXIA("Ning Xia","宁夏"),
    QINGHAI("Qing Hai","青海"),
    SHANXIQIN("Shaan Xi","陕西"),
    SHANXIJIN("Shan Xi","山西"),
    SHANDONG("Shan Dong","山东"),
    SICHUANG("Si Chuang","四川"),
    TAIWAN("Tai Wan","台湾"),
    XIZANG("Xi Zang","西藏"),
    XINJIANG("Xin Jiang","新疆"),
    YUNNAN("Yun Nan","云南"),
    ZHEJIANG("Zhe Jiang","浙江");

    private String name;
    private String chineseName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    ProvinceChinese(String name, String chineseName) {
        this.name = name;
        this.chineseName = chineseName;
    }


    public static String getNameByChineseName(String chineseName)
    {
        for (ProvinceChinese province:ProvinceChinese.values()){
            if (chineseName.equals((province.getChineseName()))){
                return province.getName();
            }
        }
        return null;
    }

    public static String getChineseNameByName(String name)
    {
        for (ProvinceChinese province:ProvinceChinese.values()){
            if (name.equals((province.getName()))){
                return province.getChineseName();
            }
        }
        return null;
    }
}
