package com.wty.img;

/**
 * @author tqf
 * @Description
 * @Version 1.0
 * @since 2022-06-06 15:32
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.CategoryTableXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class JfreeChartUtils {
    // 参考文章 https://www.jianshu.com/p/6c4f3832c396
    /**
     * 生成的图片存放地址
     */
    public static String imagePath = "/Users/peter/IdeaProjects/my-project/JavaUp";

    public static void main(String[] args) throws Exception {
//        testCreateManyLineChart();
        testCreateManyXYLineChart();
    }

    public static void testCreateManyXYLineChart(){

        XYSeries seriesLeft = new XYSeries("Left Y-Axis Series");
        // Add data points to the left Y-axis series here...
        seriesLeft.add(1.0, 10.0);
        seriesLeft.add(2.0, 20.0);
        seriesLeft.add(3.0, 30.0);

        XYSeries seriesRight = new XYSeries("Right Y-Axis Series");
        // Add data points to the right Y-axis series here...
        seriesRight.add(1.0, 220.0);
        seriesRight.add(2.0, 240.0);
        seriesRight.add(3.0, 260.0);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(seriesLeft);
        dataset.addSeries(seriesRight);
        JFreeChart chart = createManyXyChart(dataset);
        //在D盘目录下生成图片
        File p = new File(imagePath);
        if (!p.exists()) {
            p.mkdirs();
        }
        String imageName = System.currentTimeMillis() + "_多条折线图" + ".jpeg";
        File file = new File(p.getPath() + "/" + imageName);
        try {
            if(file.exists()) {
                file.delete();
            }
            ChartUtils.saveChartAsJPEG(file, chart, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void testCreateManyLineChart(){
        String[] title = {"成功量","失败量"};
        String[] xValue = {"2021-01","2021-02","2021-03","2021-04","2021-05"};
        // 绘图数据集
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        Random r = new Random();
        for (int i =0;i<title.length;i++) {
            for (int j=0;j<xValue.length;j++) {
                if ("成功量".equals(title[i])) {
                    int value = r.nextInt(10) * 2;
                    dataSet.setValue(value,title[i],xValue[j]);
                } else {
                    // 生成[0,10]区间的整数
                    int value = r.nextInt(10) * 2;
                    dataSet.setValue(value, title[i], xValue[j]);
                }
            }
        }
        JFreeChart chart = createManyLineChart(dataSet);
        //在D盘目录下生成图片
        File p = new File(imagePath);
        if (!p.exists()) {
            p.mkdirs();
        }
        String imageName = System.currentTimeMillis() + "_多条折线图" + ".jpeg";
        File file = new File(p.getPath() + "/" + imageName);
        try {
            if(file.exists()) {
                file.delete();
            }
            ChartUtils.saveChartAsJPEG(file, chart, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 生成折线图 多条
     * @return
     */
    public static JFreeChart createManyLineChart(DefaultCategoryDataset dataSet){
        //如果把createLineChart改为createLineChart3D就变为了3D效果的折线图
        JFreeChart  chart = ChartFactory.createLineChart("图表标题", "X轴标题", "Y轴标题", dataSet,
                PlotOrientation.VERTICAL, // 绘制方向
                true, // 显示图例
                true, // 采用标准生成器
                false // 是否生成超链接
        );
        //如 果不使用Font,中文将显示不出来
        Font font = new Font("新宋体", Font.BOLD, 15);

        chart.getTitle().setFont(font); // 设置标题字体
        chart.getLegend().setItemFont(font);// 设置图例类别字体
        // chart.setBackgroundPaint();// 设置背景色
        //获取绘图区对象
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.LIGHT_GRAY); // 设置绘图区背景色
        plot.setRangeGridlinePaint(Color.gray); // 设置水平方向背景线颜色
        // 设置背景透明度
        plot.setBackgroundAlpha(0.1f);
        // 设置网格横线颜色
        plot.setRangeGridlinePaint(Color.gray);
        // 设置网格横线大小
        plot.setDomainGridlineStroke(new BasicStroke(0.2F));
        plot.setRangeGridlineStroke(new BasicStroke(0.2F));
        plot.setRangeGridlinesVisible(true);// 设置是否显示水平方向背景线,默认值为true
        plot.setDomainGridlinePaint(Color.WHITE); // 设置垂直方向背景线颜色
        plot.setDomainGridlinesVisible(true); // 设置是否显示垂直方向背景线,默认值为false

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setLabelFont(font); // 设置横轴字体
        domainAxis.setTickLabelFont(font);// 设置坐标轴标尺值字体
        domainAxis.setLowerMargin(0.01);// 左边距 边框距离
        domainAxis.setUpperMargin(0.06);// 右边距 边框距离,防止最后边的一个数据靠近了坐标轴。
        domainAxis.setMaximumCategoryLabelLines(2);

        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setLabelFont(font);
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());//Y轴显示整数
        rangeAxis.setAutoRangeMinimumSize(1);   //最小跨度
        rangeAxis.setUpperMargin(0.18);//上边距,防止最大的一个数据靠近了坐标轴。
        rangeAxis.setLowerBound(0);   //最小值显示0
        rangeAxis.setAutoRange(false);   //不自动分配Y轴数据
        rangeAxis.setTickMarkStroke(new BasicStroke(1.6f));     // 设置坐标标记大小
        rangeAxis.setTickMarkPaint(Color.BLACK);     // 设置坐标标记颜色

        // 获取折线对象
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        BasicStroke realLine = new BasicStroke(1.8f); // 设置实线
        // 设置虚线
        float dashes[] = { 5.0f };
        BasicStroke brokenLine = new BasicStroke(2.2f, // 线条粗细
                BasicStroke.CAP_ROUND, // 端点风格
                BasicStroke.JOIN_ROUND, // 折点风格
                8f, dashes, 0.6f);
        for (int i = 0; i < dataSet.getRowCount(); i++) {
            if (i % 2 == 0) {
                renderer.setSeriesStroke(i, realLine); // 利用实线绘制
            } else {
                renderer.setSeriesStroke(i, brokenLine); // 利用虚线绘制
            }
            // 生成折线图上的数字
            //绘图区域(红色矩形框的部分)
            renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
            //设置图表上的数字可见
            renderer.setDefaultItemLabelsVisible(true);
            //设置图表上的数字字体
            renderer.setDefaultItemLabelFont(new Font("宋体",Font.BOLD,15));

            //设置折线图拐角上的正方形
            //创建一个正方形
            Rectangle  shape=new Rectangle(4,4);
            renderer.setSeriesShape(0, shape);
            //设置拐角上图形可见
            renderer.setSeriesShapesVisible(0, true);
        }

        plot.setNoDataMessage("无对应的数据，请重新查询。");
        plot.setNoDataMessageFont(font);//字体的大小
        plot.setNoDataMessagePaint(Color.RED);//字体颜色
        return chart;
    }

    public static JFreeChart createManyXyChart(XYDataset dataset) {
        JFreeChart scatterPlot = ChartFactory.createXYLineChart("图表标题", "X轴标题", "Y轴标题",
                dataset
                , PlotOrientation.VERTICAL,
                true,
                true,
                false);
        XYPlot plot = scatterPlot.getXYPlot();
        NumberAxis yAxisLeft = (NumberAxis) plot.getRangeAxis(); // Get the left Y axis
        NumberAxis yAxisRight = new NumberAxis("Y (Right)"); // Create a new right Y axis
        plot.setRangeAxis(1, yAxisRight); // Add the right Y axis to the plot (index 1)

        // Set the range (limits) for the left Y axis
        yAxisLeft.setRange(0, 100); // Example: range from 0 to 100

        // Set the range (limits) for the right Y axis
        yAxisRight.setRange(100, 300); // Example: range from 200 to 300

        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        BasicStroke realLine = new BasicStroke(1.8f); // 设置实线
        // 设置虚线
        float dashes[] = { 5.0f };
        BasicStroke brokenLine = new BasicStroke(2.2f, // 线条粗细
                BasicStroke.CAP_ROUND, // 端点风格
                BasicStroke.JOIN_ROUND, // 折点风格
                8f, dashes, 0.6f);
        for (int i = 0; i < dataset.getSeriesCount(); i++) {
            if (i % 2 == 0) {
                renderer.setSeriesStroke(i, realLine); // 利用实线绘制
            } else {
                renderer.setSeriesStroke(i, brokenLine); // 利用虚线绘制
            }
            // 生成折线图上的数字
            //绘图区域(红色矩形框的部分)
            renderer.setDefaultItemLabelGenerator(new StandardXYItemLabelGenerator());
            //设置图表上的数字可见
            renderer.setDefaultItemLabelsVisible(true);
            //设置图表上的数字字体
            renderer.setDefaultItemLabelFont(new Font("宋体",Font.BOLD,15));

            //设置折线图拐角上的正方形
            //创建一个正方形
            Rectangle  shape=new Rectangle(4,4);
            renderer.setSeriesShape(0, shape);
            //设置拐角上图形可见
            renderer.setSeriesShapesVisible(0, true);
        }

        return scatterPlot;
    }

    public static void testLine(){
        //如 果不使用Font,中文将显示不出来
        Font font = new Font("新宋体", Font.BOLD, 15);
        // 创建数据
        Map<String, Map<String, Double>> datas =new HashMap<>();
        String monthArray[] = {"一月","二月","三月","四月","五月","六月","七月","八月"};
        double value[]= {20,30,25,50,40,25,50,40};
        double value2[]= {10000,500000,550000,300000,600000,350000,200000,200000};


        for (int i=0; i<monthArray.length;i++) {
            Map<String, Double> map =new HashMap<>();
            map.put("故障数量", value[i]);
            map.put("headler", value2[i]);
            datas.put(monthArray[i],map);
        }

        JFreeChart chart = createLineChart("故障数量-时间曲线", datas, "月份", "故障次数（次）", font);
        //在D盘目录下生成图片
        File p = new File(imagePath);
        if (!p.exists()) {
            p.mkdirs();
        }
        String imageName = System.currentTimeMillis() + "_折线图" + ".jpeg";
        File file = new File(p.getPath() + "/" + imageName);
        try {
            if(file.exists()) {
                file.delete();
            }
            ChartUtils.saveChartAsJPEG(file, chart, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成折线图
     * @param title
     * @param data
     * @param type
     * @param unit
     * @param font
     * @return
     */
    public static JFreeChart createLineChart(String title, Map<String, Map<String, Double>> data, String type, String unit, Font font) {
        try {
            DefaultCategoryDataset ds = new DefaultCategoryDataset();
            Set<Map.Entry<String, Map<String, Double>>> set1 = data.entrySet();
            Iterator iterator1 = set1.iterator();
            Iterator iterator2;
            HashMap<String, Double> map;
            Set<Map.Entry<String, Double>> set2;
            Map.Entry entry1;
            Map.Entry entry2;
            while (iterator1.hasNext()) {
                entry1 = (Map.Entry) iterator1.next();
                map = (HashMap<String, Double>) entry1.getValue();
                set2 = map.entrySet();
                iterator2 = set2.iterator();
                while (iterator2.hasNext()) {
                    entry2 = (Map.Entry) iterator2.next();
                    ds.setValue(Double.parseDouble(entry2.getValue().toString()), entry2.getKey().toString(), entry1.getKey().toString());
                }
            }

            //创建折线图,折线图分水平显示和垂直显示两种
            // //2D折线图
            JFreeChart chart = ChartFactory.createLineChart(title, type, unit, ds, PlotOrientation.VERTICAL, true, true, true);

            //设置整个图片的标题字体
            chart.getTitle().setFont(font);

            //设置提示条字体
            font = new Font("宋体", Font.BOLD, 15);
            chart.getLegend().setItemFont(font);

            //得到绘图区
            CategoryPlot plot = (CategoryPlot) chart.getPlot();
            //得到绘图区的域轴(横轴),设置标签的字体
            plot.getDomainAxis().setLabelFont(font);

            // 设置背景透明度
            plot.setBackgroundAlpha(0.1f);
            // 设置网格横线颜色
            plot.setRangeGridlinePaint(Color.gray);
            // 设置网格横线大小
            plot.setDomainGridlineStroke(new BasicStroke(0.2F));
            plot.setRangeGridlineStroke(new BasicStroke(0.2F));

            //设置横轴标签项字体
            plot.getDomainAxis().setTickLabelFont(font);

            // 生成折线图上的数字
            //绘图区域(红色矩形框的部分)
            LineAndShapeRenderer lineAndShapeRenderer=(LineAndShapeRenderer)plot.getRenderer();
            lineAndShapeRenderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
            //设置图表上的数字可见
            lineAndShapeRenderer.setDefaultItemLabelsVisible(true);
            //设置图表上的数字字体
            lineAndShapeRenderer.setDefaultItemLabelFont(new Font("宋体",Font.BOLD,15));

            //设置折线图拐角上的正方形
            //创建一个正方形
            Rectangle  shape=new Rectangle(4,4);
            lineAndShapeRenderer.setSeriesShape(0, shape);
            //设置拐角上图形可见
            lineAndShapeRenderer.setSeriesShapesVisible(0, true);

            /*// 获取显示线条的对象
            LineAndShapeRenderer lasp = (LineAndShapeRenderer) plot.getRenderer();
            // 设置拐点是否可见/是否显示拐点
            lasp.setBaseShapesVisible(true);
            // 设置拐点不同用不同的形状
            lasp.setDrawOutlines(true);
            // 设置线条是否被显示填充颜色
            lasp.setUseFillPaint(true);
            // 设置拐点颜色
            lasp.setBaseFillPaint(Color.blue);//蓝色*/


            //设置范围轴(纵轴)字体
            font = new Font("宋体", Font.BOLD, 18);
            plot.getRangeAxis().setLabelFont(font);
//            plot.setForegroundAlpha(1.0f);
            return chart;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
