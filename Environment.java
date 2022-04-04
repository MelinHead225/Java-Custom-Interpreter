import java.util.HashMap;

/**
 * Serves as a enviorment for holding variables
 * Utilized hashmap in order to allow the program to run multiple statements
 * Extended to support double values
 * @author ericm
 */
public class Environment {

    //initialize a hashmap to hold variables at different locations
    private HashMap<String, Double> map = new HashMap<String, Double>();

    /**
     * insert a variable into the enviorment
     */
    public double put(String var, double val) { 
        map.put(var, val);
        return val; 
    }
    
    /**
     * get a variable from the enviornment
     */
    public double get(int pos, String var) throws EvalException { 
        if(map.containsKey(var)) {
            return map.get(var); 
        } else {
            throw new EvalException(pos, "error found in enviornment class!");
        }
    }

}
