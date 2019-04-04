package myLibrary.entity.classifier;

import lombok.Data;

@Data
public class StringClassifier  implements Classifier{
	private String code;
	private String title;

	public StringClassifier() {
	}

	public StringClassifier(String code, String title) {
		this.code = code;
		this.title = title;
	}

}
