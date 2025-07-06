
package ornekler;

import java.util.ArrayList;

public class Kursiyer implements Hesaplama {

	private int kursiyerID;
	private String kursiyerAdSoyad;
	private int kursiyerYas;
	public ArrayList<Kurs> alinanKurslar;

	@Override
	public void BorcHesapla() {
		int ucret;
		String kampanya;
		if(alinanKurslar.size()==2)
		{
		  ucret = (int) (4*500 + 4*(500*(80.0/100)));
		  kampanya = "Kampanya 1";
		}
		else if(alinanKurslar.size()==3)
		{
			ucret = (int) (4*500 + 4*500 + 4*(500*(75.0/100)));
			kampanya = "Kampanya 2";
		}
		else if(alinanKurslar.size() > 3)
		{
			ucret = (int)  (alinanKurslar.size()*4*500*(90.0/100));
			kampanya = "Kampanya 3";
		}
		else
		{
			ucret = 4*500;
			kampanya = "Tek kurs alanlara kampanya yoktur.";
		}
		System.out.println("Kursiyerin odeyecegi ucret: "+ucret);
		System.out.println("Kursiyerin yararlandigi kampanya: "+kampanya);

	}

	public Kursiyer(int kursiyerID, String kursiyerAdSoyad, int kursiyerYas, ArrayList<Kurs> alinanKurslar) {
		super();
		this.kursiyerID = kursiyerID;
		this.kursiyerAdSoyad = kursiyerAdSoyad;
		this.kursiyerYas = kursiyerYas;
		this.alinanKurslar = alinanKurslar;
	}

	public int getKursiyerID() {
		return kursiyerID;
	}

	public void setKursiyerID(int kursiyerID) {
		this.kursiyerID = kursiyerID;
	}

	public String getKursiyerAdSoyad() {
		return kursiyerAdSoyad;
	}

	public void setKursiyerAdSoyad(String kursiyerAdSoyad) {
		this.kursiyerAdSoyad = kursiyerAdSoyad;
	}

	public int getKursiyerYas() {
		return kursiyerYas;
	}

	public void setKursiyerYas(int kursiyerYas) {
		this.kursiyerYas = kursiyerYas;
	}







}
