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
public enum MetricPackage {
    CA, CE, NOC, NOI, RMA, RMD, RMI;
    
    public static boolean contains(String metric){
        for(MetricPackage m : MetricPackage.values()){
            if(m.toString().toLowerCase().equals(metric)){
                return true;
            }
        }
        return false;
    }
    
}
