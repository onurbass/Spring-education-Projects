package com.onurbas.firstexample;

public class Mario implements IRunable {
  public void up(){
	System.out.println("Zıpla");
  }
  public void down(){
	System.out.println("Deliğe gir");
  }
  public void left(){
	System.out.println("Geri dön");
  }
  public void right(){
	System.out.println("Hızlan");
  }
}
