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
import java.io.UnsupportedEncodingException;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    @Size(min=3,message="Password must be at least 4 characters long")
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
    
    @Resource(name = "jdbc/mydb")
    private DataSource ds;

    public String update() throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        Connection conn = ds.getConnection();

        if (conn == null) {
            throw new SQLException("conn is null; Can't get db connection");
        }
        try{
            PreparedStatement ps2 = conn.prepareStatement(
                    "insert into USERTABLE (username, password, email) values(?,?,?)"
            );
            ps2.setString(1, email);
            String temp = hash256(pwd);
            ps2.setString(2, temp);
            ps2.setString(3, email);
            PreparedStatement ps3 = conn.prepareStatement(
                    "insert into GROUPTABLE (groupname, username) values ('customergroup', ?)"
            );
            ps3.setString(1, email);
            ps2.execute();
            ps3.execute();
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
                    "select PASSWORD, USERNAME FROM USERTABLE"
            );

            // retrieve customer data from database
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                Users c = new Users();
                c.setEmail(result.getString("USERNAME"));
                c.setPwd(result.getString("PASSWORD"));
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
    
    public static String hash256(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(data.getBytes());
        return bytesToHex(md.digest());
    }
    
    public static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte byt : bytes) result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }
}