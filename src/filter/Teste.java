/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import metrics.Granulatiry;
import metrics.MetricMethod;
import metrics.MetricPackage;
import structure.DetectionStrategy;
import structure.Method;
import structure.Project;
import structure.dao.LogDAO;
import structure.log.Log;

/**
 *
 * @author bruno
 */
public class Teste {
    
    public static void main(String[] args) {
        
        DetectionStrategy detection = new DetectionStrategy(1, "God Class", Granulatiry.Type, "(wmc > 30 AND nof > 5 AND nom > 2) OR (lcom >= 0.265 AND nof > 3)");
        Project project = new Project(1, "Webmail");
        
        List<Log> logs = (List<Log>) new LogDAO().selectAll();
        
//        for(Log l : logs){
//            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            System.out.printf("%-10s  %-10s  %-20s %-20s  %-10s  %-20s %-10s  %-10s\n", l.getId(), l.getArtifact().getProject().getName(), l.getArtifact().getName(), l.getArtifact().getPack(), l.getMetric(), l.getSubject(), l.getMessage(), dateFormat.format(l.getDate()));
//        }
        List<String> strs = new ArrayList<>();
        strs.add("Ovo");
        strs.add("Abacaxi");
        strs.add("Maçã");
        System.out.println(strs.toString());
        
//        log.printLog();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
//        Collections.sort(strs);
//        System.out.println(strs.toString());
        System.out.println(cal.getTime());
        
        
        
        
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
