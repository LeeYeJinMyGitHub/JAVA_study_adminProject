package com.example.admin_project.service;

import com.example.admin_project.ifs.CrudInterface;
import com.example.admin_project.model.entity.Item;
import com.example.admin_project.model.entity.Partner;
import com.example.admin_project.model.network.Header;
import com.example.admin_project.model.network.Request.ItemApiRequest;
import com.example.admin_project.model.network.Response.ItemApiResponse;
import com.example.admin_project.repository.ItemRepository;
import com.example.admin_project.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ItemApiLogicService implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PartnerRepository partnerRepository;

    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {

        ItemApiRequest body = request.getData();

        Item item = Item.builder()
                .status(body.getStatus())
                .name(body.getName())
                .title(body.getTitle())
                .content(body.getContent())
                .price(body.getPrice())
                .brandName(body.getBrandName())
                .registeredAt(LocalDateTime.now())
                .partner(partnerRepository.getOne(body.getPartnerId()))
                .build();

        //아이템을 저장하기 위해 itemRepository를 불러와서 save에 엔티티(item)을 넘김
        Item newItem = itemRepository.save(item);

        return response(newItem);
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {

        return itemRepository.findById(id)
                .map(item -> response(item))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {

        //1. 아이템을 찾아와서 바디에 담는다.
        ItemApiRequest body = request.getData();

        //바디에는 어떤 데이터 로우에 대해 업데이트 시킬건지 id가 들어있다.
        return itemRepository.findById(body.getId())
                //데이터가 있다면 request에 있던 값들로 모두 업데이트한다.
                .map(entityItem -> {
                     entityItem
                            .setStatus(body.getStatus())
                            .setName(body.getName())
                            .setTitle(body.getTitle())
                            .setPrice(body.getPrice())
                            .setBrandName(body.getBrandName())
                            .setRegisteredAt(body.getRegisteredAt())
                            .setUnregisteredAt(body.getUnregisteredAt())
                            ;
                //다음 객체로 넘겨준다.
                return entityItem;
            })
            //업데이트된 newEntityItem을 받아서 저장한다.
            .map(newEntityItem -> itemRepository.save(newEntityItem))
            .map(item -> response(item))
            .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {

        return itemRepository.findById(id)
                .map(item -> {
                    itemRepository.delete(item);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<ItemApiResponse> response(Item item){
        ItemApiResponse body = ItemApiResponse.builder()
                .id(item.getId())
                .status(item.getStatus())
                .name(item.getName())
                .title(item.getTitle())
                .content(item.getContent())
                .price(item.getPrice())
                .brandName(item.getBrandName())
                .registeredAt(item.getRegisteredAt())
                .unregisteredAt(item.getUnregisteredAt())
                .partnerId(item.getPartner().getId())
                .build();

        return Header.OK(body);
    }
}
