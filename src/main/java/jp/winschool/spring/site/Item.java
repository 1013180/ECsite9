package jp.winschool.spring.site;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer item_id;

	private String name;

	private Integer price;
	
	private String image;

}
