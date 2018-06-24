package com.pawel14157.deltadroid;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.softmoore.android.graphlib.Graph;
import com.softmoore.android.graphlib.GraphView;
import java.util.ArrayList;
import java.util.List;

public class Chart extends AppCompatActivity {
    private double a;
    private double b;
    private double c;
    private Double x1;
    private Double x2;
    private double p;
    private double q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        Bundle extras = getIntent().getExtras();
        a = extras.getDouble("a");
        b = extras.getDouble("b");
        c = extras.getDouble("c");
        x1 = extras.getDouble("x1");
        x2 = extras.getDouble("x2");
        p = extras.getDouble("p");
        q = extras.getDouble("q");

        Graph graph = new Graph.Builder()
                .addFunction(x -> a*x*x+b*x+c, Color.RED)
                .build();
        GraphView graphView = findViewById(R.id.graph_view);

        graphView.setGraph(graph);


    }


    public void back(View view) {
        Intent intent = new Intent(getApplicationContext(), Resolve.class);
        startActivity(intent);
    }
}
