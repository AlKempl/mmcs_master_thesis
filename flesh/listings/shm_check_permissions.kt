private val permissionKeySet: Set<PermissionKey> =
  setOf(
    PermissionKey(HealthConstants.HeartRate.HEALTH_DATA_TYPE, PermissionType.READ),
    PermissionKey(HealthConstants.StepCount.HEALTH_DATA_TYPE, PermissionType.READ))
      
// Check whether the permissions that this application needs are acquired
private fun checkPermissionsAcquired(): Boolean {
  val pmsManager = HealthPermissionManager(healthDataStore)

  // Check whether the permissions that this application needs are acquired
  return runCatching { pmsManager.isPermissionAcquired(permissionKeySet) }
    .onFailure { Log.e(TAG, "Permission request fails.", it) }
    .map { it.values.all { it } }
    .getOrDefault(false)
}