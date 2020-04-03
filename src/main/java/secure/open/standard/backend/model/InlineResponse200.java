package secure.open.standard.backend.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * InlineResponse200
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-03T17:12:46.725+02:00")

public class InlineResponse200   {
  @JsonProperty("message")
  private String message = null;

  @JsonProperty("tokenid")
  private String tokenid = null;

  public InlineResponse200 message(String message) {
    this.message = message;
    return this;
  }

  /**
   * JWE message from an authority 
   * @return message
  **/
  @ApiModelProperty(value = "JWE message from an authority ")

@Pattern(regexp="^[/-A-Za-z0-9+=_.]+$") 
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public InlineResponse200 tokenid(String tokenid) {
    this.tokenid = tokenid;
    return this;
  }

  /**
   * signaling token id, utilized in the event of abuse 
   * @return tokenid
  **/
  @ApiModelProperty(required = true, value = "signaling token id, utilized in the event of abuse ")
  @NotNull


  public String getTokenid() {
    return tokenid;
  }

  public void setTokenid(String tokenid) {
    this.tokenid = tokenid;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse200 inlineResponse200 = (InlineResponse200) o;
    return Objects.equals(this.message, inlineResponse200.message) &&
        Objects.equals(this.tokenid, inlineResponse200.tokenid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message, tokenid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse200 {\n");
    
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    tokenid: ").append(toIndentedString(tokenid)).append("\n");
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

