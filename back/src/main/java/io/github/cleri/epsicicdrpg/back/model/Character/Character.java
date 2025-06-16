package main.java.io.github.cleri.epsicicdrpg.back.model.Encounter;

public class Character {
    private String name;
    private CharacterStats stats;

    public Character(String name, CharacterStats stats) {
        this.name = name;
        this.stats = stats;
    }

    public String getName() {
        return name;
    }

    public CharacterStats getStats() {
        return stats;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStats(CharacterStats stats) {
        this.stats = stats;
    }
}