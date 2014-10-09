package gr.telesto.data.smart;

public class Place {
	private String place_type;
	private String name;
	private String country_code;
	private String url;
	private String country;
	private String full_name;
	
	public Place (){
		
	}
	public Place(String place_type, String name, String country_code,String url, String country, String full_name ){
		this.setPlace_type(place_type);
		this.setName(name);
		this.setCountry_code(country_code);
		this.setUrl(url);
		this.setCountry(country);
		this.setFull_name(full_name);	
		
	}
	/**
	 * @return the place_type
	 */
	public String getPlace_type() {
		return place_type;
	}
	/**
	 * @param place_type the place_type to set
	 */
	public void setPlace_type(String place_type) {
		this.place_type = place_type;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the country_code
	 */
	public String getCountry_code() {
		return country_code;
	}
	/**
	 * @param country_code the country_code to set
	 */
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the full_name
	 */
	public String getFull_name() {
		return full_name;
	}
	/**
	 * @param full_name the full_name to set
	 */
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

}
