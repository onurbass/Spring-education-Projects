package com.onurbas;

public class Main {
  public static void main(String[] args) {
	for (String item : args) {
	  System.out.println(item);

	}
  }

  public static String upperString(String str) {
	return str.toUpperCase();
  }

  public static int notOrtalama(int vize,int finalNotu,int adet) {
	int ort = 0;
	try {
	  ort = (vize + finalNotu) / adet;
	  if (ort < 50) {
		System.out.println("Kaldınız");
	  } else if (ort > 50) {
		System.out.println("Geçtiniz");
	  } else {
		System.out.println("Şartlı ");
	  }
	  return 1;

	} catch (Exception e) {
	  System.out.println("Gecerli bilgi giriniz");
	  return 0;
	}

  }
}