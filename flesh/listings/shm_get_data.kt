private fun readStepCount(startTime: Long) {
  // Get sum of step counts by device
  val request = AggregateRequest.Builder()
    .setDataType(StepCount.HEALTH_DATA_TYPE)
    .addFunction(AggregateFunction.SUM, StepCount.COUNT, ALIAS_TOTAL_COUNT)
    .addGroup(StepCount.DEVICE_UUID, ALIAS_DEVICE_UUID)
    .setLocalTimeRange(StepCount.START_TIME, StepCount.TIME_OFFSET, startTime, startTime + TIME_INTERVAL)
    .setSort(ALIAS_TOTAL_COUNT, SortOrder.DESC)
    .build()

  runCatching { healthDataResolver.aggregate(request) }
    .onFailure { Log.e(TAG, "Getting step count fails.", it) }
    .getOrNull()
    ?.setResultListener {
      it.use {
        it.firstOrNull()
          .also { observer.onChanged(it?.getInt(ALIAS_TOTAL_COUNT) ?: 0) }
          ?.let { readStepCountBinning(startTime, it.getString(ALIAS_DEVICE_UUID)) }
          ?: observer.onBinningDataChanged(emptyList())
      }
    }
}