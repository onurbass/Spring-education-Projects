package com.bilgeadam.service;

import com.bilgeadam.dto.request.TahminRequestDto;
import com.bilgeadam.dto.response.Sehir;
import com.bilgeadam.dto.response.TahminResponeDto;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.TahminManagerException;
import com.bilgeadam.manager.IKullaniciManager;
import com.bilgeadam.manager.ISehirManager;
import com.bilgeadam.mapper.ITahminMapper;
import com.bilgeadam.rabbitmq.model.SkorModel;
import com.bilgeadam.rabbitmq.producer.SkorProducer;
import com.bilgeadam.repository.TahminRepository;
import com.bilgeadam.repository.entity.Tahmin;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TahminService extends ServiceManager<Tahmin,Long> {

    private final TahminRepository tahminRepository;

    private final ISehirManager sehirManager;
    private final IKullaniciManager kullaniciManager;
    private  final SkorProducer producer;

    public TahminService(TahminRepository tahminRepository, ISehirManager sehirManager, IKullaniciManager kullaniciManager, SkorProducer producer) {
        super(tahminRepository);
        this.tahminRepository = tahminRepository;
        this.sehirManager = sehirManager;
        this.kullaniciManager = kullaniciManager;
        this.producer = producer;
    }

    public TahminResponeDto olustur(Long userId) {
        Sehir sehir=sehirManager.sehirGetir().getBody();
        System.out.println(sehir);
        Tahmin tahmin=Tahmin.builder().userId(userId).dogruCevap(sehir.getIsim()).blur(sehir.getBlur()).build();
        save(tahmin);
        kullaniciManager.updateAktifTahminId(userId,tahmin.getId());
        return ITahminMapper.INSTANCE.toTahminResponeDto(tahmin);
    }


    public String tahminYap(TahminRequestDto dto){
        Optional<Tahmin> tahmin=tahminRepository.findById(dto.getTahminId());
            if (tahmin.get().getHak()==0){
                return "Hakkınız bitmiştir";
            }

            if (tahmin.get().isDogrulandiMi()){
                throw new TahminManagerException(ErrorType.BAD_REQUEST,"Tamin daha once dogrulanmıs");
            }

            tahmin.get().getTahminler().add(dto.getTahmin());

        if (tahmin.get().getDogruCevap().equalsIgnoreCase(dto.getTahmin())){
            tahmin.get().setDogrulandiMi(true);
            update(tahmin.get());
            producer.sendSkor(SkorModel.builder().skor(10).userId(dto.getUserId()).build());
            return "Tebrikler Doğru cevap ";
        }

        tahmin.get().setHak(tahmin.get().getHak()-1);
        update(tahmin.get());
        producer.sendSkor(SkorModel.builder().skor(-5).userId(dto.getUserId()).build());
        return "Yanlış cevap lutfen tekrar deneyiniz "+ tahmin.get().getHak()+" hakkınız kaldı";

    }
}
