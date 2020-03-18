package com.leopaulmartin.spring.leboncoinecole.persistence.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "addresses")
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private Long addressId;

	@Column(name = "label", length = 200, nullable = false)
	@Size(min = 5, max = 50, message = "Address's label must be longer than 5 characters and shorter than 50 characters")
	@NotNull(message = "Cannot be null")
	private String label;

	@Column(name = "zipcode", length = 5, nullable = false)
	@Size(min = 5, max = 5, message = "Zipcode size musth be 5 characters")
	@NotNull(message = "Cannot be null")
	private String zipCode;

	@Column(name = "city", length = 30, nullable = false)
	@NotNull(message = "Cannot be null")
	private String city;

	@Column(name = "country", length = 30, nullable = false)
	@NotNull(message = "Cannot be null")
	private String country;

	@Column(name = "longitude")
	private float longitude;

	@Column(name = "latitude")
	private float latitude;

	public Address() {
	}

	public Address(String label, String zipCode, String city, String country) {
		this.label = label;
		this.zipCode = zipCode;
		this.city = city;
		this.country = country;
	}

	public Address(String label, String zipCode, String city, String country, float longitude, float latitude) {
		this.label = label;
		this.zipCode = zipCode;
		this.city = city;
		this.country = country;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return "Address{" +
				"addressId=" + addressId +
				", label='" + label + '\'' +
				", zipCode='" + zipCode + '\'' +
				", city='" + city + '\'' +
				", country='" + country + '\'' +
				", longitude=" + longitude +
				", latitude=" + latitude +
				'}';
	}
}
