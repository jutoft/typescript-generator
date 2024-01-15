package cz.habarta.typescript.generator;

import java.io.Serializable;
import java.net.URLClassLoader;
import java.util.List;
import java.util.function.Predicate;

public class InputParameters implements Serializable {
    public List<String> classNames;
    public List<String> classNamePatterns;
    public List<String> classesWithAnnotations;
    public List<String> classesImplementingInterfaces;
    public List<String> classesExtendingClasses;
    public String jaxrsApplicationClassName;
    public boolean automaticJaxrsApplication;
    public Predicate<String> isClassNameExcluded;
    public URLClassLoader classLoader;
    public List<String> scanningAcceptedPackages;
    public boolean debug;
}
