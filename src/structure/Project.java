/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

import java.util.HashMap;
import metrics.MetricProject;

/**
 *
 * @author bruno
 */
public class Project {

    private Integer id;
    private String name;
    private HashMap<MetricProject, Double> metricsProject;

    public Project(Integer id, String name) {
        this.id = id;
        this.name = name;
        initializeMetricsProject();
    }

    public Project(String name) {
        this.name = name;
        initializeMetricsProject();
    }

    private void initializeMetricsProject() {
        this.metricsProject = new HashMap<>();
        for (MetricProject metric : MetricProject.values()) {
            this.metricsProject.put(metric, -1.0);
        }
    }

    public void updateMetric(MetricProject metric, Double value) {
        this.metricsProject.remove(metric);
        this.metricsProject.put(metric, value);
    }

    public void setMetricProject(HashMap<MetricProject, Double> metricsProject) {
        this.metricsProject = metricsProject;
    }

    public Double getMetricProject(MetricProject metric) {
        return this.metricsProject.get(metric);
    }

    public String getName() {
        return this.name;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
