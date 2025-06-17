package io.github.cleri.epsicicdrpg.back.model.character;


public class CharacterStats {
    private int atk;
    private int def;
    private int hp;
    private int regen;
    private int speed;

    public CharacterStats(int atk, int def, int hp, int regen, int speed) {
        this.atk = atk;
        this.def = def;
        this.hp = hp;
        this.regen = regen;
        this.speed = speed;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public int getHp() {
        return hp;
    }

    public int getRegen() {
        return regen;
    }

    public int getSpeed() {
        return speed;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setRegen(int regen) {
        this.regen = regen;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}