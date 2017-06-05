package com.example.uniwien.gesunderleben;

import android.util.Log;

import java.io.File;
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
        File file = null;
        try {
            file = new File(MyApplication.getAppContext().getFilesDir(), User.file);
 //if (file.exists()) file.delete();
            Log.d("loadUser",  MyApplication.getAppContext().getFilesDir() + "/" + User.file);

            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            User.user = (User)ois.readObject();
            ois.close();
        }
        catch (FileNotFoundException e) {
            User.user = new User();
            return;
        }
        catch(ClassNotFoundException e) {
            if (file != null && file.exists()) file.delete();
            throw new IllegalArgumentException("Illegal DataSource");
        }
        catch (IOException e) {
            if (file != null && file.exists()) file.delete();
            throw new IllegalArgumentException("IO Exception in DataSource");
        }
    }

    public static void saveUser() throws IllegalArgumentException {
        try
        {
            File file = new File(MyApplication.getAppContext().getFilesDir(), User.file);
            Log.d("saveUser",  MyApplication.getAppContext().getFilesDir() + "/" + User.file);

            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(User.user);
            oos.flush();
            oos.close();
        }
        catch(Exception e)
        {
            //e.printStackTrace();
            Log.e("Some Tag", e.getMessage(), e);
            throw new IllegalArgumentException("Serialization Exception");
        }
    }







    // instance properties
    private Date dateRegistered = null;
    public boolean getRegistered() { return (dateRegistered != null); }
    public void setRegistered() { dateRegistered = getCurrentDate(); }

    private int age = -1;
    public int getAge() { return age; }
    public void setAge(int value) { age = value; }

    private boolean gender = true;
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

    public void resetData() {
        dateRegistered = null;
        params.clear();
    }

    // map of food data

    // map of activities data

}
