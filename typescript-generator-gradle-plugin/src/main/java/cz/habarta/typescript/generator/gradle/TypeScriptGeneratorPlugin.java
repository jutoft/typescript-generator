package cz.habarta.typescript.generator.gradle;

import org.gradle.api.NamedDomainObjectProvider;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.JavaPluginExtension;
import org.gradle.api.provider.Provider;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.SourceSetContainer;
import org.gradle.api.tasks.TaskProvider;
import org.gradle.jvm.toolchain.JavaLauncher;
import org.gradle.jvm.toolchain.JavaToolchainService;
import org.gradle.jvm.toolchain.JavaToolchainSpec;


public class TypeScriptGeneratorPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        final TaskProvider<GenerateTask> generateTsTask = project.getTasks().register("generateTypeScript", GenerateTask.class);

        project.getPlugins().withType(JavaPlugin.class).configureEach(javaPlugin -> generateTsTask.configure(generateTask -> {
            // Access the default toolchain
            JavaToolchainSpec toolchain = project.getExtensions().getByType(JavaPluginExtension.class).getToolchain();

            // acquire a provider that returns the launcher for the toolchain
            JavaToolchainService service = project.getExtensions().getByType(JavaToolchainService.class);
            Provider<JavaLauncher> defaultLauncher = service.launcherFor(toolchain);

            // use it as our default for the property
            generateTask.getLauncher().convention(defaultLauncher);
            NamedDomainObjectProvider<SourceSet> mainSourceSet = project.getExtensions().getByType(SourceSetContainer.class).named(SourceSet.MAIN_SOURCE_SET_NAME);
            generateTask.getClasspath().from(mainSourceSet.get().getOutput().getClassesDirs());
            generateTask.getClasspath().from(mainSourceSet.get().getCompileClasspath());
        }));
    }

}
