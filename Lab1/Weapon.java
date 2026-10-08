public class Weapon {
    private String name;
    private double weight;
    private int condition;

    public Weapon(String name, double weight, int condition) {
        setName(name);
        setWeight(weight);
        setCondition(condition);
    }

    public void describe() {
        System.out.println("Название: " + name +
                ", вес (кг): " + weight +
                ", состояние: " + condition);
    }

    public void wear(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Износ не может быть отрицательным");
        }
        condition = Math.max(0, condition - amount);
    }

    public void repair(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Ремонт не может быть отрицательным");
        }
        condition = Math.min(100, condition + amount);
    }

    public String getName() {return name;}

    public double getWeight() {return weight;}

    public int getCondition() {return condition;}

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название не может быть пустым");
        }
        this.name = name;
    }

    public void setWeight(double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Вес должен быть больше нуля");
        }
        this.weight = weight;
    }

    public void setCondition(int condition) {
        if (condition < 0 || condition > 100) {
            throw new IllegalArgumentException("Некорректное состояние (от 0 до 100)");
        }
        this.condition = condition;
    }

}