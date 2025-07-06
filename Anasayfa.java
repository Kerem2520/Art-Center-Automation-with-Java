
package ornekler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Anasayfa {

	public static void main(String[] args) throws NumberFormatException, IOException {

        ArrayList<Kursiyer> kursiyerler = new ArrayList<Kursiyer>();
        ArrayList<Kurs> kurslar = new ArrayList<Kurs>();
        ArrayList<Kurs> kurslar_gecici = new ArrayList<Kurs>();
        ArrayList<Kurs> kurslar_gecici2 = new ArrayList<Kurs>();



		File kursiyer_dosyam = new File("src\\ornekler\\kursiyer.txt");
		if(!kursiyer_dosyam.exists())
		{
			System.out.println("Kursiyer Dosyası Bulunamadı");
		}
		FileReader fr = new FileReader(kursiyer_dosyam);
		BufferedReader br = new BufferedReader(fr);

		String okunan;
		String[] okunan_dizi;
		int id = 0;
		String adSoyad = null;
		int yas = 0;
		int kursID;
		String kursAD;
		boolean kontrol = false;
		while((okunan = br.readLine()) != null)
		{
			if(okunan.startsWith("*"))
			{
				if(kontrol == true)
				{
				 kursiyerler.add(new Kursiyer(id,adSoyad,yas,new ArrayList<>(kurslar_gecici)));
				}
				kurslar_gecici.clear();
				okunan_dizi = okunan.substring(1).split("%");
				id = Integer.parseInt(okunan_dizi[0]);
                adSoyad = okunan_dizi[1];
                yas = Integer.parseInt(okunan_dizi[2]);


			}
			else if(okunan.startsWith("+"))
			{
				okunan_dizi = okunan.substring(1).split("%");
				kursID = Integer.parseInt(okunan_dizi[0]);
                kursAD = okunan_dizi[1];
                kurslar_gecici.add(new Kurs(kursID,kursAD));
                kontrol = true;


			}


		}

		if (kontrol==true)
		{
		    kursiyerler.add(new Kursiyer(id, adSoyad, yas, new ArrayList<>(kurslar_gecici)));
		}
		fr.close();
		br.close();



		//---------------------------------------------------------------------------------------------------------------------
		File kurs_dosyam = new File("src\\ornekler\\kurs.txt");
		if(!kurs_dosyam.exists())
		{
			System.out.println("Kurs Dosyası Bulunamadı");
		}
		FileReader fr2 = new FileReader(kurs_dosyam);
		BufferedReader br2 = new BufferedReader(fr2);

		String okunan2;
		String[] okunan_dizi2;
		int kursID2;
		String kursAD2;
		while((okunan2 = br2.readLine()) != null)
		{
				okunan_dizi2 = okunan2.split("%");
				kursID2 = Integer.parseInt(okunan_dizi2[0]);
                kursAD2 = okunan_dizi2[1];
    		    kurslar.add(new Kurs(kursID2,kursAD2));


		}

		fr2.close();
		br2.close();

		// ***************************** ANA MENU*******************************************************
		Scanner scan = new Scanner(System.in);
		int secim;
		String kalin = "\u001B[1m";  // Kalın yazı
        String normal = "\u001B[0m"; // Normal yazı
		while(true)
		{
			System.out.println();
			System.out.println("Ana Menü");
			System.out.println("--------------------");
			System.out.println("1) Kurs Ekle");
			System.out.println("2) Kurs Listele");
			System.out.println("3) Kurs Ara");
			System.out.println("4) Kurs Sil");
			System.out.println("5) Kursiyer Ekle");
			System.out.println("6) Kursiyer Ara");
			System.out.println("7) Kursiyer Sil");
			System.out.println("8) Kursiyer Listele");
			System.out.println("9) Kursiyerleri Ayrintili Listele");
			System.out.println("10) Kursiyerin Odeyecegi Tutari Hesapla");
			System.out.println("11) Cikis");
			System.out.println("Seciminiz: ");
			secim = scan.nextInt();

			if(secim==1)
			{
				System.out.println("Eklenecek kursun ID'sini girin: ");
				int kurs_id = scan.nextInt();
				scan.nextLine();
				boolean mevcut_mu = false;
                for (Kurs kurs : kurslar)
                {
                    if (kurs.getKursID() == kurs_id)
                    {
                        mevcut_mu = true;
                        break;
                    }
                }

                if (mevcut_mu == true)
                {
                    System.out.println("Bu ID numarasına sahip bir kurs zaten var.");
                }
                else
                {
                    System.out.println("Eklenecek kursun ismini girin: ");
                    String kurs_ad = scan.nextLine();
                    kurslar.add(new Kurs(kurs_id, kurs_ad));
                    System.out.println("Kurs başarıyla eklendi.");
                }

			}
			else if(secim==2)
			{
				System.out.println(kalin + "Kurs ID       Kurs Ad" + normal);
				for(Kurs krs:kurslar)
				{
					System.out.println(krs.getKursID()+"          "+krs.getKursAd());
				}

			}
			else if(secim == 3)
			{
				scan.nextLine();
				System.out.println("Aranacak kursun adini giriniz: ");
				String kurs_add = scan.nextLine();
	            boolean bulundu = false;
				for (Kurs kurs : kurslar)
                {
                    if (kurs.getKursAd().equals(kurs_add))
                    {
                        System.out.println("Bulunan Kursun ID'si ve ismi: "+kurs.getKursID()+"  "+kurs.getKursAd());
                        bulundu = true;
                    }
                }
				if(bulundu == false)
				{
					System.out.println("Aranan isimde bir kurs bulunamadi.");
				}

			}
			else if(secim == 4)
			{
				boolean bulundu = false;
				boolean sil = false;
				String krs_ad;
				scan.nextLine();
				System.out.println("Silinecek olan kursun ismini giriniz: ");
				krs_ad = scan.nextLine();
				for(Kurs kurs:kurslar)
				{
				  if(kurs.getKursAd().equals(krs_ad))
				  {
					    bulundu = true;
					    for(Kursiyer krsyr:kursiyerler)
						{
					 		for (Kurs kurs2 : krsyr.alinanKurslar)
					 		{
					 	       if(kurs2.getKursAd().equals(krs_ad))
					 	       {
					 	    	   System.out.println("Bu kursu alan kursiyer/kursiyerler oldugu icin silinemez.");
					 	    	   sil = true;
					 	    	   break;
					 	       }
					 	    }
						}
					    if(sil == false)
					    {
					       kurslar.remove(kurs);
					       System.out.println("Kurs Silindi");
						   break;
					    }
				  }
				}
				if(bulundu == false)
				{
					System.out.println("Silinmesi Istenen Kurs Bulunamadi");
				}

			}
			else if(secim == 5)
			{
				kurslar_gecici2.clear();
				System.out.println("Eklenecek kursiyerin ID'sini girin: ");
				int kursiyer_id = scan.nextInt();
				scan.nextLine();
				boolean mevcut_mu = false;
                for (Kursiyer krs : kursiyerler)
                {
                    if (krs.getKursiyerID() == kursiyer_id)
                    {
                        mevcut_mu = true;
                        break;
                    }
                }

                if (mevcut_mu == true)
                {
                    System.out.println("Bu ID numarasına sahip bir kursiyer zaten var.");
                }
                else
                {
                    System.out.println("Eklenecek kursiyerin ismini girin: ");
                    String kursiyer_ad = scan.nextLine();
                    System.out.println("Eklenecek kursiyerin soy ismini girin: ");
                    String kursiyer_soyad = scan.nextLine();
                    System.out.println("Eklenecek kursiyerin yasini girin: ");
                    int yass = scan.nextInt();
                    System.out.println("Kursiyer kac tane kurs alacak? ");
                    int kurs_adedi = scan.nextInt();
                    for(int i=0;i<kurs_adedi;i++)
                    {

                    	System.out.println("Eklenecek "+(i+1)+". kursun ID'sini girin: ");
                        int k_id = scan.nextInt();
                        scan.nextLine();
                    	System.out.println("Eklenecek "+(i+1)+". kursun adini girin: ");
                        String k_ad = scan.nextLine();
                        kurslar_gecici2.add(new Kurs(k_id,k_ad));
                    }
                    kursiyerler.add(new Kursiyer(kursiyer_id, kursiyer_ad+" "+kursiyer_soyad, yass, new ArrayList<>(kurslar_gecici2)));


                    System.out.println("Kursiyer başarıyla eklendi.");
                }

			}
			else if(secim == 6)
			{
				scan.nextLine();
				System.out.println("Aranacak kursiyerin ismini ve soy ismini araya bosluk birakarak giriniz: ");
				String ad_soyad = scan.nextLine();
				boolean bulundu = false;
					for (Kursiyer krs : kursiyerler)
	                {
	                    if (krs.getKursiyerAdSoyad().equals(ad_soyad))
	                    {
	                        System.out.println("Bulunan Kursiyerin ID'si: "+krs.getKursiyerID());
	                        System.out.println("Bulunan Kursiyerin Ismi ve Soyismi: "+krs.getKursiyerAdSoyad());
	                        System.out.println("Bulunan Kursiyerin Yasi: "+krs.getKursiyerYas());
	                        System.out.println("Kursiyerin aldigi kurslar: ");
	        			 		for (Kurs kurs : krs.alinanKurslar)
	        			 		{
	        			 	        System.out.println("     "+kurs.getKursID()+ " "+kurs.getKursAd());
	        			 	    }

	                        bulundu = true;
	                    }
	                }
					if(bulundu == false)
					{
						System.out.println("Aranan isimde bir kursiyer bulunamadi.");
					}


			}
			else if(secim == 7)
			{
				boolean bulundu = false;
				String krs_ad;
				scan.nextLine();
				System.out.println("Silinecek olan kursiyerin ID'sini giriniz: ");
				int krsiyer_id = scan.nextInt();
				for(Kursiyer krs:kursiyerler)
				{
				  if(krs.getKursiyerID() == krsiyer_id)
				  {
					    bulundu = true;
					    kursiyerler.remove(krs);
					    System.out.println("Kursiyer Silindi");
						break;

				  }
				}
				if(bulundu == false)
				{
					System.out.println("Silinmesi Istenen Kursiyer Bulunamadi");
				}

			}
			else if(secim == 8)
			{
				System.out.println(kalin + "Tum Kursiyerler" + normal);
				for(Kursiyer krsyr:kursiyerler)
				{
					System.out.println(krsyr.getKursiyerID()+" "+krsyr.getKursiyerAdSoyad()+" "+krsyr.getKursiyerYas());
				}
			}
			else if(secim == 9)
			{
				System.out.println(kalin + "Tum Kursiyerler ve Aldiklari Kurslar" + normal);
				for(Kursiyer krsyr:kursiyerler)
				{
					System.out.println(krsyr.getKursiyerID()+" "+krsyr.getKursiyerAdSoyad()+" "+krsyr.getKursiyerYas());
			 		for (Kurs kurs : krsyr.alinanKurslar)
			 		{
			 	        System.out.println("     "+kurs.getKursID()+ " "+kurs.getKursAd());
			 	    }
				}

			}
			else if(secim == 10)
			{
				System.out.println("Odeyecegi ucret hesaplanacak olan kursiyerin ID'sini giriniz: ");
				int krs_id = scan.nextInt();

				boolean bulundu = false;
				for(Kursiyer krs:kursiyerler)
				{
				  if(krs.getKursiyerID() == krs_id)
				  {
					    bulundu = true;
					    krs.BorcHesapla();
						break;

				  }
				}
				if(bulundu == false)
				{
					System.out.println("Girilen ID degerine sahip bir kursiyer bulunamadi");
				}


			}
			else if(secim == 11)
			{
				FileWriter fw = new FileWriter(kursiyer_dosyam);
				BufferedWriter bw = new BufferedWriter(fw);

				for(Kursiyer krsyr:kursiyerler)
				{
					bw.write("*"+krsyr.getKursiyerID()+"%"+krsyr.getKursiyerAdSoyad()+"%"+krsyr.getKursiyerYas()+"\n");
			 		for (Kurs kurs : krsyr.alinanKurslar)
			 		{
			 			bw.write("+"+kurs.getKursID()+"%"+kurs.getKursAd()+"\n");
			 	    }
				}
				bw.flush();

				FileWriter fw2 = new FileWriter(kurs_dosyam);
				BufferedWriter bw2 = new BufferedWriter(fw2);

				for(Kurs krs:kurslar)
				{
					bw2.write(krs.getKursID()+"%"+krs.getKursAd()+"\n");

				}
				bw2.flush();



			  break;
			}
			else
			{
				System.out.println("Hatali secim yaptiniz. Tekrar deneyin.");
			}
		}
	}

}
