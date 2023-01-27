package cz.habarta.typescript.generator.gradle;

import org.gradle.api.NamedDomainObjectProvider;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.SourceSetContainer;
import org.gradle.api.tasks.TaskProvider;


public class TypeScriptGeneratorPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        final TaskProvider<GenerateTask> generateTsTask = project.getTasks().register("generateTypeScript", GenerateTask.class);

        project.getPlugins().withType(JavaPlugin.class).configureEach(javaPlugin -> generateTsTask.configure(generateTask -> {
            NamedDomainObjectProvider<SourceSet> mainSourceSet = project.getExtensions().getByType(SourceSetContainer.class).named(SourceSet.MAIN_SOURCE_SET_NAME);
            generateTask.getClasspath().from(mainSourceSet.get().getOutput().getClassesDirs());
            generateTask.getClasspath().from(mainSourceSet.get().getCompileClasspath());
        }));
    }

}
