package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.ui.ApplicationFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.SwingUtilities;

public class CenterOfMass extends ApplicationFrame {

    public CenterOfMass(String title) {
        super(title);
        XYSeriesCollection dataset = createDataset();
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Center of Mass and Mass Positions",
                "X",
                "Y",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(false, true);
        plot.setRenderer(renderer);

        // Customize the renderer to show large circles for mass positions
        Shape circle = new Ellipse2D.Double(-5, -5, 10, 10);
        renderer.setSeriesShape(0, circle);
        renderer.setSeriesPaint(0, Color.BLUE);

        // Customize the renderer to show red square for center of mass
        Shape square = new Ellipse2D.Double(-5, -5, 10, 10);
        renderer.setSeriesShape(1, square);
        renderer.setSeriesPaint(1, Color.RED);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private XYSeriesCollection createDataset() {
        double[] masses = {50, 30, 20};
        double[][] coordinates = {
                {2, 3},
                {5, 6},
                {8, 9}
        };

        double[] centerOfMass = calculateCenterOfMass(masses, coordinates);

        XYSeries massSeries = new XYSeries("Mass Positions");
        for (int i = 0; i < masses.length; i++) {
            massSeries.add(coordinates[i][0], coordinates[i][1]);
        }

        XYSeries centerOfMassSeries = new XYSeries("Center of Mass");
        centerOfMassSeries.add(centerOfMass[0], centerOfMass[1]);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(massSeries);
        dataset.addSeries(centerOfMassSeries);

        return dataset;
    }

    private double[] calculateCenterOfMass(double[] masses, double[][] coordinates) {
        double totalMass = 0;
        for (double mass : masses) {
            totalMass += mass;
        }

        if (totalMass == 0) {
            throw new IllegalArgumentException("Total mass cannot be zero.");
        }

        double[] centerOfMass = {0, 0};
        for (int i = 0; i < masses.length; i++) {
            for (int j = 0; j < 2; j++) {
                centerOfMass[j] += masses[i] * coordinates[i][j];
            }
        }

        for (int j = 0; j < 2; j++) {
            centerOfMass[j] /= totalMass;
        }

        return centerOfMass;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CenterOfMass demo = new CenterOfMass("Center of Mass Demo");
            demo.pack();
            demo.setLocationRelativeTo(null); // Centers the frame on the screen
            demo.setVisible(true);
        });
    }
}
