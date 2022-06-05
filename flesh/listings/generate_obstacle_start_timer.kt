return object : CountDownTimer(millisInFuture.toLong(), 1000) {
override fun onTick(millisUntilFinished: Long) {
  Log.d("CDT-" + this.hashCode(),"seconds remaining: " + millisUntilFinished / 1000)
}
override fun onFinish() {
  val obstaclesManager = ObstaclesManager.getInstance(context)
  val obstacle = ObstacleFactory.buildObstacle(type, context, duration)
  val desc = "obstacle generation"
  obstaclesManager.setCurrent(obstacle, this.hashCode())
}
}