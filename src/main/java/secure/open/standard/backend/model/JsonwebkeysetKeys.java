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
 * json web key
 */
@ApiModel(description = "json web key")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-03T17:12:46.725+02:00")

public class JsonwebkeysetKeys   {
  @JsonProperty("n")
  private String n = null;

  @JsonProperty("e")
  private String e = null;

  @JsonProperty("kty")
  private String kty = null;

  @JsonProperty("alg")
  private String alg = null;

  @JsonProperty("kid")
  private String kid = null;

  @JsonProperty("k")
  private String k = null;

  public JsonwebkeysetKeys n(String n) {
    this.n = n;
    return this;
  }

  /**
   * modulus
   * @return n
  **/
  @ApiModelProperty(value = "modulus")


  public String getN() {
    return n;
  }

  public void setN(String n) {
    this.n = n;
  }

  public JsonwebkeysetKeys e(String e) {
    this.e = e;
    return this;
  }

  /**
   * exponent
   * @return e
  **/
  @ApiModelProperty(value = "exponent")


  public String getE() {
    return e;
  }

  public void setE(String e) {
    this.e = e;
  }

  public JsonwebkeysetKeys kty(String kty) {
    this.kty = kty;
    return this;
  }

  /**
   * ktype
   * @return kty
  **/
  @ApiModelProperty(required = true, value = "ktype")
  @NotNull


  public String getKty() {
    return kty;
  }

  public void setKty(String kty) {
    this.kty = kty;
  }

  public JsonwebkeysetKeys alg(String alg) {
    this.alg = alg;
    return this;
  }

  /**
   * alorithm
   * @return alg
  **/
  @ApiModelProperty(value = "alorithm")


  public String getAlg() {
    return alg;
  }

  public void setAlg(String alg) {
    this.alg = alg;
  }

  public JsonwebkeysetKeys kid(String kid) {
    this.kid = kid;
    return this;
  }

  /**
   * keyid
   * @return kid
  **/
  @ApiModelProperty(required = true, value = "keyid")
  @NotNull


  public String getKid() {
    return kid;
  }

  public void setKid(String kid) {
    this.kid = kid;
  }

  public JsonwebkeysetKeys k(String k) {
    this.k = k;
    return this;
  }

  /**
   * key
   * @return k
  **/
  @ApiModelProperty(value = "key")


  public String getK() {
    return k;
  }

  public void setK(String k) {
    this.k = k;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    JsonwebkeysetKeys jsonwebkeysetKeys = (JsonwebkeysetKeys) o;
    return Objects.equals(this.n, jsonwebkeysetKeys.n) &&
        Objects.equals(this.e, jsonwebkeysetKeys.e) &&
        Objects.equals(this.kty, jsonwebkeysetKeys.kty) &&
        Objects.equals(this.alg, jsonwebkeysetKeys.alg) &&
        Objects.equals(this.kid, jsonwebkeysetKeys.kid) &&
        Objects.equals(this.k, jsonwebkeysetKeys.k);
  }

  @Override
  public int hashCode() {
    return Objects.hash(n, e, kty, alg, kid, k);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class JsonwebkeysetKeys {\n");
    
    sb.append("    n: ").append(toIndentedString(n)).append("\n");
    sb.append("    e: ").append(toIndentedString(e)).append("\n");
    sb.append("    kty: ").append(toIndentedString(kty)).append("\n");
    sb.append("    alg: ").append(toIndentedString(alg)).append("\n");
    sb.append("    kid: ").append(toIndentedString(kid)).append("\n");
    sb.append("    k: ").append(toIndentedString(k)).append("\n");
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

