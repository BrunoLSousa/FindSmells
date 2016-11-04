/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

import java.util.HashMap;
import metrics.MetricMethod;

/**
 *
 * @author bruno
 */
public class Method extends Artifact{
    
    private HashMap<MetricMethod, Double> metrics;
    
    public Method(Integer id, String name, Project project, String source, String pack){
        super(id, name, project, source, pack);
        initilizeMetrics();
    }
    
    public Method(String name, Project project, String source, String pack){
        super(name, project, source, pack);
        initilizeMetrics();
    }
    
    private void initilizeMetrics(){
        this.metrics = new HashMap<>();
        for(MetricMethod metric : MetricMethod.values()){
            this.metrics.put(metric, -1.0);
        }
    }
    
    public Double getValueMetric(MetricMethod metric){
        return this.metrics.get(metric);
    }

    @Override
    public void updateValueMetric(String metric, Double value) {
        this.metrics.remove(MetricMethod.valueOf(metric.toUpperCase()));
        this.metrics.put(MetricMethod.valueOf(metric.toUpperCase()), value);
    }
    
}
