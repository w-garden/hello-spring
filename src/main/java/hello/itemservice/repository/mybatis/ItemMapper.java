package hello.itemservice.repository.mybatis;


import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;


/**
 *  이 인터페이스의 메서드를 호출하면  .xml 의 해당 SQL을 실행하고 결과를 돌려준다
 */
@Mapper
public interface ItemMapper {

    void save(Item item);

    void update(@Param("id") Long id, @Param("updateParam") ItemUpdateDto updateParam);

    Optional<Item> findById(Long id);
    List<Item> findAll(ItemSearchCond itemSearch);

}
