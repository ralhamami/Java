package registration;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@Named(value = "user")
@SessionScoped
public class user implements Serializable {

    private String lastName;
    private String firstName;
    private String pwd;
    private String phone;
    private String email;
    private String gender;
    private String location;
    private String[] languages; 
    private String languageSelection;
    private boolean isValid = true;
            
    public void validate() {
        if (lastName == null || !lastName.matches("[a-zA-Z]+")){
            FacesMessage lnameErr = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Last name must contain "
                    + "only alphabetic characters",null);
            FacesContext.getCurrentInstance().addMessage("registration:lastName", lnameErr);
            isValid=false;
        }
        else{
            FacesMessage lnameErr = new FacesMessage(FacesMessage.SEVERITY_INFO,"\u2713 Last name",null);
            FacesContext.getCurrentInstance().addMessage("registration:lastName", lnameErr);
        }
        if (firstName == null || !firstName.matches("[a-zA-Z]+")){
            FacesMessage fnameErr = new FacesMessage(FacesMessage.SEVERITY_ERROR,"First name must only contain "
                    + "alphabetic characters",null);
            FacesContext.getCurrentInstance().addMessage("registration:firstName", fnameErr);
            isValid=false;
        }
        else{
            FacesMessage fnameErr = new FacesMessage(FacesMessage.SEVERITY_INFO,"\u2713 First name",null);
            FacesContext.getCurrentInstance().addMessage("registration:firstName", fnameErr);
        } 
        if (pwd.length()<4){
            FacesMessage pwdErr = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Password must be at least 4 "
                    + "characters",null);
            FacesContext.getCurrentInstance().addMessage("registration:pwd", pwdErr);
            isValid=false;
        }
        else{
            FacesMessage pwdErr = new FacesMessage(FacesMessage.SEVERITY_INFO,"\u2713 Password",null);
            FacesContext.getCurrentInstance().addMessage("registration:pwd", pwdErr);
        } 
        if (!email.contains("@")){
            FacesMessage emailErr = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please enter a valid email "
                    + "(ex:jondoe@email.com)",null);
            FacesContext.getCurrentInstance().addMessage("registration:email", emailErr);
            isValid=false;
        }
        else{
            FacesMessage emailErr = new FacesMessage(FacesMessage.SEVERITY_INFO,"\u2713 Email",null);
            FacesContext.getCurrentInstance().addMessage("registration:email", emailErr);
        }
        if (!phone.matches("^[1-9]\\d{2}-\\d{3}-\\d{4}")){
            FacesMessage phoneErr = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please enter phone in "
                    + "###-###-#### format",null);
            FacesContext.getCurrentInstance().addMessage("registration:phone", phoneErr);
            isValid=false;
        }
        else{
            FacesMessage phoneErr = new FacesMessage(FacesMessage.SEVERITY_INFO,"\u2713 Phone Number",null);
            FacesContext.getCurrentInstance().addMessage("registration:phone", phoneErr);
        }
        
        if(gender==null){
            FacesMessage gend = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please select a gender",null);
            FacesContext.getCurrentInstance().addMessage("registration:gender", gend);
            isValid=false;
        }
        else{
            FacesMessage gend = new FacesMessage(FacesMessage.SEVERITY_INFO,"\u2713 Gender",null);
            FacesContext.getCurrentInstance().addMessage("registration:gender", gend);
        }
        
        if(languages.length < 1){
            FacesMessage lang = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please select at least "
                    + "one language",null);
            FacesContext.getCurrentInstance().addMessage("registration:languages", lang);
            isValid=false;
        }
        else{
            FacesMessage lang = new FacesMessage(FacesMessage.SEVERITY_INFO,"\u2713 Language",null);
            FacesContext.getCurrentInstance().addMessage("registration:languages", lang);
        }
        if(location==null){
            FacesMessage loc = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please select a location",null);
            FacesContext.getCurrentInstance().addMessage("registration:location", loc);
            isValid=false;
        }
        else{
            FacesMessage loc = new FacesMessage(FacesMessage.SEVERITY_INFO,"\u2713 Location",null);
            FacesContext.getCurrentInstance().addMessage("registration:location", loc);
        }
        if (isValid){
            try {
                FacesContext.getCurrentInstance().getExternalContext().dispatch("success.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        isValid=true;
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

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
        languageSelection="";
        for (int i=0; i<languages.length; i++){
            languageSelection += languages[i];
            if (i < languages.length-1)
                languageSelection += "/";
        }
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isIsValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public String getLanguageSelection() {
        return languageSelection;
    }

    public void setLanguageSelection(String languageSelection) {
        this.languageSelection = languageSelection;
    }
}
