public class Main {
    public static void main(String[] args) {

        System.out.println("=== Создание винтовки ===");
        SniperRifle rifle = new SniperRifle("СВ-98", 6.2, 100,
                "7.62x54R", 10, 3,
                8, 1000);
        rifle.describe();

        System.out.println("\n=== Методы SniperRifle ===");
        rifle.aimedShot();          // сошки сложены — выстрела не будет
        rifle.toggleBipod();        // раскладываем сошки
        rifle.aimedShot();          // теперь выстрел проходит
        rifle.zoomIn();
        rifle.zoomIn();
        rifle.zoomOut();

        System.out.println("\n=== Методы Firearm ===");
        int shots = 0;
        while (rifle.shoot()) {     // стреляем, пока shoot() возвращает true
            shots++;
        }
        System.out.println("Сделано выстрелов: " + shots);

        int left = rifle.reload(15);
        System.out.println("Не поместилось в магазин: " + left);
        System.out.println("В магазине: " + rifle.getAmmoInMagazine()
                + "/" + rifle.getMagazineCapacity());

        int removed = rifle.unload();
        System.out.println("Извлечено патронов: " + removed);
        rifle.reload(5);

        System.out.println("\n=== Методы Weapon ===");
        rifle.wear(200);
        System.out.println("Состояние после износа: " + rifle.getCondition());
        rifle.aimedShot();          // оружие сломано — выстрела не будет
        rifle.repair(60);
        System.out.println("Состояние после ремонта: " + rifle.getCondition());
        rifle.aimedShot();          // после ремонта снова стреляет

        System.out.println("\n=== Геттеры и сеттеры ===");
        rifle.setName("СВ-98М");
        rifle.setScopeZoom(12);
        rifle.toggleBipod();        // складываем сошки
        System.out.println("Калибр: " + rifle.getCaliber());
        System.out.println("Дальность: " + rifle.getEffectiveRange() + " м");
        rifle.describe();

        System.out.println("\n=== Полиморфизм ===");
        Weapon[] arsenal = {
                new Weapon("Нож", 0.3, 90),
                new Firearm("ПМ", 0.73, 100, "9x18", 8, 8),
                rifle
        };
        for (Weapon w : arsenal) {
            w.describe();           // у каждого объекта вызывается своя версия
            System.out.println();
        }

        System.out.println("=== Проверка некорректных данных ===");
        try {
            new SniperRifle("Ошибка", -2, 100, "7.62x54R", 10, 0, 8, 1000);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            rifle.setAmmoInMagazine(50);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            rifle.setScopeZoom(30);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            rifle.repair(-10);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}