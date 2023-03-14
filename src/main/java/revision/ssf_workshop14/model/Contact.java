package revision.ssf_workshop14.model;
import java.security.SecureRandom;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class Contact {
    @NotNull(message="This field is mandatory")
    @Size(min=3, max=64, message="name must be between 3 and 64 characters")
    private String name;

    @Email(message="Invalid Email")
    @NotEmpty(message="This field is mandatory")
    private String email;

    @Size(min=7, message="Phone number must be at least 7 digits")
    @NotEmpty(message="This field is mandatory")
    private String phoneNumber;

    @Past(message="Date of Birth cannot be future")
    @NotNull(message="This field is mandatory")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @Min(value=10, message="Age must be min 10 years old")
    @Max(value=100, message="Age cannot be above 100 years old")
    private Integer age;

    private String id;

    public Contact() {
        //Create a new unique id everytime Contact is instantiated => new user entry
        this.id = generateId(8);   
    }

    public Contact(String id, String name, String email, String phoneNumber, LocalDate dateOfBirth, Integer age) {
        this.id = generateId(8);
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
    }

    public Contact(String id, String name, String email, String phoneNumber, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        
    }

    private synchronized String generateId(int numOfChar){
        
        //SecureRandom uses a secure algorithm to generate random numbers that cannot be easily 
        //predicted or reproduced.It provides a higher level of security compared to 
        //the standard java.util.Random class, which only produces predictable 
        //and reproducible results.
        
        SecureRandom sr = new SecureRandom();
        StringBuilder sb = new StringBuilder();


        //Integer.toHexString() converts an integer to a string 
        //representing the integer's hexadecimal value. The method takes an integer as input 
        //and returns a string that represents the hexadecimal value of the integer. 
        //For example, Integer.toHexString(255) would return the string "ff", 
        //since the hexadecimal representation of 255 is 0xFF.
        
        while(sb.length() < numOfChar){
            sb.append(Integer.toHexString(sr.nextInt()));
        }
        
        //Each integer may be represented by hexadecimal that is more than 1 char. So we need to cap it to 8 char
        return sb.toString().substring(0, numOfChar);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Contact [name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + ", dateOfBirth="
                + dateOfBirth + ", age=" + age + ", id=" + id + "]";
    }
}
