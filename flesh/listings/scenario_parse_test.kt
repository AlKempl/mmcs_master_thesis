@Test
fun testScenarioParses() {
	val scenarioManager = ScenarioManager.getInstance(appContext)
	scenarioManager.initialize()
	val scenario = scenarioManager.scenario
	assertEquals(true, scenarioManager.scenarioParsed)
	scenarioManager.clear()
}