package cz.habarta.typescript.generator.gradle;

import cz.habarta.typescript.generator.*;
import org.gradle.api.DefaultTask;
import org.gradle.api.file.ConfigurableFileCollection;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Nested;
import org.gradle.api.tasks.TaskAction;
import org.gradle.jvm.toolchain.JavaLauncher;
import org.gradle.workers.WorkQueue;
import org.gradle.workers.WorkerExecutor;

import javax.inject.Inject;
import java.io.File;
import java.util.List;


public abstract class GenerateTask extends DefaultTask {

    public String outputFile;
    public TypeScriptFileType outputFileType;
    public TypeScriptOutputKind outputKind;
    public String module;
    public String namespace;
    public boolean mapPackagesToNamespaces;
    public String umdNamespace;
    public List<ModuleDependency> moduleDependencies;
    public List<String> classes;
    public List<String> classPatterns;
    public List<String> classesWithAnnotations;
    public List<String> classesImplementingInterfaces;
    public List<String> classesExtendingClasses;
    public String classesFromJaxrsApplication;
    public boolean classesFromAutomaticJaxrsApplication;
    public List<String> scanningAcceptedPackages;
    public List<String> excludeClasses;
    public List<String> excludeClassPatterns;
    public List<String> includePropertyAnnotations;
    public List<String> excludePropertyAnnotations;
    public JsonLibrary jsonLibrary;
    public Jackson2Configuration jackson2Configuration;
    public GsonConfiguration gsonConfiguration;
    public JsonbConfiguration jsonbConfiguration;
    public List<String> additionalDataLibraries;
    public OptionalProperties optionalProperties;
    public OptionalPropertiesDeclaration optionalPropertiesDeclaration;
    public NullabilityDefinition nullabilityDefinition;
    public boolean declarePropertiesAsReadOnly;
    public String removeTypeNamePrefix;
    public String removeTypeNameSuffix;
    public String addTypeNamePrefix;
    public String addTypeNameSuffix;
    public List<String> customTypeNaming;
    public String customTypeNamingFunction;
    public List<String> referencedFiles;
    public List<String> importDeclarations;
    public List<String> customTypeMappings;
    public List<String> customTypeAliases;
    public DateMapping mapDate;
    public MapMapping mapMap;
    public EnumMapping mapEnum;
    public IdentifierCasing enumMemberCasing;
    public boolean nonConstEnums;
    public List<String> nonConstEnumAnnotations;
    public ClassMapping mapClasses;
    public List<String> mapClassesAsClassesPatterns;
    public boolean generateConstructors;
    public List<String> disableTaggedUnionAnnotations;
    public boolean disableTaggedUnions;
    public boolean generateReadonlyAndWriteonlyJSDocTags;
    public boolean ignoreSwaggerAnnotations;
    public boolean generateJaxrsApplicationInterface;
    public boolean generateJaxrsApplicationClient;
    public boolean generateSpringApplicationInterface;
    public boolean generateSpringApplicationClient;
    public boolean scanSpringApplication;
    public RestNamespacing restNamespacing;
    public String restNamespacingAnnotation;
    public String restResponseType;
    public String restOptionsType;
    public String customTypeProcessor;
    public boolean sortDeclarations;
    public boolean sortTypeDeclarations;
    public boolean noFileComment;
    public boolean noTslintDisable;
    public boolean noEslintDisable;
    public boolean tsNoCheck;
    public List<File> javadocXmlFiles;
    public List<String> extensionClasses;
    public List<String> extensions;
    public List<Settings.ConfiguredExtension> extensionsWithConfiguration;
    public List<String> optionalAnnotations;
    public List<String> requiredAnnotations;
    public List<String> nullableAnnotations;
    public boolean primitivePropertiesRequired;
    public boolean generateInfoJson;
    public boolean generateNpmPackageJson;
    public String npmName;
    public String npmVersion;
    public String npmTypescriptVersion;
    public String npmBuildScript;
    public List<String> npmDependencies;
    public List<String> npmDevDependencies;
    public List<String> npmPeerDependencies;
    public StringQuotes stringQuotes;
    public String indentString;
    public boolean jackson2ModuleDiscovery;
    public List<String> jackson2Modules;
    public Logger.Level loggingLevel;

    public GenerateTask() {
        // add default
    }

    private InputSettings createSettings() {
        InputSettings settings = new InputSettings();
        if (outputFileType != null) {
            settings.outputFileType = outputFileType;
        }
        settings.outputKind = outputKind;
        settings.module = module;
        settings.namespace = namespace;
        settings.mapPackagesToNamespaces = mapPackagesToNamespaces;
        settings.umdNamespace = umdNamespace;
        settings.moduleDependencies = moduleDependencies;
        settings.excludeClasses = excludeClasses;
        settings.excludeClassPatterns = excludeClassPatterns;
        settings.jsonLibrary = jsonLibrary;
        settings.jackson2Configuration = jackson2Configuration;
        settings.gsonConfiguration = gsonConfiguration;
        settings.jsonbConfiguration = jsonbConfiguration;
        settings.additionalDataLibraries = additionalDataLibraries;
        settings.optionalProperties = optionalProperties;
        settings.optionalPropertiesDeclaration = optionalPropertiesDeclaration;
        settings.nullabilityDefinition = nullabilityDefinition;
        settings.declarePropertiesAsReadOnly = declarePropertiesAsReadOnly;
        settings.removeTypeNamePrefix = removeTypeNamePrefix;
        settings.removeTypeNameSuffix = removeTypeNameSuffix;
        settings.addTypeNamePrefix = addTypeNamePrefix;
        settings.addTypeNameSuffix = addTypeNameSuffix;
        settings.customTypeNaming = Settings.convertToMap(customTypeNaming, "customTypeNaming");
        settings.customTypeNamingFunction = customTypeNamingFunction;
        settings.referencedFiles = referencedFiles;
        settings.importDeclarations = importDeclarations;
        settings.customTypeMappings = Settings.convertToMap(customTypeMappings, "customTypeMapping");
        settings.customTypeAliases = Settings.convertToMap(customTypeAliases, "customTypeAlias");
        settings.mapDate = mapDate;
        settings.mapMap = mapMap;
        settings.mapEnum = mapEnum;
        settings.enumMemberCasing = enumMemberCasing;
        settings.nonConstEnums = nonConstEnums;
        settings.nonConstEnumAnnotations = nonConstEnumAnnotations;
        settings.mapClasses = mapClasses;
        settings.mapClassesAsClassesPatterns = mapClassesAsClassesPatterns;
        settings.generateConstructors = generateConstructors;
        settings.disableTaggedUnionAnnotations = disableTaggedUnionAnnotations;
        settings.disableTaggedUnions = disableTaggedUnions;
        settings.generateReadonlyAndWriteonlyJSDocTags = generateReadonlyAndWriteonlyJSDocTags;
        settings.ignoreSwaggerAnnotations = ignoreSwaggerAnnotations;
        settings.generateJaxrsApplicationInterface = generateJaxrsApplicationInterface;
        settings.generateJaxrsApplicationClient = generateJaxrsApplicationClient;
        settings.generateSpringApplicationInterface = generateSpringApplicationInterface;
        settings.generateSpringApplicationClient = generateSpringApplicationClient;
        settings.scanSpringApplication = scanSpringApplication;
        settings.restNamespacing = restNamespacing;
        settings.restNamespacingAnnotation = restNamespacingAnnotation;
        settings.restResponseType = restResponseType;
        settings.restOptionsType = restOptionsType;
        settings.customTypeProcessor = customTypeProcessor;
        settings.sortDeclarations = sortDeclarations;
        settings.sortTypeDeclarations = sortTypeDeclarations;
        settings.noFileComment = noFileComment;
        settings.noTslintDisable = noTslintDisable;
        settings.noEslintDisable = noEslintDisable;
        settings.tsNoCheck = tsNoCheck;
        settings.javadocXmlFiles = javadocXmlFiles;
        settings.extensions = extensions;
        settings.extensionClasses = extensionClasses;
        settings.extensionsWithConfiguration = extensionsWithConfiguration;
        settings.includePropertyAnnotations = includePropertyAnnotations;
        settings.excludePropertyAnnotations = excludePropertyAnnotations;
        settings.optionalAnnotations = optionalAnnotations;
        settings.requiredAnnotations = requiredAnnotations;
        settings.nullableAnnotations = nullableAnnotations;
        settings.primitivePropertiesRequired = primitivePropertiesRequired;
        settings.generateInfoJson = generateInfoJson;
        settings.generateNpmPackageJson = generateNpmPackageJson;
        settings.npmName = npmName == null && generateNpmPackageJson ? getProject().getName() : npmName;
        settings.npmVersion = npmVersion == null && generateNpmPackageJson ? "1.0.0" : npmVersion;
        settings.npmTypescriptVersion = npmTypescriptVersion;
        settings.npmBuildScript = npmBuildScript;
        settings.npmPackageDependencies = Settings.convertToMap(npmDependencies, "npmDependencies");
        settings.npmDevDependencies = Settings.convertToMap(npmDevDependencies, "npmDevDependencies");
        settings.npmPeerDependencies = Settings.convertToMap(npmPeerDependencies, "npmPeerDependencies");
        settings.stringQuotes = stringQuotes;
        settings.indentString = indentString;
        settings.jackson2ModuleDiscovery = jackson2ModuleDiscovery;
        settings.jackson2Modules = jackson2Modules;
        settings.loggingLevel = loggingLevel;
        return settings;
    }

    @org.gradle.api.tasks.Classpath
    public abstract ConfigurableFileCollection getClasspath();

    @Inject
    abstract public WorkerExecutor getWorkerExecutor();

    @Nested
    public abstract Property<JavaLauncher> getLauncher();

    @TaskAction
    public void generate() {
        if (outputKind == null) {
            throw new RuntimeException("Please specify 'outputKind' property.");
        }
        if (jsonLibrary == null) {
            throw new RuntimeException("Please specify 'jsonLibrary' property.");
        }

        TypeScriptGenerator.setLogger(new Logger(loggingLevel));
        TypeScriptGenerator.printVersion();

        // class loader
        WorkQueue workQueue = getWorkerExecutor().processIsolation(processWorkerSpec -> {
            processWorkerSpec.getClasspath().from(getClasspath());

            processWorkerSpec.forkOptions(forkOptions -> forkOptions.setExecutable(getLauncher().get().getExecutablePath().getAsFile()));
        });

        final InputSettings settings = createSettings();

        final Input.Parameters parameters = new Input.Parameters();
        parameters.classNames = classes;
        parameters.classNamePatterns = classPatterns;
        parameters.classesWithAnnotations = classesWithAnnotations;
        parameters.classesImplementingInterfaces = classesImplementingInterfaces;
        parameters.classesExtendingClasses = classesExtendingClasses;
        parameters.jaxrsApplicationClassName = classesFromJaxrsApplication;
        parameters.automaticJaxrsApplication = classesFromAutomaticJaxrsApplication;
        parameters.scanningAcceptedPackages = scanningAcceptedPackages;
        parameters.debug = loggingLevel == Logger.Level.Debug;

        final Settings settingsTemp = new Settings();
        settingsTemp.outputFileType = outputFileType;

        final File output = outputFile != null
                ? getProject().file(outputFile)
                : new File(new File(getProject().getBuildDir(), "typescript-generator"), getProject().getName() + settingsTemp.getExtension());
        settingsTemp.validateFileName(output);

        workQueue.submit(GenerateTypescriptWorker.class, configs -> {
            configs.getName().set(getProject().getName());
            configs.getInputParameters().set(parameters);
            configs.getOutput().set(output);
            configs.getSettings().set(settings);
            configs.getClasspath().from(this.getClasspath());
        });
        workQueue.await();
    }
}
