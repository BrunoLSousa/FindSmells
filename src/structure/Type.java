/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

import java.util.HashMap;
import metrics.MetricClass;

/**
 *
 * @author bruno
 */
public class Type extends Artifact{
    
    private HashMap<MetricClass, Double> metrics;
    
    public Type(Integer id, String name, Project project, String source, String pack){
        super(id, name, project, source, pack);
        initilizeMetrics();
    }
    
    public Type(String name, Project project, String source, String pack){
        super(name, project, source, pack);
        initilizeMetrics();
    }
    
    private void initilizeMetrics(){
        this.metrics = new HashMap<>();
        for(MetricClass metric : MetricClass.values()){
            this.metrics.put(metric, -1.0);
        }
    }

    @Override
    public void updateValueMetric(String metric, Double value) {
        this.metrics.remove(MetricClass.valueOf(metric.toUpperCase()));
        this.metrics.put(MetricClass.valueOf(metric.toUpperCase()), value);
    }
    
    public Double getValueMetric(MetricClass metric){
        return this.metrics.get(metric);
    }
    
}
