package edu.uco.ralhamami;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.sql.DataSource;

@Named
@RequestScoped
public class registration implements Serializable{
    
    @NotNull(message="Last name cannot be blank")
    @Pattern(regexp="[a-zA-Z]+",message="Last name must contain only alphabetic characters")
    private String lastName;
    
    @NotNull(message="Last name cannot be blank")
    @Pattern(regexp="[a-zA-Z]+",message="Last name must contain only alphabetic characters")
    private String firstName;
    
    @NotNull(message="Password cannot be blank")
    @Size(min=4,message="Password must be at least 4 characters long")
    private String pwd;
    
    @NotNull(message="Phone cannot be blank")
    @Pattern(regexp="^[1-9]\\d{2}-\\d{3}-\\d{4}",message="Phone must be in ###-###-#### format")
    private String phone;
    
    @NotNull(message="Email cannot be blank")
    @Pattern(regexp="[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+",message="Please enter a valid email")
    private String email;
    
    @NotNull(message="Gender must be selected")
    private String gender;
    
    @NotNull(message="Location must be selected")
    private String location;
    
    @Size(min=1,message="At least one language must be selected")
    private String[] languages;
    
    private String languageSelection;
    private boolean isValid = true;
    
    @Resource(name = "jdbc/myDatasource")
    private DataSource ds;

    public String update() throws SQLException {
        Connection conn = ds.getConnection();

        if (conn == null) {
            throw new SQLException("conn is null; Can't get db connection");
        }
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO USERS (FNAME, LNAME, PWD, EMAIL,PHONE,GENDER,LANGUAGES,LOCATION) "
                            + "VALUES(?,?,?,?,?,?,?,?)"
            );
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, pwd);
            ps.setString(4, email);
            ps.setString(5, phone);
            ps.setString(6, gender);
            String lang="";
            for(int i=0;i<languages.length;i++){
                lang+=languages[i] + ",";
            }
            ps.setString(7, lang);
            
            
            ps.setString(8, location);
            
            ps.executeUpdate();
             System.out.println("Data Added Successfully");

        } finally {
            conn.close();
        }
        return null;
    }
    
    public List<Users> getUsers() throws SQLException {

        if (ds == null) {
            throw new SQLException("ds is null; Can't get data source");
        }

        Connection conn = ds.getConnection();

        if (conn == null) {
            throw new SQLException("conn is null; Can't get db connection");
        }

        List<Users> list = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select FNAME, LNAME, PWD, EMAIL,PHONE,GENDER,LANGUAGES,LOCATION FROM USERS"
            );

            // retrieve customer data from database
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                Users c = new Users();
                c.setlName(result.getString("LNAME"));
                c.setfName(result.getString("FNAME"));
                c.setPhone(result.getString("PHONE"));
                c.setEmail(result.getString("EMAIL"));
                c.setPwd(result.getString("PWD"));
                c.setPhone(result.getString("PHONE"));
                c.setGender(result.getString("GENDER"));
                c.setLocation(result.getString("LOCATION"));
                c.setLanguages(result.getString("LANGUAGES"));
                list.add(c);
            }

        } finally {
            conn.close();
        }

        return list;
    }
        

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    public String getLanguageSelection() {
        return languageSelection;
    }

    public void setLanguageSelection(String languageSelection) {
        this.languageSelection = languageSelection;
    }

    public boolean isIsValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }
}