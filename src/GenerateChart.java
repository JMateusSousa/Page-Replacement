import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class GenerateChart extends ApplicationFrame {

    public GenerateChart(String title, List<ArrayList<ChartPoint>> chartPoints) {
        super(title);
        XYDataset dataset = createDataset(chartPoints);
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new java.awt.Dimension(700, 400));
        chartPanel.setMouseZoomable(true, false);
        setContentPane(chartPanel);
    }

    private static JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Número de acertos em substituição de páginas",
                "Número de frames",
                "Número de acertos",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(false);
        plot.setDomainGridlinesVisible(false);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Número de acertos dos algoritmos",
                        new Font("Serif", Font.BOLD, 18)
                )
        );

        return chart;
    }

    private static XYDataset createDataset(List<ArrayList<ChartPoint>> chartPoints) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries("FIFO");
        XYSeries series2 = new XYSeries("MRU");
        ArrayList<XYSeries> series = new ArrayList<>();
        series.add(series1);
        series.add(series2);
        int i = 0;
        for(ArrayList line: chartPoints){
            ArrayList<ChartPoint> line1 = line;
            for(ChartPoint item: line1){
                series.get(i).add(new XYDataItem(item.getNumFrames(), item.getNumHits()));
            }
            i++;
        }

        dataset.addSeries(series1);
        dataset.addSeries(series2);

        return dataset;

    }

    public static JPanel createDemoPanel(List<ArrayList<ChartPoint>>chartPoints) {
        JFreeChart chart = createChart(createDataset(chartPoints));
        return new ChartPanel(chart);
    }

}
