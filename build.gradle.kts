plugins {
    id("org.ajoberstar.reckon") version("0.18.2")
}

reckon {
    stages("rc", "final")
    setScopeCalc(calcScopeFromProp().or(calcScopeFromCommitMessages()))
    setStageCalc(calcStageFromProp())
}
