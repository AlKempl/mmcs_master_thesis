return object : CountDownTimer(millisInFuture.toLong() + duration, 1000) {
override fun onTick(millisUntilFinished: Long) {
  Log.d("CDT-" + this.hashCode(),"seconds remaining: " + millisUntilFinished / 1000)
}
override fun onFinish() {
  val desc = "obstacle finalization"
  Log.d("CDT-" + this.hashCode(), "action done: $desc")
  val obstaclesManager = ObstaclesManager.getInstance(context)
  obstaclesManager.finalize(this.hashCode())
}
}