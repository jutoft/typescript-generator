plugins {
    id("org.ajoberstar.reckon") version("0.16.1")
}

reckon {
    stages("rc", "final")
    setScopeCalc(calcScopeFromProp().or(calcScopeFromCommitMessages()))
    setStageCalc(calcStageFromProp())
}
