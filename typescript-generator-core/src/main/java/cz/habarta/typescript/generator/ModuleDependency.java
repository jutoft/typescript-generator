
package cz.habarta.typescript.generator;

import com.fasterxml.jackson.annotation.JsonInclude;
import cz.habarta.typescript.generator.util.Utils;
import java.io.File;
import java.io.Serializable;


@JsonInclude(JsonInclude.Include.NON_DEFAULT) 
public class ModuleDependency implements Serializable {

    public boolean global;
    public String importFrom;
    public String importAs;
    public File infoJson;
    public String npmPackageName;
    public String npmVersionRange;
    public boolean peerDependency;

    public ModuleDependency() {
    }

    private ModuleDependency(boolean global, String importFrom, String importAs, File infoJson, String npmPackageName, String npmVersionRange, boolean peerDependency) {
        this.global = global;
        this.importFrom = importFrom;
        this.importAs = importAs;
        this.infoJson = infoJson;
        this.npmPackageName = npmPackageName;
        this.npmVersionRange = npmVersionRange;
        this.peerDependency = peerDependency;
    }

    public static ModuleDependency module(String importFrom, String importAs, File infoJson, String npmPackageName, String npmVersionRange) {
        return new ModuleDependency(false, importFrom, importAs, infoJson, npmPackageName, npmVersionRange, false);
    }

    public static ModuleDependency global(File infoJson) {
        return new ModuleDependency(true, null, null, infoJson, null, null, false);
    }

    @Override
    public String toString() {
        return Utils.objectToString(this);
    }

    public String toShortString() {
        return global ? "global" : "'" + importFrom + "'";
    }

}
