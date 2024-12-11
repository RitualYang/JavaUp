package com.wty.img;

import java.awt.Color;
import java.awt.Font;
import lombok.AllArgsConstructor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeriesCollection;
/**
 * 柱状图基类
 * @author mln-wyf
 */
@AllArgsConstructor
public class StackedBarChartAction {


    /**
     * 双Y轴折线图
     *
     * @param datasetLeft  Y轴左边的数据集
     * @param datasetRight Y轴右边的数据集
     * @return
     */
    public static JFreeChart createLineChart(XYSeriesCollection datasetLeft,
            XYSeriesCollection datasetRight) {
        // 设置字体样式
        Font fs = new Font("微软雅黑", Font.BOLD, 12);
        Font f = new Font("微软雅黑", Font.PLAIN, 12);

        JFreeChart chart = ChartFactory.createXYLineChart("测试",
                "X坐标",
                "Y坐标",
                datasetLeft,
                PlotOrientation.VERTICAL,
                true,
                true,
                true);
        XYPlot xyplot = (XYPlot) chart.getPlot();
        xyplot.setDomainPannable(true);
        xyplot.setRangePannable(true);

        // 设置X轴显示方式
        //NumberAxis domainAxis = new NumberAxis(this.x);
        NumberAxis domainAxis = (NumberAxis) xyplot.getDomainAxis();
        domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits()); //设置x轴数字为整数
        domainAxis.setVisible(true);
        domainAxis.setAutoRangeIncludesZero(false);
        xyplot.setRangeAxis(0, domainAxis);

        // 左边y轴显示方式
        //NumberAxis numberaxis= (NumberAxis)plotxy.getRangeAxis();//y轴整数显示
        NumberAxis numberaxis = new NumberAxis("y1");
        numberaxis.setLabelFont(fs);
        numberaxis.setTickLabelFont(f);
        xyplot.setRangeAxis(0, numberaxis);

        // 右边y轴显示方式
        NumberAxis numberaxis2 = new NumberAxis("y2");
        numberaxis2.setLabelFont(fs);
        numberaxis2.setTickLabelFont(f);
        xyplot.setRangeAxis(1, numberaxis2);
        xyplot.setDataset(1, datasetRight);
        xyplot.mapDatasetToRangeAxis(1, 1);

        // 左边y轴
        XYItemRenderer renderer = xyplot.getRenderer();
        //xylineandshaperenderer1.setBaseToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());   //数据显示格式
        if (renderer instanceof XYLineAndShapeRenderer)  //显示数据
        {
            XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) renderer;
            xylineandshaperenderer.setDefaultShapesVisible(true);
            xylineandshaperenderer.setDefaultShapesFilled(true);
        }
        // 右边y轴
        XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer();
        renderer1.setDefaultShapesVisible(true);
        renderer1.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());  //数据显示格式
        renderer1.setSeriesPaint(0, Color.green);
        renderer1.setDefaultOutlinePaint(Color.green);
        renderer1.setUseOutlinePaint(true);
        renderer1.setUseFillPaint(true);
        renderer1.setDrawOutlines(true);
        xyplot.setRenderer(1, renderer1);

        xyplot.setBackgroundPaint(new Color(238, 244, 255));//设置图表的颜色        
        xyplot.setDomainGridlinePaint(Color.lightGray);// 设置垂直网格线的颜色
        xyplot.setRangeGridlinePaint(Color.lightGray);// 设置水平网格线的颜色
        xyplot.setDomainGridlinesVisible(true); // 设置垂直网格线是否显示
        xyplot.setRangeGridlinesVisible(true); // 设置水平网格线是否显示

        return chart;
    }

}