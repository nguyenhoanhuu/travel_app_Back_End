package fit.iuh.dulichgiare.entity;

import lombok.Data;

@Data
public class Messages {
	private String object;
	private String field;
	private Object rejectedValue;
	private String message;
}
