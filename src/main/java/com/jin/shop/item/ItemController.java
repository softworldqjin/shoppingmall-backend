package com.jin.shop.item;

import com.jin.shop.comment.Comment;
import com.jin.shop.comment.CommentRepository;
import com.jin.shop.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.List;
import java.util.Optional;

@Controller
//@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;
    private final ItemService itemService;
    private final S3Service s3Service;
    private final CommentService commentService;

    @Autowired
    public ItemController(ItemRepository itemRepository, ItemService itemService, S3Service s3Service, CommentService commentService) {
        this.s3Service = s3Service;
        this.itemRepository = itemRepository;
        this.itemService = itemService;
        this.commentService = commentService;
    }

    @GetMapping("/list")
    String list(Model model) {
//        List<Item> result = itemService.findAll();
        Page<Item> result = itemRepository.findPageBy(PageRequest.of(0, 5));
        model.addAttribute("items", result.getContent());
        model.addAttribute("page", result);
//
//        var a = new Item();
//        System.out.println(a.toString());
        return "list.html";
    }

    @GetMapping("/write")
    String write(Authentication auth) {

        return "write.html";
    }

    @GetMapping("/detail/{id}")
    String detail(@PathVariable Integer id, @RequestParam(defaultValue = "0") int page, Model model) {

        Optional<Item> result = itemService.findById(id);
        if (!result.isPresent()) {
            return "redirect:/list";
        }

        Item item = result.get();
        Page<Comment> comments = commentService.findByParentId(id, PageRequest.of(page, 5));
        model.addAttribute("i", item);
        model.addAttribute("comments", comments);
        model.addAttribute("currentPage", page);
        return "detail.html";

    }

    @GetMapping("/edit/{id}")
    String edit(@PathVariable Integer id, Model model) {

        Optional<Item> result = itemService.findById(id);
        if (result.isPresent()) {
            Item item = result.get();
            model.addAttribute("item", item);
        } else {
            return "redirect:/list";
        }
        return "edit.html";
    }

    @PostMapping("/add")
    String addPost(@RequestParam String username, @RequestParam String title, @RequestParam Integer price,
                   @RequestParam String url) { // String타입으로 변환함

        itemService.saveItem(username, title, price, url);
        return "redirect:/list";

    }

    @PostMapping("/edit")
    String editPost(@RequestParam Integer id, @RequestParam String title, @RequestParam Integer price,
                    @RequestParam String url) {

        itemService.editItem(id, title, price, url);
        return "redirect:/list";
    }

    @GetMapping("/test1")
    String test1(@RequestParam String name) {
        System.out.println(name);
        return "redirect:/list";
    }

    @DeleteMapping("/item")
    ResponseEntity<String> deleteItem(@RequestParam Long id) {
        itemRepository.deleteById(id);
        return ResponseEntity.status(200).body("삭제완료"); //status(200) 성공 400 유저탓 500 서버탓

    }

    @GetMapping("/list/page/{num}")
    String getListPage(@PathVariable Integer num, Model model) {

        Page<Item> result = itemRepository.findPageBy(PageRequest.of(num-1, 5));
        model.addAttribute("items", result.getContent());
        model.addAttribute("page", result);
        return "list.html";
    }

    @GetMapping("/presigned-url")
    @ResponseBody
    String getURL(@RequestParam String filename) {
        System.out.println(filename);
        var result = s3Service.createPresignedUrl("test/" + filename);
        System.out.println(result);
        return result;
    }

    @GetMapping("/search")
    String getSearch(@RequestParam String searchText, @RequestParam(defaultValue = "0") int page, Model model) {
        Page<Item> result = itemRepository.findAllByTitleContains(searchText, PageRequest.of(page, 5));
        var result1 = itemRepository.rawQuery1(searchText);
        System.out.println(result1);

        model.addAttribute("items", result.getContent());
        model.addAttribute("page", result);
        return "list.html";
    }

}
