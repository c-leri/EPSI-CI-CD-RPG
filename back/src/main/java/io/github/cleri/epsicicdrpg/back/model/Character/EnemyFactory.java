package io.github.cleri.epsicicdrpg.back.model.character;

public class EnemyFactory {
    public Enemy create(String difficulty, int roomIndex) {
        // Stats stats = new Stats(50 + 10 * roomIndex, 5 + roomIndex);
        int exp = 10 * (roomIndex + 1);
        int gold = 5 * (roomIndex + 1);
        return new Enemy(difficulty, null, gold, gold);
    }
}