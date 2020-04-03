package secure.open.standard.backend.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Body
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-03T17:12:46.725+02:00")

public class Body   {
  @JsonProperty("refids")
  @Valid
  private List<String> refids = null;

  @JsonProperty("message")
  private String message = null;

  public Body refids(List<String> refids) {
    this.refids = refids;
    return this;
  }

  public Body addRefidsItem(String refidsItem) {
    if (this.refids == null) {
      this.refids = new ArrayList<>();
    }
    this.refids.add(refidsItem);
    return this;
  }

  /**
   * JWE ref-IDs the device saw
   * @return refids
  **/
  @ApiModelProperty(value = "JWE ref-IDs the device saw")

@Size(min=1) 
  public List<String> getRefids() {
    return refids;
  }

  public void setRefids(List<String> refids) {
    this.refids = refids;
  }

  public Body message(String message) {
    this.message = message;
    return this;
  }

  /**
   * JWE with a nice message from some authorithy
   * @return message
  **/
  @ApiModelProperty(value = "JWE with a nice message from some authorithy")

@Pattern(regexp="^[/-A-Za-z0-9+=_.]+$") 
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Body body = (Body) o;
    return Objects.equals(this.refids, body.refids) &&
        Objects.equals(this.message, body.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(refids, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Body {\n");
    
    sb.append("    refids: ").append(toIndentedString(refids)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

