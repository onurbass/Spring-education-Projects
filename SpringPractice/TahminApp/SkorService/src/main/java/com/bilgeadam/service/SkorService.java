package com.bilgeadam.service;

import com.bilgeadam.repository.entity.Skor;
import com.bilgeadam.rabbitmq.model.SkorModel;
import com.bilgeadam.repository.ISkorRepository;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkorService  extends ServiceManager<Skor,Long> {

    private final ISkorRepository skorRepository;

    public SkorService(ISkorRepository skorRepository) {
        super(skorRepository);
      this.skorRepository = skorRepository;
    }


    public Skor saveOrUpdateSkor(SkorModel model){
        Optional<Skor> skor=skorRepository.findByUserId(model.getUserId());
        if (skor.isEmpty()){
           skor= Optional.of(save(Skor.builder().skor(model.getSkor()).userId(model.getUserId()).build())) ;
        }else{
            skor.get().setSkor(skor.get().getSkor()+ model.getSkor());
            update(skor.get());
        }
        return  skor.get();
    }

    public List<Skor> sirala() {

        return skorRepository.findAllByOrderBySkorDesc();
    }
}
