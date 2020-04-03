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
 * Jsonwebkeyset
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-03T17:12:46.725+02:00")

public class Jsonwebkeyset   {
  @JsonProperty("keys")
  @Valid
  private List<JsonwebkeysetKeys> keys = null;

  public Jsonwebkeyset keys(List<JsonwebkeysetKeys> keys) {
    this.keys = keys;
    return this;
  }

  public Jsonwebkeyset addKeysItem(JsonwebkeysetKeys keysItem) {
    if (this.keys == null) {
      this.keys = new ArrayList<>();
    }
    this.keys.add(keysItem);
    return this;
  }

  /**
   * list of keys
   * @return keys
  **/
  @ApiModelProperty(value = "list of keys")

  @Valid

  public List<JsonwebkeysetKeys> getKeys() {
    return keys;
  }

  public void setKeys(List<JsonwebkeysetKeys> keys) {
    this.keys = keys;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Jsonwebkeyset jsonwebkeyset = (Jsonwebkeyset) o;
    return Objects.equals(this.keys, jsonwebkeyset.keys);
  }

  @Override
  public int hashCode() {
    return Objects.hash(keys);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Jsonwebkeyset {\n");
    
    sb.append("    keys: ").append(toIndentedString(keys)).append("\n");
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

