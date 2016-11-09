/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure.log;

import java.util.Calendar;
import java.util.Date;
import structure.Artifact;
import structure.Method;
import structure.dao.LogDAO;
import structure.Type;
import structure.Package;

/**
 *
 * @author bruno
 */
public class Log {

    private Integer id;
    private Artifact artifact;
    private String metric;
    protected String subject;
    protected String message;
    protected Date date;

    public Log(Artifact artifact, String metric) {
        this.artifact = artifact;
        this.metric = metric;
    }

    public Log(Integer id, Artifact artifact, String metric, String subject, String message, Date date) {
        this.id = id;
        this.artifact = artifact;
        this.metric = metric;
        this.subject = subject;
        this.message = message;
        this.date = date;
    }

    public void writeLogCharacterInvalid() {
        this.subject = "Character invalid";
        this.message = "By default -1 assigned to the metric value.";
        this.date = new Date();
        new LogDAO().register(this);
    }

    public String printLog() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.date);
        String log = cal.getTime() + "     ";
        if (this.artifact instanceof Method) {
            Method method = (Method) this.artifact;
            String[] split = method.getSource().split("\\.");
            log += method.getPack() + "." + split[0] + "::" + method.getName() + "     ";
        } else if (this.artifact instanceof Method) {
            Type type = (Type) this.artifact;
            log += type.getPack() + "." + type.getName() + "     ";
        } else {
            Package pack = (Package) this.artifact;
            log += pack.getName() + "     ";
        }
        log += this.metric + "     ";
        log += "INFO: (" + this.subject + ") " + this.message + "\n";
        return log;
    }

    public String getMetric() {
        return this.metric;
    }

    public String getSubject() {
        return this.subject;
    }

    public Artifact getArtifact() {
        return this.artifact;
    }

    public String getMessage() {
        return this.message;
    }

    public Date getDate() {
        return this.date;
    }

    public Integer getId() {
        return this.id;
    }

}
