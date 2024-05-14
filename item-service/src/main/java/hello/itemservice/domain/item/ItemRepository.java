package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 핵심 도메인 로직
 */
@Repository
public class ItemRepository {
    private static final Map<Long, Item> store = new HashMap<>(); //싱글톤 유지 위해 static 선언
    private static long sequence = 0L;

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id){
        return store.get(id);
    } //id 기준조회

    public List<Item> findAll(){ //전체 조회
        return new ArrayList<>(store.values()); //한번 감싸서 넘김
    }

    public void update(Long itemId, Item updateParam){ //수정 메서드
        //ItemParamDTO를 만드는 것을 더 추천

        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
        findItem.setOpen(updateParam.getOpen());
        findItem.setRegions(updateParam.getRegions());
        findItem.setItemType(updateParam.getItemType());
        findItem.setDeliveryCode(updateParam.getDeliveryCode());

    }
    public void clearStore(){
        store.clear();
    }

}
