package asteroids.model;

import be.kuleuven.cs.som.annotate.Model;

public class Helper {
	 //Total
	/**
     * Variable that determines whether or not debug output is enabled
     */
    public static boolean debug = false;
    
    /**
     * A helper method to make a small errorMessage
     *
     * @param errorMessage
     *        The error message that needs to be shown
     * @post  If debug equals true the errorMessage is shown.
     *        | if debug == true
     *        |     then System.out.println(errorMessage)
     * @post  If debug equals false the errorMessage isn't shown.
     *        | if debug == true
     *        |     then nothing
     */
    public static void log(String errorMessage){
        if(debug) System.out.println(errorMessage);
    }
    
    /**
     * Check whether the given number is a double.
     *
     * @param  number
     *         The double to check.
     * @return True if and only if the given double is a finite number
     *         | result == (!(Double.isNaN(number) || (Double.isInfinite(number))))
     */
    @Model
    public static boolean isValidDouble(double number){
        if(Double.isNaN(number) || Double.isInfinite(number) || !(number < Double.MAX_VALUE))
            return false;
        return true;
    }
}
