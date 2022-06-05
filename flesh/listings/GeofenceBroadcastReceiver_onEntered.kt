private fun onGoodGeofenceEntered(
        enteredGeofence: GeofenceEntry,
        nextGeofence: GeofenceEntry?,
        context: Context
) {
	val ttsEnteredDesc = enteredGeofence.getEnteredText(TextContentType.VOICE)
	val enteredDesc = enteredGeofence.getEnteredText(TextContentType.ONSCREEN)
	val nextDesc = nextGeofence?.getTargetedText(TextContentType.ONSCREEN) ?: ""
	val ttsNextDesc = nextGeofence?.getTargetedText(TextContentType.VOICE) ?: ""

	val pattern = longArrayOf(0, 200, 100, 300)
	vibrate(context, pattern)

	ttsManager.speak("$ttsEnteredDesc .. $ttsNextDesc")

	Toast.makeText(
		context,
		"$enteredDesc: $nextDesc",
		Toast.LENGTH_LONG
	).show()

	//        TODO: process events on entering
	actionsManager.clearGeofenceTimers()

	enteredGeofence.events?.forEach { event ->
		event.actions?.forEach { action ->
			actionsManager.processEventAction(event, action, true)
		}
	}
}