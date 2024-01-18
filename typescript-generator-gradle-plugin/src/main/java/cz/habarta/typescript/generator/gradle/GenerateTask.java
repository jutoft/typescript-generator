package cz.habarta.typescript.generator.gradle;

import cz.habarta.typescript.generator.*;
import org.gradle.api.DefaultTask;
import org.gradle.api.file.ConfigurableFileCollection;
import org.gradle.api.file.DirectoryProperty;
import org.gradle.api.file.RegularFile;
import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.provider.ListProperty;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.*;
import org.gradle.api.tasks.Input;
import org.gradle.jvm.toolchain.JavaLauncher;
import org.gradle.workers.WorkQueue;
import org.gradle.workers.WorkerExecutor;

import javax.inject.Inject;
import java.io.File;
import java.util.List;


public abstract class GenerateTask extends DefaultTask {
    @Input
    public abstract Property<String> getOutputFileName();

    @Internal("Represented by outputFile, jsonInfoOutputFile, npmPackageJsonOutputFile")
    public abstract DirectoryProperty getOutputDirectory();

    @OutputFile
    public abstract RegularFileProperty getOutputFile();

    @Input
    public abstract Property<TypeScriptFileType> getOutputFileType();

    @Input
    public abstract Property<String> getJsonInfoOutputFileName();

    @OutputFile
    @Optional
    public abstract RegularFileProperty getJsonInfoOutputFile();

    @Input
    public abstract Property<String> getNpmPackageJsonOutputFileName();

    @OutputFile
    @Optional
    public abstract RegularFileProperty getNpmPackageJsonOutputFile();
    @Input
    public abstract Property<TypeScriptOutputKind> getOutputKind();
    @Input
    @Optional
    public abstract Property<String> getModule();
    @Input
    @Optional
    public abstract Property<String> getNamespace();

    @Input
    @Optional
    public abstract Property<Boolean> getMapPackagesToNamespaces();
    @Input
    @Optional
    public abstract Property<String> getUmdNamespace();
    /**
     * if moduleDependencies is used add inputs.property annotations to support caching and up-to-date checks.
     */
    public List<ModuleDependency> moduleDependencies;
    @Input
    @Optional
    public abstract ListProperty<String> getClasses();
    @Input
    @Optional
    public abstract ListProperty<String> getClassPatterns();
    @Input
    @Optional
    public abstract ListProperty<String> getClassesWithAnnotations();
    @Input
    @Optional
    public abstract ListProperty<String> getClassesImplementingInterfaces();
    @Input
    @Optional
    public abstract ListProperty<String> getClassesExtendingClasses();
    @Input
    @Optional
    public abstract Property<String> getClassesFromJaxrsApplication();
    @Input
    @Optional
    public abstract Property<Boolean> getClassesFromAutomaticJaxrsApplication();
    @Input
    @Optional
    public abstract ListProperty<String> getScanningAcceptedPackages();
    @Input
    @Optional
    public abstract ListProperty<String> getExcludeClasses();
    @Input
    @Optional
    public abstract ListProperty<String> getExcludeClassPatterns();
    @Input
    @Optional
    public abstract ListProperty<String> getIncludePropertyAnnotations();
    @Input
    @Optional
    public abstract ListProperty<String> getExcludePropertyAnnotations();
    @Input
    @Optional
    public abstract Property<JsonLibrary> getJsonLibrary();
    public final Jackson2Configuration jackson2Configuration = new Jackson2Configuration();
    public final GsonConfiguration gsonConfiguration = new GsonConfiguration();
    public final JsonbConfiguration jsonbConfiguration = new JsonbConfiguration();
    @Input
    @Optional
    public abstract ListProperty<String> getAdditionalDataLibraries();
    @Input
    @Optional
    public abstract Property<OptionalProperties> getOptionalProperties();
    @Input
    @Optional
    public abstract Property<OptionalPropertiesDeclaration> getOptionalPropertiesDeclaration();
    @Input
    @Optional
    public abstract Property<NullabilityDefinition> getNullabilityDefinition();

    @Input
    @Optional
    public abstract Property<Boolean> getDeclarePropertiesAsReadOnly();
    @Input
    @Optional
    public abstract Property<String> getRemoveTypeNamePrefix();
    @Input
    @Optional
    public abstract Property<String> getRemoveTypeNameSuffix();
    @Input
    @Optional
    public abstract Property<String> getAddTypeNamePrefix();
    @Input
    @Optional
    public abstract Property<String> getAddTypeNameSuffix();
    @Input
    @Optional
    public abstract ListProperty<String> getCustomTypeNaming();
    @Input
    @Optional
    public abstract Property<String> getCustomTypeNamingFunction();
    @Input
    @Optional
    public abstract ListProperty<String> getReferencedFiles();
    @Input
    @Optional
    public abstract ListProperty<String> getImportDeclarations();
    @Input
    @Optional
    public abstract ListProperty<String> getCustomTypeMappings();
    @Input
    @Optional
    public abstract ListProperty<String> getCustomTypeAliases();
    @Input
    @Optional
    public abstract Property<DateMapping> getMapDate();
    @Input
    @Optional
    public abstract Property<MapMapping> getMapMap();
    @Input
    @Optional
    public abstract Property<EnumMapping> getMapEnum();
    @Input
    @Optional
    public abstract Property<IdentifierCasing> getEnumMemberCasing();
    @Input
    @Optional
    public abstract Property<Boolean> getNonConstEnums();
    @Input
    @Optional
    public abstract ListProperty<String> getNonConstEnumAnnotations();
    @Input
    @Optional
    public abstract Property<ClassMapping> getMapClasses();
    @Input
    @Optional
    public abstract ListProperty<String> getMapClassesAsClassesPatterns();
    @Input
    @Optional
    public abstract Property<Boolean> getGenerateConstructors();
    @Input
    @Optional
    public abstract ListProperty<String> getDisableTaggedUnionAnnotations();
    @Input
    @Optional
    public abstract Property<Boolean> getDisableTaggedUnions();
    @Input
    @Optional
    public abstract Property<Boolean> getGenerateReadonlyAndWriteonlyJSDocTags();
    @Input
    @Optional
    public abstract Property<Boolean> getIgnoreSwaggerAnnotations();
    @Input
    @Optional
    public abstract Property<Boolean> getGenerateJaxrsApplicationInterface();
    @Input
    @Optional
    public abstract Property<Boolean> getGenerateJaxrsApplicationClient();
    @Input
    @Optional
    public abstract Property<Boolean> getGenerateSpringApplicationInterface();
    @Input
    @Optional
    public abstract Property<Boolean> getGenerateSpringApplicationClient();
    @Input
    @Optional
    public abstract Property<Boolean> getScanSpringApplication();
    @Input
    @Optional
    public abstract Property<RestNamespacing> getRestNamespacing();
    @Input
    @Optional
    public abstract Property<String> getRestNamespacingAnnotation();
    @Input
    @Optional
    public abstract Property<String> getRestResponseType();
    @Input
    @Optional
    public abstract Property<String> getRestOptionsType();
    @Input
    @Optional
    public abstract Property<String> getCustomTypeProcessor();
    @Input
    @Optional
    public abstract Property<Boolean> getSortDeclarations();
    @Input
    @Optional
    public abstract Property<Boolean> getSortTypeDeclarations();
    @Input
    @Optional
    public abstract Property<Boolean> getNoFileComment();
    @Input
    @Optional
    public abstract Property<Boolean> getNoTslintDisable();
    @Input
    @Optional
    public abstract Property<Boolean> getNoEslintDisable();
    @Input
    @Optional
    public abstract Property<Boolean> getTsNoCheck();
    @Input
    @Optional
    public abstract ListProperty<File> getJavadocXmlFiles();
    @Input
    @Optional
    public abstract ListProperty<String> getExtensionClasses();
    @Input
    @Optional
    public abstract ListProperty<String> getExtensionsList();

    /**
     * if extensionsWithConfiguration is used add inputs.property annotations to support caching and up-to-date checks.
     */
    public List<Settings.ConfiguredExtension> extensionsWithConfiguration;
    @Input
    @Optional
    public abstract ListProperty<String> getOptionalAnnotations();
    @Input
    @Optional
    public abstract ListProperty<String> getRequiredAnnotations();
    @Input
    @Optional
    public abstract ListProperty<String> getNullableAnnotations();
    @Input
    @Optional
    public abstract Property<Boolean> getPrimitivePropertiesRequired();
    @Input
    public abstract Property<Boolean> getGenerateInfoJson();
    @Input
    public abstract Property<Boolean> getGenerateNpmPackageJson();
    @Input
    public abstract Property<String> getNpmName();
    @Input
    public abstract Property<String> getNpmVersion();
    @Input
    @Optional
    public abstract Property<String> getNpmTypescriptVersion();
    @Input
    @Optional
    public abstract Property<String> getNpmBuildScript();
    @Input
    @Optional
    public abstract ListProperty<String> getNpmDependencies();
    @Input
    @Optional
    public abstract ListProperty<String> getNpmDevDependencies();
    @Input
    @Optional
    public abstract ListProperty<String> getNpmPeerDependencies();
    @Input
    @Optional
    public abstract Property<StringQuotes> getStringQuotes();
    @Input
    @Optional
    public abstract Property<String> getIndentString();
    @Input
    @Optional
    public abstract Property<Boolean> getJackson2ModuleDiscovery();
    @Input
    @Optional
    public abstract ListProperty<String> getJackson2Modules();
    public Logger.Level loggingLevel;

    public GenerateTask() {
        // add defaults
        getGenerateInfoJson().convention(false);
        getGenerateNpmPackageJson().convention(false);
        getNpmName().convention(getProject().provider(() -> getProject().getName()));
        getNpmVersion().convention("1.0.0");

        getOutputDirectory().convention(getProject().getLayout().getBuildDirectory().dir("typescript-generator"));
        getOutputFileType().convention(TypeScriptFileType.declarationFile);
        getOutputFileName().convention(getOutputFileType().map(ext -> getProject().getName() + Settings.toFileExtension(ext)));
        getOutputFile().convention(getOutputDirectory().file(getOutputFileName()));

        getJsonInfoOutputFileName().convention("typescript-generator-info.json");
        getJsonInfoOutputFile().fileProvider(getOutputFile().zip(getJsonInfoOutputFileName(), (outFile, fileName) -> outFile.getAsFile().toPath().getParent().resolve(fileName).toFile()));

        getNpmPackageJsonOutputFileName().convention("package.json");
        getNpmPackageJsonOutputFile().fileProvider(getOutputFile().zip(getNpmPackageJsonOutputFileName(), (outFile, fileName) -> outFile.getAsFile().toPath().getParent().resolve(fileName).toFile()));

        // faking input property checks that can not easily be done as Properties
        getInputs().property("jackson2Configuration.fieldVisibility", jackson2Configuration.fieldVisibility).optional(true);
        getInputs().property("jackson2Configuration.getterVisibility", jackson2Configuration.getterVisibility).optional(true);
        getInputs().property("jackson2Configuration.isGetterVisibility", jackson2Configuration.isGetterVisibility).optional(true);
        getInputs().property("jackson2Configuration.setterVisibility", jackson2Configuration.setterVisibility).optional(true);
        getInputs().property("jackson2Configuration.creatorVisibility", jackson2Configuration.creatorVisibility).optional(true);
        getInputs().property("jackson2Configuration.shapeConfigOverrides", jackson2Configuration.shapeConfigOverrides).optional(true);
        getInputs().property("jackson2Configuration.enumsUsingToString", jackson2Configuration.enumsUsingToString).optional(true);
        getInputs().property("jackson2Configuration.disableObjectIdentityFeature", jackson2Configuration.disableObjectIdentityFeature).optional(true);
        getInputs().property("jackson2Configuration.serializerTypeMappings", jackson2Configuration.serializerTypeMappings).optional(true);
        getInputs().property("jackson2Configuration.deserializerTypeMappings", jackson2Configuration.deserializerTypeMappings).optional(true);
        getInputs().property("jackson2Configuration.view", jackson2Configuration.view).optional(true);
        getInputs().property("gsonConfiguration.excludeFieldsWithModifiers", gsonConfiguration.excludeFieldsWithModifiers).optional(true);
        getInputs().property("jsonbConfiguration.namingStrategy", jsonbConfiguration.namingStrategy).optional(true);
    }

    private InputSettings createSettings() {
        InputSettings settings = new InputSettings();
        settings.outputFileType = getOutputFileType().get();
        settings.outputKind = getOutputKind().get();
        settings.module = getModule().getOrNull();
        settings.namespace = getNamespace().getOrNull();
        settings.mapPackagesToNamespaces = getMapPackagesToNamespaces().getOrElse(false);
        settings.umdNamespace = getUmdNamespace().getOrNull();
        settings.moduleDependencies = moduleDependencies;
        settings.excludeClasses = getExcludeClasses().getOrNull();
        settings.excludeClassPatterns = getExcludeClassPatterns().getOrNull();
        settings.jsonLibrary = getJsonLibrary().getOrNull();
        settings.jackson2Configuration = jackson2Configuration;
        settings.gsonConfiguration = gsonConfiguration;
        settings.jsonbConfiguration = jsonbConfiguration;
        settings.additionalDataLibraries = getAdditionalDataLibraries().getOrNull();
        settings.optionalProperties = getOptionalProperties().getOrNull();
        settings.optionalPropertiesDeclaration = getOptionalPropertiesDeclaration().getOrNull();
        settings.nullabilityDefinition = getNullabilityDefinition().getOrNull();
        settings.declarePropertiesAsReadOnly = getDeclarePropertiesAsReadOnly().getOrElse(false);
        settings.removeTypeNamePrefix = getRemoveTypeNamePrefix().getOrNull();
        settings.removeTypeNameSuffix = getRemoveTypeNameSuffix().getOrNull();
        settings.addTypeNamePrefix = getAddTypeNamePrefix().getOrNull();
        settings.addTypeNameSuffix = getAddTypeNameSuffix().getOrNull();
        settings.customTypeNaming = Settings.convertToMap(getCustomTypeNaming().getOrNull(), "customTypeNaming");
        settings.customTypeNamingFunction = getCustomTypeNamingFunction().getOrNull();
        settings.referencedFiles = getReferencedFiles().getOrNull();
        settings.importDeclarations = getImportDeclarations().getOrNull();
        settings.customTypeMappings = Settings.convertToMap(getCustomTypeNaming().getOrNull(), "customTypeMapping");
        settings.customTypeAliases = Settings.convertToMap(getCustomTypeAliases().getOrNull(), "customTypeAlias");
        settings.mapDate = getMapDate().getOrNull();
        settings.mapMap = getMapMap().getOrNull();
        settings.mapEnum = getMapEnum().getOrNull();
        settings.enumMemberCasing = getEnumMemberCasing().getOrNull();
        settings.nonConstEnums = getNonConstEnums().getOrElse(false);
        settings.nonConstEnumAnnotations = getNonConstEnumAnnotations().getOrNull();
        settings.mapClasses = getMapClasses().getOrNull();
        settings.mapClassesAsClassesPatterns = getMapClassesAsClassesPatterns().getOrNull();
        settings.generateConstructors = getGenerateConstructors().getOrElse(false);
        settings.disableTaggedUnionAnnotations = getDisableTaggedUnionAnnotations().getOrNull();
        settings.disableTaggedUnions = getDisableTaggedUnions().getOrElse(false);
        settings.generateReadonlyAndWriteonlyJSDocTags = getGenerateReadonlyAndWriteonlyJSDocTags().getOrElse(false);
        settings.ignoreSwaggerAnnotations = getIgnoreSwaggerAnnotations().getOrElse(false);
        settings.generateJaxrsApplicationInterface = getGenerateJaxrsApplicationInterface().getOrElse(false);
        settings.generateJaxrsApplicationClient = getGenerateJaxrsApplicationClient().getOrElse(false);
        settings.generateSpringApplicationInterface = getGenerateSpringApplicationInterface().getOrElse(false);
        settings.generateSpringApplicationClient = getGenerateSpringApplicationClient().getOrElse(false);
        settings.scanSpringApplication = getScanSpringApplication().getOrElse(false);
        settings.restNamespacing = getRestNamespacing().getOrNull();
        settings.restNamespacingAnnotation = getRestNamespacingAnnotation().getOrNull();
        settings.restResponseType = getRestResponseType().getOrNull();
        settings.restOptionsType = getRestOptionsType().getOrNull();
        settings.customTypeProcessor = getCustomTypeProcessor().getOrNull();
        settings.sortDeclarations = getSortDeclarations().getOrElse(false);
        settings.sortTypeDeclarations = getSortTypeDeclarations().getOrElse(false);
        settings.noFileComment = getNoFileComment().getOrElse(false);
        settings.noTslintDisable = getNoTslintDisable().getOrElse(false);
        settings.noEslintDisable = getNoEslintDisable().getOrElse(false);
        settings.tsNoCheck = getTsNoCheck().getOrElse(false);
        settings.javadocXmlFiles = getJavadocXmlFiles().getOrNull();
        settings.extensions = getExtensionsList().getOrNull();
        settings.extensionClasses = getExtensionClasses().getOrNull();
        settings.extensionsWithConfiguration = extensionsWithConfiguration;
        settings.includePropertyAnnotations = getIncludePropertyAnnotations().getOrNull();
        settings.excludePropertyAnnotations = getExcludePropertyAnnotations().getOrNull();
        settings.optionalAnnotations = getOptionalAnnotations().getOrNull();
        settings.requiredAnnotations = getRequiredAnnotations().getOrNull();
        settings.nullableAnnotations = getNullableAnnotations().getOrNull();
        settings.primitivePropertiesRequired = getPrimitivePropertiesRequired().getOrElse(false);
        settings.generateInfoJson = getGenerateInfoJson().get();
        settings.infoJsonOutput = getJsonInfoOutputFile().getAsFile().get();
        settings.npmPackageOutput = getNpmPackageJsonOutputFile().getAsFile().get();
        settings.generateNpmPackageJson = getGenerateNpmPackageJson().get();
        settings.npmName = getGenerateNpmPackageJson().get() ? getNpmName().get() : null;
        settings.npmVersion = getGenerateNpmPackageJson().get() ? getNpmVersion().get() : null;
        settings.npmTypescriptVersion = getNpmTypescriptVersion().getOrNull();
        settings.npmBuildScript = getNpmBuildScript().getOrNull();
        settings.npmPackageDependencies = Settings.convertToMap(getNpmDependencies().getOrNull(), "npmDependencies");
        settings.npmDevDependencies = Settings.convertToMap(getNpmDevDependencies().getOrNull(), "npmDevDependencies");
        settings.npmPeerDependencies = Settings.convertToMap(getNpmPeerDependencies().getOrNull(), "npmPeerDependencies");
        settings.stringQuotes = getStringQuotes().getOrNull();
        settings.indentString = getIndentString().getOrNull();
        settings.jackson2ModuleDiscovery = getJackson2ModuleDiscovery().getOrElse(false);
        settings.jackson2Modules = getJackson2Modules().getOrNull();
        settings.loggingLevel = loggingLevel;
        return settings;
    }

    @org.gradle.api.tasks.CompileClasspath
    public abstract ConfigurableFileCollection getClasspath();


    @Inject
    abstract public WorkerExecutor getWorkerExecutor();

    @Nested
    @Optional
    public abstract Property<JavaLauncher> getLauncher();

    @TaskAction
    public void generate() {
        if (!getOutputKind().isPresent()) {
            throw new RuntimeException("Please specify 'outputKind' property.");
        }
        if (!getJsonLibrary().isPresent()) {
            throw new RuntimeException("Please specify 'jsonLibrary' property.");
        }

        TypeScriptGenerator.setLogger(new Logger(loggingLevel));
        TypeScriptGenerator.printVersion();

        // class loader
        WorkQueue workQueue = getWorkerExecutor().processIsolation(processWorkerSpec -> {
            processWorkerSpec.getClasspath().from(getClasspath());

            if(getLauncher().isPresent()) {
                processWorkerSpec.forkOptions(forkOptions -> forkOptions.setExecutable(getLauncher().get().getExecutablePath().getAsFile()));
            }
        });

        final InputSettings settings = createSettings();

        final InputParameters parameters = new InputParameters();
        parameters.classNames = getClasses().getOrNull();
        parameters.classNamePatterns = getClassPatterns().getOrNull();
        parameters.classesWithAnnotations = getClassesWithAnnotations().getOrNull();
        parameters.classesImplementingInterfaces = getClassesImplementingInterfaces().getOrNull();
        parameters.classesExtendingClasses = getClassesExtendingClasses().getOrNull();
        parameters.jaxrsApplicationClassName = getClassesFromJaxrsApplication().getOrNull();
        parameters.automaticJaxrsApplication = getClassesFromAutomaticJaxrsApplication().getOrElse(false);
        parameters.scanningAcceptedPackages = getScanningAcceptedPackages().getOrNull();
        parameters.debug = loggingLevel == Logger.Level.Debug;

        Settings.validateFileName(this.getOutputFileType().get(), this.getOutputFile().getAsFile().get());

        workQueue.submit(GenerateTypescriptWorker.class, configs -> {
            configs.getName().set(getProject().getName());
            configs.getInputParameters().set(parameters);
            configs.getOutput().set(this.getOutputFile());
            configs.getSettings().set(settings);
            configs.getClasspath().from(this.getClasspath());
        });
        workQueue.await();
    }
}
