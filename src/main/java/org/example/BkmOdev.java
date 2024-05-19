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
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.Scanner;
import javax.swing.SwingUtilities;

public class BkmOdev extends ApplicationFrame {

    public BkmOdev(String title) {
        super(title);
        XYSeriesCollection dataset = createDataset();
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Ağırlık Merkezi ve Kütle Konumları",
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
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private XYSeriesCollection createDataset() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Römorkun kısa kenar uzunluğunu girin (m): ");
        double kisaKenar = scanner.nextDouble();

        System.out.println("Römorkun uzun kenar uzunluğunu girin (m): ");
        double uzunKenar = scanner.nextDouble();

        System.out.println("Römorkun yüksekliğini girin (m): ");
        double yukseklik = scanner.nextDouble();

        System.out.println("Yük sayısını girin: ");
        int numLoads = scanner.nextInt();

        double[] masses = new double[numLoads];
        double[][] coordinates = new double[numLoads][3];

        for (int i = 0; i < numLoads; i++) {
            System.out.println("Yük " + (i + 1) + " kütlesini girin (kg): ");
            masses[i] = scanner.nextDouble();

            System.out.println("Yük " + (i + 1) + " x koordinatını girin (m): ");
            coordinates[i][0] = scanner.nextDouble();

            System.out.println("Yük " + (i + 1) + " y koordinatını girin (m): ");
            coordinates[i][1] = scanner.nextDouble();

            System.out.println("Yük " + (i + 1) + " z koordinatını girin (m): ");
            coordinates[i][2] = scanner.nextDouble();
        }

        double[] centerOfMass = calculateCenterOfMass(masses, coordinates);

        System.out.println("Ağırlık merkezi koordinatları: ");
        System.out.println("X: " + centerOfMass[0] + " metre");
        System.out.println("Y: " + centerOfMass[1] + " metre");
        System.out.println("Z: " + centerOfMass[2] + " metre");

        XYSeries massSeries = new XYSeries("Kütle Konumları");
        for (int i = 0; i < masses.length; i++) {
            massSeries.add(coordinates[i][0], coordinates[i][1]);
        }

        XYSeries centerOfMassSeries = new XYSeries("Ağırlık Merkezi");
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
            throw new IllegalArgumentException("Toplam kütle sıfır olamaz.");
        }

        double[] centerOfMass = {0, 0, 0};
        for (int i = 0; i < masses.length; i++) {
            for (int j = 0; j < 3; j++) {
                centerOfMass[j] += masses[i] * coordinates[i][j];
            }
        }

        for (int j = 0; j < 3; j++) {
            centerOfMass[j] /= totalMass;
        }

        return centerOfMass;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BkmOdev demo = new BkmOdev("Ağırlık Merkezi Demo");
            demo.pack();
            demo.setLocationRelativeTo(null); // Centers the frame on the screen
            demo.setVisible(true);
        });
    }
}
