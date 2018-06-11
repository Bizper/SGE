package constant;

public interface DefaultConstant {

    int DEFAULT_ID = -1;

    int MIN_HP = 0;

    int MIN_MP = 0;

    int DEFAULT_HP = 100;

    int DEFAULT_MP = 100;

    int MIN_ATTACK = 0;

    int DEFAULT_ATTACK = 10;

    int MIN_DEFENCE = 0;

    int DEFAULT_DEFENCE = 5;

    String DEFAULT_NAME = "";

    int DEFAULT_PACK_SIZE = 40;

    int MIN_LEVEL = 0;

    int DEFAULT_LEVEL = 0;

    int MAX_LEVEL = 100;

    int MIN_MOVE_SPEED = 0;

    int DEFAULT_MOVE_SPEED = 100;

    int MAX_MOVE_SPEED = 550;

    int MAX_SPELL_NUMBER = 5;

    double DEFAULT_DEFENCE_SENSOR = 0.06;

    StatusType DEFAULT_STATUS = StatusType.STATUS_DEFAULT;

    int MAX_MAP_SIZE = 100;

    /**
     * WINDOWS PROPERTIES
     */

    int WIN_WIDTH = 1290;

    int WIN_HEIGHT = 768;

    String DEFAULT_TITLE = "MUD GAME";

    /**
     * THREAD PROPERTIES
     */

    int FRAME_PER_SECOND = 1000/60;

}
