package intf.constant;

import types.StatusType;

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

    int CHARACTER_WIDTH = 20;

    int CHARACTER_HEIGHT = 20;

    /**
     * WINDOWS PROPERTIES
     */

    int WIN_WIDTH = 1280;

    int WIN_HEIGHT = 760;

    String DEFAULT_TITLE = "MUD GAME";

    /**
     * THREAD PROPERTIES
     */

    int FRAME_PER_SECOND = 1000/60;

    String ASSET_IMAGE_PATH = "./src/asset/";

    int DEFAULT_BLOCK_SIZE = 20;

    int MAX_MAP_SIZE_WIDTH = WIN_WIDTH - (DEFAULT_BLOCK_SIZE << 1);//防止贴边

    int MAX_MAP_SIZE_HEIGHT = WIN_HEIGHT - (DEFAULT_BLOCK_SIZE << 1);

}
