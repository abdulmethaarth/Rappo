package com.affinity.rappo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Users extends BaseResponse {

    @SerializedName("result")
    @Expose
    private List<LoginUserDetails> userArray = null;

    public List<LoginUserDetails> getUserArray() {
        return userArray;
    }

    public void setUserArray(List<LoginUserDetails> userArray) {
        this.userArray = userArray;
    }

    public class LoginUserDetails {

        @SerializedName("id")
        @Expose
        private String id;

        @SerializedName("firstname")
        @Expose
        private String firstname;

        @SerializedName("lastname")
        @Expose
        private String lastname;

        @SerializedName("email")
        @Expose
        private String email;

        @SerializedName("mobile")
        @Expose
        private String mobile;

        @SerializedName("password")
        @Expose
        private String password;

        @SerializedName("updated_at")
        @Expose
        private String updated_at;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }
}
