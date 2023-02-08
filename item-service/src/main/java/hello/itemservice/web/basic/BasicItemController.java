package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
//@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {
    private final ItemRepository itemRepository;

    @GetMapping
    public String item(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }



    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "basic/addForm";
    }

    /**
     * @RequestParam 사용
     */
//    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                            @RequestParam int price,
                            @RequestParam Integer quantity,
                            Model model) {

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);
        itemRepository.save(item);

        model.addAttribute("item", item);
        return "basic/item";
    }

    /**
     * @param item
     * @param model
     * @return
     * @ModelAttribute 사용 1
     * 1. @ModelAttribute("item") -> 프로퍼티 접근법으로 setter 실행
     * 2. "item" 이름으로 모델 객체에 등록해줌
     */
//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item, Model model) {
//        model.addAttribute("item", item); // @ModelAttribute 가 자동으로 등록해줌
        itemRepository.save(item);

        return "basic/item";
    }

    /**
     * @ModelAttribute 사용 1
     * 1. 클래스명의 첫글자를 소문자로해서 처리 (Item -> item)
     */
//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {
        itemRepository.save(item);
        return "basic/item";
    }


    /**
     * @ModelAttribute 생략하기
     */
//    @PostMapping("/add")
    public String addItemV4(Item item) {
        itemRepository.save(item);

        return "basic/item";
    }

    /**
     * PRG - Post/Redirect/Get
     * 새고고침시 상품다시 등록되는 문제 해결
     */
//    @PostMapping("/add")
    public String addItemV5(Item item) {
        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId();
    }

    /**
     * RedirectAttributes
     */


    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        log.info("item.open()={}",item.getOpen());

        Item saveItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", saveItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}";
        // /basic/items/3?status=true 를 반환해줌
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        log.info("quantity = {}",item.getQuantity());
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        itemRepository.update(itemId, item);

        return "redirect:/basic/items/{itemId}";
        //redirect 처리 -> url 바뀜
        //@PathVariable 값도 사용가능 -> {itemId}
    }

}
