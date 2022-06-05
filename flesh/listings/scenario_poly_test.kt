@Test
fun testScenarioHasPolymorphActions() {
  val scenarioManager = ScenarioManager.getInstance(appContext)
  scenarioManager.initialize()
  assertEquals(true, scenarioManager.scenarioParsed)
  val scenario = scenarioManager.scenario
  assert(scenario != null)
  scenario?.let {
    assert(scenario.chapters != null)
    it.chapters?.let {
      assert(it.isNotEmpty())
      val firstChapter = it[0]
      firstChapter.let {
        assert(it.initial_event != null)
        it.initial_event?.let {
          assert(it.actions != null)
          it.actions?.let {
            assert(it.size > 1)
            assert(
              (it[0] is GenerateObstacleChapterEventAction)
                  or (it[0] is MusicChapterEventAction)
            )
            assert(
              (it[1] is GenerateObstacleChapterEventAction)
                  or (it[1] is MusicChapterEventAction)
            )
          }
        }
      }
    }
  }
  scenarioManager.clear()
}