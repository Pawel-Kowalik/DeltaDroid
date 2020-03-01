package com.pawel14157.deltadroid.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.pawel14157.deltadroid.R
import com.softmoore.android.graphlib.Graph
import com.softmoore.android.graphlib.GraphView

class ChartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)

        val extras = intent.extras
        val a = extras!!.getDouble("a")
        val b = extras.getDouble("b")
        val c = extras.getDouble("c")
        val x1 = extras.getDouble("x1")
        val x2 = extras.getDouble("x2")
        val x0 = extras.getDouble("x0")
        val p = extras.getDouble("p")
        val q = extras.getDouble("q")

        val graph = Graph.Builder()
                .addFunction({ x -> a * x * x + b * x + c }, Color.RED)
                .build()
        val graphView = findViewById<GraphView>(R.id.graph_view)
        graphView.setGraph(graph)
    }

    fun back(view: View) {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }
}
