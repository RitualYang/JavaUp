package com.base.future;

import com.base.SmallTool;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;

/**
 * @author wty
 * @date 2022/3/23 22:30
 */
public class CompletableFutureMain {

    public static void main(String[] args) {
        SmallTool.printTimeAndThread("第一步");
        SmallTool.printTimeAndThread("第二步");
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("第三步");
            SmallTool.sleepMillis(100);
            return "第五步";
        });

        SmallTool.printTimeAndThread("第四步");
        SmallTool.printTimeAndThread(stringCompletableFuture.join());
    }

}
