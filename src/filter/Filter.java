/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import metrics.MetricType;
import metrics.MetricMethod;
import metrics.MetricPackage;
import structure.Package;
import metrics.MetricProject;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import structure.Cycle;
import structure.Method;
import structure.Project;
import structure.Type;
import structure.dao.CycleDAO;
import structure.dao.DAOMetric;
import structure.dao.MethodDAO;
import structure.dao.PackageDAO;
import structure.dao.ProjectDAO;
import structure.dao.TypeDAO;
import view.FilterSystemForm;
import view.ProgressBarForm;

/**
 *
 * @author bruno
 */
public class Filter extends Thread{

    private Set<File> files;
    private SAXBuilder builder;
    private Project project;
    private ProgressBarForm progressBar;
    private FilterSystemForm frame;
    

    public Filter(Set<File> files, String nameProject, FilterSystemForm frame) {
        this.files = files;
        this.builder = new SAXBuilder();
        this.project = new Project(nameProject);
        this.progressBar = new ProgressBarForm();
        this.frame = frame;
    }
    
    public void convertFiles() {
        try {
            int idProject = new ProjectDAO().register(this.project);
            this.project.setId(idProject);
            for (File f : this.files) {
                Document doc = this.builder.build(f);
                Element root = doc.getRootElement();
                progressBar.initialize(getLines(root), "Importing software metrics from the file '" + Paths.get(f.getName()) + "'. Please, wait.");
                incrementValue();
                List element = root.getChildren();
                Iterator i = element.iterator();
                converterProject(i);
                progressBar.finish();
            }
            finish();
            frame.refresh();
            
        } catch (JDOMException | IOException ex) {
            exception();
            Logger.getLogger(Filter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int getLines(Element root) {
        List element = root.getChildren();
        Iterator i = element.iterator();
        int line = 0;
        while (i.hasNext()) {
            Element e = (Element) i.next();
            if (e.getName().equals("Cycle")) {
                List<Element> elements = e.getChildren();
                line += elements.size();
            } else if (e.getName().equals("Metric")) {
                List<Element> elements = e.getChildren();
                if (elements.get(0).getName().equals("Values")) {
                    elements = elements.get(0).getChildren();
                    line += elements.size();
                } else if (elements.get(0).getName().equals("Value")) {
                    line += 1;
                }
            }

        }
        return line;
    }

    private void exception() {
        finish();
        JOptionPane.showMessageDialog(null, "Could not finish converting files from this project!", "Error", JOptionPane.ERROR_MESSAGE, null);
        int idProject = new ProjectDAO().getLastID();
        new ProjectDAO().remove(idProject);
    }

    private void converterProject(Iterator i) {
        while (i.hasNext()) {
            Element e = (Element) i.next();
            if (e.getName().equals("Cycle")) {
                Cycle cycle = new Cycle(e.getAttributeValue("name"), this.project, Integer.parseInt(e.getAttributeValue("nodes")), Integer.parseInt(e.getAttributeValue("diameter")));
                int idCycle = new CycleDAO().register(cycle);
                cycle.setId(idCycle);
                incrementValue();
                converterDataCycle(cycle, e.getChildren());
            } else if (e.getName().equals("Metric")) {
//                incrementValue();
                converterMetrics(e.getAttributeValue("id"), e.getChildren());
            }
        }
    }

    private void converterMetrics(String metric, List<Element> elements) {
        if (elements.get(0).getName().equals("Value")) {
            converterMetricProject(metric, elements);
        } else if (elements.get(0).getAttributeValue("per").equals("method")) {
            converterMetricMethod(metric, elements);
        } else if (elements.get(0).getAttributeValue("per").equals("type")) {
            converterMetricType(metric, elements);
        } else if (elements.get(0).getAttributeValue("per").equals("packageFragment")) {
            converterMetricPackage(metric, elements);
        }
    }

    private void converterMetricProject(String nameMetric, List<Element> elements) {
        for (Element el : elements) {
            HashMap<MetricProject, Double> metrics = new ProjectDAO().selectMetricsProject(project);
            MetricProject metric = MetricProject.valueOf(nameMetric.toUpperCase());
            Double valueXml = Double.parseDouble(el.getAttributeValue("value"));
            Double newValue = (this.project.getMetricProject(metric) == -1.0) ? valueXml : (this.project.getMetricProject(metric) + valueXml);
            this.project.updateMetric(metric, newValue);
            if (metrics.isEmpty()) {
                new ProjectDAO().registerMetricProject(project);
            } else {
                new ProjectDAO().updateMetricProject(project);
            }
            incrementValue();
        }
    }

    private void converterMetricPackage(String nameMetric, List<Element> elements) {
        elements = elements.get(0).getChildren();
        for (Element el : elements) {
            DAOMetric dao = new PackageDAO();
            Package pack = new Package(el.getAttributeValue("name"), project, el.getAttributeValue("package"));
            List<Package> packs = (List<Package>) dao.selectByObject(pack);
            if (packs.size() > 0) {
                for (Package p : packs) {
                    if (p.getValueMetric(MetricPackage.valueOf(nameMetric.toUpperCase())) == -1.0) {
                        pack = p;
                        break;
                    } else {
                        pack = null;
                    }
                }
                if (pack != null) {
                    pack.updateValueMetric(nameMetric, el.getAttributeValue("value"));
                    dao.update(pack);
                } else {
                    pack = new Package(el.getAttributeValue("name"), project, el.getAttributeValue("package"));
                    pack.updateValueMetric(nameMetric, el.getAttributeValue("value"));
                    dao.register(pack);
                }
            } else {
                pack = new Package(el.getAttributeValue("name"), project, el.getAttributeValue("package"));
                pack.updateValueMetric(nameMetric, el.getAttributeValue("value"));
                dao.register(pack);
            }

            incrementValue();
        }
    }

    private void converterMetricMethod(String nameMetric, List<Element> elements) {
        elements = elements.get(0).getChildren();
        for (Element el : elements) {
            DAOMetric dao = new MethodDAO();
            Method method = new Method(el.getAttributeValue("name"), project, el.getAttributeValue("source"), el.getAttributeValue("package"));
            List<Method> methods = (List<Method>) dao.selectByObject(method);
            if (methods.size() > 0) {
                for (Method m : methods) {
                    if (m.getValueMetric(MetricMethod.valueOf(nameMetric.toUpperCase())) == -1.0) {
                        method = m;
                        break;
                    } else {
                        method = null;
                    }
                }
                if (method != null) {
                    method.updateValueMetric(nameMetric, el.getAttributeValue("value"));
                    dao.update(method);
                } else {
                    method = new Method(el.getAttributeValue("name"), project, el.getAttributeValue("source"), el.getAttributeValue("package"));
                    method.updateValueMetric(nameMetric, el.getAttributeValue("value"));
                    dao.register(method);
                }
            } else {
                method = new Method(el.getAttributeValue("name"), project, el.getAttributeValue("source"), el.getAttributeValue("package"));
                method.updateValueMetric(nameMetric, el.getAttributeValue("value"));
                dao.register(method);
            }
            incrementValue();
        }

    }

    private void converterMetricType(String nameMetric, List<Element> elements) {
        elements = elements.get(0).getChildren();
        for (Element el : elements) {
            DAOMetric dao = new TypeDAO();
            Type type = new Type(el.getAttributeValue("name"), project, el.getAttributeValue("source"), el.getAttributeValue("package"));
            List<Type> types = (List<Type>) dao.selectByObject(type);
            if (types.size() > 0) {
                for (Type t : types) {
                    if (t.getValueMetric(MetricType.valueOf(nameMetric.toUpperCase())) == -1.0) {
                        type = t;
                        break;
                    } else {
                        type = null;
                    }
                }
                if (type != null) {
                    type.updateValueMetric(nameMetric, el.getAttributeValue("value"));
                    dao.update(type);
                } else {
                    type = new Type(el.getAttributeValue("name"), project, el.getAttributeValue("source"), el.getAttributeValue("package"));
                    type.updateValueMetric(nameMetric, el.getAttributeValue("value"));
                    dao.register(type);
                }
            } else {
                type = new Type(el.getAttributeValue("name"), project, el.getAttributeValue("source"), el.getAttributeValue("package"));
                type.updateValueMetric(nameMetric, el.getAttributeValue("value"));
                dao.register(type);
            }

            incrementValue();
        }
    }

    private void converterDataCycle(Cycle cycle, List<Element> elements) {
        for (Element el : elements) {
            new CycleDAO().registerCycleData(cycle.getId(), el.getContent().get(0).getValue());
            incrementValue();
        }
    }

    private void incrementValue() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (!progressBar.isVisible()) {
                    progressBar.setVisible(true);
                }

                progressBar.incrementValue();
            }
        });
    }
    
    private void finish() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                progressBar.finish();
//                frame.refresh();
            }
        });
    }
    
    @Override
    public void run(){
        convertFiles();
    }

}
