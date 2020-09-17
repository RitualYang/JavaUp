import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO
 *
 * @author wty
 * @Date 2020/9/12 18:13
 */
public class MyUtils {
    public static String getGender(){
        String[] str = {"男","女"};
        double random = Math.random() * 100;
        return str[(int) (random%2)];
    }
    /**
     * 随机生成姓名
     */
    public static String getName() {
        String sXing ="赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许"
                + "何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章"
                + "云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳"
                +"酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常"
                + "乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹"
                + "姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞"
                + "熊纪舒屈项祝董粱杜阮蓝闵席季麻强贾路娄危"
                + "江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍"
                + "虞万支柯昝管卢莫经房裘缪干解应宗丁宣贲邓"
                + "郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁 ";
        String[] sMing = {"小","明","丽","红","强","小丽","炎","燕","晓燕","洋","阳","庄","小庄","晓阳","小洋","山","进","明"
                ,"小文","文","文丽","晓歌","小歌","歌","晓波","小波","小亮","晓亮","亮","品华","霞","红霞","晓炎","晓强","德华","华","城","诚","小明"
        };
        char[] c = null;
        c = sXing.toCharArray();
        //姓 数组 长度
        int ixing = c.length;
        //名 数组 长度
        int iming = sMing.length;
        double d = 0;
        d = Math.random();
        //姓 数组中的随机数
        int r = (int)(d * 100000) % ixing;
        //名 数组中的随机数
        int r1 = (int)(d * 100000) % iming;
        //组装姓名
        String s = String.valueOf(c[r]) + sMing[r1];
        return s;
    }

    /**
     * 获取随机日期
     *
     * @param beginDate
     *            起始日期，格式为：yyyy-MM-dd
     * @param endDate
     *            结束日期，格式为：yyyy-MM-dd
     * @return
     */

    private static Date randomDate(String beginDate, String endDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);// 构造开始日期
            Date end = format.parse(endDate);// 构造结束日期
            // getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
            if (start.getTime() >= end.getTime()) {
                return null;
            }
            long date = random(start.getTime(), end.getTime());

            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        // 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }
}
