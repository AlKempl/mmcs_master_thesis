val geofencingEvent = GeofencingEvent.fromIntent(intent)

if (geofencingEvent.hasError()) {
  val errorMessage = errorMessage(context, geofencingEvent.errorCode)
  return
}

if (geofencingEvent.geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER) {

  val fenceId = when {
	geofencingEvent.triggeringGeofences.isNotEmpty() ->
	  geofencingEvent.triggeringGeofences[0].requestId
	else -> { return }
  }

  val geofencingManager =
	GeofencingManager.getInstance(context, Executors.newSingleThreadExecutor())

  val foundIndex = geofencingManager.getStored().indexOfFirst {
	it.id == fenceId
  }

  // Unknown Geofences aren't helpful to us
  if (-1 == foundIndex) { return }

  if (geofencingManager.getActiveIdx() != foundIndex) {
	return
  }

  ttsManager = TTSManager.getInstance(context)
  actionsManager = ActionsManager.getInstance(context)
  scenarioManager = ScenarioManager.getInstance(context)

  val enteredGeofence = geofencingManager.getActiveEntry()
  geofencingManager.processNext()
  val nextGeofence = geofencingManager.getActiveEntry()

  enteredGeofence?.let {
	onGoodGeofenceEntered(it, nextGeofence, context)
  }

  if (nextGeofence == null) { onChapterFinished(context) }
}
