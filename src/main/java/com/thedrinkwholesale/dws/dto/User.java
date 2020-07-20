package com.thedrinkwholesale.dws.dto;

import javax.persistence.*;


@Entity
public class User  {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String userid;

    private String password;

    private String Name;

    private String phonNo;

    private String Email;

    private String role;


    public Long getId() {
        return id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhonNo() {
        return phonNo;
    }

    public void setPhonNo(String phonNo) {
        this.phonNo = phonNo;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    //    builder 작성

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {

        private  Long id;
        private  String userid;
        private  String password;
        private  String Name;
        private  String phonNo;
        private  String Email;
        private  String role;



        private Builder() {
        }

        public Builder userid(String userid) {
            this.userid = userid;
            return this;

        }

        public Builder password(String password) {
            this.password = password;
            return this;

        }



        public Builder name(String name) {
            this.Name = name;
            return this;

        }

        public Builder phonNo(String phonNo) {
            this.phonNo = phonNo;
            return this;

        }
        public Builder Email(String Email) {
            this.Email = Email;
            return this;

        }
        public Builder role(String role) {
            this.role = role;
            return this;

        }

        public User build() {
            User user = new User();
            user.id  = this.id;
            user.userid = this.userid;
            user.password = this.password;
            user.Name = this.Name;
            user.phonNo = this.phonNo;
            user.Email = this.Email;
            user.role = this.role;

            return user;

        }



    }

    @Override
    public String toString() {
        return "User{" +
                "userid='" + userid + '\'' +
                ", password='" + password + '\'' +
                ", Name='" + Name + '\'' +
                ", phonNo='" + phonNo + '\'' +
                ", Email='" + Email + '\'' +
                '}';
    }
}
