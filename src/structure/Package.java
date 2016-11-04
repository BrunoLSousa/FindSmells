/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

import java.util.HashMap;
import metrics.MetricPackage;

/**
 *
 * @author bruno
 */
public class Package extends Artifact{
    
    private HashMap<MetricPackage, Double> metrics;
    
    public Package(Integer id, String name, Project project, String source, String pack){
        super(id, name, project, source, pack);
        initializeMetrics();
    }
    
    public Package(String name, Project project, String source, String pack){
        super(name, project, source, pack);
        initializeMetrics();
    }
    
    private void initializeMetrics(){
        this.metrics = new HashMap<>();
        for(MetricPackage metric : MetricPackage.values()){
            this.metrics.put(metric, -1.0);
        }
    }
    
    public Double getValueMetric(MetricPackage metric){
        return this.metrics.get(metric);
    }

    @Override
    public void updateValueMetric(String metric, Double value) {
        this.metrics.remove(MetricPackage.valueOf(metric.toUpperCase()));
        this.metrics.put(MetricPackage.valueOf(metric.toUpperCase()), value);
    }
    
}
