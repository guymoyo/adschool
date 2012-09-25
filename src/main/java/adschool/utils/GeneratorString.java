package adschool.utils;

import java.util.Date;



public class GeneratorString {
	@SuppressWarnings("deprecation")
	public static final String getCipM(String suffix){
		Date date = new Date();
		int year = date.getYear(); 
		 int annee = year-100 ;
		  String month = formatNumber(""+(date.getMonth()+1), 2) ;
		
		// String month = formatNumber(""+Calendar.MONTH, 2) ;
		suffix="5000";
		 return  new StringBuffer().append(annee).append(month).append(GeneratorString.formatNumber(suffix, 5)).toString();
	 }
	
	public static String formatNumber(String stringToFormat , int patern){
	String format  = null ;
		if (stringToFormat.length() > patern-1) {
			return stringToFormat ;
			
		}else {
			
			format = new StringBuilder().append(GeneratorString.getZero(patern-stringToFormat.length()-1)).append(stringToFormat).toString();
		}
		return format.trim();
	}
	
	public static String getZero(int nbOfZero){
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i <= nbOfZero; i++) {
			stringBuilder.append(0);
			
		}
		return stringBuilder.toString();
	}
	
	
	
}
