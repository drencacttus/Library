package library;

import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class Book {
    
    private String  name;
    private Date    dueDate;
    private float   chargePerDay;
    private float   maxCharge;

    public Book(String name, Date dueDate) {
        
        this.name = name;
        this.dueDate = dueDate;
        this.chargePerDay = 1;
        this.maxCharge = 100;
    }

    public Book(String name, Date dueDate, float chargePerDay, float maxCharge) {
        
        this.name = name;
        this.dueDate = dueDate;
        this.chargePerDay = chargePerDay;
        this.maxCharge = maxCharge;
    }
    
    public Book(JSONObject bookObject) {
    
        try{
            this.name = bookObject.getString("title");
            this.chargePerDay = (float)bookObject.getDouble("chargePerDay");
            this.maxCharge = (float)bookObject.getDouble("maxCharge");

            JSONObject returnDate = bookObject.getJSONObject("returnDate");

            this.dueDate = new Date(returnDate.getInt("year"), 
                                    returnDate.getInt("month"), 
                                    returnDate.getInt("day"));
        }
        catch(JSONException ex) {
        
            this.name = "Unknown book";
        }
    }

    public float getOverdueCharge(Date returnDate) {
    
        if(returnDate.after(dueDate)) {
        
            long overdueTime = returnDate.getTime() -
                               dueDate.getTime();
            
            overdueTime /= 1000 * 60 * 60 * 24;
            
            float overdueCharge = overdueTime * chargePerDay;
            
            if(overdueCharge > maxCharge){
                
                return maxCharge;
            }
            else {
                
                return overdueCharge;
            }
        }
        else {
        
            return 0;
        }
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public float getChargePerDay() {
        return chargePerDay;
    }

    public void setChargePerDay(float chargePerDay) {
        this.chargePerDay = chargePerDay;
    }
    
    public JSONObject getJSONObject() {
    
        try{
            
            JSONObject bookObject = new JSONObject();

            bookObject.put("title", name);
            bookObject.put("chargePerDay", chargePerDay);
            bookObject.put("maxCharge", maxCharge);

            JSONObject returnDate = new JSONObject();
            returnDate.put("day", dueDate.getDay());
            returnDate.put("month", dueDate.getMonth());
            returnDate.put("year", dueDate.getYear());

            bookObject.put("returnDate", returnDate);
           
            return bookObject;
        }
        catch(JSONException ex) {
        
            return null;
        }
    }
}
