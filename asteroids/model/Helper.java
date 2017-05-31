package asteroids.model;

/**
 * A class wiyh the sole purpose of containing often used aid-functions.
 *
 * @version 3.0
 * @author Brent De Bleser & Jesse Geens
 */
public class Helper {
	
	/**
     * Variable that determines whether or not debug output is enabled
     */
    private static boolean debug = true;
    
    /**
     * returns wheter or not debug is on;
     * 
     * @return returns the value of debug.
     * 		   | result == debug;
     */
    public static boolean debugIsOn(){
    	return debug;
    }
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
    public static boolean isValidDouble(double number){
        if(Double.isNaN(number) || Double.isInfinite(number) || number > Double.MAX_VALUE)
            return false;
        return true;
    }
    
    //Total
    /**
    * A helper method to solve quadratic equations.
    *
    * @param 	a
    *        	The factor of the second power term.
    * @param 	b
    *        	The factor of the first power term.
    * @param 	c
    *        	The factor of the zero power term.
    * @return   If a x^2 + b x +c doesn't have a solution
    *        	return an array of 3 elements with the first
    *        	two being 0.0 and the last being 1.0.
    *        	| if (4 * a *c > Math.pow(b, 2))
    *        	|      then x[0] = 0.0
    *        	|           x[1] = 0.0
    *        	|           x[2] = 1.0
    *        	|           result == x
    * @return	If a x^2 + b x + c has a solution terurn
    *        	an array with the first two elemnts being the two solutions
    *        	and the last elemnt being 1.0.
    *        	| if (4 * a *c > Math.pow(b, 2))
    *        	|       then double d = Math.pow(b, 2) + (4 * a *c)
    *        	|            x[0] = (-b + Math.sqrt(d)) / (2 * a)
    *        	|            x[1] = (-b - Math.sqrt(d)) / (2 * a)
    *        	|            x[2] = 0.0
    *        	|            result == x
    */
    public static double[] quadraticSolver(double a, double b, double c){
        double d = square(b) - (4 * a *c);
        double[] x = new double[3];
        if (d < 0){
            x[0] = 0.0;
            x[1] = 0.0;
            x[2] = 1.0;
            return x;
        }
        else{
            x[0] = ((-b + Math.sqrt(d)) / (2 * a));
            x[1] = ((-b - Math.sqrt(d)) / (2 * a));
            x[2] = 0.0;
            return x;
        }
    }
   
    //Nominal
    /**
     * A helper method to replace Math.pow(x, 2) with square(x).
     *
     * @param  x
     *         The number to square.
     * @pre    x must be a valid double.
     *         | Helper.isValidDouble(x)
     * @return x is squared.
     *         | result == Math.pow(x, 2)
     */
    public static double square(double x){
        return Math.pow(x, 2);
    }
   
}
