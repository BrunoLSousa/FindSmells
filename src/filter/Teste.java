/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import com.fathzer.soft.javaluator.BracketPair;
import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.Parameters;
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
    // Let's create a double evaluator that only support +,-,*,and / operators, with no constants,
    // and no functions. The default parenthesis will be allowed
    // First create empty evaluator parameters
    Parameters params = new Parameters();
    // Add the supported operators to these parameters
    params.add(DoubleEvaluator.PLUS);
    params.add(DoubleEvaluator.MINUS);
    params.add(DoubleEvaluator.MULTIPLY);
    params.add(DoubleEvaluator.DIVIDE);
    params.add(DoubleEvaluator.NEGATE);
    // Add the default expression brackets
    params.addExpressionBracket(BracketPair.PARENTHESES);
    // Create the restricted evaluator
    DoubleEvaluator evaluator = new DoubleEvaluator(params);
 
    // Let's try some expressions
    doIt(evaluator, "(3*-4)+2");
    doIt(evaluator, "3^2");
    doIt(evaluator, "ln(5)");
  }
 
  private static void doIt(DoubleEvaluator evaluator, String expression) {
    try {
      System.out.println (expression+" = "+evaluator.evaluate(expression));
    } catch (IllegalArgumentException e) {
      System.out.println (expression+" is an invalid expression");
    }
  }
    
}
