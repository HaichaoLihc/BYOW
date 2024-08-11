package core;
import tileengine.*;

public class Main {
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(100, 60);
        World world = new World(100, 60, 190);
        world.creatWorld();
        ter.renderFrame(world.getGrid());
    }
}
