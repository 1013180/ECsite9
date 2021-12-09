package jp.winschool.spring.site;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Cart implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<List<String>> items = new ArrayList<List<String>>();
	private List<List<String>> items2 = new ArrayList<List<String>>();
	private String no = "0";
	private int sum;
	private int total_amount;
	private boolean f = true;
	private boolean f2 = true;
}
