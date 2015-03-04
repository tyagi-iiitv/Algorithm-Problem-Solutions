public class Timer
{
  private double startTime; // Time when Timer started or reset

  // Create timer, initializing startTime with current time
  public Timer()
  {
    startTime = System.currentTimeMillis();
  }

  // Return number of miliseconds since last reset
  public double elapsedMilliseconds()
  {
    return System.currentTimeMillis() - startTime;
  }

  // Return number of seconds since last reset
  public double elapsedSeconds()
  {
    return this.elapsedMilliseconds() / 1000;
  }

  // Reset startTime
  public void reset()
  {
    startTime = System.currentTimeMillis();
  }
}
