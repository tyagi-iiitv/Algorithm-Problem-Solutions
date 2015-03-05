public class CountingSundays {
  public static int[] daysInMonth = {31,28,31,30,31,30,31,31,30,31,30,31};
  
  public static void main( String[] args ) {
    System.out.println(countingSundays());
  }
  
  public static int countingSundays() {
    int day = 0; // 0 to daysInMonth[month] - 1
    int month = 0; // 0 to 12
    int year = 1901;
    int weekday = 2; // 0 to 6
    int count = 0;
    while(year != 2000 || month != 11 || day != 30) {
      if(day == 0 && weekday == 0) {
        ++count;
      }
      if(++day == daysInMonth[month]) {
        day = 0;
        ++month;
      }
      if(month == 12) {
        month = 0;
        ++year;
      }
      if(++weekday == 7) {
        weekday = 0;
      }
    }
    return count;
  }
}