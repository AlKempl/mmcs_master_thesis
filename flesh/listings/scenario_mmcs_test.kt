@Test
fun testScenarioLoadsMMCS() {
	val scenarioManager = ScenarioManager.getInstance(appContext)
	scenarioManager.initialize()
	assertEquals("mmcs", scenarioManager.scenarioPrefix)
	scenarioManager.clear()
}