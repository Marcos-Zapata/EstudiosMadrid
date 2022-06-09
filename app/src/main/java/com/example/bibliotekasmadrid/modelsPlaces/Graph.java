package com.example.bibliotekasmadrid.modelsPlaces;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Graph implements Parcelable {
    @SerializedName("@id")
    @Expose
    private String id;
    @SerializedName("@type")
    @Expose
    private String type;
    @SerializedName("id")
    @Expose
    private String idG;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("relation")
    @Expose
    private String relation;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("organization")
    @Expose
    private Organization organization;

    protected Graph (@NonNull Parcel parcel){
        title = parcel.readString();
        Address address = new Address();
        address.setLocality(parcel.readString());
        address.setPostalCode(parcel.readString());
        address.setStreetAddress(parcel.readString());
        this.address = address;
        Location location = new Location();
        location.setLatitude(parcel.readDouble());
        location.setLongitude(parcel.readDouble());
        this.location = location;
        Organization organization = new Organization();
        organization.setOrganizationDesc(parcel.readString());
        organization.setSchedule(parcel.readString());
        organization.setServices(parcel.readString());
        organization.setOrganizationName(parcel.readString());
        this.organization = organization;
    }

    public static final Creator<Graph> CREATOR = new Creator<Graph>() {
        @Override
        public Graph createFromParcel(Parcel parcel) {
            return new Graph(parcel);
        }

        @Override
        public Graph[] newArray(int i) {
            return new Graph[0];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdG() {
        return idG;
    }

    public void setIdG(String idG) {
        this.idG = idG;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getTitle());
        parcel.writeString(getAddress().getLocality());
        parcel.writeString(getAddress().getPostalCode());
        parcel.writeString(getAddress().getStreetAddress());
        parcel.writeDouble(getLocation().getLatitude());
        parcel.writeDouble(getLocation().getLongitude());
        parcel.writeString(getOrganization().getOrganizationDesc());
        parcel.writeString(getOrganization().getSchedule());
        parcel.writeString(getOrganization().getServices());
        parcel.writeString(getOrganization().getOrganizationName());
    }
}