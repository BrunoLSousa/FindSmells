/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import metrics.MetricClass;
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

/**
 *
 * @author bruno
 */
public class Filter {

    private Set<File> files;
    private SAXBuilder builder;
    private Project project;

    public Filter(Set<File> files, String nameProject) {
        this.files = files;
        this.builder = new SAXBuilder();
        this.project = new Project(nameProject);
    }

    public void convertFiles() {
        try {
            for (File f : this.files) {
                Document doc = this.builder.build(f);
                Element root = doc.getRootElement();
                List element = root.getChildren();
                Iterator i = element.iterator();
                System.out.println("Conversão " + this.project.getName() + " - " + root.getAttributeValue("scope") + " iniciada !");
                int idProject = new ProjectDAO().register(this.project);
                this.project.setId(idProject);
                converterProject(i);
                System.out.println("Conversão " + project.getName() + "finalizada !\n");
            }
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(Filter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void converterProject(Iterator i) {
        while (i.hasNext()) {
            Element e = (Element) i.next();
            if (e.getName().equals("Cycle")) {
                Cycle cycle = new Cycle(e.getAttributeValue("name"), this.project, Integer.parseInt(e.getAttributeValue("nodes")), Integer.parseInt(e.getAttributeValue("diameter")));
                int idCycle = new CycleDAO().register(cycle);
                cycle.setId(idCycle);
                converterDataCycle(cycle, e.getChildren());
            } else if (e.getName().equals("Metric")) {
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
        }
    }

    private void converterMetricPackage(String nameMetric, List<Element> elements) {
        elements = elements.get(0).getChildren();
        for (Element el : elements) {
            DAOMetric dao = new PackageDAO();
            List<Package> packs = (List<Package>) dao.selectByObject(this.project);
            Package pack = null;
            if (packs.size() > 0) {
                for (Package p : packs) {
                    pack = (p.getValueMetric(MetricPackage.valueOf(nameMetric.toUpperCase())) == -1.0) ? p : null;
                }
                if (pack != null) {
                    pack.updateValueMetric(nameMetric, Double.parseDouble(el.getAttributeValue("value")));
                    dao.update(pack);
                } else {
                    pack = new Package(el.getAttributeValue("name"), project, el.getAttributeValue("source"), el.getAttributeValue("package"));
                    pack.updateValueMetric(nameMetric, Double.parseDouble(el.getAttributeValue("value")));
                    dao.register(pack);
                }
            } else {
                pack = new Package(el.getAttributeValue("name"), project, el.getAttributeValue("source"), el.getAttributeValue("package"));
                pack.updateValueMetric(nameMetric, Double.parseDouble(el.getAttributeValue("value")));
                dao.register(pack);
            }
        }
    }

    private void converterMetricMethod(String nameMetric, List<Element> elements) {
        elements = elements.get(0).getChildren();
        for (Element el : elements) {
            DAOMetric dao = new MethodDAO();
            List<Method> methods = (List<Method>) dao.selectByObject(this.project);
            Method method = null;
            if (methods.size() > 0) {
                for (Method m : methods) {
                    method = (m.getValueMetric(MetricMethod.valueOf(nameMetric.toUpperCase())) == -1.0) ? m : null;
                }
                if (method != null) {
                    method.updateValueMetric(nameMetric, Double.parseDouble(el.getAttributeValue("value")));
                    dao.update(method);
                } else {
                    method = new Method(el.getAttributeValue("name"), project, el.getAttributeValue("source"), el.getAttributeValue("package"));
                    method.updateValueMetric(nameMetric, Double.parseDouble(el.getAttributeValue("value")));
                    dao.register(method);
                }
            } else {
                method = new Method(el.getAttributeValue("name"), project, el.getAttributeValue("source"), el.getAttributeValue("package"));
                method.updateValueMetric(nameMetric, Double.parseDouble(el.getAttributeValue("value")));
                dao.register(method);
            }
        }

    }

    private void converterMetricType(String nameMetric, List<Element> elements) {
        elements = elements.get(0).getChildren();
        for (Element el : elements) {
            DAOMetric dao = new TypeDAO();
            List<Type> types = (List<Type>) dao.selectByObject(this.project);
            Type type = null;
            if (types.size() > 0) {
                for (Type t : types) {
                    type = (t.getValueMetric(MetricClass.valueOf(nameMetric.toUpperCase())) == -1.0) ? t : null;
                }
                if (type != null) {
                    type.updateValueMetric(nameMetric, Double.parseDouble(el.getAttributeValue("value")));
                    dao.update(type);
                } else {
                    type = new Type(el.getAttributeValue("name"), project, el.getAttributeValue("source"), el.getAttributeValue("package"));
                    type.updateValueMetric(nameMetric, Double.parseDouble(el.getAttributeValue("value")));
                    dao.register(type);
                }
            } else {
                type = new Type(el.getAttributeValue("name"), project, el.getAttributeValue("source"), el.getAttributeValue("package"));
                type.updateValueMetric(nameMetric, Double.parseDouble(el.getAttributeValue("value")));
                dao.register(type);
            }
        }
    }

    private void converterDataCycle(Cycle cycle, List<Element> elements) {
        elements = elements.get(0).getChildren();
        for (Element el : elements) {
//            cycle.addDataCycle(el.getContent().get(0).getValue());
            new CycleDAO().registerCycleData(cycle.getId(), el.getContent().get(0).getValue());
        }
    }

}
