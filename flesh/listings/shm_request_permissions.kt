private fun requestPermission() {
  val pmsManager = HealthPermissionManager(healthDataStore)

  // Show user permission UI for allowing user to change options
  runCatching { pmsManager.requestPermissions(permissionKeySet, this) }
    .onFailure { Log.e(TAG, "Permission setting fails.", it) }
    .getOrNull()
    ?.setResultListener(mPermissionListener)
}