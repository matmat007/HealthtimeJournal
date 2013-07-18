package com.healthtimejournal;

import java.util.Arrays;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.androidplot.series.XYSeries;
import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.healthtimejournal.androidplot.MultitouchPlot;

public class WeightGraphActivity extends Activity {
 
    public MultitouchPlot weightPlot;
 
    @Override
    public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weight_graph_page);
        
        weightPlot = (MultitouchPlot) findViewById(R.id.weightXyPlot);

        // Create two arrays of y-values to plot:
        Number[] series1Numbers = {1, 8, 5, 2, 7, 4};
		Number[] series2Numbers = {4, 6, 3, 8, 2, 10};

        // Turn the above arrays into XYSeries:
        XYSeries series1 = new SimpleXYSeries(
                Arrays.asList(series1Numbers),          // SimpleXYSeries takes a List so turn our array into a List
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, // Y_VALS_ONLY means use the element index as the x value
                "Obw—d brzucha");                             // Set the display title of the series

		// Same as above, for series2
        XYSeries series2 = new SimpleXYSeries(Arrays.asList(series2Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,
                "Series2");

        // Create a formatter to use for drawing a series using LineAndPointRenderer:
        LineAndPointFormatter series1Format = new LineAndPointFormatter(
                Color.rgb(0, 200, 0),                   // line color
                Color.rgb(0, 100, 0),                   // point color
                Color.rgb(150, 190, 150));              // fill color (optional)

        // Add series1 to the xyplot:
        weightPlot.addSeries(series1, series1Format);

        // Same as above, with series2:
        weightPlot.addSeries(series2, new LineAndPointFormatter(Color.rgb(0, 0, 200), Color.rgb(0, 0, 100),
                Color.rgb(150, 150, 190)));

        // Reduce the number of range labels
        weightPlot.setTicksPerRangeLabel(3);

        // By default, AndroidPlot displays developer guides to aid in laying out your plot.
        // To get rid of them call disableAllMarkup():
        weightPlot.disableAllMarkup();

        weightPlot.setRangeBoundaries(0, 10, BoundaryMode.FIXED);
		weightPlot.setDomainBoundaries(0, 2.2, BoundaryMode.FIXED);
    }

}
