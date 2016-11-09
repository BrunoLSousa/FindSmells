/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

import java.util.HashMap;
import metrics.MetricType;

/**
 *
 * @author bruno
 */
public class Type extends Artifact{
    
    private String source;
    private HashMap<MetricType, Double> metrics;
    
    public Type(Integer id, String name, Project project, String source, String pack){
        super(id, name, project, pack);
        this.source = source;
        initilizeMetrics();
    }
    
    public Type(String name, Project project, String source, String pack){
        super(name, project, pack);
        this.source = source;
        initilizeMetrics();
    }
    
    private void initilizeMetrics(){
        this.metrics = new HashMap<>();
        for(MetricType metric : MetricType.values()){
            this.metrics.put(metric, -1.0);
        }
    }

    @Override
    public void updateValueMetric(String metric, Double value) {
        this.metrics.remove(MetricType.valueOf(metric.toUpperCase()));
        this.metrics.put(MetricType.valueOf(metric.toUpperCase()), value);
    }
    
    public Double getValueMetric(MetricType metric){
        return this.metrics.get(metric);
    }
    
    public String getSource(){
        return this.source;
    }
    
}
