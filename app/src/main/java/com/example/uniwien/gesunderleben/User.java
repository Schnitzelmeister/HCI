package com.example.uniwien.gesunderleben;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Collections;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;


/**
 * User
 */

public class User implements Serializable {

    //static User object
    public static User user;

    //File, to save the user data
    private static final String file = "user.dat";

    //Serialization methods
    public static void loadUser() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(User.file));
            User.user = (User)ois.readObject();
            ois.close();
        }
        catch (FileNotFoundException e) {
            User.user = new User();
            return;
        }
        catch(ClassNotFoundException e) {
            throw new IllegalArgumentException("Illegal DataSource");
        }
        catch (IOException e) {
            throw new IllegalArgumentException("IO Exception in DataSource");
        }
    }

    public static void saveUser() throws IllegalArgumentException {
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(User.file));
            oos.writeObject(User.user);
            oos.flush();
            oos.close();
        }
        catch(Exception e)
        {
            //e.printStackTrace();
            throw new IllegalArgumentException("Serialization Exception occured");
        }
    }







    // instance properties
    private boolean registred = false;
    public boolean getRegistred() { return registred; }
    public void setRegistred(boolean value) { registred = value; }


    private boolean gender;
    public boolean getGender() { return gender; }
    public void setGender(boolean value) { gender = value; }

    protected Date getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    // map of params by Date and access methods
    private TreeMap<Date, TreeMap<ParameterEnum, Object> > params = new TreeMap<Date, TreeMap<ParameterEnum, Object> > (Collections.reverseOrder());

    public Object getActualParam(ParameterEnum param) {

        for(Iterator< Entry< Date, TreeMap<ParameterEnum, Object> >> ito = params.entrySet().iterator(); ito.hasNext(); ) {
            TreeMap<ParameterEnum, Object> p = ito.next().getValue();
            if (p.containsKey(param))
                return p.get(param);
        }

        return null;
    }

    public void setActualParam(ParameterEnum param, Object value) {
        Date current = getCurrentDate();

        if (!params.containsKey(current))
            params.put(current, new TreeMap<ParameterEnum, Object>() );

        params.get(current).put(param, value);
    }

    // map of food data

    // map of activities data

}
