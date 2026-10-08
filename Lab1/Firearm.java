public class Firearm extends Weapon {
    private String caliber;
    private int magazineCapacity;
    private int ammoInMagazine;

    public Firearm(String name, double weight, int condition,
                   String caliber, int magazineCapacity, int ammoInMagazine) {
        super(name, weight, condition);
        setCaliber(caliber);
        setMagazineCapacity(magazineCapacity);
        setAmmoInMagazine(ammoInMagazine);
    }

    @Override
    public void describe() {
        super.describe();
        System.out.println("Калибр: " + caliber
                + ", патроны: " + ammoInMagazine + "/" + magazineCapacity);
    }

    public boolean shoot() {
        if (getCondition() == 0) {
            System.out.println(getName() + ": оружие сломано, выстрел невозможен");
            return false;
        }
        if (ammoInMagazine == 0) {
            System.out.println(getName() + ": нет патронов");
            return false;
        }
        System.out.println(getName() + ": Выстрел!");
        ammoInMagazine--;
        wear(1);
        return true;
    }

    public int reload(int rounds) {
        if (rounds < 0) {
            throw new IllegalArgumentException(getName() + ": количество патронов не может быть отрицательным");
        }
        int freeSpace = magazineCapacity - ammoInMagazine;
        int loaded = Math.min(rounds, freeSpace);
        ammoInMagazine += loaded;
        return rounds - loaded;
    }

    public int unload() {
        int removed = ammoInMagazine;
        ammoInMagazine = 0;
        return removed;
    }

    public String getCaliber() {return caliber;}

    public int getMagazineCapacity() {return magazineCapacity;}

    public int getAmmoInMagazine() {return ammoInMagazine;}

    public void setCaliber(String caliber) {
        if (caliber == null || caliber.isBlank()){
            throw new IllegalArgumentException(getName() + ": калибр не может быть пустым");
        }
        this.caliber = caliber;
    }

    public void setMagazineCapacity(int magazineCapacity) {
        if (magazineCapacity <= 0){
            throw new IllegalArgumentException(getName() + ": ёмкость магазина должна быть больше нуля");
        }
        this.magazineCapacity = magazineCapacity;
        ammoInMagazine = Math.min(ammoInMagazine, magazineCapacity);
    }

    public void setAmmoInMagazine(int ammoInMagazine) {
        if (ammoInMagazine < 0){
            throw new IllegalArgumentException(getName() + ": число патронов не может быть отрицательным");
        }
        if (ammoInMagazine > magazineCapacity){
            throw new IllegalArgumentException(getName() + ": число патронов не может превышать ёмкость магазина");
        }
        this.ammoInMagazine = ammoInMagazine;
    }
}