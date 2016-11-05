/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import metrics.Granulatiry;
import metrics.MetricPackage;
import metrics.MetricProject;
import structure.DetectionStrategy;
import structure.Project;
import structure.Type;
import structure.dao.TypeDAO;

/**
 *
 * @author bruno
 */
public class Teste {
    
    public static void main(String[] args) {
        
        DetectionStrategy detection = new DetectionStrategy(1, "God Class", Granulatiry.Type, "(wmc > 30 AND nof > 5 AND nom > 2) OR (lcom >= 0.265 AND nof > 3)");
        Project project = new Project(1, "Webmail");
        
        List<Type> types = (List<Type>) new TypeDAO().applyDetectionStrategy(detection, project);
        
        for(Type t : types){
            System.out.printf("%-30s  %-30s  %-30s\n", t.getName(), t.getSource(), t.getPack());
        }
        
//        List<MetricPackage> list = (List<MetricPackage>) teste();
//        
//        for(MetricPackage m : list){
//            System.out.println(m.toString());
//        }
        
        
//        for(MetricPackage metric : MetricPackage.values()){
//            System.out.println(metric.toString());
//        }
        
    }
    
    public static Object teste(){
        List<MetricPackage> list = new ArrayList<>();
        list.addAll(Arrays.asList(MetricPackage.values()));
        return list;
    }
    
}
