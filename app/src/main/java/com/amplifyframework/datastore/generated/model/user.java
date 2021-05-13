package com.amplifyframework.datastore.generated.model;


import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the user type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "users")
public final class user implements Model {
  public static final QueryField ID = field("user", "id");
  public static final QueryField USERNAME = field("user", "username");
  public static final QueryField MOBILENUMBER = field("user", "mobilenumber");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String username;
  private final @ModelField(targetType="String", isRequired = true) String mobilenumber;
  public String getId() {
      return id;
  }
  
  public String getUsername() {
      return username;
  }
  
  public String getMobilenumber() {
      return mobilenumber;
  }
  
  private user(String id, String username, String mobilenumber) {
    this.id = id;
    this.username = username;
    this.mobilenumber = mobilenumber;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      user user = (user) obj;
      return ObjectsCompat.equals(getId(), user.getId()) &&
              ObjectsCompat.equals(getUsername(), user.getUsername()) &&
              ObjectsCompat.equals(getMobilenumber(), user.getMobilenumber());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getUsername())
      .append(getMobilenumber())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("user {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("username=" + String.valueOf(getUsername()) + ", ")
      .append("mobilenumber=" + String.valueOf(getMobilenumber()))
      .append("}")
      .toString();
  }
  
  public static UsernameStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static user justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new user(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      username,
      mobilenumber);
  }
  public interface UsernameStep {
    MobilenumberStep username(String username);
  }
  

  public interface MobilenumberStep {
    BuildStep mobilenumber(String mobilenumber);
  }
  

  public interface BuildStep {
    user build();
    BuildStep id(String id) throws IllegalArgumentException;
  }
  

  public static class Builder implements UsernameStep, MobilenumberStep, BuildStep {
    private String id;
    private String username;
    private String mobilenumber;
    @Override
     public user build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new user(
          id,
          username,
          mobilenumber);
    }
    
    @Override
     public MobilenumberStep username(String username) {
        Objects.requireNonNull(username);
        this.username = username;
        return this;
    }
    
    @Override
     public BuildStep mobilenumber(String mobilenumber) {
        Objects.requireNonNull(mobilenumber);
        this.mobilenumber = mobilenumber;
        return this;
    }
    
    /** 
     * WARNING: Do not set ID when creating a new object. Leave this blank and one will be auto generated for you.
     * This should only be set when referring to an already existing object.
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     * @throws IllegalArgumentException Checks that ID is in the proper format
     */
    public BuildStep id(String id) throws IllegalArgumentException {
        this.id = id;
        
        try {
            UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
        } catch (Exception exception) {
          throw new IllegalArgumentException("Model IDs must be unique in the format of UUID.",
                    exception);
        }
        
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String username, String mobilenumber) {
      super.id(id);
      super.username(username)
        .mobilenumber(mobilenumber);
    }
    
    @Override
     public CopyOfBuilder username(String username) {
      return (CopyOfBuilder) super.username(username);
    }
    
    @Override
     public CopyOfBuilder mobilenumber(String mobilenumber) {
      return (CopyOfBuilder) super.mobilenumber(mobilenumber);
    }
  }
  
}
