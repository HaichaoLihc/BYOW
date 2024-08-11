package core;

import tileengine.TETile;
import tileengine.Tileset;

import java.util.ArrayList;
import java.util.Random;



public class World {
    int screenWidth;
    int screenHeight;
    TETile[][] grid;
    Random random;
    private ArrayList<Room> rooms;

    public World(int sw, int sh, int seed) {
        screenHeight = sh;
        screenWidth = sw;
        grid = new TETile[screenWidth][screenHeight];
        this.rooms = new ArrayList<>();
        random = new Random(seed);
    }

    public void creatWorld() {
        for (int i = 0; i < screenWidth; i++) {
            for (int j = 0; j < screenHeight; j++) {
                grid[i][j] = Tileset.NOTHING; // Floor
            }
        }
        int roomNum = random.nextInt(10, 20);
        for (int i = 0; i < roomNum;) {
            int roomWidth = random.nextInt(10, 20);
            int roomHeight = random.nextInt(10, 20);
            int roomPosX = random.nextInt(0, screenWidth - roomWidth);
            int roomPosY = random.nextInt(0,screenHeight - roomHeight);
            Room newRoom = new Room(roomPosX, roomPosY, roomWidth, roomHeight);

            boolean overlaps = false;
            for (Room room : rooms) {
                if (newRoom.intersects(room)) {
                    overlaps = true;
                    break;
                }
            }

            if (!overlaps) {
                createRoom(newRoom);
                if (!rooms.isEmpty()) {
                    Room prevRoom = rooms.get(rooms.size() - 1);
                }
                rooms.add(newRoom);
                i++;
            }
        }
    }

    public void createRoom(Room room) {
        for (int i = room.x; i < room.x + room.width; i++) {
            for (int j = room.y; j < room.y + room.height; j++) {
                grid[i][j] = Tileset.WALL;
            }
        }
    }

    public TETile[][] getGrid() {
        return grid;
    }

    private static class Room {
        int x, y, width, height;

        public Room(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public int centerX() {
            return x + width / 2;
        }

        public int centerY() {
            return y + height / 2;
        }

        public boolean intersects(Room other) {
            return x < other.x + other.width &&
                    x + width > other.x &&
                    y < other.y + other.height &&
                    y + height > other.y;
        }
    }
}
