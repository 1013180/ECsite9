package jp.winschool.spring.exercise09;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertThat;

import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import jp.winschool.spring.site.Item;
import jp.winschool.spring.site.ItemRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ItemRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@BeforeAll
	public void setUp() {
		
		Item item1 = new Item();
		item1.setItem_id(1);
		item1.setName("天然水");
		item1.setPrice(100);
		item1.setImage(null);
		entityManager.persist(item1);
		
		Item item2 = new Item();
		item2.setItem_id(2);
		item2.setName("コーラ");
		item2.setPrice(500);
		item2.setImage(null);
		entityManager.persist(item2);
		
		Item item3 = new Item();
		item3.setItem_id(3);
		item3.setName("牛乳");
		item3.setPrice(400);
		item3.setImage(null);
		entityManager.persist(item3);
	}
//	@Test
//	public void testFindByItem(){
//		List<Item> items = itemRepository.findAllItemByItem_id();
//		assertThat(items.size()).isEqualTo(3);
//	}
}