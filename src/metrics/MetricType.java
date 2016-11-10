/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrics;

/**
 *
 * @author bruno
 */
public enum MetricType {
    DIT, LCOM, NOF, NOM, NORM, NSC, NSF, NSM, SIX, WMC;
    
    public static boolean contains(String metric){
        for(MetricType m : MetricType.values()){
            if(m.toString().toLowerCase().equals(metric)){
                return true;
            }
        }
        return false;
    }
    
    public static boolean verifyInString(String metric){
        for(MetricType m : MetricType.values()){
            if(metric.contains(m.toString().toLowerCase())){
                return true;
            }
        }
        return false;
    }
    
}
