package cz.habarta.typescript.generator.gradle;

import cz.habarta.typescript.generator.*;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InputSettings implements Serializable {
    public String indentString = "    ";
    public TypeScriptFileType outputFileType = TypeScriptFileType.declarationFile;
    public TypeScriptOutputKind outputKind = null;
    public String module = null;
    public String namespace = null;
    public boolean mapPackagesToNamespaces = false;
    public String umdNamespace = null;
    public List<ModuleDependency> moduleDependencies = new ArrayList<>();
    public List<String> jackson2Modules;
    public List<String> extensions;
    public List<Settings.ConfiguredExtension> extensionsWithConfiguration;
    public List<String> includePropertyAnnotations;
    public List<String> excludePropertyAnnotations;
    public List<String> optionalAnnotations;
    public List<String> requiredAnnotations;
    public List<String> nullableAnnotations;
    public JsonLibrary jsonLibrary = null;
    public GsonConfiguration gsonConfiguration;
    public JsonbConfiguration jsonbConfiguration;
    public List<String> additionalDataLibraries = new ArrayList<>();
    public OptionalProperties optionalProperties; // default is OptionalProperties.useSpecifiedAnnotations
    public OptionalPropertiesDeclaration optionalPropertiesDeclaration; // default is OptionalPropertiesDeclaration.questionMark
    public NullabilityDefinition nullabilityDefinition; // default is NullabilityDefinition.nullInlineUnion
    public boolean declarePropertiesAsReadOnly = false;
    public String removeTypeNamePrefix = null;
    public String removeTypeNameSuffix = null;
    public String addTypeNamePrefix = null;
    public String addTypeNameSuffix = null;
    public Map<String, String> customTypeNaming = new LinkedHashMap<>();
    public String customTypeNamingFunction = null;
    public List<String> referencedFiles = new ArrayList<>();
    public List<String> importDeclarations = new ArrayList<>();
    public Map<String, String> customTypeMappings = new LinkedHashMap<>();
    public List<String> excludeClassPatterns;
    public Jackson2Configuration jackson2Configuration;
    public List<String> excludeClasses;
    public List<String> extensionClasses;
    public Map<String, String> customTypeAliases = new LinkedHashMap<>();
    public DateMapping mapDate; // default is DateMapping.asDate
    public MapMapping mapMap; // default is MapMapping.asIndexedArray
    public EnumMapping mapEnum; // default is EnumMapping.asUnion
    public IdentifierCasing enumMemberCasing; // default is IdentifierCasing.keepOriginal
    public boolean nonConstEnums = false;
    public List<String> nonConstEnumAnnotations = new ArrayList<>();
    public ClassMapping mapClasses; // default is ClassMapping.asInterfaces
    public List<String> mapClassesAsClassesPatterns;
    public boolean generateConstructors = false;
    public List<String> disableTaggedUnionAnnotations = new ArrayList<>();
    public boolean disableTaggedUnions = false;
    public boolean generateReadonlyAndWriteonlyJSDocTags = false;
    public boolean ignoreSwaggerAnnotations = false;
    public boolean generateJaxrsApplicationInterface = false;
    public boolean generateJaxrsApplicationClient = false;
    public boolean generateSpringApplicationInterface = false;
    public boolean generateSpringApplicationClient = false;
    public boolean scanSpringApplication;
    public RestNamespacing restNamespacing;
    public String restNamespacingAnnotation = null;
    public String restResponseType = null;
    public String restOptionsType = null;
    public String customTypeProcessor = null;
    public boolean sortDeclarations = false;
    public boolean sortTypeDeclarations = false;
    public boolean noFileComment = false;
    public boolean noTslintDisable = false;
    public boolean noEslintDisable = false;
    public boolean tsNoCheck = false;
    public List<File> javadocXmlFiles = null;
    public boolean primitivePropertiesRequired = false;
    public boolean generateInfoJson = false;
    public boolean generateNpmPackageJson = false;
    public String npmName = null;
    public String npmVersion = null;
    public Map<String, String> npmPackageDependencies = new LinkedHashMap<>();
    public Map<String, String> npmDevDependencies = new LinkedHashMap<>();
    public Map<String, String> npmPeerDependencies = new LinkedHashMap<>();
    public String npmTypescriptVersion = null;
    public String npmBuildScript = null;
    public boolean jackson2ModuleDiscovery = false;

    public StringQuotes stringQuotes;
    public Logger.Level loggingLevel;
}
