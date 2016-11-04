/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import metrics.MetricPackage;
import metrics.MetricProject;

/**
 *
 * @author bruno
 */
public class Teste {
    
    public static void main(String[] args) {
        
        List<MetricPackage> list = (List<MetricPackage>) teste();
        
        for(MetricPackage m : list){
            System.out.println(m.toString());
        }
        
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
