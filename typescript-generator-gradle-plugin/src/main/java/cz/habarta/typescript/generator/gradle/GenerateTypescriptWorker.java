package cz.habarta.typescript.generator.gradle;

import cz.habarta.typescript.generator.*;
import cz.habarta.typescript.generator.util.Utils;
import org.gradle.workers.WorkAction;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class GenerateTypescriptWorker implements WorkAction<TypescriptGeneratorWorkParameters> {

    private Settings createSettings(URLClassLoader classLoader, InputSettings inputSettings) {
        final Settings settings = new Settings();
        if (inputSettings.outputFileType != null) {
            settings.outputFileType = inputSettings.outputFileType;
        }
        settings.outputKind = inputSettings.outputKind;
        settings.module = inputSettings.module;
        settings.namespace = inputSettings.namespace;
        settings.mapPackagesToNamespaces = inputSettings.mapPackagesToNamespaces;
        settings.umdNamespace = inputSettings.umdNamespace;
        settings.moduleDependencies = inputSettings.moduleDependencies;
        settings.setExcludeFilter(inputSettings.excludeClasses, inputSettings.excludeClassPatterns);
        settings.jsonLibrary = inputSettings.jsonLibrary;
        settings.setJackson2Configuration(classLoader, inputSettings.jackson2Configuration);
        settings.gsonConfiguration = inputSettings.gsonConfiguration;
        settings.jsonbConfiguration = inputSettings.jsonbConfiguration;
        settings.additionalDataLibraries = inputSettings.additionalDataLibraries;
        settings.optionalProperties = inputSettings.optionalProperties;
        settings.optionalPropertiesDeclaration = inputSettings.optionalPropertiesDeclaration;
        settings.nullabilityDefinition = inputSettings.nullabilityDefinition;
        settings.declarePropertiesAsReadOnly = inputSettings.declarePropertiesAsReadOnly;
        settings.removeTypeNamePrefix = inputSettings.removeTypeNamePrefix;
        settings.removeTypeNameSuffix = inputSettings.removeTypeNameSuffix;
        settings.addTypeNamePrefix = inputSettings.addTypeNamePrefix;
        settings.addTypeNameSuffix = inputSettings.addTypeNameSuffix;
        settings.customTypeNaming = inputSettings.customTypeNaming;
        settings.customTypeNamingFunction = inputSettings.customTypeNamingFunction;
        settings.referencedFiles = inputSettings.referencedFiles;
        settings.importDeclarations = inputSettings.importDeclarations;
        settings.customTypeMappings = inputSettings.customTypeMappings;
        settings.customTypeAliases = inputSettings.customTypeAliases;
        settings.mapDate = inputSettings.mapDate;
        settings.mapMap = inputSettings.mapMap;
        settings.mapEnum = inputSettings.mapEnum;
        settings.enumMemberCasing = inputSettings.enumMemberCasing;
        settings.nonConstEnums = inputSettings.nonConstEnums;
        settings.loadNonConstEnumAnnotations(classLoader, inputSettings.nonConstEnumAnnotations);
        settings.mapClasses = inputSettings.mapClasses;
        settings.mapClassesAsClassesPatterns = inputSettings.mapClassesAsClassesPatterns;
        settings.generateConstructors = inputSettings.generateConstructors;
        settings.loadDisableTaggedUnionAnnotations(classLoader, inputSettings.disableTaggedUnionAnnotations);
        settings.disableTaggedUnions = inputSettings.disableTaggedUnions;
        settings.generateReadonlyAndWriteonlyJSDocTags = inputSettings.generateReadonlyAndWriteonlyJSDocTags;
        settings.ignoreSwaggerAnnotations = inputSettings.ignoreSwaggerAnnotations;
        settings.generateJaxrsApplicationInterface = inputSettings.generateJaxrsApplicationInterface;
        settings.generateJaxrsApplicationClient = inputSettings.generateJaxrsApplicationClient;
        settings.generateSpringApplicationInterface = inputSettings.generateSpringApplicationInterface;
        settings.generateSpringApplicationClient = inputSettings.generateSpringApplicationClient;
        settings.scanSpringApplication = inputSettings.scanSpringApplication;
        settings.restNamespacing = inputSettings.restNamespacing;
        settings.setRestNamespacingAnnotation(classLoader, inputSettings.restNamespacingAnnotation);
        settings.restResponseType = inputSettings.restResponseType;
        settings.setRestOptionsType(inputSettings.restOptionsType);
        settings.loadCustomTypeProcessor(classLoader, inputSettings.customTypeProcessor);
        settings.sortDeclarations = inputSettings.sortDeclarations;
        settings.sortTypeDeclarations = inputSettings.sortTypeDeclarations;
        settings.noFileComment = inputSettings.noFileComment;
        settings.noTslintDisable = inputSettings.noTslintDisable;
        settings.noEslintDisable = inputSettings.noEslintDisable;
        settings.tsNoCheck = inputSettings.tsNoCheck;
        settings.javadocXmlFiles = inputSettings.javadocXmlFiles;
        settings.loadExtensions(classLoader, Utils.concat(inputSettings.extensionClasses, inputSettings.extensions), inputSettings.extensionsWithConfiguration);
        settings.loadIncludePropertyAnnotations(classLoader, inputSettings.includePropertyAnnotations);
        settings.loadExcludePropertyAnnotations(classLoader, inputSettings.excludePropertyAnnotations);
        settings.loadOptionalAnnotations(classLoader, inputSettings.optionalAnnotations);
        settings.loadRequiredAnnotations(classLoader, inputSettings.requiredAnnotations);
        settings.loadNullableAnnotations(classLoader, inputSettings.nullableAnnotations);
        settings.primitivePropertiesRequired = inputSettings.primitivePropertiesRequired;
        settings.generateInfoJson = inputSettings.generateInfoJson;
        settings.generateNpmPackageJson = inputSettings.generateNpmPackageJson;
        settings.npmName = inputSettings.npmName == null && inputSettings.generateNpmPackageJson ? getParameters().getName().get() : inputSettings.npmName;
        settings.npmVersion = inputSettings.npmVersion == null && inputSettings.generateNpmPackageJson ? settings.getDefaultNpmVersion() : inputSettings.npmVersion;
        settings.npmTypescriptVersion = inputSettings.npmTypescriptVersion;
        settings.npmBuildScript = inputSettings.npmBuildScript;
        settings.npmPackageDependencies = inputSettings.npmPackageDependencies;
        settings.npmDevDependencies = inputSettings.npmDevDependencies;
        settings.npmPeerDependencies = inputSettings.npmPeerDependencies;
        settings.setStringQuotes(inputSettings.stringQuotes);
        settings.setIndentString(inputSettings.indentString);
        settings.jackson2ModuleDiscovery = inputSettings.jackson2ModuleDiscovery;
        settings.loadJackson2Modules(classLoader, inputSettings.jackson2Modules);
        settings.classLoader = classLoader;
        return settings;
    }

    @Override
    public void execute() {
        TypeScriptGenerator.setLogger(new Logger(getParameters().getSettings().get().loggingLevel));
        TypeScriptGenerator.printVersion();

        final Set<URL> urls = new LinkedHashSet<>();

        for (File file : getParameters().getClasspath().getFiles()) {
            try {
                urls.add(file.toURI().toURL());
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        URLClassLoader classLoader = Settings.createClassLoader(getParameters().getName().get(), urls.toArray(new URL[0]), Thread.currentThread().getContextClassLoader());
        Settings settings = createSettings(classLoader, getParameters().getSettings().get());
        final InputParameters parameters = getParameters().getInputParameters().get();

        parameters.isClassNameExcluded = settings.getExcludeFilter();
        parameters.classLoader = classLoader;

        new TypeScriptGenerator(settings)
            .generateTypeScript(Input.from(parameters), Output.to(getParameters().getOutput().get().getAsFile()));
    }
}
