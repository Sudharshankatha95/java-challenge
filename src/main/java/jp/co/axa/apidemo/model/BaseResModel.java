package jp.co.axa.apidemo.model;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author sudharshan R Katha
 *
 */

@Data
@EqualsAndHashCode
@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class BaseResModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private HttpStatus httpsStatus;

	private String error;

	private boolean success;

	private boolean warning;

	private String successMessage;

	private String errorDescription;

	private int hit;

	public void copyBaseInfo(BaseResModel baseResModel) {
		baseResModel.setError(this.error);
		baseResModel.setSuccessMessage(this.successMessage);
		baseResModel.setHit(this.hit);
		baseResModel.setErrorDescription(this.errorDescription);
	}

}
