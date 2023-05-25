package org.kts.RestAssure;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(value = Include.NON_NULL)
public class Favfood {
	
	private String breakfast;
	private List<String> lunch;
	private String dinner;
	

}
