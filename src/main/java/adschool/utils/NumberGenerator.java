package adschool.utils;




public class NumberGenerator {
 public static final String getNumber(String Prefix , Long suffix , int patern){
	 return Prefix + GeneratorString.formatNumber(""+suffix, patern) ;
	/* DateTime dateTime = new DateTime();
     int year = dateTime.getYear();
     return Prefix + RandomStringUtils.randomNumeric(6).toUpperCase() + "-" + year;
*/
 }
}
