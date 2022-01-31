package model;

import java.util.Objects;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Customer {
    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private String emailRegex = "^(.+)@(.+).(.+)$";
    private Pattern emailPattern = Pattern.compile(emailRegex);

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        if(emailPattern.matcher(email).matches()) {
            this.email = email;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        try {
            emailPattern.matcher(email);
            this.email = email;
        }
        catch (Exception exception) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        StringBuilder customerInfo = new StringBuilder("\nCustomer Info");

        customerInfo.append("\n-------------------------\n");
        customerInfo.append("Name: ").append(getFirstName()).append(" ").append(getLastName()).append("\n");
        customerInfo.append("Email: ").append(getEmail()).append("\n");

        return customerInfo.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this.getClass() != object.getClass()) {
            return false;
        }

        Customer customer = (Customer) object;

        if (customer.firstName.equals(this.firstName)
                && customer.lastName.equals(this.lastName)
                && customer.email.equals(this.email)) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, firstName, lastName);
    }
}
