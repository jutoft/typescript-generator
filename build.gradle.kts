plugins {
    id("org.ajoberstar.reckon") version("0.16.1")
}
//allprojects {
//    version = "v4.0.0"
//}

// in either case
reckon {
    // START As of 0.16.0
    // what stages are allowed
    stages("rc", "final")
    // or use snapshots
//    snapshots()

    // how do you calculate the scope/stage
    setScopeCalc(calcScopeFromProp().or(calcScopeFromCommitMessages())) // fall back to commit message (see below) if no explicit prop set
    setStageCalc(calcStageFromProp())
//     these can also be arbitrary closures (see code for details)
//    scopeCalc = { inventory -> Optional.of(Scope.MAJOR) }
//    stageCalc = { inventory, targetNormal -> Optional.of('beta') }

    // END As of 0.16.0

    // START LEGACY
//    scopeFromProp()
//    stageFromProp('milestone', 'rc', 'final')

    // alternative to stageFromProp
//     snapshotFromProp()
    // END LEGACY

    // omit this to use the default of 'minor'
//    defaultInferredScope = 'patch'

    // omit this to use the deafult of 'patch'
    // if you use branches like maintenance/1.2.x, set this to 'minor'
    // if you use branches like maintenance/2.x, set this to 'major'
//    parallelBranchScope = 'minor'

    // omit to use default remote
//    remote = 'other-remote'

    // omit this to use the default of parsing tag names of the form 1.2.3 or v1.2.3
    // this is a String to Optional<Version> function
    // return an empty optional for tags you don't consider a relevant version
//    tagParser = tagName -> java.util.Optional.of(tagName)
//            .filter(name -> name.startsWith("project-a/"))
//            .map(name -> name.replace("project-a/", ""))
//            .flatMap(name -> org.ajoberstar.reckon.core.Version.parse(name))

    // omit this to use the default of writing tag names of the form 1.2.3
    // this is a Version to String function
//    tagWriter = version -> "project-a/" + version

    // omit this to use the default of tag messages including just the raw version, e.g. "1.2.3"
//    tagMessage = version.map(v -> "Version " + v)
}
