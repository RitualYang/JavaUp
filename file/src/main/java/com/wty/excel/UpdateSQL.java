package com.wty.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.util.FileUtils;

import com.alibaba.excel.util.StringUtils;
import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wty
 * @date 2022/1/10 16:27
 * 描述:
 */
public class UpdateSQL {

    public static void main(String[] args) throws IOException {
        //String fileName = "/Users/peter/Downloads/worldCupRobots.xlsx";
        String fileName = "/Users/peter/Downloads/RobotsDataFootballRound3.1.xlsx";
        DemoDataListener demoDataListener = new DemoDataListener();
        EasyExcel.read(new FileInputStream(fileName), DemoData.class, demoDataListener).headRowNumber(0).sheet(1).doRead();
        List<DemoData> list = demoDataListener.get();
//        String formet = "INSERT INTO phemex_activity.t_competition_members (user_id, competition_id, team_id, origin_team_id, user_nick_name, trade_volume_ev, pnl_ev ) VALUES ( %s, 25, %s, %s , '%s', %s,%s);";
        String formet = "update `phemex_activity`.t_competition_members set trade_volume_ev = %s, pnl_ev = %s where competition_id = 25 and user_id = %s;";
        for (DemoData  demoData: list) {
            if ("userId".equals(demoData.getUserId()) || "0".equals(demoData.getPnlEv()) || "0".equals(demoData.getTradeVolumeEv())) {
                continue;
            }
//            System.out.println(String.format(formet, demoData.getUserId(), demoData.getTeamId(),demoData.getTeamId(),demoData.getUserNickName(),
//                   new BigDecimal(demoData.getTradeVolumeEv()).multiply(BigDecimal.valueOf(10000)).setScale(0),
//                    new BigDecimal(demoData.getPnlEv()).multiply(BigDecimal.valueOf(10000)).setScale(0)
//            ));
            System.out.println(String.format(formet, new BigDecimal(demoData.getTradeVolumeEv()).multiply(BigDecimal.valueOf(10000)).setScale(0),new BigDecimal(demoData.getPnlEv()).multiply(BigDecimal.valueOf(10000)).setScale(0), demoData.getUserId()));
        }
//        String format = "UPDATE phemex_activity.t_competition_teams  SET member_count = member_count + %d WHERE id = %d;";
//        for (Integer integer : hashMap.keySet()) {
//            final Integer integer1 = hashMap.get(integer);
//            System.out.println(String.format(format, integer1, integer));
//        }
    }
}
