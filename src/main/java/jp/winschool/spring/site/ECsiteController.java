package jp.winschool.spring.site;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jp.winschool.spring.site.Cart;

@Controller
@SessionAttributes(value = "cart")
public class ECsiteController {

	@ModelAttribute("cart")
	public Cart cart() {
		return new Cart();
	}

	@Autowired
	private ItemRepository itemRepository;

	@GetMapping("/")
	public String index(Model model) {
		List<Item> items = itemRepository.findAll();
		model.addAttribute("items", items);
		return "index";
	}

	@GetMapping("/cart")
	public String cart(Cart cart, String money) {
		flag2_set(cart);
		if (money == "") {
			return "cart";
		}
		try {
			int money1 = Integer.valueOf(money);
			cart.setSum(cart.getSum() + money1);
			flag_set(cart);
			return "cart";
		} catch (NumberFormatException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return "cart";
		}
	}
	
	@GetMapping("/history")
	public String history(Cart cart) {
		return "history";
	}
	
	@PostMapping("/add")
	public String add(Cart cart, String name, String price, String count) {
		if(count=="") {
			return "redirect:/";
		}
		List<String> cart2 = new ArrayList<String>();
		if (1 <= Integer.valueOf(count) && Integer.valueOf(count) <= 100) {
			try {
				cart2.add(name);
				cart2.add(price);
				cart2.add(count);
				cart2.add(cart.getNo());
				cart.getItems().add(cart2);
				cart.setTotal_amount(cart.getTotal_amount() 
						+ Integer.valueOf(price) * Integer.valueOf(count));
				flag_set(cart);
				int no2 = (Integer.valueOf(cart.getNo())) + 1;
				String str = String.valueOf(no2);
				cart.setNo(str);
				return "redirect:/";
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				return "redirect:/";
			}
		} else {
			return "redirect:/";
		}

	}

	@PostMapping("/add2")
	public String add2(Cart cart, String name, String price, String count) {
		List<String> cart3 = new ArrayList<String>();
		try {
			cart3.add(name);
			cart3.add(price);
			cart3.add(count);
			cart3.add(cart.getNo());
			cart.getItems().add(cart3);
			cart.setTotal_amount(cart.getTotal_amount() 
					+ Integer.valueOf(price) * Integer.valueOf(count));

			int no2 = (Integer.valueOf(cart.getNo())) + 1;
			String str = String.valueOf(no2);
			cart.setNo(str);
			flag_set(cart);
			return "redirect:cart";
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return "redirect:cart";
		}

	}

	@PostMapping("/delete")
	public String end(Cart cart, String price, String count, String no) {
		try {
			cart.setTotal_amount(cart.getTotal_amount() 
					- Integer.valueOf(price) * Integer.valueOf(count));
			int no2 = Integer.valueOf(no);
			cart.getItems().remove(no2);
			for (int i = no2; i < cart.getItems().size(); i++) {
				String str = String.valueOf(i);
				cart.getItems().get(i).set(3, str);
			}
			flag_set(cart);
			flag2_set(cart);
			return "cart";
		} catch (NumberFormatException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return "cart";
		}
	}

	@GetMapping("/end")
	public String end(Cart cart) {
//    	status.setComplete(); SessionStatus status
		if (cart.isF()) {
			return "cart";
		}
		if (cart.isF2()) {
			return "cart";
		}
		for (int i = 0; i < cart.getItems().size(); i++) {
			cart.getItems2().add(cart.getItems().get(i));
		}
		cart.getItems().clear();
		cart.setSum(cart.getSum() - cart.getTotal_amount());
		cart.setTotal_amount(0);
		cart.setNo("0");
		return "end";
		
	}
	public void flag_set(Cart cart) {
		if (0 > (cart.getSum() - cart.getTotal_amount())) {
			cart.setF(true);
		}else {
		cart.setF(false);
		}
	}
	public void flag2_set(Cart cart) {
		if (cart.getItems().size()==0) {
			cart.setF2(true);
		}else {
		cart.setF2(false);
		}
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
}
