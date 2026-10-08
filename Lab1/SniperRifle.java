public class SniperRifle extends Firearm {
    private int scopeZoom;
    private boolean bipodDeployed;
    private int effectiveRange;
    private static final int MAX_ZOOM = 25;

    public SniperRifle(String name, double weight, int condition,
                            String caliber, int magazineCapacity, int ammoInMagazine,
                            int scopeZoom, int effectiveRange) {
        super(name, weight, condition, caliber, magazineCapacity, ammoInMagazine);
        setScopeZoom(scopeZoom);
        setEffectiveRange(effectiveRange);
        bipodDeployed = false;
    }

    @Override 
    public void describe() {
        super.describe();
        System.out.println("Прицел: x" + scopeZoom +
            ", дальность: " + effectiveRange + " м" +
            ", сошки: " + (bipodDeployed ? "разложены" : "сложены"));
    }

    public void toggleBipod() {
        bipodDeployed = !bipodDeployed;
        if (bipodDeployed){
            System.out.println(getName() + ": сошки разложены");
        } else {
            System.out.println(getName() + ": сошки сложены");
        }
    }

    public boolean aimedShot() {
        if (bipodDeployed) {
            return shoot();
        } else {
            System.out.println(getName() + ": для прицельного выстрела сошки должны быть разложены");
            return false;
        }
    }

    public void zoomIn(){
        scopeZoom = Math.min(scopeZoom + 1, MAX_ZOOM);
        System.out.println(getName() + ": кратность x" + scopeZoom);
    }

    public void zoomOut(){
        scopeZoom = Math.max(scopeZoom - 1, 1);
        System.out.println(getName() + ": кратность x" + scopeZoom);
    }

    public int getScopeZoom() {return scopeZoom;}

    public boolean isBipodDeployed() {return bipodDeployed;}

    public int getEffectiveRange() {return effectiveRange;}


    public void setScopeZoom(int scopeZoom) {
        if (scopeZoom <= 0) {
            throw new IllegalArgumentException(getName() + ": кратность прицела должна быть больше нуля");
        }
        if (scopeZoom > MAX_ZOOM) {
            throw new IllegalArgumentException(getName() + ": кратность прицела не может превышать " + MAX_ZOOM);
        }
        this.scopeZoom = scopeZoom;
    }

    public void setEffectiveRange(int effectiveRange) {
        if (effectiveRange <= 0) {
            throw new IllegalArgumentException(getName() + ": дальность должна быть больше нуля");
        }
        this.effectiveRange = effectiveRange;
    }

    public void setBipodDeployed(boolean bipodDeployed){
        this.bipodDeployed = bipodDeployed;
    }
}
