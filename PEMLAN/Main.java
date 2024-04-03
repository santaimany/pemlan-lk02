
import java.util.Scanner;
import java.util.ArrayList;

class Kendaraan {
    private String merk;
    private String warna;
    private String platNomor;
    public int jumlahPenumpang = 0;
    public int maxPenumpang;
    public Driver supir;
    public ArrayList<Penumpang> penumpang;
    boolean isCustomerFind;

    Kendaraan(String merk, String warna, String platNomor, int maxPenumpang, Driver supir) {
        this.merk = merk;
        this.warna = warna;
        this.platNomor = platNomor;
        this.maxPenumpang = maxPenumpang;
        penumpang = new ArrayList<>();
        this.supir = supir;
    }

    public void display(){
        System.out.print(" dengan merk " + this.merk + " bewarna " + this.warna + " dengan plat nomor " + this.platNomor + "\n");
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public void setPlatNomor(String platNomor) {
        this.platNomor = platNomor;
    }

    public void setMaxPenumpang(int maxPenumpang) {
        this.maxPenumpang = maxPenumpang;
    }

    public String getMerk(){
        return this.merk;
    }

    public String getWarna(){
        return this.warna;
    }

    public String getPlatNomor(){
        return this.platNomor;
    }



    public ArrayList<Penumpang> getPenumpang() {
        System.out.println("Daftar nama penumpang :");
        for (Penumpang p : penumpang){
            System.out.println("Penumpang : " + p.name );
        }
        return this.penumpang;
    }

    public void namaPenumpang(Penumpang penumpangs){
        this.penumpang.add(penumpangs);
    }


    public int penumpangNaik(int penumpangnaik) {
        return this.jumlahPenumpang += penumpangnaik;
    }


    public void penumpangTurun() {
        if (isCustomerFind == true) {
            this.jumlahPenumpang--;
        }
    }

    public void penumpangTurun(String namaPenumpang) {
        isCustomerFind = false;
        for (Penumpang p : this.penumpang) {
            if (p.name.equalsIgnoreCase(namaPenumpang)) {
                this.penumpang.remove(p);
                isCustomerFind = true;
                break;
            }
        }
        if (!isCustomerFind) {
            System.out.println("Tidak ada penumpang dengan nama " + namaPenumpang + " di dalam kendaraan.");
        }
    }

    public void cekJumlahPenumpang() {
        if (this.jumlahPenumpang <= this.maxPenumpang){
            System.out.println("Jumlah penumpang " + this.jumlahPenumpang);
    } else {
            this.jumlahPenumpang = maxPenumpang;
            System.out.println(("Jumlah penumpang " + this.jumlahPenumpang));
    }

}
    public String getmaxPenumpang(){
         return "Jumlah maksimal kendaraan " + this.maxPenumpang;
    }



}

class Truck extends Kendaraan{
    private int maxKapasitasMuatan;

    public int isiMuatan = 0;

    public Truck(String merk, String warna, String platNomor, int maxPenumpang, Driver supir, int maxkapasitasMuatan) {
        super(merk, warna, platNomor, maxPenumpang, supir);
        this.maxKapasitasMuatan =  maxkapasitasMuatan;
    }
    public void setMaxKapasitasMuatan(int maxKapasitasMuatan){
        this.maxKapasitasMuatan = maxKapasitasMuatan;
    }
    public int getMuatan(){
        return this.isiMuatan;
    }
    public int getMaxKapasitasMuatan() {
        return this.maxKapasitasMuatan;
    }

    public boolean cekDriver(String driverName) {
        for (Penumpang p : penumpang) {
            if (p.getName().equalsIgnoreCase(driverName)) {
                return true;
            }
        }
        return false;
    }
    public int penumpangNaik(int naik, String driverName) {
        Scanner in = new Scanner(System.in);
        if (!cekDriver(driverName)) {
            System.out.println("Driver tidak terdaftar sebagai penumpang.");
            return this.jumlahPenumpang;
        }
        this.supir.setName(driverName);
        System.out.print("Masukkan no sim :");
        String noSim = in.next();
        this.supir.setNo_sim(noSim);
        return this.jumlahPenumpang += naik;
    }
    public int isiMuatan(int muatan){
        int temp = muatan;
        if(temp > getMaxKapasitasMuatan()){
            return getMaxKapasitasMuatan();
        } else {
            return this.isiMuatan += muatan;
        }
    }


}

class Bus extends Kendaraan{
    public int ticket;
    public boolean isPay = false;
    public Bus(String merk, String warna, String platNomor, int ticket, int maxPenumpang, Driver supir) {
        super(merk, warna, platNomor, maxPenumpang, supir);
        this.ticket = ticket;
    }


    public int penumpangNaik(int penumpangNaik, int payTicket){
        if(payTicket >= this.ticket * penumpangNaik){
            isPay = true;
            payTicket -= this.ticket * penumpangNaik;
            System.out.println("Uang kembalian : " + payTicket );
            System.out.println("Pembayaran anda berhasil silahkan naik!");
            this.jumlahPenumpang +=penumpangNaik;
            return this.jumlahPenumpang;
        }
        else {
            System.out.println("Uang anda tidak mencukupi");
            isPay = false;
            return this.jumlahPenumpang;
        }

    }
}

class Driver{
    private String no_sim;
    private String name;
    public Driver(String name, String no_sim ){
        this.name = name;
        this.no_sim = no_sim;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNo_sim(String no_sim) {
        this.no_sim = no_sim;
    }

    public String getNoSim(){
        return this.no_sim;
    }

    public String getName(){
        return this.name;
    }
}

class Penumpang{
    public String name;
    public String getName() {
        return name;
    }


}



public class Main {
    public static void main(String[] args) {
            Scanner in = new Scanner(System.in);

            Driver driverMobil = new Driver("Mulyono", "5678998765");
            Kendaraan mobil1 = new Kendaraan("Toyota", "Hitam", "N 10101 BM", 4, driverMobil);

            Driver driverTruk = new Driver(null,null);
            Truck truk1 = new Truck("Daihatsi", "Merah", "N 20202 BN", 2, driverTruk, 100);

            Driver driverBus = new Driver("Mulyani", "123454321");
            Bus bus1 = new Bus("Mercedes", "Putih", "N 30303 BB", 10_000, 10, driverBus);
            int num = 1;

            Kendaraan jenisKendaraan = null;

            System.out.println("Silahkan pilih kendaraan\n 1. Mobil\n 2. Truk\n 3. Bus");
            System.out.print("Pilih menu : ");
            int switchKendaraan = in.nextInt();

            switch (switchKendaraan){
                case 1 :jenisKendaraan = mobil1;
                    System.out.print("\nAnda telah memilih Mobil" );
                    jenisKendaraan.display();
                break;
                case 2 : jenisKendaraan = truk1;
                    System.out.print("\nAnda telah memilih Truk" );
                    jenisKendaraan.display();
                break;
                case 3 : jenisKendaraan = bus1;
                    System.out.print("\nAnda telah memilih Bus"  );
                    jenisKendaraan.display();
            }



            while (num != 0){
                if(jenisKendaraan == truk1){
                    System.out.println("\nSilahkan pilih menu\n 0. Keluar\n 1. Penumpang Naik\n 2. Penumpang Turun\n 3. Cek Jumlah Penumpang\n 4. Cek Daftar Nama Penumpang\n 5. Cek Nama Pengemudi\n 6. Cek Kapasitas Truk\n 7. Mengisi Muatan\n 8. Cek Muatan");
                    System.out.print("pilih menu : ");
                    num = in.nextInt();

                } else {
                    System.out.println("\nSilahkan pilih menu\n 0. Keluar\n 1. Penumpang Naik\n 2. Penumpang Turun\n 3. Cek Jumlah Penumpang\n 4. Cek Daftar Nama Penumpang\n 5. Cek Nama Pengemudi");
                    System.out.print("pilih menu : ");
                    num = in.nextInt();
                }
                switch (num){
                    case 0:
                        return;
                    case 1 :
                        System.out.print("Jumlah penumpang naik :");
                        int naik = in.nextInt();
                        in.nextLine();
                        int temp = jenisKendaraan.jumlahPenumpang + naik;

                        if (temp >= jenisKendaraan.maxPenumpang){
                            int remainingPassenger = jenisKendaraan.maxPenumpang - jenisKendaraan.jumlahPenumpang;
                            if(remainingPassenger <= 0){
                                System.out.println("Maaf kendaraan sudah penuh");
                                break;
                            }
                            System.out.println("Kursi kendaraan tersisa " + remainingPassenger + " Kursi");
                            System.out.println("Apakah anda akan tetap membeli " + remainingPassenger + " Kursi tersebut? (iya/tidak):");

                            String decide = in.nextLine();
                            if(decide.equalsIgnoreCase("iya")){
                                naik = remainingPassenger;
                            } else if (decide.equalsIgnoreCase("tidak")) {
                                break;
                            }
                        }
                        if(jenisKendaraan.equals(bus1) ){
                            System.out.println("Masukkan nama penumpang :");
                            System.out.println("Tiket seharga Rp. " + 10_000 * naik);
                            System.out.println("Masukkan uang Anda: ");
                            int payTicket = in.nextInt();
                            in.nextLine();
                            ((Bus)jenisKendaraan).penumpangNaik(naik, payTicket);
                            if(payTicket < payTicket * naik){
                                break;
                            }

                            for (int i = 1; i <= naik; i++){
                                Penumpang penumpang = new Penumpang();
                                System.out.println("Penumpang " + i + " :");
                                penumpang.name = in.nextLine();
                                jenisKendaraan.namaPenumpang(penumpang);
                            }

                        }else{
                            System.out.println("Masukkan nama penumpang :");
                            for (int i = 1; i <= naik; i++){
                                Penumpang penumpang = new Penumpang();
                                System.out.println("Penumpang " + i + " :");
                                penumpang.name = in.nextLine();
                                jenisKendaraan.namaPenumpang(penumpang);
                            }
                            if (jenisKendaraan == truk1){
                                System.out.print("Siapa yang ingin menjadi driver? : ");
                                String driverName = in.nextLine();
                                if (!((Truck) jenisKendaraan).cekDriver(driverName)) {
                                    System.out.println("Driver tidak terdaftar sebagai penumpang.");
                                    break;
                                }
                                ((Truck) jenisKendaraan).penumpangNaik(naik, driverName);
                            }
                            jenisKendaraan.penumpangNaik(naik);
                            System.out.println(jenisKendaraan.getmaxPenumpang());
                        }
                        break;
                    case 2 :
                        System.out.print("Jumlah penumpang turun :");
                        int turun = in.nextInt();
                        String namaPenumpang = "";
                        in.nextLine();
                        System.out.println("Siapa yang turun :");
                        for (int i = 1; i <= turun;i++){
                            System.out.println("Nama : ");

                            namaPenumpang = in.nextLine();
                            jenisKendaraan.penumpangTurun(namaPenumpang);
                            jenisKendaraan.penumpangTurun();
                        }

                        break;
                    case 3  : jenisKendaraan.cekJumlahPenumpang();
                        System.out.println(jenisKendaraan.getmaxPenumpang());
                    break;
                    case 4 : jenisKendaraan.getPenumpang();
                    break;

                    case 5:
                        System.out.println("Nama Pengemudi: " + jenisKendaraan.supir.getName());
                        System.out.println("Jenis Kendaraan: " + jenisKendaraan.getClass());
                        System.out.println("No SIM Pengemudi: " + jenisKendaraan.supir.getNoSim());
                        break;
                    case 6:
                        if (jenisKendaraan == truk1){
                            System.out.println("Kapasitas muatan dari truck " + ((Truck) jenisKendaraan).getMaxKapasitasMuatan() + "Kg");
                            break;
                        }

                    case 7:
                        if(jenisKendaraan == truk1){
                            System.out.print("isi muatan truk : ");
                            int muatan = in.nextInt();
                            int tempo = muatan + ((Truck)jenisKendaraan).isiMuatan;
                            if(tempo > ((Truck) jenisKendaraan).getMaxKapasitasMuatan()){
                                int remainingTempo = (((Truck) jenisKendaraan).getMaxKapasitasMuatan() - ((Truck) jenisKendaraan).isiMuatan);
                                if (remainingTempo <= 0){
                                    System.out.println("Maaf muatan truk penuh!");
                                    break;
                                }
                                System.out.println("Muatan yang bisa masuk hanya "+ remainingTempo + "kg, Apakah anda tetap ingin memasukkan muatan?(ya/tidak)");
                                String choice = in.next();
                                if(choice.equalsIgnoreCase("ya")){
                                    ((Truck)jenisKendaraan).isiMuatan = ((Truck) jenisKendaraan).getMaxKapasitasMuatan();
                                    break;
                                } else if(choice.equalsIgnoreCase("tidak")){
                                    break;
                                } else {
                                    System.out.println("pilihan tidak valid");
                                    break;
                                }
                            }
                            ((Truck)jenisKendaraan).isiMuatan(muatan);
                            break;
                        }

                    case 8:
                        if(jenisKendaraan == truk1){
                            System.out.println("Muatan saat ini : " + ((Truck)jenisKendaraan).getMuatan() + "kg");
                            break;
                        }



                    default:
                        System.out.println("Pilihan tidak valid. Silahkan pilih ulang!");

                }
            }
    }
}
