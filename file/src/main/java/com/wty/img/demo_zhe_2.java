package com.wty.img;

import com.google.common.collect.Lists;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnitSource;
import org.jfree.chart.axis.TickUnitSource;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.DefaultCategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class demo_zhe {

    public static String imagePath = "/Users/peter/IdeaProjects/my-project/JavaUp";

    public static void main(String[] args) {
        // 创建数据集
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(20, "数量", "PE");
        dataset.addValue(41, "数量", "STEEL");
        dataset.addValue(46, "数量", "CUTTER");

        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
        dataset2.addValue(497686728, "长度和", "PE");
        dataset2.addValue(497570126, "长度和", "STEEL");
        dataset2.addValue(497470126, "长度和", "CUTTER");
        XYSeries seriesLeft = new XYSeries("Left Y-Axis Series");
        // Add data points to the left Y-axis series here...
        seriesLeft.add(1.0, 10.0);
        seriesLeft.add(2.0, 20.0);
        seriesLeft.add(3.0, 30.0);
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        xySeriesCollection.addSeries(seriesLeft);

        XYSeries seriesRight = new XYSeries("Right Y-Axis Series");
        // Add data points to the right Y-axis series here...
        seriesRight.add(1.0, 220.0);
        seriesRight.add(2.0, 240.0);
        seriesRight.add(3.0, 260.0);
        XYSeriesCollection xySeriesCollection1 = new XYSeriesCollection();
        xySeriesCollection1.addSeries(seriesRight);
//        JFreeChart lineChart = StackedBarChartAction.createLineChart(xySeriesCollection, xySeriesCollection1);
//        extracted("_多条2折线图", lineChart);

        // 创建图表
        JFreeChart chart = ChartFactory.createLineChart(
                "低压燃气管道", // 图表标题
                "材质", // 横轴标签
                "", // 纵轴标签，在后面创建纵轴标签
                dataset // 数据集

        );

        // 设置背景颜色
        chart.setBackgroundPaint(Color.white);

        // 获取绘图区域对象
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        // 设置Y1轴
        NumberAxis axis1 = (NumberAxis) plot.getRangeAxis();
        axis1.setAutoRangeIncludesZero(false);
        axis1.setLabel("数量");
        axis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // 创建第二个Y轴，使用和第一个Y轴相同的X轴
        NumberAxis axis2 = new NumberAxis("长度和");
        axis2.setAutoRangeIncludesZero(false);

        // 将第二个Y轴添加到图表中
        plot.setRangeAxis(1, axis2);
        plot.setDataset(1, dataset2);
        plot.mapDatasetToRangeAxis(1, 1);

        //设置折线图拐角上的正方形
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();

        DefaultCategoryItemRenderer renderer1 = new DefaultCategoryItemRenderer();
        renderer1.setSeriesPaint(0, Color.BLUE);
        renderer1.setDefaultOutlinePaint(Color.BLUE);
        renderer1.setUseOutlinePaint(true);
        renderer1.setUseFillPaint(true);
        renderer1.setDrawOutlines(true);
        plot.setRenderer(1, renderer1);

        Lists.newArrayList(renderer, renderer1).forEach(rendererFont -> {
            //创建一个正方形
            Rectangle shape=new Rectangle(4,4);
            rendererFont.setSeriesShape(0, shape);
            rendererFont.setSeriesShapesVisible(0, true);
            rendererFont.setSeriesShapesVisible(1, true);
            rendererFont.setSeriesStroke(0, new BasicStroke(3F));
            //在折点新鲜事数值
            rendererFont.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
            rendererFont.setDefaultItemLabelsVisible(true);
            rendererFont.setDefaultPositiveItemLabelPosition(
                    new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
            rendererFont.setDefaultItemLabelFont(new Font("宋体", Font.PLAIN, 20));
        });

        /** ---------------------- 中文乱码问题处理 Start ------------------------------- */
        CategoryAxis domainAxis = plot.getDomainAxis();     //水平底部列表
        domainAxis.setLabelFont(new Font("宋体", Font.BOLD, 14));     //水平底部标题
        domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12)); //垂直标题

        ValueAxis rangeAxis = plot.getRangeAxis();//获取柱状
        rangeAxis.setLabelFont(new Font("宋体", Font.BOLD, 15));
        chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 15));
        chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));//设置标题字体
        /** ---------------------- 中文乱码问题处理 End ------------------------------- */


        extracted("_多条折线图", chart);
    }

    private static void extracted(String _多条折线图, JFreeChart chart) {
        File p = new File(imagePath);
        if (!p.exists()) {
            p.mkdirs();
        }
        String imageName = System.currentTimeMillis() + _多条折线图 + ".png";
        File file = new File(p.getPath() + "/" + imageName);
        try {
            if (file.exists()) {
                file.delete();
            }
            ChartUtils.saveChartAsPNG(file, chart, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}