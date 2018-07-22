package intf.constant;

public interface Interrupt {

    int CLOSE_CURRENT_TASK = 0x1a;

    int CLOSE_ALL_TASK = 0x1b;

    int PRINT_ALL_TASK = 0x1c;

    int PRINT_ALL_CONCEPT = 0x1d;

    int WRITE_TO_BUFFER = 0x2a;

    int CLEAR_MESSAGE = 0x3a;

    int CLEAR_BUFFER = 0x3b;

    int PRESS_KEY = 0x4a;

    int RELEASE_KEY = 0x4b;

    int FLUSH_SCREEN = 0x7a;

    int FORCE_EXIT = 0x9a;

}
