package com.jin.shop.item;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;


    public void saveItem(String username, String title, Integer price, String url) {

//        System.out.println(title);
//        System.out.println(price);
        if (title.length() >= 10) {
            throw new IllegalArgumentException("상품명은 10자 미만 허용");
        }

        if (price <= 0) {
            throw new IllegalArgumentException("상품가격으로 음수 입력 불가, ");
        }

        if (price >= 300000) {
            throw new IllegalArgumentException("상품가격은 300000원이상 입력 불가");
        }

        Item item = new Item();
        item.setUsername(username);
        item.setTitle(title);
        item.setPrice(price);
        item.setUrl(url);
        itemRepository.save(item);
    }

    public void editItem(Integer id, String title, Integer price, String url) {

        if (title.length() >= 100) {
            throw new IllegalArgumentException("제목 100자 이상 불가");
        }

        if (price <= 0) {
            throw new IllegalArgumentException("상품가격은 0이하 불가");
        }
        Optional<Item> result = itemRepository.findById(Long.valueOf(id));
        if (result.isPresent()) {
            Item item = result.get();
            item.setTitle(title);
            item.setPrice(price);
            item.setUrl(url);
            itemRepository.save(item);
        }
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Optional<Item> findById(Integer id) {

        return itemRepository.findById(Long.valueOf(id));
    }

}
