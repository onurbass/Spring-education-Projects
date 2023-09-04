package com.onurbas;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

  @Test
  void main() {
	Main.main(new String[] {"Onur ","Bas","Sinop"});
  }

  @Test
  void mainNullTesti() {
	System.out.println("Selam2 :) ");
  }

  @Test
  public void upperStringTest() {
	Main.upperString("Onur");
  }

  @Test
  void notOrtalamaTest() {
	Main.notOrtalama(20,40,2);
  }

  @Test
  public void testNotOrtalamaGeçtiniz() {
    assertEquals(1, Main.notOrtalama(70, 80, 2));
  }

  @Test
  public void testNotOrtalamaKaldınız() {
    assertEquals(1, Main.notOrtalama(30, 40, 2));
  }

  @Test
  public void testNotOrtalamaŞartlı() {
    assertEquals(1, Main.notOrtalama(50, 50, 2));
  }

  @Test
  public void testNotOrtalamaGeçersizBilgi() {
    assertEquals(0, Main.notOrtalama(70, 80, 0));
  }
}