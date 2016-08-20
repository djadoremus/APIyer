package com.apiyer.app.controller;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;
import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class APIController {

//	@RequestMapping(value="/{user}/{service}/{format}", method = RequestMethod.GET)
//	public String getService(@PathVariable("user") final String user,
//			@PathVariable("service") final String service,
//			@PathVariable("format") final String format,
//			HttpServletRequest request) {
	@RequestMapping(value="/{service}/{format}", method = RequestMethod.GET)
	public Callable<String> getService(@PathVariable("service") final String service,
			@PathVariable("format") final String format,
			HttpServletRequest request) {
            // get parameters
            Map<String, String[]> params = request.getParameterMap();
            Callable<String> callback = new Callable<String>() {
                @Override
                public String call() throws Exception {
                    final JSONArray dbRefList = new JSONArray();
                    
                    DatabaseReference apiRef = FirebaseDatabase.getInstance().getReference("apis");
                    DatabaseReference serviceRef = apiRef.child(service);
                    
                    
                    serviceRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot ds) {
                            HashMap<String, HashMap<String, String>> list = (HashMap<String, HashMap<String, String>>) ds.getValue();
                            System.out.println("list.size " + list.size());

                            Iterator itr = list.keySet().iterator();
                            while(itr.hasNext()){
                                String key = (String) itr.next();
                                String obj = "{_id:" + key + ",";
                                
                                HashMap<String, String> val = list.get(key);
                                System.out.println("key " + key + " | val " + val);
                                
                                Iterator itrVal = val.keySet().iterator();
                                while(itrVal.hasNext()){
                                    String valKey = (String) itrVal.next();
                                    String valVal = val.get(valKey);
                                    obj += valKey + ":" + valVal +",";
                                }
                                obj += "}";
                                JSONObject jsonObject = new JSONObject(obj);
                                dbRefList.put(jsonObject);
                            }
                           System.out.println("dbRefList " + dbRefList.length());

    //                        GenericTypeIndicator<List<HashMap<String, String>>> gti = new GenericTypeIndicator<List<HashMap<String, String>>>(){};
    //                        HashMap<String, String> val = (HashMap<String, String>) ds.getValue(gti);
    //                        System.out.println(val.size());
                        }

                        @Override
                        public void onCancelled(DatabaseError de) {
                            System.out.println("The read failed: " + de.getCode());
                        }
                    });

                    Thread.sleep(15 * 1000);
                    return dbRefList.toString();
                }
            };
            return callback;
	}
}