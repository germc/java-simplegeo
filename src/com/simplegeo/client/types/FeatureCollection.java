package com.simplegeo.client.types;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FeatureCollection {

	private ArrayList<Feature> features;
	
	public FeatureCollection() {
		
	}
	
	public FeatureCollection(ArrayList<Feature> features) {
		this.features = features;
	}

	public ArrayList<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(ArrayList<Feature> features) {
		this.features = features;
	}
	
	public static FeatureCollection fromJson(JSONObject json) throws JSONException {
		FeatureCollection featureCollection = new FeatureCollection();
		int numOfFeatures = json.getInt("total");
		ArrayList<Feature> features = new ArrayList<Feature>();
		JSONArray featuresArray = json.getJSONArray("features");
		for (int i=0; i<numOfFeatures; i++) {
			features.add(Feature.fromJson(featuresArray.getJSONObject(i)));
		}
		featureCollection.setFeatures(features);
		return featureCollection;
	}
	
	public static FeatureCollection fromJsonString(String jsonString) throws JSONException {
		return fromJson(new JSONObject(jsonString));
	}
	
	public JSONArray toJson() throws JSONException {
		JSONArray jsonArray = new JSONArray();
		ArrayList<Feature> features = this.getFeatures();
		for (Feature feature : features) {
			jsonArray.put(feature.toJson());
		}
		return jsonArray;
	}
	
	public String toJsonString() throws JSONException {
		return this.toJson().toString();
	}
	
}