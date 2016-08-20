/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apiyer.app.utils;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author adoremus
 */
public class CsvToFirebaseDb {
    private File file;
    
    public String parseToJSON(File file){
        JSONArray json = new JSONArray();
        LinkedList<HashMap<String, String>> list = new LinkedList<HashMap<String, String>>();
        try {
            this.file = file;
            System.out.println(file.getAbsolutePath());
            BufferedReader br = new BufferedReader(new FileReader(this.file));
            String line = null;
            Boolean isHeader = Boolean.TRUE;
            LinkedList<String> keys = new LinkedList<String>();
            
            int counter = 1;
            while ((line = br.readLine()) != null){
//                http://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes
                String[] vals = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (isHeader){
                    for (int i=0; i<vals.length; i++){
                        keys.add(vals[i]);
                    }
                    isHeader = Boolean.FALSE;
                } else {
                    HashMap<String, String> hm = new HashMap<String, String>();
                    for (int i=0; i<vals.length; i++){
                        hm.put(keys.get(i), vals[i]);
//                        System.out.println("i " + i + " | key " + keys.get(i) + " | vals " + vals[i]);
                    }
                    list.add(hm);
                }
                counter++;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CsvToFirebaseDb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CsvToFirebaseDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("list size = " + list.size());
        
        String fName = this.file.getName().replace(".csv", "");
        DatabaseReference apiRef = FirebaseDatabase.getInstance().getReference("apis");
        for (HashMap<String, String> val : list){
            DatabaseReference fileAPIRef = apiRef.child(fName).push();
            fileAPIRef.setValue(val);
        }
        
        
        return json.toString();
    }
}
