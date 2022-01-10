package com.wty.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.SyncReadListener;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.read.listener.ReadListener;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class DemoDataListener extends SyncReadListener {

    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    /**
     * 缓存的数据
     */
    private List<DemoData> cachedDataList = new ArrayList<>(100000);


    public DemoDataListener() {
    }

    @Override
    public void invoke(Object object, AnalysisContext context) {
        cachedDataList.add((DemoData) object);
    }

    public List get() {
        return cachedDataList;
    }
}