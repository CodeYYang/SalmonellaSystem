package cn.edu.zucc.wyk31801026.utils;

import java.util.Random;
public class invitationCode {
    /**
     * 随机7位邀请码
     * @return
     */
    public static String genSixToSixteenPsw() {
        String val = "";
        Random random = new Random();
        // 如果想要7-16位: random.nextInt(9) + 7;
        int numbers = 7;
        for (int i = 0; i < numbers; i++) {
            // 输出字母还是数字
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 字符串
            if ("char".equalsIgnoreCase(charOrNum)) {
                //取得大写字母还是小写字母
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                // 数字
                val += String.valueOf(random.nextInt(10));
            }
        }
        return  val.toLowerCase();
    }

}
