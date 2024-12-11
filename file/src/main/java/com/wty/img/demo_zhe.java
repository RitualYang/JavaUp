package com.wty.img;

import com.google.common.collect.Lists;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
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

    public static void main(String[] args) throws ParseException {

        String inputDateStr = "2023-08-10";
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date inputDate = inputDateFormat.parse(inputDateStr);

        SimpleDateFormat outputDateFormat = new SimpleDateFormat("MM-dd");
        String outputDateStr = outputDateFormat.format(inputDate);

        System.out.println("Original Date: " + inputDateStr);
        System.out.println("Formatted Date: " + outputDateStr);
        // 创建数据集
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(20, "数量", "08-01");
        dataset.addValue(41, "数量", "08-02");
        dataset.addValue(46, "数量", "08-03");
        dataset.addValue(46, "数量", "08-04");
        dataset.addValue(46, "数量", "08-05");
        dataset.addValue(46, "数量", "08-06");
        dataset.addValue(46, "数量", "08-07");

        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
        dataset2.addValue(497686728, "长度和", "08-01");
        dataset2.addValue(497770126, "长度和", "08-02");
        dataset2.addValue(497870126, "长度和", "08-03");
        dataset2.addValue(498070126, "长度和", "08-04");
        dataset2.addValue(498170126, "长度和", "08-05");
        dataset2.addValue(498270126, "长度和", "08-06");
        dataset2.addValue(498370126, "长度和", "08-07");

        DefaultCategoryDataset dataset3 = new DefaultCategoryDataset();
        dataset3.addValue(497686728, "长度和2", "08-01");
        dataset3.addValue(487770126, "长度和2", "08-02");
        dataset3.addValue(477870126, "长度和2", "08-03");
        dataset3.addValue(468070126, "长度和2", "08-04");
        dataset3.addValue(458170126, "长度和2", "08-05");
        dataset3.addValue(448270126, "长度和2", "08-06");
        dataset3.addValue(438370126, "长度和2", "08-07");

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
        plot.setBackgroundPaint(Color.white);

        plot.setDomainGridlinePaint(Color.GRAY);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.GRAY);
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
        plot.setDataset(2, dataset3);
        plot.mapDatasetToRangeAxis(1, 1);
        plot.mapDatasetToRangeAxis(2, 1);


        //设置折线图拐角上的正方形
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setDefaultOutlinePaint(Color.BLUE);
        DefaultCategoryItemRenderer renderer1 = new DefaultCategoryItemRenderer();
        renderer1.setSeriesPaint(0, Color.RED);
        renderer1.setDefaultOutlinePaint(Color.RED);
        renderer1.setUseOutlinePaint(true);
        renderer1.setUseFillPaint(true);
        renderer1.setDrawOutlines(true);
        plot.setRenderer(1, renderer1);

        Lists.newArrayList(renderer, renderer1).forEach(rendererFont -> {
            //创建一个正方形
            Rectangle shape=new Rectangle(2,2);
            rendererFont.setSeriesShape(0, shape);
            rendererFont.setSeriesShapesVisible(0, true);
            rendererFont.setSeriesShapesVisible(1, true);
            rendererFont.setSeriesStroke(0, new BasicStroke(2F));
            //在折点新鲜事数值
            rendererFont.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
            rendererFont.setDefaultItemLabelsVisible(false);
            rendererFont.setDefaultPositiveItemLabelPosition(
                    new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
            rendererFont.setDefaultItemLabelFont(new Font("宋体", Font.PLAIN, 8));
        });

        /** ---------------------- 中文乱码问题处理 Start ------------------------------- */
        CategoryAxis domainAxis = plot.getDomainAxis();     //水平底部列表
        domainAxis.setLabelFont(new Font("宋体", Font.BOLD, 9));     //水平底部标题
        domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 8)); //垂直标题


        ValueAxis rangeAxis = plot.getRangeAxis();//获取柱状
        rangeAxis.setLabelFont(new Font("宋体", Font.BOLD, 8));
        chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 8));
        chart.getTitle().setFont(new Font("宋体", Font.BOLD, 9));//设置标题字体
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
            ChartUtils.saveChartAsPNG(file, chart, 480, 360);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}