package com.socialmedia.service;

import com.socialmedia.repository.IUserRepository;
import com.socialmedia.repository.entity.UserProfile;
import com.socialmedia.utility.ServiceManager;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

/*
    update metodu yazalım
    update metodu userprofile bilgilerini güncelleyecek aynı zamanda...
    update metodunda username veya email in ikisinden biri değişmiş ise
    authServisde de güncelleme yapsın..
 */

@Service
public class UserService extends ServiceManager<UserProfile, String> {

  //Dependency Injec -->> constructor injection, setter injection, field injection
  private final IUserRepository userRepository;

  private final CacheManager cacheManager;

  public UserService(IUserRepository userRepository,CacheManager cacheManager) {
	super(userRepository);
	this.userRepository = userRepository;
	this.cacheManager = cacheManager;
  }

}

