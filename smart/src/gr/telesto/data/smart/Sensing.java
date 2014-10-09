package gr.telesto.data.smart;

import java.util.ArrayList;

public class Sensing {
	public String arrayNames[] = {"colors","colorname"};
	public String names[]= {"crowd_density","sound_level","crowd_score","applause_score",
			"traffic_score","music_score","battery","light","temperature","totalCheckIns","hereNow"};
	private String crowd_density;
	private String sound_level;
	private String crowd_score;
	private String applause_score;
	private String traffic_score;
	private String music_score;
	private String battery;
	private String light;
	private String temperature;
	private String totalCheckIns;
	private String hereNow;
	private ArrayList<String> colors=new ArrayList<String>();
	private ArrayList<String> colorname=new ArrayList<String>();
	public Sensing(){
		
	}
	public Sensing(String crowd_density, String sound_level, String crowd_score, String applause_score, String traffic_score, String music_score,
			String battery,String light, String temperature, String totalCheckIns, String hereNow, ArrayList<String> colors, ArrayList<String> colorname){
		this.setCrowd_density(crowd_density);
		this.setSound_level(sound_level);
		this.setCrowd_score(crowd_score);
		this.setApplause_score(applause_score);
		this.setTraffic_score(traffic_score);
		this.setMusic_score(music_score);
		this.setColors(colors);
		this.setColorname(colorname);
		this.setBattery(battery);
		this.setLight(light);
		this.setTemperature(temperature);
		this.setTotalCheckIns(totalCheckIns);
		this.setHereNow(hereNow);
	}
	public String getCrowd_density() {
		return crowd_density;
	}
	public void setCrowd_density(String crowd_density) {
		this.crowd_density = crowd_density;
	}
	public String getSound_level() {
		return sound_level;
	}
	public void setSound_level(String sound_level) {
		this.sound_level = sound_level;
	}
	public String getCrowd_score() {
		return crowd_score;
	}
	public void setCrowd_score(String crowd_score) {
		this.crowd_score = crowd_score;
	}
	public String getApplause_score() {
		return applause_score;
	}
	public void setApplause_score(String applause_score) {
		this.applause_score = applause_score;
	}
	public String getTraffic_score() {
		return traffic_score;
	}
	public void setTraffic_score(String traffic_score) {
		this.traffic_score = traffic_score;
	}
	public String getMusic_score() {
		return music_score;
	}
	public void setMusic_score(String music_score) {
		this.music_score = music_score;
	}
	public ArrayList<String> getColors() {
		return colors;
	}
	public void setColors(ArrayList<String> colors) {
		this.colors = colors;
	}
	public ArrayList<String> getColorname() {
		return colorname;
	}
	public void setColorname(ArrayList<String> colorname) {
		this.colorname = colorname;
	}
	public String getBattery() {
		return battery;
	}
	public void setBattery(String battery) {
		this.battery = battery;
	}
	public String getLight() {
		return light;
	}
	public void setLight(String light) {
		this.light = light;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getTotalCheckIns() {
		return totalCheckIns;
	}
	public void setTotalCheckIns(String totalCheckIns) {
		this.totalCheckIns = totalCheckIns;
	}
	public String getHereNow() {
		return hereNow;
	}
	public void setHereNow(String hereNow) {
		this.hereNow = hereNow;
	}
	
}
