package cz.habarta.typescript.generator.gradle;

import cz.habarta.typescript.generator.Input;
import org.gradle.api.file.ConfigurableFileCollection;
import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.provider.Property;
import org.gradle.workers.WorkParameters;

public interface TypescriptGeneratorWorkParameters extends WorkParameters {
    Property<String> getName();
    Property<Input.Parameters> getInputParameters();
    Property<InputSettings> getSettings();
    RegularFileProperty getOutput();
    ConfigurableFileCollection getClasspath();
}
